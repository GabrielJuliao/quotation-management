package br.inatel.quotationmanagement.controller;

import br.inatel.quotationmanagement.dto.QuotationDTO;
import br.inatel.quotationmanagement.swagger.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class TestController implements Test {
    @GetMapping
    public ResponseEntity<?> getTest() {
        Map<LocalDate, String> testQuotes = new HashMap<>();
        testQuotes.put(LocalDate.now(), "10");
        testQuotes.put(LocalDate.now().plusDays(1), "11");
        testQuotes.put(LocalDate.now().plusDays(2), "12");

        QuotationDTO test = QuotationDTO.builder()
                .id(UUID.randomUUID())
                .stockId("PETR4")
                .quotes(testQuotes)
                .build();
        return new ResponseEntity<>(test, HttpStatus.OK);
    }
}
