package br.net.silva.daniel.skipedlocked;

import br.net.silva.daniel.skipedlocked.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloControler {

    private final Message message;

    @Autowired
    public HelloControler(@Qualifier("getMessageBean") Message message) {
        this.message = message;
    }

    @GetMapping
    public String getHello() {
        message.print();
        return "Hello World With " + message.getClass().getSimpleName() + "! ";
    }
}
