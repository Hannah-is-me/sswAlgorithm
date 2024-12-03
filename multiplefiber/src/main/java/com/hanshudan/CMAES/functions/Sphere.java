package com.hanshudan.CMAES.functions;

import com.hanshudan.general.Constant;
import com.hanshudan.general.actionAllocation;
import com.hanshudan.network.Network;
import com.hanshudan.traffic.Traffic;

import java.io.IOException;
import java.util.*;

public class Sphere extends Function {
    private int fibernumber;
    private int fiberbundble;
    private static int threold = 1;
    public static int RandomSeed = 2;
    public static int K = 1; // K最短路径数
    public static  String netName =" 11node";
    public  static  Integer fiberName = 6;
    public  static int NodeNumber = 6;//11node 11, 14node 14, 6 node 6
    public  static int DegreeTotal = 16; // 11node 52, 14node 40, 6 node 16
    public static  double weightS = 0.005;
    public static  double weightL = 0.005;
    public static  double  weightcourt = 0.1;
    public HashMap<int[] , Double> laserInfoList = new HashMap<>();
    public HashMap<int[] , Double> WSSInfoList = new HashMap<>();

    public Sphere(){
        super();
    }
    public Sphere(HashMap<int[] , Double> laserInfoList, HashMap<int[] , Double> WSSInfoList){
        super();
        this.laserInfoList = laserInfoList;
        this.WSSInfoList = WSSInfoList;
    }
    public double getValue(double[] x)  {
        double v[] = new double [x.length];
        double vv=0;
        for (int i = 0; i < x.length; i++) {
            v[i] = Math.round (x[i] - bias[i]); // 四舍五入
        }
        vv=v[1]*v[0];

        fiberbundble=(int)v[0];
        fibernumber= (int)vv;

//		Network network = new Network("net", 0,fiberbundble,  fibernumber);
//		network.readTopology("D:\\phd\\Projects\\Muti_fiber networks\\JAVA\\RSWAStrategy\\multiplefiber_coreave\\multiplefiber\\src\\test\\java\\14.csv");
//		Traffic traffic = new Traffic(network,this);
//		network.rankActions();
//		network.doActionList();
//		traffic.printResult();
//
//		System.out.println("how many fibers bundled: " + fiberbundble+"how many fibers"+ fibernumber);
        Network  network2 = new Network("net2",0, 4, 1);

        int K = 1; //

        int flagselection = 6;

//        Network network2 = new Network("net2", 0, this.fiberbundble, this.fibernumber);
//        network2.readTopology("D:\\phd\\Projects\\Muti_fiber networks\\JAVA\\RSWAStrategy\\FSadaptivealgorithm\\multiplefiber\\src\\test\\java\\24.csv");
//        actionAllocation doaction2 = new actionAllocation();
//        doaction2.doactionAllocation(network2 , random, 1, this);



        network2 = new Network("net2",0, this.fibernumber,this.fiberbundble);
        network2.readTopology("D:\\phd\\Projects\\Muti_fiber networks\\JAVA\\Section2\\Section2\\FSwinadaptivenodepair(improve)\\FSwinadaptivenodepair(improve)\\FSwinadaptivenodepair(improve)\\FSwinadaptivenodepair(improve)\\FSwinadaptivenodepair(improve)\\FSwinadaptivenodepair(improve)\\multiplefiber\\src\\test\\java\\6.csv");
        System.out.print( this.fiberbundble+ ": " + this.fibernumber  + " ");
        System.out.println();
        actionAllocation doaction = new actionAllocation();
        Random random = new Random(RandomSeed);
        Traffic traffic = new Traffic(network2, Constant.time,random,this.fiberbundble, this.fibernumber);
        Map networkWeights = doaction.doactionAllocation(traffic, network2, random, Constant.time, flagselection, netName,this.fibernumber, NodeNumber,
                this.fiberbundble, this.fibernumber/this.fiberbundble, DegreeTotal, weightS, weightL, weightcourt,false,false,
               this.laserInfoList, this.WSSInfoList);
        Double net_cost = (Double) networkWeights.get("net_cost");

        System.out.println(" fiber number: " + this.fibernumber+", "+"Spa SCh granuarity: "+this.fiberbundble+", "+"Network cost"+net_cost);






        //	System.out.println("-------------------------------------------------------------------------------------------");

        return net_cost ;

    }

    public int getFibernumber() {
        return fibernumber;
    }

    public void setFibernumber(int fibernumber) {
        this.fibernumber = fibernumber;
    }

    public int getFiberbundble() {
        return fiberbundble;
    }

    public void setFiberbundble(int fiberbundble) {
        this.fiberbundble = fiberbundble;
    }

    public HashMap<int[], Double> getLaserInfoList() {
        return laserInfoList;
    }

    public void setLaserInfoList(HashMap<int[], Double> laserInfoList) {
        this.laserInfoList = laserInfoList;
    }

    public HashMap<int[], Double> getWSSInfoList() {
        return WSSInfoList;
    }

    public void setWSSInfoList(HashMap<int[], Double> WSSInfoList) {
        this.WSSInfoList = WSSInfoList;
    }
}
