package org.java.demo.pojo;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String title;
	private String author;
	private LocalDate releaseDate;
	
	@OneToMany(mappedBy = "book")
	private List<Borrowing> borrowings;
	
	public Book() { }
	public Book(String title, String author, LocalDate releaseDate) {
		
		setTitle(title);
		setAuthor(author);
		setReleaseDate(releaseDate);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public LocalDate getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}
	public List<Borrowing> getBorrowings() {
		return borrowings;
	}
	public void setBorrowings(List<Borrowing> borrowings) {
		this.borrowings = borrowings;
	}
	
	@Override
	public String toString() {
			
		return "[" + getId() + "] " + getTitle() + " - " + getAuthor()
			+ " - " + getReleaseDate();
	}
}
