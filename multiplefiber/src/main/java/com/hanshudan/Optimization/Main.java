package com.hanshudan.Optimization;

import com.hanshudan.CMAES.basic.Individual;
import com.hanshudan.CMAES.basic.Params;
import com.hanshudan.CMAES.functions.Function;
import com.hanshudan.CMAES.functions.Sphere;
import com.hanshudan.CMAES.utils.Utils;
import com.hanshudan.general.IPDataAcquired;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Main {
    public static void main(String args[]) {
        // setup benchmark



        IPDataAcquired data = new IPDataAcquired();



        HashMap<int[] , Double> laserInfoList = data.readWSSInfo("D:\\phd\\ProjectsF\\FSwinadaptivenodepair(improve)\\multiplefiber\\src\\test\\java\\LaserCost.csv");
        for(Map.Entry<int[] , Double> entry : laserInfoList.entrySet()){
            int[] laserarr = entry.getKey();
            double lasercost = entry.getValue();
            System.out.println(laserarr[0] + " " + laserarr[1] + " " + lasercost);
        }

        HashMap<int[] , Double> wssInfoList = data.readWSSInfo("D:\\phd\\ProjectsF\\FSwinadaptivenodepair(improve)\\multiplefiber\\src\\test\\java\\WSSCost.csv");
        for(Map.Entry<int[] , Double> entry : wssInfoList.entrySet()){
            int[] WSSarr = entry.getKey();
            double WSScost = entry.getValue();
            System.out.println(WSSarr[0] + " " + WSSarr[1] + " " + WSScost);
        }


        Function func;


        func = new Sphere( laserInfoList , wssInfoList );
        func.dim = 2;
        func.LB = Utils.ones(func.dim, 2);
        func.UB = Utils.ones(func.dim, 5);
        func.bias = new double[func.dim];
        func.matrix = Utils.i_matrix(func.dim);
        for (int i = 0; i < func.dim; i++) {
            func.bias[i] = 0;
        }



//		func = new Rastrigin();
//		func.dim = 50;
//		func.LB = Utils.ones(func.dim, -50);
//		func.UB = Utils.ones(func.dim, 50);
//		func.bias = new double[func.dim];
//		func.matrix = Utils.i_matrix(func.dim);
//		int m = func.dim / 2;
//		for (int i = 0; i < m; i++) {
//			func.bias[i] = 40;
//		}
//		for (int i = m; i < func.dim; i++) {
//			func.bias[i] = -40;
//		}

        Params.rand = new Random(0);
        int lambda = (int) (4 + 3 * Math.floor(Math.log(func.dim)));
        CMAES solver = new CMAES(func.dim, func, lambda, false);
        solver.setIteration(100);
        solver.initialize();
        Individual best = solver.run();


        System.out.println("Best solution found: " + best.fitness+"the best local value is"+ (Arrays.toString(best.genes)));


    }
}
