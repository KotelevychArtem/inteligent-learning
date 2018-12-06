package org.khai.learning.math.expression.impl;

import org.khai.learning.math.expression.Expression;

import static java.lang.Math.abs;

public class Polynomial implements Expression<Polynomial> {
    private final double[] koefs;

    public static Polynomial of(double... koefs) {
        return new Polynomial(koefs);
    }

    private Polynomial(double... koefs) {
        assert koefs.length > 0;
        this.koefs = koefs;
    }

    @Override
    public Polynomial add(Polynomial other) {
        double[] a, b;
        if (koefs.length >= other.koefs.length) {
            a = koefs;
            b = other.koefs;
        } else {
            a = other.koefs;
            b = koefs;
        }
        double[] res = new double[a.length];
        int i = 0;
        while (i < b.length) {
            res[i] = koefs[i] + other.koefs[i++];
        }
        while (i < a.length) {
            res[i] = a[i++];
        }
        return Polynomial.of(res);
    }

    @Override
    public Polynomial sub(Polynomial other) {
        return add(other.neg());
    }

    @Override
    public Polynomial mul(Polynomial other) {
        double[] res;
        if (other.koefs.length == 1) {
            res = new double[koefs.length];
            for (int i = 0; i < koefs.length; ++i) {
                res[i] = koefs[i] * other.koefs[0];
            }
        } else {
            res = new double[koefs.length + other.koefs.length - 2];
            for (int i = 0; i < koefs.length; ++i) {
                for (int j = 0; j < other.koefs.length; ++j) {
                    res[i + j] = koefs[i] * other.koefs[j];
                }
            }
        }
        return Polynomial.of(res);
    }

    @Override
    public Polynomial div(Polynomial other) {
        throw new UnsupportedOperationException("¯\\_(ツ)_/¯");
    }

    @Override
    public Polynomial neg() {
        double[] res = new double[koefs.length];
        for (int i = 0; i < koefs.length; ++i) {
            res[i] = -koefs[i];
        }
        return Polynomial.of(res);
    }

    @Override
    public String toString() {
        if (koefs.length == 1) {
            return "" + koefs[0];
        }
        StringBuilder sb = new StringBuilder();
        if (koefs.length == 2) {
            if (koefs[1] != 0) {
                sb.append(koefs[1])
                        .append("x");
            }
            if (koefs[0] != 0) {
                sb.append(koefs[0] > 0 ? " + " : " - ")
                        .append(koefs[0]);
            }
            return sb.length() > 0 ? sb.toString() : "0";
        }
        double k = koefs[koefs.length - 1];
        if (k < 0) sb.append("-");
        sb.append(abs(k))
                .append(k != 0 ? "x^" : "")
                .append(koefs.length - 1);
        for (int i = koefs.length - 2; i > 1; --i) {
            k = koefs[i];
            if (k != 0) {
                sb.append(k > 0 ? " + " : " - ")
                        .append(abs(k))
                        .append("x^")
                        .append(i);
            }
        }
        k = koefs[1];
        sb.append(k > 0 ? " + " : " - ")
                .append(abs(k))
                .append("x");
        k = koefs[0];
        if (k != 0 && koefs.length > 1) {
            sb.append(k > 0 ? " + " : " - ")
                    .append(abs(k));
        }
        return sb.toString();
    }

}
