package org.khai.learning.math.solver;

public class GaussSystemSolver {
    private final double[][] system;
    private final int size;

    private double[] solution;

    public GaussSystemSolver(double[][] system) {
        this.system = system;
        this.size = system.length;
    }

    public double[] solve() {
        if (solution != null) {
            return solution;
        }
        for (int i = 0; i < size; ++i) {
            normalize(i);
            makeZeros(i);
        }
        solution = extractSolution();
        return solution;
    }

    private void normalize(int row) {
        double norm = system[row][row];
        for (int i = 0; i < size + 1; ++i) {
            system[row][i] /= norm;
        }
    }

    private void makeZeros(int row) {
        for (int i = row + 1; i < size; ++i) {
            double coef = system[i][row];
            extractRow(row, i, coef);
        }
    }

    private void extractRow(int base, int from, double coef) {
        for (int i = 0; i < size + 1; ++i) {
            system[from][i] -= coef * system[base][i];
        }
    }

    private double[] extractSolution() {
        double[] ans = new double[size];
        ans[size - 1] = system[size - 1][size];
        for (int i = size - 2; i >= 0; --i) {
            double root = system[i][size];
            for (int j = size - 1; j > i; --j) {
                root -= system[i][j] * ans[j];
            }
            ans[i] = root;
        }
        return ans;
    }

}
