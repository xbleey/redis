package com.xbleey.entity;

public class Text {
    private String content;

    private String[] mentioned_list;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String[] getMentioned_list() {
        return mentioned_list;
    }

    public void setMentioned_list(String[] mentioned_list) {
        this.mentioned_list = mentioned_list;
    }

    public Text(String content, String[] mentioned_list) {
        this.content = content;
        this.mentioned_list = mentioned_list;
    }
}
