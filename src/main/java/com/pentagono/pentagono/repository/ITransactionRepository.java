package com.pentagono.pentagono.repository;

import com.pentagono.pentagono.model.Transaction;
import com.pentagono.pentagono.model.TransactionDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface ITransactionRepository extends IGenericRepository<Transaction,Long>{

    //Metodo para filtrar movimientos por empleado
    @Query(value ="select * from transaction where idEmployee= ?1", nativeQuery = true)
    public abstract ArrayList<TransactionDetail> findByEmployee(Long id);

    //Metodo para filtrar movimientos por empresa
    @Query(value="select * from transaction where idEmployee in (select id from employee where idEnterprise= ?1)", nativeQuery = true)
    public abstract ArrayList<TransactionDetail> findByEnterprise(Long id);

    //Metodo para ver la suma de TODOS LOS MOVIMIENTOS
    @Query(value="SELECT SUM(amount) from transaction", nativeQuery = true)
    public abstract Long SumarMonto();

    //Metodo para ver la suma de los montos por empleado
    @Query(value="SELECT SUM(amount) from transaction where idEmployee=?1", nativeQuery = true)
    public abstract Long MontosPorEmpleado(Long id); //id del empleado

    //Metodo para ver la suma de los movimientos por empresa
    @Query(value="select sum(amount) from transaction where idEnterprise in (select id from employee where idEnterprise= ?1)", nativeQuery = true)
    public abstract Long MontosPorEmpresa(Long id); //Id de la empresa

    //Metodo que me trae el id de un usuario cuando tengo su correo
    @Query(value="select id from employee where email=?1", nativeQuery = true)
    public abstract Long IdPorEmail(String email);
}

