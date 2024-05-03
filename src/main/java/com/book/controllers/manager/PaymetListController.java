package com.book.controllers.manager;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.book.models.Payment;
import com.book.repositories.PaymentRepository;

@Controller
@RequestMapping("manager/payment")
public class PaymetListController 
{
	@Autowired private PaymentRepository repo;
	
	@RequestMapping("listoption")
	public String listChoice(Model model) 
	{
		List<String> userlist=repo.findUserList();
		model.addAttribute("list", userlist);
		return "manager/payment/payment-list-choice";
	}
	@RequestMapping("fulllist")
	public String fullList(Model model) 
	{
		List<Payment> list=repo.findAll();
		model.addAttribute("list",list);
		model.addAttribute("msg","Full payment list");
		return "manager/payment/payment-list";
	}
	@RequestMapping("userwise")
	public String userWiseList(Model model,String userid) 
	{
		List<Payment> list=repo.findByUserid(userid);
		model.addAttribute("list",list);
		model.addAttribute("msg","Payment list of user "+userid);
		return "manager/payment/payment-list";
	}
	@RequestMapping("datewise")
	public String dateWiseList(Model model) 
	{
		model.addAttribute("dt",LocalDate.now().toString());
		return "manager/payment/datewise-choice";
	}
	@RequestMapping("currentdate")
	public String currentDateWiseList(Model model) 
	{
		String date=LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		List<Payment> list=repo.findByDate(date);
		model.addAttribute("list",list);
		model.addAttribute("msg","Payment list of today");
		return "manager/payment/payment-list";
	}
	@RequestMapping("selecteddate")
	public String selectedDateWiseList(Model model,String date) 
	{
		String dt=LocalDate.parse(date).format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		List<Payment> list=repo.findByDate(dt);
		model.addAttribute("list",list);
		model.addAttribute("msg","Payment list of date "+dt);
		return "manager/payment/payment-list";
	}
	@RequestMapping("fromandto")
	public String fronAndToDateWiseList(Model model,String fromdate,String todate) 
	{
		fromdate=LocalDate.parse(fromdate).format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		todate=LocalDate.parse(todate).format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		List<Payment> list=repo.findByFromAndTo(fromdate,todate);
		model.addAttribute("list",list);
		model.addAttribute("msg","Payment list between "+fromdate+" and "+todate);
		return "manager/payment/payment-list";
	}
}
