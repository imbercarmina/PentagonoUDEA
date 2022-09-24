package com.pentagono.pentagono.repository;

import com.pentagono.pentagono.model.Employee;
import com.pentagono.pentagono.model.Enterprise;
import com.pentagono.pentagono.model.Users;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface IEmployeeRepository extends IGenericRepository<Employee, Long>{

    @Query(value="SELECT * FROM employee where enterprise_idEnterprise= ?1", nativeQuery=true)
    public abstract ArrayList<Employee> findByEnterprise(Long id);


}
