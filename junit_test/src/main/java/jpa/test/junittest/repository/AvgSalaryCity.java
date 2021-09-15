package jpa.test.junittest.repository;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AvgSalaryCity {
    private String city;
    private Double averageSalary;
}