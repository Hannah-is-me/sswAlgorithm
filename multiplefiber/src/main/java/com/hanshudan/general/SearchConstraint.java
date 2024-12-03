package com.hanshudan.general;

import com.hanshudan.network.Fiber;
import com.hanshudan.network.Node;

import java.util.ArrayList;

public class SearchConstraint {
    private ArrayList<Node> excludedNodelist = null; //excluded node list
    private ArrayList<Fiber> excludedFiberlist = null; //exclueded link list
    private double max_length = 10000000; //max-length
    private int max_hop = 10000000; //max hops

    public SearchConstraint() {
        this.excludedNodelist = new ArrayList<Node>();
        this.excludedFiberlist = new ArrayList<Fiber>();
    }

    public SearchConstraint(double max_length, int max_hop) {
        super();
        this.excludedNodelist = new ArrayList<Node>();
        this.excludedFiberlist = new ArrayList<Fiber>();
        this.max_length = max_length;
        this.max_hop = max_hop;
    }


    public ArrayList<Node> getExcludedNodelist() {
        return excludedNodelist;
    }

    public void setExcludedNodelist(ArrayList<Node> excludedNodelist) {
        this.excludedNodelist = excludedNodelist;
    }

    public ArrayList<Fiber> getExcludedFiberlist() {
        return excludedFiberlist;
    }

    public void setExcludedFiberlist(ArrayList<Fiber> excludedFiberlist) {
        this.excludedFiberlist = excludedFiberlist;
    }

    public double getMax_length() {
        return max_length;
    }

    public void setMax_length(double max_length) {
        this.max_length = max_length;
    }

    public int getMax_hop() {
        return max_hop;
    }

    public void setMax_hop(int max_hop) {
        this.max_hop = max_hop;
    }
}
