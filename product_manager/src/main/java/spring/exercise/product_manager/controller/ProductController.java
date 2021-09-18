package spring.exercise.product_manager.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import spring.exercise.product_manager.model.Category;
import spring.exercise.product_manager.model.Product;
import spring.exercise.product_manager.repository.CategoryRepo;
import spring.exercise.product_manager.repository.ProductRepo;

@Controller
public class ProductController {
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private CategoryRepo categoryRepo;

    @GetMapping("/product")
    private String listProducts(Model model) {
        List<Product> listProducts = productRepo.findAll();
        model.addAttribute("listProducts", listProducts);
        return "list-product";

    }

    @GetMapping("/product/create")
    public String createProduct(Model model) {
        List<Category> listCategories = categoryRepo.findAll();
        model.addAttribute("product", new Product());
        model.addAttribute("listCategories", listCategories);
        return "product-form";
    }

    @PostMapping("/product/save")
    public String savProduct(Product product, HttpServletRequest request) {
        String[] detailId = request.getParameterValues("detailId");
        String[] detailName = request.getParameterValues("detailName");
        String[] detailValue = request.getParameterValues("detailValue");
        for (int i = 0; i < detailName.length; i++) {
            if (detailId != null && detailId.length > 0) {
                product.editDetail(Long.valueOf(detailId[i]), detailName[i], detailValue[i]);

            } else {

                product.addDetail(detailName[i], detailValue[i]);
            }
        }

        productRepo.save(product);
        return "redirect:/product";
    }

    @GetMapping("/product/edit/{id}")
    public String editProduct(@PathVariable("id") Long id, Model model) {
        List<Category> listCategories = categoryRepo.findAll();
        Product product = productRepo.findById(id).get();
        model.addAttribute("product", product);
        model.addAttribute("listCategories", listCategories);
        return "product-form";
    }

    @GetMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id, Model model) {
        Product product = productRepo.findById(id).get();
        productRepo.delete(product);
        return "redirect:/product";

    }
}