package org.java.demo;

import java.time.LocalDate;
import java.util.Optional;

import org.java.demo.pojo.Book;
import org.java.demo.pojo.Borrowing;
import org.java.demo.serv.BookServ;
import org.java.demo.serv.BorrowingServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrudBookRelazioniApplication implements CommandLineRunner {

	@Autowired
	private BookServ bookServ;
	
	@Autowired
	private BorrowingServ borrowingServ;
	
	public static void main(String[] args) {
		SpringApplication.run(CrudBookRelazioniApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Book b1 = new Book("book 1", "author 1" , LocalDate.now());
		Book b2 = new Book("book 2", "author 2" , LocalDate.now());
		Book b3 = new Book("book 3", "author 3" , LocalDate.now());
		
		bookServ.save(b1);
		bookServ.save(b2);
		bookServ.save(b3);
		
		Borrowing br1 = new Borrowing("Guybrush", "Threepwood", b1);
		Borrowing br2 = new Borrowing("Guybrush", "Threepwood", b2);
		Borrowing br3 = new Borrowing("Guybrush", "Threepwood", b3);
		Borrowing br4 = new Borrowing("Mario", "Rossi", b1);
		Borrowing br5 = new Borrowing("Mario", "Rossi", b2);
		
		borrowingServ.save(br1);
		borrowingServ.save(br2);
		borrowingServ.save(br3);
		borrowingServ.save(br4);
		borrowingServ.save(br5);
		
		// ----------------------------------------------------------------
		
		Optional<Book> firstBookOpt = bookServ.findByIdWithBorrowing(1);
		Book firstBook = firstBookOpt.get();
		
		System.out.println(firstBook);
		System.out.println(firstBook.getBorrowings());
		
		Optional<Borrowing> firstBorrowingOpt = borrowingServ.findById(1);
		Borrowing firstBorrowing = firstBorrowingOpt.get();
		
		System.out.println(firstBorrowing);
		System.out.println(firstBorrowing.getBook());
	}
}
