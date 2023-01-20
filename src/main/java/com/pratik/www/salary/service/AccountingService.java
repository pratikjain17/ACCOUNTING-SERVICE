package com.pratik.www.salary.service;

import com.pratik.www.salary.db.entity.SalaryEntity;
import com.pratik.www.salary.db.repository.SalaryEntityRepository;
import com.pratik.www.salary.proxy.EmployeeServiceProxy;
import com.pratik.www.salary.proxy.WorkHourServiceProxy;
import com.pratik.www.salary.model.WorkHourRequest;
import com.pratik.www.salary.model.EmployeeLeave;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AccountingService {
    @Autowired
    private EmployeeServiceProxy employeeService;

    @Autowired
    private WorkHourServiceProxy workHourService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private SalaryEntityRepository salaryEntityRepository;

    public SalaryEntity calculateSalary(String employeeId, Number yearMonth) {
        SalaryEntity salary;
        Number amount = null;
        try {
            Number baseSalary = employeeService.getEmployee(employeeId).getBaseSalary();
//            EmployeeLeave leave = restTemplate
//                    .getForObject("http://localhost:8085/employeeleave/find/{employeeId}/{yearMonth}",EmployeeLeave.class, employeeId, yearMonth);
            EmployeeLeave leave =
                     workHourService
                            .getEmployeeLeaveByEmployeeIdAndYearMonth(employeeId,yearMonth)
                            .get();
            if(leave == null){
                amount = baseSalary;
            }else {
                Number leaveCount = leave.getCount();
                Number daysInMonth = calculateDaysInMonth(yearMonth);
                amount = (baseSalary.intValue()) * (daysInMonth.intValue() - leaveCount.intValue()) / daysInMonth.intValue();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        salary = new SalaryEntity.SalaryEntityBuilder()
                .setEmployeeId(employeeId)
                .setYearMonth(yearMonth)
                .setAmount(amount)
                .build();

//        salaryEntityRepository.save(salary);

        return salary;
    }

    private Number calculateDaysInMonth(Number yearMonth) {
        int year = Integer.parseInt(("" + yearMonth.intValue()).substring(0, 4));
        int month = yearMonth.intValue() % 100;
        return getDays(year,month);
    }

    private Number getDays(int year, int month){
        if (month == 2) {
            if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)
                return 29;
            return 28;
        }
        if (month == 4 || month == 6 || month == 9 || month == 11)
            return 30;
        return 31;
    }
}
