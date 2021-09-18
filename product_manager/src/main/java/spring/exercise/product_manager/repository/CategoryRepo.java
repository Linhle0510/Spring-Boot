package spring.exercise.product_manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import spring.exercise.product_manager.model.Category;

@Repository

public interface CategoryRepo extends JpaRepository<Category, Long> {

}