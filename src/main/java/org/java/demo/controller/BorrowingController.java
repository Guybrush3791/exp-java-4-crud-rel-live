package org.java.demo.controller;

import java.util.List;

import org.java.demo.pojo.Book;
import org.java.demo.pojo.Borrowing;
import org.java.demo.serv.BookServ;
import org.java.demo.serv.BorrowingServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/borrowings")
public class BorrowingController {

	@Autowired
	private BorrowingServ borrowingServ;
	
	@Autowired
	private BookServ bookServ;
	
	@GetMapping
	public String getBorrowingIndex(Model model) {
		
		List<Borrowing> borrowings = borrowingServ.findAll();
		model.addAttribute("borrowings", borrowings);
		
		return "borrowing-index";
	}
	@GetMapping("/create")
	public String borrowBookForm(Model model) {
		
		List<Book> books = bookServ.findAll();
		
		model.addAttribute("borrowing", new Borrowing());
		model.addAttribute("books", books);
		
		return "borrowing-create";
	}
	
	@PostMapping("/create")
	public String borrowBookStore(
			@ModelAttribute Borrowing borrowing
		) {
		
		borrowingServ.save(borrowing);
		
		return "redirect:/borrowings";
	}
	
	@GetMapping("/update/{id}")
	public String updateBorrowing(
			Model model,
			@PathVariable int id
		) {
		
		List<Book> books = bookServ.findAll();
		Borrowing borrowing = borrowingServ.findById(id).get();
		
		model.addAttribute("borrowing", borrowing);
		model.addAttribute("books", books);
		
		return "borrowing-update";
	}
}
