package org.example.websocket.service;

import org.example.websocket.model.Cidade;
import org.example.websocket.dto.ClimaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.Random;

@Service
@EnableScheduling
public class ClimaService {
    private final SimpMessagingTemplate messagingTemplate;
    private final TemperaturService temperaturService;
    private final Random random = new Random();

    private final List<Cidade> cidades = List.of(
            new Cidade("Goiânia", -16.68, -49.25),
            new Cidade("Brasília", -15.78, -47.93),
            new Cidade("São Paulo", -23.55, -46.63),
            new Cidade("Rio de Janeiro", -22.90, -43.20),
            new Cidade("Belo Horizonte", -19.92, -43.94),
            new Cidade("Salvador", -12.97, -38.50),
            new Cidade("Fortaleza", -3.73, -38.52),
            new Cidade("Curitiba", -25.42, -49.27),
            new Cidade("Manaus", -3.10, -60.02),
            new Cidade("Recife", -8.05, -34.88)
    );

    @Autowired
    public ClimaService(SimpMessagingTemplate messagingTemplate, TemperaturService temperaturService) {
        this.messagingTemplate = messagingTemplate;
        this.temperaturService = temperaturService;
    }
    @Scheduled(fixedRate = 5000)
    public void eviarClima(){
        Cidade cidade = cidades.get(random.nextInt(cidades.size()));
        double temperatura = temperaturService.buscarTemperatura(cidade.getLat(), cidade.getLon());

        ClimaDTO clima = new ClimaDTO();
        clima.setCidade(cidade.getNome());
        clima.setTemperatura(temperatura);
        clima.setDescricao("Tempo real");
        clima.setHorario(LocalTime.now().toString());

        messagingTemplate.convertAndSend("/topic/clima/auto", clima);
    }

    public ClimaDTO eviarClimaCidade(Cidade cidade){
        double temperatura = temperaturService.buscarTemperatura(cidade.getLat(), cidade.getLon());

        System.out.println("teste");

        ClimaDTO clima = new ClimaDTO();
        clima.setCidade(cidade.getNome());
        clima.setTemperatura(temperatura);
        clima.setDescricao("Tempo real");
        clima.setHorario(LocalTime.now().toString());

        return clima;
    }


}
