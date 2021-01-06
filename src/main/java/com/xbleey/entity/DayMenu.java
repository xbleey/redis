package com.xbleey.entity;

import java.time.LocalDate;
import java.util.List;

public class DayMenu {
    private String date;
    private String week;
    private List<Message> menus;
    private int riceNum;

    public DayMenu(int beforeDay) {
        this.date = getToday(beforeDay);
        this.week = getTodayWeek(beforeDay);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public List<Message> getMenus() {
        return menus;
    }

    public void setMenus(List<Message> menus) {
        this.menus = menus;
        for (Message message : menus) {
            riceNum += message.getNum();
        }
    }

    public String getToday(int beforeDay) {
        LocalDate localDate = LocalDate.now();
        localDate = localDate.minusDays(beforeDay);
        int year = localDate.getYear();
        int month = localDate.getMonthValue();
        int day = localDate.getDayOfMonth();
        String date = year + "-" + (month >= 10 ? month : String.valueOf("0" + month)) + "-" + (day >= 10 ? day : String.valueOf("0" + day));
        return date;
    }

    private String getTodayWeek(int beforeDay) {
        LocalDate localDate = LocalDate.now();
        localDate = localDate.minusDays(beforeDay);
        return String.valueOf(localDate.getDayOfWeek());
    }

    public int getRiceNum() {
        return riceNum;
    }

    public void setRiceNum(int riceNum) {
        this.riceNum = riceNum;
    }

    @Override
    public String toString() {
        return "DayMenu{" +
                "date='" + date + '\'' +
                ", week='" + week + '\'' +
                ", menus=" + menus +
                ", riceNum=" + riceNum +
                '}';
    }
}
