package org.example.websocket.controller;

import org.example.websocket.dto.ClimaDTO;
import org.example.websocket.model.Cidade;
import org.example.websocket.service.ClimaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ClimaController {
    private final ClimaService climaService;

    @Autowired
    public ClimaController(ClimaService climaService) {
        this.climaService = climaService;
    }

    @MessageMapping("/sendMessage")
    @SendTo("/topic/clima/usuario")
    public ClimaDTO sendMessage(Cidade cidade) {
        System.out.println("testeeeeeeeeeee");
       return climaService.eviarClimaCidade(cidade);
    }
}
