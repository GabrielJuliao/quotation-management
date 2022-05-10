package br.inatel.quotationmanagement.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

@Data
@Builder
public class QuotationDTO {
    @ApiModelProperty(notes = "Quotation ID", example = "79e5cc2c-cfd5-11ec-9d64-0242ac120002")
    private UUID id;
    @ApiModelProperty(notes = "Stock ID", example = "petr5", required = true)
    private String stockId;
    private Map<LocalDate, String> quotes;
}
