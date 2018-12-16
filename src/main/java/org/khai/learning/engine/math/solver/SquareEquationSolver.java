package org.khai.learning.engine.math.solver;

import org.khai.learning.engine.math.expression.impl.Polynomial;

public class SquareEquationSolver {
    private final Polynomial equation;

    public SquareEquationSolver(Polynomial equation) {
        assert equation.getRank() == 2;
        this.equation = equation;
    }

    public double[] solve() {
        if (equation.getCoef(2) == 0) {
            double root = -equation.getCoef(0) / equation.getCoef(1);
            return new double[] {root, root};
        }
        double d = calculateD();
        if (d < 0) {
            return new double[0];
        }
        double[] roots = new double[2];
        double a = equation.getCoef(2);
        double b = equation.getCoef(1);
        roots[0] = (-b + Math.sqrt(d)) / (2 * a);
        roots[1] = (-b - Math.sqrt(d)) / (2 * a);
        return roots;
    }

    private double calculateD() {
        double b = equation.getCoef(1);
        return b * b - 4 * equation.getCoef(2) * equation.getCoef(0);
    }
}
