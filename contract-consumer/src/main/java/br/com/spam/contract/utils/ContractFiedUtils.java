package br.com.spam.contract.utils;

import au.com.dius.pact.consumer.dsl.LambdaDsl;
import au.com.dius.pact.consumer.dsl.LambdaDslJsonBody;
import au.com.dius.pact.consumer.dsl.LambdaDslObject;
import br.com.spam.contract.annotation.ContractField;
import br.com.spam.contract.type.ContractFieldType;

import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

public class ContractFiedUtils {

    public static LambdaDslJsonBody getFields(final Class clazz) {

        if (Objects.isNull(clazz)) {
            return null;
        }

        var dsl = LambdaDsl.newJsonBody((o) -> {});
        Arrays.stream(clazz.getDeclaredFields())
            .filter(f -> f.isAnnotationPresent(ContractField.class))
            .map(f -> f.getAnnotation(ContractField.class))
            .forEach(f -> put(dsl, f.name(), f.type()));

        return dsl;
    }

    private static void put(LambdaDslObject dsl, String name, ContractFieldType type) {

        switch ( type ) {
            case STRING:
                dsl.stringType(name, type.getSample().toString());
                break;
            case NUMBER:
                dsl.numberType(name, (Number) type.getSample());
                break;
            case BOOLEAN:
                dsl.booleanType(name, (Boolean) type.getSample());
                break;
            case DATE:
                dsl.date(name, type.getSample().toString(), new Date());
                break;
            case DATE_TIME:
                dsl.time(name, type.getSample().toString(), new Date());
                break;
            case OBJECT:
                break;
        }
    }

}
