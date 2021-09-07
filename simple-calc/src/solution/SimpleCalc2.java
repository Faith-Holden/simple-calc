package solution;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import resource_classes.StatCalc;

public class SimpleCalc2 extends Application{
    private StatCalc calculator = new StatCalc();

    public void start(Stage primaryStage) throws Exception{
        TilePane root = new TilePane();
        root.setPrefColumns(1);
        root.setStyle("-fx-border-color: black; -fx-border-width: 2px; " +
                "-fx-background-color: black");
        root.setMaxWidth(400);
        root.setPadding(new Insets(5));
        root.setPrefTileHeight(45);

        Label appletInfo = new Label("Type a number, Click Enter:");
        appletInfo.setTextFill(Color.WHITE);
        appletInfo.setFont(Font.font("", FontWeight.BOLD, 20));

        TextField entryField = new TextField();
        entryField.setPrefHeight(40);
        Button entryButton = new Button("Enter");
        entryButton.setFont(Font.font("", 18));
        entryButton.setDefaultButton(true);

        Button clearButton = new Button("Clear");
        clearButton.setFont(Font.font("", 18));
        HBox editBox = new HBox();
        editBox.getChildren().addAll( entryField, entryButton, clearButton);
        editBox.setPrefHeight(25);

        editBox.setSpacing(5.0);
        entryField.setMaxWidth(Double.POSITIVE_INFINITY);
        entryButton.setMaxWidth(Double.POSITIVE_INFINITY);
        clearButton.setMaxWidth(Double.POSITIVE_INFINITY);
        entryField.setPrefWidth(root.getMaxWidth()/3);
        entryButton.setPrefWidth(root.getMaxWidth()/3);
        clearButton.setPrefWidth(root.getMaxWidth()/3);

        TextField enteredNumbersCount = new TextField();
        enteredNumbersCount.setEditable(false);
        enteredNumbersCount.setPrefHeight(40);
        enteredNumbersCount.setText("Items entered:");
        TextField sums = new TextField();
        sums.setEditable(false);
        sums.setPrefHeight(40);
        sums.setText("Sum of items entered:");
        TextField averages = new TextField();
        averages.setEditable(false);
        averages.setPrefHeight(40);
        averages.setText("Average of items entered:");
        TextField stdDev = new TextField();
        stdDev.setEditable(false);
        stdDev.setPrefHeight(40);
        stdDev.setText("Standard deviation of items entered:");

        entryButton.setOnAction(evt->{
            String input = entryField.getText();
            double inputNums;
            try {
                inputNums = Double.parseDouble(input);
            }catch (NumberFormatException e){
                entryField.setText("Please enter a number.");
                entryField.requestFocus();
                entryField.selectAll();
                return;
            }

            calculator.enter(inputNums);
            enteredNumbersCount.setText("Items entered:                                             "+ calculator.getCount());
            sums.setText("Sum of items entered:                                "+calculator.getSum());
            averages.setText("Average of items entered:                          "+calculator.getMean());
            stdDev.setText("Standard deviation of items entered:         "+calculator.getStandardDeviation());
            entryField.setText("");
            entryField.requestFocus();
        });
        clearButton.setOnAction(evt->{
            calculator = new StatCalc();
            enteredNumbersCount.setText("Items entered:");
            sums.setText("Sum of items entered:");
            averages.setText("Average of items entered:");
            stdDev.setText("Standard deviation of items entered:");
            entryField.setText("");
            entryField.requestFocus();
        });

        root.getChildren().add(appletInfo);
        root.getChildren().add(editBox);
        root.getChildren().add(enteredNumbersCount);
        root.getChildren().add(sums);
        root.getChildren().add(averages);
        root.getChildren().add(stdDev);

        root.setAlignment(appletInfo, Pos.CENTER);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public static void main (String[]Args){
        launch(Args);
    }
}
