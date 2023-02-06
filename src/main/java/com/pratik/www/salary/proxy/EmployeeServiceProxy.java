package com.pratik.www.salary.proxy;

import com.pratik.www.salary.model.EmployeeEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "EMPLOYEE-SERVICE")
public interface EmployeeServiceProxy {
    @GetMapping("/employees/{employeeId}")
    public EmployeeEntity getEmployee(@PathVariable String employeeId);
}
