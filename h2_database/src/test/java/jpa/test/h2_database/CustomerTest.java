package jpa.test.h2_database;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.jdbc.Sql;

import jpa.test.h2_database.entity.CustomerEntity;
import jpa.test.h2_database.repository.CustomerRepo;
import lombok.extern.slf4j.Slf4j;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Sql("/data.sql")
@Slf4j

public class CustomerTest {
    @Autowired
    public CustomerRepo repo;

    @Test
    public void findByNameContainingTest() {
        List<CustomerEntity> customer = repo.findByLastNameContaining("ton", Sort.by("firstName").ascending());
        customer.forEach(System.out::println);
        assertThat(customer.size()).isGreaterThan(0);

    }

    @Test
    public void findByNameTest() {
        List<CustomerEntity> customer = repo.findByFirstNameAndLastName("Yard", "Sexton");
        customer.forEach(System.out::println);
        assertThat(customer.size()).isGreaterThan(0);

    }

    @Test
    public void findJobTest() {
        List<CustomerEntity> customer = repo.findByJob("Pharmacist", Sort.by(Direction.DESC, "firstName"));
        customer.forEach(System.out::println);
        assertThat(customer.size()).isGreaterThan(1);

    }

}
