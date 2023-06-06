package nguyennguyenhoang.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nguyennguyenhoang.entity.Category;
import nguyennguyenhoang.repository.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;

	public List<Category> GetAll(){
		return categoryRepository.findAll();
	}
	
}
