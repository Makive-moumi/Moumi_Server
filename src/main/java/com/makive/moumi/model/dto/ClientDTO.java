package com.makive.moumi.model.dto;

import com.makive.moumi.model.domain.Client;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {
    private String email;
    private String name;

    public static ClientDTO fromClient(Client client) {
        return ClientDTO.builder()
                .email(client.getEmail())
                .name(client.getName())
                .build();
    }
}
