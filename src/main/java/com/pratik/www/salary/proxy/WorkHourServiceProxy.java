package com.pratik.www.salary.proxy;

import com.pratik.www.salary.model.WorkHourRequest;
import com.pratik.www.salary.model.EmployeeLeave;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@FeignClient(name = "WORKHOUR-SERVICE", url = "http://localhost:8085/employeeleave/find")
public interface WorkHourServiceProxy {
    @PostMapping
    @ResponseStatus(HttpStatus.FOUND)
    public Optional<EmployeeLeave> getEmployeeLeave(@RequestBody WorkHourRequest request);

}
