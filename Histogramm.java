package ch.fhnw.project.project;

import ch.fhnw.project.project.DataModel.DataContainer;
import ch.fhnw.project.project.input.TextFileReader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.File;
import java.util.Collections;
import java.util.List;


public class Histogramm {


    public static File file;
    public static Pane createHistogram( List<DataContainer> List1){




        VBox left = new VBox();
        left.getChildren().addAll(leftBarChart(List1));
        left.setAlignment(Pos.CENTER);
        left.setSpacing(10);
        left.setPadding(new Insets(5,5,5,5));

        VBox right = new VBox();
        right.getChildren().addAll(rightBarChart(List1));
        right.setAlignment(Pos.CENTER);
        right.setSpacing(50);
        right.setPadding(new Insets(5,5,5,5));


        HBox HBox = new HBox();
        HBox.getChildren().addAll(left,right);
        HBox.setAlignment(Pos.CENTER);
        HBox.setSpacing(20);
        HBox.setPadding(new Insets(5,5,5,5));

        StackPane pane = new StackPane();
        pane.getChildren().add(HBox);

        return pane;
    }


    private static int histogramIntervals(List<DataContainer> List1)
    {

        List<Double> a=List1.get(0).getValues();
        double n=a.size();
        return (int) Math.sqrt(n);


        }

    private static double intervalsWidth(List<DataContainer> List1)
    {
        int interval= histogramIntervals(List1);
        List<Double> variable= List1.get(0).getValues();
        double max =variable.get(variable.size()-1);
        double min= variable.get(0);
        return (max-min)/interval;

    }

    private static int[] histogramAllocation(List<DataContainer> List1)
    {
        int interval= histogramIntervals(List1);
        int[] allocationArray =new int[interval];
        List<Double> variable =List1.get(0).getValues();

        Collections.sort(variable);
        double min= variable.get(0);
        double intervalWidth =intervalsWidth(List1);
        for (Double element: variable)
        {
            for (int i=0; i<interval;i++)
            {
                if (i*intervalWidth+min<element&&(i+1)*intervalWidth+min>=element)
                {
                    allocationArray[i]=allocationArray[i]+1;
                }
            }

            if (element==min)
            {
                allocationArray[0]=allocationArray[0]+1;
            }
        }
        return allocationArray;

    }


    private static BarChart leftBarChart(List<DataContainer> List1){
        int [] allocationArray = histogramAllocation(List1);
        List<Double> variable = List1.get(0).getValues();
        double intervalWidht = intervalsWidth(List1);

        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setTickLabelsVisible(false);
        xAxis.setOpacity(0);


        BarChart<String,Number> bc = new BarChart<>(xAxis,yAxis);

        XYChart.Series series1 = new XYChart.Series();
        series1.setName(List1.get(0).getVariableName());
        double a = variable.get(0);
        for ( int i = 0; i < allocationArray.length; i++){
            XYChart.Data<String,Number> lines = new XYChart.Data<String, Number>(String.format("%s - %s",a,a+intervalWidht),allocationArray[i]);
            series1.getData().add(lines);
            a = a + intervalWidht;
        }
        bc.getData().add(series1);
        bc.setTitle(List1.get(0).getVariableName());
        return bc;



    }

    private static BarChart rightBarChart(List<DataContainer> List1) {
        int[] allocationArray = histogramAllocation(List1);
        List<Double> variable = List1.get(1).getValues();
        double intervalWidht = intervalsWidth(List1);

        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setTickLabelsVisible(false);
        xAxis.setOpacity(0);


        BarChart<String, Number> bc = new BarChart<>(xAxis, yAxis);

        XYChart.Series series1 = new XYChart.Series();
        series1.setName(List1.get(1).getVariableName());
        double a = variable.get(0);
        for (int i = 0; i < allocationArray.length; i++) {
            XYChart.Data<String, Number> lines = new XYChart.Data<String, Number>(String.format("%s - %s", a, a + intervalWidht), allocationArray[i]);
            series1.getData().add(lines);
            a = a + intervalWidht;
        }

        bc.getData().add(series1);
        bc.setTitle(List1.get(1).getVariableName());
        return bc;


    }


}
