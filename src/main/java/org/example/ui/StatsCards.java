package org.example.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.control.Label;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class StatsCards {

    // ================= LABELS =================
    public static Label packetsLabel =
            new Label("0");

    public static Label threatsLabel =
            new Label("0");

    public static Label activeIpLabel =
            new Label("0");

    public static Label trafficLabel =
            new Label("Normal");

    // ================= CREATE CARDS =================
    public static HBox createCards() {

        HBox root =
                new HBox(20);

        root.setAlignment(Pos.CENTER_LEFT);

        // ================= CARD 1 =================
        VBox packetsCard =
                createCard(
                        "📦 Total Packets",
                        packetsLabel
                );

        // ================= CARD 2 =================
        VBox threatsCard =
                createCard(
                        "🔴 Threats",
                        threatsLabel
                );

        // ================= CARD 3 =================
        VBox ipCard =
                createCard(
                        "🌐 Active IPs",
                        activeIpLabel
                );

        // ================= CARD 4 =================
        VBox trafficCard =
                createCard(
                        "⚡ Traffic",
                        trafficLabel
                );

        root.getChildren().addAll(

                packetsCard,

                threatsCard,

                ipCard,

                trafficCard
        );

        return root;
    }

    // ================= SINGLE CARD =================
    private static VBox createCard(
            String title,
            Label value
    ) {

        Label titleLabel =
                new Label(title);

        titleLabel.setStyle(

                "-fx-text-fill: white;" +

                        "-fx-font-size: 15px;" +

                        "-fx-font-weight: bold;"
        );

        value.setStyle(

                "-fx-text-fill: cyan;" +

                        "-fx-font-size: 26px;" +

                        "-fx-font-weight: bold;"
        );

        VBox card =
                new VBox(10);

        card.setPadding(
                new Insets(20)
        );

        card.setAlignment(Pos.CENTER_LEFT);

        card.setPrefWidth(220);

        card.setStyle(

                "-fx-background-color: #111827;" +

                        "-fx-background-radius: 15px;" +

                        "-fx-border-radius: 15px;" +

                        "-fx-border-color: #1e293b;"
        );

        card.getChildren().addAll(

                titleLabel,

                value
        );

        return card;
    }
}