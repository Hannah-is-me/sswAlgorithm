package com.hanshudan.traffic;

import com.hanshudan.general.Constant;
import com.hanshudan.network.Network;
import com.hanshudan.network.Node;
import com.hanshudan.network.NodePair;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


public class Event {
    private Network associateNetwork;
    private double startTime;
    private double keepTime;
    private double endTime;
    private double capacity;
    private Connection con = null;
    private Integer transportType = null;
    private List<Action> actionList;
    private Node src;
    private Node dest;
    private NodePair nodepair;
    private Integer index;
    private ArrayList<ArrayList<Integer>> win_TSslist;
    private ArrayList<Integer> NodelabelList;
    private  double weight;
    private int fiberNumber;
    private int g ;
    //事件本身的性质 开始和结束时间
    public Event(Integer index, Node src, Node dest, NodePair nodepair, Network associateNetwork, double startTime, double keepTime, double endTime, double capacity, int transportType, int fiberNumber, int g) {
        this.index =  index;
        this.associateNetwork = associateNetwork;
        this.startTime = startTime;
        this.keepTime = keepTime;
        this.endTime = endTime;
        this.capacity = capacity;
        this.src = src;
        this.dest =dest;
        this.nodepair = nodepair;
        actionList = associateNetwork.getActionList();
        this.transportType = transportType;
        this.fiberNumber = fiberNumber;
        this.g = g;

        this. win_TSslist =recodTWList ( this.startTime , this.keepTime , this.endTime );
        this.NodelabelList = new ArrayList<>();
        this.weight = 0;

        generateConnection();
        generateActions();

    }

    public ArrayList<Integer> getNodelabelList() {
        return NodelabelList;
    }

    public void setNodelabelList(ArrayList<Integer> nodelabelList) {
        NodelabelList = nodelabelList;
    }

    public ArrayList<ArrayList<Integer>> recodTWList (double   startTime , double  keepTime , double  endTime ){

        ArrayList <ArrayList<Integer>> TSListList = new  ArrayList <ArrayList<Integer>>();
;
        for ( int i =0; i+(int) startTime+(int)keepTime-1<=endTime ;i ++){

                int starttime =(int) startTime+i;
                int endtime = (int) startTime+i+(int)keepTime-1;
                 ArrayList<Integer> TSList = new ArrayList<>();

               for (int j=starttime ; j<=endtime ;j ++){
                        TSList.add(j);
                }
            TSListList.add( TSList);
                //     bw.write(String.valueOf(nodePair.getName()));
            }

        return TSListList;
        }





    public ArrayList<ArrayList<Integer>> getWin_TSslist() {
        return win_TSslist;
    }

    public void setWin_TSslist(ArrayList<ArrayList<Integer>> win_TSslist) {
        this.win_TSslist = win_TSslist;
    }

    public void generateConnection() {
        this.con = new Connection(this.src,this.dest, this.nodepair, this.capacity, this.startTime,this.keepTime, this.endTime, this.associateNetwork, this, this.fiberNumber, this.g);
        this.con.setIndex(this.getIndex());
    }
    public void generateActions() {
        Action a = new Action(this.con, this.startTime, this.keepTime, this.endTime);
        this.addActions(a);
    }

    private void addActions(Action a) {
        actionList.add(a);
    }

    public void release() {

    }

    public Network getAssociateNetwork() {
        return associateNetwork;
    }

    public void setAssociateNetwork(Network associateNetwork) {
        this.associateNetwork = associateNetwork;
    }

    public double getStartTime() {
        return startTime;
    }

    public void setStartTime(double startTime) {
        this.startTime = startTime;
    }

    public double getKeepTime() {
        return keepTime;
    }

    public void setKeepTime(double keepTime) {
        this.keepTime = keepTime;
    }

    public double getEndTime() {
        return endTime;
    }

    public void setEndTime(double endTime) {
        this.endTime = endTime;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public NodePair getNodePair() {
        return nodepair;
    }

    public void setNodePair(NodePair nodePair) {
        this.nodepair = nodePair;
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    public Integer getTransportType() {
        return transportType;
    }

    public void setTransportType(Integer transportType) {
        this.transportType = transportType;
    }

    public List<Action> getActionList() {
        return actionList;
    }

    public void setActionList(List<Action> actionList) {
        this.actionList = actionList;
    }

    public Node getSrc() {
        return src;
    }

    public void setSrc(Node src) {
        this.src = src;
    }

    public Node getDest() {
        return dest;
    }

    public void setDest(Node dest) {
        this.dest = dest;
    }

    public NodePair getNodepair() {
        return nodepair; }

    public void setNodepair(NodePair nodepair) {
        this.nodepair = nodepair; }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
