package com.insurance.config.web.exception;

import com.insurance.config.adapter.web.response.ErrorResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandleExecption {


    Logger logger = LoggerFactory.getLogger(HandleExecption.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleException(Exception ex) {
        logger.error("Ocorreu um exceção não mapeada:" + ex.getMessage());
        return ResponseEntity.ok(ErrorResponseDto
                .builder()
                    .message(ex.getMessage())
                    .code(500)
                .build());

    }

//    @ExceptionHandler(InsuranceException.class)
//    public ResponseEntity<ErrorResponseDto> handleInsuranveException(Exception ex) {
//        logger.error("Ocorreu um exceção não mapeada:" + ex.getMessage());
//        return ResponseEntity.ok(ErrorResponseDto
//                .builder()
//                .message(ex.getMessage())
//                .code(500)
//                .build());
//
//    }

}
