package org.java.demo.repo;

import org.java.demo.pojo.Borrowing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowingRepo extends JpaRepository<Borrowing, Integer> {

}
