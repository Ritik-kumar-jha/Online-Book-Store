package com.book.controllers.user;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.book.models.*;
import com.book.repositories.*;

@Controller
@RequestMapping("payment/card")
public class PaymentController 
{
	@Autowired private CreditCardRepository repo;
	@Autowired private PaymentRepository prepo;
	@Autowired private TransactionRepository trepo;
	@Autowired private CartRepository crepo;
	@Autowired private BookRepository brepo;
	
	Random ra=new Random();
	@RequestMapping("validate")
	public String validateCard(CreditCard cardu,Model model,String total,String bookid)
	{
		int c=validateCard(cardu, model, total);
		if(c==1 || c==2)
			return "/user/payment/paynow";
		int num=ra.nextInt(5000)+5000;
		model.addAttribute("otpn",num);
		return "/user/payment/otp";
	}
	@RequestMapping("single-validate")
	public String validateSingleCard(CreditCard cardu,Model model,String total)
	{
		int c=validateCard(cardu, model, total);
		if(c==1 || c==2)
			return "/user/payment/paynowsingle";
		int num=ra.nextInt(5000)+5000;
		model.addAttribute("otpn",num);
		return "/user/payment/otpsingle";
	}
	@RequestMapping("success")
	public String cardPayement(String cardno,long total,HttpSession ses)
	{
		String userid=(String)ses.getAttribute("userid");
		String date=LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		prepo.save(new Payment(userid,cardno,total,date));
		List<Book> list=(List<Book>)ses.getAttribute("blist");
		String [] qty=(String [])ses.getAttribute("bwqty"); 
		for(int i=0;i<list.size();i++)
		{
			Book b=list.get(i);
			int q=Integer.parseInt(qty[i]);
			crepo.deleteByBookid(userid,b.getBid());
			BookTransaction bt=new BookTransaction(userid,b.getBid(),q,b.getPrice(),new Date());
			trepo.save(bt);	
			brepo.updateCopies(Integer.parseInt(qty[i]), b.getBid());
		}
		return "/user/purchase/buy";
	}
	@RequestMapping("single-success")
	public String cardSinglePayement(String cardno,long total,HttpSession ses)
	{
		String uid=(String)ses.getAttribute("userid");
		String date=LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		prepo.save(new Payment(uid,cardno,total,date));
		BookTransaction bt=(BookTransaction)ses.getAttribute("bt");
		trepo.save(bt);	
		brepo.updateCopies(bt.getQuantity(),bt.getBookid());
		Cart cart=crepo.findByBookid(uid,bt.getBookid());
		if(cart!=null)
			crepo.delete(cart);
		return "/user/purchase/buy";
	}
	@RequestMapping("paynow")
	public String payNow(String tobepaid,Model model,HttpServletRequest req,HttpSession ses)
	{
		String [] str=req.getParameterValues("qr");
		ses.setAttribute("bwqty",str);
		model.addAttribute("c",new CreditCard());
		model.addAttribute("paid",tobepaid);
		ses.setAttribute("bbid",req.getParameter("bid"));
		return "/user/payment/paynow";
	}
	
	@RequestMapping("paynowsingle")
	public String payNow(BookTransaction bt,String tobepaid,Model model,HttpServletRequest req,HttpSession ses)
	{
		String bid=req.getParameter("bookid");
		bt.setDate(new Date());
		bt.setUserid((String)ses.getAttribute("userid"));
		ses.setAttribute("bt",bt);
		model.addAttribute("bid",bid);
		model.addAttribute("paid",tobepaid);
		model.addAttribute("c", new CreditCard());
		return "/user/payment/paynowsingle";
	}
	private int validateCard(CreditCard cardu,Model model,String total)
	{
		int c=0;
		CreditCard cardd=repo.findById(cardu.getCard()).orElse(null);
		model.addAttribute("c",cardu);
		model.addAttribute("paid",total);
		model.addAttribute("cardno",cardu.getCard());
		if(cardd==null)
		{
			model.addAttribute("cardmsg", "Card number does not exist");
			c=1;
			return c;
		}
		if(!cardu.getHolder().equals(cardd.getHolder()))
		{
			c=2;
			model.addAttribute("holdermsg", "Card holder name is wrong");
		}
		if(cardu.getMonth()!=cardd.getMonth() || cardu.getYear()!=cardd.getYear())
		{
			c=2;
			model.addAttribute("expmsg", "Expiration date is wrong");
		}
		if(cardu.getCvv()!=cardd.getCvv())
		{
			c=2;
			model.addAttribute("cvvmsg", "CVV is wrong");
		}
		return c;
	}
}
