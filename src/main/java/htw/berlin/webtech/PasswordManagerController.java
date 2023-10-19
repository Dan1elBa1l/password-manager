package htw.berlin.webtech;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PasswordManagerController {

    @GetMapping("/")
    public String index() {
        return "Hello World!";
    }

}