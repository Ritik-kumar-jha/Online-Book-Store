package com.book.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.book.models.CreditCard;

public interface CreditCardRepository extends JpaRepository<CreditCard,String>
{
	List<CreditCard> findAllByUserid(String userid);
}
