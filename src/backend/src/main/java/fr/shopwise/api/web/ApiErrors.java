package fr.shopwise.api.web;
import fr.shopwise.api.service.ResourceMissingException; import org.springframework.http.*;import org.springframework.web.bind.annotation.*;
@RestControllerAdvice
public class ApiErrors {
 @ExceptionHandler(ResourceMissingException.class) @ResponseStatus(HttpStatus.NOT_FOUND) public ErrorBody missing(Exception e){return new ErrorBody(e.getMessage());}
 @ExceptionHandler(IllegalArgumentException.class) @ResponseStatus(HttpStatus.BAD_REQUEST) public ErrorBody invalid(Exception e){return new ErrorBody(e.getMessage());}
 public record ErrorBody(String message){}
}
