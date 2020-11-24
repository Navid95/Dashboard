package mtni.its.dashboard.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class InputFileErrorAdvice {

    @ResponseBody
    @ResponseStatus(value = HttpStatus.EXPECTATION_FAILED)
    @ExceptionHandler(InputFileErrorException.class)
    public String InputFileErrorHandler(InputFileErrorException e){
        return e.getMessage();
    }
}
