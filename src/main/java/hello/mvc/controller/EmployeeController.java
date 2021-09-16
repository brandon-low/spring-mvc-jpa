package hello.mvc.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import hello.mvc.entity.Employee;
import hello.mvc.service.EmployeeService;
/**
 * Created by JavaDeveloperZone on 19-07-2017.
 */
@RestController     // for rest response
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // to add new employee
    @RequestMapping(value = "save",method = RequestMethod.POST)     // or user @GetMapping
    public Employee save(Employee employee){
        return employeeService.save(employee);
    }

    // list of all employee
    @RequestMapping(value = "listEmployee",method = RequestMethod.GET)   // or use @GetMapping
    public java.util.List<Employee> listEmployee() {
        return employeeService.findAll();
    }

    // delete specific employee using employee id
    @RequestMapping(value = "delete", method = RequestMethod.DELETE)        // or use @DeleteMapping
    public void delete(@RequestParam("id")long id){
         employeeService.delete(id);
    }

    // search employee start with name
    @RequestMapping(value = "startWithName/{name}")
    public java.util.List<Employee> findByName(@PathVariable("name")String name){
        return employeeService.findEmployeeByEmployeeNameStartingWith(name);
    }

    // search employee by role
    @RequestMapping(value = "findByRole/{role}")
    public java.util.List<Employee> findByRole(@PathVariable("role")String role){
        return employeeService.findEmployeeByEmployeeRole(role);
    }

    @RequestMapping(value = "/query")
    public java.util.List<Employee> test(){
        //return employeeService.findByCriteria("Jone");
        // return employeeService.findByCriteria("Jo","ADMIN");
        // return  employeeService.findByLikeCriteria("info");
        // return employeeService.findByLikeAndBetweenCriteria(null,0,0);
    	// Pageable paging = PageRequest.of(page, size);
        return  employeeService.findByPagingCriteria("Jone", PageRequest.of(0,10));
    }
}
