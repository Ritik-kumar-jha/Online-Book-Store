package com.book.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.book.models.BookTransaction;

public interface TransactionRepository extends JpaRepository<BookTransaction,Integer>
{
	List<BookTransaction> findByUserid(String userid);
}
