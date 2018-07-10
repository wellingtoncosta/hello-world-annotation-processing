package helloworld.processing;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.SOURCE;

/**
 * @author Wellington Costa on 10/07/18.
 */
@Target(TYPE)
@Retention(SOURCE)
public @interface HelloWorld { }