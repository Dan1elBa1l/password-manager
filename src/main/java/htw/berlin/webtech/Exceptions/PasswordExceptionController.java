package htw.berlin.webtech.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class PasswordExceptionController {

    @ExceptionHandler(PasswordNotFoundException.class)
    public ResponseEntity<Object> handlePasswordNotFoundException(PasswordNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
