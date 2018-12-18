package org.khai.learning.service.model;

public class LaplasContextModel {
    private double a, b, c;
    private double outerCoef;
    private double coef0 = 1;
    private double coef1, alpha1;
    private double coef2, alpha2;

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public double getC() {
        return c;
    }

    public void setC(double c) {
        this.c = c;
    }

    public double getOuterCoef() {
        return outerCoef;
    }

    public void setOuterCoef(double outerCoef) {
        this.outerCoef = outerCoef;
    }

    public double getCoef0() {
        return coef0;
    }

    public void setCoef0(double coef0) {
        this.coef0 = coef0;
    }

    public double getCoef1() {
        return coef1;
    }

    public void setCoef1(double coef1) {
        this.coef1 = coef1;
    }

    public double getAlpha1() {
        return alpha1;
    }

    public void setAlpha1(double alpha1) {
        this.alpha1 = alpha1;
    }

    public double getCoef2() {
        return coef2;
    }

    public void setCoef2(double coef2) {
        this.coef2 = coef2;
    }

    public double getAlpha2() {
        return alpha2;
    }

    public void setAlpha2(double alpha2) {
        this.alpha2 = alpha2;
    }
}
