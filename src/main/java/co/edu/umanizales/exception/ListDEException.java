package co.edu.umanizales.exception;

import lombok.Data;

@Data
public class ListDEException extends RuntimeException {
    String code;

    public ListDEException(String code, String message) {
        super(message);
        this.code = code;
    }
}
