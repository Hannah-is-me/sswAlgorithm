package com.hanshudan.traffic;

import com.hanshudan.algorithm.RouteSearching;
import com.hanshudan.general.Constant;
import com.hanshudan.general.EventListSort;
import com.hanshudan.network.FSRoute;
import com.hanshudan.network.Network;
import com.hanshudan.network.Node;
import com.hanshudan.network.NodePair;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

import com.hanshudan.general.Constant.*;


public class Traffic {
    private List<Event> eventList = null;
    private Network associatenetwork = null;
    private HashMap<NodePair,Event> associatedNodePaireventMap = null;
    private double blocknumer;
  //  private List<Event> sortList;
    private double bandwidth;
    public Traffic(Network associatenetwork, int time, Random random, int fiberNumber, int g) {

        this.eventList = new ArrayList<>();
        this.associatenetwork = associatenetwork;
        this.associatedNodePaireventMap = new HashMap<>();
        this.generateEvent(time, random, fiberNumber, g);
   //    this.sortList = new EventListSort(associatenetwork,time,random,this.eventList).sortEvent();
        blocknumer = 0.0;
         bandwidth = 0.0;

     //   for (Event e:this.eventList){
     //              System. out.println(   e.getCapacity() + " ");
   //     }

    //    System. out.println( "~~~~~~~~~~~~~~~~~~~~~~~~ ");

     //   for (Event e:this.sortList){
     //       System. out.println(   e.getCapacity() + " ");
    //    }
   //     System. out.println( "~~~~~~~~~~~~~~~~~~~~~~~~ ");

    }



    public void generateEvent(int time, Random random, int fiberNumber, int g) {

        System.out.println("Event is generating...");
        List<Node> nodeList = associatenetwork.getNodeList();
        associatenetwork.generateNodePairs();
        HashMap<String, NodePair> map_nodepair = associatenetwork.getNodepairMap();
        int nodepairNum = map_nodepair.size();
        int totalNum = nodepairNum*Constant.EventNum;
        ArrayList<Integer>starttimeList = new ArrayList<>();
        ArrayList<Integer>duratimeList = new ArrayList<>();
        ArrayList<Integer>endtimeList = new ArrayList<>();
        ArrayList<Double>capacityList = new ArrayList<>();
        duratimeList = DuratimeList( totalNum,time,random);
        starttimeList = StarttimeList( totalNum,duratimeList ,random);
        endtimeList = EndtimeList( totalNum,starttimeList,duratimeList,random);
        capacityList = CapacityList(Constant.wavCapacity, totalNum, random);
        TSornot ( time, starttimeList,   duratimeList ,  endtimeList, random);
    //    long seed = 1;
     //   Random random = new Random(seed);
     //   Random random1 = new Random( 1);

    //    for(int i = 0; i < totalNum; i++){
      //      System.out.println(duratimeList.get(i) + " " + starttimeList.get(i) + " " + endtimeList.get(i) + " " + capacityList.get(i));
     //   }

        Iterator<String> iter_nodepair = map_nodepair.keySet().iterator();

//            NodePair nodePair = map_nodepair.get("N0-N2");
//            double[] timeArray = arrivalList();
//            for (int i = 0; i < Constant.EventNum; i++) {
//                double capacity =(1)*Constant. wavCapacity;
//             //  double capacity =(1)*Constant. wavCapacity;
//                double startTime = timeArray[i];
//                double keepTime = generateTimeInfo();
//                int transportType = Constant.JSwitch ; // 这边需要修改
//                Event e = new Event(nodePair.getSrcNode(), nodePair.getDesNode(), associatenetwork, startTime, keepTime, capacity, transportType);
//                //     e.setNodePair(nodePair);
//                eventList.add(e);
//                //   associatedNodePaireventMap.put(nodePair, e);
//
//
//            }
//
 //      while (iter_nodepair.hasNext())

  //      for ( int j = 0; j< nodepairNum; nodepairNum++)


     for ( int j = 0; j< nodepairNum; j++)

        {
            NodePair nodePair = (NodePair) (map_nodepair.get(iter_nodepair.next()));
        //    double[] timeArray = arrivalList(random);
            for (int i = 0; i < Constant.EventNum; i++) { //将nodepair个数也提取出来，然后进行综合生成序列

            //   double capacity =(7)*Constant. wavCapacity;
                int transportType = Constant.JSwitch ; // 这边需要修改
                Event e = new Event(i, nodePair.getSrcNode(), nodePair.getDesNode(),  nodePair , associatenetwork, starttimeList.get(i+j), duratimeList.get(i+j),  endtimeList.get(i+j), capacityList.get(i+j), transportType, fiberNumber,g );

           //     e.setNodePair(nodePair);
                eventList.add(e);

             //   associatedNodePaireventMap.put(nodePair, e);


            }


        }





//        for (Node node : nodeList) {
//            double[] timeArray = arrivalList();
//            for (int i = 0; i < Constant.EventNum; i++) {
//                // double capacity = Math.random()*60 + 40;
//          //      double capacity = 6*Math.random()*Constant. wavCapacity;
//         //       double capacity =(4+random1.nextInt(3))*Constant. wavCapacity;
//                double capacity =(1+random1.nextInt(5))*Constant. wavCapacity;
//         //       System.out.println("Event is generating..."+capacity );
//                double startTime = timeArray[i];
//                Node dest = null;
//                do {
//                    int temp = random.nextInt(nodeList.size());
//          //          System.out.println("node is generating..."+temp );
//                    dest = nodeList.get(temp);
//                }while (dest.equals(node));
//                double keepTime = generateTimeInfo();
//                int transportType = Constant.JSwitch ; // 这边需要修改
//                Event e = new Event(node, dest, associatenetwork, startTime, keepTime, capacity, transportType);
//                eventList.add(e);
//            }
//
//        }

       // Collections.sort(eventList, ((o1, o2) -> (int) (o1.getStartTime() - o2.getStartTime()))); //排序

   //     Collections.shuffle(eventList); //打乱

    }


   /* private void generateEvent() {
        System.out.println("Event is generating...");
        List<Node> nodeList = associatenetwork.getNodeList();


           double[] timeArray = arrivalList();
            for (int i = 0; i < Constant.EventNum/3; i++) {
                // double capacity = Math.random()*60 + 40;
                double capacity = Math.random()*Constant.fiberNum*Constant.wavCapacity ;
                double startTime = timeArray[i];
                double keepTime = generateTimeInfo();
                int transportType = Constant.JSwitch ; // 这边需要修改
                Event e = new Event(nodeList.get(0), nodeList.get(5), associatenetwork, startTime, keepTime, capacity, transportType);
                eventList.add(e);
            }

            for (int i = Constant.EventNum/3 ; i < Constant.EventNum*2/3; i++) {
                // double capacity = Math.random()*60 + 40;
                double capacity =  Math.random()*Constant.fiberNum*Constant.wavCapacity;
                double startTime = timeArray[i];
                double keepTime = generateTimeInfo();
                int transportType = Constant.JSwitch ; // 这边需要修改
                Event e = new Event(nodeList.get(0), nodeList.get(4), associatenetwork, startTime, keepTime, capacity, transportType);
                eventList.add(e);

            }

           /* for (int i = Constant.EventNum/3 ; i < Constant.EventNum*1/2; i++) {
                // double capacity = Math.random()*60 + 40;
                double capacity = 6*12.5;
                double startTime = timeArray[i];
                double keepTime = generateTimeInfo();
                int transportType = Constant.JSwitch ; // 这边需要修改
                Event e = new Event(nodeList.get(0), nodeList.get(11), associatenetwork, startTime, keepTime, capacity, transportType);
                eventList.add(e);
            }

            for (int i =  Constant.EventNum*1/2 ; i < Constant.EventNum*2/3; i++) {
                // double capacity = Math.random()*60 + 40;
                double capacity = 8*12.5;
                double startTime = timeArray[i];
                double keepTime = generateTimeInfo();
                int transportType = Constant.JSwitch ; // 这边需要修改
                Event e = new Event(nodeList.get(1), nodeList.get(13), associatenetwork, startTime, keepTime, capacity, transportType);
                eventList.add(e);

            }

           for (int i =  Constant.EventNum*2/3 ; i < Constant.EventNum*5/6; i++) {
            // double capacity = Math.random()*60 + 40;
            double capacity = 1*12.5;
            double startTime = timeArray[i];
            double keepTime = generateTimeInfo();
            int transportType = Constant.JSwitch ; // 这边需要修改
            Event e = new Event(nodeList.get(1), nodeList.get(12), associatenetwork, startTime, keepTime, capacity, transportType);
            eventList.add(e);

           }

           for (int i =  Constant.EventNum*5/6 ; i < Constant.EventNum; i++) {
            // double capacity = Math.random()*60 + 40;
            double capacity = 3*12.5;
            double startTime = timeArray[i];
            double keepTime = generateTimeInfo();
            int transportType = Constant.JSwitch ; // 这边需要修改
            Event e = new Event(nodeList.get(1), nodeList.get(11), associatenetwork, startTime, keepTime, capacity, transportType);
            eventList.add(e);

          }*/

            /*  int arraylist = nodeMapListAccount()*/



        /*for (Node node : nodeList) {
            double[] timeArray = arrivalList();
            for (int i = 0; i < Constant.EventNum/4; i++) {
               // double capacity = Math.random()*60 + 40;
                double capacity = 8*12.5;
                double startTime = timeArray[i];
                Node dest = null;
                do {
                    int temp = (new Random()).nextInt(nodeList.size());
                    dest = nodeList.get(temp);
                } while (dest.equals(node));
                double keepTime = generateTimeInfo();
                int transportType = Constant.JSwitch ; // 这边需要修改
                Event e = new Event(node, dest, associatenetwork, startTime, keepTime, capacity, transportType);
                eventList.add(e);
            }

            for (int i = Constant.EventNum/4 ; i < Constant.EventNum/2; i++) {
                // double capacity = Math.random()*60 + 40;
                double capacity = 8*12.5;
                double startTime = timeArray[i];
                Node dest = null;
                do {
                    int temp = (new Random()).nextInt(nodeList.size());
                    dest = nodeList.get(temp);
                }while (dest.equals(node));
                double keepTime = generateTimeInfo();
                int transportType = Constant.JSwitch ; // 这边需要修改
                Event e = new Event(node, dest, associatenetwork, startTime, keepTime, capacity, transportType);
                eventList.add(e);

            }

            for (int i = Constant.EventNum/2 ; i < Constant.EventNum*3/4; i++) {
                // double capacity = Math.random()*60 + 40;
                double capacity = 8*12.5;
                double startTime = timeArray[i];
                Node dest = null;
                do {
                    int temp = (new Random()).nextInt(nodeList.size());
                    dest = nodeList.get(temp);
                }while (dest.equals(node));
                double keepTime = generateTimeInfo();
                int transportType = Constant.JSwitch ; // 这边需要修改
                Event e = new Event(node, dest, associatenetwork, startTime, keepTime, capacity, transportType);
                eventList.add(e);
            }

            for (int i =  Constant.EventNum*3/4 ; i < Constant.EventNum; i++) {
                // double capacity = Math.random()*60 + 40;
                double capacity = 8*12.5;
                double startTime = timeArray[i];
                Node dest = null;
                do {
                    int temp = (new Random()).nextInt(nodeList.size());
                    dest = nodeList.get(temp);
                }while (dest.equals(node));
                double keepTime = generateTimeInfo();
                int transportType = Constant.JSwitch ; // 这边需要修改
                Event e = new Event(node, dest, associatenetwork, startTime, keepTime, capacity, transportType);
                eventList.add(e);

            }

          *//*  int arraylist = nodeMapListAccount()*//*
        }*/

        /*Collections.sort(eventList, new Comparator<Event>() {
            @Override
            public int compare(Event o1, Event o2) {
                if(o1.getStartTime() > o2.getStartTime()){
                    return 1;
                }else {
                    return -1;
                }
            }
        });

    }
    */


    public double printResult(){


    //    System.out.println("该模型的阻塞率为：" + 100.0 * Constant.BlockNum / this.eventList.size() + "%");
        double a = (double) this.blocknumer / this.eventList.size();
//        for (int i = 0 ; i < eventList.size(); i++){
//            System.out.println("该模型的阻塞值为：" +eventList.get(i).getSrc().getName()+eventList.get(i).getDest().getName());
//        }
        System.out.println("该模型的阻塞值为：" +a);
        return a;

    }


    public double printbandwidthBPResult(){
        double totalbandwidth = 0;
        for (Event event:this.eventList){
            totalbandwidth = event.getCapacity()+totalbandwidth;
        }

        //    System.out.println("该模型的阻塞率为：" + 100.0 * Constant.BlockNum / this.eventList.size() + "%");
        double a = (double) this.bandwidth / totalbandwidth ;
//        for (int i = 0 ; i < eventList.size(); i++){
//            System.out.println("该模型的阻塞值为：" +eventList.get(i).getSrc().getName()+eventList.get(i).getDest().getName());
//        }
        System.out.println("算法带宽阻塞值为：" +a);
        return a;

    }

    public void Result(){
        double a = (double)  blocknumer / this.eventList.size();
        System.out.println("该模型的阻塞值为：" +a);
    }






    public double[] arrivalList(Random random){
        double[]timeList = new double[Constant.EventNum];
        timeList[0] = 0;
        for (int i = 1; i < Constant.EventNum; i++) {
            timeList[i] = random.nextInt(16) ;
        }
        return timeList;
    }



//    public double[] arrivalList(){
//        double[]timeList = new double[Constant.EventNum];
//        timeList[0] = this.nextArrivalTime();
//        for (int i = 1; i < Constant.EventNum; i++) {
//            timeList[i] = timeList[i-1] + this.nextArrivalTime();
//        }
//        return timeList;
//    }

    public double nextArrivalTime(){
        Random r = new Random();
        double P = r.nextDouble();
        double t = -1.0/Constant.lambda * (Math.log(P));
        return t;
    }


    public ArrayList<Integer>StarttimeList( Integer event_number, ArrayList<Integer> duratimeList, Random random){

        ArrayList<Integer> starttimeList = new ArrayList<Integer>();
        for (int i = 0; i< event_number; i++){
            starttimeList.add(random.nextInt(Constant.timeslotNum) + 1);
        }

        return starttimeList ;
    }

    public  ArrayList<Double> CapacityList(double wavCapacity, Integer event_number, Random random){

        ArrayList<Double> capacityList = new ArrayList<>();
        for (int i = 0; i< event_number; i++){

           capacityList.add((double)((Constant.FSslotNumlowbound+random.nextInt(Constant.FSslotNumhighbound))*wavCapacity));

           // capacityList.add((double)((Constant.FSslotNum)*wavCapacity));
        }

        return  capacityList;

    }



    public  ArrayList<Integer> DuratimeList(Integer event_number, Integer time, Random random){

        ArrayList<Integer> timeList = new ArrayList<Integer>();
       for (int i = 0; i< event_number; i++){
           timeList.add(random.nextInt(2*time)+2);
       }

        return timeList;
    }

    public  ArrayList<Integer> EndtimeList(Integer event_number, ArrayList<Integer> starttimeList,ArrayList<Integer> duratimeList, Random random){

        ArrayList<Integer> endtimeList = new ArrayList<Integer>();
        for (int i = 0; i< event_number; i++){
            int ii = random.nextBoolean()?1:0;
            endtimeList.add(starttimeList.get(i)+(duratimeList.get(i))-ii);
        }

        return  endtimeList;
    }


    public  void TSornot (  Integer time, ArrayList<Integer>starttimeList,  ArrayList<Integer> timeList,  ArrayList<Integer> endtimeList, Random random){
        for (int i = 0; i< starttimeList.size(); i++){
            while( endtimeList.get(i)>Constant.timetotalnum){
                starttimeList.set(i, random.nextInt(Constant.timeslotNum));
                timeList.set(i, random.nextInt(2*time)+2);
                endtimeList.set(i, starttimeList.get(i)+(timeList.get(i)* (int)Math.ceil(random.nextInt(2)+0.001)));
            }
        }

    }



    public double generateTimeInfo( Integer time, Random random){

        int td = random.nextInt(2*time-2+1)+2;
        int yy= random.nextInt(2)+1;
        int keepTime = (int) Math.ceil(td/yy) ;

        return keepTime;
    }


    public List<Event> getEventList() {
        return eventList;
    }

    public void setEventList(List<Event> eventList) {
        this.eventList = eventList;
    }

    public Network getAssociatenetwork() {
        return associatenetwork;
    }

    public void setAssociatenetwork(Network associatenetwork) {
        this.associatenetwork = associatenetwork;
    }

    public HashMap<NodePair, Event> getAssociatedNodePaireventMap() {
        return associatedNodePaireventMap;
    }

    public void setAssociatedNodePaireventMap(HashMap<NodePair, Event> associatedNodePaireventMap) {
        this.associatedNodePaireventMap = associatedNodePaireventMap;
    }

    public double getBlocknumer() {
        return blocknumer;
    }

    public void setBlocknumer(double blocknumer) {
        this.blocknumer = blocknumer;
    }

    public double getBandwidth() {
        return bandwidth;
    }

    public void setBandwidth(double bandwidth) {
        this.bandwidth = bandwidth;
    }
}
