package org.example.ui;

public class CyberSecurityBot {

    public static String getResponse(String question) {

        question =
                question.toLowerCase().trim();

        // ================= GREETINGS =================

        if (question.contains("hello") ||
                question.contains("hi") ||
                question.contains("hey")) {

            return """
Hello 👋

Welcome to the AI Cybersecurity Platform
created by Riddhi and Vikrant.

I am your Offline AI Security Assistant 🤖

You can ask me about:
• Deep Packet Inspection (DPI)
• Cyber attacks
• Ports and protocols
• Network traffic
• Suspicious activity
• Packet monitoring
• Firewalls and encryption

How can I help you today?
""";
        }

        else if (question.contains("who made this project")) {

            return """
This AI-Powered Cybersecurity Platform
was created by:

👩 Riddhi
👨 Vikrant

The project is designed to monitor
live network traffic, analyze packets,
detect suspicious activity, and provide
AI-based cybersecurity assistance.
""";
        }

        else if (question.contains("who are you")) {

            return """
I am your Offline AI Security Assistant 🤖

My purpose is to help users understand:
• Cybersecurity concepts
• Network protocols
• Ports and services
• Packet analysis
• DPI systems
• Cyber attacks
• Suspicious traffic

I also explain how this project works
in real time.
""";
        }

        // ================= DPI =================

        else if (question.contains("dpi system") ||
                question.contains("deep packet inspection")) {

            return """
Deep Packet Inspection (DPI) is an advanced
network traffic analysis technology used
to inspect packets traveling across a network.

Unlike basic packet filtering,
DPI examines:

• Source and destination IP
• Protocol information
• Packet contents
• Application traffic
• Suspicious patterns

DPI is commonly used in:
• Cybersecurity systems
• Firewalls
• Intrusion Detection Systems
• Traffic monitoring tools

In this project,
the DPI system captures live packets,
analyzes traffic,
detects suspicious activity,
and displays real-time insights
through the dashboard.
""";
        }

        // ================= PROJECT =================

        else if (question.contains("how does this project work")) {

            return """
This AI Cybersecurity Platform works by
capturing live network packets from
the system network interface.

The project performs:
• Live packet capture
• Protocol analysis
• Traffic monitoring
• Risk detection
• Packet inspection
• AI-based explanations

The dashboard displays:
• Source IP
• Destination IP
• Protocols
• Packet size
• Risk level
• Live traffic statistics

The AI Assistant helps users understand
attacks, ports, protocols,
and cybersecurity concepts.
""";
        }

        else if (question.contains("what does this dashboard do")) {

            return """
This dashboard is used for monitoring
and analyzing live network traffic.

Main features include:
• Real-time packet monitoring
• Deep Packet Inspection
• Suspicious traffic detection
• Protocol analysis
• Risk categorization
• Traffic statistics
• AI cybersecurity assistant

The dashboard helps users understand
network behavior and identify
potential security threats.
""";
        }

        // ================= PACKETS =================

        else if (question.contains("packet")) {

            return """
A packet is a small unit of data
transmitted across a network.

Whenever users:
• Open websites
• Send messages
• Download files
• Watch videos

the data is divided into packets.

Each packet contains:
• Source IP address
• Destination IP address
• Protocol information
• Data payload

This project captures and analyzes
these packets in real time.
""";
        }

        else if (question.contains("packet monitoring")) {

            return """
Packet monitoring is the process of
capturing and analyzing network packets
traveling through a network.

It helps in:
• Network analysis
• Traffic monitoring
• Threat detection
• Performance monitoring
• Cybersecurity investigations

This project uses packet monitoring
to inspect live network traffic
and identify suspicious activity.
""";
        }

        // ================= TRAFFIC =================

        else if (question.contains("network traffic")) {

            return """
Network traffic refers to the flow of
data packets across a network.

Examples include:
• Website browsing
• Video streaming
• File downloads
• Online gaming
• Messaging applications

Monitoring network traffic helps
identify unusual or malicious activity.

This project analyzes live traffic
to detect risks and suspicious behavior.
""";
        }

        else if (question.contains("suspicious traffic")) {

            return """
Suspicious traffic refers to unusual
or potentially harmful network activity.

Examples include:
• Port scanning
• DDoS attacks
• Malware communication
• Repeated failed requests
• Abnormal traffic spikes

Cybersecurity systems analyze
traffic patterns to identify
possible threats.

This project categorizes traffic
as LOW, MEDIUM, or HIGH risk
based on packet behavior.
""";
        }

        // ================= RISK =================

        else if (question.contains("high risk")) {

            return """
High risk traffic may indicate
dangerous or malicious activity.

Examples:
• DDoS attacks
• SYN Flood attacks
• Malware traffic
• Port scanning

High risk packets should be
investigated immediately because
they may affect system security
or network performance.
""";
        }

        else if (question.contains("risk detection")) {

            return """
Risk detection is the process of
analyzing network traffic patterns
to identify suspicious behavior.

The system examines:
• Packet frequency
• Protocols
• Traffic volume
• Connection behavior

Based on analysis,
traffic is categorized as:
🟢 LOW Risk
🟡 MEDIUM Risk
🔴 HIGH Risk
""";
        }

        // ================= PORTS =================

        else if (question.contains("port 80")) {

            return """
Port 80 is commonly used for
HTTP web communication.

It is responsible for:
• Loading websites
• Web browsing
• Internet communication

HTTP traffic on Port 80
is usually unencrypted.
""";
        }

        else if (question.contains("port 443")) {

            return """
Port 443 is used for HTTPS
secure encrypted communication.

It protects data using:
• SSL encryption
• TLS security protocols

Most modern websites use
Port 443 for secure browsing.

Examples:
• Online banking
• Gmail
• Shopping websites
""";
        }

        else if (question.contains("port 21")) {

            return """
Port 21 is used for
FTP (File Transfer Protocol).

FTP allows users to:
• Upload files
• Download files
• Transfer data between systems

Traditional FTP traffic
is not encrypted.
""";
        }

        else if (question.contains("port 22")) {

            return """
Port 22 is used for
SSH (Secure Shell).

SSH provides:
• Secure remote login
• Encrypted communication
• Remote server management

It is widely used by
system administrators.
""";
        }

        // ================= PROTOCOLS =================

        else if (question.contains("tcp")) {

            return """
TCP stands for
Transmission Control Protocol.

It is a reliable
connection-oriented protocol.

TCP ensures:
• Data delivery
• Correct packet order
• Error checking
• Reliable communication

TCP is commonly used in:
• Web browsing
• Email
• File transfer
""";
        }

        else if (question.contains("udp")) {

            return """
UDP stands for
User Datagram Protocol.

UDP is:
• Fast
• Lightweight
• Connectionless

Unlike TCP,
UDP does not guarantee
packet delivery.

It is commonly used in:
• Online gaming
• Video streaming
• Voice calls
""";
        }

        else if (question.contains("http")) {

            return """
HTTP stands for
HyperText Transfer Protocol.

It is used for communication
between web browsers and websites.

HTTP transfers:
• Web pages
• Images
• Website content

HTTP traffic is usually
not encrypted.
""";
        }

        else if (question.contains("https")) {

            return """
HTTPS stands for
HyperText Transfer Protocol Secure.

It is a secure version of HTTP.

HTTPS protects users using:
• SSL/TLS encryption
• Secure communication
• Data privacy

Most modern websites use HTTPS.
""";
        }

        // ================= ATTACKS =================

        else if (question.contains("syn flood")) {

            return """
SYN Flood is a type of
Denial-of-Service (DoS) attack.

In this attack,
the attacker sends a huge number
of TCP SYN requests to a server.

This causes:
• Resource exhaustion
• Slow performance
• Server crashes

Cybersecurity systems monitor
traffic patterns to detect
SYN Flood behavior.
""";
        }

        else if (question.contains("ddos")) {

            return """
DDoS stands for
Distributed Denial of Service.

In a DDoS attack,
multiple systems flood a server
with massive traffic.

Effects include:
• Website downtime
• Slow network performance
• Service disruption

DDoS attacks are one of the most
common cyber threats today.
""";
        }

        else if (question.contains("phishing")) {

            return """
Phishing is a cyber attack used
to steal sensitive information.

Attackers create:
• Fake emails
• Fake websites
• Fraudulent login pages

The goal is to steal:
• Passwords
• Banking information
• Personal data

Users should always verify
website authenticity before
sharing information.
""";
        }

        else if (question.contains("malware")) {

            return """
Malware means
Malicious Software.

It is designed to:
• Damage systems
• Steal information
• Spy on users
• Disrupt operations

Examples include:
• Viruses
• Worms
• Trojans
• Spyware
• Ransomware
""";
        }

        else if (question.contains("ransomware")) {

            return """
Ransomware is a dangerous type
of malware.

It encrypts user files
and demands payment to restore them.

Effects include:
• File loss
• Data encryption
• Business disruption

Prevention methods:
• Regular backups
• Antivirus software
• Safe browsing practices
""";
        }

        // ================= SECURITY =================

        else if (question.contains("firewall")) {

            return """
A Firewall is a network security
system that monitors and filters
incoming and outgoing traffic.

It helps:
• Block unauthorized access
• Prevent attacks
• Protect systems

Firewalls are essential components
of cybersecurity infrastructure.
""";
        }

        else if (question.contains("vpn")) {

            return """
VPN stands for
Virtual Private Network.

VPN creates a secure encrypted
connection between users
and the internet.

Benefits include:
• Privacy protection
• Secure browsing
• IP hiding
• Safe public WiFi usage
""";
        }

        else if (question.contains("encryption")) {

            return """
Encryption converts readable data
into secure unreadable format.

Only authorized users with
the correct key can decrypt it.

Encryption protects:
• Passwords
• Banking data
• Personal information
• Online communication
""";
        }

        else if (question.contains("ip address")) {

            return """
An IP Address is a unique identifier
assigned to devices connected
to a network.

It helps systems:
• Identify devices
• Send data
• Receive communication

Example:
192.168.1.1
""";
        }

        else if (question.contains("mac address")) {

            return """
MAC Address stands for
Media Access Control Address.

It is a unique hardware identifier
assigned to network devices.

MAC addresses are used for:
• Device identification
• Local network communication
• Network management
""";
        }

        // ================= THANK YOU =================

        else if (question.contains("thank")) {

            return """
You're welcome 😊

Happy to help you learn
Cybersecurity and Networking.

Stay secure 🔐
""";
        }

        // ================= DEFAULT =================

        else {

            return """
Sorry, I do not have information
about that topic yet.

Try asking about:
• DPI Systems
• Cyber attacks
• Ports and protocols
• Network traffic
• Packet monitoring
• Firewalls
• Encryption
• Suspicious activity
""";
        }
    }
}