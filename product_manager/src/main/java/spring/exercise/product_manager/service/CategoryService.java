package spring.exercise.product_manager.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.exercise.product_manager.exception.RecordNotFoundException;
import spring.exercise.product_manager.model.Category;
import spring.exercise.product_manager.repository.CategoryRepo;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;

    public List<Category> getAllCategory() {
        List<Category> categoryList = categoryRepo.findAll();
        if (categoryList.size() > 0) {
            return categoryList;
        } else {
            return new ArrayList<Category>();
        }

    }

    public Category getCategoryById(Long id) throws RecordNotFoundException {
        Optional<Category> category = categoryRepo.findById(id);
        if (category.isPresent()) {
            return category.get();
        } else {
            throw new RecordNotFoundException("No Category record exist for given id");

        }

    }

    public Category createOrUpdateCategory(Category category) {

        // create new category

        if (category.getId() == null) {
            category = categoryRepo.save(category);
            return category;
        } else {

            Optional<Category> presentCategory = categoryRepo.findById(category.getId());
            if (presentCategory.isPresent()) {
                Category newCategory = presentCategory.get();
                newCategory.setName(category.getName());
                newCategory = categoryRepo.save(newCategory);
            } else {
                category = categoryRepo.save(category);
            }
        }
        return category;
    }

    public void deleteCategoryById(Long id) throws RecordNotFoundException {

        Optional<Category> category = categoryRepo.findById(id);
        if (category.isPresent()) {
            categoryRepo.deleteById(id);
        } else {
            throw new RecordNotFoundException("No category exist for given id");
        }

    }

}
