package com.jrfom.tla.annotations;

import java.lang.annotation.*;

/**
 * <p>Provides an annotation, @Layout, that defines a ThymeLeaf layout to use
 * for rendering a view. A "layout" is essentially a master template that
 * embeds other templates (the layout template).</p>
 *
 * <p>Taken from
 * <a href="http://blog.codeleak.pl/2013/11/thymeleaf-template-layouts-in-spring.html">
 *   http://blog.codeleak.pl/2013/11/thymeleaf-template-layouts-in-spring.html
 * </a>
 * </p>
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Layout {
  String value() default "";
}