package com.hanshudan.general;

import com.hanshudan.network.FSRoute;
import com.hanshudan.network.Network;

import java.util.ArrayList;

public class Timeroute extends Network {
    private ArrayList<Time>timeslot;
    private int firstSlotNum=0;
    private int lastSlotNum=0;
    private ArrayList<FSRoute> slotWindows;


    public Timeroute(String name, int index, int fiberNumber, int g){
        super(name, index, fiberNumber,g);
        this.timeslot = new ArrayList<>();
        this.slotWindows = new ArrayList<>();
    }

    public ArrayList<Time> getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(ArrayList<Time> timeslot) {
        this.timeslot = timeslot;
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

    public ArrayList<FSRoute> getSlotWindows() {
        return slotWindows;
    }

    public void setSlotWindows(ArrayList<FSRoute> slotWindows) {
        this.slotWindows = slotWindows;
    }
}
