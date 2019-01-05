package ua.logos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class AlreadyExsistsException extends RuntimeException {
	private static final long serialVersionUID = -7120961421465304338L;

	public AlreadyExsistsException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public AlreadyExsistsException(String arg0) {
		super(arg0);
	}
}
