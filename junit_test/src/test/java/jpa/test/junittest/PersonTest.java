package jpa.test.junittest;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.test.context.jdbc.Sql;

import jpa.test.junittest.repository.AvgSalaryCity;
import jpa.test.junittest.repository.AvgSalaryJob;
import jpa.test.junittest.repository.CityJobCount;
import jpa.test.junittest.repository.CountJobCity;
import jpa.test.junittest.repository.JobCount;
import jpa.test.junittest.repository.PersonRepo;
import lombok.extern.slf4j.Slf4j;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Sql({ "/people.sql" })
@Slf4j
public class PersonTest {
     @Autowired
    public PersonRepo repo;
    
    // Đếm số người theo từng công việc theo thứ tự giảm dần
    @Test
    public void countByJobTest() {
        List<JobCount> jobCounts = repo.getListOfJobCounts();
        jobCounts.forEach(System.out::println);
        assertThat(jobCounts.size()).isGreaterThan(1);
    }

    // Đếm top 5 công việc có nhiều người làm nhất
    @Test
    public void countByJobTop5() {
        Pageable topFive = PageRequest.of(0, 5);

        List<JobCount> jobCountTop5 = repo.getListOfJobCountTop5(topFive);
        jobCountTop5.forEach(System.out::println);
        assertThat(jobCountTop5.size()).isGreaterThan(2);
    }

    @Test
    public void countJobInCityTest() {
        List<CityJobCount> countJobCity = repo.countJobInCity();
        countJobCity.forEach(System.out::println);
        assertThat(countJobCity.size()).isGreaterThan(2);
    }

   @Test
    public void avgSalaryOfJobTest() {
        List<AvgSalaryJob> avgSalaryJobs = repo.getAvgSalaryOfJob();
        avgSalaryJobs.forEach(System.out::println);
        assertThat(avgSalaryJobs.size()).isGreaterThan(5);
    } 

    @Test
    public void avgSalaryOfCityTest(){
        List<AvgSalaryCity> avgSalaryOfCity = repo.getAvgSalaryOfCity();
        avgSalaryOfCity.forEach(System.out::println);
        assertThat(avgSalaryOfCity.size()).isGreaterThan(5);
        
    }
 
    @Test
    public void top5JobInCityTest(){
        Pageable top5Job = PageRequest.of(0, 5);
        List<CityJobCount>top5JobCities = repo.getTop5JobInCity("Nurse",top5Job);
        top5JobCities.forEach(System.out::println);
        assertThat(top5JobCities.size()).isGreaterThan(0);
        
    }  
 
 

    
}