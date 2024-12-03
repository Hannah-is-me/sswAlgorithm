package com.hanshudan.network;


import com.hanshudan.algorithm.Dijkstra;
import com.hanshudan.general.Constant;
import com.hanshudan.general.SearchConstraint;
import com.hanshudan.traffic.Action;
import com.hanshudan.traffic.Connection;

import java.util.*;

public class FSRoute extends Network {
    private ArrayList<Wavelength> SlotList=null;
    private int firstSlotNum=0;
    private int lastSlotNum=0;
    private SearchConstraint constraint=null;
    private int fiberFSweight;



    public FSRoute(String name, int index, int fiberNumber, int g) {
        super(name, index,fiberNumber,g);
  //      this.readTopology("D:\\phd\\Projects\\Muti_fiber networks\\JAVA\\RSWAStrategy\\firstfitnodepair\\multiplefiber\\src\\test\\java\\11.csv");
        this.SlotList = new ArrayList<Wavelength>();
        this.constraint = new SearchConstraint();
        this.fiberFSweight = 0;
    }

    public int getFiberFSweight() {
        return fiberFSweight;
    }

    public void setFiberFSweight(int fiberFSweight) {
        this.fiberFSweight = fiberFSweight;
    }

    public SearchConstraint getConstraint() {
        return constraint;
    }

    public void setConstraint(SearchConstraint constraint) {
        this.constraint = constraint;
    }

    public ArrayList<Wavelength> getSlotList() {
        return SlotList;
    }

    public void setSlotList(ArrayList<Wavelength> slotList) {
        SlotList = slotList;
    }

    public int getFirstSlotNum() {
        return firstSlotNum;
    }

    public void setFirstSlotNum(int firstSlotNum) {
        this.firstSlotNum = firstSlotNum;
    }

    public int getLastSlotNum() {
        return lastSlotNum;
    }

    public void setLastSlotNum(int lastSlotNum) {
        this.lastSlotNum = lastSlotNum;
    }
}

