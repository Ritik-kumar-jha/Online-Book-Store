package com.book.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.book.models.BookCategory;

public interface CategoryRepository extends JpaRepository<BookCategory,Integer>
{
}
