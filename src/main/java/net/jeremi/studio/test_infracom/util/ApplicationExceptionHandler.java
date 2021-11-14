package net.jeremi.studio.test_infracom.util;

import net.jeremi.studio.test_infracom.exception.IdAlreadyUsedException;
import net.jeremi.studio.test_infracom.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {

        ErrorResponse response = new ErrorResponse();
        response.setTimestamp(new Date());
        response.setDetails(exception.getMessage());

        if (exception instanceof ResourceNotFoundException) {
            response.setError("Resource not found");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } else if (exception instanceof IdAlreadyUsedException) {
            response.setError("Identifier already used");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } else {
            response.setError("General Error , please report administrator");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
