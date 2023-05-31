package org.java.demo.serv;

import java.util.List;
import java.util.Optional;

import org.java.demo.pojo.Borrowing;
import org.java.demo.repo.BorrowingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BorrowingServ {

	@Autowired
	private BorrowingRepo borrowingRepo;
	
	public List<Borrowing> findAll() {
		
		return borrowingRepo.findAll();
	}
	public Optional<Borrowing> findById(int id) {
		
		return borrowingRepo.findById(id);
	}
	public Borrowing save(Borrowing borrowing) {
		
		return borrowingRepo.save(borrowing);
	}
}
