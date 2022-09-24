package com.pentagono.pentagono.repository;

import com.pentagono.pentagono.model.Employee;
import com.pentagono.pentagono.model.Enterprise;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface IEnterpriseRepository extends IGenericRepository<Enterprise, Long>{

    @Query(value="SELECT * FROM empleado where empresa_id= ?1", nativeQuery=true)
    public abstract ArrayList<Employee> findByEnterprise(Long id);

}
