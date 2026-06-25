package org.example.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class AISecurityAssistantPage {

    public static VBox createPage() {

        // ================= ROOT =================

        VBox root = new VBox(15);

        root.setPadding(new Insets(20));

        root.setStyle(
                "-fx-background-color: #020617;"
        );

        // ================= TITLE =================

        Label title =
                new Label("NETGUARD AI");

        title.setStyle(
                "-fx-text-fill: cyan;" +
                        "-fx-font-size: 34px;" +
                        "-fx-font-weight: bold;"
        );
        

        // ================= SUBTITLE =================

        Label subtitle =
                new Label(
                        "Cyber Defense & Threat Intelligence Center"
                );

        subtitle.setStyle(
                "-fx-text-fill: #94A3B8;" +
                        "-fx-font-size: 16px;"
        );

        // ================= STATUS =================

        Label status =
                new Label(
                        "● Monitoring Live Network Activity"
                );

        status.setStyle(
                "-fx-text-fill: #22c55e;" +
                        "-fx-font-size: 14px;" +
                        "-fx-font-weight: bold;"
        );

        // ================= HEADER =================

        VBox header =
                new VBox(5);

        header.setAlignment(Pos.CENTER);

        header.getChildren().addAll(
                title,
                subtitle,
                status
        );

        // ================= CHAT AREA =================

        TextArea chatArea =
                new TextArea();

        chatArea.setEditable(false);

        chatArea.setWrapText(true);

        chatArea.setPrefHeight(500);

        chatArea.setStyle(
                "-fx-control-inner-background: #0f172a;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 15px;" +
                        "-fx-background-radius: 12;" +
                        "-fx-border-radius: 12;" +
                        "-fx-border-color: #1e293b;"
        );

        chatArea.appendText(
                "NETGUARD AI initialized successfully.\n\n" +
                        "Capabilities:\n" +
                        "• Deep Packet Inspection (DPI)\n" +
                        "• Threat Detection\n" +
                        "• Network Protocol Analysis\n" +
                        "• Malware Intelligence\n" +
                        "• Risk Assessment\n" +
                        "• Cybersecurity Guidance\n\n" +
                        "Ask a cybersecurity-related question to begin.\n\n"
        );

        // ================= INPUT FIELD =================

        TextField inputField =
                new TextField();

        inputField.setPromptText(
                "Ask NETGUARD AI about cybersecurity..."
        );

        inputField.setPrefWidth(550);

        inputField.setStyle(
                "-fx-background-color: #1e293b;" +
                        "-fx-text-fill: white;" +
                        "-fx-prompt-text-fill: gray;" +
                        "-fx-background-radius: 10;"
        );

        // ================= SEND BUTTON =================

        Button sendBtn =
                new Button("Analyze");

        sendBtn.setStyle(
                "-fx-background-color: #06b6d4;" +
                        "-fx-text-fill: black;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 10;"
        );

        // ================= BUTTON ACTION =================

        sendBtn.setOnAction(e -> {

            String question =
                    inputField.getText();

            if (question == null ||
                    question.trim().isEmpty()) {

                return;
            }

            chatArea.appendText(
                    "👤 You: " +
                            question +
                            "\n\n"
            );

            try {

                String response =
                        CyberSecurityBot.getResponse(question);

                chatArea.appendText(
                        "🤖 NETGUARD AI: " +
                                response +
                                "\n\n"
                );

            } catch (Exception ex) {

                chatArea.appendText(
                        "⚠ AI Error Occurred\n\n"
                );

                ex.printStackTrace();
            }

            inputField.clear();
        });

        // ================= INPUT ROW =================

        HBox inputRow =
                new HBox(10);

        inputRow.setAlignment(
                Pos.CENTER_LEFT
        );

        inputRow.getChildren().addAll(
                inputField,
                sendBtn
        );

        // ================= ADD ALL =================

        root.getChildren().addAll(
                header,
                chatArea,
                inputRow
        );

        return root;
    }

}
