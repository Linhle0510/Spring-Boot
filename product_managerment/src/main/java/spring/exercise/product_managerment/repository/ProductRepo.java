package spring.exercise.product_managerment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import spring.exercise.product_managerment.model.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long>{

}

    
