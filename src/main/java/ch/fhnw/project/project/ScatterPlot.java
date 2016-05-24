package ch.fhnw.project.project;


import ch.fhnw.project.project.DataModel.DataContainer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.*;
import javafx.geometry.Insets;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class ScatterPlot {

    public static Pane creatterScatterPane(List<DataContainer> dataContainerList){

        List<DataContainer> List1 = dataContainerList;


        //create Widgets
        CheckBox timeLine = new CheckBox("Show Time Line");
        Slider pointSizeSlider = new Slider(1,2,5);
        Label pointSizeSliderLaber = new Label("Point Size");
        ColorPicker cP = new ColorPicker(Color.BLACK);
        Label sliderLAbel = new Label("Change Point Size");


        //Create Pane

        ScatterChart<Number,Number> sc = getsc(List1,pointSizeSlider,cP,timeLine);
        LineChart<Number,Number> lc = getlc(List1,timeLine,cP);

        sc.lookup(".chart-plot-background").setStyle("fx-background-color:transparent");
        lc.setVisible(false);

        //Checkbox Commands
        timeLine.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(oldValue){
                    lc.setVisible(false);
                } else{
                    lc.setVisible(true);
                }

            }
        });


        //style pane
        HBox firstLine = new HBox();
        firstLine.getChildren().addAll(pointSizeSliderLaber,pointSizeSlider);
        firstLine.setAlignment(Pos.CENTER);
        firstLine.setPadding(new Insets(5,5,5,5));

        HBox secondLine = new HBox();
        secondLine.getChildren().addAll(pointSizeSliderLaber,pointSizeSlider);
        secondLine.setAlignment(Pos.CENTER);
        secondLine.setSpacing(20);
        secondLine.setPadding(new Insets(10,10,10,10));

        StackPane scatterPane = new StackPane();
        scatterPane.getChildren().addAll(lc,sc);
        scatterPane.setAlignment(Pos.CENTER);

        //scatterPane.setMaxWidht(600)

        VBox overAllBox = new VBox();
        overAllBox.getChildren().addAll(firstLine,secondLine,scatterPane);
        overAllBox.setAlignment(Pos.CENTER);
        overAllBox.setSpacing(10);
        overAllBox.setPadding(new Insets(5,5,5,5));

        return overAllBox;



    }


    private static List<DataContainer> getlist()
    {

        List<DataContainer> List1=new LinkedList<>();

        List<Double> a=new LinkedList<>();
        a.add(3.0);a.add(1.0); a.add(4.0); a.add(6.0); a.add(5.0);
        DataContainer var1= new DataContainer("Variable1");


        List<Double> b=new LinkedList<>();
        b.add(3.0);b.add(1.0); b.add(4.0); b.add(6.0); b.add(5.0);
        DataContainer var2= new DataContainer("Variable2");

        List1.add(var1);
        List1.add(var2);

        return List1;


    }



    private static ScatterChart<Number,Number> getsc(List<DataContainer> List1,Slider pointSizeSlider, ColorPicker cP, CheckBox timeLine)
    {

        NumberAxis xAxis=new NumberAxis();
        NumberAxis yAxis=new NumberAxis();
        xAxis.setLabel(List1.get(0).getVariableName());
        yAxis.setLabel(List1.get(1).getVariableName());
        ScatterChart<Number, Number> sc= new ScatterChart<Number, Number>(xAxis,yAxis);

        List<Double>a,b;
        a=List1.get(0).getValues();
        b=List1.get(1).getValues();

        XYChart.Series data1=new XYChart.Series();


        for (int i=0; i< a.size(); i++)
        {
            XYChart.Data<Number,Number> point=new XYChart.Data<>(a.get(i),b.get(i));
            Circle circle =new Circle();
            circle.fillProperty().bind(cP.valueProperty());
            circle.radiusProperty().bind(pointSizeSlider.valueProperty());
            point.setNode(circle);
            data1.getData().add(point);
        }

        sc.getData().add(data1);
        sc.setLegendVisible(false);

        return sc;
    }


    private static LineChart<Number,Number> getlc(List<DataContainer> List1, CheckBox timeLine, ColorPicker cP){
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel(" ");
        yAxis.setLabel(" ");
        LineChart<Number, Number> lc = new LineChart<>(xAxis,yAxis);

        List<Double>a, b;
        a = List1.get(0).getValues();
        b = List1.get(1).getValues();

        XYChart.Series data1 = new XYChart.Series();


        for(int i = 0; i < a.size();i ++){
            XYChart.Data<Number, Number> lines = new XYChart.Data<>(a.get(i), b.get(i));
            data1.getData().add(lines);
        }


        lc.getData().add(data1);
        lc.setCreateSymbols(false);
        lc.setAxisSortingPolicy(LineChart.SortingPolicy.NONE);
        lc.setHorizontalGridLinesVisible(false);
        lc.setVerticalGridLinesVisible(false);
        lc.setLegendVisible(false);
        return lc;
    }


}


