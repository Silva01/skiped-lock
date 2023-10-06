package br.net.silva.daniel.skipedlocked.config;

import br.net.silva.daniel.skipedlocked.message.Message;
import br.net.silva.daniel.skipedlocked.message.ServiceBroker;
import br.net.silva.daniel.skipedlocked.message.ServiceSMS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfig {

    private final ApplicationContext context;

    @Value("${message.type:false}")
    private boolean flag;

    @Autowired
    public MessageConfig(ApplicationContext context) {
        this.context = context;
    }

    @Bean
    public Message getMessageBean() {
        if (flag) {
            return context.getBean(ServiceSMS.class);
        } else {
            return context.getBean(ServiceBroker.class);
        }
    }

    @Bean
    public Message getSMS() {
        return new ServiceSMS();
    }

    @Bean
    public Message getBroker() {
        return new ServiceBroker();
    }
}
