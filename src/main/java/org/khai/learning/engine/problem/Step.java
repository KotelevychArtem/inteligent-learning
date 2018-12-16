package org.khai.learning.engine.problem;


public interface Step<T> {
    boolean checkAnswer(T answer);
    T getAnswer();
}
