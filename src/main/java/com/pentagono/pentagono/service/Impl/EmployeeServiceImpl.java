package com.pentagono.pentagono.service.Impl;

import com.pentagono.pentagono.model.Employee;
import com.pentagono.pentagono.repository.IEmployeeRepository;
import com.pentagono.pentagono.repository.IGenericRepository;
import com.pentagono.pentagono.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl extends CRUDImpl<Employee, Long> implements IEmployeeService {

    @Autowired
    private IEmployeeRepository iEmployeeRepository;

    @Autowired
    private IEmployeeRepository repo;

    //Metodo para ver todos los empleados registrados
    public List<Employee> getAllEmpleado(){
        List<Employee> empleadoList= new ArrayList<>();
        iEmployeeRepository.findAll().forEach(empleado -> empleadoList.add(empleado));
        return empleadoList;
    }

    //Metodo para buscar empleados por ID
    public Optional<Employee> getEmpleadoById(Long id){
        return iEmployeeRepository.findById(id);
    }

    //Metodo para buscar empleados por empresa
    public ArrayList<Employee> obtenerPorEmpresa(Long id){
        return iEmployeeRepository.findByEnterprise(id);
    }

    @Override
    public Employee createUser(Employee employee) {
        return null;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return null;
    }

    @Override
    public void saveEmployee(Employee employee) {

    }

    @Override
    public Employee getEmployeeById(long id) {
        return null;
    }

    @Override
    public void deleteEmployeeById(Long id) {

    }

    @Override
    public Employee createEmployee(Employee employee) {
        return null;
    }

    //Metodo para guardar o actualizar registros en Empleados
    public boolean saveOrUpdateEmpleado(Employee empl){
        Employee emp=iEmployeeRepository.save(empl);
        if (iEmployeeRepository.findById(emp.getIdEmployee())!=null){
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteEmployee(Long id) {
        return false;
    }

    //Metodo para eliminar un registro de Empleado por Id
    public boolean deleteEmpleado(Long id){
        iEmployeeRepository.deleteById(id);
        if(this.iEmployeeRepository.findById(id).isPresent()){
            return false;
        }
        return true;
    }

    @Override
    protected IGenericRepository<Employee, Long> getRepo() {
        return null;
    }
}


















/*

    @Override
    protected IGenericRepository<Employee, Long> getRepo(){ return repo; }

    @Override
    public void saveEmployee(Employee employee) { }


    @Override
    public Employee getEmployeeById(long id) {
        Optional<Employee>optional=iEmployeeRepository.findById(id);
        Employee employee=null;
        if (optional.isPresent()){
            employee=optional.get();
        } else{
            throw  new RuntimeException("El empleado con el id "+id+"no fue encontrado en la base de datos");
        }
        return employee;
    }

    @Override
    public void deleteEmployeeById(Long id) {
        this.iEmployeeRepository.deleteById(id);
    }



    /*metodos nuevos archivados

    @Override
    public List<Employee> findByName(String name) {
        return repo.findByName(name);
    }


    @Override
    public Employee create(Employee employee) {
        return this.iEmployeeRepository.save(employee);
    }

    @Override
    public Page<Employee> findPaginated(int pageNo, int pageSiza, String sortField, String sortDirection) {
        return null;
    }*/




