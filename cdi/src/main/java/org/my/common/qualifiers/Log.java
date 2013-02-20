package org.my.common.qualifiers;

import javax.inject.Qualifier;
import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.ElementType.FIELD;

@Qualifier
@Retention(RUNTIME)
@Target({TYPE, FIELD})
public @interface Log
{
    LogType value();
}
