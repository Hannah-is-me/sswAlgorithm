package com.hanshudan.traffic;

import com.hanshudan.general.Constant;
import com.hanshudan.general.Timeroute;
import com.hanshudan.network.*;
//import com.sun.media.jfxmedia.events.NewFrameEvent;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import sun.net.www.content.audio.wav;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Connection {
   private  int index ;
    private int state = Constant.waiting;
    private Node src;
    private Node dest;
    private double startTime;
    private double keepTime;
    private double endTime;
    private double capacity;
    private double standcapacity;
    private double leavingcapacity;
    private Network associateNetwork;
    private List<Fiber> routeList; // 这个啥意思
    private HashMap<String,Fiber> routeHashMap;//记录每个LINK用了多少资源
    private List<Wavelength> wavList = null;
    private Map<String, Double> preUseWavMap;//connection 赋予波长后实际使用情况
    private Map<String, Double> wavMap;
    private Event associateEvent;
    private NodePair nodePair;
    private LightPath lightPath;
    private  FSRoute FSrouterecord;
    private Timeroute Timerouterecord;
    boolean AllocateIs;
    private ArrayList<Timeroute>timeWindowList  ;
    private ArrayList<ArrayList<FSRoute>> FSWindowListlist;

    private ArrayList<ArrayList<Integer>> win_TSslist;
    private ArrayList<Integer> NodelabelList;



    //事件分配路由波长信息
    public Connection(Node src, Node dest, NodePair nodepair, double capacity, double startTime, double duraTime, double endTime, Network network, Event associateEvent, int fiberNumber, int g) {
        this.index = 0;
        this.routeHashMap = new HashMap<>();
        this.routeList = new ArrayList<>();
        this.src = src;
        this.dest =dest;
        this.nodePair = nodepair;
        this.startTime = startTime;
        this.keepTime = duraTime;
        this.capacity = capacity;
        this.leavingcapacity = leavingcapacity;
        this.endTime = endTime;
        this.associateNetwork = network;
        this.associateEvent = associateEvent;
        this.wavList = new ArrayList<>();
        wavMap = new HashMap<>();
        preUseWavMap = new HashMap<>();
       this.lightPath = new LightPath("",0, nodePair);
       this.FSrouterecord = new FSRoute("",0,fiberNumber,g);
       this.Timerouterecord = new Timeroute("",0,fiberNumber,g);
       this. AllocateIs = false;
       this. NodelabelList = new ArrayList<>();
        timeWindowList = new ArrayList<>();
        FSWindowListlist = new ArrayList<>();

        this. win_TSslist =recodTWList ( this.startTime , this.keepTime , this.endTime );
        recordNode(network,this.src,this.dest,this);
        this.standcapacity = Math.ceil(this.capacity/Constant.g/Constant.wavCapacity);

    }

    public ArrayList<ArrayList<Integer>> getWin_TSslist() {
        return win_TSslist;
    }

    public void setWin_TSslist(ArrayList<ArrayList<Integer>> win_TSslist) {
        this.win_TSslist = win_TSslist;
    }

    public ArrayList<Integer> getNodelabelList() {
        return NodelabelList;
    }

    public void setNodelabelList(ArrayList<Integer> nodelabelList) {
        NodelabelList = nodelabelList;
    }

    public double getStandcapacity() {
        return standcapacity;
    }

    public void setStandcapacity(double standcapacity) {
        this.standcapacity = standcapacity;
    }

    public  void recordNode(Network net, Node src, Node des, Connection con){


        for (Node node:net.getNodeList()){

            if (src.getIndex() == node.getIndex() | des.getIndex() == node.getIndex()){

                 con.getNodelabelList().add(1);

            }

            else {

                con.getNodelabelList().add(0);

            }

        }

    }



    public ArrayList<ArrayList<Integer>> recodTWList (double   startTime , double  keepTime , double  endTime ) {


        int size = (int) (endTime - keepTime + 1);

        ArrayList<ArrayList<Integer>> TSListList = new ArrayList<ArrayList<Integer>>();
        outer: for (int kk = 0; kk < size; kk++) {

          for (int i = 0; i + (int) startTime + (int) keepTime - 1 <= endTime; i++) {
             int starttime = (int) startTime + i;
             int endtime = (int) startTime + i + (int) keepTime - 1;

             ArrayList<Integer> TSList = new ArrayList<>();
             inner: for (int index = 1; index <= Constant.timetotalnum; index++) {

                     if (index == starttime) {
                        for (int j = starttime; j <= endtime; j++) {
                        TSList.add(1);
                    }
                      for (int ii = endtime + 1; ii <= Constant.timetotalnum; ii++) {
                        TSList.add(0);
                    }

               //     TSListList.add(TSList);
                    if (TSList.get(TSList.size() - 1) == 1) {
                        break outer;
                    }

                    kk++;
                    break inner;


                } else {

                    TSList.add(0);

                }


                //     bw.write(String.valueOf(nodePair.getName()));
            }
            TSListList.add(TSList);
        }
    }

        return TSListList;
    }



    public ArrayList<Timeroute> getTimeWindowList() {
        return timeWindowList;
    }

    public void setTimeWindowList(ArrayList<Timeroute> timeWindowList) {
        this.timeWindowList = timeWindowList;
    }

    public ArrayList<ArrayList<FSRoute>> getFSWindowListlist() {
        return FSWindowListlist;
    }

    public void setFSWindowListlist(ArrayList<ArrayList<FSRoute>> FSWindowListlist) {
        this.FSWindowListlist = FSWindowListlist;
    }

    public boolean isAllocateIs() {
        return AllocateIs;
    }

    public void setAllocateIs(boolean allocateIs) {
        AllocateIs = allocateIs;
    }

    public Timeroute getTimerouterecord() {
        return Timerouterecord;
    }

    public void setTimerouterecord(Timeroute timerouterecord) {
        Timerouterecord = timerouterecord;
    }

    public FSRoute getFSrouterecord() {
        return FSrouterecord;
    }

    public void setFSrouterecord(FSRoute FSrouterecord) {
        this.FSrouterecord = FSrouterecord;
    }

    public LightPath getLightPath() {
        return lightPath;
    }

    public void setLightPath(LightPath lightPath) {
        this.lightPath = lightPath;
    }

/*    public void release() {
//        System.out.println("---------release业务"+this.getSrc()+"-"+this.getDest()+getState()+"-----------------");

//       System.out.println("---------release业务"+this.getSrc()+"-"+this.getDest()+getState()+"-----------------");
        for (Fiber fiber :this.getRouteList()) {
            for (int i = this.getFSrouterecord().getFirstSlotNum(); i <=  this.getFSrouterecord().getLastSlotNum(); i++) {//这边是当前节点对所有路由和波长全部释放
//                System.out.println("释放"+fiber.getName());
                fiber.release(fiber.getWavelengthList().get(i));
            }

        }*/
//        this.getLightPath().getPhysicPath().getFiberlist().clear();
//        this.getLightPath().getPhysicPath().getNodelist().clear();
//    }

    public void searchroute()

    {

    }


    public void releasePreWavMap() {
        preUseWavMap.clear();
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public NodePair getNodePair() {
        return nodePair;
    }

    public void setNodePair(NodePair nodePair) {
        this.nodePair = nodePair;
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

    public Network getAssociateNetwork() {
        return associateNetwork;
    }

    public void setAssociateNetwork(Network associateNetwork) {
        this.associateNetwork = associateNetwork;
    }

    public List<Fiber> getRouteList() {
        return routeList;
    }

    public void setRouteList(List<Fiber> routeList) {
        this.routeList = routeList;
    }

    public HashMap<String, Fiber> getRouteHashMap() {
        return routeHashMap;
    }

    public void setRouteHashMap(HashMap<String, Fiber> routeHashMap) {
        this.routeHashMap = routeHashMap;
    }

    public List<Wavelength> getWavList() {
        return wavList;
    }

    public void setWavList(List<Wavelength> wavList) {
        this.wavList = wavList;
    }

    public Map<String, Double> getPreUseWavMap() {
        return preUseWavMap;
    }

    public void setPreUseWavMap(Map<String, Double> preUseWavMap) {
        this.preUseWavMap = preUseWavMap;
    }

    public Map<String, Double> getWavMap() {
        return wavMap;
    }

    public void setWavMap(Map<String, Double> wavMap) {
        this.wavMap = wavMap;
    }

    public Event getAssociateEvent() {
        return associateEvent;
    }

    public void setAssociateEvent(Event associateEvent) {
        this.associateEvent = associateEvent;
    }

    public double getLeavingcapacity() {
        return leavingcapacity;
    }

    public void setLeavingcapacity(double leavingcapacity) {
        this.leavingcapacity = leavingcapacity;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
