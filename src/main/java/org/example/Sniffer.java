package org.example;

import org.example.ui.AlertsPage;
import javafx.application.Platform;
import javafx.scene.control.Alert;

import org.example.ui.AnalyticsPage;
import org.example.ui.Dashboard;
import org.example.ui.PacketRow;

import org.pcap4j.core.BpfProgram;
import org.pcap4j.core.NotOpenException;
import org.pcap4j.core.PacketListener;
import org.pcap4j.core.PcapHandle;
import org.pcap4j.core.PcapNetworkInterface;
import org.pcap4j.core.Pcaps;

import org.pcap4j.packet.IpV4Packet;
import org.pcap4j.packet.Packet;
import org.pcap4j.packet.TcpPacket;

import java.io.FileWriter;
import java.io.PrintWriter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sniffer {


    // ================= FILTER =================
    static final String FILTER = "tcp";

    // ================= LOG FILE =================
    static final String LOG_FILE =
            "packets_log.txt";

    // ================= RUNNING =================
    static volatile boolean running = false;

    // ================= HANDLE =================
    static PcapHandle handle;

    // ================= FILE WRITER =================
    static PrintWriter fileWriter;

    // ================= IP COUNTER =================
    static Map<String, Integer> ipCounter =
            new HashMap<>();
    static Map<String, Integer> portScanCounter =
            new HashMap<>();

    // ================= ALERT CONTROL =================
    static boolean alertShown = false;

    // ================= START CAPTURE =================
    public static void startCapture() {

        if (running) {
            return;
        }

        running = true;

        new Thread(() -> {

            try {

                runSniffer();

            } catch (Exception e) {

                e.printStackTrace();
            }

        }).start();
    }

    // ================= STOP CAPTURE =================
    public static void stopCapture() {

        running = false;

        try {

            if (handle != null &&
                    handle.isOpen()) {

                handle.breakLoop();

                handle.close();
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        System.out.println("Capture STOPPED.");
    }

    // ================= MAIN =================
    private static void runSniffer()
            throws Exception {

        // ================= FILE LOGGER =================
        fileWriter =
                new PrintWriter(
                        new FileWriter(LOG_FILE, false)
                );

        String startTime =
                LocalDateTime.now()
                        .format(
                                DateTimeFormatter.ofPattern(
                                        "yyyy-MM-dd HH:mm:ss"
                                )
                        );

        fileWriter.println(
                "===== DPI LOG STARTED ====="
        );

        fileWriter.println(
                "Started: " + startTime
        );

        fileWriter.println();

        fileWriter.flush();

        // ================= NETWORK DEVICES =================
        List<PcapNetworkInterface> devices =
                Pcaps.findAllDevs();

        if (devices == null ||
                devices.isEmpty()) {

            System.out.println(
                    "No network devices found!"
            );

            return;
        }

        // ================= SELECT DEVICE =================
        int index =
                Math.min(4, devices.size() - 1);

        System.out.println(
                "Using Interface:"
        );

        System.out.println(
                devices.get(index).getDescription()
        );

        // ================= OPEN DEVICE =================
        handle = devices.get(index).openLive(

                65536,

                PcapNetworkInterface
                        .PromiscuousMode
                        .PROMISCUOUS,

                10
        );

        // ================= FILTER =================
        handle.setFilter(
                FILTER,
                BpfProgram.BpfCompileMode.OPTIMIZE
        );

        System.out.println(
                "Capture started..."
        );

        // ================= PACKET LOOP =================
        handle.loop(

                -1,

                (PacketListener) packet -> {

                    // ================= STOP CHECK =================
                    if (!running) {

                        try {

                            if (handle != null &&
                                    handle.isOpen()) {

                                handle.breakLoop();
                            }

                        } catch (NotOpenException e) {

                            e.printStackTrace();
                        }

                        return;
                    }

                    // ================= PACKET TYPES =================
                    IpV4Packet ip =
                            packet.get(IpV4Packet.class);

                    TcpPacket tcp =
                            packet.get(TcpPacket.class);

                    if (ip == null ||
                            tcp == null) {

                        return;
                    }

                    // ================= IP =================
                    String src =
                            ip.getHeader()
                                    .getSrcAddr()
                                    .getHostAddress();

                    String dst =
                            ip.getHeader()
                                    .getDstAddr()
                                    .getHostAddress();

                    // ================= SIZE =================
                    int size =
                            packet.length();

                    // ================= PROTOCOL =================
                    String protocol =
                            "TCP";

                    // ================= PORTS =================
                    int srcPort =
                            tcp.getHeader()
                                    .getSrcPort()
                                    .valueAsInt();

                    int dstPort =
                            tcp.getHeader()
                                    .getDstPort()
                                    .valueAsInt();

                    // ================= APPLICATION DETECTION =================
                    String application;

                    if (dst.contains("142.250") ||
                            dst.contains("172.217")) {

                        application =
                                "Google Services";
                    }

                    else if (dst.contains("142.251")) {

                        application =
                                "YouTube";
                    }

                    else if (dst.contains("157.240")) {

                        application =
                                "WhatsApp / Facebook";
                    }

                    else if (dst.contains("54.230")) {

                        application =
                                "Netflix";
                    }

                    else if (dst.contains("13.") ||
                            dst.contains("20.")) {

                        application =
                                "Microsoft Services";
                    }


else if (dst.contains("140.82")) {

            application =
                    "GitHub";
        }

        else if (dst.contains("31.13")) {

            application =
                    "Instagram";
        }

        else if (dst.contains("151.101")) {

            application =
                    "StackOverflow";
        }


        else if (srcPort == 443 ||
                            dstPort == 443) {

                        application =
                                "HTTPS Secure Traffic";
                    }

                    else if (srcPort == 80 ||
                            dstPort == 80) {

                        application =
                                "HTTP Web Traffic";
                    }

                    else if (srcPort == 53 ||
                            dstPort == 53) {

                        application =
                                "DNS Service";
                    }

                    else if (srcPort == 21 ||
                            dstPort == 21) {

                        application =
                                "FTP Service";
                    }

                    else if (srcPort == 22 ||
                            dstPort == 22) {

                        application =
                                "SSH Connection";
                    }

                    else if (srcPort == 25 ||
                            dstPort == 25) {

                        application =
                                "SMTP Mail Service";
                    }

                    else if (srcPort == 3306 ||
                            dstPort == 3306) {

                        application =
                                "MySQL Database";
                    }

                    else if (srcPort == 3389 ||
                            dstPort == 3389) {

                        application =
                                "Remote Desktop";
                    }

                    else {

                        application =
                                "Unknown Application";
                    }

                    // ================= TIME =================
                    String time =
                            LocalDateTime.now()
                                    .format(
                                            DateTimeFormatter.ofPattern(
                                                    "HH:mm:ss"
                                            )
                                    );

                    // ================= REQUEST COUNT =================
                    ipCounter.put(

                            src,

                            ipCounter.getOrDefault(src, 0) + 1
                    );

                    int requestCount =
                            ipCounter.get(src);
                    // ================= PORT SCAN COUNT =================

                    portScanCounter.put(

                            src,

                            portScanCounter.getOrDefault(src, 0) + 1
                    );

                    int portScanCount =
                            portScanCounter.get(src);

                    // ================= RISK ANALYSIS =================

                    final String risk;

                    boolean suspiciousPort =

                            srcPort == 21  || dstPort == 21  ||   // FTP
                                    srcPort == 23  || dstPort == 23  ||   // Telnet
                                    srcPort == 445 || dstPort == 445 ||   // SMB
                                    srcPort == 135 || dstPort == 135 ||   // RPC
                                    srcPort == 3389|| dstPort == 3389;    // RDP

                    boolean packetFlood =
                            requestCount > 100;
                    boolean portScanDetected =
                            portScanCount > 200;

                    boolean unknownTraffic =
                            application.equals("Unknown Application");

                    if (requestCount > 100 ||
                            unknownTraffic ||
                            portScanDetected) {

                        risk = "HIGH";
                    }
                    else if (requestCount > 50 ||
                            suspiciousPort) {

                        risk = "MEDIUM";
                    }
                    else {

                        risk = "LOW";
                    }
                    // ================= CONSOLE =================
                    System.out.println("--------------------------------");

                    System.out.println(
                            "TIME : " + time
                    );

                    System.out.println(
                            "SRC  : " + src
                    );

                    System.out.println(
                            "DST  : " + dst
                    );

                    System.out.println(
                            "APP  : " + application
                    );

                    System.out.println(
                            "PROTOCOL : " + protocol
                    );

                    System.out.println(
                            "SIZE : " + size
                    );

                    System.out.println(
                            "RISK : " + risk
                    );

                    System.out.println("--------------------------------");

                    // ================= FILE LOG =================
                    fileWriter.println(

                            time + " | " +

                                    src + " -> " +

                                    dst + " | " +

                                    application + " | " +

                                    protocol + " | " +

                                    size + " bytes | " +

                                    risk
                    );

                    fileWriter.flush();

                    // ================= ALERT =================
                    if (risk.equals("HIGH")
                            && !alertShown) {

                        alertShown = true;

                        AlertsPage.alerts.add(
                                time + " | HIGH | " + src
                        );

                        Platform.runLater(() -> {

                            Alert alert =
                                    new Alert(
                                            Alert.AlertType.WARNING
                                    );

                            alert.setTitle(
                                    "Threat Detected"
                            );

                            alert.setHeaderText(
                                    "⚠ Suspicious Traffic"
                            );

                            alert.setContentText(

                                    "Potential malicious traffic detected from:\n"
                                            + src
                            );

                            alert.showAndWait();

                            alertShown = false;
                        });
                    }

                    // ================= UPDATE UI =================
                    Platform.runLater(() -> {

                        PacketRow row =
                                new PacketRow(

                                        time,

                                        src,

                                        dst,

                                        application,

                                        protocol,

                                        String.valueOf(size),

                                        risk
                                );

                        Dashboard.data.add(row);
                        if (risk.equals("HIGH")) {

                            Dashboard.alertData.add(row);
                        }
                        Dashboard.counterLabel.setText(

                                "📦 Packets: " +

                                        Dashboard.data.size()
                        );

                        if (risk.equals("LOW")) {

                            Dashboard.lowCount++;
                        }

                        else if (risk.equals("MEDIUM")) {

                            Dashboard.mediumCount++;
                        }

                        else {

                            Dashboard.highCount++;
                        }

                        // ================= PROTOCOL COUNT =================

                        if (protocol.equalsIgnoreCase("TCP")) {

                            AnalyticsPage.tcpCount++;
                        }

                        else if (protocol.equalsIgnoreCase("UDP")) {

                            AnalyticsPage.udpCount++;
                        }

                        else if (protocol.equalsIgnoreCase("HTTP")) {

                            AnalyticsPage.httpCount++;
                        }

                        else if (protocol.equalsIgnoreCase("HTTPS")) {

                            AnalyticsPage.httpsCount++;
                        }

                        AnalyticsPage.updateProtocolChart();

                        Dashboard.lowRiskLabel.setText(

                                "🟢 Low Risk: " +

                                        Dashboard.lowCount
                        );

                        Dashboard.mediumRiskLabel.setText(

                                "🟡 Medium Risk: " +

                                        Dashboard.mediumCount
                        );

                        Dashboard.highRiskLabel.setText(

                                "🔴 High Risk: " +

                                        Dashboard.highCount
                        );

                        AnalyticsPage.updateThreatSummary(

                                Dashboard.lowCount,

                                Dashboard.mediumCount,

                                Dashboard.highCount
                        );

                        AnalyticsPage.updateChart(

                                Dashboard.data.size()
                        );
                    });
                }
        );

        // ================= CLEANUP =================
        if (fileWriter != null) {

            fileWriter.println(
                    "===== CAPTURE STOPPED ====="
            );

            fileWriter.close();
        }

        if (handle != null &&
                handle.isOpen()) {

            handle.close();
        }

        System.out.println(
                "Sniffer stopped safely."
        );
    }

}
