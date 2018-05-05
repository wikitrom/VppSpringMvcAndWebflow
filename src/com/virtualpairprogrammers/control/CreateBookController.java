package com.virtualpairprogrammers.control;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
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
@RequestMapping("/addNewBook") // request action decide witch method the dispatcher should call
public class CreateBookController {

	@Autowired
	private BookService bookService;

	@RequestMapping(method = RequestMethod.GET)   // === @GetMapping
	public ModelAndView show() {
		return new ModelAndView("add-new-book", "book", new Book());
	}

	@RequestMapping(method = RequestMethod.POST) // === @PostMapping
	public ModelAndView processForm(@Valid Book newBook, Errors error) {

		if (error.hasErrors()) {
			return new ModelAndView("add-new-book", "book", newBook);
		}
		bookService.registerNewBook(newBook);
		return new ModelAndView("book-added", "title", newBook.getTitle());
	}

}
