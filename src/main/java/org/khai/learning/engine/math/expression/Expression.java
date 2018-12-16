package org.khai.learning.engine.math.expression;

public interface Expression {
    Expression add(Expression other);
    Expression sub(Expression other);
    Expression mul(Expression other);
    Expression div(Expression other);
    Expression neg();
    Double eval(Double... args);
}
