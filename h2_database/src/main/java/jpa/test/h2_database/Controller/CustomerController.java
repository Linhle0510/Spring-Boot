package jpa.test.h2_database.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jpa.test.h2_database.entity.CustomerEntity;
import jpa.test.h2_database.exception.RecordNotFoundException;
import jpa.test.h2_database.service.CustomerService;

@Controller
@RequestMapping("/")
public class CustomerController {
    @Autowired
    CustomerService service;

    @RequestMapping
    public String getAllCustomers(Model model) {

        List<CustomerEntity> list = service.getAllCustomers();
        model.addAttribute("customers", list);
        return "list-customers";
    }

    @RequestMapping(path = { "/edit", "/edit/{id}" })
    public String editCustomerById(Model model, @PathVariable("id") Optional<Long> id) throws RecordNotFoundException {
        System.out.println("editCustomerById" + id);
        if (id.isPresent()) {
            CustomerEntity entity = service.getCustomerById(id.get());
            model.addAttribute("customer", entity);
        } else {
            model.addAttribute("customer", new CustomerEntity());
        }

        return "add-edit-customer";
    }

    @RequestMapping(path = "/delete/{id}")
    public String deleteCustomerById(Model model, @PathVariable("id") Long id) throws RecordNotFoundException {

        System.out.println("deleteCustomerById" + id);

        service.deleteCustomerById(id);
        return "redirect:/";
    }

    @RequestMapping(path = "/createCustomer", method = RequestMethod.POST)
    public String createOrUpdateCustomer(CustomerEntity customer) {
        System.out.println("createOrUpdateCustomer ");

        service.createOrUpdateCustomer(customer);

        return "redirect:/";
    }
}