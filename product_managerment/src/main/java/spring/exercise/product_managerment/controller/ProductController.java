package spring.exercise.product_managerment.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import spring.exercise.product_managerment.exception.RecordNotFoundException;
import spring.exercise.product_managerment.model.Product;
import spring.exercise.product_managerment.service.ProductService;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ProductController {
    @Autowired
    private ProductService service;

    @RequestMapping("/")
    public String viewHomePage(Model model) {
        return viewPage(model, 1, "id", "asc");
    }

    @RequestMapping("/page/{pageNum}")
    public String viewPage(Model model, @PathVariable(name = "pageNum") int pageNum,
            @Param("sortField") String sortField, @Param("sortDir") String sortDir) {

        Page<Product> page = service.listAll(pageNum, sortField, sortDir);

        List<Product> listProducts = page.getContent();

        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listProducts", listProducts);

        return "product-list";
    }

    @RequestMapping(path = { "/edit", "/edit/{id}" })
    public String editCustomerById(Model model, @PathVariable("id") Optional<Long> id) throws RecordNotFoundException {
        System.out.println("editProductById" + id);
        if (id.isPresent()) {
            Product entity = service.getProductById(id.get());
            model.addAttribute("product", entity);
        } else {
            model.addAttribute("product", new Product());
        }

        return "add-edit-product";
    }

    @RequestMapping(path = "/delete/{id}")
    public String deleteProductById(Model model, @PathVariable("id") Long id) throws RecordNotFoundException {

        System.out.println("deleteProductById" + id);

        service.deleteProductById(id);
        return "redirect:/";
    }

    @RequestMapping(path = "/createProduct", method = RequestMethod.POST)
    public String createOrUpdateProduct(Product product) {
        System.out.println("createOrUpdateCustomer ");

        service.createOrUpdateProduct(product);

        return "redirect:/";
    }

}
