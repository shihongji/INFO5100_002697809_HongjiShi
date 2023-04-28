import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.imageio.ImageIO;

public class ImageConverterGUI extends JFrame implements ActionListener {

    private JButton uploadButton, downloadButton;
    private JLabel thumbnailLabel, propertiesLabel;
    private JCheckBox jpegCheckBox, pngCheckBox, bmpCheckBox;
    private JFileChooser fileChooser;
    private File[] selectedFiles;

    public ImageConverterGUI() {
        super("Image Converter");

        // Create GUI components
        uploadButton = new JButton("Upload");
        downloadButton = new JButton("Download");
        thumbnailLabel = new JLabel();
        propertiesLabel = new JLabel();
        jpegCheckBox = new JCheckBox("JPEG");
        pngCheckBox = new JCheckBox("PNG");
        bmpCheckBox = new JCheckBox("BMP");
        fileChooser = new JFileChooser();

        // Add action listeners to buttons
        uploadButton.addActionListener(this);
        downloadButton.addActionListener(this);

        // Add components to panels
        JPanel uploadPanel = new JPanel();
        uploadPanel.add(uploadButton);

        JPanel thumbnailPanel = new JPanel();
        thumbnailPanel.add(thumbnailLabel);

        JPanel propertiesPanel = new JPanel();
        propertiesPanel.add(propertiesLabel);

        JPanel formatPanel = new JPanel();
        formatPanel.setLayout(new BoxLayout(formatPanel, BoxLayout.PAGE_AXIS));
        formatPanel.add(new JLabel("Select format(s) to convert:"));
        formatPanel.add(jpegCheckBox);
        formatPanel.add(pngCheckBox);
        formatPanel.add(bmpCheckBox);

        JPanel downloadPanel = new JPanel();
        downloadPanel.add(downloadButton);

        // Add panels to frame
        Container contentPane = getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));
        contentPane.add(uploadPanel);
        contentPane.add(thumbnailPanel);
        contentPane.add(propertiesPanel);
        contentPane.add(formatPanel);
        contentPane.add(downloadPanel);

        // Set frame properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == uploadButton) {
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                selectedFiles = fileChooser.getSelectedFiles();
                try {
                    // Display thumbnail and image properties
                    Image thumbnail = ImageIO.read(selectedFiles[0]).getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                    thumbnailLabel.setIcon