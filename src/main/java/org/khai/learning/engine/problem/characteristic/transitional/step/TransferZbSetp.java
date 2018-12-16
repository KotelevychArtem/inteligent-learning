package org.khai.learning.engine.problem.characteristic.transitional.step;

import org.khai.learning.engine.math.expression.Expression;
import org.khai.learning.engine.math.expression.impl.Fraction;
import org.khai.learning.engine.math.expression.impl.Polynomial;
import org.khai.learning.engine.problem.Step;
import org.khai.learning.engine.problem.characteristic.transitional.context.TransitionalContext;

import java.util.Objects;

public class TransferZbSetp implements Step<TransitionalContext> {
    private final TransitionalContext context;
    private Fraction formula;

    public TransferZbSetp(TransitionalContext context) {
        this.context = context;
    }

    @Override
    public boolean checkAnswer(TransitionalContext answer) {
        return Objects.equals(formula, answer.getfZb());
    }

    @Override
    public TransitionalContext getAnswer() {
        solve();
        return context;
    }

    private void solve() {
        if (formula != null) return;
        Expression denomMulExpr = Polynomial.of(1, context.getT1()).mul(Polynomial.of(1, context.getT2()));
        Expression wdw =
                Polynomial.of(context.getKdw())
                        .div(denomMulExpr);
        Expression wdu =
                Polynomial.of(context.getKdu())
                        .div(denomMulExpr);
        Expression lowerPart = Polynomial.of(1).add(
                Polynomial.of(context.getWpe()).mul(Polynomial.of(context.getWpp())).mul(wdu).mul(Polynomial.of(context.getWtg())).mul(Polynomial.of(context.getWke())));

        formula = (Fraction) wdw.mul(Polynomial.of(context.getWtg())).div(lowerPart);
        Polynomial coef  = Polynomial.of(((Polynomial) formula.getDenom()).getCoef(0));
        formula = Fraction.of(formula.getNum().div(coef), formula.getDenom().div(coef));
        context.setfZb(formula);
        context.getLaplasZb().setOuterCoef(((Polynomial) formula.getNum()).getCoef(0) * context.getMn());
    }
}

