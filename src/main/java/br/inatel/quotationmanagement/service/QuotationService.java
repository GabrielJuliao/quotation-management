package br.inatel.quotationmanagement.service;

import br.inatel.quotationmanagement.dto.QuotationDTO;
import br.inatel.quotationmanagement.dto.StockDTO;
import br.inatel.quotationmanagement.exceptions.InvalidStockException;
import br.inatel.quotationmanagement.exceptions.ResourceNotFoundException;
import br.inatel.quotationmanagement.model.Quotation;
import br.inatel.quotationmanagement.repository.QuotationRepository;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Setter
public class QuotationService {
    private final QuotationRepository quotationRepository;
    private final StockService stockService;

    public QuotationService(QuotationRepository quotationRepository, StockService stockService) {
        this.quotationRepository = quotationRepository;
        this.stockService = stockService;
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

    public QuotationDTO readOneById(UUID uuid) {
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

    public Collection<QuotationDTO> readAll() {
        Iterable<Quotation> quotationList = quotationRepository.findAll();
        return StreamSupport.stream(quotationList.spliterator(), false)
                .map(
                        quotation -> new QuotationDTO(
                                quotation.getId(),
                                quotation.getStockId(),
                                quotation.getQuotes()
                        )
                ).collect(Collectors.toList());
    }

    private boolean isQuotationValid(String stockId) {
        for (StockDTO s : stockService.getRegisteredStocks()) {
            if (s.getId().equals(stockId)) {
                return true;
            }
        }
        return false;
    }

}
