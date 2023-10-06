package br.net.silva.daniel.skipedlocked.message;

import org.springframework.stereotype.Service;

@Service
public class ServiceBroker implements Message {

    @Override
    public void print() {
        System.out.println("Broker");
    }
}
