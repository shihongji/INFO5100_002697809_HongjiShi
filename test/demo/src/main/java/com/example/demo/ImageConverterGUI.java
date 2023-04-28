package com.example.demo;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.drew.imaging.ImageProcessingException;
import org.apache.commons.io.FileUtils;
import org.im4java.core.ConvertCmd;
import org.im4java.core.IM4JavaException;
import org.im4java.core.IMOperation;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifIFD0Directory;
public class ImageConverterGUI extends JFrame {

    private static final int THUMBNAIL_SIZE = 150;

    private JLabel imageLabel;
    private JLabel sizeLabel;
    private JLabel locationLabel;
    private JLabel dimensionsLabel;

    private JLabel cameraLabel;
    private JButton uploadButton;
    private JButton convertButton;
    private JButton downloadButton;
    private JComboBox<String> formatBox;

    /**
     *
     */
    public ImageConverterGUI() {
        setTitle("Image Converter");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        getContentPane().add(topPanel);

        imageLabel = new JLabel();
        topPanel.add(imageLabel, BorderLayout.CENTER);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(4, 1));
        topPanel.add(infoPanel, BorderLayout.SOUTH);

        sizeLabel = new JLabel();
        infoPanel.add(sizeLabel);

        locationLabel = new JLabel();
        infoPanel.add(locationLabel);

        dimensionsLabel = new JLabel();
        infoPanel.add(dimensionsLabel);


        cameraLabel = new JLabel();
        infoPanel.add(cameraLabel);

        downloadButton = new JButton("Download");
        downloadButton.setEnabled(false);
        downloadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int result = fileChooser.showOpenDialog(ImageConverterGUI.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File outputDir = fileChooser.getSelectedFile();
//                    System.out.println("outputDir: "+outputDir.getAbsolutePath());
                    File inputFile = new File(locationLabel.getText().substring(10));
//                    System.out.println(locationLabel.getText().substring(10));
//                    the inputFile.getName() returns the converted name;
                    String outputFile = outputDir.getAbsolutePath() + "/" + inputFile.getName();
                    try {
                        FileUtils.copyFile(inputFile, new File(outputFile));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        infoPanel.add(downloadButton);
//        topPanel.add(downloadButton, BorderLayout.PAGE_END);
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());
        getContentPane().add(bottomPanel, BorderLayout.SOUTH);

        uploadButton = new JButton("Upload");
        uploadButton.addActionListener(new ActionListener() {
                                           public void actionPerformed(ActionEvent event) {
                                               JFileChooser fileChooser = new JFileChooser();
                                               int result = fileChooser.showOpenDialog(ImageConverterGUI.this);
                                               if (result == JFileChooser.APPROVE_OPTION) {
                                                   File file = fileChooser.getSelectedFile();
                                                   try {
                                                       BufferedImage image = ImageIO.read(file);
                                                       Image scaledImage = image.getScaledInstance(THUMBNAIL_SIZE, THUMBNAIL_SIZE, Image.SCALE_SMOOTH);
                                                       ImageIcon icon = new ImageIcon(scaledImage);
                                                       imageLabel.setIcon(icon);
                                                       sizeLabel.setText("Size: " + FileUtils.byteCountToDisplaySize(file.length()));
                                                       locationLabel.setText("Location: " + file.getAbsolutePath());
                                                       dimensionsLabel.setText("Dimensions: " + image.getWidth() + "x" + image.getHeight());
                                                       cameraLabel.setText("Camera: " + getCameraModel(file));
                                                       convertButton.setEnabled(true);
                                                   } catch (IOException ex) {
                                                       ex.printStackTrace();
                                                   } catch (ImageProcessingException e) {
                                                       throw new RuntimeException(e);
                                                   }
                                               }
                                           }
                                       });
        bottomPanel.add(uploadButton);
        convertButton = new JButton("Convert");
        convertButton.setEnabled(false);
        convertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                try {
                    File inputFile = new File(locationLabel.getText().substring(10));
                    String outputFormat = formatBox.getSelectedItem().toString();
                    File outputFile = new File(inputFile.getParent(),
                            inputFile.getName().replaceFirst("[.][^.]+$", "") + "." + outputFormat.toLowerCase());

                    IMOperation op = new IMOperation();
                    op.addImage(inputFile.getAbsolutePath());
                    op.format(outputFormat.toLowerCase());
                    op.addImage(outputFile.getAbsolutePath());

                    ConvertCmd cmd = new ConvertCmd();
                    cmd.run(op);

                    locationLabel.setText("Location: " + outputFile.getAbsolutePath());
                    downloadButton.setEnabled(true);
                } catch (IOException | InterruptedException | IM4JavaException ex) {
                    ex.printStackTrace();
                }
            }
        });
        bottomPanel.add(convertButton);

        formatBox = new JComboBox<>(new String[]{"JPEG", "PNG", "GIF", "BMP"});
        bottomPanel.add(formatBox);

        setVisible(true);
    }
    private String getCameraModel(File file) throws IOException, JSONException, ImageProcessingException {
        Metadata metadata = ImageMetadataReader.readMetadata(file);
        ExifIFD0Directory directory = metadata.getFirstDirectoryOfType(ExifIFD0Directory.class);

        if (directory != null && directory.containsTag(ExifIFD0Directory.TAG_MODEL)) {
            return directory.getString(ExifIFD0Directory.TAG_MODEL);
        } else {
            return "Unknown";
        }
    }

    public static void main(String[] args)
    {
        new ImageConverterGUI();
    }
}

