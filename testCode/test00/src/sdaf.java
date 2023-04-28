import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.*;
import org.apache.commons.io.*;
import org.im4java.core.*;
import org.im4java.process.*;

public class ImageConverterGUI extends JFrame {
    private static final int THUMBNAIL_SIZE = 100;
    private JLabel imageLabel;
    private JLabel sizeLabel;
    private JLabel locationLabel;
    private JLabel cameraLabel;
    private JButton uploadButton;
    private JButton convertButton;
    private JComboBox<String> formatBox;
    private JButton downloadButton;

    public ImageConverterGUI() {
        setTitle("Image Converter");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        getContentPane().add(topPanel);

        imageLabel = new JLabel();
        topPanel.add(imageLabel, BorderLayout.CENTER);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(3, 1));
        topPanel.add(infoPanel, BorderLayout.SOUTH);

        sizeLabel = new JLabel();
        infoPanel.add(sizeLabel);

        locationLabel = new JLabel();
        infoPanel.add(locationLabel);

        cameraLabel = new JLabel();
        infoPanel.add(cameraLabel);

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
                        cameraLabel.setText("Camera: " + getCameraModel(file));
                        convertButton.setEnabled(true);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        bottomPanel.add(uploadButton);

        convertButton = new JButton("Convert");
        convertButton.setEnabled(false);
        convertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int result = fileChooser.showOpenDialog(ImageConverterGUI.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File outputDir = fileChooser.getSelectedFile();
                    String format = (String) formatBox.getSelectedItem();
                    File inputFile = new File(locationLabel.getText().substring(10));
                    String outputFile = outputDir.getAbsolutePath() + "/" + inputFile.getName().substring(0, inputFile.getName().lastIndexOf('.')) + "." + format;
                    try {
                        IMOperation operation = new IMOperation();
                        operation.addImage(inputFile.getAbsolutePath());
                        operation.format(format);
                        operation.addImage(outputFile);
                        ConvertCmd convertCmd = new ConvertCmd();
                        convertCmd.run(operation);
                        downloadButton.setEnabled(true);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    } catch (IM4JavaException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        bottomPanel.add(convertButton);

        formatBox = new