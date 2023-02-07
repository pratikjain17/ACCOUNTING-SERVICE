package com.pratik.www.salary.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = EmployeeLeaveEntity.EmployeeLeaveBuilder.class)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeLeaveEntity {

    private String id;
    private String employeeId;
    private Number yearMonth;
    private Number count;

    public EmployeeLeaveEntity() {
    }

    public EmployeeLeaveEntity(EmployeeLeaveBuilder builder) {
        this.id = builder.id;
        this.employeeId = builder.employeeId;
        this.yearMonth = builder.yearMonth;
        this.count = builder.count;
    }

    public String getId() {
        return id;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public Number getYearMonth() {
        return yearMonth;
    }

    public Number getCount() {
        return count;
    }

    @Override
    public String toString() {
        return "EmployeeLeaveEntity{" +
                "id='" + id + '\'' +
                ", employeeId='" + employeeId + '\'' +
                ", yearMonth=" + yearMonth +
                ", count=" + count +
                '}';
    }

    @JsonPOJOBuilder(withPrefix = "set")
    public static class EmployeeLeaveBuilder {

        protected String id;
        protected String employeeId;
        protected Number yearMonth;
        protected Number count;

        public EmployeeLeaveBuilder setId(String id) {
            this.id = id;
            return this;
        }

        public EmployeeLeaveBuilder setEmployeeId(String employeeId) {
            this.employeeId = employeeId;
            return this;
        }

        public EmployeeLeaveBuilder setYearMonth(Number yearMonth) {
            this.yearMonth = yearMonth;
            return this;
        }

        public EmployeeLeaveBuilder setCount(Number count) {
            this.count = count;
            return this;
        }


        public EmployeeLeaveEntity build() {
            return new EmployeeLeaveEntity(this);
        }
    }
}