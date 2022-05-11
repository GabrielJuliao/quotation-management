package br.inatel.quotationmanagement.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(name = "stockId", example = "petr4")
    private String stockId;
    @Schema(name = "quotes", example = "{\n" +
            "        \"2022-05-01\": \"100\"\n" +
            "    }"
    )
    private Map<@NotNull LocalDate, @NotEmpty @Pattern(regexp = "^[0-9]+[0-9]*$") String> quotes;

    public QuotationDTO() {
    }
}
