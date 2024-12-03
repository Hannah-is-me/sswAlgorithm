package com.hanshudan.network;

import com.hanshudan.general.CommonObject;
import com.hanshudan.general.Constant;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;





public class Node extends CommonObject {
    private Network associateNetwork;
    private List<Node> aroundNodeList;
    private HashMap<String, Node> aroundNodeMap;
    private Node parentNode;
    private Double lengthFromSrc;
    private List<Port>ingress_portsList;
    private HashMap<String, Port>ingress_portsMap;
    private List<Port>egress_portsList;
    private HashMap<String, Port>egress_portsMap;
    private List<Fiber>interLinkList;
    private HashMap<String, Fiber>interLinkMap;
    private double cost_from_src = 10000000; //used search algorithm to get he cost from the src node
    private double length_from_src = 10000000; //used search algorithm to get he cost from the src node
    private int hop_from_src = 100000000; //used in search algorithm to get the number of hops from the src node
    private  Integer status = Constant.Unvisited; //not visited yet
    private Fiber minFiber;


    public Node(String name, Integer index, Network associateNetwork) {
        super(name, index);
        this.associateNetwork = associateNetwork;
        this.aroundNodeList = new ArrayList<>();
        this.aroundNodeMap = new HashMap<>();
        this.minFiber = null;
    }

    public Fiber getMinFiber() {
        return minFiber;
    }

    public void setMinFiber(Fiber minFiber) {
        this.minFiber = minFiber;
    }

    public Network getAssociateNetwork() {
        return associateNetwork;
    }

    public void setAssociateNetwork(Network associateNetwork) {
        this.associateNetwork = associateNetwork;
    }

    public List<Node> getAroundNodeList() {
        return aroundNodeList;
    }

    public void setAroundNodeList(List<Node> aroundNodeList) {
        this.aroundNodeList = aroundNodeList;
    }

    public HashMap<String, Node> getAroundNodeMap() {
        return aroundNodeMap;
    }

    public void setAroundNodeMap(HashMap<String, Node> aroundNodeMap) {
        this.aroundNodeMap = aroundNodeMap;
    }

    public Node getParentNode() {
        return parentNode;
    }

    public void setParentNode(Node parentNode) {
        this.parentNode = parentNode;
    }

    public Double getLengthFromSrc() {
        return lengthFromSrc;
    }

    public void setLengthFromSrc(Double lengthFromSrc) {
        this.lengthFromSrc = lengthFromSrc;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Port> getIngress_portsList() {
        return ingress_portsList;
    }

    public void setIngress_portsList(List<Port> ingress_portsList) {
        this.ingress_portsList = ingress_portsList;
    }

    public HashMap<String, Port> getIngress_portsMap() {
        return ingress_portsMap;
    }

    public void setIngress_portsMap(HashMap<String, Port> ingress_portsMap) {
        this.ingress_portsMap = ingress_portsMap;
    }

    public double getCost_from_src() {
        return cost_from_src;
    }

    public void setCost_from_src(double cost_from_src) {
        this.cost_from_src = cost_from_src;
    }

    public double getLength_from_src() {
        return length_from_src;
    }

    public void setLength_from_src(double length_from_src) {
        this.length_from_src = length_from_src;
    }

    public int getHop_from_src() {
        return hop_from_src;
    }

    public void setHop_from_src(int hop_from_src) {
        this.hop_from_src = hop_from_src;
    }

    public List<Port> getEgress_portsList() {
        return egress_portsList;
    }

    public void setEgress_portsList(List<Port> egress_portsList) {
        this.egress_portsList = egress_portsList;
    }

    public HashMap<String, Port> getEgress_portsMap() {
        return egress_portsMap;
    }

    public void setEgress_portsMap(HashMap<String, Port> egress_portsMap) {
        this.egress_portsMap = egress_portsMap;
    }

    public List<Fiber> getInterLinkList() {
        return interLinkList;
    }

    public void setInterLinkList(List<Fiber> interLinkList) {
        this.interLinkList = interLinkList;
    }

    public HashMap<String, Fiber> getInterLinkMap() {
        return interLinkMap;
    }

    public void setInterLinkMap(HashMap<String, Fiber> interLinkMap) {
        this.interLinkMap = interLinkMap;
    }

    public void addAroundNode(Node node) {
        aroundNodeList.add(node);
        aroundNodeMap.put(node.getName(), node);
    }

    public void removeAroundNode(String name){
        for(int i=0;i<this.aroundNodeList.size();i++){
            if(this.aroundNodeList.get(i).getName().equals(name)){
                this.aroundNodeList.remove(this.aroundNodeList.get(i));
                break;
            }
        }

    }

    public void removeNeiNode(int index){
        for(int i=0;i<this.aroundNodeList.size();i++){
            if(this.aroundNodeList.get(i).getIndex() == index){
                this.aroundNodeList.remove(this.aroundNodeList.get(i));
                break;
            }
        }

    }

    public void removeNeiNode(Node node){
        this.aroundNodeList.remove(node);
    }

    public int getDegree(){
        return this.aroundNodeList.size();
    }

    public void addIngress_ports(Port port) {
        ingress_portsList.add(port);
        ingress_portsMap.put(port.getName(), port);
    }

    public void addEgress_ports(Port port) {
        egress_portsList.add(port);
        egress_portsMap.put(port.getName(), port);
    }

    public double giveDirections(List<Node> aroundNodeList ) {
        return  aroundNodeList.size();
    }


}
