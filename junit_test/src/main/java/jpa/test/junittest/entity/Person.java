package jpa.test.junittest.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//id,fullName,job,gender,city,salary
@Entity
@Table(name = "people")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "job")
    private String job;

    @Column(name = "gender")
    private String gender;

    @Column (name = "city")
    private String city;

    @Column(name = "salary")
    private Long salary;

}