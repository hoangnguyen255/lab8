package nguyennguyenhoang.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nguyennguyenhoang.entity.Product;
import nguyennguyenhoang.repository.ProductRepository;
@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;
	private List<Product> listProduct = new ArrayList<>();

	public List<Product> GetAll() {
		return (List<Product>) productRepository.findAll();
	}
	public void add(Product newProduct) {
		listProduct.add(newProduct);
	}
	
	public Product get(int id) {
		return listProduct.stream().filter(p->p.getId()==id).findFirst().orElse(null);
	}
	public void edit(Product editProduct) {
		Product find = listProduct.get(editProduct.getId());
		if(find != null) {
			find.setName(editProduct.getName());
			find.setImage(editProduct.getImage());
			find.setPrice(editProduct.getPrice());
		}
	}
	 public void delete(int id){
	        productRepository.deleteById(id);
	    }
}
