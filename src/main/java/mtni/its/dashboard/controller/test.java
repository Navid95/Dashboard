package mtni.its.dashboard.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class test {

    @RequestMapping("hi")
    public String hellowWorld(){
        System.err.println("*******************************************************************************************");

        return "Fuck You\n";
    }
}
