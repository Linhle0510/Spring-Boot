package spring.exercise.product_manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import spring.exercise.product_manager.model.Brand;
import spring.exercise.product_manager.model.Category;
import spring.exercise.product_manager.repository.BrandRepo;
import spring.exercise.product_manager.repository.CategoryRepo;

@Controller
public class BrandController {

    @Autowired
    private BrandRepo brandRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @GetMapping("/brand/create")
    public String createBrand(Model model) {
        List<Category> listCategories = categoryRepo.findAll();
        model.addAttribute("brand", new Brand());
        model.addAttribute("listCategories", listCategories);
        return "brand-form";
    }

    @PostMapping("/brand/save")
    public String saveBrand(Brand brand) {
        brandRepo.save(brand);
        return "redirect:/brand";
    }

    @GetMapping("/brand")
    public String listBrands(Model model) {
        List<Brand> listBrands = brandRepo.findAll();
        model.addAttribute("listBrands", listBrands);
        return "list-brand";
    }

    @GetMapping("/brand/edit/{id}")
    public String editBrand(@PathVariable("id") Long id, Model model) {
        List<Category> listCategories = categoryRepo.findAll();
        Brand brand = brandRepo.findById(id).get();
        model.addAttribute("brand", brand);
        model.addAttribute("listCategories", listCategories);
        return "brand-form";
    }

    @GetMapping("/brand/delete/{id}")
    public String deleteBrand(@PathVariable("id") Long id, Model model) {
        Brand brand = brandRepo.findById(id).get();
        brandRepo.delete(brand);
        return "redirect:/brand";
    }

}
