package br.inatel.quotationmanagement.service;

import br.inatel.quotationmanagement.dto.QuotationDTO;
import br.inatel.quotationmanagement.dto.StockDTO;
import br.inatel.quotationmanagement.exceptions.InvalidStockException;
import br.inatel.quotationmanagement.exceptions.ResourceNotFoundException;
import br.inatel.quotationmanagement.model.Quotation;
import br.inatel.quotationmanagement.repository.QuotationRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@Setter
public class QuotationService {
    private final QuotationRepository quotationRepository;
    private final RestTemplate restTemplate;
    @Value("${app.stock-manager.url}")
    private String stockManagerUrl;

    public QuotationService(QuotationRepository quotationRepository, RestTemplate restTemplate) {
        this.quotationRepository = quotationRepository;
        this.restTemplate = restTemplate;
    }

    public QuotationDTO createOne(QuotationDTO quotationDTO) {
        if (isQuotationValid(quotationDTO.getStockId())) {
            Quotation quotation = quotationRepository
                    .save(
                            Quotation.builder()
                                    .stockId(quotationDTO.getStockId())
                                    .quotes(quotationDTO.getQuotes())
                                    .build()
                    );

            return QuotationDTO.builder()
                    .id(quotation.getId())
                    .stockId(quotation.getStockId())
                    .quotes(quotation.getQuotes())
                    .build();
        }
        throw new InvalidStockException();
    }

    public QuotationDTO readOne(UUID uuid) {
        Optional<Quotation> optionalQuotation = quotationRepository.findById(uuid);
        if (optionalQuotation.isPresent()) {
            Quotation quotation = optionalQuotation.get();
            return QuotationDTO.builder()
                    .id(quotation.getId())
                    .stockId(quotation.getStockId())
                    .quotes(quotation.getQuotes())
                    .build();
        }
        throw new ResourceNotFoundException();
    }

    //readAll

    private Boolean isQuotationValid(String stockId) {
        ResponseEntity<StockDTO[]> responseEntity = restTemplate.getForEntity(stockManagerUrl + "/stock", StockDTO[].class);
        StockDTO[] stockDTOList = Objects.requireNonNull(responseEntity.getBody());

        for (StockDTO s : stockDTOList) {
            if (s.getId().equals(stockId)) {
                return true;
            }
        }

        return false;
    }
}
