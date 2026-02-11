package com.oauth.rest.exception;

public class UserPasswordException extends RuntimeException {

	private static final long serialVersionUID = -7978601526802035152L;

	public UserPasswordException() {
		super("Las contraseñas no coinciden");
	}
}
