package com.yzz.springpractice.autowiredmyself;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
@Inherited
public @interface AutoWiredMyself {
}
