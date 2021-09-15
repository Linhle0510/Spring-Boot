package jpa.test.junittest.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jpa.test.junittest.entity.Person;

@Repository
public interface PersonRepo extends JpaRepository<Person,Long> {

        @Query("SELECT new jpa.test.junittest.repository.JobCount(p.job,COUNT(*))"
                        + "FROM Person as p GROUP BY p.job order by COUNT(*) DESC ")
        List<JobCount> getListOfJobCounts();

        @Query("SELECT new jpa.test.junittest.repository.JobCount(p.job,COUNT(*))"
                        + "FROM Person as p GROUP BY p.job order by COUNT(*) DESC")
        List<JobCount> getListOfJobCountTop5(Pageable pageable);

        @Query("SELECT new jpa.test.junittest.repository.CityJobCount(p.city, p.job, COUNT(*))"
                        + "FROM Person as p GROUP BY p.city, p.job ORDER BY COUNT(*) DESC")
        List<CityJobCount> countJobInCity();

        @Query("SELECT new jpa.test.junittest.repository.AvgSalaryJob(p.job, AVG(p.salary))"
                        + "FROM Person as p GROUP BY p.job ORDER BY AVG(p.salary) DESC")
        List<AvgSalaryJob> getAvgSalaryOfJob();

        @Query("SELECT new jpa.test.junittest.repository.AvgSalaryCity(p.city, AVG(p.salary))"
                        + "FROM Person As p GROUP BY p.city ORDER BY AVG(p.salary) DESC")

        List<AvgSalaryCity> getAvgSalaryOfCity();

        @Query("SELECT new jpa.test.junittest.repository.CityJobCount(p.city, p.job, COUNT(p.job))"
                        + "FROM Person as p GROUP BY p.city, p.job ORDER BY COUNT(p.job) DESC")
        List<CityJobCount> getTop5JobInCity(String job, Pageable pageable); 
 
}
