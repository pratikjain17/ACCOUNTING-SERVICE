package com.pratik.www.salary.proxy;

import com.pratik.www.salary.model.EmployeeLeaveEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@FeignClient(name = "WORKHOUR-SERVICE")
public interface WorkHourServiceProxy {

    @GetMapping("employeeleave/find/{employeeId}/{yearMonth}")
    public Optional<EmployeeLeaveEntity> getEmployeeLeaveByEmployeeIdAndYearMonth(@PathVariable String employeeId, @PathVariable Number yearMonth);

}
