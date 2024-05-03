package com.book.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.book.models.Book;

public interface BookRepository extends JpaRepository<Book,Long>
{
	@Transactional
	@Modifying
	@Query("update Book b set b.copies=b.copies-:qty where b.bid=:bid")
	void updateCopies(@Param("qty") int qty,@Param("bid") long bid);
	@Query("from Book b where b.copies=0")
	List<Book> getOutOfStock();
	List<Book> findByAuthor(String author);
	List<Book> findByTitle(String title);
	List<Book> findByCategory(String category);
	List<Book> findByPublisher(String publisher);
	List<Book> findByBid(long bid);
}
