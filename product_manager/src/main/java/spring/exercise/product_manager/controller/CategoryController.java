package spring.exercise.product_manager.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.exercise.product_manager.exception.RecordNotFoundException;
import spring.exercise.product_manager.model.Category;
import spring.exercise.product_manager.service.CategoryService;

@Controller

public class CategoryController {

    @Autowired
    CategoryService service;

    @RequestMapping("/category")
    public String getAllCategories(Model model) {
        List<Category> listCategories = service.getAllCategory();
        model.addAttribute("listCategories", listCategories);
        return "list-categories";

    }

    @RequestMapping(path = { "/category/edit", "category/edit/{id}" })
    public String editCategoryById(Model model, @PathVariable("id") Optional<Long> id) throws RecordNotFoundException {

        if (id.isPresent()) {
            Category category = service.getCategoryById(id.get());
            model.addAttribute("category", category);
        } else {
            model.addAttribute("category", new Category());
        }

        return "category-form";
    }

    @RequestMapping(path = "/category/delete/{id}")
    public String deleteCategoryById(Model model, @PathVariable("id") Long id) throws RecordNotFoundException {

        service.deleteCategoryById(id);
        return "redirect:/category";
    }

    @RequestMapping(path = "/createCategory", method = { RequestMethod.GET, RequestMethod.POST })
    public String createOrUpdateCategory(Category category) {
        System.out.println("createOrUpdateCategory");

        service.createOrUpdateCategory(category);

        return "redirect:/category";
    }
}