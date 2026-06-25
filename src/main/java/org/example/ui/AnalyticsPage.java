package org.example.ui;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class AnalyticsPage {

    // ================= THREAT LABELS =================
    public static Label lowRiskSummary;
    public static Label mediumRiskSummary;
    public static Label highRiskSummary;
    public static Label statusSummary;

    // ================= TRAFFIC GRAPH =================
    public static XYChart.Series<Number, Number> packetSeries =
            new XYChart.Series<>();

    private static AreaChart<Number, Number> areaChart;

    // ================= PIE CHART =================
    private static PieChart protocolPieChart;
    private static TextArea protocolStatsArea;

    private static PieChart.Data tcpSlice =
            new PieChart.Data("TCP", 1);

    private static PieChart.Data udpSlice =
            new PieChart.Data("UDP", 1);

    private static PieChart.Data httpSlice =
            new PieChart.Data("HTTP", 1);

    private static PieChart.Data httpsSlice =
            new PieChart.Data("HTTPS", 1);

    // ================= PROTOCOL COUNTERS =================
    public static int tcpCount = 0;
    public static int udpCount = 0;
    public static int httpCount = 0;
    public static int httpsCount = 0;

    // ================= GRAPH COUNTER =================
    private static int secondCounter = 0;

    // ================= PAGE =================
    public static VBox createPage() {

        // ================= TITLE =================
        Label title =
                new Label("📊 Live Traffic Analytics");

        title.setStyle(
                "-fx-font-size: 28px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: cyan;"
        );

        // ================= AXES =================
        NumberAxis xAxis =
                new NumberAxis();

        xAxis.setLabel("Last 30 Seconds");

        NumberAxis yAxis =
                new NumberAxis();

        yAxis.setLabel("Packets / Second");

        // ================= AREA CHART =================
        areaChart =
                new AreaChart<>(xAxis, yAxis);

        areaChart.setTitle(
                "Real-Time Network Traffic"
        );

        areaChart.setLegendVisible(false);
        areaChart.setAnimated(false);
        areaChart.setCreateSymbols(false);
        areaChart.setPrefHeight(650);
        areaChart.setPrefWidth(900);

        packetSeries.setName("Traffic");

        areaChart.getData().add(packetSeries);

        areaChart.setStyle(
                "-fx-background-color: #111827;"
        );

        // ================= PIE CHART =================
        protocolPieChart =
                new PieChart();

        protocolPieChart.setTitle(
                "Protocol Distribution"
        );

        protocolPieChart.getData().addAll(
                tcpSlice,
                udpSlice,
                httpSlice,
                httpsSlice
        );

        protocolPieChart.setPrefWidth(450);
        protocolPieChart.setPrefHeight(500);

        protocolPieChart.setStyle(
                "-fx-background-color: #111827;" +
                        "-fx-text-fill: white;"
        );

        // ================= PIE COLORS =================
        Platform.runLater(() -> {

            if (tcpSlice.getNode() != null) {
                tcpSlice.getNode().setStyle(
                        "-fx-pie-color: cyan;"
                );
            }

            if (udpSlice.getNode() != null) {
                udpSlice.getNode().setStyle(
                        "-fx-pie-color: #22c55e;"
                );
            }

            if (httpSlice.getNode() != null) {
                httpSlice.getNode().setStyle(
                        "-fx-pie-color: orange;"
                );
            }

            if (httpsSlice.getNode() != null) {
                httpsSlice.getNode().setStyle(
                        "-fx-pie-color: red;"
                );
            }
        });

        // ================= PROTOCOL STATS =================
        protocolStatsArea =
                new TextArea();

        protocolStatsArea.setEditable(false);

        protocolStatsArea.setPrefWidth(250);
        protocolStatsArea.setPrefHeight(180);

        protocolStatsArea.setStyle(
                "-fx-control-inner-background: #111827;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 14px;"
        );

        // ================= RIGHT PANEL =================
        VBox rightPanel =
                new VBox(15);

        rightPanel.getChildren().addAll(
                protocolPieChart,
                protocolStatsArea
        );

        // ================= ANALYTICS CONTAINER =================
        HBox analyticsContainer =
                new HBox(20);

        analyticsContainer.getChildren().addAll(
                areaChart,
                rightPanel
        );

        // ================= THREAT SUMMARY =================
        Label threatTitle =
                new Label("Threat Summary");

        threatTitle.setStyle(
                "-fx-text-fill: cyan;" +
                        "-fx-font-size: 20px;" +
                        "-fx-font-weight: bold;"
        );

        lowRiskSummary =
                new Label("🟢 Low Risk : 0");

        mediumRiskSummary =
                new Label("🟡 Medium Risk : 0");

        highRiskSummary =
                new Label("🔴 High Risk : 0");

        statusSummary =
                new Label("✅ Status : SAFE");

        lowRiskSummary.setStyle("-fx-text-fill:white;");
        mediumRiskSummary.setStyle("-fx-text-fill:white;");
        highRiskSummary.setStyle("-fx-text-fill:white;");
        statusSummary.setStyle("-fx-text-fill:lime;");

        // ================= THREAT PANEL =================
        VBox threatPanel =
                new VBox(15);

        threatPanel.setPadding(
                new Insets(20)
        );

        threatPanel.setPrefWidth(400);

        threatPanel.setStyle(
                "-fx-background-color:#111827;" +
                        "-fx-background-radius:15;" +
                        "-fx-border-color:#1e293b;" +
                        "-fx-border-radius:15;"
        );

        threatPanel.getChildren().addAll(
                threatTitle,
                lowRiskSummary,
                mediumRiskSummary,
                highRiskSummary,
                statusSummary
        );

        // ================= ROOT =================
        VBox root =
                new VBox(20);

        root.setPadding(
                new Insets(20)
        );

        root.setAlignment(
                Pos.TOP_CENTER
        );

        root.setStyle(
                "-fx-background-color: #020617;"
        );

        root.getChildren().addAll(
                title,
                StatsCards.createCards(),
                analyticsContainer,
                threatPanel
        );

        return root;
    }

    // ================= UPDATE TRAFFIC GRAPH =================
    public static void updateChart(int packetsPerSecond) {

        Platform.runLater(() -> {

            secondCounter++;

            packetSeries.getData().add(
                    new XYChart.Data<>(
                            secondCounter,
                            packetsPerSecond
                    )
            );

            if (packetSeries.getData().size() > 30) {
                packetSeries.getData().remove(0);
            }
        });
    }

    // ================= UPDATE PIE CHART =================
    public static void updateProtocolChart() {

        Platform.runLater(() -> {

            tcpSlice.setPieValue(
                    Math.max(tcpCount, 1)
            );

            udpSlice.setPieValue(
                    Math.max(udpCount, 1)
            );

            httpSlice.setPieValue(
                    Math.max(httpCount, 1)
            );

            httpsSlice.setPieValue(
                    Math.max(httpsCount, 1)
            );

            protocolStatsArea.setText(
                    "📊 Protocol Statistics\n\n" +
                            "TCP   : " + tcpCount + "\n" +
                            "UDP   : " + udpCount + "\n" +
                            "HTTP  : " + httpCount + "\n" +
                            "HTTPS : " + httpsCount
            );
        });
    }

    // ================= UPDATE THREAT SUMMARY =================
    public static void updateThreatSummary(
            int low,
            int medium,
            int high
    ) {

        Platform.runLater(() -> {

            lowRiskSummary.setText(
                    "🟢 Low Risk : " + low
            );

            mediumRiskSummary.setText(
                    "🟡 Medium Risk : " + medium
            );

            highRiskSummary.setText(
                    "🔴 High Risk : " + high
            );

            if (high > 10) {

                statusSummary.setText(
                        "⚠ Status : UNDER ATTACK"
                );
            }
            else if (medium > 10) {

                statusSummary.setText(
                        "⚠ Status : SUSPICIOUS"
                );
            }
            else {

                statusSummary.setText(
                        "✅ Status : SAFE"
                );
            }
        });
    }
}