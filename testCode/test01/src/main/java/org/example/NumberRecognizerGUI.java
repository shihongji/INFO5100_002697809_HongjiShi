package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import org.tensorflow;
import org.tensorflow.contrib.android.TensorFlowInferenceInterface;
import java.util.*;
import java.nio.file.*;
import java.util.List;
public class NumberRecognizerGUI extends JFrame {
    private static final int CANVAS_WIDTH = 200;
    private static final int CANVAS_HEIGHT = 200;
    private static final int DIGIT_SIZE = 28;
    private static final int LABEL_SIZE = 10;
    private static final String[] LABEL_NAMES = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    private static final String MODEL_FILE = "model.pb";
    private static final String LABEL_FILE = "labels.txt";

    private JPanel canvasPanel;
    private BufferedImage canvasImage;
    private Graphics2D canvasGraphics;
    private JButton clearButton;
    private JLabel resultLabel;
    private JLabel probabilityLabel;

    private float[] probabilities;
    private List<String> labelList;
    private TensorFlowInferenceInterface inferenceInterface;

    public NumberRecognizerGUI() {
        setTitle("Number Recognizer");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        getContentPane().add(topPanel);

        canvasPanel = new JPanel();
        canvasPanel.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
        canvasPanel.setBackground(Color.WHITE);
        canvasPanel.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent event) {
                int x = event.getX();
                int y = event.getY();
                canvasGraphics.fillOval(x - DIGIT_SIZE / 2, y - DIGIT_SIZE / 2, DIGIT_SIZE, DIGIT_SIZE);
                canvasPanel.repaint();
            }
        });
        topPanel.add(canvasPanel, BorderLayout.CENTER);

        canvasImage = new BufferedImage(CANVAS_WIDTH, CANVAS_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        canvasGraphics = canvasImage.createGraphics();
        canvasGraphics.setColor(Color.BLACK);

        clearButton = new JButton("Clear");
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                canvasGraphics.clearRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);
                canvasPanel.repaint();
                resultLabel.setText("Result: ");
                probabilityLabel.setText("Probability: ");
            }
        });
        topPanel.add(clearButton, BorderLayout.SOUTH);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(2, 1));
        getContentPane().add(bottomPanel, BorderLayout.SOUTH);

        resultLabel = new JLabel("Result: ");
        bottomPanel.add(resultLabel);

        probabilityLabel = new JLabel("Probability: ");
        bottomPanel.add(probabilityLabel);

        probabilities = new float[LABEL_SIZE];
        labelList = new ArrayList<String>();
        try {
            byte[] graphDef = Files.readAllBytes(Paths.get(MODEL_FILE));
            inferenceInterface = new TensorFlowInferenceInterface(graphDef);
            InputStream labelInput = getClass().getResourceAsStream(LABEL_FILE);
            BufferedReader labelReader = new BufferedReader(new InputStreamReader(labelInput));
            String line;
            while ((line = labelReader.readLine()) != null) {
                labelList.add(line);
            }
            labelReader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        setVisible(true);
    }

    public void recognizeNumber() {
        Image scaledImage = canvasImage.getScaledInstance(DIGIT_SIZE, DIGIT_SIZE, Image.SCALE_SMOOTH);
        BufferedImage digitImage = new BufferedImage(DIGIT_SIZE, DIGIT_SIZE, BufferedImage.TYPE_INT);    digitImage.getGraphics().drawImage(scaledImage, 0, 0, null);

        // Flatten the image into a 1D array of floats between 0 and 1.
        float[] pixels = new float[DIGIT_SIZE * DIGIT_SIZE];
        int pixelIdx = 0;
        for (int y = 0; y < DIGIT_SIZE; y++) {
            for (int x = 0; x < DIGIT_SIZE; x++) {
                int color = digitImage.getRGB(x, y);
                int alpha = (color >> 24) & 0xff;
                int red = (color >> 16) & 0xff;
                int green = (color >> 8) & 0xff;
                int blue = color & 0xff;
                float gray = (0.2126f * red + 0.7152f * green + 0.0722f * blue) / 255.0f;
                pixels[pixelIdx++] = gray;
            }
        }

        // Perform the inference.
        inferenceInterface.feed("input", pixels, 1, DIGIT_SIZE, DIGIT_SIZE, 1);
        inferenceInterface.run(new String[] {"output"});
        inferenceInterface.fetch("output", probabilities);

        // Find the index of the maximum probability.
        int maxIdx = 0;
        for (int i = 1; i < LABEL_SIZE; i++) {
            if (probabilities[i] > probabilities[maxIdx]) {
                maxIdx = i;
            }
        }

        // Update the result label and probability label.
        resultLabel.setText("Result: " + LABEL_NAMES[maxIdx]);
        probabilityLabel.setText(String.format("Probability: %.2f%%", probabilities[maxIdx] * 100));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                NumberRecognizerGUI gui = new NumberRecognizerGUI();
                new Timer(1000 / 60, new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        gui.recognizeNumber();
                    }
                }).start();
            }
        });
    }