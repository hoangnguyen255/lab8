package nguyennguyenhoang.comtroller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import nguyennguyenhoang.entity.Product;
import nguyennguyenhoang.services.CategoryService;
import nguyennguyenhoang.services.ProductService;

@RequestMapping("/products")
@Controller
public class ProductController {
	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryService categoryService;

	@GetMapping("")
	public String index(Model model) {
		model.addAttribute("listproduct", productService.GetAll());
		return "admin/products/index.html";
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable int id, Model model) {
		model.addAttribute("product", productService.get(id));
		model.addAttribute("listCategory", categoryService.GetAll());
		return "admin/products/edit";
	}

	@PostMapping("/edit")
	public String edit(@Valid Product editProduct, @RequestParam MultipartFile imageProduct, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			model.addAttribute("product", editProduct);
			model.addAttribute("listCategory", categoryService.GetAll());
			return "admin/products/edit";
		}
		if (imageProduct != null && imageProduct.getSize() > 0) {
			try {
				File saveFile = new ClassPathResource("static/images").getFile();
				Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + editProduct.getImage());
				Files.copy(imageProduct.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		productService.edit(editProduct);
		return "redirect:/admin/products";
	}
	
}