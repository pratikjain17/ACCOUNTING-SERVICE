package com.pratik.www.salary.controller;

import com.pratik.www.salary.db.entity.SalaryEntity;
import com.pratik.www.salary.model.WorkHourRequest;

import com.pratik.www.salary.service.AccountingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounting")
public class AccountingController {

    @Autowired
    private AccountingService accountingService;

    @GetMapping("/calculateSalary/{employeeId}/{yearMonth}")
    public SalaryEntity calculateSalary(@PathVariable String employeeId, @PathVariable Number yearMonth){
        return this.accountingService.calculateSalary(employeeId,yearMonth);
    }

}
