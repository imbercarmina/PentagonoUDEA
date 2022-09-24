package com.pentagono.pentagono.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.pentagono.pentagono.model.RoleName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)/*ojo*/
public class UsersDTO {

    @Min(1)
    @Max(999)
    private Long idUser;
    private EnterpriseDTO enterprise;
    private String name;
    private String password;
    private RoleName rolename;
    private boolean enabled;
}
