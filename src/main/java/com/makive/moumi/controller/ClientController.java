package com.makive.moumi.controller;

import com.makive.moumi.exception.dto.DataResponse;
import com.makive.moumi.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @GetMapping("/{clientId}")
    public DataResponse getClient(@PathVariable Long clientId) {
        return DataResponse.of(clientService.getClient(clientId));
    }
}
