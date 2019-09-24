package com.mpp.buyAndSell.core.FunctionalProgramming;
@FunctionalInterface
public interface TriFunction<T,U,Y,E> {
E apply(T t,U u,Y y);
}
