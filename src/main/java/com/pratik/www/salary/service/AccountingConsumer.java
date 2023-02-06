package com.pratik.www.salary.service;


import com.pratik.www.salary.db.entity.SalaryEntity;
import com.pratik.www.salary.model.EmployeeEntity;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;



@Service
public class AccountingConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountingConsumer.class);

    @KafkaListener(topics={"${spring.kafka.topic1}","${spring.kafka.topic2}"} ,
            groupId = "${spring.kafka.consumer.group-id}", containerFactory = "EmployeeEntityListener")
    public void consumeAndCalculate(EmployeeEntity employee){
        LOGGER.info(String.format("employee Event consumed:  => %s",employee));
//        LOGGER.info(String.format("workhour_topics Event consumed:  => %s",));
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
