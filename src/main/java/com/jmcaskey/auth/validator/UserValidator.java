package com.jmcaskey.auth.validator;

import com.jmcaskey.auth.model.User;
import com.jmcaskey.auth.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
	@Autowired
	private UserService userService;

	@Override
	public boolean supports(Class<?> aClass) {
		return User.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors errors) {
		User user = (User) o;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
		if (user.getUsername().length() < 6 || user.getUsername().length() > 32) {
			errors.rejectValue("username", "Size.userForm.username");
		}
		if (userService.findByUsername(user.getUsername()) != null) {
			errors.rejectValue("username", "Duplicate.userForm.username");
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
		if (user.getPassword().length() < 5) {
			errors.rejectValue("password", "Size.userForm.password");
		} else {
			String password = user.getPassword();
			int len = password.length();
			int lower = 0;
			int upper = 0;
			int num = 0;
			int symbol = 0;
			for (int i = 0; i < len && (lower + upper + num + symbol) < 3; i++) {
				if (Character.isLetter(password.charAt(i))) {
					if (Character.isLowerCase(password.charAt(i))) {
						lower = 1;
					} else {
						upper = 1;
					}
				} else if (Character.isDigit(password.charAt(i))) {
					num = 1;
				} else if (Character.isDefined(password.charAt(i))) {
					symbol = 1;
				}
			}

			if ((lower + upper + num + symbol) < 3) {
				errors.rejectValue("password", "Complex.userForm.password");
			}
		}

		if (!user.getPasswordConfirm().equals(user.getPassword())) {
			errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
		}
	}
}
