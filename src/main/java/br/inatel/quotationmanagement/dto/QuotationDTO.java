package br.inatel.quotationmanagement.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

@Data
@Builder
public class QuotationDTO {
    private UUID id;
    private String stockId;
    private Map<LocalDate, String> quotes;
}
