package com.book.controllers.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.book.models.Book;
import com.book.models.BookTransaction;
import com.book.models.Cart;
import com.book.models.CreditCard;
import com.book.repositories.CartRepository;
import com.book.services.BookService;
import com.book.services.CartService;

@Controller
@RequestMapping("cart")
public class CartController
{
	@Autowired private CartService cservice;
	@Autowired private BookService bservice;
	@Autowired private CartRepository crepo;
	
	@RequestMapping("add")
	public String addToCart(long bid,HttpSession ses,Model model) 
	{
		String uid=(String)ses.getAttribute("userid");
		Cart ct=crepo.findByBookid(uid,bid);
		if(ct!=null)
		{
			return "/user/cart/not-save";
		}
		Book book=bservice.getBook(bid);
		Cart cart=new Cart(uid,bid,book.getTitle(),book.getPrice());
		cservice.addBook(cart);
		return "/user/cart/save";
	}
	@RequestMapping("cart-items")
	public String cartItems(Model model,HttpSession ses,HttpServletRequest req) 
	{
		List<Cart> list=cservice.getCartItems((String)ses.getAttribute("userid"));
		model.addAttribute("list",list);
		return "/user/cart/cart-items";
	}
	@RequestMapping("remove")
	public String removeFromCart(int cartid,HttpSession ses) 
	{
		crepo.deleteById(cartid);
		return "redirect:/cart/cart-items";
	}
	@RequestMapping("checked")
	public String orderAllBooks(HttpServletRequest req,String bt,Model model,HttpSession ses) 
	{
		String [] ids=req.getParameterValues("cb");
		if(bt.equals("oc"))
		{
			List<Book> list=new ArrayList<Book>();
			for(String id:ids) 
			{
				Book book=bservice.getBook(Long.parseLong(id));
				list.add(book);
			}
			ses.setAttribute("blist",list);
			return "redirect:/purchase/buybooks";
		}
		return removeChecked(ids,ses);
	}
	private String removeChecked(String [] ids,HttpSession ses)
	{
		for(String id:ids)
			crepo.deleteByBookid((String)ses.getAttribute("userid"),(Long.parseLong(id)));
		return "redirect:/cart/cart-items";
	}
}
