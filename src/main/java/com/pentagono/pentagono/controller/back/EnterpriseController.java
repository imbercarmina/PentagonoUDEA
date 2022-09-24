package com.pentagono.pentagono.controller.back;

import com.pentagono.pentagono.dto.EnterpriseDTO;
import com.pentagono.pentagono.exceptions.ModelNotFoundException;
import com.pentagono.pentagono.model.Enterprise;
import com.pentagono.pentagono.service.IEnterpriseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/enterprises")
public class EnterpriseController {

    @Autowired
    private IEnterpriseService iEnterpriseService;

    @Autowired
    @Qualifier("enterpriseMapper")
    private ModelMapper mapper;

    @PostMapping
    public Enterprise createEnterprise(@RequestBody Enterprise enterprise){return iEnterpriseService.createEnterprise(enterprise);}

    @GetMapping
    public List<Enterprise> getEnterprise(){return iEnterpriseService.getAllEnterprises();}

    /*@GetMapping
    public ResponseEntity<List<EnterpriseDTO>> readAll() throws Exception{
        List<EnterpriseDTO> list = iEnterpriseService.readAll().stream()
                .map(e -> mapper.map(e, EnterpriseDTO.class))
                .collect(Collectors.toList());
            return new ResponseEntity <>(list, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EnterpriseDTO> create(@Valid @RequestBody EnterpriseDTO enterpriseDTO) throws Exception{
        Enterprise ent = iEnterpriseService.create(mapper.map(enterpriseDTO, Enterprise.class));
        EnterpriseDTO dto = mapper.map(ent,EnterpriseDTO.class);
        return new ResponseEntity<>(dto,HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnterpriseDTO> readById(@PathVariable("id") Long id) throws Exception{
        Enterprise ent = iEnterpriseService.readById(id);
        if(ent == null){
            throw new ModelNotFoundException("Id de la empresa ingresado no fue encontrado en la Base de datos Emplas: " + id);
        }
        EnterpriseDTO dto = mapper.map(ent, EnterpriseDTO.class);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnterpriseDTO> update(@Valid @RequestBody EnterpriseDTO enterpriseDTO) throws Exception{
        Enterprise ent = iEnterpriseService.readById(enterpriseDTO.getIdEnterprise());
        if(ent == null){
            throw new ModelNotFoundException("No fue encontrado en la Base de Datos Emplas Id de la empresa: " + enterpriseDTO.getIdEnterprise());
        }
        Enterprise enterprise = iEnterpriseService.update(mapper.map(enterpriseDTO, Enterprise.class));
        EnterpriseDTO dto = mapper.map(enterprise, EnterpriseDTO.class);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EnterpriseDTO> updatePatch(@Valid @RequestBody EnterpriseDTO enterpriseDTO) throws Exception{
        Enterprise ent = iEnterpriseService.readById(enterpriseDTO.getIdEnterprise());
        if(ent == null){
            throw new ModelNotFoundException("No fue encontrado en la Base de Datos Emplas Id de la empresa: " + enterpriseDTO.getIdEnterprise());
        }*/
        /*Enterprise enterprise = iEnterpriseService.update(mapper.map(enterpriseDTO, Enterprise.class));
        EnterpriseDTO dto = mapper.map(enterprise, EnterpriseDTO.class);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) throws Exception{
        Enterprise ent = iEnterpriseService.readById(id);
        if(ent == null){
            throw new ModelNotFoundException("Id no encontrado en la Base de datos Emplas: " + id);
        }
        iEnterpriseService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/find/name/{param}")
    public ResponseEntity<List<EnterpriseDTO>> findByName(@PathVariable("param") String param) throws Exception
    {
        List<EnterpriseDTO> list = service.findByName(param).stream()
                .map(c -> mapper.map(c, EnterpriseDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/find/name/like/{param}")
    public ResponseEntity<List<EnterpriseDTO>> findByNameLike(@PathVariable("param") String param) throws Exception
    {
        List<EnterpriseDTO> list = service.findByNameLike(param).stream()
                .map(e -> mapper.map(e, EnterpriseDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }*/


}
