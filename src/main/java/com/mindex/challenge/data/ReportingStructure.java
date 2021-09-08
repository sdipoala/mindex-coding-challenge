package com.mindex.challenge.data;

import java.util.List;

import com.mindex.challenge.controller.EmployeeController;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.EmployeeService;

public class ReportingStructure {
    private Employee employee;
    private int numberOfReports;
    private EmployeeController controller;

    public ReportingStructure(Employee employee, EmployeeController controller) {
        this.employee = employee;
        this.controller = controller;
        this.numberOfReports = calcNumberOfReports();
    }

    public Employee getEmployee() {
        return employee;
    }

    public int getNumberOfReports() {
        return numberOfReports;
    }

    public int calcNumberOfReports() {

        int numOfReports = 0;

        if (employee.getDirectReports() != null) {
            numOfReports = employee.getDirectReports().size();
            for (Employee report : employee.getDirectReports()) {
                String reportId = report.getEmployeeId();
                report = controller.read(reportId);

                numOfReports += new ReportingStructure(report, controller).calcNumberOfReports();
            }
        }
        
        return numOfReports;
        
    }
}
