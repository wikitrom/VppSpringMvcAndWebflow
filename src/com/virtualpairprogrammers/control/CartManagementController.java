package com.virtualpairprogrammers.control;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.lf5.Log4JLogRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.virtualpairprogrammers.domain.Book;
import com.virtualpairprogrammers.domain.ShoppingCart;
import com.virtualpairprogrammers.services.BookService;

@Controller
public class CartManagementController {
	@Autowired
	private BookService bookService;

	@RequestMapping("/addToCart")
	public ModelAndView addToCart(@RequestParam("id") int id, HttpSession session) {
		Book requiredBook = bookService.getBookById(id);

		// BUT HOW DO WE ADD IT TO THE USER'S SESSION?
		// -- try 1: using session HttpSession parameter as input 
		ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
		if (cart == null) {
			cart = new ShoppingCart();
		}
		cart.addItem(requiredBook);
		session.setAttribute("cart", cart);
		return new ModelAndView("/bookAddedToCart.jsp", "title", requiredBook.getTitle());
	}

	@RequestMapping("/viewCart")
	public ModelAndView viewCart(HttpSession session) {

		// -- try 1: using session HttpSession parameter as input 
		ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
		if (cart == null) {
			System.out.println("UABTROM: SOMETHING WENT WRONG WITH THE SESSION");
		}
		
		List<Book> cartBooks =	cart.getAllItems();
		
		return new ModelAndView("/cartContents.jsp", "cart", cartBooks);
	}

	
	
}
