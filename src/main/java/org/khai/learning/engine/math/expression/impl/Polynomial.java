package org.khai.learning.engine.math.expression.impl;

import org.khai.learning.engine.math.expression.Expression;

import java.util.Arrays;

import static java.lang.Math.abs;

public class Polynomial implements Expression {
    public static final Polynomial ZERO = new Polynomial(0);
    public static final Polynomial ONE = new Polynomial(1);

    final double[] coefs;

    /**
     * Creates polynomial with given coefficients in ascending order
     * e.g. Polynomial.of(5, 3, 4) = 4x^2 + 3x + 5
     * For creating ones and zeros use {@link Polynomial#ONE} and {@link Polynomial#ZERO}
     */
    public static Polynomial of(double... coefs) {
        assert coefs != null && coefs.length > 0;
        if (coefs.length == 1) {
            if (coefs[0] == 0) return ZERO;
            if (coefs[0] == 1) return ONE;
        }
        return new Polynomial(coefs);
    }

    private Polynomial(double... coefs) {
        this.coefs = coefs;
    }

    public double getCoef(int i) {
        if (i >= coefs.length) {
            return 0;
        } else {
            return coefs[i];
        }
    }

    public int getRank() {
        return coefs.length - 1;
    }

    @Override
    public Expression add(Expression other) {
        if (other instanceof Fraction) {
            return Fraction.of(this).add(other);
        }
        if (other instanceof Polynomial) {
            Polynomial otherPol = (Polynomial) other;
            double[] a, b;
            if (coefs.length >= otherPol.coefs.length) {
                a = coefs;
                b = otherPol.coefs;
            } else {
                a = otherPol.coefs;
                b = coefs;
            }
            double[] res = new double[a.length];
            int i = 0;
            while (i < b.length) {
                res[i] = coefs[i] + otherPol.coefs[i++];
            }
            while (i < a.length) {
                res[i] = a[i++];
            }
            return Polynomial.of(res);
        }
        throw new UnsupportedOperationException();
    }

    @Override
    public Expression sub(Expression other) {
        return add(other.neg());
    }

    @Override
    public Expression mul(Expression other) {
        if (other instanceof Fraction) {
            return Fraction.of(this).mul(other);
        }
        if (other instanceof Polynomial) {
            Polynomial otherPol = (Polynomial) other;
            double[] res;
            if (otherPol.coefs.length == 1) {
                res = new double[coefs.length];
                for (int i = 0; i < coefs.length; ++i) {
                    res[i] = coefs[i] * otherPol.coefs[0];
                }
            } else {
                res = new double[coefs.length + otherPol.coefs.length - 1];
                for (int i = 0; i < coefs.length; ++i) {
                    for (int j = 0; j < otherPol.coefs.length; ++j) {
                        res[i + j] += coefs[i] * otherPol.coefs[j];
                    }
                }
            }
            return Polynomial.of(res);
        }
        throw new UnsupportedOperationException();
    }

    @Override
    public Expression div(Expression other) {
        if (other instanceof Polynomial) {
            Polynomial otherPol = (Polynomial) other;
            if (otherPol.coefs.length == 1) {
                double[] res = new double[coefs.length];
                double a = otherPol.coefs[0];
                for (int i = 0; i < coefs.length; ++i) {
                    res[i] = coefs[i] / a;
                }
                return Polynomial.of(res);
            }
        }
        return Fraction.of(this, other);
    }

    @Override
    public Polynomial neg() {
        double[] res = new double[coefs.length];
        for (int i = 0; i < coefs.length; ++i) {
            res[i] = -coefs[i];
        }
        return Polynomial.of(res);
    }

    @Override
    public Double eval(Double... args) {
        assert args != null && args.length >= 1;
        double x = args[0];
        double ans  = coefs[0];
        for (int i = 1; i < coefs.length; ++i) {
            ans += coefs[i] * x;
            x *= x;
        }
        return ans;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Polynomial that = (Polynomial) o;
        return Arrays.equals(coefs, that.coefs);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(coefs);
    }

    @Override
    public String toString() {
        if (coefs.length == 1) {
            return "" + coefs[0];
        }
        StringBuilder sb = new StringBuilder();
        if (coefs.length == 2) {
            if (coefs[1] != 0) {
                sb.append(toXKoef(abs(coefs[1])))
                        .append("x");
            }
            if (coefs[0] != 0) {
                sb.append(signOf(coefs[0]))
                        .append(coefs[0]);
            }
            return sb.length() > 0 ? sb.toString() : "0";
        }
        double k = coefs[coefs.length - 1];
        if (k < 0) sb.append("-");
        sb.append(toXKoef(abs(k)))
                .append(k != 0 ? "x^" : "")
                .append(coefs.length - 1);
        for (int i = coefs.length - 2; i > 1; --i) {
            k = coefs[i];
            if (k != 0) {
                sb.append(signOf(k))
                        .append(toXKoef(abs(k)))
                        .append("x^")
                        .append(i);
            }
        }
        k = coefs[1];
        sb.append(signOf(k))
                .append(abs(k))
                .append("x");
        k = coefs[0];
        if (k != 0 && coefs.length > 1) {
            sb.append(k > 0 ? " + " : " - ")
                    .append(abs(k));
        }
        return sb.toString();
    }

    private String toXKoef(double k) {
        return k == 1 ? "" : String.valueOf(k);
    }

    private String signOf(double k) {
        if (k > 0) return " + ";
        if (k < 0) return " - ";
        return "";
    }

    // TODO: test
    public static void main(String[] args) {
        Expression exp = Polynomial.of(5, 3, 1)
                .mul(Polynomial.of(3, 5)
                        .sub(Fraction.of(Polynomial.of(12), Polynomial.of(21)))
                        .div(Fraction.of(Polynomial.of(2, 2), Polynomial.of(3, 3)))
                );
        System.out.println(exp);
        System.out.println(exp.eval(2.0));
    }

}
