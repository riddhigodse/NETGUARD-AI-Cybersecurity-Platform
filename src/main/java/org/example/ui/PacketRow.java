package org.example.ui;

public class PacketRow {

    private String time;
    private String src;
    private String dst;
    private String application;
    private String protocol;
    private String size;
    private String risk;

    // ================= CONSTRUCTOR =================
    public PacketRow(

            String time,

            String src,

            String dst,

            String application,

            String protocol,

            String size,

            String risk
    ) {

        this.time = time;

        this.src = src;

        this.dst = dst;

        this.application = application;

        this.protocol = protocol;

        this.size = size;

        this.risk = risk;
    }

    // ================= GETTERS =================
    public String getTime() {
        return time;
    }

    public String getSrc() {
        return src;
    }

    public String getDst() {
        return dst;
    }

    public String getApplication() {
        return application;
    }

    public String getProtocol() {
        return protocol;
    }

    public String getSize() {
        return size;
    }

    public String getRisk() {
        return risk;
    }

    // ================= SETTERS =================
    public void setTime(String time) {
        this.time = time;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public void setDst(String dst) {
        this.dst = dst;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setRisk(String risk) {
        this.risk = risk;
    }
}