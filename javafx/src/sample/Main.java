package sample;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.NumberAxis;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        stage.setTitle("Scatter Chart Sample");

        final NumberAxis xAxis = new NumberAxis(1000, 5000, 1000);
        final NumberAxis yAxis = new NumberAxis(0, 50, 10);
        final ScatterChart<Number,Number> sc = new
                ScatterChart<Number,Number>(xAxis,yAxis);
        sc.setId("scatterplot");
        xAxis.setLabel("Weight)");
        yAxis.setLabel("MPG");
        sc.setTitle("Scatterplot");
        Scene scene  = new Scene(sc, 500, 400);
        sc.getStylesheets().add(getClass().getResource("Chart.css").toExternalForm());        stage.setScene(scene);

        XYChart.Series series1 = new XYChart.Series();

        //read data from csv file
        String line="";
        try (BufferedReader br = new BufferedReader(new FileReader("cars-sample.csv"))) {
            //skip first line
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] car = line.split(",");
                System.out.println("Car [man= " + car[2] + " , weight=" + car[7] + ", mpg=" + car[3]);
                Double mpg = 0.0;
                if(!car[3].equals("NA")){
                    mpg = Double.parseDouble(car[3]);
                    final XYChart.Data<Number, Number> data = new XYChart.Data(Double.parseDouble(car[7]), mpg);
                    data.nodeProperty().addListener(new ChangeListener<Node>() {
                        @Override public void changed(ObservableValue<? extends Node> ov, Node oldNode, final Node node) {
                            if (node != null) {
                                adjustColors(data, car[2]);
                            }
                        }
                    });
                    series1.getData().add(data);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        //adjustColors(series1.getData());
        sc.getData().addAll(series1);
        stage.show();
    }

    public void adjustColors(XYChart.Data<Number, Number> data, String man){
        Node node = data.getNode();
        System.out.println(man);
        if(man == "bmw"){
            node.setStyle("-fx-background-color: -fx-bmw");
        }
        else if(man.equals("ford")){
            System.out.println("got here");
            node.setStyle("-fx-background-color: -fx-ford");
        }
        else if(man.equals("honda")){
            node.setStyle("-fx-background-color: -fx-honda");
        }
        else if(man.equals("mercedes")){
            node.setStyle("-fx-background-color: -fx-mercedes");
        }
        else if(man.equals("toyota")){
            node.setStyle("-fx-background-color: -fx-toyota");
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
