package com.springbootminio.util;

public interface LinkUtils<T> {

    T addOperationWithLink(T clazz);

    T getOperationWithLink(T clazz);
}
