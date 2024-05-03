package com.book.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.book.models.Cart;

public interface CartRepository extends JpaRepository<Cart,Integer> 
{
	List<Cart> findByUserid(String userid);
	@Transactional
	@Modifying
	@Query("delete from Cart c where c.userid=:uid and c.bookid=:bid")
	void deleteByBookid(String uid,long bid);
	@Query("from Cart c where c.userid=:uid and c.bookid=:bid")
	Cart findByBookid(String uid,long bid);
}
