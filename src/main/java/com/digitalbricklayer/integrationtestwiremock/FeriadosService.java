package com.digitalbricklayer.integrationtestwiremock;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Service
public class FeriadosService {

    private final RestTemplate restTemplate;

    private final String DOMAIN_API = "https://date.nager.at/api/v3/PublicHolidays/2023/BR";


    public FeriadosService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Feriado> capturaFeriados(LocalDate data){
        HashMap<String, Object> pathParams = new HashMap<>();
        pathParams.put("Year",data.getYear());
        pathParams.put("CountryCode","BRA");
        ResponseEntity<Feriado[]> responseEntity =
                restTemplate.getForEntity(DOMAIN_API,Feriado[].class);

        if (responseEntity.getStatusCode().is2xxSuccessful() && responseEntity.getBody() != null){
            return Arrays.asList(responseEntity.getBody());
        }else{
            return new ArrayList<>();
        }
    }
}
