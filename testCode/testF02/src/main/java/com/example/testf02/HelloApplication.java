package com.example.testf02;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.image.WritablePixelFormat;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.bytedeco.javacpp.indexer.UByteRawIndexer;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Size;

import static org.bytedeco.opencv.global.opencv_core.CV_8UC4;
import static org.bytedeco.opencv.global.opencv_core.CV_8UC1;
import static org.bytedeco.opencv.global.opencv_imgproc.COLOR_RGBA2GRAY;
import static org.bytedeco.opencv.global.opencv_imgproc.INTER_AREA;
import static org.bytedeco.opencv.global.opencv_imgproc.resize;
import static org.bytedeco.opencv.global.opencv_imgproc.cvtColor;

public class HelloApplication extends Application {
    private static final int WIDTH = 300;
    private static final int HEIGHT = 300;
    private GraphicsContext graphicsContext;

    private void setUpDrawing(Canvas canvas) {
        graphicsContext = canvas.getGraphicsContext2D();

        canvas.setOnMousePressed(e -> {
            graphicsContext.beginPath();
            graphicsContext.moveTo(e.getX(), e.getY());
            graphicsContext.stroke();
        });

        canvas.setOnMouseDragged(e -> {
            graphicsContext.lineTo(e.getX(), e.getY());
            graphicsContext.stroke();
        });

        canvas.setOnMouseReleased(e -> {
            graphicsContext.closePath();
            Image drawnImage = captureCanvasImage(canvas);
            Mat matImage = imageToMat(drawnImage);
            Mat preprocessImage = preprocessImage(matImage, 28, 28);

        });
    }

    private Image captureCanvasImage(Canvas canvas) {
        WritableImage writableImage = new WritableImage((int)canvas.getWidth(), (int)canvas.getHeight());
        canvas.snapshot(null, writableImage);
        return writableImage;
    }

    private Mat imageToMat(Image image) {
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();
        byte[] buffer = new byte[width * height * 4];
        PixelReader pixelReader = image.getPixelReader();
        pixelReader.getPixels(0, 0, width, height, WritablePixelFormat.getByteBgraInstance(), buffer, 0, width*4);
        Mat mat = new Mat(height, width, CV_8UC4);
        UByteRawIndexer indexer = mat.createIndexer();
        for (int y=0; y < height; y++) {
            for (int x=0; x<width; x++) {
                int index = y*width*4 + x*4;
                indexer.put(y, x, 0, buffer[index]);
                indexer.put(y, x, 1, buffer[index + 1]);
                indexer.put(y, x, 2, buffer[index + 2]);
                indexer.put(y, x, 3, buffer[index + 3]);
            }
        }
        return mat;
    }
    private Mat preprocessImage(Mat inputImage, int width, int height) {
        Mat resizedImage = new Mat();
        Size newSize = new Size(width, height);
        resize(inputImage, resizedImage, newSize, 0, 0, INTER_AREA);

        Mat grayImage = new Mat();
        cvtColor(resizedImage, grayImage, COLOR_RGBA2GRAY);

        return grayImage;
    }
    @Override
    public void start(Stage primaryStage) {
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        setUpDrawing(canvas);
        StackPane root = new StackPane(canvas);
        Scene scene = new Scene(root, WIDTH, HEIGHT);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Draw a number");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
