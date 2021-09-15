package jpa.test.h2_database.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jpa.test.h2_database.entity.CustomerEntity;
import jpa.test.h2_database.exception.RecordNotFoundException;
import jpa.test.h2_database.repository.CustomerRepo;

@Service
public class CustomerService {
    @Autowired
    CustomerRepo customerRepo;

    public List<CustomerEntity> getAllCustomers() {

        System.out.println("getAllCustomers");
        List<CustomerEntity> result = (List<CustomerEntity>) customerRepo.findAll();
        if (result.size() > 0) {
            return result;
        } else {
            return new ArrayList<CustomerEntity>();
        }
    }

    public CustomerEntity getCustomerById(Long id) throws RecordNotFoundException {
        System.out.println("getCustomerById");
        Optional<CustomerEntity> customer = customerRepo.findById(id);
        if (customer.isPresent()) {
            return customer.get();
        }else{
            throw new RecordNotFoundException("No customer record exist for given id");
        }
    }

    public CustomerEntity createOrUpdateCustomer(CustomerEntity entity) {
        System.out.println("createOrUpdateCustomer");
        //create new entry
        if (entity.getId() == null) {
            entity = customerRepo.save(entity);
            return entity;
        //update
        }else{

            Optional<CustomerEntity> customer = customerRepo.findById(entity.getId());
            if (customer.isPresent()) {
                
                CustomerEntity newEntity = customer.get();
                newEntity.setFirstName(entity.getFirstName());
                newEntity.setLastName(entity.getLastName());
                newEntity.setEmail(entity.getEmail());
                newEntity.setMobile(entity.getMobile());
                newEntity.setJob(entity.getJob());

                newEntity = customerRepo.save(newEntity);
            }else{
                entity = customerRepo.save(entity);
            }
        }
        return entity;
    }
    public void deleteCustomerById(Long id) throws RecordNotFoundException{
        System.out.println("deleteCustomerById");
        Optional<CustomerEntity> customer = customerRepo.findById(id);
        if (customer.isPresent()) {
            customerRepo.deleteById(id);
        }else{
            throw new RecordNotFoundException("No customer exist for given id");
        }

    }
}