package jpa.test.h2_database.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jpa.test.h2_database.entity.CustomerEntity;

@Repository
public interface CustomerRepo extends JpaRepository<CustomerEntity, Long> {

   
    List<CustomerEntity> findByFirstNameAndLastName( String firstName,String lastName);

    List<CustomerEntity> findByLastNameContaining(String lastName, Sort sort);
    
    List<CustomerEntity> findByJob(String job, Sort sort);
}

