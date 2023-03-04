package com.example.demo;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Calculator extends Application {

    private double operand1;
    private double operand2;
    private String operator;
    private TextField display;
    @Override
    public void start(Stage stage) throws IOException {
        Button button1 = new Button("1");
        Button button2 = new Button("2");
        Button button3 = new Button("3");
        Button button4 = new Button("4");
        Button button5 = new Button("5");
        Button button6 = new Button("6");
        Button button7 = new Button("7");
        Button button8 = new Button("8");
        Button button9 = new Button("9");
        Button button0 = new Button("0");
        Button buttonDot = new Button(".");
        Button buttonPlus = new Button("+");
        Button buttonMinus = new Button("-");
        Button buttonMultiply = new Button("*");
        Button buttonDivide = new Button("/");
        Button buttonEquals = new Button("=");

        display = new TextField();

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(20);
        grid.setHgap(20);

        grid.add(button1, 0, 1);
        grid.add(button2, 1, 1);
        grid.add(button3, 2, 1);
        grid.add(buttonPlus, 3, 1);

        grid.add(button4, 0, 2);
        grid.add(button5, 1, 2);
        grid.add(button6, 2, 2);
        grid.add(buttonMinus, 3, 2);

        grid.add(button7, 0, 3);
        grid.add(button8, 1, 3);
        grid.add(button9, 2, 3);
        grid.add(buttonMultiply, 3, 3);

        grid.add(buttonDot, 0, 4);
        grid.add(button0, 1, 4);
        grid.add(buttonEquals, 2, 4);
        grid.add(buttonDivide, 3, 4);
        grid.add(display, 0, 0, 4, 1);

        button1.setOnAction(new ButtonClickHandler("1"));
        button2.setOnAction(new ButtonClickHandler("2"));
        button3.setOnAction(new ButtonClickHandler("3"));
        button4.setOnAction(new ButtonClickHandler("4"));
        button5.setOnAction(new ButtonClickHandler("5"));
        button6.setOnAction(new ButtonClickHandler("6"));
        button7.setOnAction(new ButtonClickHandler("7"));
        button8.setOnAction(new ButtonClickHandler("8"));
        button9.setOnAction(new ButtonClickHandler("9"));
        button0.setOnAction(new ButtonClickHandler("0"));
        buttonDot.setOnAction(new ButtonClickHandler("."));
        buttonPlus.setOnAction(new ButtonClickHandler("+"));
        buttonMinus.setOnAction(new ButtonClickHandler("-"));
        buttonMultiply.setOnAction(new ButtonClickHandler("*"));
        buttonDivide.setOnAction(new ButtonClickHandler("/"));
        buttonEquals.setOnAction(new ButtonClickHandler("="));

        // Set the scene
        Scene scene = new Scene(grid, 500, 400);
        stage.setScene(scene);
        stage.show();
    }

    // Define the ButtonClickHandler class
    private class ButtonClickHandler implements EventHandler<ActionEvent> {

        private String text;

        public ButtonClickHandler(String text) {
            this.text = text;
        }

        @Override
        public void handle(ActionEvent event) {
            String tmp = "";
            if ("0123456789.".contains(text)) {
                display.setText(display.getText() + text);
            } else if ("+-*/".contains(text)) {
                operand1 = Double.parseDouble(display.getText());
                operator = text;
                // TODO: set the screen to display the expression
//                tmp = display.getText();
//                display.setText(display.getText() + text);
                display.setText("");
            } else if ("=".equals(text)) {
                operand2 = Double.parseDouble(display.getText());
                double result = 0;
                if ("+".equals(operator)) {
                    result = operand1 + operand2;
                } else if ("-".equals(operator)) {
                    result = operand1 - operand2;
                } else if ("*".equals(operator)) {
                    result = operand1 * operand2;
                } else if ("/".equals(operator)) {
                    result = operand1 / operand2;
                }
                display.setText(Double.toString(result));
            }
        }
    }

    public static void main(String[] args) {
        launch();
    }
}