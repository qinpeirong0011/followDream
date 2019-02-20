package com.qinpr.follow.messageQueue.common.annotation;

import java.lang.annotation.*;

/**
 * Created by qinpr on 2019/2/19.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE})
public @interface ImportantField {
}
