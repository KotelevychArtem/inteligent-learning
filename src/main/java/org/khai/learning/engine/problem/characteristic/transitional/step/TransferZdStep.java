package org.khai.learning.engine.problem.characteristic.transitional.step;

import org.khai.learning.engine.math.expression.Expression;
import org.khai.learning.engine.math.expression.impl.Fraction;
import org.khai.learning.engine.math.expression.impl.Polynomial;
import org.khai.learning.engine.problem.Step;
import org.khai.learning.engine.problem.characteristic.transitional.context.TransitionalContext;

import java.util.Objects;

public class TransferZdStep implements Step<TransitionalContext> {
    private final TransitionalContext context;
    private Fraction formula;

    public TransferZdStep(TransitionalContext context) {
        this.context = context;
    }

    @Override
    public boolean checkAnswer(TransitionalContext answer) {
        return Objects.equals(formula, answer.getfZd());
    }

    @Override
    public TransitionalContext getAnswer() {
        if (formula == null) {
            solve();
        }
        return context;
    }

    private void solve() {
        Expression wdu =
                Polynomial.of(context.getKdu())
                        .div(Polynomial.of(1, context.getT1()).mul(Polynomial.of(1, context.getT2())));
        Expression upperPart = Polynomial.of(context.getWpe()).mul(Polynomial.of(context.getWpp())).mul(wdu).mul(Polynomial.of(context.getWtg()));

        Expression lowerPart = Polynomial.of(1).add(
                upperPart.mul(Polynomial.of(context.getWke()))
        );

        formula = (Fraction) upperPart.div(lowerPart);
        Polynomial coef  = Polynomial.of(((Polynomial) formula.getDenom()).getCoef(0));
        formula = Fraction.of(formula.getNum().div(coef), formula.getDenom().div(coef));
        context.setfZd(formula);
        context.setCharEquation((Polynomial) formula.getDenom());
        context.getLaplasZd().setOuterCoef(((Polynomial) formula.getNum()).getCoef(0) * context.getUz());
    }
}

