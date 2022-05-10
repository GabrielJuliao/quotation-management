package br.inatel.quotationmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StockDTO {
    private String id;
    private String description;

    public StockDTO() {
    }
}
