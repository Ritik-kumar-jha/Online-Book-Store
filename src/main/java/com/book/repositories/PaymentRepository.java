package com.book.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.book.models.Payment;

public interface PaymentRepository extends JpaRepository<Payment,Integer>
{
	@Query("select distinct userid from Payment")
	List<String> findUserList();
	List<Payment> findByUserid(String userid);
	List<Payment> findByDate(String date);
	@Query("select p from Payment p where p.date between :fromdate and :todate")
	List<Payment> findByFromAndTo(@Param("fromdate") String fromdate,@Param("todate") String todate);
}
