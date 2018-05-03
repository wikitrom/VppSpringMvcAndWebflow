package com.virtualpairprogrammers.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.virtualpairprogrammers.domain.Book;
import com.virtualpairprogrammers.services.BookService;

/**
 * @author User
 * 
 *         This is a FORM controller using the Spring MVC Form support.
 *
 */
@Controller
@RequestMapping("/addNewBook")     // request action decide witch method the dispatcher should call
public class CreateBookController {

	@Autowired
	private BookService bookService;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView show() {
		return new ModelAndView("/add-new-book.jsp", "book", new Book());
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView processForm(Book book) {

		bookService.registerNewBook(book);
		return new ModelAndView("/book-added.jsp", "title", book.getTitle());
	}

}