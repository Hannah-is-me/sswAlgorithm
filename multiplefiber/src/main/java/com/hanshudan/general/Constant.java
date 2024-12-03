package com.hanshudan.general;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Constant {
// public  static  final int[] fiberNums = {6,8,9,10,12,14,15};
 public  static  final int[] fiberNums = {6};
 //public  static  final int[] fiberNums = {4,6,8,10,12,14,16,18,20};

//   public  static  final Map<Integer,Integer[]> gNum =
//           new HashMap<Integer,Integer[]>(){{ put(6, new Integer[]{1, 2, 3, 6}); };};


//  public  static  final Map<Integer,Integer[]> gNum =
//           new HashMap<Integer,Integer[]>(){{  put(6, new Integer[]{3,2,1,6});put(8, new Integer[]{4,2,1,8});
//            put(10, new Integer[]{5,2,1,10});put(9, new Integer[]{3,1,8});put(9, new Integer[]{3,1,8});
//            put(12, new Integer[]{12,2,6,3,4,1}); put(14, new Integer[]{14,2,7,1}); put(15, new Integer[]{15,3,5,1});};};

 public  static  final Map<Integer,Integer[]> gNum =
         new HashMap<Integer,Integer[]>(){{  put(6, new Integer[]{6,3,2,1});}};

   //new HashMap<Integer,Integer[]>(){{put(6, new Integer[]{1, 2, 3, 6});
  // put(8, new Integer[]{1, 2, 4, 8}); put(4, new Integer[]{1, 2, 4}); put(10, new Integer[]{1, 2, 5, 10}); put(12, new Integer[]{1, 2, 3, 4, 6, 12}); put(9, new Integer[]{1, 3, 3, 9});
  //put(14, new Integer[]{1, 2, 7, 14}); put(16, new Integer[]{1, 2, 8, 16}); put(18, new Integer[]{1, 2, 9, 18}); put(20, new Integer[]{1, 2, 4, 5,10, 20});};};



   public  static  final int time = 5; // timebase time=3(CMA-ES)

    public static final  int  timeslotNum = 1;//开始时间 // timeslotNum=6(CMA-ES)
    public static final int timetotalnum = 12;

    public static final  int  timeslotcapacity = 1;// time slot 基本单位

    public static final int FSslotNumlowbound = 1; // 业务Capacity下限
    public static final int FSslotNumhighbound = 8; // 业务Capacity上限

    public static final double wavCapacity = 20;
    public static final double wavCapacitygrid = 15;
    //public static final int fiberNum =6;
    public static  final int g = 6;
   // public static  final int SwitchGroup= fiberNum /g;
    public static final int wavNum = 20;//304
    public static final int EventNum = 4;  //14node:110*4    11node:110*4
    public static final double lambda =1;
    public static final double mu = 1;
    public static final int InSwitch = 1;
    public static final int FrJSwitch = 2;
    public static final int JSwitch = 3;
    public static final boolean SLC = false;
    public static final double INF = 100000;
    public static final int Unvisited = 0;
    public static final int VisitedTwice = 2;
    public static final int VisitedOnce = 1;
    public static final int Ingress_portsNum = 18;
    public static final int Egress_portsNum = 18;

    public static final int waiting = 0;
    public static final int doing = 1;
    public static final int blocking = 2;
    public static final int finishing = 3;
    public static int BlockNum = 0;

    public static int generation_Num = 20;
    public static double bandwidth = 0;
    /**
     * action information
     */
    public static final int arrival = 0;
    public static final int leave = 1;



}
