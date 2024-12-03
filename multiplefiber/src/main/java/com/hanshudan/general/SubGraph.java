package com.hanshudan.general;

import com.hanshudan.network.Fiber;
import com.hanshudan.network.Node;

import java.util.ArrayList;
import java.util.HashMap;

public class SubGraph extends CommonObject {
    private ArrayList<Node> nodelist = null; // node list
    private ArrayList<Fiber> fiberlist = null; // link list
    private ArrayList<SubGraph> routelist = null; // a set of route associated w
    private HashMap<String, Fiber>  fiberMap = null;
    private int Fmaxrecord;

    public SubGraph(String name, Integer index) {
        super(name, index);
        this.nodelist = new ArrayList<>();
        this.fiberlist =  new ArrayList<>();
        this.routelist =  new ArrayList<>();
        this.Fmaxrecord = 0 ;
        this.fiberMap = new HashMap<>();
    }

    public void addFiber(Fiber fiber) {
        this.fiberlist.add(fiber);
        this.fiberMap.put(fiber.getName(), fiber);
    }

    public int getFmaxrecord() {
        int sum = 0;

        for (Fiber fiber : this.getFiberlist()) {
            sum += fiber.getFuse();
        }
        return sum;
    }

    public HashMap<String, Fiber> getFiberMap() {
        return fiberMap;
    }

    public void setFiberMap(HashMap<String, Fiber> fiberMap) {
        this.fiberMap = fiberMap;
    }

    public void setFmaxrecord(int fmaxrecord) {
        Fmaxrecord = fmaxrecord;
    }


//    /**
//     * get the cost of subgraph
//     */
//    public double getCost() {
//        double sum = 0;
//        for (Fiber fiber : this.getfiberlist()) {
//            sum += fiber.getCost();
//        }
//        return sum;
//    }


    public ArrayList<Node> getNodelist() {
        return nodelist;
    }

    public void setNodelist(ArrayList<Node> nodelist) {
        this.nodelist = nodelist;
    }

    public ArrayList<Fiber> getFiberlist() {
        return fiberlist;
    }

    public void setFiberlist(ArrayList<Fiber> fiberlist) {
        this.fiberlist = fiberlist;
    }

    public ArrayList<SubGraph> getRoutelist() {
        return routelist;
    }

    public void setRoutelist(ArrayList<SubGraph> routelist) {
        this.routelist = routelist;
    }
}
