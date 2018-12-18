package org.khai.learning.engine.problem.characteristic.transitional.step;

import org.khai.learning.engine.math.MathUtils;
import org.khai.learning.engine.math.solver.SquareEquationSolver;
import org.khai.learning.engine.problem.Step;
import org.khai.learning.engine.problem.characteristic.transitional.context.TransitionalContext;

public class CharacteristicEquationStep implements Step<TransitionalContext> {
    private TransitionalContext context;
    private double[] roots;

    public CharacteristicEquationStep(TransitionalContext context) {
        this.context = context;
    }

    @Override
    public boolean checkAnswer(TransitionalContext answer) {
        return context.getTz1() == answer.getTz1() && context.getTz1() == answer.getTz1();
    }

    @Override
    public TransitionalContext getAnswer() {
        if (roots == null) {
            solve();
        }
        return context;
    }

    private void solve() {
        roots = new SquareEquationSolver(context.getCharEquation()).solve();
        context.setCharRoot1(roots[0]);
        context.setCharRoot2(roots[1]);
        context.setTz1(-1.0 / roots[0]);
        context.setTz2(-1.0 / roots[1]);
    }
}
