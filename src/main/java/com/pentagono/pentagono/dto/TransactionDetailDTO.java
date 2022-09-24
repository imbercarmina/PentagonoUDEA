package com.pentagono.pentagono.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionDetailDTO {

    @JsonIgnore
    private Long idTransactionDetail;
    private TransactionDTO transaction;
    private String concept;
    private Long amount;


}
