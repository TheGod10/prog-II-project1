package ch.fhnw.project.project;
import ch.fhnw.project.project.DataModel.DataContainer;
import ch.fhnw.project.project.input.TextFileReader;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.ScatterChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class TesterClass extends Application {
    public static File file;

    @Override
    public void start(final Stage primaryStage) {



       //TextFileReader textFileReader = new TextFileReader(file);
        //textFileReader.readFile(primaryStage);






       /* StackPane pane = new StackPane(MainPane.createMainPain(ScatterPlot.creatterScatterPane()));
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.show();*/




    }


    public static void main(String[] args) {
        launch(args);
    }


}


