package com.xbleey.entity;

public class SentBody {
    private String msgtype;
    private Text text;

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }

    public SentBody(String msgtype, Text text) {
        this.msgtype = msgtype;
        this.text = text;
    }
}
