package com.hanshudan.CMAES.basic;

import com.hanshudan.CMAES.functions.Function;

public class Individual implements Comparable {

    private static int counter = 0;
    private int id;

    public double[] genes;
    public double fitness;

    public Individual(int dim) {
        id = counter++;
        this.genes = new double[dim];
        this.fitness = Double.MAX_VALUE;
    }

    public Individual(double[] d) {
        id = counter++;
        this.genes = d;
        this.fitness = Double.MAX_VALUE;
    }

    public void randomInit() {
        for (int i = 0; i < genes.length; i++) {
            genes[i] = Params.rand.nextDouble();
        }
    }

    public double calcFitness(Function f) {
        if (Params.countEvals >= Params.MAX_EVALS) {
            return Double.MAX_VALUE;
        }

        Params.countEvals++;
        return f.getValue(f.deNormalize(genes));
    }

    public int getID() {
        return id;
    }

    public double getGene(int index) {
        return this.genes[index];
    }

    @Override
    public int compareTo(Object o) {
        // TODO Auto-generated method stub
        Individual other = (Individual) o;
        return Double.valueOf(this.fitness).compareTo(other.fitness);
    }

}


