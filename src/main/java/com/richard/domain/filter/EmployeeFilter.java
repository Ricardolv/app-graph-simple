package com.richard.domain.filter;

public class EmployeeFilter {

    private FilterField salary;
    private FilterField age;
    private FilterField position;

    public EmployeeFilter() {}

    public EmployeeFilter(FilterField salary, FilterField age, FilterField position) {
        this.salary = salary;
        this.age = age;
        this.position = position;
    }

    public FilterField getSalary() {
        return salary;
    }

    public void setSalary(FilterField salary) {
        this.salary = salary;
    }

    public FilterField getAge() {
        return age;
    }

    public void setAge(FilterField age) {
        this.age = age;
    }

    public FilterField getPosition() {
        return position;
    }

    public void setPosition(FilterField position) {
        this.position = position;
    }
}
