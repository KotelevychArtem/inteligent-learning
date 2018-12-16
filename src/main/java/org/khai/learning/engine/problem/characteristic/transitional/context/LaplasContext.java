package org.khai.learning.engine.problem.characteristic.transitional.context;

public class LaplasContext {
    private double a, b, c;
    private double outerCoef;
    private double coef0 = 1;
    private double coef1, alpha1;
    private double coef2, alpha2;

    public double getA() {
        return a;
    }

    public LaplasContext setA(double a) {
        this.a = a;
        return this;
    }

    public double getB() {
        return b;
    }

    public LaplasContext setB(double b) {
        this.b = b;
        return this;
    }

    public double getC() {
        return c;
    }

    public LaplasContext setC(double c) {
        this.c = c;
        return this;
    }

    public double getOuterCoef() {
        return outerCoef;
    }

    public LaplasContext setOuterCoef(double outerCoef) {
        this.outerCoef = outerCoef;
        return this;
    }

    public double getCoef0() {
        return coef0;
    }

    public LaplasContext setCoef0(double coef0) {
        this.coef0 = coef0;
        return this;
    }

    public double getCoef1() {
        return coef1;
    }

    public LaplasContext setCoef1(double coef1) {
        this.coef1 = coef1;
        return this;
    }

    public double getAlpha1() {
        return alpha1;
    }

    public LaplasContext setAlpha1(double alpha1) {
        this.alpha1 = alpha1;
        return this;
    }

    public double getCoef2() {
        return coef2;
    }

    public LaplasContext setCoef2(double coef2) {
        this.coef2 = coef2;
        return this;
    }

    public double getAlpha2() {
        return alpha2;
    }

    public LaplasContext setAlpha2(double alpha2) {
        this.alpha2 = alpha2;
        return this;
    }
}
