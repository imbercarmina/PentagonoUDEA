package com.pentagono.pentagono.service;

import com.pentagono.pentagono.model.Enterprise;
import com.pentagono.pentagono.model.Transaction;
import com.pentagono.pentagono.model.TransactionDetail;
import java.util.List;


public interface ITransactionService extends ICRUD<Transaction,Long>{


    List<Transaction> getAllTransactions();
    void saveTransaction(Transaction transaction);
    Transaction getTransactionById(long id);
    void deleteTransactionById(Long id);

    Transaction createTransaction(Transaction transaction);

    boolean saveOrUpdateMovimiento(TransactionDetail mov);

    Long MontosPorEmpleado(Integer id);

    Long MontosPorEmpresa(Integer id);

    Long obtenerSumaMontos();

    List<TransactionDetail> obtenerPorEmpleado(Integer id);

    List<TransactionDetail> obtenerPorEmpresa(Integer id);

    Long idPorCorreo(String correo);
}
