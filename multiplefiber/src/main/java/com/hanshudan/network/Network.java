package com.hanshudan.network;

import com.hanshudan.algorithm.Dijkstra;
import com.hanshudan.algorithm.FixRoute;
import com.hanshudan.algorithm.RouteSearching;
import com.hanshudan.general.CommonObject;
import com.hanshudan.general.Constant;
import com.hanshudan.general.Time;
import com.hanshudan.traffic.Action;
import com.hanshudan.traffic.Connection;

import java.io.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;



public class Network extends CommonObject{
    private List<Node> nodeList;
    private HashMap<String, Node> nodeMap;
    private List<Fiber> fiberList;
    private List<Link> linkList;
    private HashMap<String, Fiber> fiberMap;
    private HashMap<String, Link> linkMap;
    private List<Wavelength> wavelengthList;
    private HashMap<String, Wavelength> wavelengthMap;
    private List<Time> timeList;
    private HashMap<String, Time> timeHashMap;
    private List<String>  SwitchGranularityList;
    private HashMap<String, Fiber> SwitchGranularityMap;
    private List<Action> actionList;
    private List<Connection> connectionList;
    private List<Node> listFinal;
    private HashMap<Node, Integer> Nodeaccountmap;
    private int fiber_index;
    private HashMap<String, NodePair> nodepairMap ;
    private ArrayList<NodePair>nodePairsList;
    private int fiberNum;
    private int g;
    private int fiberNum_g;


    public Network(String name, Integer index, int fiberNum, int g) {
        super(name, index);
        nodeList = new ArrayList<>();
        nodeMap = new HashMap<>();
        fiberList = new ArrayList<>();
        fiberMap = new HashMap<>();
        linkList = new ArrayList<>();
        linkMap = new HashMap<>();
        wavelengthList = new ArrayList<>();
        wavelengthMap = new HashMap<>();
        timeList = new ArrayList<>();
        timeHashMap = new HashMap<>();
        actionList = new ArrayList<>();
        connectionList = new ArrayList<>();
        listFinal = new ArrayList<>();
        Nodeaccountmap= new HashMap<Node, Integer>();
        fiber_index = 0;
        generateWav();
        generateTim();
        this.nodepairMap = new HashMap<>();
        this.nodePairsList = new ArrayList<>();
        this.fiberNum = fiberNum;
        this.g = g;
        this.fiberNum_g = this.fiberNum/this.g ;
    }


     // the fibers group number


    public void calculatepowerconsumption( Fiber fiber, Time time){
        int flag = 0 ;
        int PW = 0;
        for (Wavelength wav: fiber.getTimeList().get(time.getIndex()).getWavelengthList()){

            if (wav.getCapacity()==0){

                flag = flag+1;


            }



        }


    }

    public void calculatecost(){}


    public void generateWav() {
        for (int i = 0; i < Constant.wavNum; i++) {
            Wavelength wav = new Wavelength("λ" + i, i, Constant.wavCapacity);
            addWav(wav);
        }
    }

    public void generateTim() {
        for (int i = 0; i < Constant.timetotalnum; i++) {
            Time tim = new Time("T" + i, i, Constant.timeslotcapacity);
            addTim(tim);
        }
    }

/*    public void generateG(){
        for (int i =0; i<SwitchGroup)
    }

    private List<String>  SwitchGranularityList;
    private HashMap<String, Fiber> SwitchGranularityMap;*/

    public void readTopology1(String filename) {
        System.out.println("Node is generating...");
        String[] data = new String[10];
        File file = new File(filename);
        BufferedReader bufRdr = null;
        try {
            bufRdr = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String line = null;
        int col = 0;
        //*read title line*//*
        try {
            line = bufRdr.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*read each line of file*/
        try {
            boolean Fiber = false;
            while ((line = bufRdr.readLine()) != null)
            {
                StringTokenizer st = new StringTokenizer(line, ",");//去除逗号
                while (st.hasMoreTokens()) {
                    data[col] = st.nextToken();
                    col++;
                }

                col = 0;
                String name = data[0];
                if (name.equals("Fiber")) { //比较两个字符串内容是否相等
                    Fiber = true;
                }

                if (!Fiber) {//node add
                    if (!name.equals("Node")) {

                    }
                }
                else
                {//Fiber add
                    if (!name.equals("Fiber")) {
                        Node nodeA = this.nodeMap.get(data[1]);
                        Node nodeB = this.nodeMap.get(data[2]);
                        Double length = Double.parseDouble(data[3]);
//                        double cost = Double.parseDouble(data[4]);
//                        double Type = Double.parseDouble(data[5]);
                            name = nodeA.getName() + "-" + nodeB.getName() ;
                            Link newLink1 = new Link(name, 0,this, nodeA, nodeB, length);
                            name = nodeB.getName() + "-" + nodeA.getName() ;
                            Link newLink2 = new Link (name, 0,this, nodeB, nodeA, length);
                            this.addLink( newLink1);
                            this.addLink(newLink2);

                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            bufRdr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void readTopology(String filename) {
        System.out.println("Node is generating...");
        String[] data = new String[10];
        File file = new File(filename);
        BufferedReader bufRdr = null;
        try {
            bufRdr = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String line = null;
        int col = 0;
        //*read title line*//*
        try {
            line = bufRdr.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*read each line of file*/
        try {
            boolean Fiber = false;
            while ((line = bufRdr.readLine()) != null)
            {
                StringTokenizer st = new StringTokenizer(line, ",");//去除逗号
                while (st.hasMoreTokens()) {
                    data[col] = st.nextToken();
                    col++;
                }

                col = 0;
                String name = data[0];
                if (name.equals("Fiber")) { //比较两个字符串内容是否相等
                    Fiber = true;
                }

                if (!Fiber) {//node add
                    if (!name.equals("Node")) {
                        int index = this.nodeMap.size();
//                        double type = Double.parseDouble(data[1]);
                        Node newnode = new Node(name, index, this);
                        this.addNode(newnode);
                    }
                }
                else
                {//Fiber add
                    if (!name.equals("Fiber")) {
                        Node nodeA = this.nodeMap.get(data[1]);
                        Node nodeB = this.nodeMap.get(data[2]);
                        double length = Double.parseDouble(data[3]);
//                        double cost = Double.parseDouble(data[4]);
//                        double Type = Double.parseDouble(data[5]);
                        int index = this.fiberMap.size();

                        for (int i = 0; i <  fiberNum_g; i++) {
                            name = nodeA.getName() + "-" + nodeB.getName() + "-" + i;
                            Fiber newFiber1 = new Fiber(name, i, this, nodeA, nodeB, length);
                            name = nodeB.getName() + "-" + nodeA.getName() + "-" + i;
                            Fiber newFiber2 = new Fiber(name, i, this, nodeB, nodeA, length);
                            this.addFiber(newFiber1);
                            this.addFiber(newFiber2);
                        }

                        nodeA.addAroundNode(nodeB);
                        nodeB.addAroundNode(nodeA);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            bufRdr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void addWav(Wavelength wav) {
        wavelengthList.add(wav);
        wavelengthMap.put(wav.getName(), wav);
    }

    public void addTim(Time tim) {
        timeList.add(tim);
        timeHashMap.put(tim.getName(), tim);
    }

    public void addFiber(Fiber fiber) {
        fiberList.add(fiber);
        fiberMap.put(fiber.getName(), fiber);
    }

    public void addLink(Link link) {
        linkList.add(link);
        linkMap.put(link.getName(), link);
    }

    public void addNode(Node node) {
        nodeList.add(node);
        nodeMap.put(node.getName(), node);

    }

    public void removeFiber(String fibername) {
        this.fiberMap.get(fibername).setAssociateNetwork(null);
        // 节点的相邻节点需要改变
        Fiber fiber = this.fiberMap.get(fibername);
        Node A = fiber.getSrcNode();
        Node B = fiber.getSrcNode();
        A.removeNeiNode(B);
        B.removeNeiNode(A);
        // 移除link
        this.fiberMap.remove(fibername);
    }

    public void removeFiber(Fiber fiber) {
        fiber.setAssociateNetwork(null);
        this.fiberMap.remove(fiber.getName());
        fiber.getSrcNode().getAroundNodeList().remove(fiber.getDestNode());
 //       fiber.getSrcNode().getAroundNodeMap().remove(fiber.getDestNode());
        fiber.getDestNode().getAroundNodeList().remove(fiber.getSrcNode());
 //       fiber.getDestNode().getAroundNodeMap().remove(fiber.getSrcNode());
    }

    public void addConnection(Connection connection) {
        connectionList.add(connection);
    }

    public double giveIngressDirectionType(List<Node> aroundNodeList ) {
        return  aroundNodeList.size();
    }

    public double giveEgressDirectionType(List<Node> aroundNodeList ) {
        return  aroundNodeList.size();
    }


/*    public void accountNode(Connection connection) {

        HashMap<Node, Integer> map = new HashMap<Node, Integer>();
        for (Node temp : routeNode) {
            Integer count = map.get(temp);
            map.put(temp, (count == null) ? 1 : count + 1);
        }
    }*/

    public List<Fiber> findFiberList(Node currentNode, Node node) {
        List<Fiber> fibers = new ArrayList<>();
        for (int i = 0; i <fiberNum_g; i++) {    // the fiber group number
            String name = currentNode.getName() + "-" + node.getName() + "-" + i;
         if (fiberMap.containsKey(name )){
            fibers.add(fiberMap.get(name));}
        }
        return fibers;
    }

    public HashMap<Integer, Fiber> findFiberMap(Node currentNode, Node node) {
        HashMap<Integer, Fiber> fibersMap = new HashMap<Integer, Fiber>();
        for (int i = 0; i <fiberNum_g; i++) {    // the fiber group number
            String name = currentNode.getName() + "-" + node.getName() + "-" + i;
            if (fiberMap.containsKey(name )){
                fibersMap.put(i, fiberMap.get(name));}
        }
        return  fibersMap;
    }



    public void rankActions() {
        Collections.sort(this.actionList, (o1, o2) -> (int) (o1.getStarttime() - o2.getStarttime()));
    }
/*
    public void doActionList() {

            for (int i =0; i < actionList.size(); i++) {
                Action action = actionList.get(i);
                doAction(action);
            }


    }*/



/*    public void doAction(Action action) {
        Connection connection = action.getCon();
        addConnection(connection);

            List<Node> routeNode = findRouteNode(connection);

            List<Fiber> fibers = findFiberList(routeNode.get(0), routeNode.get( 1));

              for (int  fiber_index = 0; fiber_index <fibers.size(); fiber_index ++)
              {
                  assignWavInRoute(connection, routeNode, fiber_index);
                  if (connection.getLeavingcapacity() == 0) {
                      break;
                  }
              }
            if (connection.getLeavingcapacity() != 0) {
                connection.setState(Constant.blocking);
                Constant.BlockNum++;
                connection.release();
            }


           *//* HashMap<Node, String> ArrivalNodeErlangMap =*//*


    }*/


/*    public void assignWavInRoute(Connection connection, List<Node> routeNode, int fiber_index) {
        if (connection.getAssociateEvent().getTransportType().equals(Constant.InSwitch)) {
            doInSwitch(connection, routeNode);
        } else if (connection.getAssociateEvent().getTransportType().equals(Constant.FrJSwitch)) {
            doFrJSwitch(connection, routeNode);
        } else {
          //  doJSwitch(connection, routeNode);
            doJSwitchwithoutband(connection, routeNode,fiber_index);
        }


    }*/

   private void  doInSwitch(Connection connection, List<Node> routeNode) {
/*        double capacity = connection.getCapacity();


        for (int i = 0; i < routeNode.size() - 1; i++) {

            double useCapacity = Math.ceil( Math.ceil(capacity/ 12.5 )/ Capacity_g)* 12.5;//基础Frequenent slot 大小为12.5
            List<Fiber> fibers = findFiberList(routeNode.get(i), routeNode.get(i + 1));

        for ( Fiber fiber : fibers)//判断每个波，波长连续性

        {

            boolean flag = false;
                if (i == 0) {

                    for (Wavelength wavelength : wavelengthList) {

                        if (fiber.canUseCapacity(wavelength, useCapacity)) { // 这边条件包含else if 条件
                            addPreUseWavMap(wavelength, useCapacity, connection);

                        }
                        else if (fiber.canUseCapacity(wavelength, 12.5) )// 这边逻辑是不是有问题
                        {
                            double realUseCapacity = Math.ceil(Math.ceil(fiber.getWavelengthMap().get(wavelength.getName()).getCapacity() / 12.5)) * 12.5;
                            addPreUseWavMap(wavelength, realUseCapacity, connection);

                        }
                        else
                        {
                            flag = true;
                            break;
                        } // 一个一个fiber中一个一个频段去判断能否保证是相邻，且是连续的，我是需要频谱连续性哇。
                    }
                }

                else

                { // 当i>1的时候 为什么有情况不同了
                    for (Wavelength wavelength : wavelengthList) {
                        // 节点2的情况下如何计算
                        double realUseCapacity = connection.getPreUseWavMap().get(wavelength.getName());
                        if (fiber.canUseCapacity(wavelength, realUseCapacity)) {
                            addPreUseWavMap(wavelength, realUseCapacity, connection);
                        }
                        else
                        {// 当下波长不能满足当前的span
                            flag = true;
                            break;
                        }
                    }
                }

                if (flag)
                {// 这里跳出路由span的遍历，因为当下的波长已经不能满足全span了
                    break;
                }

            }

            if (flag) { // 这边什么意思

                connection.getPreUseWavMap().clear();//清楚分配的波长及其容量
            }
            else
            {
                    //                集中处理链路波长的使用
//                    System.out.println("---------use业务"+connection.getSrc()+"-"+connection.getDest()+"-----------------");
                    for (Wavelength wavelength : wavelengthList) {
//                        System.out.println("使用"+fiber.getName());
                        fiber.useCapacity(wavelength, connection.getPreUseWavMap().get(wavelength.getName()));
                        addFiberAndWav(fiber, wavelength, connection.getPreUseWavMap().get(wavelength.getName()), connection);
                        if (capacity > connection.getPreUseWavMap().get(wavelength.getName())*Capacity_g)
                        {
                            capacity -= connection.getPreUseWavMap().get(wavelength.getName())*Capacity_g;
                        }
                        else
                        {
                            capacity = 0;
                        }

                    }
                }
            }

            if (capacity == 0 && !flag) {
                break;
            }
        }

        if (capacity != 0) {
            connection.setState(Constant.blocking);
            Constant.BlockNum++;
            connection.release();
        }*/

    }


    private void  doFrJSwitch(Connection connection, List<Node> routeNode) {


    }


/*    private void  doJSwitch(Connection connection, List<Node> routeNode) {
        double capacity = (connection.getCapacity()/ Capacity_g) ;
     //   for (int fiber_index = 0; fiber_index < fiberNum_g; fiber_index++) {

        for (Wavelength wavelength : wavelengthList)//判断每个波，波长连续性
        {
            for (fiber_index = 0;fiber_index < fiberNum_g; fiber_index++)
         { // capacity_g is spatial elements, fibers number is F, fibers group is F/capacity_g,
            double useCapacity = (int) ((capacity/ Constant. wavCapacity + 1) * Constant. wavCapacity);//基础Frequenent slot 大小为12.5
            boolean flag = false;


            for (int i = 0; i < routeNode.size() - 1; i++) {

                List<Fiber> fibers = findFiberList(routeNode.get(i), routeNode.get(i + 1));


                if (i == 0) {

                        if (fibers.get(fiber_index).canUseCapacity(wavelength, useCapacity)) { // 这边条件包含else if 条件
                            addPreUseWavMap(wavelength, useCapacity, connection);

                        } else if (fibers.get(fiber_index).canUseCapacity(wavelength, Constant.wavCapacity))// 这边逻辑是不是有问题
                        {
                            double realUseCapacity = (int) (fibers.get(fiber_index).getWavelengthMap().get(wavelength.getName()).getCapacity() / 12.5) * 12.5;
                            addPreUseWavMap(wavelength, realUseCapacity, connection);
                        } else {
                            flag = true;
                            break;
                        } // 一个一个fiber中一个一个频段去判断能否保证是相邻，且是连续的，我是需要频谱连续性哇。

                    } else{ // 当i>1的时候 为什么有情况不同了

                        // 节点2的情况下如何计算
                        double realUseCapacity = connection.getPreUseWavMap().get(wavelength.getName());
                        if (fibers.get(fiber_index).canUseCapacity(wavelength, realUseCapacity)) {
                            addPreUseWavMap(wavelength, realUseCapacity, connection);
                        } else {// 当下波长不能满足当前的span
                            flag = true;
                            break;
                        }

                    }

                    if (flag) {// 这里跳出路由span的遍历，因为当下的波长已经不能满足全span了
                        break;
                    }

                }

                if (flag) { // 这边什么意思

                    connection.getPreUseWavMap().clear();//清楚分配的波长及其容量
                }
                else
                {
                    for (int i = 0; i < routeNode.size() - 1; i++)
                    {
                        List<Fiber> fibers = findFiberList(routeNode.get(i), routeNode.get(i + 1));
                        //                集中处理链路波长的使用
//                    System.out.println("---------use业务"+connection.getSrc()+"-"+connection.getDest()+"-----------------");

//                        System.out.println("使用"+fiber.getName());
                        fibers.get(fiber_index).useCapacity(wavelength, connection.getPreUseWavMap().get(wavelength.getName()));
                        addFiberAndWav(fibers.get(fiber_index), wavelength, connection.getPreUseWavMap().get(wavelength.getName()), connection);
                        if (capacity > connection.getPreUseWavMap().get(wavelength.getName()) ) {
                            capacity -= connection.getPreUseWavMap().get(wavelength.getName());
                        }
                        else
                        {
                            capacity = 0;
                        }

                    }
                }

                if (capacity == 0 && !flag) {
                    break;
                }

            }

            if (capacity == 0) {
                break;
            }


        }
        if (capacity != 0) {
            connection.setState(Constant.blocking);
            Constant.BlockNum++;
            connection.release();
        }

    }*/

   /* private void  doJSwitchwithoutband(Connection connection, List<Node> routeNode, int fiber_index) {
        double capacity = connection.getCapacity()/ Capacity_g ;//Capacity_g=1
        double useCapacity =  Math.ceil (capacity  / Constant.wavCapacity) *Constant.wavCapacity;//
        double flagCapacity = useCapacity;
        //   for (int fiber_index = 0; fiber_index < fiberNum_g; fiber_index++) {
        //for (fiber_index = 0;fiber_index < fiberNum_g; fiber_index++) {
          int  waveadaptIndex =0;

        // capacity_g is spatial elements, fibers number is F, fibers group is F/capacity_g,

            for (int waveIndex = waveadaptIndex ; waveIndex< wavelengthList.size(); waveIndex++)//判断每个波，波长连续性
            {
                    Wavelength wavelength = wavelengthList.get(waveIndex);
                        //这边放置位置有待考虑

                        boolean flag = false;
                        for (int i = 0; i < routeNode.size() - 1; i++) {
                            List<Fiber> fibers = findFiberList(routeNode.get(i), routeNode.get(i + 1));

                            if (i == 0) {

                                if (fibers.get(fiber_index).canUseCapacity(wavelength, useCapacity)) { // 这边条件包含else if 条件
                                    addPreUseWavMap(fibers.get(fiber_index).getWavelengthMap().get(wavelength.getName()), useCapacity, connection);
                                //    addUseWavFiberMap(fibers.get(fiber_index).getWavelengthMap().get(wavelength.getName()), useCapacity,fibers.get(fiber_index));
                                }
                                else if (fibers.get(fiber_index).canUseCapacity(wavelength, Constant.wavCapacity))// 这边逻辑是不是有问题
                                {
                                    double realUseCapacity =  fibers.get(fiber_index).getWavelengthMap().get(wavelength.getName()).getCapacity();
                                    addPreUseWavMap(fibers.get(fiber_index).getWavelengthMap().get(wavelength.getName()), realUseCapacity, connection);
                              //      addUseWavFiberMap(fibers.get(fiber_index).getWavelengthMap().get(wavelength.getName()), useCapacity,fibers.get(fiber_index));
                                }
                                else
                                {
                                   flag = true;
                                    break;
                                } // 如果遍历完还没有可用波长则跳出循环
                            }
                            else
                            { // 当i>1的时候 为什么有情况不同了get(fibers.get(fiber_index).getWavelengthMap().get(wavelength.getName()))
                                // 节点2的情况下如何计算
                                double realUseCapacity = connection.getPreUseWavMap().get(wavelength.getName());
                                if (fibers.get(fiber_index).canUseCapacity(wavelength, realUseCapacity)) {
                                    addPreUseWavMap(fibers.get(fiber_index).getWavelengthList().get(wavelength.getIndex()), realUseCapacity, connection);
                                //    addUseWavFiberMap(fibers.get(fiber_index).getWavelengthMap().get(wavelength.getName()), useCapacity,fibers.get(fiber_index));
                                }
                                else
                                {// 当下波长不能满足当前的span
                                    flag = true;
                                    break;
                                }

                            }

                            if (flag) {// 这里跳出路由span的遍历，因为当下的波长已经不能满足全span了
                                break;
                            }

                        }// nodelist

                        if (flag) { // 遍历当先容器没有可用波长，即要判断为阻塞

                            connection.getPreUseWavMap().clear();//这边要清楚到底是connection还是fiber的容量
                        //    connection.getPreUseWavFiberMap().clear();
                        }
                        else
                        {
                            for (int i = 0; i < routeNode.size() - 1; i++)
                            {
                                List<Fiber> fibers = findFiberList(routeNode.get(i), routeNode.get(i + 1));
                                if (i == 0){

                                    //                集中处理链路波长的使用
//                            System.out.println("---------use业务"+connection.getSrc()+"-"+connection.getDest()+"-----------------");
//                            System.out.println("使用"+fiber.getName());
                                    fibers.get(fiber_index).useCapacity(wavelength, connection.getPreUseWavMap().get(wavelength.getName()));
                                    addFiberAndWav(fibers.get(fiber_index), wavelength, connection.getPreUseWavMap().get(wavelength.getName()), connection);

                                    if (useCapacity > connection.getPreUseWavMap().get(wavelength.getName()))
                                    {
                                        useCapacity -= connection.getPreUseWavMap().get(wavelength.getName());
                                    }else{
                                        useCapacity = 0;
                                    }


                                }
                              else
                               {
                                    //                集中处理链路波长的使用
//                            System.out.println("---------use业务"+connection.getSrc()+"-"+connection.getDest()+"-----------------");
//                            System.out.println("使用"+fiber.getName());
                                   fibers.get(fiber_index).useCapacity(wavelength, connection.getPreUseWavMap().get(wavelength.getName()));
                                   addFiberAndWav(fibers.get(fiber_index), wavelength, connection.getPreUseWavMap().get(wavelength.getName()), connection);
                     // 每条链路上波长容量没有减去
                               }

                            } //nodelist
                        }



                if (!FScontinueOrnot( connection ))

                {   connection.getPreUseWavMap().clear() ;
                    useCapacity =  flagCapacity;
                    waveadaptIndex = 0;

                }

                        if (useCapacity == 0 && !flag)
                        {
                            break;
                        }

               if (!FibercontinueOrnot( connection ))
               {   connection.getPreUseWavMap().clear() ;
                   useCapacity =  flagCapacity;
                   waveadaptIndex =    1 ;
                }

            }//fiberindexlist

             connection.setLeavingcapacity(useCapacity)  ;


    }*/

    private  boolean FScontinueOrnot( Connection connection )
    {

       int number =  connection.getWavList().size();
       ArrayList<Integer> FSindexs = new ArrayList<>();
       for(int i=0; i<number; i++)
       {
           Wavelength wwave =  connection.getWavList().get(i);
            FSindexs.add(wwave.getIndex());
        }
       if (number!=0) {
           int maxElement = Collections.max(FSindexs);
           int minElement = Collections.min(FSindexs);
           if ((maxElement - minElement) <= number - 1) {
               return true;
           } else {
               return false;
           }
       }
       else{return false;}
    }

    private  boolean  FibercontinueOrnot( Connection connection )
    {
        boolean flag = true;
        ArrayList<Integer> indexList = new ArrayList<>();
        Iterator<Fiber> iterator = connection.getRouteList().iterator();
        while (iterator.hasNext()){
                       indexList.add( iterator.next().getIndex());
        }
        for (int i=0; i<indexList.size()-1;i++){

            for(int k =0; k<indexList.size()-1;k++){
                if(!( indexList.get(i)==indexList.get(k))){
                    flag = false;
                }
                break;
            }

        }
        return flag;
    }



    private void doFDSwitch(Connection connection, List<Node> routeNode) {
//        double capacity = connection.getCapacity();
//        Map<String, Double> wavMap = new HashMap<>();
//
//        if (Constant.SLC) {
//
//                for (int i = 0; i < routeNode.size()-1; i++) {
//                    List<Fiber> fibers = findFiberList(routeNode.get(i), routeNode.get(i + 1));
//                    for (Fiber fiber : fibers) {
//
//                        if (wavMap.isEmpty()) {
//                            for (Wavelength wav : wavelengthList) {
//                                double canUseCapacity = fiber.getWavelengthMap().get(wav.getName()).getCapacity();
//                                if (canUseCapacity < capacity+25) {
//                                    double useCapacity = (int) (canUseCapacity / 12.5) * 12.5;
//                                    fiber.useCapacity(wav, useCapacity);
//                                    capacity -= useCapacity - 25;
//                                    addFiberAndWav(fiber, wav, useCapacity, connection);
//                                    wavMap.put(wav.getName(), useCapacity);
//                                }else {
//                                    double useCapacity = (int) ((capacity + 25) / 12.5) * 12.5;
//                                    fiber.useCapacity(wav, useCapacity);
//                                    capacity = 0;
//                                    addFiberAndWav(fiber, wav, useCapacity, connection);
//                                    wavMap.put(wav.getName(), useCapacity);
//                                }
//                            }
//                        }else {
//                            for (Map.Entry<String, Double> entry : wavMap.entrySet()) {
//                                String key = entry.getKey();
//                                Wavelength wavelength = wavelengthMap.get(key);
//                                if (fiber.canUseCapacity(wavelength, entry.getValue())) {
//                                    fiber.useCapacity(wavelength, entry.getValue());
//                                    addFiberAndWav(fiber, wavelength, entry.getValue(), connection);
//                                }
//                            }
//
//
//                        }
//
////                        保证在一条光纤上
//                        if (capacity != 0) {
//                            connection.release();
//                        }
//                    }
//                }
//        }else {
//            ArrayList<Fiber> useFibers = new ArrayList<>();
//            while (true) {
//                boolean flag = false;
//                for (Wavelength wav : wavelengthList) {
//                    for (int i = 0; i < routeNode.size()-1; i++) {
//                        List<Fiber> fibers = findFiberList(routeNode.get(i), routeNode.get(i + 1));
//                        if (useFibers.size() == 0) {
//                            for (Fiber fiber : fibers) {
//
//                                double canUseCapacity = fiber.getWavelengthMap().get(wav.getName()).getCapacity();
//                                if (canUseCapacity < capacity + 25) {
//                                    double useCapacity = (int) (canUseCapacity / 12.5) * 12.5;
//                                    fiber.useCapacity(wav, useCapacity);
//                                    capacity -= useCapacity - 25;
//                                    addFiberAndWav(fiber, wav, useCapacity, connection);
//                                    useFibers.add(fiber);
//                                } else {
//                                    double useCapacity = (int) ((capacity + 25) / 12.5) * 12.5;
//                                    fiber.useCapacity(wav, useCapacity);
//                                    capacity = 0;
//                                    addFiberAndWav(fiber, wav, useCapacity, connection);
//                                    useFibers.add(fiber);
//                                }
//
//                            }
//                        } else {
//                            Integer index = fibers.get(0).getIndex();
//                            for (Fiber fiber : fibers) {
//                                if (fiber.getIndex() == index) {
//
//                                    double canUseCapacity = fiber.getWavelengthMap().get(wav.getName()).getCapacity();
//                                    if (canUseCapacity < capacity + 25) {
//                                        double useCapacity = (int) (canUseCapacity / 12.5) * 12.5;
//                                        fiber.useCapacity(wav, useCapacity);
//                                        capacity -= useCapacity - 25;
//                                        addFiberAndWav(fiber, wav, useCapacity, connection);
//                                        useFibers.add(fiber);
//                                    } else {
//                                        double useCapacity = (int) ((capacity + 25) / 12.5) * 12.5;
//                                        fiber.useCapacity(wav, useCapacity);
//                                        capacity = 0;
//                                        addFiberAndWav(fiber, wav, useCapacity, connection);
//                                        useFibers.add(fiber);
//                                    }
//
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//
//        }
//
//        if (capacity != 0) {
//            connection.setState(Constant.blocking);
//            connection.release();
//        }

    }



    private void addPreUseWavMap(Wavelength wavelength, double useCapacity, Connection connection) {
        connection.getPreUseWavMap().put(wavelength.getName(), useCapacity);// 记录当前connection使用的波长index，及其使用波长容量。
    }

    public void addFiberAndWav(Fiber fiber, Wavelength wav, double useCapacity, Connection connection) {
        if (!connection.getRouteHashMap().containsKey(fiber.getName())){//这边name应该改成index吧
            connection.getRouteList().add(fiber);//映射已经使用的波长还是未使用的波长呢
            connection.getRouteHashMap().put(fiber.getName(), fiber);
        }

            if (!connection.getWavList().contains(wav)) {
                connection.getWavList().add(wav);
                connection.getWavMap().put(wav.getName(), useCapacity);
            }

    }

    private List<Node> findRouteNode(Connection connection) {
        Dijkstra dijkstra = new Dijkstra(connection.getSrc(), connection.getDest(), this);
        List<Node> pathList = dijkstra.getPathList();
        NodeRouteAccount(pathList);
        return pathList;
    }



    private HashMap<Node, Integer> NodeRouteAccount(List<Node> pathList){
        this.listFinal.addAll(pathList);
        for (Node temp : listFinal) {
            Integer count = Nodeaccountmap.get(temp);
            Nodeaccountmap.put(temp, (count == null) ? 1 : count + 1);
        }
        return Nodeaccountmap;
    }

    /**
     * Create node pair based on the existing node list
     */
    public void generateNodePairs() {
        HashMap<String, Node> map = this.getNodeMap();
        HashMap<String, Node> map2 =  this.getNodeMap();
        Iterator<String> iter1 = map.keySet().iterator();
        while (iter1.hasNext()) {
            Node nodeA = (Node) map.get(iter1.next());
            Iterator<String> iter2 = map.keySet().iterator();
            while (iter2.hasNext()) {//这边限制了单向传播了吧
                Node nodeB = (Node) map2.get(iter2.next());
                if (nodeA.getIndex() ==  nodeB.getIndex()) {
                    continue;
                }
                else{
                    String name = nodeA.getName() + "-" + nodeB.getName();
                    int index = this.getNodeMap().size();
                    NodePair newNodePair = new NodePair(name, index, this, nodeA, nodeB);
                    this.addNodepair(newNodePair);
                }
            }
        }
    }

    /**
     * add node pair to the layer
     */
    public void addNodepair(NodePair nodepair) {
        nodepairMap.put(nodepair.getName(), nodepair);
        nodePairsList.add(nodepair);
        nodepair.setAssociateLayer(this);
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

    public List<String> getSwitchGranularityList() {
        return SwitchGranularityList;
    }

    public void setSwitchGranularityList(List<String> switchGranularityList) {
        SwitchGranularityList = switchGranularityList;
    }

    public HashMap<String, Fiber> getSwitchGranularityMap() {
        return SwitchGranularityMap;
    }

    public void setSwitchGranularityMap(HashMap<String, Fiber> switchGranularityMap) {
        SwitchGranularityMap = switchGranularityMap;
    }

    public List<Action> getActionList() {
        return actionList;
    }

    public void setActionList(List<Action> actionList) {
        this.actionList = actionList;
    }

    public List<Connection> getConnectionList() {
        return connectionList;
    }

    public void setConnectionList(List<Connection> connectionList) {
        this.connectionList = connectionList;
    }

    public List<Node> getListFinal() {
        return listFinal;
    }

    public void setListFinal(List<Node> listFinal) {
        this.listFinal = listFinal;
    }

    public HashMap<Node, Integer> getNodeaccountmap() {
        return Nodeaccountmap;
    }

    public void setNodeaccountmap(HashMap<Node, Integer> nodeaccountmap) {
        Nodeaccountmap = nodeaccountmap;
    }

    public int getFiber_index() {
        return fiber_index;
    }

    public void setFiber_index(int fiber_index) {
        this.fiber_index = fiber_index;
    }



    public int getFiberNum_g() {
        return fiberNum_g;
    }

    public void setFiberNum_g(int fiberNum_g) {
        this.fiberNum_g = fiberNum_g;
    }

    public HashMap<String, NodePair> getNodepairMap() {
        return nodepairMap;
    }

    public void setNodepairMap(HashMap<String, NodePair> nodepairMap) {
        this.nodepairMap = nodepairMap;
    }

    /*

    private HashMap<Node, Integer> LinkRouteAccount( List<Connection> connectionList){
        connectionList.
        for (Node temp : listFinal) {
            Integer count = Nodeaccountmap.get(temp);
            Nodeaccountmap.put(temp, (count == null) ? 1 : count + 1);
        }
        return Nodeaccountmap;
    }
*/




/*    private List<Node> findFixedRouteNode(Connection connection) {
        FixRoute fixRoute = new FixRoute (connection.getSrc(), connection.getDest(),this);
        List<Node> pathList = FixRoute.getPathList();
        return pathList;
    }*/

   // public Object clone() throws CloneNotSupportedException{
    //    Network network = new Network(getName(), getIndex()); }


    public List<Time> getTimeList() {
        return timeList;
    }

    public void setTimeList(List<Time> timeList) {
        this.timeList = timeList;
    }

    public HashMap<String, Time> getTimeHashMap() {
        return timeHashMap;
    }

    public void setTimeHashMap(HashMap<String, Time> timeHashMap) {
        this.timeHashMap = timeHashMap;
    }

    public ArrayList<NodePair> getNodePairsList() {
        return nodePairsList;
    }

    public void setNodePairsList(ArrayList<NodePair> nodePairsList) {
        this.nodePairsList = nodePairsList;
    }

    public List<Link> getLinkList() {
        return linkList;
    }

    public void setLinkList(List<Link> linkList) {
        this.linkList = linkList;
    }

    public HashMap<String, Link> getLinkMap() {
        return linkMap;
    }

    public void setLinkMap(HashMap<String, Link> linkMap) {
        this.linkMap = linkMap;
    }


}
