package dev.mocad.springsecuritybasic;

import java.sql.SQLException;

import dev.mocad.springsecuritybasic.exception.GlobalException;
import dev.mocad.springsecuritybasic.model.dto.ErrorDTO;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@RestControllerAdvice
public class ApplicationControllerAdvice extends ResponseEntityExceptionHandler {

  @ExceptionHandler({GlobalException.class})
  public ResponseEntity<ErrorDTO> handleExceptionCustom(GlobalException ex) {
    ErrorDTO errorDTO = new ErrorDTO();
    errorDTO.setError(ex.getMessage());
    errorDTO.setCode(HttpStatus.OK.toString());
    return ResponseEntity.ok(errorDTO);
  }

  /*Captura execeçoes do projeto*/

  @ExceptionHandler({Exception.class, RuntimeException.class, Throwable.class})
  @Override
  protected ResponseEntity<Object> handleExceptionInternal
      (Exception ex, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {

    ErrorDTO errorDTO = new ErrorDTO();
    String msg = "";

   if (ex instanceof MethodArgumentNotValidException) {
     List<ObjectError> list = ((MethodArgumentNotValidException) ex).getBindingResult().getAllErrors();

     for (ObjectError objectError : list) {
      msg += objectError.getDefaultMessage() + " - ";
     }
   } else if (ex instanceof HttpMessageNotReadableException) {
     msg = "No data sent to the request body";
   } else {
     msg = ex.getMessage();
   }
    errorDTO.setError(msg.toString());
    errorDTO.setCode(statusCode.toString());
    ex.printStackTrace();

    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDTO);

  }

  /*Captura erro na parte de banco*/
  @ExceptionHandler({DataIntegrityViolationException.class,
      ConstraintViolationException.class, SQLException.class})
  protected ResponseEntity<Object> handleExceptionDataIntegry(Exception ex){

    ErrorDTO errorDTO = new ErrorDTO();

    String msg = "";

    if (ex instanceof DataIntegrityViolationException) {
      msg = "Integrity Error in DataBase: " +  ((DataIntegrityViolationException) ex).getCause().getCause().getMessage();
    }else
    if (ex instanceof ConstraintViolationException) {
      msg = "Foreign key error in DataBase " + ((ConstraintViolationException) ex).getCause().getCause().getMessage();
    }else
    if (ex instanceof SQLException) {
      msg = "Query SQL Error in DataBase: " + ((SQLException) ex).getCause().getCause().getMessage();
    }else {
      msg = ex.getMessage();
    }

    errorDTO.setError(msg);
    errorDTO.setCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());

    ex.printStackTrace();

    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDTO);
  }

}
