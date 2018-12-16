package org.khai.learning.engine.problem.characteristic.transitional.step;

import org.khai.learning.engine.math.MathUtils;
import org.khai.learning.engine.problem.Step;
import org.khai.learning.engine.problem.characteristic.transitional.context.TransitionalContext;

public class SystemCharacteristicStep implements Step<TransitionalContext> {
    private static final double DELTA = 0.05;

    private final TransitionalContext context;

    public SystemCharacteristicStep(TransitionalContext context) {
        this.context = context;
    }

    @Override
    public boolean checkAnswer(TransitionalContext answer) {
        return context.getStableTzb() == answer.getStableTzb()
                && context.getStableTzd() == answer.getStableTzd()
                && context.getSigmaZb() == answer.getSigmaZb()
                && context.getSigmaZd() == answer.getSigmaZd();
    }

    @Override
    public TransitionalContext getAnswer() {
        if (context.isStable() && context.getStableTzb() == 0) {
            solve();
        }
        return context;
    }

    private void solve() {
        double[] tmp;

        tmp = findStats(context.getUtzb());
        context.setStableTzb(tmp[0]);
        context.setSigmaZb(tmp[2]);

        tmp = findStats(context.getUtzd());
        context.setStableTzd(tmp[0]);
        context.setSigmaZd(tmp[2]);
    }

    private double[] findStats(double[] f) {
        double stableVal = f[f.length - 1];
        double delta = Math.abs(stableVal * DELTA);
        double stableT = 0;
        double max = 0;
        int indMax = 0;
        for (int i = f.length - 1; i >= 0; --i) {
            double absf = Math.abs(f[i]);
            if (absf > max) {
                max = absf;
                indMax = i;
            }
            if (stableT == 0 && Math.abs(f[i] - stableVal) >= delta) {
                stableT = MathUtils.round4(context.getT()[i]);
            }
        }

        double sigma = MathUtils.round4((f[indMax] - stableVal) / stableVal * 100);
        return new double[] {stableT, 0, sigma};
    }
}
