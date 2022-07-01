package com.mindex.challenge.controller;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.reports.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class EmployeeController {
    private static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);

    private int numberOfReports = 0;

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/employee")
    public Employee create(@RequestBody Employee employee) {
        LOG.debug("Received employee create request for [{}]", employee);

        return employeeService.create(employee);
    }

    @GetMapping("/employee/{id}")
    public Employee read(@PathVariable String id) {
        LOG.debug("Received employee create request for id [{}]", id);

        return employeeService.read(id);
    }

    @PutMapping("/employee/{id}")
    public Employee update(@PathVariable String id, @RequestBody Employee employee) {
        LOG.debug("Received employee create request for id [{}] and employee [{}]", id, employee);

        employee.setEmployeeId(id);
        return employeeService.update(employee);
    }

    @GetMapping("employee/reporting-structure/{id}")
    public ReportingStructure getReportingStructure(@PathVariable String id){
        LOG.debug("Get reporting structure for employee: " + id);

        //Need to reset the class variable
        numberOfReports=0;
        Employee employee = employeeService.read(id);

        return buildReportingStructure(employee);
    }

    /**
     * Build the full reporting structure for the given employee.
     * Use recursive call to fill out the structure for each successive direct report
     * @param employee
     * @return ReportingStructure
     */
    private ReportingStructure buildReportingStructure(Employee employee) {

        ReportingStructure reportingStructure = new ReportingStructure();
        List<Employee> reports = employee.getDirectReports();
        List<Employee> newEmpList = new ArrayList<>();
        Employee newEmp;
        if (reports != null) {
            numberOfReports+=reports.size();
            for (Employee report : reports) {
                newEmp = employeeService.read(report.getEmployeeId());
                newEmpList.add(newEmp);
                buildReportingStructure(newEmp);
            }
        }
        employee.setDirectReports(newEmpList);
        reportingStructure.setEmployee(employee);
        reportingStructure.setNumberOfReports(numberOfReports);
        return reportingStructure;
    }

    // Started with this hack method to help define what the recursive method would ultimately accomplish.
    // Included just to show thought process
    /**private ReportingStructure buildHackRptStructure(Employee employee){
        ReportingStructure reportingStructure = new ReportingStructure();
        if(employee.getDirectReports()==null || employee.getDirectReports().size()==0){
            reportingStructure.setEmployee(employee);
            reportingStructure.setNumberOfReports(0);
        }
        else{
            List<Employee> newEmpList = new ArrayList<>();
            for(Employee em: employee.getDirectReports()){
                Employee newEmp = employeeService.read(em.getEmployeeId());
                if(newEmp.getDirectReports()==null || newEmp.getDirectReports().size()==0) {
                    newEmpList.add(newEmp);
                }
                else{
                    List<Employee> newEmpList1 = new ArrayList<>();
                    for(Employee em1: newEmp.getDirectReports()){
                        Employee newEmp1 = employeeService.read(em1.getEmployeeId());
                        newEmpList1.add(newEmp1);
                    }
                    newEmp.setDirectReports(newEmpList1);
                    newEmpList.add(newEmp);
                }
            }
            employee.setDirectReports(newEmpList);
            reportingStructure.setEmployee(employee);
            reportingStructure.setNumberOfReports(employee.getDirectReports().size());
        }
        return reportingStructure;
    } **/
}
