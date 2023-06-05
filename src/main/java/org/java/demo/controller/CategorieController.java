package org.java.demo.controller;

import java.util.List;

import org.java.demo.pojo.Book;
import org.java.demo.pojo.Category;
import org.java.demo.serv.BookServ;
import org.java.demo.serv.CategoryServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/categories")
public class CategorieController {

	@Autowired
	private CategoryServ categoryServ;
	
	@Autowired
	private BookServ bookServ;
	
	@GetMapping
	public String getIndex(Model model) {
		
		List<Category> categories = categoryServ.findAll();
		model.addAttribute("categories", categories);
		
		return "category-index";
	}
	
	@GetMapping("/create")
	public String getCategoryForm(Model model) {
		
		List<Book> books = bookServ.findAll();
		
		model.addAttribute("category", new Category());
		model.addAttribute("books", books);
		
		return "category-create";
	}
	
	@PostMapping("/create")
	public String storeNewCategory(
			@ModelAttribute Category category
		) {
		
		categoryServ.save(category);
		
		for (Book b : category.getBooks()) {
			
			b.addCategory(category);
			bookServ.save(b);
		}
		
		return "redirect:/categories";
	}
	
	@GetMapping("/update/{id}")
	public String getCategoryUpdateForm(
			Model model,
			@PathVariable int id
		) {
		
		List<Book> books = bookServ.findAll();
		Category category = categoryServ.findById(id).get();
		
		model.addAttribute("books", books);
		model.addAttribute("category", category);
		
		return "category-update";
	}
	@PostMapping("/update/{id}")
	public String storeUpdatedCategory(
			@ModelAttribute Category category
		) {
		
		categoryServ.save(category);
		
		for (Book b : bookServ.findAll()) {
			
			b.removeCategory(category);
			bookServ.save(b);
		}
		for (Book b : category.getBooks()) {
			
			b.addCategory(category);
			bookServ.save(b);
		}
		
		return "redirect:/categories";
	}
}
