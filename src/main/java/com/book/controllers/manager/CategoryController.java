package com.book.controllers.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.book.models.BookCategory;
import com.book.repositories.CategoryRepository;

@Controller
@RequestMapping("manager/category")
public class CategoryController 
{
	@Autowired private CategoryRepository crepo;
	
	@RequestMapping("update")
	public String getCategoryList(Model model)
	{
		List<BookCategory> list=crepo.findAll();
		model.addAttribute("list",list);
		return "manager/category/category-list";
	}
	@RequestMapping("add")
	public String getCategory()
	{
		return "manager/category/add-category";
	}
	@RequestMapping("save")
	public String saveCategory(BookCategory cat)
	{
		System.out.println(cat.getCategory());
		crepo.save(cat);
		return "redirect:update";
	}
	@RequestMapping("details")
	public String getCategoryDetails(int cid,Model model)
	{
		BookCategory cat=crepo.findById(cid).orElse(null);
		model.addAttribute("cat",cat);
		return "manager/category/category-details";
	}
	@RequestMapping("remove")
	public String removeCategory(int cid)
	{
		crepo.deleteById(cid);
		return "redirect:update";
	}
	@RequestMapping("edit")
	public String getEdit(int cid,Model model)
	{
		BookCategory cat=crepo.findById(cid).orElse(null);
		model.addAttribute("cat",cat);
		return "manager/category/edit-category";
	}
	@RequestMapping("editsave")
	public String saveEdit(BookCategory cat)
	{
		crepo.save(cat);
		return "redirect:update";
	}
}
