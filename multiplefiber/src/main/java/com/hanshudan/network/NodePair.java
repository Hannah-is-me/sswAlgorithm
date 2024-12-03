package com.hanshudan.network;

import com.hanshudan.algorithm.RouteSearching;
import com.hanshudan.general.CommonObject;
import com.hanshudan.traffic.Action;

import java.util.ArrayList;

public class NodePair extends CommonObject {
    private Network associateLayer ; // the layer that the node pair associated with
    private Node srcNode ; // the source node
    private Node desNode; // Destination node
    private ArrayList<LinearRoute> linearroutelist; //list of three shortest linear route associated with the node pair
    private Fiber minfiberbetween;

    public NodePair(String name, Integer index, Network associateLayer, Node srcNode, Node desNode) {
        super(name, index);
        this.associateLayer = associateLayer;
        this.srcNode = srcNode;
        this.desNode = desNode;
        usedLightPath = new LightPath("", 0,  this);
        this.linearroutelist = new ArrayList<LinearRoute>();   //在起点和终点遍历所有波平面寻找可建立路由
        searchroute();
        this.minfiberbetween = null;
    }



    public void searchroute() {

        RouteSearching routesearch=  new RouteSearching();
        routesearch.kShortest(this.srcNode,  this.desNode,  this.associateLayer, 1,   this.linearroutelist, true);
    }


    private LightPath usedLightPath = null;
    private LinearRoute usedRoute = null;
    public LightPath getUsedLightPath() {
        return usedLightPath;
    }

    public void setUsedLightPath(LightPath usedLightPath) {
        this.usedLightPath = usedLightPath;
    }

    public LinearRoute getUsedRoute() {
        return usedRoute;
    }

    public void setUsedRoute(LinearRoute usedRoute) {
        this.usedRoute = usedRoute;
    }

    public Network getAssociateLayer() {
        return associateLayer;
    }

    public void setAssociateLayer(Network associateLayer) {
        this.associateLayer = associateLayer;
    }

    public Node getSrcNode() {
        return srcNode;
    }

    public void setSrcNode(Node srcNode) {
        this.srcNode = srcNode;
    }

    public Node getDesNode() {
        return desNode;
    }

    public void setDesNode(Node desNode) {
        this.desNode = desNode;
    }

    public ArrayList<LinearRoute> getLinearroutelist() {
        return linearroutelist;
    }

    public void setLinearroutelist(ArrayList<LinearRoute> linearroutelist) {
        this.linearroutelist = linearroutelist;
    }

    public Fiber getMinfiberbetween() {
        return minfiberbetween;
    }

    public void setMinfiberbetween(Fiber minfiberbetween) {
        this.minfiberbetween = minfiberbetween;
    }
}
