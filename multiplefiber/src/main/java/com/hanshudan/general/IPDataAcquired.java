package com.hanshudan.general;

import com.hanshudan.network.Network;
import com.hanshudan.traffic.Action;
import com.hanshudan.traffic.Event;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class IPDataAcquired {
    private List<Action> iactionList;
    private Network network;
    private  int  fibernumber;
    private String name;
    private List<Action>actionList;

    private HashMap<String, Integer>  fibernumMap;
    private  ArrayList<String> CList;
    private  ArrayList<String> fibernumberList;

    public IPDataAcquired(){

    }
    public IPDataAcquired(List<Action> nactiontList, Network layer, String networkname, int time)throws IOException {
      this.actionList = nactiontList;
      this.network = layer;
      this.fibernumber=4;
      this.name = networkname;
      this.CList = new ArrayList<String>();
      this.fibernumMap = new  HashMap<String, Integer>();
      this.fibernumberList = new ArrayList<String>();

//       this.CList.add("C1");
//       this.CList.add("C2");
//       this.CList.add("C4");
//     //  this.CList.add("C6");
//       this.fibernumMap.put("C1",1);
//       this.fibernumMap.put("C2",2);
//        this.fibernumMap.put("C4",4);
//        this.fibernumberList.add("4");
//        this.fibernumberList.add("4");
//        this.fibernumberList.add("4");

        this.CList.add("C1");
        this.CList.add("C2");
        this.CList.add("C3");
        this.CList.add("C6");
        //  this.CList.add("C6");
        this.fibernumMap.put("C1",1);
        this.fibernumMap.put("C2",2);
        this.fibernumMap.put("C3",3);
        this.fibernumMap.put("C6",6);
        this.fibernumberList.add("6");
        this.fibernumberList.add("6");
        this.fibernumberList.add("6");
        this.fibernumberList.add("6");

//        this.CList.add("C1");
//        this.CList.add("C2");
//        this.CList.add("C4");
//        this.CList.add("C8");
//        //  this.CList.add("C6");
//        this.fibernumMap.put("C1",1);
//        this.fibernumMap.put("C2",2);
//        this.fibernumMap.put("C4",4);
//        this.fibernumMap.put("C8",8);
//        this.fibernumberList.add("8");
//        this.fibernumberList.add("8");
//        this.fibernumberList.add("8");
//        this.fibernumberList.add("8");
//
//        this.CList.add("C1");
//        this.CList.add("C2");
//        this.CList.add("C5");
//        this.CList.add("C10");
//        //  this.CList.add("C6");
//        this.fibernumMap.put("C1",1);
//        this.fibernumMap.put("C2",2);
//        this.fibernumMap.put("C5",5);
//        this.fibernumMap.put("C10",10);
//        this.fibernumberList.add("10");
//        this.fibernumberList.add("10");
//        this.fibernumberList.add("10");
//        this.fibernumberList.add("10");
//
//        this.CList.add("C1");
//        this.CList.add("C2");
//        this.CList.add("C3");
//        this.CList.add("C6");
//        this.CList.add("C4");
//        this.CList.add("C12");
//        //  this.CList.add("C6");
//        this.fibernumMap.put("C1",1);
//        this.fibernumMap.put("C2",2);
//        this.fibernumMap.put("C3",3);
//        this.fibernumMap.put("C4",4);
//        this.fibernumMap.put("C6",6);
//        this.fibernumMap.put("C12",12);
//        this.fibernumberList.add("12");
//        this.fibernumberList.add("12");
//        this.fibernumberList.add("12");
//        this.fibernumberList.add("12");
//        this.fibernumberList.add("12");
//        this.fibernumberList.add("12");
//
//        this.CList.add("C1");
//        this.CList.add("C2");
//        this.CList.add("C7");
//        this.CList.add("C14");
//        //  this.CList.add("C6");
//        this.fibernumMap.put("C1",1);
//        this.fibernumMap.put("C2",2);
//        this.fibernumMap.put("C7",7);
//        this.fibernumMap.put("C14",14);
//        this.fibernumberList.add("14");
//        this.fibernumberList.add("14");
//        this.fibernumberList.add("14");
//        this.fibernumberList.add("14");
//
//        this.CList.add("C1");
//        this.CList.add("C3");
//        this.CList.add("C5");
//        this.CList.add("C15");
//        //  this.CList.add("C6");
//        this.fibernumMap.put("C1",1);
//        this.fibernumMap.put("C3",3);
//        this.fibernumMap.put("C5",5);
//        this.fibernumMap.put("C15",15);
//        this.fibernumberList.add("15");
//        this.fibernumberList.add("15");
//        this.fibernumberList.add("15");
//        this.fibernumberList.add("15");
//
//        this.CList.add("C1");
//        this.CList.add("C2");
//        this.CList.add("C4");
//        this.CList.add("C8");
//        this.CList.add("C16");
//        //  this.CList.add("C6");
//        this.fibernumMap.put("C1",1);
//        this.fibernumMap.put("C2",2);
//        this.fibernumMap.put("C4",4);
//        this.fibernumMap.put("C8",8);
//        this.fibernumMap.put("C16",16);
//        this.fibernumberList.add("16");
//        this.fibernumberList.add("16");
//        this.fibernumberList.add("16");
//        this.fibernumberList.add("16");
//        this.fibernumberList.add("16");
//
//        this.CList.add("C1");
//        this.CList.add("C2");
//        this.CList.add("C9");
//        this.CList.add("C18");
//        //  this.CList.add("C6");
//        this.fibernumMap.put("C1",1);
//        this.fibernumMap.put("C2",2);
//        this.fibernumMap.put("C9",9);
//        this.fibernumMap.put("C18",18);
//        this.fibernumberList.add("18");
//        this.fibernumberList.add("18");
//        this.fibernumberList.add("18");
//        this.fibernumberList.add("18");
//
//        this.CList.add("C1");
//        this.CList.add("C2");
//        this.CList.add("C4");
//        this.CList.add("C5");
//        this.CList.add("C10");
//        this.CList.add("C20");
//        //  this.CList.add("C6");
//        this.fibernumMap.put("C1",1);
//        this.fibernumMap.put("C2",2);
//        this.fibernumMap.put("C4",4);
//        this.fibernumMap.put("C5",5);
//        this.fibernumMap.put("C20",20);
//        this.fibernumberList.add("20");
//        this.fibernumberList.add("20");
//        this.fibernumberList.add("20");
//        this.fibernumberList.add("20");
//        this.fibernumberList.add("20");
//        this.fibernumberList.add("20");
     //  this.fibernumMap.put("C6",6);
        //    for (int i = 0; i <lists.size(); i++) {
        //         Connection con = lists.get(i);


   //   outputeventTS(this.actionList,this.network,this.name);
  //    outputeventroute(this.actionList,this.network,this.name);
   //   outputeNodepair(this.actionList,this.network,this.name);
   //   outputeventTW(this.actionList,this.network,this.name);
   //   outputeventrealcapacity(this.actionList,this.network,this.name);
    //  outputeventformedcapacity(this.actionList,this.network,this.fibernumber,this.name);

      allinformaninAll(this.actionList,this.network, this.fibernumberList,this.name,this.CList, this.fibernumMap, time);

    }




    public void  allinformaninAll( List<Action> actionList, Network layer, ArrayList<String> fibernumberList, String networkname, ArrayList<String> CList , HashMap<String, Integer> fiberNumMap, int time ) throws IOException{


        //    for (int i = 0; i <lists.size(); i++) {
        //         Connection con = lists.get(i);
        File file = new File(
                "D:\\tianyangpeng\\Section2\\FSwinadaptivenodepair(improve)\\FSwinadaptivenodepair(improve)\\FSwinadaptivenodepair(improve)\\FSwinadaptivenodepair(improve)\\FSwinadaptivenodepair(improve)\\FSwinadaptivenodepair(improve)\\multiplefiber\\src\\main\\java\\com\\hanshudan\\dataStorage\\"+ actionList.size() +time+ "个业务"+"IP模型信息"+ networkname+".csv");
        if (file.exists()) {
            if (!file.delete()) {
                throw new IllegalArgumentException("初始化文件失败");
            }
        }
        if (!file.createNewFile()) {
            throw new IllegalArgumentException("创建文件失败");
        }
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));

       //---------------------------节点对信息--------------------------------

        bw.write("set NR:= ");
        for (Action event: actionList) {
            bw.write(event.getCon().getNodePair().getSrcNode().getName()+"-"+event.getCon().getNodePair().getDesNode().getName()+"-"+event.getCon().getIndex());
            bw.write(' ');
        }
        bw.write(';');
        bw.write('\n');

        //---------------------------节点对路由信息--------------------------------

      //  bw.write("节点对,开始时间,持续时间,结束时间\n");
        bw.write('\n');
        for (Action event:actionList) {
            bw.write("set P["+event.getCon().getNodePair().getSrcNode().getName()+"-"+event.getCon().getNodePair().getDesNode().getName()+"-"+event.getCon().getIndex()+"] :=");
            for ( int i = 0; i<event.getCon().getNodePair().getLinearroutelist().get(0).getFiberlist().size();i ++) {

                bw.write(" " + event.getCon().getNodePair().getLinearroutelist().get(0).getFiberlist().get(i).getSrcNode().getName() +
                        "-" + event.getCon().getNodePair().getLinearroutelist().get(0).getFiberlist().get(i).getDestNode().getName());
                //     bw.write(String.valueOf(nodePair.getName()));

            }
            bw.write(';');
            bw.write('\n');
        }


     //------------------------时间窗信息---------------------------------------
    //    bw.write("节点对,开始时间,持续时间,结束时间\n");
        bw.write('\n');
        for (Action event:  actionList) {
            bw.write("set TW["+event.getCon().getSrc().getName()+"-"+event.getCon().getDest().getName()+"-"+event.getCon().getIndex()+"] :=");
            for ( int i =0; i+(int)event.getCon().getStartTime()+(int)event.getCon().getKeepTime()-1<=event.getCon().getEndTime();i ++){
                int starttime =(int)event.getCon().getStartTime()+i;
                int endtime = (int)event.getCon().getStartTime()+i+(int)event.getCon().getKeepTime()-1;
                bw.write(" "+starttime +"-"+endtime );
                //     bw.write(String.valueOf(nodePair.getName()));
            }
            bw.write(";");
            bw.write('\n');
        }


        //--------------------时间窗的时间隙信息--------------------------------
        //bw.write("节点对,开始时间,持续时间,结束时间\n");
        bw.write('\n');
        for (Action event: actionList) {

            for ( int i =0; i+(int)event.getCon().getStartTime()+(int)event.getCon().getKeepTime()-1<=event.getCon().getEndTime();i ++){
                int starttime =(int)event.getCon().getStartTime()+i;
                int endtime = (int)event.getCon().getStartTime()+i+(int)event.getCon().getKeepTime()-1;

                bw.write("set TS["+event.getCon().getSrc().getName()+"-"+event.getCon().getDest().getName()+"-"+event.getCon().getIndex());
                bw.write(",");
                bw.write(" "+starttime +"-"+endtime );
                bw.write("]:= ");
                for (int j=starttime ; j<=endtime ;j ++){
                    bw.write(j+" ");}
                bw.write(";");
                bw.write('\n');
                //     bw.write(String.valueOf(nodePair.getName()));
            }
        }

        //-----------------------所需要的FS数--------------------------------
        bw.write('\n');
        bw.write("param d :=");
        bw.write('\n');

        for (Action event:actionList) {

            bw.write(event.getCon().getSrc().getName()+"-"+event.getCon().getDest().getName()+"-"+event.getCon().getIndex());
            //  int cap = (int)Math.ceil(action.getCon().getCapacity()/Constant.wavCapacity/Constant.g);
            int cap = (int)Math.ceil(event.getCon().getCapacity()/Constant.wavCapacity);
            bw.write(" "+cap);

            bw.write('\n');
            //     bw.write(String.valueOf(nodePair.getName()));

        }

        bw.write(";");
        bw.write('\n');

        //-----------------------实际所需要的FS数(经过SCh)--------------------------------



        bw.write('\n');
        bw.write("param Rr :=");
        bw.write('\n');

        for (int i = 0; i <CList.size();i++){

            for (Action event:actionList) {
                //    int num = Constant.fiberNum;
                for (int ii = 0; ii < event.getCon().getNodePair().getLinearroutelist().get(0).getFiberlist().size(); ii++) {

                 bw.write(fibernumberList.get(i) + ",");//Constant.fiber
                 bw.write(CList.get(i) + ","); //Constant.g
                 bw.write(event.getCon().getSrc().getName() + "-" + event.getCon().getDest().getName() + "-" + event.getCon().getIndex() + ",");
                 int cap = (int) Math.ceil(event.getCon().getCapacity() / Constant.wavCapacity / fiberNumMap.get(CList.get(i)));
                 bw.write(" " + event.getCon().getNodePair().getLinearroutelist().get(0).getFiberlist().get(ii).getSrcNode().getName() +
                            "-" + event.getCon().getNodePair().getLinearroutelist().get(0).getFiberlist().get(ii).getDestNode().getName()+ ",");
                    //     bw.write(String.valueOf(nodePair.getName()));

                bw.write(" " + cap);
                bw.write('\n');

              }
            }
        }

        bw.write(";");



        //            bw.write("=============================");
        bw.flush();
        bw.close();
        //   }




    }





    public  void outputLink( Network layer, String networkname) throws IOException {

        //    for (int i = 0; i <lists.size(); i++) {
        //         Connection con = lists.get(i);
        File file = new File(
                "D:\\phd\\Projects\\Muti_fiber networks\\JAVA\\Static scenerio\\FSwinadaptivenodepair(improve)\\multiplefiber" +
                        "\\src\\main\\java\\com"+ "个业务"+"P[r]"+ networkname+".csv");
        if (file.exists()) {
            if (!file.delete()) {
                throw new IllegalArgumentException("初始化文件失败");
            }
        }
        if (!file.createNewFile()) {
            throw new IllegalArgumentException("创建文件失败");
        }

        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        bw.write("节点对,开始时间,持续时间,结束时间\n");
        for (int i = 0 ; i < layer.getFiberList().size(); i ++ ){
            bw.write("set L :=");

                bw.write( layer.getNodePairsList().get(i).getSrcNode().getName()+"-"+layer.getNodePairsList().get(i).getDesNode().getName());
                //     bw.write(String.valueOf(nodePair.getName()));


        }
        bw.write(';');
        //            bw.write("=============================");
        bw.flush();
        bw.close();
        //   }
    }

    public  void outputeventTS( List<Action> actionList, Network layer, String networkname) throws IOException {
      //  ArrayList<Event> lists = eventlist;
        //    for (int i = 0; i <lists.size(); i++) {
        //         Connection con = lists.get(i);
        File file = new File("D:\\phd\\Projects\\Muti_fiber networks\\JAVA\\Static scenerio\\FSwinadaptivenodepair(improve)\\multiplefiber" +
                "\\src\\main\\java\\com"+actionList.size() + "个业务"+"TS"+ networkname+".csv");
        if (file.exists()) {
            if (!file.delete()) {
                throw new IllegalArgumentException("初始化文件失败");
            }
        }
        if (!file.createNewFile()) {
            throw new IllegalArgumentException("创建文件失败");
        }
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        bw.write("节点对,开始时间,持续时间,结束时间\n");
        for (Action event: actionList) {

            for ( int i =0; i+(int)event.getCon().getStartTime()+(int)event.getCon().getKeepTime()-1<=event.getCon().getEndTime();i ++){
                int starttime =(int)event.getCon().getStartTime()+i;
                int endtime = (int)event.getCon().getStartTime()+i+(int)event.getCon().getKeepTime()-1;

                bw.write("set TS["+event.getCon().getSrc().getName()+"-"+event.getCon().getDest().getName()+"-"+event.getCon().getIndex());
                bw.write(",");
                bw.write(" "+starttime +"-"+endtime );
                bw.write("]:= ");
                for (int j=starttime ; j<=endtime ;j ++){
                    bw.write(j+" ");}
                bw.write(";");
                bw.write('\n');
                //     bw.write(String.valueOf(nodePair.getName()));
            }
        }




        //            bw.write("=============================");
        bw.flush();
        bw.close();
        //   }
    }

    public  void outputeventroute( List<Action> actionList, Network layer, String networkname) throws IOException {

        //    for (int i = 0; i <lists.size(); i++) {
        //         Connection con = lists.get(i);
        File file = new File(
                "D:\\phd\\Projects\\Muti_fiber networks\\JAVA\\Static scenerio\\FSwinadaptivenodepair(improve)\\multiplefiber" +
                        "\\src\\main\\java\\com"+ actionList.size() + "个业务"+"P[r]"+ networkname+".csv");
        if (file.exists()) {
            if (!file.delete()) {
                throw new IllegalArgumentException("初始化文件失败");
            }
        }
        if (!file.createNewFile()) {
            throw new IllegalArgumentException("创建文件失败");
        }
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        bw.write("节点对,开始时间,持续时间,结束时间\n");
        for (Action event:actionList) {
            bw.write("set P["+event.getCon().getNodePair().getSrcNode().getName()+"-"+event.getCon().getNodePair().getDesNode().getName()+"-"+event.getCon().getIndex()+"] :=");
            for ( int i = 0; i<event.getCon().getNodePair().getLinearroutelist().get(0).getFiberlist().size();i ++) {

                bw.write(" " + event.getCon().getNodePair().getLinearroutelist().get(0).getFiberlist().get(i).getSrcNode().getName() +
                        "-" + event.getCon().getNodePair().getLinearroutelist().get(0).getFiberlist().get(i).getDestNode().getName());
                //     bw.write(String.valueOf(nodePair.getName()));

            }
            bw.write(';');
            bw.write('\n');
        }
        //            bw.write("=============================");
        bw.flush();
        bw.close();
        //   }
    }

    public  void outputeNodepair( List<Action> actionList, Network layer, String networkname) throws IOException {

        //    for (int i = 0; i <lists.size(); i++) {
        //         Connection con = lists.get(i);
        File file = new File(
                "D:\\phd\\Projects\\Muti_fiber networks\\JAVA\\Static scenerio\\FSwinadaptivenodepair(improve)\\multiplefiber" +
                        "\\src\\main\\java\\com"+ actionList.size() + "个业务"+"NR"+ networkname+".csv");
        if (file.exists()) {
            if (!file.delete()) {
                throw new IllegalArgumentException("初始化文件失败");
            }
        }
        if (!file.createNewFile()) {
            throw new IllegalArgumentException("创建文件失败");
        }
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        bw.write("节点对,开始时间,持续时间,结束时间\n");
        bw.write("set NR:= ");
        for (Action event: actionList) {
            bw.write(event.getCon().getNodePair().getSrcNode().getName()+"-"+event.getCon().getNodePair().getDesNode().getName()+"-"+event.getCon().getIndex());
            bw.write(' ');
        }
        bw.write(';');
        //            bw.write("=============================");
        bw.flush();
        bw.close();
        //   }
    }

    public  void outputeventTW(List<Action> actionList, Network layer, String networkname) throws IOException {

        //    for (int i = 0; i <lists.size(); i++) {
        //         Connection con = lists.get(i);
        File file = new File("D:\\phd\\Projects\\Muti_fiber networks\\JAVA\\Static scenerio\\FSwinadaptivenodepair(improve)\\multiplefiber" +
                "\\src\\main\\java\\com"+  actionList.size() + "个业务"+"TW"+networkname+".csv");
        if (file.exists()) {
            if (!file.delete()) {
                throw new IllegalArgumentException("初始化文件失败");
            }
        }
        if (!file.createNewFile()) {
            throw new IllegalArgumentException("创建文件失败");
        }
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        bw.write("节点对,开始时间,持续时间,结束时间\n");
        for (Action event:  actionList) {
            bw.write("set TW["+event.getCon().getSrc().getName()+"-"+event.getCon().getDest().getName()+"-"+event.getCon().getIndex()+"] :=");
            for ( int i =0; i+(int)event.getCon().getStartTime()+(int)event.getCon().getKeepTime()-1<=event.getCon().getEndTime();i ++){
                int starttime =(int)event.getCon().getStartTime()+i;
                int endtime = (int)event.getCon().getStartTime()+i+(int)event.getCon().getKeepTime()-1;
                bw.write(" "+starttime +"-"+endtime );
                //     bw.write(String.valueOf(nodePair.getName()));
            }
            bw.write(";");
            bw.write('\n');
        }
        //            bw.write("=============================");
        bw.flush();
        bw.close();
        //   }
    }

    public  void outputeventrealcapacity( List<Action> actionList, Network layer, String networkname) throws IOException {

        //    for (int i = 0; i <lists.size(); i++) {
        //         Connection con = lists.get(i);
        File file = new File("D:\\phd\\Projects\\Muti_fiber networks\\JAVA\\Static scenerio\\FSwinadaptivenodepair(improve)\\multiplefiber" +
                "\\src\\main\\java\\com"+ actionList.size() + "个业务"+"dr"+ networkname+".csv");
        if (file.exists()) {
            if (!file.delete()) {
                throw new IllegalArgumentException("初始化文件失败");
            }
        }
        if (!file.createNewFile()) {
            throw new IllegalArgumentException("创建文件失败");
        }
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));

        bw.write("节点对,开始时间,持续时间,结束时间\n");
        for (Action event:actionList) {

            bw.write(event.getCon().getSrc().getName()+"-"+event.getCon().getDest().getName()+"-"+event.getCon().getIndex());
            //  int cap = (int)Math.ceil(action.getCon().getCapacity()/Constant.wavCapacity/Constant.g);
            int cap = (int)Math.ceil(event.getCon().getCapacity()/Constant.wavCapacity);
            bw.write(" "+cap);

            bw.write('\n');
            //     bw.write(String.valueOf(nodePair.getName()));

        }
        bw.write(";");
        //            bw.write("=============================");

        bw.flush();
        bw.close();
        //   }
    }

    public  void outputeventformedcapacity(List<Action> actionList, Network layer, int fibernumber, String networkname) throws IOException {


        File file = new File("D:\\phd\\Projects\\Muti_fiber networks\\JAVA\\Static scenerio\\FSwinadaptivenodepair(improve)\\multiplefiber" +
                "\\src\\main\\java\\com"+ actionList.size() + "个业务"+"Rr"+ networkname+".csv");
        if (file.exists()) {
            if (!file.delete()) {
                throw new IllegalArgumentException("初始化文件失败");
            }
        }
        if (!file.createNewFile()) {
            throw new IllegalArgumentException("创建文件失败");
        }
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));


        HashMap <String, Integer> fiberNumMap = new HashMap<>();
        ArrayList<String> CList = new ArrayList<>();
        CList.add("C1");
        //  CList.add("C2");
        //  CList.add("C3");
        //  CList.add("C6");
        fiberNumMap.put("C1",1);
        //fiberNumMap.put("C2",2);
        //fiberNumMap.put("C3",3);
        //fiberNumMap.put("C6",6);
        //    for (int i = 0; i <lists.size(); i++) {
        //         Connection con = lists.get(i);

        bw.write("节点对,开始时间,持续时间,结束时间\n");

        for (int i = 0; i <CList.size();i++){

            for (Action event:actionList) {
                //    int num = Constant.fiberNum;

                bw.write(fibernumber+ ",");//Constant.fiber
                bw.write(CList.get(i) + ","); //Constant.g
                bw.write(event.getCon().getSrc().getName() + "-" + event.getCon().getDest().getName() +"-"+event.getCon().getIndex()+ ",");
                int cap = (int) Math.ceil(event.getCon().getCapacity() / Constant.wavCapacity / fiberNumMap.get(CList.get(i)));
                bw.write(" " + cap );

                bw.write('\n');

            }

        }

        //  int cap = (int)Math.ceil(action.getCon().getCapacity()/Constant.wavCapacity/Constant.g);


        //     bw.write(String.valueOf(nodePair.getName()));


        //            bw.write("=============================");
        bw.flush();
        bw.close();
        //   }
    }

    public HashMap<int[], Double> readWSSInfo(String filename){
        HashMap<int[], Double> wssInfoList = new HashMap<>();
        String[] dataArr = new String[3];
        File file = new File(filename);
        BufferedReader BufRdr = null;
        try{
            BufRdr = new BufferedReader(new FileReader(file));
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line = null;
        int col = 0;
        try{
            line = BufRdr.readLine();
        }catch (IOException e) {
            e.printStackTrace();
        }
        try{
            while((line = BufRdr.readLine()) != null){
                StringTokenizer st = new StringTokenizer(line, ",");
                while(st.hasMoreTokens()){
                    dataArr[col++] = st.nextToken();
                }
                col = 0;
                int[] arr = new int[2];
                int ingress = Integer.parseInt(dataArr[0]);
                int outgress = Integer.parseInt(dataArr[1]);
                arr[0] = ingress;
                arr[1] = outgress;
                double cost = Double.parseDouble(dataArr[2]);
                wssInfoList.put(arr, cost);
            }
        } catch(IOException e){
            e.printStackTrace();
        }
        try {
            BufRdr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wssInfoList;
    }


    public Network getNetwork() {
        return network;
    }

    public void setNetwork(Network network) {
        this.network = network;
    }

    public int getFibernumber() {
        return fibernumber;
    }

    public void setFibernumber(int fibernumber) {
        this.fibernumber = fibernumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Action> getIactionList() {
        return iactionList;
    }

    public void setIactionList(List<Action> iactionList) {
        this.iactionList = iactionList;
    }

    public List<Action> getActionList() {
        return actionList;
    }

    public void setActionList(List<Action> actionList) {
        this.actionList = actionList;
    }

    public HashMap<String, Integer> getFibernumMap() {
        return fibernumMap;
    }

    public void setFibernumMap(HashMap<String, Integer> fibernumMap) {
        this.fibernumMap = fibernumMap;
    }

    public ArrayList<String> getCList() {
        return CList;
    }

    public void setCList(ArrayList<String> CList) {
        this.CList = CList;
    }
}
