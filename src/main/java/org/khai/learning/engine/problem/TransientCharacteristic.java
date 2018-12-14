package org.khai.learning.engine.problem;

import org.khai.learning.engine.Step;
import org.khai.learning.math.expression.Expression;
import org.khai.learning.math.expression.impl.Fraction;
import org.khai.learning.math.expression.impl.Polynomial;
import org.khai.learning.math.solver.GaussSystemSolver;
import org.khai.learning.math.solver.SquareEquationSolver;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public class TransientCharacteristic {
    private final Context context;
    private List<Step> steps;

    public TransientCharacteristic(Context context) {
        this.context = context;
        context.setLaplasZd(new LaplasSolution())
                .setLaplasZb(new LaplasSolution());
        steps = Arrays.asList(
                new FirstStep(context),
                new SecondStep(context),
                new ThirdStep(context),
                new LaplasStep(context, Context::getLaplasZd),
                new LaplasStep(context, Context::getLaplasZb)
        );
    }

    public Context solve() {
        steps.forEach(Step::getAnswer);
        return context;
    }

    public static class FirstStep implements Step<Context> {
        private final Context context;
        private Fraction formula;

        public FirstStep(Context context) {
            this.context = context;
        }

        @Override
        public int getHint(int pos) {
            return 0;
        }

        @Override
        public boolean checkAnswer(Context answer) {
            return Objects.equals(formula, answer.fZd);
        }

        @Override
        public Context getAnswer() {
            if (formula == null) {
                solve();
            }
            return context;
        }

        private void solve() {
            Expression wdu =
                    Polynomial.of(context.kdu)
                            .div(Polynomial.of(1, context.t1).mul(Polynomial.of(1, context.t2)));
            Expression upperPart = Polynomial.of(context.wpe).mul(Polynomial.of(context.wpp)).mul(wdu).mul(Polynomial.of(context.wtg));

            Expression lowerPart = Polynomial.of(1).add(
                    upperPart.mul(Polynomial.of(context.wke))
            );

            formula = (Fraction) upperPart.div(lowerPart);
            Polynomial coef  = Polynomial.of(((Polynomial) formula.getDenom()).getCoef(0));
            formula = Fraction.of(formula.getNum().div(coef), formula.getDenom().div(coef));
            context.fZd = formula;
            context.charEquation = (Polynomial) formula.getDenom();
            context.laplasZd.setOuterCoef(((Polynomial) formula.getNum()).getCoef(0) * context.uz);
        }
    }

    public static class SecondStep implements Step<Context> {
        private final Context context;
        private Fraction formula;

        public SecondStep(Context context) {
            this.context = context;
        }

        @Override
        public int getHint(int pos) {
            return 0;
        }

        @Override
        public boolean checkAnswer(Context answer) {
            return Objects.equals(formula, answer.fZb);
        }

        @Override
        public Context getAnswer() {
            solve();
            return context;
        }

        private void solve() {
            if (formula != null) return;
            Expression wdw =
                    Polynomial.of(context.kdw)
                            .div(Polynomial.of(1, context.t1).mul(Polynomial.of(1, context.t2)));
            Expression wdu =
                    Polynomial.of(context.kdu)
                            .div(Polynomial.of(1, context.t1).mul(Polynomial.of(1, context.t2)));
            Expression lowerPart = Polynomial.of(1).add(
                    Polynomial.of(context.wpe).mul(Polynomial.of(context.wpp)).mul(wdu).mul(Polynomial.of(context.wtg)).mul(Polynomial.of(context.wke)));

            formula = (Fraction) wdw.mul(Polynomial.of(context.wtg)).div(lowerPart);
            Polynomial coef  = Polynomial.of(((Polynomial) formula.getDenom()).getCoef(0));
            formula = Fraction.of(formula.getNum().div(coef), formula.getDenom().div(coef));
            context.fZb = formula;
            context.laplasZb.setOuterCoef(((Polynomial) formula.getNum()).getCoef(0) * context.mn);
        }
    }

    public static class ThirdStep implements Step<Context> {
        private Context context;
        private double[] roots;

        public ThirdStep(Context context) {
            this.context = context;
        }

        @Override
        public int getHint(int pos) {
            return 0;
        }

        @Override
        public boolean checkAnswer(Context answer) {
            return context.tz1 == answer.tz1 && context.tz2 == answer.tz2;
        }

        @Override
        public Context getAnswer() {
            if (roots == null) {
                solve();
            }
            return context;
        }

        private void solve() {
            roots = new SquareEquationSolver(context.charEquation).solve();
            context.tz1 = Math.abs(1.0 / roots[0]);
            context.tz2 = Math.abs(1.0 / roots[1]);
        }
    }

    public static class LaplasStep implements Step<Context> {
        private static final Polynomial S = Polynomial.of(0, 1);

        private final Function<Context, LaplasSolution> solutionGetter;
        private final Context context;

        private Polynomial aPart;
        private Polynomial bPart;
        private Polynomial cPart;

        private double[][] system;

        private LaplasSolution laplasSolution;

        public LaplasStep(Context context, Function<Context, LaplasSolution> solutionGetter) {
            this.context = context;
            this.solutionGetter = solutionGetter;
        }

        @Override
        public int getHint(int pos) {
            return 0;
        }

        @Override
        public boolean checkAnswer(Context answer) {
            return solutionGetter.apply(context).equals(solutionGetter.apply(answer));
        }

        @Override
        public Context getAnswer() {
            if (laplasSolution == null) {
                solve();
            }
            return context;
        }

        private void solve() {
            double t1 = context.tz1;
            double t2 = context.tz2;
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

    public static void main(String[] args) {
        Context context = new Context()
                .setWpe(3.2).setWpp(2.6)
                .setKdu(10.5).setKdw(-8.2e3)
                .setT1(0.15).setT2(0.003)
                .setWtg(0.08).setWke(1)
                .setUz(1.5).setMn(10e-2);
        TransientCharacteristic problem = new TransientCharacteristic(context);
        problem.solve();
        System.out.println(context);
    }

    public static class Context {
        private double wpe, wpp, kdu, kdw, t1, t2, wtg, wke, tz1, tz2;
        private Expression fZd, fZb;
        private double uz, mn;
        private Polynomial charEquation;
        private LaplasSolution laplasZb, laplasZd;

        public double getWpe() {
            return wpe;
        }

        public Context setWpe(double wpe) {
            this.wpe = wpe;
            return this;
        }

        public double getWpp() {
            return wpp;
        }

        public Context setWpp(double wpp) {
            this.wpp = wpp;
            return this;
        }

        public double getKdu() {
            return kdu;
        }

        public Context setKdu(double kdu) {
            this.kdu = kdu;
            return this;
        }

        public double getKdw() {
            return kdw;
        }

        public Context setKdw(double kdw) {
            this.kdw = kdw;
            return this;
        }

        public double getT1() {
            return t1;
        }

        public Context setT1(double t1) {
            this.t1 = t1;
            return this;
        }

        public double getT2() {
            return t2;
        }

        public Context setT2(double t2) {
            this.t2 = t2;
            return this;
        }

        public double getWtg() {
            return wtg;
        }

        public Context setWtg(double wtg) {
            this.wtg = wtg;
            return this;
        }

        public double getWke() {
            return wke;
        }

        public Context setWke(double wke) {
            this.wke = wke;
            return this;
        }

        public double getTz1() {
            return tz1;
        }

        public Context setTz1(double tz1) {
            this.tz1 = tz1;
            return this;
        }

        public double getTz2() {
            return tz2;
        }

        public Context setTz2(double tz2) {
            this.tz2 = tz2;
            return this;
        }

        public Expression getfZd() {
            return fZd;
        }

        public Context setfZd(Expression fZd) {
            this.fZd = fZd;
            return this;
        }

        public Expression getfZb() {
            return fZb;
        }

        public Context setfZb(Expression fZb) {
            this.fZb = fZb;
            return this;
        }

        public double getUz() {
            return uz;
        }

        public Context setUz(double uz) {
            this.uz = uz;
            return this;
        }

        public double getMn() {
            return mn;
        }

        public Context setMn(double mn) {
            this.mn = mn;
            return this;
        }

        public Polynomial getCharEquation() {
            return charEquation;
        }

        public Context setCharEquation(Polynomial charEquation) {
            this.charEquation = charEquation;
            return this;
        }

        public LaplasSolution getLaplasZb() {
            return laplasZb;
        }

        public Context setLaplasZb(LaplasSolution laplasZb) {
            this.laplasZb = laplasZb;
            return this;
        }

        public LaplasSolution getLaplasZd() {
            return laplasZd;
        }

        public Context setLaplasZd(LaplasSolution laplasZd) {
            this.laplasZd = laplasZd;
            return this;
        }

        @Override
        public String toString() {
            return "Context{" +
                    "wpe=" + wpe +
                    ", wpp=" + wpp +
                    ", kdu=" + kdu +
                    ", kdw=" + kdw +
                    ", t1=" + t1 +
                    ", t2=" + t2 +
                    ", wtg=" + wtg +
                    ", wke=" + wke +
                    ", tz1=" + tz1 +
                    ", tz2=" + tz2 +
                    ", fZd=" + fZd +
                    ", fZb=" + fZb +
                    ", charEquation=" + charEquation +
                    ", laplasZb=" + laplasZb +
                    ", laplasZd=" + laplasZd +
                    '}';
        }
    }

    public static class LaplasSolution {
        private double a, b, c;
        private double outerCoef;
        private double coef0 = 1;
        private double coef1, alpha1;
        private double coef2, alpha2;

        public double getA() {
            return a;
        }

        public LaplasSolution setA(double a) {
            this.a = a;
            return this;
        }

        public double getB() {
            return b;
        }

        public LaplasSolution setB(double b) {
            this.b = b;
            return this;
        }

        public double getC() {
            return c;
        }

        public LaplasSolution setC(double c) {
            this.c = c;
            return this;
        }

        public double getOuterCoef() {
            return outerCoef;
        }

        public LaplasSolution setOuterCoef(double outerCoef) {
            this.outerCoef = outerCoef;
            return this;
        }

        public double getCoef0() {
            return coef0;
        }

        public LaplasSolution setCoef0(double coef0) {
            this.coef0 = coef0;
            return this;
        }

        public double getCoef1() {
            return coef1;
        }

        public LaplasSolution setCoef1(double coef1) {
            this.coef1 = coef1;
            return this;
        }

        public double getAlpha1() {
            return alpha1;
        }

        public LaplasSolution setAlpha1(double alpha1) {
            this.alpha1 = alpha1;
            return this;
        }

        public double getCoef2() {
            return coef2;
        }

        public LaplasSolution setCoef2(double coef2) {
            this.coef2 = coef2;
            return this;
        }

        public double getAlpha2() {
            return alpha2;
        }

        public LaplasSolution setAlpha2(double alpha2) {
            this.alpha2 = alpha2;
            return this;
        }
    }
}
