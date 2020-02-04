package com.example.jackson.pojos;

public class Employee {
    private String nameEmp;
    private String emailEmp;

    public Employee() {
    }

    public Employee(String nameEmp, String emailEmp) {
        this.nameEmp = nameEmp;
        this.emailEmp = emailEmp;
    }

    public String getNameEmp() {
        return nameEmp;
    }

    public void setNameEmp(String nameEmp) {
        this.nameEmp = nameEmp;
    }

    public String getEmailEmp() {
        return emailEmp;
    }

    public void setEmailEmp(String emailEmp) {
        this.emailEmp = emailEmp;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "nameEmp='" + nameEmp + '\'' +
                ", emailEmp='" + emailEmp + '\'' +
                '}';
    }
}
