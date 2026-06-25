package org.example.ui;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import org.example.ui.StatsCards;

public class CapturePage {

    public static VBox createPage() {

        VBox page = new VBox(20);

        page.setPadding(new Insets(20));

        page.setStyle(
                "-fx-background-color: #020617;"
        );

        // ================= TITLE =================
        Label title =
                new Label("📡 Live Capture");

        title.setStyle(
                "-fx-text-fill: cyan;" +
                        "-fx-font-size: 28px;" +
                        "-fx-font-weight: bold;"
        );

        // ================= TABLE =================
        TableView<PacketRow> table =
                new TableView<>();

        // Use same live packet data
        table.setItems(Dashboard.data);

        table.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY
        );

        table.setStyle(
                "-fx-control-inner-background: #0f172a;" +
                        "-fx-background-color: #0f172a;" +
                        "-fx-table-cell-border-color: #1e293b;" +
                        "-fx-text-background-color: white;" +
                        "-fx-font-size: 14px;"
        );

        // ================= TIME =================
        TableColumn<PacketRow, String> timeCol =
                new TableColumn<>("Time");

        timeCol.setCellValueFactory(
                new PropertyValueFactory<>("time")
        );

        // ================= SOURCE =================
        TableColumn<PacketRow, String> srcCol =
                new TableColumn<>("Source");

        srcCol.setCellValueFactory(
                new PropertyValueFactory<>("src")
        );

        // ================= DESTINATION =================
        TableColumn<PacketRow, String> dstCol =
                new TableColumn<>("Destination");

        dstCol.setCellValueFactory(
                new PropertyValueFactory<>("dst")
        );

        // ================= PROTOCOL =================
        TableColumn<PacketRow, String> protocolCol =
                new TableColumn<>("Protocol");

        protocolCol.setCellValueFactory(
                new PropertyValueFactory<>("protocol")
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
                protocolCol,
                riskCol
        );

        // Fill available space
        VBox.setVgrow(table, Priority.ALWAYS);

        // ================= ADD TO PAGE =================
        page.getChildren().addAll(
                title,
                StatsCards.createCards(),
                table
        );

        return page;
    }
}