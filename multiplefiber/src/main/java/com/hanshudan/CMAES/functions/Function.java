package com.hanshudan.CMAES.functions;

/**
 * @author cuonglv.hust@gmail.com
 * @date 24/02/2021
 *
 */
public abstract class Function {

    public int dim; // dimension
    public double[] LB;
    public double[] UB;

    public double[] bias;
    public double[][] matrix;

    public Function() {

    }

    public Function(int dim, double[] shift, double[][] rotation, double[] lb, double[] ub) {
        this.dim = dim;
        this.bias = shift;
        this.matrix = rotation;
        this.UB = ub;
        this.LB = lb;
    }

    public double[] deNormalize(double[] y) {
        double x[] = new double[dim];
        for (int i = 0; i < dim; i++) {
            if (y[i] < 0)
                y[i] = 0;
            if (y[i] > 1)
                y[i] = 1;
            x[i] = y[i] * (UB[i] - LB[i]) + LB[i];
        }
        return x;
    }

    public abstract double getValue(double[] solution);// getValue of the function

}
