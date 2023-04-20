package com.standard.Common.Exception;


import com.standard.untils.Res;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(value = RuntimeException.class)
    public Res handler(RuntimeException r){
        r.printStackTrace();
        log.error("运行时异常-----{}"+r.getMessage());

        return Res.error(r.getMessage());
    }
}
