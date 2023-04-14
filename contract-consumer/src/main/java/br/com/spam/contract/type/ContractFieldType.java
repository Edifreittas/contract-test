package br.com.spam.contract.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
public enum ContractFieldType {

    STRING("Text"),
    NUMBER(BigDecimal.TEN),
    BOOLEAN(Boolean.TRUE),
    DATE("dd-MM-yyyy"),
    DATE_TIME("dd-MM-yyyy'T'HH:mm:ss'Z'"),
    OBJECT("Object");

    @Getter
    private Object sample;

}
