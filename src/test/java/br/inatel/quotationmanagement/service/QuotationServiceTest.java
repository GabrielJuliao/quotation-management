package br.inatel.quotationmanagement.service;

import br.inatel.quotationmanagement.dto.QuotationDTO;
import br.inatel.quotationmanagement.dto.StockDTO;
import br.inatel.quotationmanagement.exceptions.InvalidStockException;
import br.inatel.quotationmanagement.exceptions.ResourceNotFoundException;
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
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class QuotationServiceTest {

    @Mock
    private RestTemplate restTemplate;
    @Mock
    private QuotationRepository quotationRepository;
    @InjectMocks
    private QuotationService quotationService;
    //data
    private Quotation mockEntity;
    private QuotationDTO mockDTO;

    @BeforeEach
    void setUp() {
        //Necessary URL to use restTemplate mock
        String url = "http://localhost:8080/stock";
        quotationService.setStocksUrl(url);

        //Default stocks
        List<StockDTO> defaultStocks = new ArrayList<>();
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

        Mockito.lenient().when(restTemplate.getForEntity(url, StockDTO[].class)).thenReturn(new ResponseEntity<>(responseBody, HttpStatus.OK));
    }

    @Test
    void givenAValidStockQuotationThenItShouldBeCreated() {
        Mockito.when(quotationRepository.save(Mockito.any())).thenReturn(mockEntity);
        assertEquals(mockDTO, quotationService.createOne(mockDTO));
    }

    @Test
    void giveAnInvalidStockIdThenItShouldThrowAnException() {
        QuotationDTO invalidIdDTO = QuotationDTO
                .builder()
                .id(mockEntity.getId())
                .stockId("invalid-stock-id")
                .quotes(mockEntity.getQuotes())
                .build();

        assertThrows(InvalidStockException.class, () -> quotationService.createOne(invalidIdDTO));
    }

    @Test
    void giveAnEmptyStockIdThenItShouldThrowAnException() {
        QuotationDTO emptyStockIdDTO = QuotationDTO
                .builder()
                .id(mockEntity.getId())
                .stockId("")
                .quotes(mockEntity.getQuotes())
                .build();

        assertThrows(Exception.class, () -> quotationService.createOne(emptyStockIdDTO));
    }

    @Test
    void giveANullStockIdThenItShouldThrowAnException() {
        QuotationDTO nullStockIdDTO = QuotationDTO
                .builder()
                .id(mockEntity.getId())
                .quotes(mockEntity.getQuotes())
                .build();

        assertThrows(Exception.class, () -> quotationService.createOne(nullStockIdDTO));
    }

    @Test
    void givenANegativeQuotesValueThenItShouldThrowAnException() {
        QuotationDTO negativeQuotesValueDTO = QuotationDTO
                .builder()
                .id(mockEntity.getId())
                .stockId(mockEntity.getStockId())
                .quotes(Map.of(LocalDate.now(), "-1"))
                .build();

        assertThrows(Exception.class, () -> quotationService.createOne(negativeQuotesValueDTO));
    }

    @Test
    void givenANonNumericQuotesValueThenItShouldThrowAnException() {
        QuotationDTO nonNumericQuotesValueDTO = QuotationDTO
                .builder()
                .id(mockEntity.getId())
                .stockId(mockEntity.getStockId())
                .quotes(Map.of(LocalDate.now(), "x"))
                .build();

        assertThrows(Exception.class, () -> quotationService.createOne(nonNumericQuotesValueDTO));
    }

    @Test
    void givenAnEmptyQuotesValueThenItShouldThrowAnException() {
        QuotationDTO emptyQuotesValueDTO = QuotationDTO
                .builder()
                .id(mockEntity.getId())
                .stockId(mockEntity.getStockId())
                .quotes(Map.of(LocalDate.now(), ""))
                .build();

        assertThrows(Exception.class, () -> quotationService.createOne(emptyQuotesValueDTO));
    }

    @Test
    void givenAnInvalidQuotesDateThenItShouldThrowAnException() {
        QuotationDTO invalidQuotesDateDTO = QuotationDTO
                .builder()
                .id(mockEntity.getId())
                .stockId(mockEntity.getStockId())
                .quotes(Map.of(LocalDate.ofYearDay(2022, 1), "1"))
                .build();

        assertThrows(Exception.class, () -> quotationService.createOne(invalidQuotesDateDTO));
    }

    @Test
    void givenANullQuotesThenItShouldThrowAnException() {
        QuotationDTO nullQuotesDTO = QuotationDTO
                .builder()
                .id(mockEntity.getId())
                .stockId(mockEntity.getStockId())
                .build();

        assertThrows(Exception.class, () -> quotationService.createOne(nullQuotesDTO));
    }

    @Test
    void givenAValidUUIDThenItShouldReturnAStockQuotation() {
        Mockito.when(quotationRepository.findById(Mockito.any())).thenReturn(Optional.ofNullable(mockEntity));
        assertEquals(mockDTO, quotationService.readOneById(mockDTO.getId()));
    }

    @Test
    void givenAnInValidUUIDThenItShouldThrowAnException() {
        Mockito.when(quotationRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> quotationService.readOneById(UUID.randomUUID()));
    }
}
