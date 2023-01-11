package com.pratik.www.salary.external.services;

import com.pratik.www.salary.model.WorkHourRequest;
import com.pratik.www.salary.model.EmployeeLeave;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@FeignClient(name = "WORKHOUR-SERVICE")
public interface WorkHourService {
    @PostMapping("/employeeleave/find")
    @ResponseStatus(HttpStatus.FOUND)
    public Optional<EmployeeLeave> getEmployeeLeave(@RequestBody WorkHourRequest request);

//    @GetMapping("/employeeleave/find/{employeeId}/{yearMonth}")
//    @ResponseStatus(HttpStatus.FOUND)
//    public Optional<EmployeeLeave> getEmployeeLeave(@PathVariable String employeeId, @PathVariable Number yearMonth);
}
