package com.webLean.domain;

public class WechatProperties {
    private Long id;

    private String accesstoken;

    private String jsapiTicket;

    private String apiTicket;

    private String refreshtime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccesstoken() {
        return accesstoken;
    }

    public void setAccesstoken(String accesstoken) {
        this.accesstoken = accesstoken == null ? null : accesstoken.trim();
    }

    public String getJsapiTicket() {
        return jsapiTicket;
    }

    public void setJsapiTicket(String jsapiTicket) {
        this.jsapiTicket = jsapiTicket == null ? null : jsapiTicket.trim();
    }

    public String getApiTicket() {
        return apiTicket;
    }

    public void setApiTicket(String apiTicket) {
        this.apiTicket = apiTicket == null ? null : apiTicket.trim();
    }

    public String getRefreshtime() {
        return refreshtime;
    }

    public void setRefreshtime(String refreshtime) {
        this.refreshtime = refreshtime == null ? null : refreshtime.trim();
    }
}