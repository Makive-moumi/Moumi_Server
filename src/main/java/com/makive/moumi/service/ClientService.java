package com.makive.moumi.service;

import com.makive.moumi.exception.Code;
import com.makive.moumi.exception.GeneralException;
import com.makive.moumi.model.domain.Client;
import com.makive.moumi.model.dto.ClientDTO;
import com.makive.moumi.model.dto.response.ClientResponse;
import com.makive.moumi.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientResponse getClient(Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new GeneralException(Code.DATA_NOT_FOUND));

        return ClientResponse.builder()
                .client(ClientDTO.fromClient(client))
                .build();
    }
}
