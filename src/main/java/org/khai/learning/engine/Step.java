package org.khai.learning.engine;


public interface Step<T> {
    int getHint(int pos);
    boolean checkAnswer(T answer);
    T getAnswer();
}
