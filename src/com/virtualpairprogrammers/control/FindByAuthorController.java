package com.virtualpairprogrammers.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.virtualpairprogrammers.domain.Book;
import com.virtualpairprogrammers.services.BookService;

@Controller
public class FindByAuthorController {

	@Autowired
	private BookService bookService;

	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

	@RequestMapping("/findByAuthor")
	public ModelAndView findByAuthor(@RequestParam("AUTHOR") String author) {
		List<Book> foundBooks = bookService.getAllBooksByAuthor(author);
		return new ModelAndView("/displayAllBooks.jsp", "allBooks", foundBooks);
	}
}
