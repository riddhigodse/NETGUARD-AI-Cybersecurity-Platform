package org.example.ui;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class ReportsPage {

    public static VBox createPage() {

        VBox root = new VBox(15);

        root.setPadding(new Insets(20));

        root.setStyle(
                "-fx-background-color: #020617;"
        );

        // ================= TITLE =================

        Label title =
                new Label("📁 Reports & CSV History");

        title.setStyle(
                "-fx-text-fill: cyan;" +
                        "-fx-font-size: 28px;" +
                        "-fx-font-weight: bold;"
        );

        // ================= TOTAL PACKETS =================

        Label totalPackets =
                new Label(
                        "📦 Total Captured Packets : "
                                + Dashboard.data.size()
                );

        totalPackets.setStyle(
                "-fx-text-fill: white;" +
                        "-fx-font-size: 18px;" +
                        "-fx-font-weight: bold;"
        );

        // ================= TABLE =================

        TableView<PacketRow> table =
                new TableView<>();

        table.setItems(
                Dashboard.data
        );

        table.setPrefHeight(650);

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

        // ================= PROTOCOL =================

        TableColumn<PacketRow, String> protocolCol =
                new TableColumn<>("Protocol");

        protocolCol.setCellValueFactory(
                new PropertyValueFactory<>("protocol")
        );

        // ================= SIZE =================

        TableColumn<PacketRow, String> sizeCol =
                new TableColumn<>("Size");

        sizeCol.setCellValueFactory(
                new PropertyValueFactory<>("size")
        );

        // ================= RISK =================

        TableColumn<PacketRow, String> riskCol =
                new TableColumn<>("Risk");

        riskCol.setCellValueFactory(
                new PropertyValueFactory<>("risk")
        );

        // ================= ADD COLUMNS =================

        table.getColumns().addAll(
                timeCol,
                srcCol,
                dstCol,
                appCol,
                protocolCol,
                sizeCol,
                riskCol
        );

        // ================= ADD TO ROOT =================

        root.getChildren().addAll(
                title,
                totalPackets,
                table
        );

        return root;
    }
}