package com.virtualpairprogrammers.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.virtualpairprogrammers.domain.Book;
import com.virtualpairprogrammers.services.BookService;

@Controller
public class BookManagementController {

	@Autowired
	private BookService bookService;

	@RequestMapping("/viewAllBooks")
	public ModelAndView viewAllBooks() {
		List<Book> allBooks = bookService.getEntireCatalogue();
		return new ModelAndView("displayAllBooks", "allBooks", allBooks);
	}

	// -- alternative allBooks view - PDF (uses https://www.itextpdf.com)
	@RequestMapping("/viewAllBooksPdf")
	public ModelAndView viewAllBooksPDF() {
		List<Book> allBooks = bookService.getEntireCatalogue();
		return new ModelAndView("bookReportPdf", "allBooks", allBooks);
	}

	// -- alternative allBooks view - Excel (uses https://poi.apache.org)
	@RequestMapping("/viewAllBooksExcel")
	public ModelAndView viewAllBooksExcel() {
		List<Book> allBooks = bookService.getEntireCatalogue();
		return new ModelAndView("bookReportExcel", "allBooks", allBooks);
	}

	@RequestMapping("/findByAuthor")
	public ModelAndView findByAuthor(@RequestParam("AUTHOR") String author) {
		List<Book> foundBooks = bookService.getAllBooksByAuthor(author);
		return new ModelAndView("displayAllBooks", "allBooks", foundBooks);
	}

	// -- AJAX/JSON 'view'
	// purpose: handle AJAXcall to retrieve partial search results
	@RequestMapping("/looseSearch")
	@ResponseBody
	public List<Book> performLooseSearch(@RequestParam("CHARS") String chars) {

		// limit when to return data - better to let the caller hold back on its
		// requests to reduce network traffic
		if (chars.length() < 3) {
			return null;
		}
		return bookService.searchBooksByLooseMatch(chars);
	}

}
