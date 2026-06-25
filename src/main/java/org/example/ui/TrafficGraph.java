package org.example.ui;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

import javafx.collections.ObservableList;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import javafx.util.Duration;

public class TrafficGraph {

    // ================= SERIES =================
    public static XYChart.Series<Number, Number> series =
            new XYChart.Series<>();

    // ================= COUNTER =================
    private static int xSeriesData = 0;

    // ================= CREATE GRAPH =================
    public static LineChart<Number, Number> createGraph() {

        NumberAxis xAxis =
                new NumberAxis();

        NumberAxis yAxis =
                new NumberAxis();

        xAxis.setLabel("Time");

        yAxis.setLabel("Packets");

        LineChart<Number, Number> lineChart =
                new LineChart<>(xAxis, yAxis);

        lineChart.setTitle(
                "📊 Live Network Traffic"
        );

        lineChart.setAnimated(false);

        lineChart.setLegendVisible(false);

        lineChart.setPrefHeight(300);

        lineChart.setStyle(

                "-fx-background-color: #0f172a;" +

                        "-fx-text-fill: white;"
        );

        series.setName("Traffic");

        lineChart.getData().add(series);

        // ================= LIVE UPDATE =================
        Timeline timeline =
                new Timeline(

                        new KeyFrame(

                                Duration.seconds(1),

                                event -> {

                                    ObservableList<PacketRow> data =
                                            Dashboard.data;

                                    int packets =
                                            data.size();

                                    series.getData().add(

                                            new XYChart.Data<>(

                                                    xSeriesData++,

                                                    packets
                                            )
                                    );

                                    // LIMIT GRAPH POINTS
                                    if (series.getData().size() > 20) {

                                        series.getData().remove(0);
                                    }
                                }
                        )
                );

        timeline.setCycleCount(
                Timeline.INDEFINITE
        );

        timeline.play();

        return lineChart;
    }
}