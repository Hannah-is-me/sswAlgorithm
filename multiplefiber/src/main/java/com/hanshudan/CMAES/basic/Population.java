package com.hanshudan.CMAES.basic;

import com.hanshudan.CMAES.functions.Function;

import java.util.ArrayList;
import java.util.Collections;

public class Population {

    public int dim;
    public ArrayList<Individual> individuals;
    public Function func;

    public Population(int dim, Function func) {
        this.dim = dim;
        this.func = func;
        this.individuals = new ArrayList<Individual>();
    }

    public Individual randomInit(int size) {
        this.individuals.clear();
        Individual best = null;

        while (this.individuals.size() < size) {
            Individual indiv = new Individual(this.dim);
            for (int i = 0; i < this.dim; i++) {
                indiv.genes[i] = Params.rand.nextDouble();
            }

            indiv.fitness = indiv.calcFitness(func);
            this.individuals.add(indiv);

            if (best == null || best.fitness > indiv.fitness) {
                best = indiv;
            }
        }
        return best;
    }

    @SuppressWarnings("unchecked")
    public void sort() {
        Collections.sort(this.individuals);
    }

    public void clear() {
        this.individuals.clear();
    }

    public void addIndividual(Individual indiv) {
        this.individuals.add(indiv);
    }

    public Individual getIndividual(int index) {
        return this.individuals.get(index);
    }
}


