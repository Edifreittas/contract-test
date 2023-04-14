package br.com.spam.dto;

import br.com.spam.contract.annotation.ContractField;
import br.com.spam.contract.type.ContractFieldType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDTO {

    @ContractField(name = "id", type = ContractFieldType.STRING)
    private String id;

    @ContractField(name = "price", type = ContractFieldType.NUMBER)
    private BigDecimal price;

    @ContractField(name = "date", type = ContractFieldType.DATE)
    private LocalDate date;

    @ContractField(name = "valid", type = ContractFieldType.BOOLEAN)
    private Boolean valid;

}
