package br.inatel.quotationmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class QuotationDTO {
    private UUID id;
    @NotEmpty
    private String stockId;
    private Map<@NotNull LocalDate,@NotEmpty @Pattern(regexp = "^[1-9]+[0-9]*$") String> quotes;
}
