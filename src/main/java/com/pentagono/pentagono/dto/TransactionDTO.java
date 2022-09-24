package com.pentagono.pentagono.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionDTO {

    private long idTransaction;
    private EmployeeDTO employee;
    private EnterpriseDTO enterprise;
    private Date createdAt;
    private Date updatedAt;
    private long balance;
    private boolean enabled;
    private String concept;/*lINA CAMBIO DE LIST*/
    private float amount;/*LINA*/


}
