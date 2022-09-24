package com.pentagono.pentagono.controller.back;

import com.pentagono.pentagono.dto.EmployeeDTO;
import com.pentagono.pentagono.exceptions.ModelNotFoundException;
import com.pentagono.pentagono.model.Employee;
import com.pentagono.pentagono.service.IEmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("employees")
public class EmployeeController {
    @Autowired
    private IEmployeeService iEmployeeService;

    @Autowired
    @Qualifier("employeeMapper")
    private ModelMapper mapper;

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee){return iEmployeeService.createEmployee(employee);}

    @GetMapping
    public List<Employee> getEmployee(){return iEmployeeService.getAllEmployees();}

    /*@GetMapping
    public ResponseEntity<List<EmployeeDTO>> readAll() throws Exception{
        List<EmployeeDTO> list = iEmployeeService.readAll().stream()
                .map(l -> mapper.map(l, EmployeeDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> readById(@PathVariable("id") Long id) throws Exception{
        Employee empl = iEmployeeService.readById(id);
        if(empl == null){
            throw new ModelNotFoundException("Id del Empleado Nº: " + id + " No fue encontrado");
        }
        EmployeeDTO dto = mapper.map(empl, EmployeeDTO.class);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }*/

    /*@PostMapping
    public ResponseEntity<EmployeeDTO> create(@Valid @RequestBody EmployeeDTO employeeDto) throws Exception{
        Employee empl = iEmployeeService.create(mapper.map(employeeDto,Employee.class));
        EmployeeDTO dto = mapper.map(empl,EmployeeDTO.class);
        return new ResponseEntity<>(dto,HttpStatus.CREATED);
    }*/



    /*@GetMapping("/{name}")
    public ResponseEntity<EmployeeDTO> findByName(@PathVariable("name") String name) throws Exception{
        List<Employee> empl = iEmployeeService.findByName(name);
        if(empl == null){
            throw new ModelNotFoundException("Nombre del Empleado Nº: " + name + " No fue encontrado");
        }
        EmployeeDTO dto = mapper.map(empl, EmployeeDTO.class);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }*/

    @PutMapping
    public ResponseEntity<EmployeeDTO> update(@Valid @RequestBody EmployeeDTO employeeDto) throws Exception{
        Employee empl = iEmployeeService.readById(employeeDto.getIdEmployee());
        if(empl == null){
            throw new ModelNotFoundException("Id del Empleado no encontrado: " + employeeDto.getIdEmployee());
        }
        Employee employee = iEmployeeService.update(mapper.map(employeeDto, Employee.class));
        EmployeeDTO dto = mapper.map(employee, EmployeeDTO.class);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EmployeeDTO> updatePatch(@Valid @RequestBody EmployeeDTO employeeDto) throws Exception{
        Employee empl = iEmployeeService.readById(employeeDto.getIdEmployee());
        if(empl == null){
            throw new ModelNotFoundException("Id del Empleado no encontrado: " + employeeDto.getIdEmployee());
        }
        Employee employee = iEmployeeService.update(mapper.map(employeeDto, Employee.class));
        EmployeeDTO dto = mapper.map(employee, EmployeeDTO.class);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) throws Exception{
        Employee empl = iEmployeeService.readById(id);
        if(empl == null){
            throw new ModelNotFoundException("Id del Empleado no encontrado: " + id);
        }
        iEmployeeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /*CODIGO NUEVO
    @GetMapping("/")
    public String viewHomePage(Model model){
        model.addAttribute("ListEmployee",iEmployeeService.getAllEmployees());
            return "index";
    }

   /* @GetMapping("/")
    public String showNewEmployeeForm(Model model){
        Employee employee = new Employee();
        model.addAttribute("Employee",employee);
        return "new_employee";
    }
  clase mintic thymeleaf
    @GetMapping List<Employee> getUsers(){return iEmployeeService.getAllEmployees();}}

    @PostMapping
    public Employee createUser(@RequestBody Employee employee){return iEmployeeService.createUser(employee);}*/

}
