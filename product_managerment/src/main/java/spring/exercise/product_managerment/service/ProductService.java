package spring.exercise.product_managerment.service;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import spring.exercise.product_managerment.exception.RecordNotFoundException;
import spring.exercise.product_managerment.model.Product;
import spring.exercise.product_managerment.repository.ProductRepo;


@Service
public class ProductService {
	@Autowired
	private ProductRepo repo;
	
	public Page<Product> listAll(int pageNum, String sortField, String sortDir) {
		
		Pageable pageable = PageRequest.of(pageNum - 1, 5, 
				sortDir.equals("asc") ? Sort.by(sortField).ascending()
									  : Sort.by(sortField).descending()
		);
		
		return repo.findAll(pageable);
	}
	
    public Product getProductById(Long id) throws RecordNotFoundException {
        System.out.println("getProductById");
        Optional<Product> product = repo.findById(id);
        if (product.isPresent()) {
            return product.get();
        }else{
            throw new RecordNotFoundException("No customer record exist for given id");
        }
    }
	public Product createOrUpdateProduct(Product entity) {
        System.out.println("createOrUpdateProduct");
        //create new entry
        if (entity.getId() == null) {
            entity = repo.save(entity);
            return entity;
        //update
        }else{

            Optional<Product> product = repo.findById(entity.getId());
            if (product.isPresent()) {
                
                Product newProduct = product.get();
                newProduct.setName(entity.getName());
                newProduct.setBrand(entity.getBrand());
                newProduct.setMadeIn(entity.getMadeIn());
                newProduct.setPrice(entity.getPrice());
                

                newProduct = repo.save(newProduct);
            }else{
                entity = repo.save(entity);
            }
        }
        return entity;
    }
    public void deleteProductById(Long id) throws RecordNotFoundException{
        System.out.println("deleteProductById");
        Optional<Product> product = repo.findById(id);
        if (product.isPresent()) {
            repo.deleteById(id);
        }else{
            throw new RecordNotFoundException("No customer exist for given id");
        }

    }
}