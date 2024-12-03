package com.hanshudan.network;

import com.hanshudan.general.CommonObject;

import java.util.HashMap;
import java.util.List;

public class Port  extends CommonObject {
    private int Type;
    private List<Node> nodeList;
    private HashMap<String, Node> nodeMap;
    private List<Fiber> fiberList;
    private HashMap<String, Fiber> fiberMap;
    private List<Wavelength> wavelengthList;
    private HashMap<String, Wavelength> wavelengthMap;


    public Port(String name, Integer index) {
        super(name, index);
    }

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }

    public List<Node> getNodeList() {
        return nodeList;
    }

    public void setNodeList(List<Node> nodeList) {
        this.nodeList = nodeList;
    }

    public HashMap<String, Node> getNodeMap() {
        return nodeMap;
    }

    public void setNodeMap(HashMap<String, Node> nodeMap) {
        this.nodeMap = nodeMap;
    }

    public List<Fiber> getFiberList() {
        return fiberList;
    }

    public void setFiberList(List<Fiber> fiberList) {
        this.fiberList = fiberList;
    }

    public HashMap<String, Fiber> getFiberMap() {
        return fiberMap;
    }

    public void setFiberMap(HashMap<String, Fiber> fiberMap) {
        this.fiberMap = fiberMap;
    }

    public List<Wavelength> getWavelengthList() {
        return wavelengthList;
    }

    public void setWavelengthList(List<Wavelength> wavelengthList) {
        this.wavelengthList = wavelengthList;
    }

    public HashMap<String, Wavelength> getWavelengthMap() {
        return wavelengthMap;
    }

    public void setWavelengthMap(HashMap<String, Wavelength> wavelengthMap) {
        this.wavelengthMap = wavelengthMap;
    }
}
