package br.com.spam.contract.annotation;

import br.com.spam.contract.type.ContractFieldType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ContractField {

    String name();
    String format() default "";
    ContractFieldType type();

}
