package org.khai.learning.math.expression.impl;

import org.khai.learning.math.expression.Expression;

public class Fraction implements Expression<Fraction> {
    private final Polynomial num;
    private final Polynomial denom;

    public static Fraction of(Polynomial num, Polynomial denom) {
        return new Fraction(num, denom);
    }

    private Fraction(Polynomial num, Polynomial denom) {
        this.num = num;
        this.denom = denom;
    }

    @Override
    public Fraction add(Fraction other) {
        return Fraction.of(
                num.mul(other.denom).add(other.num.mul(denom)),
                denom.mul(other.denom));

    }

    @Override
    public Fraction sub(Fraction other) {
        return add(other.neg());
    }

    @Override
    public Fraction mul(Fraction other) {
        return Fraction.of(num.mul(other.num), denom.mul(other.denom));
    }

    @Override
    public Fraction div(Fraction other) {
        return Fraction.of(num.mul(other.denom), denom.mul(other.num));
    }

    @Override
    public Fraction neg() {
        return Fraction.of(num.neg(), denom);
    }

    @Override
    public String toString() {
        return "(" + num.toString() + ") / (" + denom.toString() + ")";
    }
}
