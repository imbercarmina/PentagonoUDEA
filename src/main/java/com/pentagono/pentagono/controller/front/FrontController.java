package com.pentagono.pentagono.controller.front;

import com.pentagono.pentagono.model.Employee;
import com.pentagono.pentagono.model.Enterprise;
import com.pentagono.pentagono.model.Transaction;
import com.pentagono.pentagono.model.TransactionDetail;
import com.pentagono.pentagono.repository.ITransactionRepository;
import com.pentagono.pentagono.service.IEmployeeService;
import com.pentagono.pentagono.service.IEnterpriseService;
import com.pentagono.pentagono.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/")
public class FrontController {


    @Autowired
    private IEmployeeService iEmployeeService;
    @Autowired
    private IEnterpriseService iEnterpriseService;
    @Autowired
    private ITransactionService iTransactionService;



    /*CODIGO PARA HOME*/
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }


    /*CODIGO PARA EMPLEADO*/
    @RequestMapping(value = "/employeeS", method = RequestMethod.GET)
    public String employeeS(Model model) {
        List<Employee> employees = this.iEmployeeService.getAllEmployees();
        model.addAttribute("employeeS", employees);
        return "see_employee";
    }
    @GetMapping("/AgregarEmpleado")
    public String nuevoEmpleado(Model model, @ModelAttribute("mensaje") String mensaje){
        Employee empl= new Employee();
        model.addAttribute("empl",empl);
        model.addAttribute("mensaje",mensaje);
        List<Enterprise> listaEmpresas= iEnterpriseService.getAllEnterprises();
        model.addAttribute("emprelist",listaEmpresas);
        return "new_employee"; //Llamar HTML
    }

    @PostMapping("/GuardarEmpleado")
    public String guardarEmpleado(Employee empl, RedirectAttributes redirectAttributes){
        /*String passEncriptada=passwordEncoder().encode(empl.getPassword());
        empl.setPassword(passEncriptada);*/
        if(iEmployeeService.saveOrUpdateEmpleado(empl)==true){
            redirectAttributes.addFlashAttribute("mensaje","saveOK");
            return "redirect:/see_employee";
        }
        redirectAttributes.addFlashAttribute("mensaje","saveError");
        return "redirect:/new_employee";
    }

    @GetMapping("/EditarEmpleado/{id}")
    public String editarEmpleado(Model model, @PathVariable Integer id, @ModelAttribute("mensaje") String mensaje){
        Employee empl=iEmployeeService.getEmployeeById(id).get();
        //Creamos un atributo para el modelo, que se llame igualmente empl y es el que ira al html para llenar o alimentar campos
        model.addAttribute("empl",empl);
        model.addAttribute("mensaje", mensaje);
        List<Enterprise> listaEmpresas= iEnterpriseService.getAllEnterprises();
        model.addAttribute("emprelist",listaEmpresas);
        return "editarEmpleado";
    }

    @PostMapping("/ActualizarEmpleado")
    public String updateEmpleado(@ModelAttribute("empl") Employee empl, RedirectAttributes redirectAttributes){
        Long id=empl.getIdEmployee();
        /*String Oldpass=iEmployeeService.getEmployeeById(id).get().getPassword(); //Con ese id consultamos la contraseña que ya esta en la base
        if(!empl.getPassword().equals(Oldpass)){
            String passEncriptada=passwordEncoder().encode(empl.getPassword());
            empl.setPassword(passEncriptada);
        }*/
        if(iEmployeeService.saveOrUpdateEmpleado(empl)){
            redirectAttributes.addFlashAttribute("mensaje","updateOK");
            return "redirect:/see_employee";
        }
        redirectAttributes.addFlashAttribute("mensaje","updateError");
        return "redirect:/edit_employee/"+empl.getIdEmployee();

    }

    @GetMapping("/EliminarEmpleado/{id}")
    public String eliminarEmpleado(@PathVariable Long id, RedirectAttributes redirectAttributes){
        if (iEmployeeService.deleteEmployee(id)){
            redirectAttributes.addFlashAttribute("mensaje","deleteOK");
            return "redirect:/see_employee";
        }
        redirectAttributes.addFlashAttribute("mensaje", "deleteError");
        return "redirect:/see_employee";
    }

    @GetMapping("/Empresa/{id}/Empleados")
    public String verEmpleadosPorEmpresa(@PathVariable("id") Long id, Model model){
        List<Employee> listaEmpleados = iEmployeeService.obtenerPorEmpresa(id);
        model.addAttribute("emplelist",listaEmpleados);
        return "see_employee";
    }






    /*CODIGO PARA EMPRESA*/

        @RequestMapping(value = "/enterpriseS", method = RequestMethod.GET)/*Ver Empresas*/
        public String enterpriseS (Model model){
            List<Enterprise> enterprises = this.iEnterpriseService.getAllEnterprises();
            model.addAttribute("enterpriseS", enterprises);
            return "see_enterprise";
        }

        @RequestMapping(value = "/enterpriseN", method = RequestMethod.GET)/*Crear Empresa*/
        public String enterpriseN (@ModelAttribute Enterprise enterprise, Model model){
            model.addAttribute("enterpriseN", new Enterprise());
            return "new_enterprise";
        }

    @GetMapping("/AgregarEmpresa")
    public String nuevaEmpresa(Model model, @ModelAttribute("mensaje") String mensaje){
        Enterprise emp= new Enterprise();
        model.addAttribute("emp",emp);
        model.addAttribute("mensaje",mensaje);
        return "agregarEmpresa";
    }

    @PostMapping("/GuardarEmpresa")
    public String guardarEmpresa(Enterprise emp, RedirectAttributes redirectAttributes){
        if(iEnterpriseService.saveOrUpdateEmpresa(emp)==true){
            redirectAttributes.addFlashAttribute("mensaje","saveOK");
            return "redirect:/VerEmpresas";
        }
        redirectAttributes.addFlashAttribute("mensaje","saveError");
        return "redirect:/AgregarEmpresa";
    }

    @GetMapping("/EditarEmpresa/{id}")
    public String editarEmpresa(Model model, @PathVariable Integer id, @ModelAttribute("mensaje") String mensaje){
        Enterprise emp=iEnterpriseService.getEnterpriseById(id);
        model.addAttribute("mensaje", mensaje);
        return "editarEmpresa";
    }


    @PostMapping("/ActualizarEmpresa")
    public String updateEmpresa(@ModelAttribute("emp") Enterprise emp, RedirectAttributes redirectAttributes){
        if(iEnterpriseService.saveOrUpdateEmpresa(emp)){
            redirectAttributes.addFlashAttribute("mensaje","updateOK");
            return "redirect:/see_enterprise";
        }
        redirectAttributes.addFlashAttribute("mensaje","updateError");
        return "redirect:/edit_enterprise/"+emp.getIdEnterprise();

    }

    @GetMapping("/EliminarEmpresa/{id}")
    public String eliminarEmpresa(@PathVariable Long id, RedirectAttributes redirectAttributes){
        if (iEnterpriseService.deleteEnterprise(id)==true){
            redirectAttributes.addFlashAttribute("mensaje","deleteOK");
            return "redirect:/VerEmpresas";
        }
        redirectAttributes.addFlashAttribute("mensaje", "deleteError");
        return "redirect:/VerEmpresas";
    }



    /*CODIGO PARA TRANSACCION*/
   /* @RequestMapping ("/VerMovimientos")// Controlador que nos lleva al template donde veremos todos los movimientos
    public String viewMovimientos(@RequestParam(value="pagina", required=false, defaultValue = "1") int NumeroPagina,
                                  @RequestParam(value="medida", required=false, defaultValue = "5") int medida,
                                  Model model, @ModelAttribute("mensaje") String mensaje){
        Page<TransactionDetail> paginaMovimientos= ITransactionRepository.findAll(PageRequest.of(NumeroPagina,medida));
        model.addAttribute("movlist",paginaMovimientos.getContent());
        model.addAttribute("paginas",new int[paginaMovimientos.getTotalPages()]);
        model.addAttribute("paginaActual", NumeroPagina);
        model.addAttribute("mensaje",mensaje);
        Long sumaMonto=iTransactionService.obtenerSumaMontos();
        model.addAttribute("SumaMontos",sumaMonto);//Mandamos la suma de todos los montos a la plantilla
        return "verMovimientos"; //Llamamos al HTML
    }*/

    @GetMapping("/AgregarMovimiento") //Controlador que nos lleva al template donde podremos crear un nuevo movimiento
    public String nuevoMovimiento(Model model, @ModelAttribute("mensaje") String mensaje){
        TransactionDetail movimiento= new TransactionDetail();
        model.addAttribute("mov",movimiento);
        model.addAttribute("mensaje",mensaje);
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        String correo=auth.getName();
        Long idEmpleado=iTransactionService.idPorCorreo(correo);
        model.addAttribute("idEmpleado",idEmpleado);
        return "new_transaction";
    }

    @PostMapping("/GuardarMovimiento")
    public String guardarMovimiento(TransactionDetail mov, RedirectAttributes redirectAttributes){
        if(iTransactionService.saveOrUpdateMovimiento(mov)){
            redirectAttributes.addFlashAttribute("mensaje","saveOK");
            return "redirect:/VerMovimientos";
        }
        redirectAttributes.addFlashAttribute("mensaje","saveError");
        return "redirect:/AgregarMovimiento";
    }

    @GetMapping("/EditarMovimiento/{id}")
    public String editarMovimento(Model model, @PathVariable Long id, @ModelAttribute("mensaje") String mensaje){
        Transaction mov=iTransactionService.getTransactionById(id);
        //Creamos un atributo para el modelo, que se llame igualmente empl y es el que ira al html para llenar o alimentar campos
        model.addAttribute("mov",mov);
        model.addAttribute("mensaje", mensaje);
        List<Employee> listaEmpleados= iEmployeeService.getAllEmployees();
        model.addAttribute("emplelist",listaEmpleados);
        return "editarMovimiento";
    }

    @PostMapping("/ActualizarMovimiento")
    public String updateMovimiento(@ModelAttribute("mov") TransactionDetail mov, RedirectAttributes redirectAttributes){
        if(iTransactionService.saveOrUpdateMovimiento(mov)){
            redirectAttributes.addFlashAttribute("mensaje","updateOK");
            return "redirect:/VerMovimientos";
        }
        redirectAttributes.addFlashAttribute("mensaje","updateError");
        return "redirect:/EditarMovimiemto/"+mov.getId();

    }

    /*@GetMapping("/EliminarMovimiento/{id}")
    public String eliminarMovimiento(@PathVariable Long id, RedirectAttributes redirectAttributes){
        if iTransactionService.deleteTransactionById(id);{
            redirectAttributes.addFlashAttribute("mensaje","deleteOK");
            return "redirect:/VerMovimientos";
        }
        redirectAttributes.addFlashAttribute("mensaje", "deleteError");
        return "redirect:/VerMovimientos";
    }*/

    @GetMapping("/Empleado/{id}/Movimientos") //Filtro de movimientos por empleados
    public String movimientosPorEmpleado(@PathVariable("id")Integer id, Model model){
        List<TransactionDetail> movlist = iTransactionService.obtenerPorEmpleado(id);
        model.addAttribute("movlist",movlist);
        Long sumaMonto=iTransactionService.MontosPorEmpleado(id);
        model.addAttribute("SumaMontos",sumaMonto);
        return "verMovimientos"; //Llamamos al HTML
    }

    @GetMapping("/Empresa/{id}/Movimientos") //Filtro de movimientos por empresa
    public String movimientosPorEmpresa(@PathVariable("id")Integer id, Model model){
        List<TransactionDetail> movlist = iTransactionService.obtenerPorEmpresa(id);
        model.addAttribute("movlist",movlist);
        Long sumaMonto=iTransactionService.MontosPorEmpresa(id);
        model.addAttribute("SumaMontos",sumaMonto);
        return "verMovimientos"; //Llamamos al HTML
    }

    //Controlador que me lleva al template de No autorizado
    @RequestMapping(value="/Denegado")
    public String accesoDenegado(){
        return "accessDenied";
    }


    //Metodo para encriptar contraseñas
   /* @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }*/


    }
