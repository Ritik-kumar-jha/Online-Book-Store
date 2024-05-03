package com.book.controllers.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.book.models.BookPublisher;
import com.book.repositories.PublisherRepository;

@Controller
@RequestMapping("manager/publisher")
public class PublisherController 
{
	@Autowired private PublisherRepository prepo;
	@RequestMapping("update")
	public String getCategoryList(Model model)
	{
		List<BookPublisher> list=prepo.findAll();
		model.addAttribute("list",list);
		return "manager/publisher/publisher-list";
	}
	@RequestMapping("add")
	public String getPublisher()
	{
		return "manager/publisher/add-publisher";
	}
	@RequestMapping("save")
	public String savePublisher(BookPublisher pub)
	{
		prepo.save(pub);
		return "redirect:update";
	}
	@RequestMapping("details")
	public String getCategoryDetails(int pid,Model model)
	{
		BookPublisher pub=prepo.findById(pid).orElse(null);
		model.addAttribute("pub",pub);
		return "manager/publisher/publisher-details";
	}
	@RequestMapping("remove")
	public String removeCategory(int pid)
	{
		prepo.deleteById(pid);
		return "redirect:update";
	}
	@RequestMapping("edit")
	public String getEdit(int pid,Model model)
	{
		BookPublisher pub=prepo.findById(pid).orElse(null);
		model.addAttribute("pub",pub);
		return "manager/publisher/edit-publisher";
	}
	@RequestMapping("editsave")
	public String saveEdit(BookPublisher pub)
	{
		prepo.save(pub);
		return "redirect:update";
	}
}
