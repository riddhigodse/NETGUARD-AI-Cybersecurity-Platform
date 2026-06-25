package org.example.ui;

import org.example.ui.Dashboard;
import org.example.ui.AlertsPage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class SidebarMenu {

    public static VBox createSidebar(BorderPane root) {

        VBox sidebar = new VBox(20);

        sidebar.setPadding(new Insets(20));

        sidebar.setPrefWidth(250);

        sidebar.setStyle(
                "-fx-background-color: #020617;" +
                        "-fx-border-color: #1e293b;" +
                        "-fx-border-width: 0 1 0 0;"
        );

        // LOGO

        Label logo = new Label("NETGUARD");

        logo.setStyle(
                "-fx-text-fill: #06b6d4;" +
                        "-fx-font-size: 30px;" +
                        "-fx-font-weight: bold;"
        );

        Label subLogo =
                new Label("Threat Intelligence Platform");

        subLogo.setStyle(
                "-fx-text-fill: #94A3B8;" +
                        "-fx-font-size: 12px;"
        );

        // BUTTONS

        Button dashboardBtn =
                createMenuButton("🏠 Dashboard");

        Button captureBtn =
                createMenuButton("📡 Live Capture");

        Button alertsBtn =
                createMenuButton("⚠ Alerts");

        Button reportsBtn =
                createMenuButton("📁 Reports");

        Button analyticsBtn =
                createMenuButton("📊 Analytics");

        Button settingsBtn =
                createMenuButton("⚙ Settings");

        Button aiBtn =
                createMenuButton("🧠 NETGUARD AI");

        // DASHBOARD

        dashboardBtn.setOnAction(e -> {

            if (Dashboard.dashboardPage != null) {

                root.setCenter(
                        Dashboard.dashboardPage
                );
            }
        });

        // CAPTURE

        captureBtn.setOnAction(e ->
                root.setCenter(
                        CapturePage.createPage()
                )
        );

        // ALERTS

        alertsBtn.setOnAction(e ->
                root.setCenter(
                        AlertsPage.createPage()
                )
        );

        // REPORTS

        reportsBtn.setOnAction(e ->
                root.setCenter(
                        ReportsPage.createPage()
                )
        );

        // ANALYTICS

        analyticsBtn.setOnAction(e ->
                root.setCenter(
                        AnalyticsPage.createPage()
                )
        );

        // SETTINGS

        settingsBtn.setOnAction(e -> {

            VBox page = new VBox(20);

            page.setPadding(
                    new Insets(30)
            );

            page.setStyle(
                    "-fx-background-color: #020617;"
            );

            Label title =
                    new Label("⚙ System Settings");

            title.setStyle(
                    "-fx-text-fill: #86efac;" +
                            "-fx-font-size: 30px;" +
                            "-fx-font-weight: bold;"
            );

            VBox dpiCard =
                    createSettingCard(
                            "🛡 DPI Engine",
                            "ENABLED"
                    );

            VBox captureCard =
                    createSettingCard(
                            "📡 Packet Capture",
                            "RUNNING"
                    );

            VBox alertCard =
                    createSettingCard(
                            "⚠ Alert Engine",
                            "ACTIVE"
                    );

            VBox aiCard =
                    createSettingCard(
                            "🧠 NETGUARD AI",
                            "ONLINE"
                    );

            page.getChildren().addAll(
                    title,
                    dpiCard,
                    captureCard,
                    alertCard,
                    aiCard
            );

            root.setCenter(page);
        });

        // AI ASSISTANT

        aiBtn.setOnAction(e -> {

            root.setCenter(
                    AISecurityAssistantPage.createPage()
            );
        });

        // SPACER

        Region spacer =
                new Region();

        VBox.setVgrow(
                spacer,
                Priority.ALWAYS
        );

        // SECURITY CARD

        VBox adminCard =
                new VBox(10);

        adminCard.setAlignment(
                Pos.CENTER_LEFT
        );

        adminCard.setPadding(
                new Insets(15)
        );

        adminCard.setStyle(
                "-fx-background-color: #0f172a;" +
                        "-fx-background-radius: 12;" +
                        "-fx-border-color: #334155;" +
                        "-fx-border-radius: 12;"
        );

        Label adminTitle =
                new Label("🛡 Security Engine");

        adminTitle.setStyle(
                "-fx-text-fill: white;" +
                        "-fx-font-size: 18px;" +
                        "-fx-font-weight: bold;"
        );

        Label online =
                new Label("🟢 Protection Active");

        online.setStyle(
                "-fx-text-fill: #22c55e;" +
                        "-fx-font-size: 15px;" +
                        "-fx-font-weight: bold;"
        );

        adminCard.getChildren().addAll(
                adminTitle,
                online
        );

        sidebar.getChildren().addAll(
                logo,
                subLogo,
                dashboardBtn,
                captureBtn,
                alertsBtn,
                reportsBtn,
                analyticsBtn,
                settingsBtn,
                aiBtn,
                spacer,
                adminCard
        );

        return sidebar;
    }

    private static VBox createSettingCard(
            String title,
            String status
    ) {

        VBox card = new VBox(8);

        card.setPadding(
                new Insets(15)
        );

        card.setStyle(
                "-fx-background-color: #0f172a;" +
                        "-fx-background-radius: 15;" +
                        "-fx-border-color: #1e293b;" +
                        "-fx-border-radius: 15;"
        );

        Label titleLabel =
                new Label(title);

        titleLabel.setStyle(
                "-fx-text-fill: cyan;" +
                        "-fx-font-size: 18px;" +
                        "-fx-font-weight: bold;"
        );

        Label statusLabel =
                new Label("Status : " + status);

        statusLabel.setStyle(
                "-fx-text-fill: #22c55e;" +
                        "-fx-font-size: 14px;"
        );

        card.getChildren().addAll(
                titleLabel,
                statusLabel
        );

        return card;
    }

    private static Button createMenuButton(
            String text
    ) {

        Button btn =
                new Button(text);

        btn.setAlignment(
                Pos.CENTER_LEFT
        );

        btn.setPrefWidth(210);

        btn.setPrefHeight(50);

        btn.setStyle(
                "-fx-background-color: #111827;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 15px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 10;"
        );

        btn.setOnMouseEntered(e ->
                btn.setStyle(
                        "-fx-background-color: #1e293b;" +
                                "-fx-text-fill: cyan;" +
                                "-fx-font-size: 15px;" +
                                "-fx-font-weight: bold;" +
                                "-fx-background-radius: 10;"
                )
        );

        btn.setOnMouseExited(e ->
                btn.setStyle(
                        "-fx-background-color: #111827;" +
                                "-fx-text-fill: white;" +
                                "-fx-font-size: 15px;" +
                                "-fx-font-weight: bold;" +
                                "-fx-background-radius: 10;"
                )
        );

        return btn;
    }


}
