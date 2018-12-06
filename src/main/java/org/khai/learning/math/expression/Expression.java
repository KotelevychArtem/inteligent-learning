package org.khai.learning.math.expression;

public interface Expression<T extends Expression> {
    T add(T other);
    T sub(T other);
    T mul(T other);
    T div(T other);
    T neg();
}
