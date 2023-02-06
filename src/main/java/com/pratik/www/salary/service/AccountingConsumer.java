package com.pratik.www.salary.service;

import com.pratik.www.salary.db.entity.SalaryEntity;
import com.pratik.www.salary.model.Employee;
import com.pratik.www.salary.model.EmployeeLeave;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.common.protocol.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;



@Service
public class AccountingConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountingConsumer.class);

//    @Autowired
//    Environment env;
//
//    private String topic1 = env.getProperty("spring.kafka.topic1");
//    private String topic2 = env.getProperty("spring.kafka.topic2");

    @KafkaListener(topics={"${spring.kafka.topic1}"} ,
            groupId = "${spring.kafka.consumer.group-id}")
    public void consumeAndCalculate(ConsumerRecord<String, Employee> employeeConsumerRecord){
        LOGGER.info(String.format("employee_topics Event consumed:  => %s", employeeConsumerRecord.value().toString()));
//        LOGGER.info(String.format("workhour_topics Event consumed:  => %s", employeeLeave.getPayload().toString()));
//        SalaryEntity salary;
//        Number amount = null;
//
//        Number baseSalary = employee.getPayload().getBaseSalary();
//        if(employeeLeave == null){
//            amount = baseSalary;
//        }else {
//            Number leaveCount = employeeLeave.getPayload().getCount();
//            Number daysInMonth = calculateDaysInMonth(employeeLeave.getPayload().getYearMonth());
//            amount = (baseSalary.intValue()) * (daysInMonth.intValue() - leaveCount.intValue()) / daysInMonth.intValue();
//        }
//
//        salary = new SalaryEntity.SalaryEntityBuilder()
//                .setEmployeeId(employee.getPayload().getId())
//                .setYearMonth(employeeLeave.getPayload().getYearMonth())
//                .setAmount(amount)
//                .build();
//
//        LOGGER.info(String.format("Salary calculated: => %s", salary.toString()));
//        return salary;
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
