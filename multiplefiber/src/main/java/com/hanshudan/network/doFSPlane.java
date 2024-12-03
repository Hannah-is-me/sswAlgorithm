package com.hanshudan.network;

import com.hanshudan.algorithm.Dijkstra;
import com.hanshudan.algorithm.RouteSearching;
import com.hanshudan.general.Constant;
import com.hanshudan.general.Time;
import com.hanshudan.general.Timeroute;
import com.hanshudan.traffic.Action;
import com.hanshudan.traffic.Connection;

import java.util.*;

public class doFSPlane {



    public doFSPlane() {

    }
/*
    public ArrayList<FSRoute> GenerateFSPlane(Network network,  int requiredFS){
        ArrayList<FSRoute> FSPlaneList = new ArrayList<FSRoute>(10000);
        for (int i = 0; i < Constant.wavNum - requiredFS + 1; i++) {
            FSRoute newFSRoute = new FSRoute("", i);
            newFSRoute.setFirstSlotNum(i);
            newFSRoute.setLastSlotNum(i + requiredFS - 1);
           // newFSRoute.setNodeList(network.getNodeList());

            for (Fiber fiber : network.getFiberList()) {
                if (hasIdleSlots(fiber, i, i + requiredFS - 1)){
                    newFSRoute.getFiberMap().put(fiber.getName(), fiber);
                    newFSRoute.getFiberList().add(fiber);
                }else{}
            }

            newFSRoute.setNodeList(network.getNodeList());  // 建立频谱窗平面  Link，Node，parameter，Nodepair
            newFSRoute.setNodepairMap(network.getNodepairMap());
            FSPlaneList .add(newFSRoute);
        }
        return  FSPlaneList;
    }*/

//    public  ArrayList<Timeroute>GenerateTimePlane(Network network, double requiredtimeslot, ArrayList<FSRoute> slotWindows ){
//        ArrayList<Timeroute>timePlaneList = new ArrayList<>();
//        for(int i = 0; i <Constant.timeslotNum-requiredtimeslot+1;i++){
//            Timeroute newtimeroute = new Timeroute("",i);
//            newtimeroute.setFirstSlotNum(i);
//            newtimeroute.setLastSlotNum(i+(int)requiredtimeslot-1);
//
//            for (Fiber fiber : network.getFiberList()) {
//                if (hasIdleSlots(fiber, i, i + requiredFS - 1)){
//                    newFSRoute.getFiberMap().put(fiber.getName(), fiber);
//                    newFSRoute.getFiberList().add(fiber);
//                }else{}
//            }
//
//            newFSRoute.setNodeList(network.getNodeList());  // 建立频谱窗平面  Link，Node，parameter，Nodepair
//            newFSRoute.setNodepairMap(network.getNodepairMap());
//            FSPlaneList .add(newFSRoute);
//        }
//        return  FSPlaneList;
//    }

    public  HashMap<Timeroute,ArrayList<FSRoute>> GenerateTimeFSPlane(Network network, Connection connection, int requiredFS, int fiberNumber, int g ){

        HashMap<Timeroute,ArrayList<FSRoute>>timeFSHashMap = new HashMap<>();
        ArrayList<Timeroute>timeList = new ArrayList<>();
        ArrayList<ArrayList<FSRoute>> FSRouteListlist= new ArrayList<>();

        for(int i =0; i+(int) connection.getStartTime()+(int)  connection.getKeepTime()-1<= connection.getEndTime();i ++) {

            int starttime =(int) connection.getStartTime()+i;
            int endtime = starttime+(int) connection.getKeepTime()-1;

            Timeroute newtimeroute = new Timeroute("", starttime, fiberNumber, g);
            newtimeroute.setFirstSlotNum(starttime);
            newtimeroute.setLastSlotNum(endtime);

            ArrayList<FSRoute> FSPlaneList = new ArrayList<FSRoute>(10000);

           for (int j = 0; j < Constant.wavNum-requiredFS+1;j++) {
              FSRoute newFSRoute = new FSRoute("", j, fiberNumber,g); // 是生成频谱窗平面还是空间频谱窗平面？
              newFSRoute.setFirstSlotNum(j);   // 第几个频谱窗开始波长下标
              newFSRoute.setLastSlotNum(j + requiredFS - 1); // 第几个频谱窗结束波长下标

            for (Fiber fiber : network.getFiberList() ) {
                if (hastimeFSSlots(fiber, starttime, endtime, j, j + requiredFS - 1)) { //这边只是增加了当前时隙窗和频谱窗空闲的fiber
                    newFSRoute.getFiberMap().put(fiber.getName(), fiber);   //这频谱窗包含的光纤组合，也就是并不是生成的空间频谱窗。
                    newFSRoute.getFiberList().add(fiber);
                } else {
                }
            }
            newFSRoute.setNodeList(network.getNodeList());  // 建立频谱窗平面  Link，Node，parameter，Nodepair
            newFSRoute.setNodepairMap(network.getNodepairMap()); // node的信息从network继承，而fiber 的内容发生了改变
            FSPlaneList.add(newFSRoute);
        }

            timeFSHashMap .put(newtimeroute,FSPlaneList);
            timeList.add(newtimeroute);
            FSRouteListlist.add(FSPlaneList);

        }

        connection.setTimeWindowList(timeList);
        connection.setFSWindowListlist(FSRouteListlist);

        return   timeFSHashMap;

    }



    public  HashMap<Timeroute,ArrayList<FSRoute>> GenerateTWSSW(Network network, Connection connection, int requiredFS, int fiberNumber, int g, List<Fiber> newRoutefixedfiberall ){

        HashMap<Timeroute,ArrayList<FSRoute>>timeFSHashMap = new HashMap<>();
        ArrayList<Timeroute>timeList = new ArrayList<>();
        ArrayList<ArrayList<FSRoute>> FSRouteListlist= new ArrayList<>();

        for(int i =0; i+(int) connection.getStartTime()+(int)  connection.getKeepTime()-1<= connection.getEndTime();i ++) {

            int starttime =(int) connection.getStartTime()+i;
            int endtime = starttime+(int) connection.getKeepTime()-1;

            Timeroute newtimeroute = new Timeroute("", starttime, fiberNumber, g);
            newtimeroute.setFirstSlotNum(starttime);
            newtimeroute.setLastSlotNum(endtime);

            ArrayList<FSRoute> FSPlaneList = new ArrayList<FSRoute>(10000);

            for (int j = 0; j < Constant.wavNum-requiredFS+1;j++) {
                FSRoute newFSRoute = new FSRoute("", j, fiberNumber,g); // 是生成频谱窗平面还是空间频谱窗平面？
                newFSRoute.setFirstSlotNum(j);   // 第几个频谱窗开始波长下标
                newFSRoute.setLastSlotNum(j + requiredFS - 1); // 第几个频谱窗结束波长下标

                for (Fiber fiber : newRoutefixedfiberall ) {
                    if (hastimeFSSlots(fiber, starttime, endtime, j, j + requiredFS - 1)) { //这边只是增加了当前时隙窗和频谱窗空闲的fiber
                        newFSRoute.getFiberMap().put(fiber.getName(), fiber);   //这频谱窗包含的光纤组合，也就是并不是生成的空间频谱窗。
                        newFSRoute.getFiberList().add(fiber);
                    } else {
                    }
                }
                newFSRoute.setNodeList(network.getNodeList());  // 建立频谱窗平面  Link，Node，parameter，Nodepair
                newFSRoute.setNodepairMap(network.getNodepairMap()); // node的信息从network继承，而fiber 的内容发生了改变
                FSPlaneList.add(newFSRoute);
            }

            timeFSHashMap .put(newtimeroute,FSPlaneList);
            timeList.add(newtimeroute);
            FSRouteListlist.add(FSPlaneList);

        }

        connection.setTimeWindowList(timeList);
        connection.setFSWindowListlist(FSRouteListlist);

        return   timeFSHashMap;

    }







/*    private  boolean hasIdleSlots( Fiber fiber, int start, int end) {
        for (int i = start; i <= end; i++) {
            if (fiber.getWavelengthList().get(i).getCapacity() == 0) {
                return false;
            }
        }
        return true;
    }*/

    private  boolean hastimeFSSlots( Fiber fiber, int Timestart, int Timeend, int FSstart, int FSend) {
        for (int i =Timestart; i < Timeend; i++) {
            for (int j=  FSstart; j <= FSend; j++) {
                if (fiber.getTimeList().get(i).getWavelengthList().get(j).getCapacity() == 0) {
                    return false;
                }
            }
        }
        return true;
    }

//    private  boolean hasIdleTimeSlots( Fiber fiber, int start, int end) {
//        for (int i = start; i <= end; i++) {
//            if (fiber.getWavelengthList().get(i).getCapacity() == 0) {
//                return false;
//            }
//        }
//        return true;
//    }

/*    public boolean findandassigavaFSwindow(Connection connection, NodePair nodePair, LightPath lightPath, ArrayList<FSRoute> FSRouteList,Network layer){
        FSRoute slotWindow= findFSwindowfirstfit(connection, nodePair, lightPath, FSRouteList,layer);//在给定nodepair情况下寻找可用波长拓扑路由
        if (slotWindow != null){
            assignTimeFSwindow(connection, timeWindows, slotWindow,  nodePair);
//
//            for (int ii = 0 ; ii < lightPath.getPhysicPath().getFiberlist().size(); ii++)
//            {
//                System.out.println("使用" +  lightPath.getPhysicPath().getFiberlist().get(ii).getName());
//            }
//            System.out.println("------------------------");
            return true;


        }
          return false;
    }*/

    public boolean containTimeFSWindowrouteSSWfirst(Connection connection, NodePair nodePair, LightPath lightPath, HashMap<Timeroute,ArrayList<FSRoute>>  timeFSWindowsHashMap , Network layer) {
//

        LinearRoute newRoutefixed =  nodePair.getLinearroutelist().get(0);
        RouteSearching routesearching = new RouteSearching();

        routesearching.DijkstraallfibeList(nodePair.getSrcNode(), nodePair.getDesNode(), layer, newRoutefixed , null);
        OUT:

    //       for ( ArrayList<FSRoute> slotWindowList : connection.getFSWindowListlist()) {
         for( ArrayList<FSRoute>  slotWindowList: connection.getFSWindowListlist()){
             for (FSRoute slotWindow : slotWindowList){
             for (Timeroute timeWindow:  timeFSWindowsHashMap.keySet()) {
                 LinearRoute intRoute =   nodePair.getLinearroutelist().get(0) ;
                 LinearRoute newRoute = new LinearRoute(intRoute);
                 RouteSearching routesearching1 = new RouteSearching();

                 routesearching1.Dijkstra(nodePair.getSrcNode(), nodePair.getDesNode(), slotWindow, newRoute, slotWindow.getConstraint());

                 if (newRoute.getFiberlist().size() !=0) {
                     if (newRoutefixed.getFiberlist().containsAll(newRoute.getFiberlist())) {
                         lightPath.setPhysicPath(newRoute);//这边只是用nodelist信息 没有记录nodepair uselightpath
                         //     connection.setLightPath(lightPath);
                         for (int i = 0; i < newRoute.getFiberlist().size(); i++) {
                             connection.getRouteList().add(newRoute.getFiberlist().get(i));
                             connection.setTimerouterecord(timeWindow);
                         }   // 业务路由信息放在connection的routelist中
                         assignTimeFSwindow(connection, timeWindow, slotWindow, nodePair, layer);
                         connection.setAllocateIs(true);
                         break OUT;
                     }
                 }
            }
           }
       }

        if (connection.isAllocateIs()==false)
        {return  false;}
        else{
            return true;
        }
    }

    public boolean containupdateTimeFSWindowrouteSSWfirst(Connection connection, NodePair nodePair, LightPath lightPath, HashMap<Timeroute,ArrayList<FSRoute>>  timeFSWindowsHashMap , Network layer) {
//


        OUT:

        //       for ( ArrayList<FSRoute> slotWindowList : connection.getFSWindowListlist()) {
        for( ArrayList<FSRoute>  slotWindowList: connection.getFSWindowListlist()){
            for (FSRoute slotWindow : slotWindowList){
                for (Timeroute timeWindow:  timeFSWindowsHashMap.keySet()) {
                    LinearRoute intRoute =   nodePair.getLinearroutelist().get(0) ;
                    LinearRoute newRoute = new LinearRoute(intRoute);
                    RouteSearching routesearching1 = new RouteSearching();

                    routesearching1.Dijkstra(nodePair.getSrcNode(), nodePair.getDesNode(), slotWindow, newRoute, slotWindow.getConstraint());

                    if (newRoute.getFiberlist().size() != 0) {
                            lightPath.setPhysicPath(newRoute);//这边只是用nodelist信息 没有记录nodepair uselightpath
                            //     connection.setLightPath(lightPath);
                            for (int i = 0; i < newRoute.getFiberlist().size(); i++) {
                                connection.getRouteList().add(newRoute.getFiberlist().get(i));
                                connection.setTimerouterecord(timeWindow);
                            }   // 业务路由信息放在connection的routelist中
                            assignTimeFSwindow(connection, timeWindow, slotWindow, nodePair, layer);
                            connection.setAllocateIs(true);
                            break OUT;
                    }
                }
            }
        }

        if (connection.isAllocateIs()==false)
        {return  false;}
        else{
            return true;
        }
    }

    public boolean   containTimeFSWindowrouteSSWleastload(Connection connection, NodePair nodePair, LightPath lightPath, HashMap<Timeroute,ArrayList<FSRoute>>  timeFSWindowsHashMap , Network layer) {

        ArrayList<Timeroute>TWList = new ArrayList<>();
        ArrayList<FSRoute> FSRouteList = new ArrayList<>();
        HashMap <Timeroute, FSRoute> TWFSMap = new HashMap<>();
        HashMap<Timeroute, Integer> TWMap = new HashMap<Timeroute, Integer>();
        HashMap<FSRoute, Integer> FSMap = new HashMap<>();
        HashMap<Timeroute, ArrayList<Fiber>> TWFiberMap = new HashMap<>();
        LinearRoute intRoute =   nodePair.getLinearroutelist().get(0) ;
        LinearRoute newRoutefixed = new LinearRoute(intRoute);
        RouteSearching routesearching = new RouteSearching();
     //   routesearching.DijkstraallfibeList(nodePair.getSrcNode(), nodePair.getDesNode(), layer, newRoutefixed , null);
       //这边有固定路由中所包含的fiberlist信息+ timeFSWindowsHashMap中FSS可组合成SSW信息。

        //LinearRoute newRoutefixed =  nodePair.getLinearroutelist().get(0);
        //RouteSearching routesearching = new RouteSearching();

       //routesearching.DijkstraallfibeList(nodePair.getSrcNode(), nodePair.getDesNode(), layer, newRoutefixed , null);
        OUT:
        //       for ( ArrayList<FSRoute> slotWindowList : connection.getFSWindowListlist()) {
        for (Timeroute timeWindow:  connection.getTimeWindowList()){
            //  for( ArrayList<FSRoute>  slotWindowList :timeFSWindowsHashMap.get(timeWindow)){
            for (FSRoute slotWindow :timeFSWindowsHashMap.get(timeWindow)){

                     LinearRoute newRoute = new LinearRoute(intRoute);
                     RouteSearching routesearching1 = new RouteSearching();
                   //  routesearching1. DijkstraLestloadfibeList(nodePair.getSrcNode(), nodePair.getDesNode(),slotWindow, newRoute, slotWindow.getConstraint());//这边slotwindow到底有啥作用呢
                     ArrayList<Fiber>minfiberslist = new ArrayList<>();

                     for (Fiber fiber : lightPath.getPhysicPath().getFiberlist()){
                       HashMap<Fiber, Integer>fiberflagMap = new HashMap<>();
                         for(Fiber fiberall : slotWindow.getFiberList()){ //遍历每条link，选取当前link中最小FS的值。
                               if( fiber.getSrcNode() ==  fiberall.getSrcNode() &  fiber.getDestNode() == fiberall.getDestNode()){
                              //     fiberall.setFuse(timeWindow); //后加的
                                   fiberflagMap.put(fiberall,fiberall.getFuse());// 计算当前fiber中使用FS最小的值。
                               }
                         }
                        if ( fiberflagMap.size()==0){break;}
                         minfiberslist.add(findminfiber(fiberflagMap)); //所以这边最小值是找的什么，这边取的是最小值，这边没有体现波长数
                     }

                    if ( minfiberslist.size()== 0){
                        TWMap.put(timeWindow,(int)Constant.INF);
                        FSMap.put(slotWindow,(int)Constant.INF);
                        TWFSMap.put(timeWindow,slotWindow);
                        TWFiberMap.put(timeWindow,newRoute.getFiberlist());
                    }
                    else{
                        if ( Fmaxrecord( minfiberslist) == 0){
                            connection.setRouteList( minfiberslist);
                            assignTimeFSwindow(connection, timeWindow, slotWindow, nodePair, layer);
                            connection.setAllocateIs(true);
                            break OUT;
                        }else{
                            TWMap.put(timeWindow,Fmaxrecord( minfiberslist) );
                            FSMap.put(slotWindow,Fmaxrecord( minfiberslist) );
                            TWFSMap.put(timeWindow,slotWindow);
                            TWFiberMap.put(timeWindow,minfiberslist);
                        }
                    }
                }
            }
      if (TWMap.size()!=0){
          Timeroute minTW =  findminPWofTW( TWMap); //这边是把功耗考虑进去了
          FSRoute minFSW = TWFSMap.get(minTW);
          connection.setRouteList(TWFiberMap.get(minTW ));
          assignTimeFSwindow(connection,  minTW , minFSW , nodePair, layer);
          connection.setAllocateIs(true);
      }

        if (connection.isAllocateIs()==false)
        {return  false;}
        else{
            return true;
        }
    }


    public boolean   containupdateTimeFSWindowrouteTest(Connection connection, NodePair nodePair, LightPath lightPath, HashMap<Timeroute,ArrayList<FSRoute>>  timeFSWindowsHashMap , Network layer) {

        ArrayList<Timeroute>TWList = new ArrayList<>();
        ArrayList<FSRoute> FSRouteList = new ArrayList<>();
        HashMap <Timeroute, FSRoute> TWFSMap = new HashMap<>();
        HashMap<Timeroute, Integer> TWMap = new HashMap<Timeroute, Integer>();
        HashMap<FSRoute, Integer> FSMap = new HashMap<>();
        HashMap<Timeroute, ArrayList<Fiber>> TWFiberMap = new HashMap<>();



        //LinearRoute newRoutefixed =  nodePair.getLinearroutelist().get(0);
        //RouteSearching routesearching = new RouteSearching();

        //routesearching.DijkstraallfibeList(nodePair.getSrcNode(), nodePair.getDesNode(), layer, newRoutefixed , null);
        OUT:
        //       for ( ArrayList<FSRoute> slotWindowList : connection.getFSWindowListlist()) {
        for (Timeroute timeWindow:  connection.getTimeWindowList())
        {
            //  for( ArrayList<FSRoute>  slotWindowList :timeFSWindowsHashMap.get(timeWindow)){
            for (FSRoute slotWindow :timeFSWindowsHashMap.get(timeWindow))
            {
                LinearRoute intRoute = nodePair.getLinearroutelist().get(0);
                LinearRoute newRoute = new LinearRoute(intRoute);
                RouteSearching routesearching1 = new RouteSearching();
                routesearching1.DijkstraLestloadfibeList(nodePair.getSrcNode(), nodePair.getDesNode(), slotWindow, newRoute, slotWindow.getConstraint());
                //这边的newroute是自动变化按照nodepair，但是下面可以把他强制转化为固定路由，但是他选择是第一个，但是我要是全部吧。
                LinearRoute newRoutefixed = new LinearRoute(intRoute);
                RouteSearching routesearching = new RouteSearching();
                routesearching.DijkstraallfibeList(nodePair.getSrcNode(), nodePair.getDesNode(), layer, newRoutefixed, null);
                ArrayList<Fiber> minfiberslist = new ArrayList<>();

                if (newRoute.getFiberlist().size() >= lightPath.getPhysicPath().getFiberlist().size())
                {
                    // if (newRoute.getFiberlist().size() != 0) {//也就是slotwindow没有当前TW和SSW中没有合适的fiberlist
                    if (newRoutefixed.getFiberlist().containsAll(newRoute.getFiberlist()))
                    {

                     //这边将自适应更新路由 改为固定路由

                      for (Fiber fiber : newRoute.getFiberlist())
                      {
                         HashMap<Fiber, Integer> fiberflagMap = new HashMap<>();

                          for (Fiber fiberall : newRoutefixed.getFiberlist())
                            {
                             if (fiber.getSrcNode() == fiberall.getSrcNode() & fiber.getDestNode() == fiberall.getDestNode())
                              {
                              //  fiberall.setFuse(timeWindow); //后加的
                               fiberflagMap.put(fiberall, fiberall.getFuse());//应该增加TW信息
                              }
                            }

                          if (fiberflagMap.size() == 0)
                          {
                             break;
                          }

                        minfiberslist.add(findminfiber(fiberflagMap));
                      }

                    if (minfiberslist.size() == 0)
                      {
                       TWMap.put(timeWindow, (int) Constant.INF);
                       FSMap.put(slotWindow, (int) Constant.INF);
                       TWFSMap.put(timeWindow, slotWindow);
                       TWFiberMap.put(timeWindow, newRoute.getFiberlist());
                      }
                    else
                      {
                       if (Fmaxrecord(minfiberslist) == 0)
                         {
                         connection.setRouteList(minfiberslist);
                         assignTimeFSwindow(connection, timeWindow, slotWindow, nodePair, layer);
                         connection.setAllocateIs(true);
                         break OUT;
                         }
                       else
                         {
                          TWMap.put(timeWindow, Fmaxrecord(minfiberslist));
                          FSMap.put(slotWindow, Fmaxrecord(minfiberslist));
                          TWFSMap.put(timeWindow, slotWindow);
                          TWFiberMap.put(timeWindow, minfiberslist);
                         }
                       }
                 }
               }
            }
         }

        if (TWMap.size()!=0)
        {
            Timeroute minTW =  findminPWofTW( TWMap);
            FSRoute minFSW = TWFSMap.get(minTW);
            connection.setRouteList(TWFiberMap.get(minTW ));
            assignTimeFSwindow(connection,  minTW , minFSW , nodePair, layer);
            connection.setAllocateIs(true);
        }


        if (connection.isAllocateIs()==false)
        {
            return  false;
        }
        else
        {
            return true;
        }
    }





    public boolean   containupdateTimeFSWindowrouteSSWleastload(Connection connection, NodePair nodePair, LightPath lightPath, HashMap<Timeroute,ArrayList<FSRoute>>  timeFSWindowsHashMap , Network layer) {

        ArrayList<Timeroute>TWList = new ArrayList<>();
        ArrayList<FSRoute> FSRouteList = new ArrayList<>();
        HashMap <Timeroute, FSRoute> TWFSMap = new HashMap<>();
        HashMap<Timeroute, Integer> TWMap = new HashMap<Timeroute, Integer>();
        HashMap<FSRoute, Integer> FSMap = new HashMap<>();
        HashMap<Timeroute, ArrayList<Fiber>> TWFiberMap = new HashMap<>();



        //LinearRoute newRoutefixed =  nodePair.getLinearroutelist().get(0);
        //RouteSearching routesearching = new RouteSearching();

        //routesearching.DijkstraallfibeList(nodePair.getSrcNode(), nodePair.getDesNode(), layer, newRoutefixed , null);
        OUT:
        //       for ( ArrayList<FSRoute> slotWindowList : connection.getFSWindowListlist()) {
        for (Timeroute timeWindow:  connection.getTimeWindowList()){
            //  for( ArrayList<FSRoute>  slotWindowList :timeFSWindowsHashMap.get(timeWindow)){
            for (FSRoute slotWindow :timeFSWindowsHashMap.get(timeWindow)){
                LinearRoute intRoute =   nodePair.getLinearroutelist().get(0) ;
                LinearRoute newRoute = new LinearRoute(intRoute);
                RouteSearching routesearching1 = new RouteSearching();
                routesearching1. DijkstraLestloadfibeList(nodePair.getSrcNode(), nodePair.getDesNode(),slotWindow, newRoute, slotWindow.getConstraint());

                LinearRoute newRoutefixed = new LinearRoute(intRoute);
                RouteSearching routesearching = new RouteSearching();
                routesearching.DijkstraallfibeList(nodePair.getSrcNode(), nodePair.getDesNode(),layer, newRoutefixed , null);

                ArrayList<Fiber>minfiberslist = new ArrayList<>();

                for (Fiber fiber : newRoute.getFiberlist()){
                    HashMap<Fiber, Integer>fiberflagMap = new HashMap<>();
                    for(Fiber fiberall :  newRoutefixed.getFiberlist()){
                        if( fiber.getSrcNode() ==  fiberall.getSrcNode() &  fiber.getDestNode() == fiberall.getDestNode()){

                          //  fiberall.setFuse(timeWindow); //后加的
                            fiberflagMap.put(fiberall,fiberall.getFuse());//应该增加TW信息
                        }
                    }
                    if ( fiberflagMap.size()==0){break;}
                    minfiberslist.add(findminfiber(fiberflagMap));
                }

                if ( minfiberslist.size()== 0){
                    TWMap.put(timeWindow,(int)Constant.INF);
                    FSMap.put(slotWindow,(int)Constant.INF);
                    TWFSMap.put(timeWindow,slotWindow);
                    TWFiberMap.put(timeWindow,newRoute.getFiberlist());
                }
                else{
                    if ( Fmaxrecord( minfiberslist) == 0){
                        connection.setRouteList( minfiberslist);
                        assignTimeFSwindow(connection, timeWindow, slotWindow, nodePair, layer);
                        connection.setAllocateIs(true);
                        break OUT;
                    }else{
                        TWMap.put(timeWindow,Fmaxrecord( minfiberslist) );
                        FSMap.put(slotWindow,Fmaxrecord( minfiberslist) );
                        TWFSMap.put(timeWindow,slotWindow);
                        TWFiberMap.put(timeWindow,minfiberslist);
                    }
                }
            }
        }
        if (TWMap.size()!=0){
            Timeroute minTW =  findminPWofTW( TWMap);
            FSRoute minFSW = TWFSMap.get(minTW);
            connection.setRouteList(TWFiberMap.get(minTW ));
            assignTimeFSwindow(connection,  minTW , minFSW , nodePair, layer);
            connection.setAllocateIs(true);
        }

        if (connection.isAllocateIs()==false)
        {return  false;}
        else{
            return true;
        }
    }

    public int Fmaxrecord(ArrayList<Fiber> fiberlist) {
        int sum = 0;

        for (Fiber fiber :  fiberlist) {
            sum += fiber.getFuse();
        }
        return sum;
    }

    public Timeroute  findminPWofTW(   HashMap<Timeroute, Integer> TWMap ){
        List<Map.Entry<Timeroute, Integer>> list = new ArrayList(TWMap.entrySet());
        Collections.sort(list, (o1, o2) ->(int)(o1.getValue() - o2.getValue()));
        return list.get(0).getKey();
    }

    public Fiber findminfiber( HashMap<Fiber, Integer> TWMap ){
        List<Map.Entry<Fiber, Integer>> list = new ArrayList(TWMap.entrySet());
        Collections.sort(list, (o1, o2) ->(int)(o1.getValue() - o2.getValue()));
        return list.get(0).getKey();
    }


    public boolean containTimeFSWindowrouteTWfirst(Connection connection, NodePair nodePair, LightPath lightPath, HashMap<Timeroute,ArrayList<FSRoute>>  timeFSWindowsHashMap , Network layer) {
////////////

        LinearRoute newRoutefixed =  nodePair.getLinearroutelist().get(0);
        RouteSearching routesearching = new RouteSearching();

        routesearching.DijkstraallfibeList(nodePair.getSrcNode(), nodePair.getDesNode(), layer, newRoutefixed , null);
        OUT:

        //       for ( ArrayList<FSRoute> slotWindowList : connection.getFSWindowListlist()) {
        for (Timeroute timeWindow:  connection.getTimeWindowList()){
          //  for( ArrayList<FSRoute>  slotWindowList :timeFSWindowsHashMap.get(timeWindow)){
            for (FSRoute slotWindow :timeFSWindowsHashMap.get(timeWindow))
             {

                    LinearRoute intRoute =   nodePair.getLinearroutelist().get(0) ;
                    LinearRoute newRoute = new LinearRoute(intRoute);
                    RouteSearching routesearching1 = new RouteSearching();
                    routesearching1.Dijkstra(nodePair.getSrcNode(), nodePair.getDesNode(), slotWindow, newRoute, slotWindow.getConstraint()); //这个是否矛盾 slotwindow中包含的fiberlist是不全的
                   // 为啥newRoute是一组fiber不是fiberlist

                    if (newRoute.getFiberlist().size() !=0 ){
                    // if (newRoute.getFiberlist().size() != 0) {//也就是slotwindow没有当前TW和SSW中没有合适的fiberlist
                        if (newRoutefixed.getFiberlist().containsAll(newRoute.getFiberlist())) {
                            lightPath.setPhysicPath(newRoute);//这边只是用nodelist信息 没有记录nodepair uselightpath
                            //     connection.setLightPath(lightPath);
                            for (int i = 0; i < newRoute.getFiberlist().size(); i++) {
                                connection.getRouteList().add(newRoute.getFiberlist().get(i));
                                connection.setTimerouterecord(timeWindow);
                            }   // 业务路由信息放在connection的routelist中
                            assignTimeFSwindow(connection, timeWindow, slotWindow, nodePair, layer);
                            connection.setAllocateIs(true);
                            break OUT;
                        }
                    }
       //         }
            }
        }

        if (connection.isAllocateIs()==false)
        {
            return  false;
        }
        else{
            return true;
        }
    }




    public boolean containupdateTimeFSWindowrouteTWfirst(Connection connection, NodePair nodePair, LightPath lightPath, HashMap<Timeroute,ArrayList<FSRoute>>  timeFSWindowsHashMap , Network layer) {
///////


        OUT:

        //       for ( ArrayList<FSRoute> slotWindowList : connection.getFSWindowListlist()) {
        for (Timeroute timeWindow:  connection.getTimeWindowList()){
            //  for( ArrayList<FSRoute>  slotWindowList :timeFSWindowsHashMap.get(timeWindow)){
            for (FSRoute slotWindow :timeFSWindowsHashMap.get(timeWindow))
                {

                    LinearRoute intRoute =   nodePair.getLinearroutelist().get(0) ;
                    LinearRoute newRoute = new LinearRoute(intRoute);
                    RouteSearching routesearching1 = new RouteSearching();
                    routesearching1.Dijkstra(nodePair.getSrcNode(), nodePair.getDesNode(), slotWindow, newRoute, slotWindow.getConstraint());

                    if (newRoute.getFiberlist().size() != 0) {

                            lightPath.setPhysicPath(newRoute);//这边只是用nodelist信息 没有记录nodepair uselightpath
                            //     connection.setLightPath(lightPath);
                            for (int i = 0; i < newRoute.getFiberlist().size(); i++) {
                                connection.getRouteList().add(newRoute.getFiberlist().get(i));
                                connection.setTimerouterecord(timeWindow);
                            }   // 业务路由信息放在connection的routelist中
                            assignTimeFSwindow(connection, timeWindow, slotWindow, nodePair, layer);
                            connection.setAllocateIs(true);
                            break OUT;

                    }
                    //         }
                }
            }

        if (connection.isAllocateIs()==false)
        {return  false;}
        else{
            return true;
        }
    }



    public boolean  allocationstartup(Connection connection, NodePair nodePair, LightPath lightPath, HashMap<Timeroute,ArrayList<FSRoute>>  timeFSWindowsHashMap , Network layer){

        LinearRoute newRoutefixed =  nodePair.getLinearroutelist().get(0);
        RouteSearching routesearching = new RouteSearching();

        routesearching.DijkstraallfibeList(nodePair.getSrcNode(), nodePair.getDesNode(), layer, newRoutefixed , null);



        for (Timeroute timeWindow:  timeFSWindowsHashMap.keySet()){
            for (  ArrayList<FSRoute> slotWindowList : timeFSWindowsHashMap.values()) {
                for (FSRoute slotWindow: slotWindowList)
                {
                    LinearRoute newRoute = nodePair.getLinearroutelist().get(0);
                    RouteSearching routesearching1 = new RouteSearching();
                    routesearching1.Dijkstra(nodePair.getSrcNode(), nodePair.getDesNode(), slotWindow, newRoute, slotWindow.getConstraint());

                    if (newRoute.getFiberlist().size() != 0) {
                        if (newRoutefixed.getFiberlist().containsAll(newRoute.getFiberlist())) {
                            lightPath.setPhysicPath(newRoute);//这边只是用nodelist信息 没有记录nodepair uselightpath
                            //     connection.setLightPath(lightPath);
                            for (int i = 0; i < newRoute.getFiberlist().size(); i++) {
                                connection.getRouteList().add(newRoute.getFiberlist().get(i));
                            }   // 业务路由信息放在connection的routelist中
                            assignTimeFSwindow(connection, timeWindow, slotWindow, nodePair,layer);
                            return true;
                        }
                    }
                }

            }
        }

        return false;
    }



    public boolean Time_FStimeallocate(Connection connection, NodePair nodePair, LightPath lightPath, HashMap<Timeroute,ArrayList<FSRoute>>  timeFSWindowsHashMap , Network layer) {


        //将所有的SCh都加入route中
        //  LinearRoute newRoute = nodePair.getLinearroutelist().get(0);
        //  RouteSearching routesearching1 = new RouteSearching();
        //  routesearching1.Dijkstra(nodePair.getSrcNode(), nodePair.getDesNode(), slotWindow, newRoute, slotWindow.getConstraint());
        ArrayList<Node> ScrNodeList = new ArrayList<>();
        ArrayList<Node> DesNodeList = new ArrayList<>();

        ArrayList<HashMap<Timeroute, Fiber>> routefiberTimeList = new ArrayList<>();

        HashMap<Fiber, Double> FiberminPWMap = new HashMap<>();
        ArrayList<Fiber> FiberminPWList = new ArrayList<>();
        HashMap<Timeroute, ArrayList<Fiber>> TimewindowFiberMap = new HashMap<>();


        LinearRoute route = nodePair.getUsedRoute();
        for (Fiber fiber : route.getFiberlist()) {
            ScrNodeList.add(fiber.getSrcNode());
            DesNodeList.add(fiber.getDestNode());
        }

        ArrayList<Fiber> MinFibertimewindowPWList = new ArrayList<>();
        HashMap<Fiber, Timeroute> MinFibertimewindowPWMap = new HashMap<>();
        ArrayList<Fiber>FiberList = new ArrayList<>();
       ArrayList<Timeroute> TWList = new ArrayList<>();

        for (int i = 0; i < ScrNodeList.size(); i++) {

            //  ArrayList<Fiber>FibertimewindowPWList =new ArrayList<>();
          //  HashMap<Timeroute, Fiber> FibertimewindowMap = new HashMap<>();
            HashMap<HashMap<Timeroute, Fiber>, Double> FibertimepwMap = new HashMap<>();
            //  HashMap<Timeroute, Double>FibertimewindowPWvalueMap = new HashMap<>();

            HashMap<Timeroute, Fiber> TimeFiberMap = new HashMap<>();
            HashMap<Timeroute, Double> TimePwMap = new HashMap<>();

            for (Timeroute timeWindow : timeFSWindowsHashMap.keySet()) {
                LinearRoute newRoutefixed = nodePair.getLinearroutelist().get(0);
                RouteSearching routesearching = new RouteSearching();
                routesearching.DijkstraallfibeList(ScrNodeList.get(i), DesNodeList.get(i), layer, newRoutefixed, null);

                 HashMap<Fiber, Double> FiberPWMap = new HashMap<>();
          //      ArrayList<Double> FiberPWList = new ArrayList<>();

           //     HashMap<Fiber, Double> MinFiberPWMap = new HashMap<>();
            //    ArrayList<Double> MinFiberPWList = new ArrayList<>();

                for (int j = 0; j < newRoutefixed.getFiberlist().size(); j++) {

                    Double PW = net_pwofTimeoffiber(timeWindow, newRoutefixed.getFiberlist().get(j));
            //        FiberPWList.add(PW);
                    FiberPWMap.put(newRoutefixed.getFiberlist().get(j), PW);
                    //    FiberminPWList.add(findminPW(FiberPWMap));

                    newRoutefixed.getFiberlist().get(j).AddFiberTimePW(timeWindow.getIndex(), PW);

                //    FibertimewindowMap.put(timeWindow, newRoutefixed.getFiberlist().get(j));

                    //      FibertimepwMap.put( FibertimewindowMap, newRoutefixed.getFiberlist().get(j).getTimewindowPWHashMap().get(timeWindow));
                    //       FibertimewindowPWvalueMap.put(timeWindow,findminPW(FiberPWMap).getTimewindowPWHashMap().get(timeWindow));
                    //    FibertimewindowPWList.add(findminPW(FiberPWMap));
                }
                Fiber fiber = findminPWofFiber(FiberPWMap);// 寻找最小值的时候，如果遇到有好几个最小值相同怎么办？？
               //现在当前波平面内找出最小的fiber值。
                TimeFiberMap.put(timeWindow, fiber);
                TimePwMap.put(timeWindow, fiber.getTimewindowPWHashMap().get(timeWindow.getIndex()));
            }

// fiber timeroute
         //   MinFibertimewindowPWMap.put(TimeFiberMap.get(findminPW(TimePwMap)), findminPW(TimePwMap));

            FiberList.add(TimeFiberMap.get(findminPW(TimePwMap)));//最小的fiber集合
            TWList.add(findminPW(TimePwMap));
            //      findminPW(FibertimewindowPWvalueMap);//最小timewindow
            //   findminPW(   FibertimewindowPWMap);
        }



// 使用first-first去选择slotwindow,确保每根光纤束上选择相同的空闲频谱窗。
        // 空闲的频谱窗建立的时候就已经判断。
        // 现在主要是判断每根光纤使用相同的频谱窗。
        int flagg =0;
        for ( Fiber fiber:  FiberList){

            ArrayList<FSRoute> FSwindowList = timeFSWindowsHashMap.get( TWList.get(flagg));

            flagg = flagg +1;

         for (FSRoute FSwindow:FSwindowList){
             if (  FSwindow.getFiberList().containsAll( FiberList))
           {

                 assignTimeFSwindow1(connection,MinFibertimewindowPWMap.get(fiber),FSwindow, nodePair,layer);
                 connection.setAllocateIs(true);
                 break;
             }
             else {
                 continue;
             }
         }

            if (connection.isAllocateIs()==false)
            {break;}

        }

        //
        // timeFSWindowsHashMap
    if (connection.isAllocateIs()==false)
        {return  false;}
       else{
       return true;
      }
    }




// 如果选择最小的，是否会存在多个最小的。
    public Fiber findminPWofFiber(HashMap<Fiber, Double> FiberPWMap ){
        List<Map.Entry<Fiber, Double>> list = new ArrayList(FiberPWMap.entrySet());
        Collections.sort(list, (o1, o2) ->(int)(o1.getValue() - o2.getValue()));
       return list.get(0).getKey();
    }


    public Timeroute findminPW(HashMap<Timeroute, Double>FibertimewindowPWvalueMap ){
        List<Map.Entry<Timeroute, Double>> list = new ArrayList(FibertimewindowPWvalueMap.entrySet());
        Collections.sort(list, (o1, o2) ->(int)(o1.getValue() - o2.getValue()));
        return list.get(0).getKey();
    }

    public Double net_pwofTimeoffiber(Timeroute timeWindow, Fiber fiber){
        Double flag = 0.0;
        Double Pw = 0.0;
       for (int i = 0;  i < timeWindow.getTimeslot().size();i++){
        for(Wavelength wav:fiber.getTimeList().get(i).getWavelengthList()) {
            if (wav.getCapacity()==0){
                flag = flag +1;
              }
          }
      }
        Pw = flag*3+46*flag*Constant.g;
        return Pw;
    }


    public FSRoute findFSwindowfirstfit(Connection connection, NodePair nodePair, LightPath lightPath, ArrayList<FSRoute> slotWindowList, Network layer){

       for (FSRoute slotWindow :  slotWindowList){
            LinearRoute newRoute = new LinearRoute(nodePair.getName(), 0);
            RouteSearching routesearching = new RouteSearching();
           routesearching.Dijkstra(nodePair.getSrcNode(), nodePair.getDesNode(), slotWindow, newRoute, slotWindow.getConstraint());
            //int size = newRoute.getFiberlist().size();
            if (newRoute.getFiberlist().size()!=0){
           //     System.out.println("该模型的阻塞率为：" +newRoute.getFiberlist().size());
//                int index = 0;
//                ArrayList<Fiber> fiberList = new ArrayList<>(newRoute.getFiberlist());
//                Collections.shuffle(fiberList);
                lightPath.setPhysicPath(newRoute);//这边只是用nodelist信息 没有记录nodepair uselightpath
           //     connection.setLightPath(lightPath);
                for (int i = 0 ; i < newRoute.getFiberlist().size(); i++){
                  connection.getRouteList().add( newRoute.getFiberlist().get(i));
                }

//                for (int i = 0)
//                layer.assignWavInRoute();

      //        nodePair.setUsedLightPath(lightPath);
                return slotWindow;
            }
        }
        return null;
    }


    public FSRoute findFSwindow(Connection connection, NodePair nodePair, LightPath lightPath, FSRoute slotWindow){
            LinearRoute newRoute = new LinearRoute(nodePair.getName(), 0);
            RouteSearching routesearching = new RouteSearching();
            routesearching.Dijkstra(nodePair.getSrcNode(), nodePair.getDesNode(), slotWindow, newRoute, slotWindow.getConstraint());
            //int size = newRoute.getFiberlist().size();
                //     System.out.println("该模型的阻塞率为：" +newRoute.getFiberlist().size());
//                int index = 0;
//                ArrayList<Fiber> fiberList = new ArrayList<>(newRoute.getFiberlist());
//                Collections.shuffle(fiberList);
                lightPath.setPhysicPath(newRoute);//这边只是用nodelist信息 没有记录nodepair uselightpath
//               connection.setLightPath(lightPath);
//                nodePair.setUsedLightPath(lightPath);
        for (int i = 0 ; i < newRoute.getFiberlist().size(); i++){
            connection.getRouteList().add( newRoute.getFiberlist().get(i));
        }
                return slotWindow;
    }

    private void assignTimeFSwindow1(Connection connection, Timeroute timeWindows, FSRoute slotWindow, NodePair nodePair, Network layer){

    }


    private void assignTimeFSwindow(Connection connection, Timeroute timeWindows, FSRoute slotWindow, NodePair nodePair,Network layer){
// lightPath.getPhysicPath().getFiberlist()中记录路由情况, 这边是否有可疑点
        LightPath lightPath = nodePair.getUsedLightPath(); //这边能否加入已经使用的path
//这边有疑问
        for (Fiber fiber: connection.getRouteList()) {
            for (int slotNum = timeWindows.getFirstSlotNum(); slotNum < timeWindows.getLastSlotNum(); slotNum++) {
              for (int FSNum = slotWindow.getFirstSlotNum();FSNum <=slotWindow.getLastSlotNum();FSNum++)
                {
                fiber.getTimeList().get(slotNum).getWavelengthList().get(FSNum).setCapacity(0.0);
                Integer index =   layer.getFiberList().indexOf(fiber);
                layer.getFiberList().get(index).getTimeList().get( slotNum ).getWavelengthList().get(FSNum ).setCapacity(0.0);
            }
            }
        }



        lightPath.setAssociatedtimeroute(timeWindows);
        lightPath.setAssociatedslotwindow(slotWindow); //要将时间slot属性加入lightpath中
        connection.setTimerouterecord(timeWindows);
        connection.setFSrouterecord(slotWindow);

    }



//    public HashMap<String, List<Node>> FSrouterecord(Connection connection){
//
//        for (int i = 0 ; i < this.getWavelengthList().size(); i ++) {
//            findFSRouteNode(this, connection);
//            int sizeofFSRoute =  findFSRouteNode(this, connection).size();
//            if (sizeofFSRoute !=0){
//                this.FSrouterecordMap.put(this.getWavelengthList().get(i).getName(), findFSRouteNode(this, connection));
//                this.FSrouterecordList.add(findFSRouteNode(this, connection));
//            }
//            else{
//                i++;
//            }
//
//        }
//        return  this.FSrouterecordMap;
//    }
//
//
//
//    public List<Node> findFSRouteNode(FSRoute FSrouteadapt, Connection connection) { // 这边应该是包含了相邻节点间的nodepair不同fiberbundles
//
////        FSRoute FSrouteadapt = new FSRoute("", index_FS);
//
//
//        boolean flag = true;
//
//        for (int j = 0; j < FSrouteadapt.getFiberList().size(); j++) {
//
//            Node scr = FSrouteadapt.getFiberList().get(j).getSrcNode();
//            Node des = FSrouteadapt.getFiberList().get(j).getDestNode();
////            if (FSrouteadapt.getFiberList().get(j).getWavelengthList().get(index_FS).getCapacity() == 0) {
//
//            List<Fiber> fberflaglist = this.findFiberList(scr, des);
//
//            for (int i = 0; i < fberflaglist.size(); i++) {
//                Wavelength FS = FSrouteadapt.getFiberList().get(i).getWavelengthList().get(0);
//                if (FS.getCapacity() != 0)
//                {
//                    flag = false;
//                    break;
//
//                }
//                //只要该nodepair中fiberbunlde存在可用波长就不去除相邻点，在nodepair间所有fiberbundle对于该FS都不可用的时候去除f相邻节点
//            }
//
//            if (!flag){
//            }
//            else {
//                scr.getAroundNodeList().remove(des);
//                scr.getAroundNodeMap().remove(des);
//                des.getAroundNodeList().remove(scr);
//                des.getAroundNodeMap().remove(scr);
//
//            }
//
//        }
//
//        Dijkstra dijkstra = new Dijkstra(connection.getSrc(), connection.getDest(), FSrouteadapt);
//        List<Node> pathList = dijkstra.getPathList();
//        return pathList;
//    }
}
