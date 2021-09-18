package spring.exercise.product_manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import spring.exercise.product_manager.model.Brand;

@Repository
public interface BrandRepo extends JpaRepository<Brand, Long> {

}