package com.pentagono.pentagono.service.Impl;

import com.pentagono.pentagono.model.Employee;
import com.pentagono.pentagono.model.Enterprise;
import com.pentagono.pentagono.repository.IEnterpriseRepository;
import com.pentagono.pentagono.repository.IGenericRepository;
import com.pentagono.pentagono.service.IEnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EnterpriseServiceImpl extends CRUDImpl<Enterprise, Long> implements IEnterpriseService {

    @Autowired
    private IEnterpriseRepository iEnterpriseRepository;

    @Autowired
    private IEnterpriseRepository repo;

    //Metodo que retornará la lista de empresas usando metodos heredados del jpaRepository
    public List<Enterprise> getAllEnterprises() {
        List<Enterprise> empresaList = new ArrayList<>();
        iEnterpriseRepository.findAll().forEach(empresa -> empresaList.add(empresa));
        return empresaList;
    }

    @Override
    public void saveEnterprise(Enterprise enterprise) {

    }

    @Override
    public Enterprise getEnterpriseById(long id) {
        return null;
    }

    @Override
    public void deleteEnterpriseById(Long id) {

    }

    @Override
    public Enterprise createEnterprise(Enterprise enterprise) {
        return null;
    }

    @Override
    public boolean deleteEnterprise(Long id) {
        return false;
    }

    //Metodo que me trae un objeto de tipo Empresa cuando cuento con el id de la misma
    public Enterprise getEnterpriseById(Long id) {
        return iEnterpriseRepository.findById(id).get();
    }

    //Metodo para guardar o actualizar objetos de tipo Empresa
    public boolean saveOrUpdateEmpresa(Enterprise enterprise) {
        Enterprise emp = iEnterpriseRepository.save(enterprise);
        if (iEnterpriseRepository.findById(emp.getIdEnterprise()) != null) {
            return true;
        }
        return false;
    }

    //Metodo para eliminar empresas
    public boolean deleteEmpresa(Long id) {
        iEnterpriseRepository.deleteById(id);

        if (iEnterpriseRepository.findById(id).isPresent()) {
            return true;
        }
        return false;
    }

    @Override
    protected IGenericRepository<Enterprise, Long> getRepo() {
        return null;
    }
}