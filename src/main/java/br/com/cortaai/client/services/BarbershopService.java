package br.com.cortaai.client.services;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BarbershopService {

    public List<String> listBarbershops() {
        // This is a placeholder implementation. In a real application, this would likely
        // involve fetching data from a database or an external API.
        return List.of("Barbershop A", "Barbershop B", "Barbershop C");
    }
}
