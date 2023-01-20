package com.pratik.www.salary.proxy;

import com.pratik.www.salary.model.WorkHourRequest;
import com.pratik.www.salary.model.EmployeeLeave;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@FeignClient(name = "WORKHOUR-SERVICE")
public interface WorkHourServiceProxy {

    @GetMapping("employeeleave/find/{employeeId}/{yearMonth}")
    public Optional<EmployeeLeave> getEmployeeLeaveByEmployeeIdAndYearMonth(@PathVariable String employeeId, @PathVariable Number yearMonth);

}
