package com.mindex.challenge.data;

import java.util.List;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.controller.EmployeeController;

public class ReportingStructure {
    private Employee employee;
    private int numberOfReports;
    private EmployeeController controller;

    public ReportingStructure() {
    }

    public int getNumberOfReports(Employee employee, EmployeeController controller) {
        int numOfReports = 0;

        if (employee.getDirectReports() != null) {
            numOfReports = employee.getDirectReports().size();
            for (Employee report : employee.getDirectReports()) {
                String reportId = report.getEmployeeId();
                report = controller.read(reportId);

                numOfReports += new ReportingStructure().getNumberOfReports(report, controller);
            }
        }
        return numOfReports;
        
    }
}
