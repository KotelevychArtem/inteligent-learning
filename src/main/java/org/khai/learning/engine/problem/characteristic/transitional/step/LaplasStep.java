package org.khai.learning.engine.problem.characteristic.transitional.step;

import org.khai.learning.engine.math.expression.impl.Polynomial;
import org.khai.learning.engine.math.solver.GaussSystemSolver;
import org.khai.learning.engine.problem.Step;
import org.khai.learning.engine.problem.characteristic.transitional.context.TransitionalContext;
import org.khai.learning.engine.problem.characteristic.transitional.context.LaplasContext;

import java.util.function.Function;

public class LaplasStep implements Step<TransitionalContext> {
    private static final Polynomial S = Polynomial.of(0, 1);

    private final Function<TransitionalContext, LaplasContext> solutionGetter;
    private final TransitionalContext context;

    private Polynomial aPart;
    private Polynomial bPart;
    private Polynomial cPart;

    private double[][] system;

    private LaplasContext laplasSolution;

    public LaplasStep(TransitionalContext context, Function<TransitionalContext, LaplasContext> solutionGetter) {
        this.context = context;
        this.solutionGetter = solutionGetter;
    }

    @Override
    public boolean checkAnswer(TransitionalContext answer) {
        return solutionGetter.apply(context).equals(solutionGetter.apply(answer));
    }

    @Override
    public TransitionalContext getAnswer() {
        if (laplasSolution == null) {
            solve();
        }
        return context;
    }

    private void solve() {
        double t1 = context.getTz1();
        double t2 = context.getTz2();
        Polynomial left = Polynomial.of(1, t1);
        Polynomial right = Polynomial.of(1, t2);
        aPart = (Polynomial) left.mul(right);
        bPart = (Polynomial) S.mul(right);
        cPart = (Polynomial) S.mul(left);
        initSystem();
        double[] solution = new GaussSystemSolver(system).solve();
        laplasSolution = solutionGetter.apply(context);
        laplasSolution
                .setA(solution[0])
                .setB(solution[1])
                .setC(solution[2])
                .setCoef1(solution[1] / t1)
                .setAlpha1(-1 / t1)
                .setCoef2(solution[2] / t2)
                .setAlpha2(-1 / t2);
    }

    private void initSystem() {
        system = new double[3][];
        for (int i = 0; i < 3; ++i) {
            system[i] = extractSystemRow(i);
        }
        system[0][3] = 1;
    }

    private double[] extractSystemRow(int row) {
        return new double[] {aPart.getCoef(row), bPart.getCoef(row), cPart.getCoef(row), 0};
    }
}
