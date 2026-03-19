package br.com.cortaai.client.facades;

import br.com.cortaai.client.services.BarbershopService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BarbershopFacade {

    private final BarbershopService barbershopService;

    public List<String> listBarbershops() {
        return barbershopService.listBarbershops();
    }

}
