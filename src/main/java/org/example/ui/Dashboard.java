package org.example.ui;

import javafx.application.Application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.Scene;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javafx.stage.Stage;

import org.example.Sniffer;

public class Dashboard extends Application {

    public static VBox dashboardPage;
    // ================= RUNNING =================
    private static volatile boolean running = false;

    // ================= TABLE DATA =================
    public static ObservableList<PacketRow> data =
            FXCollections.observableArrayList();
    public static ObservableList<PacketRow> alertData =
            FXCollections.observableArrayList();

    // ================= LABELS =================
    public static Label counterLabel =
            new Label("📦 Packets: 0");

    public static Label lowRiskLabel =
            new Label("🟢 Low Risk: 0");

    public static Label mediumRiskLabel =
            new Label("🟡 Medium Risk: 0");

    public static Label highRiskLabel =
            new Label("🔴 High Risk: 0");

    // ================= COUNTERS =================
    public static int lowCount = 0;
    public static int mediumCount = 0;
    public static int highCount = 0;

    @Override
    public void start(Stage stage) {

        // ================= ROOT =================
        BorderPane root =
                new BorderPane();

        root.setStyle(
                "-fx-background-color: #020617;"
        );

        // ================= TITLE =================
        Label title =
                new Label(
                        "🔍 AI Powered Deep Packet Inspector"
                );

        title.setStyle(

                "-fx-font-size: 30px;" +

                        "-fx-font-weight: bold;" +

                        "-fx-text-fill: cyan;"
        );

        // ================= STATUS =================
        Label status =
                new Label("Status: Idle");

        status.setStyle(

                "-fx-text-fill: white;" +

                        "-fx-font-size: 16px;"
        );

        // ================= SEARCH =================
        TextField searchField =
                new TextField();

        searchField.setPromptText(
                "Search Source IP"
        );

        searchField.setPrefWidth(220);

        searchField.setStyle(

                "-fx-background-color: #1e293b;" +

                        "-fx-text-fill: white;" +

                        "-fx-prompt-text-fill: gray;" +

                        "-fx-background-radius: 8px;"
        );

        // ================= FILTER =================
        ComboBox<String> protocolFilter =
                new ComboBox<>();

        protocolFilter.getItems().addAll(
                "ALL",
                "TCP"
        );

        protocolFilter.setValue("ALL");

        protocolFilter.setStyle(

                "-fx-background-color: #1e293b;" +

                        "-fx-text-fill: white;"
        );

        // ================= CLEAR BUTTON =================
        Button clearBtn =
                new Button("🗑 Clear");

        clearBtn.setStyle(

                "-fx-background-color: #dc2626;" +

                        "-fx-text-fill: white;" +

                        "-fx-font-weight: bold;" +

                        "-fx-background-radius: 8px;"
        );

        clearBtn.setOnAction(e -> {

            data.clear();

            counterLabel.setText(
                    "📦 Packets: 0"
            );

            lowCount = 0;
            mediumCount = 0;
            highCount = 0;

            lowRiskLabel.setText(
                    "🟢 Low Risk: 0"
            );

            mediumRiskLabel.setText(
                    "🟡 Medium Risk: 0"
            );

            highRiskLabel.setText(
                    "🔴 High Risk: 0"
            );
        });

        // ================= EXPORT BUTTON =================
        Button exportBtn =
                new Button("📁 Export CSV");

        exportBtn.setStyle(

                "-fx-background-color: #2563eb;" +

                        "-fx-text-fill: white;" +

                        "-fx-font-weight: bold;" +

                        "-fx-background-radius: 8px;"
        );

        exportBtn.setOnAction(e -> {

            try {
                java.io.File reportsFolder =
                        new java.io.File("Reports");

                if (!reportsFolder.exists()) {

                    reportsFolder.mkdir();
                }

                String fileName =
                        "Reports/packets_" +

                                java.time.LocalDateTime.now()
                                        .format(
                                                java.time.format.DateTimeFormatter.ofPattern(
                                                        "yyyyMMdd_HHmmss"
                                                )
                                        )

                                + ".csv";

                java.io.FileWriter writer =
                        new java.io.FileWriter(
                                fileName
                        );

                writer.write(
                        "Time,Source,Destination,Application,Protocol,Size,Risk\n"
                );

                for (PacketRow p : data) {

                    writer.write(

                            p.getTime() + "," +

                                    p.getSrc() + "," +

                                    p.getDst() + "," +

                                    p.getApplication() + "," +

                                    p.getProtocol() + "," +

                                    p.getSize() + "," +

                                    p.getRisk() + "\n"
                    );
                }

                writer.close();

                Alert alert =
                        new Alert(
                                Alert.AlertType.INFORMATION
                        );

                alert.setTitle("Success");

                alert.setHeaderText(null);

                alert.setContentText(
                        "CSV Exported Successfully!"
                );

                alert.showAndWait();

            } catch (Exception ex) {

                ex.printStackTrace();
            }
        });

        // ================= STATS =================
        HBox statsBar =
                new HBox(
                        20,

                        counterLabel,

                        lowRiskLabel,

                        mediumRiskLabel,

                        highRiskLabel
                );

        statsBar.setAlignment(Pos.CENTER_LEFT);

        counterLabel.setStyle(

                "-fx-text-fill: white;" +

                        "-fx-font-size: 15px;" +

                        "-fx-font-weight: bold;"
        );

        lowRiskLabel.setStyle(

                "-fx-text-fill: lime;" +

                        "-fx-font-size: 15px;" +

                        "-fx-font-weight: bold;"
        );

        mediumRiskLabel.setStyle(

                "-fx-text-fill: orange;" +

                        "-fx-font-size: 15px;" +

                        "-fx-font-weight: bold;"
        );

        highRiskLabel.setStyle(

                "-fx-text-fill: red;" +

                        "-fx-font-size: 15px;" +

                        "-fx-font-weight: bold;"
        );

        // ================= TOP BAR =================
        HBox topBar =
                new HBox(
                        20,

                        searchField,

                        protocolFilter,

                        clearBtn,

                        exportBtn
                );

        topBar.setAlignment(Pos.CENTER_LEFT);

        // ================= TABLE =================
        TableView<PacketRow> table =
                new TableView<>();

        table.setItems(data);

        table.setPrefHeight(500);

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

        timeCol.setPrefWidth(120);

        // ================= SOURCE =================
        TableColumn<PacketRow, String> srcCol =
                new TableColumn<>("Source");

        srcCol.setCellValueFactory(
                new PropertyValueFactory<>("src")
        );

        srcCol.setPrefWidth(200);

        // ================= DESTINATION =================
        TableColumn<PacketRow, String> dstCol =
                new TableColumn<>("Destination");

        dstCol.setCellValueFactory(
                new PropertyValueFactory<>("dst")
        );

        dstCol.setPrefWidth(220);

        // ================= APPLICATION =================
        TableColumn<PacketRow, String> appCol =
                new TableColumn<>("Application");

        appCol.setCellValueFactory(
                new PropertyValueFactory<>("application")
        );

        appCol.setPrefWidth(220);

        // ================= PROTOCOL =================
        TableColumn<PacketRow, String> protocolCol =
                new TableColumn<>("Protocol");

        protocolCol.setCellValueFactory(
                new PropertyValueFactory<>("protocol")
        );

        protocolCol.setPrefWidth(120);

        // ================= SIZE =================
        TableColumn<PacketRow, String> sizeCol =
                new TableColumn<>("Size");

        sizeCol.setCellValueFactory(
                new PropertyValueFactory<>("size")
        );

        sizeCol.setPrefWidth(120);

        // ================= RISK =================
        TableColumn<PacketRow, String> riskCol =
                new TableColumn<>("Risk");

        riskCol.setCellValueFactory(
                new PropertyValueFactory<>("risk")
        );

        riskCol.setPrefWidth(120);

        // ================= NORMAL CELLS =================
        setWhiteCell(timeCol);
        setWhiteCell(srcCol);
        setWhiteCell(dstCol);
        setWhiteCell(appCol);
        setWhiteCell(protocolCol);
        setWhiteCell(sizeCol);

        // ================= RISK COLORS =================
        riskCol.setCellFactory(column -> new TableCell<>() {

            @Override
            protected void updateItem(
                    String item,
                    boolean empty
            ) {

                super.updateItem(item, empty);

                if (empty || item == null) {

                    setText(null);

                    setStyle("");
                }

                else {

                    setText(item);

                    if (item.equals("HIGH")) {

                        setStyle(

                                "-fx-text-fill: red;" +

                                        "-fx-font-weight: bold;" +

                                        "-fx-background-color: #0f172a;"
                        );
                    }

                    else if (item.equals("MEDIUM")) {

                        setStyle(

                                "-fx-text-fill: orange;" +

                                        "-fx-font-weight: bold;" +

                                        "-fx-background-color: #0f172a;"
                        );
                    }

                    else {

                        setStyle(

                                "-fx-text-fill: lime;" +

                                        "-fx-font-weight: bold;" +

                                        "-fx-background-color: #0f172a;"
                        );
                    }
                }
            }
        });

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

        // ================= START BUTTON =================
        Button startBtn =
                new Button("▶ Start Capture");

        startBtn.setStyle(

                "-fx-background-color: #16a34a;" +

                        "-fx-text-fill: white;" +

                        "-fx-font-weight: bold;" +

                        "-fx-font-size: 14px;" +

                        "-fx-background-radius: 8px;"
        );

        // ================= STOP BUTTON =================
        Button stopBtn =
                new Button("⛔ Stop Capture");

        stopBtn.setStyle(

                "-fx-background-color: #dc2626;" +

                        "-fx-text-fill: white;" +

                        "-fx-font-weight: bold;" +

                        "-fx-font-size: 14px;" +

                        "-fx-background-radius: 8px;"
        );

        // ================= START ACTION =================
        startBtn.setOnAction(e -> {

            if (running) return;

            running = true;

            status.setText(
                    "Status: Capturing..."
            );

            status.setStyle(

                    "-fx-text-fill: lime;" +

                            "-fx-font-size: 15px;"
            );

            new Thread(
                    Sniffer::startCapture
            ).start();
        });

        // ================= STOP ACTION =================
        stopBtn.setOnAction(e -> {

            running = false;

            status.setText(
                    "Status: Stopped"
            );

            status.setStyle(

                    "-fx-text-fill: red;" +

                            "-fx-font-size: 15px;"
            );

            Sniffer.stopCapture();
        });

        // ================= BUTTON ROW =================
        HBox buttonRow =
                new HBox(
                        15,

                        startBtn,

                        stopBtn
                );

        buttonRow.setAlignment(Pos.CENTER_LEFT);

        // ================= MAIN CONTENT =================
        dashboardPage =
                new VBox(20);

        dashboardPage.setPadding(
                new Insets(20)
        );

        dashboardPage.setStyle(

                "-fx-background-color: #020617;"
        );

        dashboardPage.getChildren().addAll(

                title,

                status,

                statsBar,

                topBar,

                buttonRow,

                table
        );

        // ================= SIDEBAR =================
        root.setLeft(
                SidebarMenu.createSidebar(root)
        );

        // ================= CENTER =================
        root.setCenter(dashboardPage);

        // ================= SCENE =================
        Scene scene =
                new Scene(
                        root,
                        1400,
                        800
                );

        // ================= WINDOW =================
        stage.setTitle(
                "AI Cybersecurity Platform"
        );

        stage.setScene(scene);

        stage.setResizable(true);

        stage.show();
    }

    // ================= WHITE CELL =================
    private void setWhiteCell(
            TableColumn<PacketRow, String> column
    ) {

        column.setCellFactory(col ->
                new TableCell<>() {

                    @Override
                    protected void updateItem(
                            String item,
                            boolean empty
                    ) {

                        super.updateItem(item, empty);

                        if (empty || item == null) {

                            setText(null);

                            setStyle("");
                        }

                        else {

                            setText(item);

                            setStyle(

                                    "-fx-text-fill: white;" +

                                            "-fx-font-weight: bold;" +

                                            "-fx-background-color: #0f172a;"
                            );
                        }
                    }
                }
        );
    }

    // ================= MAIN =================
    public static void main(String[] args) {

        launch(args);
    }
}