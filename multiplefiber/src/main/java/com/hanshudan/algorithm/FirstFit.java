package com.hanshudan.algorithm;

import com.hanshudan.network.Fiber;
import com.hanshudan.network.Network;
import com.hanshudan.network.Node;

import com.hanshudan.network.Wavelength;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


public class FirstFit {
    private Node srcNode;
    private Node destNode;
    private double capacity;
    private Network associateNetwork;
    private Wavelength wavelength;
    private List<Node> routeList;

    private List<Wavelength> wavelengthList;
    private Dijkstra d;

    public FirstFit(Node srcNode, Node destNode, double capacity, Network associateNetwork) {
        this.srcNode = srcNode;
        this.destNode = destNode;
        this.capacity = capacity;
        wavelength = null;
        this.associateNetwork = associateNetwork;
        this.routeList = new ArrayList<>();
        wavelengthList = associateNetwork.getWavelengthList();
    }



    public void findRoute() {

        this.d = new Dijkstra(srcNode, destNode, getAssociateNetwork());

        routeList = d.getPathList();

    }

    public Node getSrcNode() {
        return srcNode;
    }

    public void setSrcNode(Node srcNode) {
        this.srcNode = srcNode;
    }

    public Node getDestNode() {
        return destNode;
    }

    public void setDestNode(Node destNode) {
        this.destNode = destNode;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public Network getAssociateNetwork() {
        return associateNetwork;
    }

    public void setAssociateNetwork(Network associateNetwork) {
        this.associateNetwork = associateNetwork;
    }

    public Wavelength getWavelength() {
        return wavelength;
    }

    public void setWavelength(Wavelength wavelength) {
        this.wavelength = wavelength;
    }

    public List<Node> getRouteList() {
        return routeList;
    }

    public void setRouteList(List<Node> routeList) {
        this.routeList = routeList;
    }

    public List<Wavelength> getWavelengthList() {
        return wavelengthList;
    }

    public void setWavelengthList(List<Wavelength> wavelengthList) {
        this.wavelengthList = wavelengthList;
    }

    public Dijkstra getD() {
        return d;
    }

    public void setD(Dijkstra d) {
        this.d = d;
    }
}
