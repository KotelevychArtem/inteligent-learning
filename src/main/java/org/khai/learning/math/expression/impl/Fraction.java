package org.khai.learning.math.expression.impl;

import org.khai.learning.math.expression.Expression;

import java.util.Objects;

public class Fraction implements Expression {
    final Expression num;
    final Expression denom;

    public static Fraction of(Expression num, Expression denom) {
        return new Fraction(num, denom);
    }

    public static Fraction of(Expression num) {
        return new Fraction(num, Polynomial.ONE);
    }

    private Fraction(Expression num, Expression denom) {
        this.num = num;
        this.denom = denom;
    }

    @Override
    public Fraction add(Expression other) {
        if (other instanceof Polynomial) {
            return add(Fraction.of(other));
        }
        if (other instanceof Fraction) {
            Fraction otherFraction = (Fraction) other;
            return Fraction.of(
                    num.mul(otherFraction.denom).add(otherFraction.num.mul(denom)),
                    denom.mul(otherFraction.denom));
        }
        throw new UnsupportedOperationException();
    }

    @Override
    public Fraction sub(Expression other) {
        return add(other.neg());
    }

    @Override
    public Fraction mul(Expression other) {
        if (other instanceof Polynomial) {
            return mul(Fraction.of(other));
        }
        if (other instanceof Fraction) {
            Fraction otherFraction  = (Fraction) other;
            return Fraction.of(num.mul(otherFraction.num), denom.mul(otherFraction.denom));
        }
        throw new UnsupportedOperationException();
    }

    @Override
    public Fraction div(Expression other) {
        if (other instanceof Polynomial) {
            return Fraction.of(this, other);
        }
        if (other instanceof Fraction) {
            Fraction otherFraction = (Fraction) other;
            return Fraction.of(num.mul(otherFraction.denom), denom.mul(otherFraction.num));
        }
        throw new UnsupportedOperationException();
    }

    @Override
    public Fraction neg() {
        return Fraction.of(num.neg(), denom);
    }

    @Override
    public Double eval(Double... args) {
        return num.eval(args) / denom.eval(args);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fraction fraction = (Fraction) o;
        return Objects.equals(num, fraction.num) &&
                Objects.equals(denom, fraction.denom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(num, denom);
    }

    @Override
    public String toString() {
        return resolveBrackets(num.toString()) + " / " + resolveBrackets(denom.toString());
    }

    private String resolveBrackets(String fraction) {
        return fraction.contains(" ") ? "(" + fraction + ")" : fraction;
    }
}
