package org.khai.learning.service.model;

public class TransitionalContextModel {
    private double wpe, wpp, kdu, kdw, t1, t2, wtg, wke, uz, mn;
    private double phiZdNum, phiZbNum;
    private double charEquationA, charEquationB, charEquationC;
    private double charRoot1, charRoot2;
    private double tz1, tz2;
    private LaplasContextModel laplasZd, laplasZb;
    private double[] T;
    private double[] Utzd;
    private double[] Utzb;
    private boolean stable;
    private double stableTzd;
    private double stableTzb;
    private double epsZd;
    private double epsZb;
    private double sigmaZd;
    private double sigmaZb;

    public double getWpe() {
        return wpe;
    }

    public void setWpe(double wpe) {
        this.wpe = wpe;
    }

    public double getWpp() {
        return wpp;
    }

    public void setWpp(double wpp) {
        this.wpp = wpp;
    }

    public double getKdu() {
        return kdu;
    }

    public void setKdu(double kdu) {
        this.kdu = kdu;
    }

    public double getKdw() {
        return kdw;
    }

    public void setKdw(double kdw) {
        this.kdw = kdw;
    }

    public double getT1() {
        return t1;
    }

    public void setT1(double t1) {
        this.t1 = t1;
    }

    public double getT2() {
        return t2;
    }

    public void setT2(double t2) {
        this.t2 = t2;
    }

    public double getWtg() {
        return wtg;
    }

    public void setWtg(double wtg) {
        this.wtg = wtg;
    }

    public double getWke() {
        return wke;
    }

    public void setWke(double wke) {
        this.wke = wke;
    }

    public double getUz() {
        return uz;
    }

    public void setUz(double uz) {
        this.uz = uz;
    }

    public double getMn() {
        return mn;
    }

    public void setMn(double mn) {
        this.mn = mn;
    }

    public double getPhiZdNum() {
        return phiZdNum;
    }

    public void setPhiZdNum(double phiZdNum) {
        this.phiZdNum = phiZdNum;
    }

    public double getPhiZbNum() {
        return phiZbNum;
    }

    public void setPhiZbNum(double phiZbNum) {
        this.phiZbNum = phiZbNum;
    }

    public double getCharEquationA() {
        return charEquationA;
    }

    public void setCharEquationA(double charEquationA) {
        this.charEquationA = charEquationA;
    }

    public double getCharEquationB() {
        return charEquationB;
    }

    public void setCharEquationB(double charEquationB) {
        this.charEquationB = charEquationB;
    }

    public double getCharEquationC() {
        return charEquationC;
    }

    public void setCharEquationC(double charEquationC) {
        this.charEquationC = charEquationC;
    }

    public double getCharRoot1() {
        return charRoot1;
    }

    public void setCharRoot1(double charRoot1) {
        this.charRoot1 = charRoot1;
    }

    public double getCharRoot2() {
        return charRoot2;
    }

    public void setCharRoot2(double charRoot2) {
        this.charRoot2 = charRoot2;
    }

    public double getTz1() {
        return tz1;
    }

    public void setTz1(double tz1) {
        this.tz1 = tz1;
    }

    public double getTz2() {
        return tz2;
    }

    public void setTz2(double tz2) {
        this.tz2 = tz2;
    }

    public LaplasContextModel getLaplasZd() {
        return laplasZd;
    }

    public void setLaplasZd(LaplasContextModel laplasZd) {
        this.laplasZd = laplasZd;
    }

    public LaplasContextModel getLaplasZb() {
        return laplasZb;
    }

    public void setLaplasZb(LaplasContextModel laplasZb) {
        this.laplasZb = laplasZb;
    }

    public double[] getT() {
        return T;
    }

    public void setT(double[] t) {
        T = t;
    }

    public double[] getUtzd() {
        return Utzd;
    }

    public void setUtzd(double[] utzd) {
        Utzd = utzd;
    }

    public double[] getUtzb() {
        return Utzb;
    }

    public void setUtzb(double[] utzb) {
        Utzb = utzb;
    }

    public boolean isStable() {
        return stable;
    }

    public void setStable(boolean stable) {
        this.stable = stable;
    }

    public double getStableTzd() {
        return stableTzd;
    }

    public void setStableTzd(double stableTzd) {
        this.stableTzd = stableTzd;
    }

    public double getStableTzb() {
        return stableTzb;
    }

    public void setStableTzb(double stableTzb) {
        this.stableTzb = stableTzb;
    }

    public double getEpsZd() {
        return epsZd;
    }

    public void setEpsZd(double epsZd) {
        this.epsZd = epsZd;
    }

    public double getEpsZb() {
        return epsZb;
    }

    public void setEpsZb(double epsZb) {
        this.epsZb = epsZb;
    }

    public double getSigmaZd() {
        return sigmaZd;
    }

    public void setSigmaZd(double sigmaZd) {
        this.sigmaZd = sigmaZd;
    }

    public double getSigmaZb() {
        return sigmaZb;
    }

    public void setSigmaZb(double sigmaZb) {
        this.sigmaZb = sigmaZb;
    }
}
