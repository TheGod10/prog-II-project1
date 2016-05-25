package ch.fhnw.project.project;/**
 * Created by TheGod on 25.05.16.
 */

import ch.fhnw.project.project.DataModel.DataContainer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;

public class Demo extends Application {

    private enum ValidDataType {txt, lin};

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(primaryStage);


        List<DataContainer> dataContainers = parse(file);



        StackPane pane = new StackPane();
        primaryStage.setTitle(file.getName());
        pane.getChildren().addAll(MainPane.createMainPain(dataContainers));
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private List<DataContainer> parse(File file) {
        if (file.getName().endsWith(ValidDataType.txt.toString())) {
            return parseTabDelimitedTextFile(file);
        } else {
            return parseLineOrientedTextFile(file);
        }
    }

    private List<DataContainer> parseTabDelimitedTextFile(File file) {
        List<DataContainer> dataContainerList = createEmptyDataContainerForTabDelimitedTextFile(file);

        try (Scanner scanner = new Scanner(file)) {
            // skip column names
            if (!scanner.hasNextLine()) {
                //LOG.log(Level.WARNING, "No column names have been set.");
                return null;
            }
            scanner.nextLine();

            int columnPos = 0;
            int amountOfColumns = dataContainerList.size();
            while (scanner.hasNextDouble() || scanner.hasNextInt()) {
                // do that for indexing to corresponding column
                if (columnPos == amountOfColumns) {
                    columnPos = columnPos - amountOfColumns;
                }

                if (scanner.hasNextDouble()) {
                    dataContainerList.get(columnPos).addValue(scanner.nextDouble());
                } else {
                    dataContainerList.get(columnPos).addValue((double) scanner.nextInt());
                }
                columnPos++;
            }
            return dataContainerList;

        } catch (FileNotFoundException e) {
            //LOG.log(Level.WARNING, e.toString());
            return null;

        }
    }

    private List<DataContainer> createEmptyDataContainerForTabDelimitedTextFile(File file) {
        List<DataContainer> dataContainerList = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            scanner.useDelimiter("\t");
            while (scanner.hasNext()) {
                String next = scanner.next();
                if (next.contains("\t")) {
                    dataContainerList.add(new DataContainer(next.substring(0, next.indexOf("\t"))));
                } else if (next.contains("\n")) {
                    dataContainerList.add(new DataContainer(next.substring(0, next.indexOf("\n"))));
                    break;
                } else {
                    dataContainerList.add(new DataContainer(next));
                }
            }
            return dataContainerList;
        } catch (FileNotFoundException e) {
            //LOG.log(Level.WARNING, e.toString());
            System.exit(-1);
        }
        return null;
    }


    private List<DataContainer> parseLineOrientedTextFile( File file) {
        try (Scanner scanner = new Scanner(file)) {

            List<DataContainer> dataContainerList = new ArrayList<>();

            // get number of variables
            if (!scanner.hasNextLine()) {
                //LOG.log(Level.WARNING, "No number of variables have been set!");
                return null;
            }

            int numberOfVariables;
            String nextLine = scanner.nextLine().replace("\n", "");
            try {
                numberOfVariables = Integer.valueOf(nextLine);
            } catch (NumberFormatException e) {
                //LOG.log(Level.WARNING, "'" + nextLine + "' is not a valid amount of variables!");
                return null;
            }

            // store variable names
            for (int i = 0; i < numberOfVariables; i++) {
                if (!scanner.hasNextLine()) {
                    //LOG.log(Level.WARNING, "Number of variables WRONG!");
                    return null;
                }
                dataContainerList.add(i, new DataContainer(scanner.nextLine().replace("\n", "")));
            }

            // check for delimiter
            if (!scanner.hasNextLine()) {
                //LOG.log(Level.WARNING, "No delimiter has been set!");
                return null;
            }
            // delimiter has to be single character
            char delimiter = scanner.nextLine().charAt(0);


            // get variable values
            for (int i = 0; i < numberOfVariables; i++) {

                if (!scanner.hasNextLine()) {
                    //LOG.log(Level.WARNING, "Amount of estimated variables:'" + numberOfVariables + "' is wrong!");
                    return null;
                }

                nextLine = scanner.nextLine().replace("\n", "");
                char[] nextLineCharArray = nextLine.toCharArray();
                int startIndex = 0;
                for (int j = 0; j < nextLine.length(); j++) {
                    // check if char equals delimiter or if at last position of the line
                    if (nextLineCharArray[j] == delimiter || j == nextLine.length()-1) {
                        String substring = nextLine.substring(startIndex, j);
                        dataContainerList.get(i).addValue(Double.parseDouble(substring));
                        startIndex = j+1;
                    }
                }

            }

            return dataContainerList;

        } catch (FileNotFoundException e) {
            //LOG.log(Level.WARNING, e.toString());
            return null;
        }
    }


}
