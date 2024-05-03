package com.book.controllers.manager;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("update")
public class UpdateController 
{
	@RequestMapping("/book")
	public String getBookList()
	{
		return "manager/manage/book-list?pn=1";
	}
	@RequestMapping("/category")
	public String getCatList()
	{	
		return "manager/category/category-list";
	}
	@RequestMapping("/publisher")
	public String getPublisherList()
	{
		return "manager/publisher/publisher-list";
	}
}
