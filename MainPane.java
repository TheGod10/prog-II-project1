package ch.fhnw.project.project;

import ch.fhnw.project.project.DataModel.DataContainer;
import ch.fhnw.project.project.input.TextFileReader;
import javafx.geometry.Insets;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.List;
import java.util.Stack;


public class MainPane {

    public static Pane createMainPain( List<DataContainer> List1){



        VBox mainBox = new VBox();
        mainBox.getChildren().addAll(ScatterPlot.creatterScatterPane(List1),Histogramm.createHistogram(List1));
        mainBox.setPadding(new Insets(5,5,5,5));
        mainBox.setSpacing(10);

        StackPane mainPain = new StackPane();
        mainPain.getChildren().addAll(mainBox);

        return mainPain;
    }

}
