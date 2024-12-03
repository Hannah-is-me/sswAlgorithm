package com.hanshudan.Optimization;

import com.hanshudan.general.Constant;
import com.hanshudan.general.IPDataAcquired;
import com.hanshudan.general.actionAllocation;
import com.hanshudan.network.Network;
import com.hanshudan.traffic.Action;
import com.hanshudan.traffic.Event;
import com.hanshudan.traffic.Traffic;

import java.io.IOException;
import java.util.*;

public class Solver {

    private ArrayList<Integer> fibernumber;  // [8,6];
    private Map<Integer,ArrayList<Integer>> gnumber; // [1,2,4,8],[1,2,3,6]
    private static int threold = 1;
    public static int RandomSeed = 2;
    public static int K = 1; // K最短路径数
    public static  String netName =" 6node";
    public  static  Integer fiberName = 6;
    public  static int NodeNumber = 6;
    public  static int DegreeTotal = 16;// 6-node 16, 11-node 48, 14-node 40
    public static  double weightS = 0.005;
    public static  double weightL = 0.005;
    public static  double  weightcourt = 0.001;

    public Solver() {
        this.fibernumber = new ArrayList<>();
        this.gnumber = new HashMap<>();

    }

    public static void main(String[] args) throws IOException {

        int flagselection = 4;


        int[] index = new int[2];
        int curThreold = 0;
        Solver solver = new Solver();
        Map<Integer[],Double>  preUpdate = new HashMap<>();
        for (int i = 0; i < Constant.fiberNums.length; i++) {
            Integer[] nums = Constant.gNum.get(Constant.fiberNums[i]);
            for (int j = 0; j < nums.length; j++) {
                preUpdate.put(new Integer[]{Constant.fiberNums[i],nums[j]},0.0);
            }
        }

        Network network4 = new Network("net4", 0,4,1);
  //      network4.readTopology("D:\\tianyangpeng\\Section2\\FSwinadaptivenodepair(improve)\\FSwinadaptivenodepair(improve)\\FSwinadaptivenodepair(improve)\\FSwinadaptivenodepair(improve)\\multiplefiber\\src\\test\\java\\6.csv");

        long startTime = System.currentTimeMillis();


       while (curThreold < threold && preUpdate.size() > 1) {
           double min_cost = Double.MAX_VALUE;
           Map<Integer[],Double> update1 = new HashMap<>();
           Map<Integer[],Double> update2 = new HashMap<>();
           for (Integer[] fiberNum : preUpdate.keySet() ) {

                network4 = new Network("net4",0, fiberNum[0], fiberNum[1]);
                network4.readTopology("D:\\phd\\ProjectsF\\FSwinadaptivenodepair(improve)\\multiplefiber\\src\\test\\java\\14.csv");


                   System.out.print(fiberNum[0] + ": " + fiberNum[1]  + " ");
                   System.out.println();
                    actionAllocation doaction = new actionAllocation();
                    Random random = new Random(RandomSeed);
                    Traffic traffic = new Traffic(network4,Constant.time,random,fiberNum[0], fiberNum[1]);
                   Map networkWeights = doaction.doactionAllocation(traffic, network4, random, Constant.time, flagselection, netName, fiberNum[0], NodeNumber,
                           fiberNum[1], fiberNum[0]/fiberNum[1], DegreeTotal, weightS, weightL, weightcourt,false,true);
                   Double net_cost = (Double) networkWeights.get("net_cost");
                   update1.put(new Integer[]{fiberNum[0],fiberNum[1]},net_cost);
                   System.out.print(net_cost + " ");
                   System.out.println();
                   if (net_cost < min_cost) {
                       min_cost = net_cost;

                   }
               System.out.println();


           }
           Double threadCost = min_cost*2;
           for (Integer[] fiberNum : update1.keySet() ) {
               Double net_cost = update1.get(fiberNum);
               if(net_cost <= threadCost){
                   update2.put(fiberNum,net_cost);
               }
           }
           preUpdate = update2;
           curThreold++;


       }
        System.out.println(preUpdate);
        System.out.println();
        long endTime = System.currentTimeMillis();


        List<Action> actionList = network4.getActionList();
        System.out.println("程序运行时间： "+(endTime-startTime)/1000+"s");
        //  actionList  = getRandomThreeInfoList(actionList,120);

        System.out.println("----------------------------------------------------------------------------");
        IPDataAcquired dataput = new IPDataAcquired(actionList  ,network4, "6-node", Constant.time);
        System.out.println("----------------------------------------------------------------------------");



    }





}
