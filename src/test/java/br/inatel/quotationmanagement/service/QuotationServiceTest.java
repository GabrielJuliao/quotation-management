package br.inatel.quotationmanagement.service;

import br.inatel.quotationmanagement.dto.QuotationDTO;
import br.inatel.quotationmanagement.dto.StockDTO;
import br.inatel.quotationmanagement.model.Quotation;
import br.inatel.quotationmanagement.repository.QuotationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class QuotationServiceTest {

    @Mock
    private RestTemplate restTemplate;
    @Mock
    private QuotationRepository quotationRepository;
    @InjectMocks
    private QuotationService quotationService;

    //data
    private String url;
    private List<StockDTO> defaultStocks;
    private Quotation mockEntity;
    private QuotationDTO mockDTO;

    @BeforeEach
    void setUp() {
        //Necessary URL to use restTemplate mock
        url = "http://localhost:8080/stock";
        quotationService.setStocksUrl(url);

        //Default stocks
        defaultStocks = new ArrayList<>();
        defaultStocks.add(new StockDTO("petr4", "Petrobras PN"));
        defaultStocks.add(new StockDTO("vale5", "Vale do Rio Doce PN"));

        //Base Entity
        mockEntity = Quotation
                .builder()
                .id(UUID.randomUUID())
                .stockId(defaultStocks.get(0).getId())
                .quotes(Map.of(LocalDate.now(), "100")).build();

        //Base DTO
        mockDTO = QuotationDTO
                .builder()
                .id(mockEntity.getId())
                .stockId(mockEntity.getStockId())
                .quotes(mockEntity.getQuotes())
                .build();

        //restTemplate Response on mock request
        StockDTO[] responseBody = new StockDTO[2];
        responseBody[0] = defaultStocks.get(0);
        responseBody[1] = defaultStocks.get(1);

        Mockito.when(restTemplate.getForEntity(url, StockDTO[].class)).thenReturn(new ResponseEntity<>(responseBody, HttpStatus.OK));
    }

    @Test
    void givenAStockQuotationThenItShouldBeCreated() {
        Mockito.when(quotationRepository.save(Mockito.any())).thenReturn(mockEntity);
        assertEquals(mockDTO, quotationService.createOne(mockDTO));
    }

}
