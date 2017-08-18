package org.kuleba.webservices.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such regex.")
public class BadPatternSyntaxException extends RuntimeException{

}
