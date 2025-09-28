package com.aop.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface HttpOutcomeRequestLog {
    String service(); // название микросервиса
}