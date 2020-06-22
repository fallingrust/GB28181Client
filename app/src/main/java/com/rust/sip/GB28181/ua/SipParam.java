package com.rust.sip.GB28181.ua;

/**
 * @author hongquan.zhao
 * Create Date:2019/1/7
 * Version:V1.0
 * SipDroid project init param class
 */
public class SipParam {

    private String username;
    private String password;
    private String server;
    private String domain;
    private String port;
    private String protocol;

    //是否回调
    private boolean callback;
    private String posUrl;

    private boolean open;
    private boolean m3G;
    private boolean wlan;
    private boolean edge;
    private String fromUser;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public boolean callback() {
        return callback;
    }

    public void setCallback(boolean callback) {
        this.callback = callback;
    }

    public String getPosUrl() {
        return posUrl;
    }

    public void setPosUrl(String posUrl) {
        this.posUrl = posUrl;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean is3G() {
        return m3G;
    }

    public void set3G(boolean m3G) {
        this.m3G = m3G;
    }

    public boolean isWlan() {
        return wlan;
    }

    public void setWlan(boolean wlan) {
        this.wlan = wlan;
    }

    public boolean edge() {
        return edge;
    }

    public void setEdge(boolean edge) {
        this.edge = edge;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public static class Builder {
        private SipParam cloudRequest;

        public Builder() {
            cloudRequest = new SipParam();
        }

        public Builder setUsername(String username) {
            this.cloudRequest.username = username;
            return this;
        }

        public Builder setPassword(String password) {
            this.cloudRequest.password = password;
            return this;
        }

        public Builder setServer(String server) {
            this.cloudRequest.server = server;
            return this;
        }


        public Builder setDomain(String domain) {
            this.cloudRequest.domain = domain;
            return this;
        }


        public Builder setPort(String port) {
            this.cloudRequest.port = port;
            return this;
        }

        public Builder setProtocol(String protocol) {
            this.cloudRequest.protocol = protocol;
            return this;
        }

        public Builder setCallback(boolean callback) {
            this.cloudRequest.callback = callback;
            return this;
        }

        public Builder setPosUrl(String posUrl) {
            this.cloudRequest.posUrl = posUrl;
            return this;
        }

        public Builder setOpen(boolean open) {
            this.cloudRequest.open = open;
            return this;
        }


        public Builder set3G(boolean m3G) {
            this.cloudRequest.m3G = m3G;
            return this;
        }

        public Builder setWlan(boolean wlan) {
            this.cloudRequest.wlan = wlan;
            return this;
        }

        public Builder setEdge(boolean edge) {
            this.cloudRequest.edge = edge;
            return this;
        }

        public Builder setFromuser(String fromUser) {
            this.cloudRequest.fromUser = fromUser;
            return this;
        }

        public SipParam build() {
            return this.cloudRequest;
        }
    }
}
