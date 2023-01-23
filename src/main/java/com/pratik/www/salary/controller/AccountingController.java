package com.pratik.www.salary.controller;

import com.pratik.www.salary.db.entity.SalaryEntity;

import com.pratik.www.salary.service.AccountingService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounting")
public class AccountingController {

    @Autowired
    private AccountingService accountingService;

    Logger logger = LoggerFactory.getLogger(AccountingController.class);

    @GetMapping("/calculateSalary/{employeeId}/{yearMonth}")
    @CircuitBreaker(name = "employeeAndWorkHourBreaker", fallbackMethod = "employeeAndWorkHourFallback")
    public SalaryEntity calculateSalary(@PathVariable String employeeId, @PathVariable Number yearMonth){
        return this.accountingService.calculateSalary(employeeId,yearMonth);
    }

    public SalaryEntity employeeAndWorkHourFallback(String employeeId, Number yearMonth, Exception ex){
        logger.info("Fallback is executed because one the services is down: ");
        logger.info(ex.getMessage());
        Number baseSalary = 20000;
        SalaryEntity salary = new SalaryEntity.SalaryEntityBuilder()
                .setEmployeeId(employeeId)
                .setYearMonth(yearMonth)
                .setAmount(baseSalary)
                .build();

        return salary;
    }

}
