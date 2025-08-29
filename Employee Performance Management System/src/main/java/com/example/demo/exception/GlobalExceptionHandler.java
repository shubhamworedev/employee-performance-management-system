package com.example.demo.exception;
import com.example.demo.exception.custom.*;
import com.example.demo.Response.ErrorResponse;
import com.example.demo.Response.UnifiedApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<UnifiedApiResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {

        String combinedErrors = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError(combinedErrors);
        errorResponse.setResponseCode(HttpStatus.BAD_REQUEST.value());

        UnifiedApiResponse unifiedApiResponse = new UnifiedApiResponse();
        unifiedApiResponse.setMeta(errorResponse);
        unifiedApiResponse.setData(ex.getBindingResult().getTarget());
        return new ResponseEntity<>(unifiedApiResponse, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<UnifiedApiResponse> handleNoSuchElementException(NoSuchElementException ex) {

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError(ex.getMessage());
        errorResponse.setResponseCode(HttpStatus.NOT_FOUND.value());

        UnifiedApiResponse unifiedApiResponse = new UnifiedApiResponse();
        unifiedApiResponse.setMeta(errorResponse);
        unifiedApiResponse.setData(null);

        return new ResponseEntity<>(unifiedApiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<UnifiedApiResponse> handleBadRequestException(BadRequestException ex) {
        log.warn("BadRequestException: {}", ex.getMessage(), ex);
        return badRequest(ex.getMessage());
    }



    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<UnifiedApiResponse> handleNotFoundException(NotFoundException ex) {
        log.warn("NotFoundException: {}", ex.getMessage());
        return notFound(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<UnifiedApiResponse> handleGeneralException(Exception ex) {
        log.error("InternalServerErrorException: {}", ex.getMessage(), ex);
        return internalServerError("Internal Server Error:"+ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<UnifiedApiResponse> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String message = String.format("Invalid value '%s' for parameter '%s'. Expected type is '%s'.",
                ex.getValue(),
                ex.getName(),
                ex.getRequiredType() != null ? ex.getRequiredType().getSimpleName() : "Unknown");

        UnifiedApiResponse response = new UnifiedApiResponse();
        response.setMeta(new ErrorResponse()
                .error(message)
                .responseCode(HttpStatus.BAD_REQUEST.value()));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    private ResponseEntity<UnifiedApiResponse> badRequest(String message) {
        return ResponseEntity.badRequest().body(
                new UnifiedApiResponse()
                        .meta(new ErrorResponse().error(message).responseCode(HttpStatus.BAD_REQUEST.value())));
    }



    private ResponseEntity<UnifiedApiResponse> notFound(String message) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new UnifiedApiResponse()
                        .meta(new ErrorResponse().error(message).responseCode(HttpStatus.NOT_FOUND.value())));
    }

    private ResponseEntity<UnifiedApiResponse> internalServerError(String message) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new UnifiedApiResponse()
                        .meta(new ErrorResponse().error(message).responseCode(HttpStatus.INTERNAL_SERVER_ERROR.value())));
    }


}
