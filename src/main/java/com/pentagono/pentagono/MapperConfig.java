package com.pentagono.pentagono;

import com.pentagono.pentagono.model.Users;
import com.pentagono.pentagono.dto.EnterpriseDTO;
import com.pentagono.pentagono.model.Enterprise;
import com.pentagono.pentagono.dto.UsersDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean("enterpriseMapper")
    public ModelMapper enterpriseMapper(){
        ModelMapper mapper = new ModelMapper();
        TypeMap<EnterpriseDTO, Enterprise> typeMap = mapper.createTypeMap(EnterpriseDTO.class,Enterprise.class);
        typeMap.addMapping(EnterpriseDTO::getIdEnterprise, Enterprise::setIdEnterprise);
        typeMap.addMapping(EnterpriseDTO::getName, Enterprise::setName);
        return mapper;
    }

    @Bean("employeeMapper")
    public ModelMapper employeeMapper(){return new ModelMapper();}


    @Bean("transactionMapper")
    public ModelMapper transactionMapper(){ return new ModelMapper();}


    @Bean("usersMapper")
    public ModelMapper usersMapper(){
        ModelMapper mapper = new ModelMapper();//yulieth
        TypeMap<UsersDTO, Users> typeMap = mapper.createTypeMap(UsersDTO.class,Users.class);//yulieth
        typeMap.addMapping(UsersDTO::getIdUser, Users::setIdUser);//yulieth
        typeMap.addMapping(UsersDTO::getName, Users::setName);//yulieth
        return mapper;
    }


}

/*
        TypeMap<EmployeeDTO, Employee> typeMap = mapper.createTypeMap(EmployeeDTO.class,Employee.class);
        typeMap.addMapping(EmployeeDTO::getIdEmployee, Employee::setIdEmployee);
        typeMap.addMapping(EmployeeDTO::getName, Employee::setName);
        typeMap.addMapping(EmployeeDTO::getIdEmployee, Employee::setEnterprise);
        return mapper;}*/
/*
        ModelMapper mapper = new ModelMapper();
        TypeMap<EmployeeDTO, Employee> typeMap = mapper.createTypeMap(EmployeeDTO.class, Employee.class);
        typeMap.addMapping(EmployeeDTO::getEnterprise, (dest, v) -> dest.getEnterprise().setIdEnterprise((Integer)v));
        return mapper;
    }*/
 /*@Bean("transactionDetailMapper")
    public ModelMapper transactionDetailMapper(){ return new ModelMapper();}*/
