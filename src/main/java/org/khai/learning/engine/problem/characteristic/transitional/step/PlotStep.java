package org.khai.learning.engine.problem.characteristic.transitional.step;

import org.khai.learning.engine.math.MathUtils;
import org.khai.learning.engine.problem.Step;
import org.khai.learning.engine.problem.characteristic.transitional.context.LaplasContext;
import org.khai.learning.engine.problem.characteristic.transitional.context.TransitionalContext;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.exp;

public class PlotStep implements Step<TransitionalContext> {
    private static final double T_MAX = 0.5;
    private static final double T_DELTA = 0.001;
    private static final int STABLE_VALUE_THRESHOLD = 3;

    private final TransitionalContext context;

    public PlotStep(TransitionalContext context) {
        this.context = context;
    }

    @Override
    public boolean checkAnswer(TransitionalContext answer) {
        return false;
    }

    @Override
    public TransitionalContext getAnswer() {
        if (context.getT() == null) {
            solve();
        }
        return context;
    }

    private void solve() {
        double[][] zd = eval(context.getLaplasZd());
        double[][] zb = eval(context.getLaplasZb());
        int size = Math.min(zd[0].length, zb[0].length);
        double[] t = new double[size];
        double[] zdY = new double[size];
        double[] zbY = new double[size];
        System.arraycopy(zd[0], 0, t, 0, size);
        System.arraycopy(zd[1], 0, zdY, 0, size);
        System.arraycopy(zb[1], 0, zbY, 0, size);
        context.setT(t);
        context.setUtzb(zbY);
        context.setUtzd(zdY);
    }

    private double[][] eval(LaplasContext l) {
        int estSize = (int) (T_MAX / (T_DELTA * 4));
        List<Double> x = new ArrayList<>(estSize);
        List<Double> y = new ArrayList<>(estSize);
        double prev = Double.NaN;
        int stableCounter = 0;
        for (double t = 0; t < T_MAX; t += T_DELTA) {
            double val = evalFunction(l, t);
            x.add(MathUtils.round4(t));
            y.add(val);
            double roundY = MathUtils.round4(val);
            if (roundY == prev) {
                ++stableCounter;
            } else {
                stableCounter = 0;
                prev = roundY;
            }
            if (stableCounter >= STABLE_VALUE_THRESHOLD) {
                break;
            }
        }
        if (stableCounter >= STABLE_VALUE_THRESHOLD) {
            context.setStable(true);
        }
        return new double[][] {
                x.stream().mapToDouble(Double::doubleValue).toArray(),
                y.stream().mapToDouble(Double::doubleValue).toArray()
        };
    }

    private double evalFunction(LaplasContext l, double t) {
        return l.getOuterCoef() *
                (l.getCoef0() + l.getCoef1() * exp(l.getAlpha1() * t) + l.getCoef2() * exp(l.getAlpha2() * t));
    }
}
