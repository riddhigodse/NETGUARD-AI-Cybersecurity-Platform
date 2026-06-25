package org.example.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class AlertsPage {

    public static ObservableList<String> alerts =
            FXCollections.observableArrayList();

    public static VBox createPage() {

        VBox root = new VBox(15);

        root.setPadding(new Insets(20));

        root.setStyle(
                "-fx-background-color: #020617;"
        );

        // ================= TITLE =================

        Label title =
                new Label("⚠ Threat History");

        title.setStyle(
                "-fx-text-fill: orange;" +
                        "-fx-font-size: 28px;" +
                        "-fx-font-weight: bold;"
        );

        // ================= THREAT COUNTERS =================

        Label highThreats =
                new Label(
                        "🔴 High Threats : " +
                                Dashboard.highCount
                );

        highThreats.setStyle(
                "-fx-text-fill: red;" +
                        "-fx-font-size: 18px;" +
                        "-fx-font-weight: bold;"
        );

        Label mediumThreats =
                new Label(
                        "🟠 Medium Threats : " +
                                Dashboard.mediumCount
                );

        mediumThreats.setStyle(
                "-fx-text-fill: orange;" +
                        "-fx-font-size: 18px;" +
                        "-fx-font-weight: bold;"
        );

        Label lowThreats =
                new Label(
                        "🟢 Low Threats : " +
                                Dashboard.lowCount
                );

        lowThreats.setStyle(
                "-fx-text-fill: limegreen;" +
                        "-fx-font-size: 18px;" +
                        "-fx-font-weight: bold;"
        );

        // ================= CLEAR BUTTON =================

        Button clearBtn =
                new Button("🗑 Clear History");

        clearBtn.setStyle(
                "-fx-background-color: #dc2626;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 8px;"
        );

        clearBtn.setOnAction(e -> {

            Dashboard.alertData.clear();

        });

        // ================= TABLE =================

        TableView<PacketRow> table =
                new TableView<>();

        table.setItems(
                Dashboard.alertData
        );

        table.setPrefHeight(600);

        // ================= TIME =================

        TableColumn<PacketRow, String> timeCol =
                new TableColumn<>("Time");

        timeCol.setCellValueFactory(
                new PropertyValueFactory<>("time")
        );

        // ================= SOURCE =================

        TableColumn<PacketRow, String> srcCol =
                new TableColumn<>("Source IP");

        srcCol.setCellValueFactory(
                new PropertyValueFactory<>("src")
        );

        // ================= DESTINATION =================

        TableColumn<PacketRow, String> dstCol =
                new TableColumn<>("Destination IP");

        dstCol.setCellValueFactory(
                new PropertyValueFactory<>("dst")
        );

        // ================= APPLICATION =================

        TableColumn<PacketRow, String> appCol =
                new TableColumn<>("Application");

        appCol.setCellValueFactory(
                new PropertyValueFactory<>("application")
        );

        // ================= RISK =================

        TableColumn<PacketRow, String> riskCol =
                new TableColumn<>("Risk");

        riskCol.setCellValueFactory(
                new PropertyValueFactory<>("risk")
        );

        // ================= RISK COLOR CODING =================

        riskCol.setCellFactory(column -> new TableCell<>() {

            @Override
            protected void updateItem(String item, boolean empty) {

                super.updateItem(item, empty);

                if (empty || item == null) {

                    setText(null);
                    setStyle("");

                } else {

                    setText(item);

                    if (item.equals("HIGH")) {

                        setStyle(
                                "-fx-text-fill: red;" +
                                        "-fx-font-weight: bold;"
                        );

                    } else if (item.equals("MEDIUM")) {

                        setStyle(
                                "-fx-text-fill: orange;" +
                                        "-fx-font-weight: bold;"
                        );

                    } else {

                        setStyle(
                                "-fx-text-fill: limegreen;" +
                                        "-fx-font-weight: bold;"
                        );
                    }
                }
            }
        });

        table.getColumns().addAll(
                timeCol,
                srcCol,
                dstCol,
                appCol,
                riskCol
        );

        root.getChildren().addAll(
                title,
                highThreats,
                mediumThreats,
                lowThreats,
                clearBtn,
                table
        );

        return root;
    }
}