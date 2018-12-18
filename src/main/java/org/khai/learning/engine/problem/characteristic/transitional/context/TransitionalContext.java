package org.khai.learning.engine.problem.characteristic.transitional.context;

import org.khai.learning.engine.math.expression.Expression;
import org.khai.learning.engine.math.expression.impl.Polynomial;

public class TransitionalContext {
    private double wpe, wpp, kdu, kdw, t1, t2, wtg, wke, uz, mn;
    private Expression fZd, fZb;
    private Polynomial charEquation;
    private double charRoot1, charRoot2;
    private double tz1, tz2;
    private LaplasContext laplasZd, laplasZb;
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

    public TransitionalContext setWpe(double wpe) {
        this.wpe = wpe;
        return this;
    }

    public double getWpp() {
        return wpp;
    }

    public TransitionalContext setWpp(double wpp) {
        this.wpp = wpp;
        return this;
    }

    public double getKdu() {
        return kdu;
    }

    public TransitionalContext setKdu(double kdu) {
        this.kdu = kdu;
        return this;
    }

    public double getKdw() {
        return kdw;
    }

    public TransitionalContext setKdw(double kdw) {
        this.kdw = kdw;
        return this;
    }

    public double getT1() {
        return t1;
    }

    public TransitionalContext setT1(double t1) {
        this.t1 = t1;
        return this;
    }

    public double getT2() {
        return t2;
    }

    public TransitionalContext setT2(double t2) {
        this.t2 = t2;
        return this;
    }

    public double getWtg() {
        return wtg;
    }

    public TransitionalContext setWtg(double wtg) {
        this.wtg = wtg;
        return this;
    }

    public double getWke() {
        return wke;
    }

    public TransitionalContext setWke(double wke) {
        this.wke = wke;
        return this;
    }

    public double getCharRoot1() {
        return charRoot1;
    }

    public TransitionalContext setCharRoot1(double charRoot1) {
        this.charRoot1 = charRoot1;
        return this;
    }

    public double getCharRoot2() {
        return charRoot2;
    }

    public TransitionalContext setCharRoot2(double charRoot2) {
        this.charRoot2 = charRoot2;
        return this;
    }

    public double getTz1() {
        return tz1;
    }

    public TransitionalContext setTz1(double tz1) {
        this.tz1 = tz1;
        return this;
    }

    public double getTz2() {
        return tz2;
    }

    public TransitionalContext setTz2(double tz2) {
        this.tz2 = tz2;
        return this;
    }

    public Expression getfZd() {
        return fZd;
    }

    public TransitionalContext setfZd(Expression fZd) {
        this.fZd = fZd;
        return this;
    }

    public Expression getfZb() {
        return fZb;
    }

    public TransitionalContext setfZb(Expression fZb) {
        this.fZb = fZb;
        return this;
    }

    public double getUz() {
        return uz;
    }

    public TransitionalContext setUz(double uz) {
        this.uz = uz;
        return this;
    }

    public double getMn() {
        return mn;
    }

    public TransitionalContext setMn(double mn) {
        this.mn = mn;
        return this;
    }

    public Polynomial getCharEquation() {
        return charEquation;
    }

    public TransitionalContext setCharEquation(Polynomial charEquation) {
        this.charEquation = charEquation;
        return this;
    }

    public LaplasContext getLaplasZb() {
        return laplasZb;
    }

    public TransitionalContext setLaplasZb(LaplasContext laplasZb) {
        this.laplasZb = laplasZb;
        return this;
    }

    public LaplasContext getLaplasZd() {
        return laplasZd;
    }

    public TransitionalContext setLaplasZd(LaplasContext laplasZd) {
        this.laplasZd = laplasZd;
        return this;
    }

    public double[] getT() {
        return T;
    }

    public TransitionalContext setT(double[] t) {
        T = t;
        return this;
    }

    public double[] getUtzd() {
        return Utzd;
    }

    public TransitionalContext setUtzd(double[] utzd) {
        Utzd = utzd;
        return this;
    }

    public double[] getUtzb() {
        return Utzb;
    }

    public TransitionalContext setUtzb(double[] utzb) {
        Utzb = utzb;
        return this;
    }

    public boolean isStable() {
        return stable;
    }

    public TransitionalContext setStable(boolean stable) {
        this.stable = stable;
        return this;
    }

    public double getStableTzd() {
        return stableTzd;
    }

    public TransitionalContext setStableTzd(double stableTzd) {
        this.stableTzd = stableTzd;
        return this;
    }

    public double getStableTzb() {
        return stableTzb;
    }

    public TransitionalContext setStableTzb(double stableTzb) {
        this.stableTzb = stableTzb;
        return this;
    }

    public double getEpsZd() {
        return epsZd;
    }

    public TransitionalContext setEpsZd(double epsZd) {
        this.epsZd = epsZd;
        return this;
    }

    public double getEpsZb() {
        return epsZb;
    }

    public TransitionalContext setEpsZb(double epsZb) {
        this.epsZb = epsZb;
        return this;
    }

    public double getSigmaZd() {
        return sigmaZd;
    }

    public TransitionalContext setSigmaZd(double sigmaZd) {
        this.sigmaZd = sigmaZd;
        return this;
    }

    public double getSigmaZb() {
        return sigmaZb;
    }

    public TransitionalContext setSigmaZb(double sigmaZb) {
        this.sigmaZb = sigmaZb;
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
