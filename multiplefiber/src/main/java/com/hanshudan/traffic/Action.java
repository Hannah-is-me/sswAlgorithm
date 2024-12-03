package com.hanshudan.traffic;

import lombok.Data;
import lombok.NoArgsConstructor;


// 判断动态事件状态
public class Action {

    private Connection con = null;
    private double starttime;
    private double duratime;
    private double endtime;
    private  double weight;

    public Action(Connection con, double starttime, double duratime, double endtime) {

        this.con = con;
        this.starttime = starttime;
        this.duratime = duratime;
        this.endtime = endtime;
        this.weight = 0;
    }



    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    public double getStarttime() {
        return starttime;
    }

    public void setStarttime(double starttime) {
        this.starttime = starttime;
    }

    public double getDuratime() {
        return duratime;
    }

    public void setDuratime(double duratime) {
        this.duratime = duratime;
    }

    public double getEndtime() {
        return endtime;
    }

    public void setEndtime(double endtime) {
        this.endtime = endtime;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}


