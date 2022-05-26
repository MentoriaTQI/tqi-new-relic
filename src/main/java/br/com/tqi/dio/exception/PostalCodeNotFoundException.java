package br.com.tqi.dio.exception;

public class PostalCodeNotFoundException extends RuntimeException {

    public PostalCodeNotFoundException(String postalCode) {
        super("postal code not found=" + postalCode);
    }
}
