package org.java.demo.serv;

import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.java.demo.pojo.Book;
import org.java.demo.repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class BookServ {

	@Autowired
	private BookRepo bookRepo;
	
	public List<Book> findAll() {
		
		return bookRepo.findAll();
	}
	public Optional<Book> findById(int id) {
		
		return bookRepo.findById(id);
	}
	@Transactional
	public Optional<Book> findByIdWithBorrowing(int id) {
		
		Optional<Book> bookOpt = bookRepo.findById(id);
		Hibernate.initialize(bookOpt.get().getBorrowings());
		
		return bookOpt;
	}
	public Book save(Book book) {
		
		return bookRepo.save(book);
	}
}
