package com.acelerati.management_service.infraestructure.exceptionhandler;
import com.acelerati.management_service.domain.exception.ProductNotFoundException;
import com.acelerati.management_service.infraestructure.exceptionhandler.response.ErrorDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleAllExceptions(Exception exception, WebRequest request){
        ErrorDetails response = new ErrorDetails(LocalDateTime.now(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    //Validate Array
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorDetails> handleConstraintViolationException(ConstraintViolationException exception, WebRequest request){
        String message = exception.getConstraintViolations().stream()
                .map(error -> error.getPropertyPath()+":"+error.getMessage())
                .collect(Collectors.joining(", "));
        ErrorDetails response = new ErrorDetails(LocalDateTime.now(), message, request.getDescription(false));
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleProductNotFoundException(ProductNotFoundException exception, WebRequest request){
        ErrorDetails response = new ErrorDetails(LocalDateTime.now(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {

        String message = exception.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(" "));
        ErrorDetails response = new ErrorDetails(LocalDateTime.now(), message, request.getDescription(false));
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}