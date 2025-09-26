package palvvz.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleNotFound(ResourceNotFoundException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("error", "Validation failed");
        body.put("details", ex.getBindingResult().getFieldErrors().stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage()).toList());
        return ResponseEntity.badRequest().body(body);
    }

//    Error creating bean with name 'handlerExceptionResolver
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<?> handleValidationErrors(MethodArgumentNotValidException ex) {
//        List<String> errors = ex.getBindingResult().getFieldErrors().stream()
//                .map(err -> err.getField() + ": " + err.getDefaultMessage())
//                .toList();
//
//        return ResponseEntity.badRequest().body(errors);
//    }
}