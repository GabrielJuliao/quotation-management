package br.inatel.quotationmanagement.service;

import br.inatel.quotationmanagement.dto.StockDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class StockService {
    private final RestTemplate restTemplate;
    @Value("${app.stock-manager.url}")
    private String stockManagerUrl;

    public StockService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Cacheable("registered-stocks")
    public List<StockDTO> getRegisteredStocks() {
        return Arrays.stream(Objects.requireNonNull(restTemplate.getForEntity(stockManagerUrl + "/stock", StockDTO[].class).getBody())).collect(Collectors.toList());
    }
}
