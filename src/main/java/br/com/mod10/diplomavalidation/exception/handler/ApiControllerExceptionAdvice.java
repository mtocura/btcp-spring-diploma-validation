package br.com.mod10.diplomavalidation.exception.handler;

import br.com.mod10.diplomavalidation.dto.ExceptionDTO;
import br.com.mod10.diplomavalidation.dto.ExceptionFieldDTO;
import br.com.mod10.diplomavalidation.utils.exception.FieldErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class ApiControllerExceptionAdvice {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<?> argumentNotValidHandler(MethodArgumentNotValidException e) {
    BindingResult result = e.getBindingResult();

    List<FieldError> fieldErrors = result.getFieldErrors();
    ExceptionFieldDTO exceptions = new ExceptionFieldDTO(FieldErrors.processFieldErrors(fieldErrors));

    return ResponseEntity.badRequest().body(exceptions);
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<?> httpMessageNotReadableHandler(HttpMessageNotReadableException e) {
    return ResponseEntity.badRequest().body(new ExceptionDTO(e.getMessage()));
  }

  @ExceptionHandler(StudentNotExistsException.class)
  public ResponseEntity<?> studentNotExistsHandler(StudentNotExistsException e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionDTO(e.getMessage()));
  }
}
