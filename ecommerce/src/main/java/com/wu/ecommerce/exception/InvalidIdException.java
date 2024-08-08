package com.wu.ecommerce.exception;

public class InvalidIdException extends Exception {
	public InvalidIdException(String msg) {
		super(msg);
	}
	public String toString() {
		return super.getMessage();
	}
}
