package com.digitalbricklayer.integrationtestwiremock;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;

import java.time.LocalDate;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@SpringBootTest
@AutoConfigureWireMock(port = 0)
class IntegrationTestWiremockApplicationTests {


    @Autowired
    private FeriadosService feriadosService;

    @Test
    public void deveRetornarFeriados(){
        String url = String.format("/api/v3/PublicHolidays/%d/%s",LocalDate.now().getYear(),"BR");
        stubFor(
                get(urlEqualTo(url))
                        .willReturn(aResponse().withBody("[{\"date\":\"2023-01-01\",\"localName\":\"Confraternização Universal\",\"name\":\"New Year's Day\",\"countryCode\":\"BR\",\"fixed\":true,\"global\":true,\"counties\":null,\"launchYear\":null,\"types\":[\"Public\"]},{\"date\":\"2023-02-20\",\"localName\":\"Carnaval\",\"name\":\"Carnival\",\"countryCode\":\"BR\",\"fixed\":false,\"global\":true,\"counties\":null,\"launchYear\":null,\"types\":[\"Bank\",\"Optional\"]},{\"date\":\"2023-02-21\",\"localName\":\"Carnaval\",\"name\":\"Carnival\",\"countryCode\":\"BR\",\"fixed\":false,\"global\":true,\"counties\":null,\"launchYear\":null,\"types\":[\"Bank\",\"Optional\"]},{\"date\":\"2023-04-07\",\"localName\":\"Sexta-feira Santa\",\"name\":\"Good Friday\",\"countryCode\":\"BR\",\"fixed\":false,\"global\":true,\"counties\":null,\"launchYear\":null,\"types\":[\"Public\"]},{\"date\":\"2023-04-09\",\"localName\":\"Domingo de Páscoa\",\"name\":\"Easter Sunday\",\"countryCode\":\"BR\",\"fixed\":false,\"global\":true,\"counties\":null,\"launchYear\":null,\"types\":[\"Public\"]},{\"date\":\"2023-04-21\",\"localName\":\"Dia de Tiradentes\",\"name\":\"Tiradentes\",\"countryCode\":\"BR\",\"fixed\":true,\"global\":true,\"counties\":null,\"launchYear\":null,\"types\":[\"Public\"]},{\"date\":\"2023-05-01\",\"localName\":\"Dia do Trabalhador\",\"name\":\"Labour Day\",\"countryCode\":\"BR\",\"fixed\":true,\"global\":true,\"counties\":null,\"launchYear\":null,\"types\":[\"Public\"]},{\"date\":\"2023-06-08\",\"localName\":\"Corpus Christi\",\"name\":\"Corpus Christi\",\"countryCode\":\"BR\",\"fixed\":false,\"global\":true,\"counties\":null,\"launchYear\":null,\"types\":[\"Public\"]},{\"date\":\"2023-07-09\",\"localName\":\"Revolução Constitucionalista de 1932\",\"name\":\"Constitutionalist Revolution of 1932\",\"countryCode\":\"BR\",\"fixed\":true,\"global\":false,\"counties\":[\"BR-SP\"],\"launchYear\":null,\"types\":[\"Public\"]},{\"date\":\"2023-09-07\",\"localName\":\"Dia da Independência\",\"name\":\"Independence Day\",\"countryCode\":\"BR\",\"fixed\":true,\"global\":true,\"counties\":null,\"launchYear\":null,\"types\":[\"Public\"]},{\"date\":\"2023-10-12\",\"localName\":\"Nossa Senhora Aparecida\",\"name\":\"Our Lady of Aparecida\",\"countryCode\":\"BR\",\"fixed\":true,\"global\":true,\"counties\":null,\"launchYear\":null,\"types\":[\"Public\"]},{\"date\":\"2023-11-02\",\"localName\":\"Dia de Finados\",\"name\":\"All Souls' Day\",\"countryCode\":\"BR\",\"fixed\":true,\"global\":true,\"counties\":null,\"launchYear\":null,\"types\":[\"Public\"]},{\"date\":\"2023-11-15\",\"localName\":\"Proclamação da República\",\"name\":\"Republic Proclamation Day\",\"countryCode\":\"BR\",\"fixed\":true,\"global\":true,\"counties\":null,\"launchYear\":null,\"types\":[\"Public\"]},{\"date\":\"2023-12-25\",\"localName\":\"Natal\",\"name\":\"Christmas Day\",\"countryCode\":\"BR\",\"fixed\":true,\"global\":true,\"counties\":null,\"launchYear\":null,\"types\":[\"Public\"]}]"))
        );

        List<Feriado> feriados =  feriadosService.capturaFeriados(LocalDate.now());

        Assertions.assertEquals(14,feriados.size());

        feriados.stream().forEach( it -> System.out.println (it.date + " " + it.name));
        WireMock.saveAllMappings();

    }


}
