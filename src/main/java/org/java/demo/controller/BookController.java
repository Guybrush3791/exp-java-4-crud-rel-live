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
@RequestMapping("/books")
public class BookController {

	@Autowired
	private BookServ bookServ;
	
	@Autowired
	private BorrowingServ borrowingServ;
	
	@GetMapping
	public String getBookIndex(Model model) {
		
		List<Book> books = bookServ.findAll();
		model.addAttribute("books", books);
		
		return "book-index";
	}
	
	@GetMapping("/borrow/{id}")
	public String borrowBookForm(
			Model model,
			@PathVariable int id
		) {
		
		Book book = bookServ.findById(id).get();
		Borrowing borrowing = new Borrowing();
		borrowing.setBook(book);
		
		model.addAttribute("borrowing", borrowing);
		
		return "book-borrow";
	}
	@PostMapping("/borrow/create")
	public String borrowBookStore(
			@ModelAttribute Borrowing borrowing
		) {
		
		System.err.println(borrowing);
		borrowingServ.save(borrowing);		
		
		return "redirect:/borrowings";
	}
}
