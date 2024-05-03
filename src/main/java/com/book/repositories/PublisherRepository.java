package com.book.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.book.models.BookPublisher;

public interface PublisherRepository extends JpaRepository<BookPublisher,Integer>
{
}
