package com.pentagono.pentagono.service.Impl;

import com.pentagono.pentagono.model.Employee;
import com.pentagono.pentagono.model.Enterprise;
import com.pentagono.pentagono.model.Transaction;
import com.pentagono.pentagono.model.TransactionDetail;
import com.pentagono.pentagono.repository.IGenericRepository;
import com.pentagono.pentagono.repository.ITransactionRepository;
import com.pentagono.pentagono.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionServiceImpl extends CRUDImpl <Transaction, Long> implements ITransactionService {
    @Autowired
    private ITransactionRepository iTransactionRepository;
    @Autowired
    private ITransactionRepository iTransactionDetailRepository;

   /* public List<TransactionDetail> getAllMovimientos(){
        List<TransactionDetail> movimientosList = new ArrayList<>();
        iTransactionRepository.findAll().forEach(movimiento -> movimientosList.add(transaction));
        return movimientosList;
    }*/

    /*public TransactionDetail getMovimientoById(Long id){ //Ver movimientos por id
        return iTransactionRepository.findById(id).get();
    }*/

    /*public boolean saveOrUpdateMovimiento(TransactionDetail transactionDetail){ //Guardar o actualizar elementos
        TransactionDetail mov=iTransactionDetailRepository.save(transactionDetail);
        if (iTransactionDetailRepository.findById(mov.getId())!=null){
            return true;
        }
        return false;
    }*/

    public boolean deleteTransaction(Long id){
        iTransactionRepository.deleteById(id);
        if(this.iTransactionRepository.findById(id).isPresent()){
            return false;
        }
        return true; //
    }

    public ArrayList<TransactionDetail> obtenerPorEmpleado(Long id) {
        return iTransactionRepository.findByEmployee(id);
    }

    public ArrayList<TransactionDetail> obtenerPorEmpresa(Long id) {
        return iTransactionRepository.findByEnterprise(id);
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return null;
    }

    @Override
    public void saveTransaction(Transaction transaction) {

    }

    @Override
    public Transaction getTransactionById(long id) {
        return null;
    }

    @Override
    public void deleteTransactionById(Long id) {

    }

    @Override
    public Transaction createTransaction(Transaction transaction) {
        return null;
    }

    @Override
    public boolean saveOrUpdateMovimiento(TransactionDetail mov) {
        return false;
    }

    @Override
    public Long MontosPorEmpleado(Integer id) {
        return null;
    }

    @Override
    public Long MontosPorEmpresa(Integer id) {
        return null;
    }

    //Servicio para ver la suma de todos los montos
    public Long obtenerSumaMontos(){
        return iTransactionRepository.SumarMonto();
    }

    @Override
    public List<TransactionDetail> obtenerPorEmpleado(Integer id) {
        return null;
    }

    @Override
    public List<TransactionDetail> obtenerPorEmpresa(Integer id) {
        return null;
    }

    @Override
    public Long idPorCorreo(String correo) {
        return null;
    }

    //Servicio para ver la suma de los montos por empleado
    public Long MontosPorEmpleado(Long id){
        return iTransactionRepository.MontosPorEmpleado(id);
    }

    //Servicio para ver la suma de los montos por empresa
    public Long MontosPorEmpresa(Long id){
        return iTransactionRepository.MontosPorEmpresa(id);
    }


    @Override
    protected IGenericRepository<Transaction, Long> getRepo() {
        return null;
    }
}

