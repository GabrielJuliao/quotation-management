package br.inatel.quotationmanagement.swagger;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;

public interface StockCacheDoc {
    @Operation(operationId = "evictStocks", description = "Delete cached stocks")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Delete cached stocks"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    ResponseEntity<?> evictStocks();
}
