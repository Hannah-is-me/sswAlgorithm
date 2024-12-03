package com.hanshudan.CMAES.utils;

import com.hanshudan.CMAES.basic.Params;

public class Utils {

    public static double sum(double[] x) {
        double d = 0;

        for (double val : x) {
            d += val;
        }

        return d;
    }

    public static double sumSq(double[] x) {
        double d = 0;

        for (double val : x) {
            d += val * val;
        }

        return d;
    }

    public static double max(double[] x) {
        double max = -Double.MAX_VALUE;
        for (double val : x) {
            max = max < val ? val : max;
        }
        return max;
    }

    public static double min(double[] x) {
        double min = Double.MAX_VALUE;
        for (double val : x) {
            min = min > val ? val : min;
        }
        return min;
    }

    public static double[] ones(int n, double scale) {
        double[] matrix = new double[n];
        for (int i = 0; i < n; i++) {
            matrix[i] = 1 * scale;
        }
        return matrix;
    }

    public static double[][] i_matrix(int n) {
        double[][] matrix = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                if (i == j) {
                    matrix[i][j] = (int) 1;
                } else {
                    matrix[i][j] = matrix[j][i] = (int) 0;
                }
            }

        }
        return matrix;
    }

    double cauchy_g(double mu, double gamma) {
        return mu + gamma * Math.tan(Math.PI * (Params.rand.nextDouble() - 0.5));
    }

    double gauss(double mu, double sigma) {
        return mu + sigma * Math.sqrt(-2.0 * Math.log(Params.rand.nextDouble()))
                * Math.sin(2.0 * Math.PI * Params.rand.nextDouble());
    }

    // The remaining functions in this file are copied almost verbatim from
    // Nikolaus Hansen's Java implementation.

    /**
     * Symmetric Householder reduction to tridiagonal form, taken from JAMA package.
     *
     * This is derived from the Algol procedures tred2 by Bowdler, Martin, Reinsch,
     * and Wilkinson, Handbook for Auto. Comp., Vol.ii-Linear Algebra, and the
     * corresponding Fortran subroutine in EISPACK.
     */
    public static void tred2(int n, double[][] V, double[] d, double[] e) {
        for (int j = 0; j < n; j++) {
            d[j] = V[n - 1][j];
        }

        // Householder reduction to tridiagonal form.
        for (int i = n - 1; i > 0; i--) {

            // Scale to avoid under/overflow.
            double scale = 0.0;
            double h = 0.0;
            for (int k = 0; k < i; k++) {
                scale = scale + Math.abs(d[k]);
            }
            if (scale == 0.0) {
                e[i] = d[i - 1];
                for (int j = 0; j < i; j++) {
                    d[j] = V[i - 1][j];
                    V[i][j] = 0.0;
                    V[j][i] = 0.0;
                }
            } else {
                // Generate Householder vector.
                for (int k = 0; k < i; k++) {
                    d[k] /= scale;
                    h += d[k] * d[k];
                }
                double f = d[i - 1];
                double g = Math.sqrt(h);
                if (f > 0) {
                    g = -g;
                }
                e[i] = scale * g;
                h = h - f * g;
                d[i - 1] = f - g;
                for (int j = 0; j < i; j++) {
                    e[j] = 0.0;
                }

                // Apply similarity transformation to remaining columns.
                for (int j = 0; j < i; j++) {
                    f = d[j];
                    V[j][i] = f;
                    g = e[j] + V[j][j] * f;
                    for (int k = j + 1; k <= i - 1; k++) {
                        g += V[k][j] * d[k];
                        e[k] += V[k][j] * f;
                    }
                    e[j] = g;
                }
                f = 0.0;
                for (int j = 0; j < i; j++) {
                    e[j] /= h;
                    f += e[j] * d[j];
                }
                double hh = f / (h + h);
                for (int j = 0; j < i; j++) {
                    e[j] -= hh * d[j];
                }
                for (int j = 0; j < i; j++) {
                    f = d[j];
                    g = e[j];
                    for (int k = j; k <= i - 1; k++) {
                        V[k][j] -= (f * e[k] + g * d[k]);
                    }
                    d[j] = V[i - 1][j];
                    V[i][j] = 0.0;
                }
            }
            d[i] = h;
        }

        // Accumulate transformations.
        for (int i = 0; i < n - 1; i++) {
            V[n - 1][i] = V[i][i];
            V[i][i] = 1.0;
            double h = d[i + 1];
            if (h != 0.0) {
                for (int k = 0; k <= i; k++) {
                    d[k] = V[k][i + 1] / h;
                }
                for (int j = 0; j <= i; j++) {
                    double g = 0.0;
                    for (int k = 0; k <= i; k++) {
                        g += V[k][i + 1] * V[k][j];
                    }
                    for (int k = 0; k <= i; k++) {
                        V[k][j] -= g * d[k];
                    }
                }
            }
            for (int k = 0; k <= i; k++) {
                V[k][i + 1] = 0.0;
            }
        }
        for (int j = 0; j < n; j++) {
            d[j] = V[n - 1][j];
            V[n - 1][j] = 0.0;
        }
        V[n - 1][n - 1] = 1.0;
        e[0] = 0.0;
    }

    /**
     * Symmetric tridiagonal QL algorithm, taken from JAMA package.
     *
     * This is derived from the Algol procedures tql2, by Bowdler, Martin, Reinsch,
     * and Wilkinson, Handbook for Auto. Comp., Vol.ii-Linear Algebra, and the
     * corresponding Fortran subroutine in EISPACK.
     */
    public static void tql2(int n, double[] d, double[] e, double[][] V) {
        for (int i = 1; i < n; i++) {
            e[i - 1] = e[i];
        }
        e[n - 1] = 0.0;

        double f = 0.0;
        double tst1 = 0.0;
        double eps = Math.pow(2.0, -52.0);
        for (int l = 0; l < n; l++) {
            // Find small subdiagonal element
            tst1 = Math.max(tst1, Math.abs(d[l]) + Math.abs(e[l]));
            int m = l;
            while (m < n) {
                if (Math.abs(e[m]) <= eps * tst1) {
                    break;
                }
                m++;
            }

            // If m == l, d[l] is an eigenvalue,
            // otherwise, iterate.
            if (m > l) {
                int iter = 0;
                do {
                    iter = iter + 1; // (Could check iteration count here.)

                    // Compute implicit shift
                    double g = d[l];
                    double p = (d[l + 1] - g) / (2.0 * e[l]);
                    double r = hypot(p, 1.0);
                    if (p < 0) {
                        r = -r;
                    }
                    d[l] = e[l] / (p + r);
                    d[l + 1] = e[l] * (p + r);
                    double dl1 = d[l + 1];
                    double h = g - d[l];
                    for (int i = l + 2; i < n; i++) {
                        d[i] -= h;
                    }
                    f = f + h;

                    // Implicit QL transformation.
                    p = d[m];
                    double c = 1.0;
                    double c2 = c;
                    double c3 = c;
                    double el1 = e[l + 1];
                    double s = 0.0;
                    double s2 = 0.0;
                    for (int i = m - 1; i >= l; i--) {
                        c3 = c2;
                        c2 = c;
                        s2 = s;
                        g = c * e[i];
                        h = c * p;
                        r = hypot(p, e[i]);
                        e[i + 1] = s * r;
                        s = e[i] / r;
                        c = p / r;
                        p = c * d[i] - s * g;
                        d[i + 1] = h + s * (c * g + s * d[i]);

                        // Accumulate transformation.
                        for (int k = 0; k < n; k++) {
                            h = V[k][i + 1];
                            V[k][i + 1] = s * V[k][i] + c * h;
                            V[k][i] = c * V[k][i] - s * h;
                        }
                    }
                    p = -s * s2 * c3 * el1 * e[l] / dl1;
                    e[l] = s * p;
                    d[l] = c * p;

                    // Check for convergence.
                } while (Math.abs(e[l]) > eps * tst1);
            }
            d[l] = d[l] + f;
            e[l] = 0.0;
        }

        // Sort eigenvalues and corresponding vectors.
        for (int i = 0; i < n - 1; i++) {
            int k = i;
            double p = d[i];
            for (int j = i + 1; j < n; j++) {
                if (d[j] < p) { // NH find smallest k>i
                    k = j;
                    p = d[j];
                }
            }
            if (k != i) {
                d[k] = d[i]; // swap k and i
                d[i] = p;
                for (int j = 0; j < n; j++) {
                    p = V[j][i];
                    V[j][i] = V[j][k];
                    V[j][k] = p;
                }
            }
        }
    }

    /**
     * Compute sqrt(a^2 + b^2) without under/overflow.
     */
    private static double hypot(double a, double b) {
        double r = 0;
        if (Math.abs(a) > Math.abs(b)) {
            r = b / a;
            r = Math.abs(a) * Math.sqrt(1 + r * r);
        } else if (b != 0) {
            r = a / b;
            r = Math.abs(b) * Math.sqrt(1 + r * r);
        }
        return r;
    }

    /**
     * Exhaustive test of the output of the eigendecomposition. Needs O(n^3)
     * operations.
     *
     * @return the number of detected inaccuracies
     */
    public static int checkEigenSystem(int N, double[][] C, double[] diag, double[][] Q) {
        /* compute Q diag Q^T and Q Q^T to check */
        int i;
        int j;
        int k;
        int res = 0;
        double cc;
        double dd;

        for (i = 0; i < N; ++i) {
            for (j = 0; j < N; ++j) {
                for (cc = 0., dd = 0., k = 0; k < N; ++k) {
                    cc += diag[k] * Q[i][k] * Q[j][k];
                    dd += Q[i][k] * Q[j][k];
                }
                /* check here, is the normalization the right one? */
                if (Math.abs(cc - C[i > j ? i : j][i > j ? j : i]) / Math.sqrt(C[i][i] * C[j][j]) > 1e-10
                        && Math.abs(cc - C[i > j ? i : j][i > j ? j : i]) > 1e-9) { /* quite large */
                    System.err.println("imprecise result detected " + i + " " + j + " " + cc + " "
                            + C[i > j ? i : j][i > j ? j : i] + " " + (cc - C[i > j ? i : j][i > j ? j : i]));
                    ++res;
                }
                if (Math.abs(dd - (i == j ? 1 : 0)) > 1e-10) {
                    System.err.println("imprecise result detected (Q not orthog.) " + i + " " + j + " " + dd);
                    ++res;
                }
            }
        }
        return res;
    }
}


