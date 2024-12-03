package com.hanshudan.Optimization;

import com.hanshudan.general.Constant;
import com.hanshudan.general.IPDataAcquired;
import com.hanshudan.general.actionAllocation;
import com.hanshudan.network.Network;
import com.hanshudan.traffic.Action;
import com.hanshudan.traffic.Traffic;

import java.util.*;

class fitnessCost {
    public  static void main (String args[]) {
        int RandomSeed = 2;
        int fibernumber = 6;
        int fiberbundble= 6;
        Network network2 = new Network("net2",0, 4, 1);
        int threold = 1;

        int K = 1; // K最短路径数
        String netName =" 11node";
        Integer fiberName = 11;
        int NodeNumber = 11;//11node 11, 14node 14, 6 node 6
        int DegreeTotal = 52; // 11node 52, 14node 40, 6 node 16
        double weightS = 0.005;
        double weightL = 0.005;
        double  weightcourt = 0.1;




        IPDataAcquired data = new IPDataAcquired();
        HashMap<int[] , Double> wssInfoList = data.readWSSInfo("D:\\phd\\Projects\\Muti_fiber networks\\JAVA\\Section2\\Section2\\FSwinadaptivenodepair(improve)\\FSwinadaptivenodepair(improve)\\FSwinadaptivenodepair(improve)\\FSwinadaptivenodepair(improve)\\FSwinadaptivenodepair(improve)\\FSwinadaptivenodepair(improve)\\multiplefiber\\src\\test\\java\\WSSCost.csv");
        for(Map.Entry<int[] , Double> entry : wssInfoList.entrySet()){
            int[] WSSarr = entry.getKey();
            double WSScost = entry.getValue();
            System.out.println(WSSarr[0] + " " + WSSarr[1] + " " + WSScost);
        }


        HashMap<int[] , Double> laserInfoList = data.readWSSInfo("D:\\phd\\Projects\\Muti_fiber networks\\JAVA\\Section2\\Section2\\FSwinadaptivenodepair(improve)\\FSwinadaptivenodepair(improve)\\FSwinadaptivenodepair(improve)\\FSwinadaptivenodepair(improve)\\FSwinadaptivenodepair(improve)\\FSwinadaptivenodepair(improve)\\multiplefiber\\src\\test\\java\\LaserCost.csv");
        for(Map.Entry<int[] , Double> entry : laserInfoList.entrySet()){
            int[] laserarr = entry.getKey();
            double lasercost = entry.getValue();
            System.out.println(laserarr[0] + " " + laserarr[1] + " " + lasercost);
        }

        int flagselection = 6;

//        Network network2 = new Network("net2", 0, this.fiberbundble, this.fibernumber);
//        network2.readTopology("D:\\phd\\Projects\\Muti_fiber networks\\JAVA\\RSWAStrategy\\FSadaptivealgorithm\\multiplefiber\\src\\test\\java\\24.csv");
//        actionAllocation doaction2 = new actionAllocation();
//        doaction2.doactionAllocation(network2 , random, 1, this);



        network2 = new Network("net2",0, fibernumber,fiberbundble);
        network2.readTopology("D:\\phd\\Projects\\Muti_fiber networks\\JAVA\\Section2\\Section2\\FSwinadaptivenodepair(improve)\\FSwinadaptivenodepair(improve)\\FSwinadaptivenodepair(improve)\\FSwinadaptivenodepair(improve)\\FSwinadaptivenodepair(improve)\\FSwinadaptivenodepair(improve)\\multiplefiber\\src\\test\\java\\11.csv");
        System.out.print( fiberbundble+ ": " + fibernumber  + " ");
        System.out.println();

        Random random = new Random(RandomSeed);

        Traffic traffic = new Traffic(network2, Constant.time,random,fiberbundble, fibernumber);

        ArrayList<Double> costrecord  = new ArrayList<Double>();

     for (int i = 0; i<200;i++){
         Collections.shuffle(network2.getActionList());
         actionAllocation doaction = new actionAllocation();
        Map networkWeights = doaction.doactionAllocation(traffic, network2, random, Constant.time, flagselection, netName, fibernumber, NodeNumber,
                fiberbundble, fibernumber/fiberbundble, DegreeTotal, weightS, weightL, weightcourt,false,false,
                laserInfoList, wssInfoList);
        Double net_cost = (Double) networkWeights.get("net_cost");
          // netcost 于系数相乘?
        System.out.println(" fiber number: " + fibernumber+", "+"Spa SCh granuarity: "+fiberbundble+", "+"Network cost"+net_cost);
         costrecord.add(net_cost );
     }



        double max = calculateMax(  costrecord);
        double min = calculateMin(   costrecord);
        double average = calculateAverage(   costrecord);
        double range = calculateRange(   costrecord);
        double variance = calculateVariance(costrecord);

        System.out.println("最大值: " + max);
        System.out.println("最小值: " + min);
        System.out.println("平均值: " + average);
        System.out.println("反差: " + range);
        System.out.println("方差: " + variance);

    }


    public static double calculateMax(List<Double> numbers) {
        return numbers.stream().max(Double::compare).get();
    }

    public static double calculateMin(List<Double> numbers) {
        return numbers.stream().min(Double::compare).get();
    }

    public static double calculateAverage(List<Double> numbers) {
        return numbers.stream().mapToDouble(Double::doubleValue).average().getAsDouble();
    }

    public static double calculateRange(List<Double> numbers) {
        return calculateMax(numbers) - calculateMin(numbers);
    }





        public static double calculateVariance(List<Double> numbers) {
            if (numbers.isEmpty()) {
                throw new IllegalArgumentException("List must not be empty");
            }

            double mean = numbers.stream().mapToDouble(Double::doubleValue).average().getAsDouble();
            double variance = numbers.stream()
                    .map(n -> Math.pow(n - mean, 2))
                    .mapToDouble(Double::doubleValue)
                    .sum();

            return variance / numbers.size();
        }






}


