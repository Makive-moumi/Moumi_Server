package com.makive.moumi.model.dto.response;

import com.makive.moumi.model.dto.ClientDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientResponse {
    private ClientDTO client;
}
