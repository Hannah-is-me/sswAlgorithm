package com.hanshudan.general;

import com.hanshudan.algorithm.RouteSearching;
import com.hanshudan.network.*;
import com.hanshudan.traffic.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class actionAllocation {
    private Map<String,Double> networkWeights;
    private List<Action> sortList;

    public actionAllocation() {

        this.networkWeights = new HashMap<>();
        this.sortList = new ArrayList<>();
    }

    public  Map doactionAllocation (Traffic traffic, Network layer, Random random, int time, int flagselection, String netname, Integer fibernumber, Integer NodeNumber, Integer g,
                                     Integer F_g, int Degreetotal, double weightS, double weightL, double weightcourt, boolean flag, boolean resultofFSmax,
                                     HashMap<int[] , Double> laserInfoList, HashMap<int[] , Double> WSSInfoList) {


//        for (int i = 0 ; i < traffic.getEventList().size(); i++){
//            connectionList.add(traffic.getEventList().get(i).getCon());
//        }
//        for (int i = 0; i < actionList.size(); i++){
//            System.out.println( actionList.get(i).getType());
//        }
        int Timeinsert =5;
        int PW = 0;

        //    traffic.setBlocknumer(0);
        layer.generateNodePairs();
        //     RouteSearching RS = new RouteSearching();
        //    RS.kShortestPath(layer, 1,true);
      //  layer.rankActions();
        List<Action> actionList = layer.getActionList();

//        for (Action e:actionList){
//            System. out.println(   e.getCon().getCapacity() + " ");
//        }
//        System. out.println(   " ~~~~");
//        layer.rankActions();
//
//        for (Action e:actionList){
//                     System. out.println(   e.getCon().getCapacity() + " ");
//        }

  //   Collections.shuffle(actionList);
   //     sortList = new ActionListSort(actionList,random).sortAction(random);


//           for (Action e:actionList){
//                      System. out.println(   e.getCon().getCapacity() + " ");
//             }
//
//            System. out.println( "~~~~~~~~~~~~~~~~~~~~~~~~ ");
//
//           for (Action e:sortList){
//               System. out.println(   e.getCon().getCapacity() + " ");
//            }
//             System. out.println( "~~~~~~~~~~~~~~~~~~~~~~~~ ");





        // demandAllocationSWP( actionList, layer,traffic);

  //      Collections.shuffle(actionList); //打乱


       ;
    //    for (Action a : actionList){
      //      Event E = a.getCon().getAssociateEvent();
        //    System. out.println(E.getCapacity() + " ");

     //   }
        System.out.println("------------------------------------");





        traffic.setBlocknumer(0);//first-fit选择路由
    //  timeslot_first


        if (flag == false)

        {
            if(flagselection ==0){

                costselectTimeFSwindow(actionList, layer,traffic,fibernumber, g);
            }

            //  timeslot_first+fixed route
            if(flagselection ==1){

                fixedrouterfirstfitselectTimewindow(actionList, layer,traffic, fibernumber, g);

            }
            //  timeslot_first+update route
            if(flagselection ==2){

                updaterouterfirstfitselectTimewindow(actionList, layer,traffic, fibernumber, g);

            }




            //  SSW_first+fixed route
            if(flagselection ==3){

                fixedrouterfirstfitselectSSWwindow(actionList, layer,traffic, fibernumber, g);

            }

            //  SSW_first+update route
            if(flagselection ==4){

                updaterouterfirstfitselectSSWwindow(actionList, layer,traffic, fibernumber, g);

            }

            //  SSW_leastload+fixe route
            if(flagselection == 5){

           //     fixedrouterleastloadSSWwindow(actionList, layer,traffic, fibernumber, g);
                fixedrouterfirstfitselectTimewindowTestleastload(actionList, layer,traffic, fibernumber, g);
            }

            //  SSW_leastload+update route
            if(flagselection == 6){

                updaterouterleastloadSSWwindow(actionList, layer,traffic, fibernumber,g);

            }


            if(flagselection == 7){

                updaterouterleastloadSSWwindow(actionList, layer,traffic, fibernumber, g);

            }
        }

        else
            {

            if(flagselection ==0){

                costselectTimeFSwindow(  sortList, layer,traffic, fibernumber, g);
            }

            //  timeslot_first+fixed route
            if(flagselection ==1){

                fixedrouterfirstfitselectTimewindow(  sortList, layer,traffic, fibernumber, g);

            }
            //  timeslot_first+update route
            if(flagselection ==2){

                updaterouterfirstfitselectTimewindow(  sortList, layer,traffic, fibernumber, g);

            }




            //  SSW_first+fixed route
            if(flagselection ==3){

                fixedrouterfirstfitselectSSWwindow(  sortList, layer,traffic, fibernumber, g);

            }

            //  SSW_first+update route
            if(flagselection ==4){

                updaterouterfirstfitselectSSWwindow(  sortList, layer,traffic, fibernumber,g);

            }

            //  SSW_leastload+fixe route
            if(flagselection == 5){

                fixedrouterleastloadSSWwindow(  sortList, layer,traffic, fibernumber, g);

            }

            //  SSW_leastload+update route
            if(flagselection == 6){

                updaterouterleastloadSSWwindow(  sortList, layer,traffic, fibernumber, g);

            }


            if(flagselection == 7){

                updaterouterleastloadSSWwindow(  sortList, layer,traffic, fibernumber, g);

            }




        }





        PW = net_pwofTime( Timeinsert, layer);
        System.out.println("当前时隙"+Timeinsert+"网络总功耗"+PW);
        traffic.Result();

        traffic.printResult();
        traffic.printbandwidthBPResult();
      //  traffic.setBlocknumer(0);
       // outputAllTime( actionList , layer);
      //  outputeventformedcapacity(actionList, layer,Constant.fiberNum);
       // outputeventDL(actionList, layer,Constant.fiberNum);
        // outputFmaxofFiber(actionList, layer,flagselection);
        //  outputFSofFiber(actionList, layer, flagselection);


        // outputeventTS(actionList,layer, netname);
        //outputeventroute(actionList,layer, netname);
        // outputeNodepair(actionList,layer, netname);
        // outputeventTW(actionList,layer, netname);
        // outputeventrealcapacity(actionList,layer, netname);
        //  outputeventformedcapacity(actionList,layer,fibernumber,netname);

        double net_cost = net_cost(weightS, weightL, weightcourt, NodeNumber, g, F_g, Degreetotal, layer, resultofFSmax, laserInfoList, WSSInfoList);
        networkWeights.put("net_cost",net_cost);
     //   System.out.println(net_cost);
        return  getNetworkWeights();


/*        if(flagselection == 3){
            Traffic traffic = new Traffic(layer,1,random);
       //     traffic.setBlocknumer(0);
            layer.generateNodePairs();
            RouteSearching RS = new RouteSearching();
            RS.kShortestPath(layer, 1,true);
            layer.rankActions();
            List<Action> actionList = layer.getActionList();

        //    demandAllocationSWP( actionList, layer,traffic);
            traffic.setBlocknumer(0);
            fixedrouterrandomselectFSwindow(actionList, layer,traffic);
            traffic.printResult();
            traffic.setBlocknumer(0);}*/
  //

   //  fixedrouterfirstfitselectFSwindow(actionList, layer);
     //    RS.kShortestPath(layer, 1,true);
    }

    //P = 3*o+46*s*o;

    public  Map doactionAllocation (Traffic traffic, Network layer, Random random, int time, int flagselection, String netname, Integer fibernumber, Integer NodeNumber, Integer g,
                                    Integer F_g, int Degreetotal, double weightS, double weightL, double weightcourt, boolean flag, boolean resultofFSmax) {


//        for (int i = 0 ; i < traffic.getEventList().size(); i++){
//            connectionList.add(traffic.getEventList().get(i).getCon());
//        }
//        for (int i = 0; i < actionList.size(); i++){
//            System.out.println( actionList.get(i).getType());
//        }
        int Timeinsert =2;
        int PW = 0;

        //    traffic.setBlocknumer(0);
        layer.generateNodePairs();
        //     RouteSearching RS = new RouteSearching();
        //    RS.kShortestPath(layer, 1,true);
        //  layer.rankActions();
        List<Action> actionList = layer.getActionList();

//        for (Action e:actionList){
//            System. out.println(   e.getCon().getCapacity() + " ");
//        }
//        System. out.println(   " ~~~~");
//        layer.rankActions();
//
//        for (Action e:actionList){
//                     System. out.println(   e.getCon().getCapacity() + " ");
//        }

        //      Collections.shuffle(actionList);
        //     sortList = new ActionListSort(actionList,random).sortAction(random);


//           for (Action e:actionList){
//                      System. out.println(   e.getCon().getCapacity() + " ");
//             }
//
//            System. out.println( "~~~~~~~~~~~~~~~~~~~~~~~~ ");
//
//           for (Action e:sortList){
//               System. out.println(   e.getCon().getCapacity() + " ");
//            }
//             System. out.println( "~~~~~~~~~~~~~~~~~~~~~~~~ ");





        // demandAllocationSWP( actionList, layer,traffic);

        //      Collections.shuffle(actionList); //打乱


        ;
        //    for (Action a : actionList){
        //      Event E = a.getCon().getAssociateEvent();
        //    System. out.println(E.getCapacity() + " ");

        //   }
        System.out.println("------------------------------------");





        traffic.setBlocknumer(0);//first-fit选择路由
        //  timeslot_first
        traffic.setBandwidth(0.0);

        if (flag == false)

        {
            if(flagselection ==0){

                costselectTimeFSwindow(actionList, layer,traffic,fibernumber, g);
            }

            //  timeslot_first+fixed route
            if(flagselection ==1){

                fixedrouterfirstfitselectTimewindow(actionList, layer,traffic, fibernumber, g);

            }
            //  timeslot_first+update route
            if(flagselection ==2){

                updaterouterfirstfitselectTimewindow(actionList, layer,traffic, fibernumber, g);

            }




            //  SSW_first+fixed route
            if(flagselection ==3){

                fixedrouterfirstfitselectSSWwindow(actionList, layer,traffic, fibernumber, g);

            }

            //  SSW_first+update route
            if(flagselection ==4){

                updaterouterfirstfitselectSSWwindow(actionList, layer,traffic, fibernumber, g);

            }

            //  SSW_leastload+fixe route
            if(flagselection == 5){

                //     fixedrouterleastloadSSWwindow(actionList, layer,traffic, fibernumber, g);
                fixedrouterfirstfitselectTimewindowTestleastload(actionList, layer,traffic, fibernumber, g);
            }

            //  SSW_leastload+update route
            if(flagselection == 6){

                updaterouterleastloadSSWwindow(actionList, layer,traffic, fibernumber,g);

            }


            if(flagselection == 7){

                updaterouterleastloadSSWwindow(actionList, layer,traffic, fibernumber, g);

            }
        }

        else
        {

            if(flagselection ==0){

                costselectTimeFSwindow(  sortList, layer,traffic, fibernumber, g);
            }

            //  timeslot_first+fixed route
            if(flagselection ==1){

                fixedrouterfirstfitselectTimewindow(  sortList, layer,traffic, fibernumber, g);

            }
            //  timeslot_first+update route
            if(flagselection ==2){

                updaterouterfirstfitselectTimewindow(  sortList, layer,traffic, fibernumber, g);

            }




            //  SSW_first+fixed route
            if(flagselection ==3){

                fixedrouterfirstfitselectSSWwindow(  sortList, layer,traffic, fibernumber, g);

            }

            //  SSW_first+update route
            if(flagselection ==4){

                updaterouterfirstfitselectSSWwindow(  sortList, layer,traffic, fibernumber,g);

            }

            //  SSW_leastload+fixe route
            if(flagselection == 5){

                fixedrouterleastloadSSWwindow(  sortList, layer,traffic, fibernumber, g);

            }

            //  SSW_leastload+update route
            if(flagselection == 6){

                updaterouterleastloadSSWwindow(  sortList, layer,traffic, fibernumber, g);

            }


            if(flagselection == 7){

                updaterouterleastloadSSWwindow(  sortList, layer,traffic, fibernumber, g);

            }




        }





        PW = net_pwofTime( Timeinsert, layer);
        System.out.println("当前时隙"+Timeinsert+"网络总功耗"+PW);
   //     traffic.Result();

        traffic.printResult();
        traffic.printbandwidthBPResult();//  traffic.setBlocknumer(0);
        // outputAllTime( actionList , layer);
        //  outputeventformedcapacity(actionList, layer,Constant.fiberNum);
        // outputeventDL(actionList, layer,Constant.fiberNum);
        // outputFmaxofFiber(actionList, layer,flagselection);
        //  outputFSofFiber(actionList, layer, flagselection);


        // outputeventTS(actionList,layer, netname);
        //outputeventroute(actionList,layer, netname);
        // outputeNodepair(actionList,layer, netname);
        // outputeventTW(actionList,layer, netname);
        // outputeventrealcapacity(actionList,layer, netname);
        //  outputeventformedcapacity(actionList,layer,fibernumber,netname);

        double net_cost = net_cost(weightS, weightL, weightcourt, NodeNumber, g, F_g, Degreetotal, layer, resultofFSmax);
        networkWeights.put("net_cost",net_cost);
        //   System.out.println(net_cost);
        return  getNetworkWeights();


/*        if(flagselection == 3){
            Traffic traffic = new Traffic(layer,1,random);
       //     traffic.setBlocknumer(0);
            layer.generateNodePairs();
            RouteSearching RS = new RouteSearching();
            RS.kShortestPath(layer, 1,true);
            layer.rankActions();
            List<Action> actionList = layer.getActionList();

        //    demandAllocationSWP( actionList, layer,traffic);
            traffic.setBlocknumer(0);
            fixedrouterrandomselectFSwindow(actionList, layer,traffic);
            traffic.printResult();
            traffic.setBlocknumer(0);}*/
        //

        //  fixedrouterfirstfitselectFSwindow(actionList, layer);
        //    RS.kShortestPath(layer, 1,true);
    }

    public double laserCost(int g, int F, HashMap<int[] , Double> laserInfoList){
        double lasercost = 0;

        for(Map.Entry<int[] , Double> entry : laserInfoList.entrySet()){
             int[] laserarr = entry.getKey();

             if ((g ==  laserarr[0])&&( F == laserarr[1]) ) {

                 lasercost = entry.getValue();
                 break;

             }
        }



        return  lasercost;
    }



    public double WSSCost(int g, int F, HashMap<int[] , Double> WSSInfoList){
        double WSScost = 0;

        for(Map.Entry<int[] , Double> entry : WSSInfoList.entrySet()){
            int[] WSSrarr = entry.getKey();

            if ((g ==  WSSrarr[0])&&( F == WSSrarr[1]) ) {

                WSScost = entry.getValue();
                break;

            }
        }



        return  WSScost;
    }







    public int net_pwofTime(int Timeinsert, Network layer){
    int flag = 0;
    int Pw = 0;
        for(Fiber fiber:layer.getFiberList()){
                for(Wavelength wav:fiber.getTimeList().get(Timeinsert).getWavelengthList()) {
                        if (wav.getCapacity()==0){
                                flag = flag +1;
                        }
                }
        }
        Pw = flag*3+46*flag*Constant.g;
        return Pw;
    }



// nodeNum是节点总个数, g是超通道的粒度，F是光纤数， Degreetotal Degreetotal是网络中总的节点度，
    public double net_cost(Double weightL, Double weightS, Double weightcourt,int NodeNumber, int g, int F_g, int Degreetotal, Network layer, boolean resultofFSmax,    HashMap<int[] , Double> laserInfoList, HashMap<int[] , Double> WSSInfoList){
        double net_WSS;
        double net_FS = 0;
        double  net_deviceCost ;
        double  net_cost = 0.0;
        int d = Degreetotal/NodeNumber;
        ArrayList<Integer> FSmaxofTimeList = new ArrayList<>() ;
        Double laserNum = NodeNumber*F_g*laserCost(g,g*F_g, laserInfoList);
        double switchingNum = 2*g*Degreetotal*(WSSCost(F_g,2*F_g,WSSInfoList))+2*F_g*g*NodeNumber*WSSCost(d,d,WSSInfoList);
        net_deviceCost = switchingNum+ laserNum;



        for (Integer time=0;time< Constant.timetotalnum;time++) {

            //     bw.write(',');
            //     bw.write(String.valueOf(nodePair.getName()));
            int Pw = 0;
            int Fmax = 0;
            ArrayList<Integer> fuselist = new ArrayList<>();
            for(Fiber fiber:layer.getFiberList()){
                int flag = 0;
                for(Wavelength wav:fiber.getTimeList().get(time).getWavelengthList()) {
                    if (wav.getCapacity()==0){
                        flag = flag +1;
                    }
                }
                fiber.setFuseoutput(flag);
                fuselist.add(fiber.getFuseputput());
            }

            int max = findmaxbyfor(fuselist);
            FSmaxofTimeList.add(max);




        }

        for (int e: FSmaxofTimeList){
         //   System. out.println(  e + " ");
        }

      if (resultofFSmax==false)

           {
             double fsmaxofTimeSum =  Collections.max( FSmaxofTimeList); // （a1+a2）/2= avg, avg*2 = sum(a1+a2),
           //    double fsmaxofTimeSum =  sum( FSmaxofTimeList); // （a1+a2）/2= avg, avg*2 = sum(a1+a2),
           //    net_cost =    weightcourt * (double)fsmaxofTimeSum+  weightL*net_deviceCost ; // deviceCost已经乘以系数
               net_cost =   fsmaxofTimeSum ; // deviceCost已经乘以系数
               System. out.println( " fsmaxofTimeSum " + fsmaxofTimeSum );
               System. out.println(  " net_deviceCost"  + net_deviceCost );
               System. out.println(  "net_total"  +  net_cost );


           }

      else
          {

          //    int fsmaxofTimeSum =   sum( FSmaxofTimeList);
              int fsmaxofTimeSum =  findmax( FSmaxofTimeList);
              net_cost =    (double)fsmaxofTimeSum ;
           }


        return  net_cost ;
    }




    public double net_cost(Double weightL, Double weightS, Double weightcourt,int NodeNumber, int g, int F_g, int Degreetotal, Network layer, boolean resultofFSmax){
        double net_WSS;
        double net_FS = 0;
        double  net_deviceCost ;
        double  net_cost = 0.0;
        int d = Degreetotal/NodeNumber;
        ArrayList<Integer> FSmaxofTimeList = new ArrayList<>() ;
        int laserNum = NodeNumber*F_g;
        double switchingNum = 2*g*Degreetotal+2*F_g*g*NodeNumber;
        net_deviceCost = weightS*switchingNum+ weightL*laserNum;



        for (Integer time=0;time< Constant.timetotalnum;time++) {

            //     bw.write(',');
            //     bw.write(String.valueOf(nodePair.getName()));
            int Pw = 0;
            int Fmax = 0;
            ArrayList<Integer> fuselist = new ArrayList<>();
            for(Fiber fiber:layer.getFiberList()){
                int flag = 0;
                for(Wavelength wav:fiber.getTimeList().get(time).getWavelengthList()) {
                    if (wav.getCapacity()==0){
                        flag = flag +1;
                    }
                }
                fiber.setFuseoutput(flag);
                fuselist.add(fiber.getFuseputput());
            }

            int max = findmaxbyfor(fuselist);
            FSmaxofTimeList.add(max);




        }

        for (int e: FSmaxofTimeList){
            //   System. out.println(  e + " ");
        }

        if (resultofFSmax==false)

        {
            int fsmaxofTimeSum =   sum( FSmaxofTimeList); // （a1+a2）/2= avg, avg*2 = sum(a1+a2),
            net_cost =    (double)fsmaxofTimeSum +  net_deviceCost ;
            System. out.println(  fsmaxofTimeSum + " ");
            System. out.println(  net_deviceCost  + " ");
            System. out.println(  net_cost  + " ");


        }

        else
        {

            //    int fsmaxofTimeSum =   sum( FSmaxofTimeList);
            int fsmaxofTimeSum =  sum( FSmaxofTimeList);
            net_cost =    (double)fsmaxofTimeSum ;
        }

        FSmaxofTimeList.clear();
        return  net_cost ;
    }


    public  Integer sum(ArrayList<Integer> list) {
        Integer sum = 0;
        for (Integer d : list)  sum += d;
        return sum;
    }

    public  Integer findmax(ArrayList<Integer> list) {
      Integer max = Collections.max(list);
      return  max;
    }


    public void outputFSofFiber( List<Action> actionlist, Network layer, int flagselection) throws IOException{
        List<Action> lists = actionlist;
        //    for (int i = 0; i <lists.size(); i++) {
        //         Connection con = lists.get(i);
        File file = new File(
                "D:\\phd\\Projects\\Muti_fiber networks\\JAVA\\Static scenerio\\FSwinadaptivenodepair(improve)\\multiplefiber" +
                        "\\src\\main\\java\\com\\data"+ actionlist.size() + "个业务"+" 时隙使用FS"+ flagselection+"11-node.csv");
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
        for (Integer time=0;time< Constant.timetotalnum;time++) {

            //     bw.write(',');
            //     bw.write(String.valueOf(nodePair.getName()));
            int Pw = 0;
            int Fmax = 0;
            ArrayList<Integer> fuselist = new ArrayList<>();
            for(Fiber fiber:layer.getFiberList()){
                int flag = 0;
                for(Wavelength wav:fiber.getTimeList().get(time).getWavelengthList()) {
                    if (wav.getCapacity()==0){
                        flag = flag +1;
                    }
                }
                fiber.setFuseoutput(flag);
                fuselist.add(fiber.getFuseputput());
                bw.write("时隙"+time+"网络中"+  fiber.getName()+"使用频隙数为"+fiber.getFuseputput() );
                bw.write('\n'); }





        }
        //            bw.write("=============================");
        bw.flush();
        bw.close();
        //

    }



    public  void outputFmaxofFiber( List<Action> actionlist, Network layer, int flagselection) throws IOException {
        List<Action> lists = actionlist;
        //    for (int i = 0; i <lists.size(); i++) {
        //         Connection con = lists.get(i);
        File file = new File(
                "D:\\phd\\Projects\\Muti_fiber networks\\JAVA\\Static scenerio\\FSwinadaptivenodepair(improve)\\multiplefiber" +
                        "\\src\\main\\java\\com\\data"+ actionlist.size() + "个业务"+" 时隙下最大Fmax值"+ flagselection+"11-node.csv");
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
        for (Integer time=0;time< Constant.timetotalnum;time++) {

            //     bw.write(',');
            //     bw.write(String.valueOf(nodePair.getName()));
            int Pw = 0;
            int Fmax = 0;
            ArrayList<Integer> fuselist = new ArrayList<>();
            for(Fiber fiber:layer.getFiberList()){
                int flag = 0;
                for(Wavelength wav:fiber.getTimeList().get(time).getWavelengthList()) {
                    if (wav.getCapacity()==0){
                        flag = flag +1;
                    }
                }
                fiber.setFuseoutput(flag);
                fuselist.add(fiber.getFuseputput());
            }

            int max = findmaxbyfor(fuselist);
            String numStr = "";
            numStr = String.valueOf(max);
            bw.write( numStr  );

            bw.write('\n');
        }
        //            bw.write("=============================");
        bw.flush();
        bw.close();
        //
    }



   public int findmaxbyfor(  ArrayList<Integer> fuselist){
        int max = 0;
        for (int item:fuselist){
            if( item >= max){
                max = item;
            }
        }
        return max;
   }


    public int net_pwofTimeoffiber(Time time, Fiber fiber, Network layer){
        int flag = 0;
        int Pw = 0;

            for(Wavelength wav:fiber.getTimeList().get(time.getIndex()).getWavelengthList()) {
                if (wav.getCapacity()==0){
                    flag = flag +1;
                }
            }

        Pw = flag*3+46*flag*Constant.g;
        return Pw;
    }


    public  void outputAllTime( List<Action> actionlist, Network layer) throws IOException {
        List<Action> lists = actionlist;
    //    for (int i = 0; i <lists.size(); i++) {
   //         Connection con = lists.get(i);
            File file = new File(
                    "D:\\phd\\Projects\\Muti_fiber networks\\JAVA\\Static scenerio\\FSwinadaptivenodepair(improve)\\multiplefiber" +
                            "\\src\\main\\java\\com\\data"+ actionlist.size() + "个业务"+" 时隙11-node.csv");
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
            for (Action action:actionlist) {
                bw.write(action.getCon().getSrc().getName()+"-"+action.getCon().getDest().getName()+","+
                        action.getCon().getStartTime()+","+action.getCon().getKeepTime()+","+action.getCon().getEndTime());
           //     bw.write(',');
           //     bw.write(String.valueOf(nodePair.getName()));
                bw.write('\n');
            }
            //            bw.write("=============================");
            bw.flush();
            bw.close();
     //
    }


    public  void outputeventroute( List<Action> actionlist, Network layer,String networkname) throws IOException {
        List<Action> lists = actionlist;
        //    for (int i = 0; i <lists.size(); i++) {
        //         Connection con = lists.get(i);
        File file = new File(
                "D:\\phd\\Projects\\Muti_fiber networks\\JAVA\\Static scenerio\\FSwinadaptivenodepair(improve)\\multiplefiber" +
                        "\\src\\main\\java\\com\\data"+ actionlist.size()  + "个业务"+"P[r]"+ networkname+".csv");
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
        for (Action action:actionlist) {
            bw.write("set P["+action.getCon().getSrc().getName()+"-"+action.getCon().getDest().getName()+"-"+action.getCon().getIndex()+"] :=");
             for ( int i = 0; i<action.getCon().getNodePair().getLinearroutelist().get(0).getFiberlist().size();i ++){
            bw.write(" "+action.getCon().getNodePair().getLinearroutelist().get(0).getFiberlist().get(i).getSrcNode().getName()+"-"
                    + action.getCon().getNodePair().getLinearroutelist().get(0).getFiberlist().get(i).getDestNode().getName());
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

    public  void outputeventTS( List<Action> actionlist, Network layer,String networkname) throws IOException {
        //    for (int i = 0; i <lists.size(); i++) {
        //         Connection con = lists.get(i);
        File file = new File("D:\\phd\\Projects\\Muti_fiber networks\\JAVA\\Static scenerio\\FSwinadaptivenodepair(improve)\\multiplefiber" +
                "\\src\\main\\java\\com"+ actionlist.size() + "个业务"+"TS"+ networkname+".csv");
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
        for (Action action:actionlist) {

            for ( int i =0; i+(int)action.getCon().getStartTime()+(int)action.getCon().getKeepTime()-1<=action.getCon().getEndTime();i ++){
                int starttime =(int)action.getCon().getStartTime()+i;
                int endtime = (int)action.getCon().getStartTime()+i+(int)action.getCon().getKeepTime()-1;

                bw.write("set TS["+action.getCon().getSrc().getName()+"-"+action.getCon().getDest().getName()+"-"+action.getCon().getIndex());
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

    public  void outputeventformedcapacity(List<Action> actionList, Network layer, int fibernumber, String networkname) throws IOException {

        HashMap <String, Integer> fiberNumMap = new HashMap<>();
        ArrayList<String> CList = new ArrayList<>();
        CList.add("C1");
        CList.add("C2");
        CList.add("C3");
        CList.add("C6");
        fiberNumMap.put("C1",1);
        fiberNumMap.put("C2",2);
        fiberNumMap.put("C3",3);
        fiberNumMap.put("C6",6);
        //    for (int i = 0; i <lists.size(); i++) {
        //         Connection con = lists.get(i);
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


//    public  void outputeventrealcapacity( List<Action> actionlist, Network layer) throws IOException {
//        List<Action> lists = actionlist;
//        //    for (int i = 0; i <lists.size(); i++) {
//        //         Connection con = lists.get(i);
//        File file = new File("D:\\phd\\Projects\\Muti_fiber networks\\JAVA\\Static scenerio\\FSwinadaptivenodepair\\multiplefiber" +
//                "\\src\\main\\java\\com\\data"+ actionlist.size() + "个业务"+"dr11-node.csv");
//        if (file.exists()) {
//            if (!file.delete()) {
//                throw new IllegalArgumentException("初始化文件失败");
//            }
//        }
//        if (!file.createNewFile()) {
//            throw new IllegalArgumentException("创建文件失败");
//        }
//        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
//        bw.write("节点对,开始时间,持续时间,结束时间\n");
//        for (Action action:actionlist) {
//
//
//            bw.write(action.getCon().getSrc().getName()+"-"+action.getCon().getDest().getName());
//            //  int cap = (int)Math.ceil(action.getCon().getCapacity()/Constant.wavCapacity/Constant.g);
//            int cap = (int)Math.ceil(action.getCon().getCapacity()/Constant.wavCapacity);
//            bw.write(" "+cap);
//            bw.write(";");
//            bw.write('\n');
//            //     bw.write(String.valueOf(nodePair.getName()));
//
//        }
//        //            bw.write("=============================");
//        bw.flush();
//        bw.close();
//        //   }
//    }
//


//    public  void outputeventformedcapacity( List<Action> actionlist, Network layer, int fibernumber) throws IOException {
//        List<Action> lists = actionlist;
//
//        ArrayList<String>CList = new ArrayList<>();
//        CList.add("C1");
//        CList.add("C2");
//        CList.add("C4");
//        //    for (int i = 0; i <lists.size(); i++) {
//        //         Connection con = lists.get(i);
//        File file = new File("D:\\phd\\Projects\\Muti_fiber networks\\JAVA\\Static scenerio\\FSwinadaptivenodepair\\multiplefiber" +
//                "\\src\\main\\java\\com\\data"+ actionlist.size() + "个业务"+"Rr11-node.csv");
//        if (file.exists()) {
//            if (!file.delete()) {
//                throw new IllegalArgumentException("初始化文件失败");
//            }
//        }
//        if (!file.createNewFile()) {
//            throw new IllegalArgumentException("创建文件失败");
//        }
//        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
//        bw.write("节点对,开始时间,持续时间,结束时间\n");
//
//
//
//        for (int i = 1; i <=CList.size();i++){
//
//            for (Action action:actionlist) {
//            //    int num = Constant.fiberNum;
//
//                  bw.write(fibernumber+ ",");//Constant.fiber
//                  bw.write(CList.get(i) + ","); //Constant.g
//                  bw.write(action.getCon().getSrc().getName() + "-" + action.getCon().getDest().getName() + ",");
//                  int cap = (int) Math.ceil(action.getCon().getCapacity() / Constant.wavCapacity / i);
//                  bw.write(" " + cap + ",");
//                 bw.write(";");
//                  bw.write('\n');
//
//            }
//
//        }
//
//        //  int cap = (int)Math.ceil(action.getCon().getCapacity()/Constant.wavCapacity/Constant.g);
//
//
//        //     bw.write(String.valueOf(nodePair.getName()));
//
//
//        //            bw.write("=============================");
//        bw.flush();
//        bw.close();
//        //   }
//    }

    public  void outputeventDL( List<Action> actionlist, Network layer, int fibernumber) throws IOException {
        List<Action> lists = actionlist;
        //    for (int i = 0; i <lists.size(); i++) {
        //         Connection con = lists.get(i);
        File file = new File("D:\\phd\\Projects\\Muti_fiber networks\\JAVA\\Static scenerio\\FSwinadaptivenodepair(improve)\\multiplefiber" +
                "\\src\\main\\java\\com\\data"+ actionlist.size() + "个业务"+"DL11-node.csv");
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



        for (int i = 1; i <=fibernumber;i++){

            for (Action action:actionlist) {
                //    int num = Constant.fiberNum;



                for ( int j = 0; j<action.getCon().getRouteList().size();j ++){
                    bw.write(fibernumber+ ",");//Constant.fiber
                    bw.write("C" + i + ","); //Constant.g
                    bw.write(action.getCon().getSrc().getName() + "-" + action.getCon().getDest().getName() + ",");
                    bw.write(" "+action.getCon().getRouteList().get(j).getSrcNode().getName()+"-"+action.getCon().getRouteList().get(j).getDestNode().getName());
                    //     bw.write(String.valueOf(nodePair.getName()));
                   int flag = 0;
                    if (action.getCon().getRouteList().get(j).getSrcNode().getIndex()<action.getCon().getRouteList().get(j).getDestNode().getIndex())
                    {flag =1;}
                    else{flag =2;}
                    bw.write(" "+flag);
                    bw.write('\n');
               }



            }

        }

        //  int cap = (int)Math.ceil(action.getCon().getCapacity()/Constant.wavCapacity/Constant.g);


        //     bw.write(String.valueOf(nodePair.getName()));


        //            bw.write("=============================");
        bw.flush();
        bw.close();
        //   }
    }





/*    public void demandAllocationSWP(  List<Action> actionList, Network layer, Traffic traffic){
//        LinearRoute route = new LinearRoute("",0);
//        RouteSearching RS = new RouteSearching() ;
//        RS.Dijkstra(connection.getNodePair().getSrcNode(),connection.getNodePair().getDesNode(),layer,route,null);

        for (int j= 0 ; j<actionList.size(); j++){

         //   Connection connection = actionList.get(j).getCon();
            //addConnection(connection);
        String name = actionList.get(j).getCon().getSrc().getName()+"-"+actionList.get(j).getCon().getDest().getName();
        NodePair nodePair = layer.getNodepairMap().get(name);
        int  FSrequirednumber= (int)Math.ceil(actionList.get(j).getCon().getCapacity()/Constant.wavCapacity/Constant.g);
        LinearRoute initRoute = nodePair.getLinearroutelist().get(0);
          LinearRoute route = new LinearRoute(initRoute);

        nodePair.setUsedRoute(route);
        nodePair.getUsedLightPath().setPhysicPath(route); // build lightpaths according to the OSNR
        LightPath lightPath = nodePair.getUsedLightPath();
        lightPath.setRequiredSlotNum(FSrequirednumber);
        doFSPlane doFSwindowPlane =  new doFSPlane();
        ArrayList<FSRoute> slotWindows = doFSwindowPlane.GenerateFSPlane(layer,FSrequirednumber);

        if ( !doFSwindowPlane.findandassigavaFSwindow(actionList.get(j).getCon(), nodePair,lightPath,slotWindows, layer )){ // 这边是否问题是否要遍历所有波长
            actionList.get(j).getCon().setState(Constant.blocking);
            Constant.BlockNum++;
            traffic.setBlocknumer(traffic.getBlocknumer()+1);}

                if (actionList.get(j).getCon().getState() != Constant.blocking) {
                    actionList.get(j).getCon().setState(Constant.doing);
                }


      }
    }*/


    public void   costselectTimeFSwindow ( List<Action> actionList, Network layer, Traffic traffic, int fiberNumber, int g){
//        LinearRoute route = new LinearRoute("",0);
//        RouteSearching RS = new RouteSearching() ;
//        RS.Dijkstra(connection.getNodePair().getSrcNode(),connection.getNodePair().getDesNode(),layer,route,null);

        for (int j= 0 ; j<actionList.size(); j++){

            Connection connection = actionList.get(j).getCon();
            //addConnection(connection);   这个固定路由算法寻找，光纤index限制了，这个要注意，least-load算法比较适用。
            //不同时间间隙，判断在不同的时间间隙分配频谱，从而使每段的时间间隙中超级通道中FS最少。
            String name = connection.getNodePair().getName();
            NodePair nodePair = layer.getNodepairMap().get(name);
            int  FSrequirednumber= (int)Math.ceil(connection.getCapacity()/Constant.wavCapacity/g);
            LinearRoute initRoute = nodePair.getLinearroutelist().get(0);
            LinearRoute route = new LinearRoute(initRoute);
            nodePair.setUsedRoute(route);
            nodePair.getUsedLightPath().setPhysicPath(route); // build lightpaths according to the OSNR
            LightPath lightPath = nodePair.getUsedLightPath();
            lightPath.setRequiredSlotNum(FSrequirednumber);
            doFSPlane doFSwindowPlane =  new doFSPlane();

            //     ArrayList<FSRoute> slotWindows = doFSwindowPlane.GenerateFSPlane(layer,FSrequirednumber);
            //     ArrayList<Timeroute>timeWindows = doFSwindowPlane.GenerateTimePlane(layer,connection.getKeepTime(),slotWindows);

            HashMap<Timeroute,ArrayList<FSRoute>> TimeFSWindowsHashMap = doFSwindowPlane.GenerateTimeFSPlane(layer,connection,FSrequirednumber,fiberNumber,g);

            if (j == 0){
                doFSwindowPlane.allocationstartup(connection, nodePair,lightPath,  TimeFSWindowsHashMap ,layer);
            }
            else{
                doFSwindowPlane.Time_FStimeallocate(connection, nodePair,lightPath,  TimeFSWindowsHashMap ,layer);}


        }
    }


    public void  fixedrouterfirstfitselectSSWwindow ( List<Action> actionList, Network layer, Traffic traffic, int fiberNumber, int g){
//        LinearRoute route = new LinearRoute("",0);
//        RouteSearching RS = new RouteSearching() ;
//        RS.Dijkstra(connection.getNodePair().getSrcNode(),connection.getNodePair().getDesNode(),layer,route,null);

        for (int j= 0 ; j<actionList.size(); j++){

            Connection connection = actionList.get(j).getCon();
            //addConnection(connection);   这个固定路由算法寻找，光纤index限制了，这个要注意，least-load算法比较适用。
            //不同时间间隙，判断在不同的时间间隙分配频谱，从而使每段的时间间隙中超级通道中FS最少。
            String name = connection.getNodePair().getName();
            NodePair nodePair = layer.getNodepairMap().get(name);
            int  FSrequirednumber= (int)Math.ceil(connection.getCapacity()/Constant.wavCapacity/g);
            LinearRoute initRoute = nodePair.getLinearroutelist().get(0);
            LinearRoute route = new LinearRoute(initRoute);
            nodePair.setUsedRoute(route);
            nodePair.getUsedLightPath().setPhysicPath(route); // build lightpaths according to the OSNR
            LightPath lightPath = nodePair.getUsedLightPath();
            lightPath.setRequiredSlotNum(FSrequirednumber);
            doFSPlane doFSwindowPlane =  new doFSPlane();

            //     ArrayList<FSRoute> slotWindows = doFSwindowPlane.GenerateFSPlane(layer,FSrequirednumber);
            //     ArrayList<Timeroute>timeWindows = doFSwindowPlane.GenerateTimePlane(layer,connection.getKeepTime(),slotWindows);

            HashMap<Timeroute,ArrayList<FSRoute>> TimeFSWindowsHashMap = doFSwindowPlane.GenerateTimeFSPlane(layer,connection, lightPath.getRequiredSlotNum(),fiberNumber,g);

            if ( !doFSwindowPlane.containTimeFSWindowrouteSSWfirst(connection, nodePair,lightPath,  TimeFSWindowsHashMap ,layer))
            {  connection.setState(Constant.blocking);
                Constant.BlockNum++;
                traffic.setBlocknumer(traffic.getBlocknumer()+1);

                Constant.bandwidth=  Constant.bandwidth+connection.getCapacity();
                traffic.setBandwidth(traffic.getBandwidth()+connection.getCapacity());
            }

            //   lightPath.release();//我觉得这边有问题 没有改变


            if (connection.getState() != Constant.blocking) {
                connection.setState(Constant.doing);
            }


        }
    }

    public void  updaterouterfirstfitselectSSWwindow ( List<Action> actionList, Network layer, Traffic traffic, int fiberNumber, int g){
//        LinearRoute route = new LinearRoute("",0);
//        RouteSearching RS = new RouteSearching() ;
//        RS.Dijkstra(connection.getNodePair().getSrcNode(),connection.getNodePair().getDesNode(),layer,route,null);

        for (int j= 0 ; j<actionList.size(); j++){

            Connection connection = actionList.get(j).getCon();
            //addConnection(connection);   这个固定路由算法寻找，光纤index限制了，这个要注意，least-load算法比较适用。
            //不同时间间隙，判断在不同的时间间隙分配频谱，从而使每段的时间间隙中超级通道中FS最少。
            String name = connection.getNodePair().getName();
            NodePair nodePair = layer.getNodepairMap().get(name);
            int  FSrequirednumber= (int)Math.ceil(connection.getCapacity()/Constant.wavCapacity/g);
            LinearRoute initRoute = nodePair.getLinearroutelist().get(0);
            LinearRoute route = new LinearRoute(initRoute);
            nodePair.setUsedRoute(route);
            nodePair.getUsedLightPath().setPhysicPath(route); // build lightpaths according to the OSNR
            LightPath lightPath = nodePair.getUsedLightPath();
            lightPath.setRequiredSlotNum(FSrequirednumber);
            doFSPlane doFSwindowPlane =  new doFSPlane();

            //     ArrayList<FSRoute> slotWindows = doFSwindowPlane.GenerateFSPlane(layer,FSrequirednumber);
            //     ArrayList<Timeroute>timeWindows = doFSwindowPlane.GenerateTimePlane(layer,connection.getKeepTime(),slotWindows);

            HashMap<Timeroute,ArrayList<FSRoute>> TimeFSWindowsHashMap = doFSwindowPlane.GenerateTimeFSPlane(layer,connection, lightPath.getRequiredSlotNum(),fiberNumber,g);

            if ( !doFSwindowPlane.containupdateTimeFSWindowrouteSSWfirst(connection, nodePair,lightPath,  TimeFSWindowsHashMap ,layer))
            {  connection.setState(Constant.blocking);
                Constant.BlockNum++;
                traffic.setBlocknumer(traffic.getBlocknumer()+1);

                Constant.bandwidth=  Constant.bandwidth+connection.getCapacity();
                traffic.setBandwidth(traffic.getBandwidth()+connection.getCapacity());
            }

            //   lightPath.release();//我觉得这边有问题 没有改变


            if (connection.getState() != Constant.blocking) {
                connection.setState(Constant.doing);
            }


        }
    }







    public void  fixedrouterleastloadSSWwindow ( List<Action> actionList, Network layer, Traffic traffic, int fiberNumber, int g){
//        LinearRoute route = new LinearRoute("",0);
//        RouteSearching RS = new RouteSearching() ;
//        RS.Dijkstra(connection.getNodePair().getSrcNode(),connection.getNodePair().getDesNode(),layer,route,null);

        for (int j= 0 ; j<actionList.size(); j++){

            Connection connection = actionList.get(j).getCon();
            //addConnection(connection);   这个固定路由算法寻找，光纤index限制了，这个要注意，least-load算法比较适用。
            //不同时间间隙，判断在不同的时间间隙分配频谱，从而使每段的时间间隙中超级通道中FS最少。
            String name = connection.getNodePair().getName();
            NodePair nodePair = layer.getNodepairMap().get(name);
            int  FSrequirednumber= (int)Math.ceil(connection.getCapacity()/Constant.wavCapacity/g);
            LinearRoute initRoute = nodePair.getLinearroutelist().get(0);
            LinearRoute route = new LinearRoute(initRoute);
            nodePair.setUsedRoute(route);
            nodePair.getUsedLightPath().setPhysicPath(route); // build lightpaths according to the OSNR
            LightPath lightPath = nodePair.getUsedLightPath();
            lightPath.setRequiredSlotNum(FSrequirednumber); //lightpath中包含原始业务的信息量，包括固定路由拓扑，nodepair，requiredFSs.
            doFSPlane doFSwindowPlane =  new doFSPlane();

            //     ArrayList<FSRoute> slotWindows = doFSwindowPlane.GenerateFSPlane(layer,FSrequirednumber);
            //     ArrayList<Timeroute>timeWindows = doFSwindowPlane.GenerateTimePlane(layer,connection.getKeepTime(),slotWindows);
            LinearRoute intRoute =   nodePair.getLinearroutelist().get(0) ;
            LinearRoute newRoutefixedfiberall = new LinearRoute(intRoute);
            RouteSearching routesearching = new RouteSearching();
            routesearching.DijkstraallfibeList(nodePair.getSrcNode(), nodePair.getDesNode(), layer, newRoutefixedfiberall , null);
            // 这边每个FSroute可以只添加当前固定路由下的fiberlist
            HashMap<Timeroute,ArrayList<FSRoute>> TimeFSWindowsHashMap = doFSwindowPlane.GenerateTWSSW(layer,connection, lightPath.getRequiredSlotNum(),fiberNumber,g, newRoutefixedfiberall.getFiberlist());

        //    if (j == 0){
      //          doFSwindowPlane.allocationstartup(connection, nodePair,lightPath,  TimeFSWindowsHashMap ,layer);
      //      }
        //    else{

            if ( !doFSwindowPlane.containTimeFSWindowrouteSSWleastload(connection, nodePair,lightPath,  TimeFSWindowsHashMap ,layer))
              {  connection.setState(Constant.blocking);
                Constant.BlockNum++;
                traffic.setBlocknumer(traffic.getBlocknumer()+1);
             }
           // }

            //   lightPath.release();//我觉得这边有问题 没有改变

            if (connection.getState() != Constant.blocking) {
                connection.setState(Constant.doing);
            }


        }
    }


    public void  updaterouterleastloadSSWwindow ( List<Action> actionList, Network layer, Traffic traffic, int fiberNumber, int g){
//        LinearRoute route = new LinearRoute("",0);
//        RouteSearching RS = new RouteSearching() ;
//        RS.Dijkstra(connection.getNodePair().getSrcNode(),connection.getNodePair().getDesNode(),layer,route,null);

        for (int j= 0 ; j<actionList.size(); j++){

            Connection connection = actionList.get(j).getCon();
            //addConnection(connection);   这个固定路由算法寻找，光纤index限制了，这个要注意，least-load算法比较适用。
            //不同时间间隙，判断在不同的时间间隙分配频谱，从而使每段的时间间隙中超级通道中FS最少。
            String name = connection.getNodePair().getName();
            NodePair nodePair = layer.getNodepairMap().get(name);
            int  FSrequirednumber= (int)Math.ceil(connection.getCapacity()/Constant.wavCapacity/g);
            LinearRoute initRoute = nodePair.getLinearroutelist().get(0);
            LinearRoute route = new LinearRoute(initRoute);
            nodePair.setUsedRoute(route);
            nodePair.getUsedLightPath().setPhysicPath(route); // build lightpaths according to the OSNR
            LightPath lightPath = nodePair.getUsedLightPath();
            lightPath.setRequiredSlotNum(FSrequirednumber);
            doFSPlane doFSwindowPlane =  new doFSPlane();

            //     ArrayList<FSRoute> slotWindows = doFSwindowPlane.GenerateFSPlane(layer,FSrequirednumber);
            //     ArrayList<Timeroute>timeWindows = doFSwindowPlane.GenerateTimePlane(layer,connection.getKeepTime(),slotWindows);

            HashMap<Timeroute,ArrayList<FSRoute>> TimeFSWindowsHashMap = doFSwindowPlane.GenerateTimeFSPlane(layer,connection, lightPath.getRequiredSlotNum(),fiberNumber,g);

            //    if (j == 0){
            //          doFSwindowPlane.allocationstartup(connection, nodePair,lightPath,  TimeFSWindowsHashMap ,layer);
            //      }
            //    else{

            if ( !doFSwindowPlane.containupdateTimeFSWindowrouteSSWleastload(connection, nodePair,lightPath,  TimeFSWindowsHashMap ,layer))
            {  connection.setState(Constant.blocking);
                Constant.BlockNum++;
                traffic.setBlocknumer(traffic.getBlocknumer()+1);

                Constant.bandwidth=  Constant.bandwidth+connection.getCapacity();
                traffic.setBandwidth(traffic.getBandwidth()+connection.getCapacity());
            }
            // }

            //   lightPath.release();//我觉得这边有问题 没有改变

            if (connection.getState() != Constant.blocking) {
                connection.setState(Constant.doing);
            }


        }
    }




    public void  fixedrouterfirstfitselectTimewindow ( List<Action> actionList, Network layer, Traffic traffic, int fiberNumber, int g){
//        LinearRoute route = new LinearRoute("",0);
//        RouteSearching RS = new RouteSearching() ;
//        RS.Dijkstra(connection.getNodePair().getSrcNode(),connection.getNodePair().getDesNode(),layer,route,null);

        for (int j= 0 ; j<actionList.size(); j++){

            Connection connection = actionList.get(j).getCon();
            //addConnection(connection);   这个固定路由算法寻找，光纤index限制了，这个要注意，least-load算法比较适用。
            //不同时间间隙，判断在不同的时间间隙分配频谱，从而使每段的时间间隙中超级通道中FS最少。
            String name = connection.getNodePair().getName();
            NodePair nodePair = layer.getNodepairMap().get(name);
            int  FSrequirednumber= (int)Math.ceil(connection.getCapacity()/Constant.wavCapacity/g);
            LinearRoute initRoute = nodePair.getLinearroutelist().get(0);
            LinearRoute route = new LinearRoute(initRoute);
            nodePair.setUsedRoute(route);
            nodePair.getUsedLightPath().setPhysicPath(route); // build lightpaths according to the OSNR
            LightPath lightPath = nodePair.getUsedLightPath();
            lightPath.setRequiredSlotNum(FSrequirednumber);
            doFSPlane doFSwindowPlane =  new doFSPlane();

            //     ArrayList<FSRoute> slotWindows = doFSwindowPlane.GenerateFSPlane(layer,FSrequirednumber);
            //     ArrayList<Timeroute>timeWindows = doFSwindowPlane.GenerateTimePlane(layer,connection.getKeepTime(),slotWindows);

                HashMap<Timeroute,ArrayList<FSRoute>> TimeFSWindowsHashMap = doFSwindowPlane.GenerateTimeFSPlane(layer,connection, lightPath.getRequiredSlotNum(),fiberNumber,g);

                if ( !doFSwindowPlane.containTimeFSWindowrouteTWfirst(connection, nodePair,lightPath,  TimeFSWindowsHashMap ,layer))
                {   connection.setState(Constant.blocking);
                    Constant.BlockNum++;
                    traffic.setBlocknumer(traffic.getBlocknumer()+1);
                    Constant.bandwidth=  Constant.bandwidth+connection.getCapacity();
                    traffic.setBandwidth(traffic.getBandwidth()+connection.getCapacity());

                }

                    //   lightPath.release();//我觉得这边有问题 没有改变


                if (connection.getState() != Constant.blocking) {
                    connection.setState(Constant.doing);
                }


        }
    }


    public void  fixedrouterfirstfitselectTimewindowTestleastload( List<Action> actionList, Network layer, Traffic traffic, int fiberNumber, int g){
//        LinearRoute route = new LinearRoute("",0);
//        RouteSearching RS = new RouteSearching() ;
//        RS.Dijkstra(connection.getNodePair().getSrcNode(),connection.getNodePair().getDesNode(),layer,route,null);

        for (int j= 0 ; j<actionList.size(); j++){

            Connection connection = actionList.get(j).getCon();
            //addConnection(connection);   这个固定路由算法寻找，光纤index限制了，这个要注意，least-load算法比较适用。
            //不同时间间隙，判断在不同的时间间隙分配频谱，从而使每段的时间间隙中超级通道中FS最少。
            String name = connection.getNodePair().getName();
            NodePair nodePair = layer.getNodepairMap().get(name);
            int  FSrequirednumber= (int)Math.ceil(connection.getCapacity()/Constant.wavCapacity/g);
            LinearRoute initRoute = nodePair.getLinearroutelist().get(0);
            LinearRoute route = new LinearRoute(initRoute);
            nodePair.setUsedRoute(route);
            nodePair.getUsedLightPath().setPhysicPath(route); // build lightpaths according to the OSNR
            LightPath lightPath = nodePair.getUsedLightPath();
            lightPath.setRequiredSlotNum(FSrequirednumber);
            doFSPlane doFSwindowPlane =  new doFSPlane();

            //     ArrayList<FSRoute> slotWindows = doFSwindowPlane.GenerateFSPlane(layer,FSrequirednumber);
            //     ArrayList<Timeroute>timeWindows = doFSwindowPlane.GenerateTimePlane(layer,connection.getKeepTime(),slotWindows);

            HashMap<Timeroute,ArrayList<FSRoute>> TimeFSWindowsHashMap = doFSwindowPlane.GenerateTimeFSPlane(layer,connection, lightPath.getRequiredSlotNum(),fiberNumber,g);

            if ( !doFSwindowPlane.containupdateTimeFSWindowrouteTest(connection, nodePair,lightPath,  TimeFSWindowsHashMap ,layer))
            {  connection.setState(Constant.blocking);
                Constant.BlockNum++;
                traffic.setBlocknumer(traffic.getBlocknumer()+1);


                Constant.bandwidth=  Constant.bandwidth+connection.getCapacity();
                traffic.setBandwidth(traffic.getBandwidth()+connection.getCapacity());
            }

            //   lightPath.release();//我觉得这边有问题 没有改变


            if (connection.getState() != Constant.blocking) {
                connection.setState(Constant.doing);
            }


        }
    }



    public void  updaterouterfirstfitselectTimewindow ( List<Action> actionList, Network layer, Traffic traffic, int fiberNumber, int g){
//        LinearRoute route = new LinearRoute("",0);
//        RouteSearching RS = new RouteSearching() ;
//        RS.Dijkstra(connection.getNodePair().getSrcNode(),connection.getNodePair().getDesNode(),layer,route,null);

        for (int j= 0 ; j<actionList.size(); j++){

            Connection connection = actionList.get(j).getCon();
            //addConnection(connection);   这个固定路由算法寻找，光纤index限制了，这个要注意，least-load算法比较适用。
            //不同时间间隙，判断在不同的时间间隙分配频谱，从而使每段的时间间隙中超级通道中FS最少。
            String name = connection.getNodePair().getName();
            NodePair nodePair = layer.getNodepairMap().get(name);
            int  FSrequirednumber= (int)Math.ceil(connection.getCapacity()/Constant.wavCapacity/Constant.g);
            LinearRoute initRoute = nodePair.getLinearroutelist().get(0);
            LinearRoute route = new LinearRoute(initRoute);
            nodePair.setUsedRoute(route);
            nodePair.getUsedLightPath().setPhysicPath(route); // build lightpaths according to the OSNR
            LightPath lightPath = nodePair.getUsedLightPath();
            lightPath.setRequiredSlotNum(FSrequirednumber);
            doFSPlane doFSwindowPlane =  new doFSPlane();

            //     ArrayList<FSRoute> slotWindows = doFSwindowPlane.GenerateFSPlane(layer,FSrequirednumber);
            //     ArrayList<Timeroute>timeWindows = doFSwindowPlane.GenerateTimePlane(layer,connection.getKeepTime(),slotWindows);

            HashMap<Timeroute,ArrayList<FSRoute>> TimeFSWindowsHashMap = doFSwindowPlane.GenerateTimeFSPlane(layer,connection, lightPath.getRequiredSlotNum(),fiberNumber,g);

            if ( !doFSwindowPlane.containupdateTimeFSWindowrouteTWfirst(connection, nodePair,lightPath,  TimeFSWindowsHashMap ,layer))
            {  connection.setState(Constant.blocking);
                Constant.BlockNum++;
                traffic.setBlocknumer(traffic.getBlocknumer()+1);


                Constant.bandwidth=  Constant.bandwidth+connection.getCapacity();
                traffic.setBandwidth(traffic.getBandwidth()+connection.getCapacity());
            }

            //   lightPath.release();//我觉得这边有问题 没有改变


            if (connection.getState() != Constant.blocking) {
                connection.setState(Constant.doing);
            }


        }
    }





/*    public void  fixedrouterrandomselectFSwindow (  List<Action> actionList, Network layer,Traffic traffic){
//        LinearRoute route = new LinearRoute("",0);
//        RouteSearching RS = new RouteSearching() ;
//        RS.Dijkstra(connection.getNodePair().getSrcNode(),connection.getNodePair().getDesNode(),layer,route,null);

        for (int j= 0 ; j<actionList.size(); j++){

            Connection connection = actionList.get(j).getCon();
            //addConnection(connection);
            String name = connection.getSrc().getName()+"-"+connection.getDest().getName();
            NodePair nodePair = layer.getNodepairMap().get(name);
            int  FSrequirednumber= (int)Math.ceil(connection.getCapacity()/Constant.wavCapacity/Constant.g);
            LinearRoute initRoute = nodePair.getLinearroutelist().get(0);
            LinearRoute route = new LinearRoute(initRoute);
            nodePair.setUsedRoute(route);
            nodePair.getUsedLightPath().setPhysicPath(route); // build lightpaths according to the OSNR
            LightPath lightPath = nodePair.getUsedLightPath();
            lightPath.setRequiredSlotNum(FSrequirednumber);
            doFSPlane doFSwindowPlane =  new doFSPlane();

                ArrayList<FSRoute> slotWindows = doFSwindowPlane.GenerateFSPlane(layer,FSrequirednumber);
                if ( !doFSwindowPlane.containrandomWindowroute(connection, nodePair,lightPath,slotWindows,layer))
                {  connection.setState(Constant.blocking);
                    Constant.BlockNum++;
                    traffic.setBlocknumer(traffic.getBlocknumer()+1);}

                //   lightPath.release();//我觉得这边有问题 没有改变


                if (connection.getState() != Constant.blocking) {
                    connection.setState(Constant.doing);
                }


        }
    }*/

    public Map<String, Double> getNetworkWeights() {
        return networkWeights;
    }

    public void setNetworkWeights(Map<String, Double> networkWeights) {
        this.networkWeights = networkWeights;
    }
}
