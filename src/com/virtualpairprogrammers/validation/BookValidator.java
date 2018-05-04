package com.virtualpairprogrammers.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.virtualpairprogrammers.domain.Book;

@Component
public class BookValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(Book.class);
	}

	@Override
	public void validate(Object obj, Errors error) {
		ValidationUtils.rejectIfEmptyOrWhitespace(error, "isbn", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(error, "title", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(error, "author", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(error, "price", "required");

		// -- note: any code can be called to verify input data and if not valid we can
		// reject the post. Example code below.
		// if (<condition>) {
		// error.rejectValue(arg0, arg1);
		// }
	}

}
