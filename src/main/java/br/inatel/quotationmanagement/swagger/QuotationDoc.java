package br.inatel.quotationmanagement.swagger;

import br.inatel.quotationmanagement.dto.QuotationDTO;
import br.inatel.quotationmanagement.exceptions.RFC7807ProblemsDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface QuotationDoc {

    @Operation(operationId = "createQuotation", description = "Creates a stock quotation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Returns a quotation", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = QuotationDTO.class))
            }),
            @ApiResponse(responseCode = "400", description = "Bad request", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = RFC7807ProblemsDetails.class))
            }),
            @ApiResponse(responseCode = "500", description = "Internal server error")

    })
    ResponseEntity<?> createQuotation(QuotationDTO quotationDTO);

    @Operation(operationId = "getQuotationById", description = "Get a specific quotation by UUID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns a specific quotation", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = QuotationDTO.class))
            }),
            @ApiResponse(responseCode = "404", description = "Resource not found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = RFC7807ProblemsDetails.class))
            }),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    ResponseEntity<?> getQuotationById(UUID id);

    @Operation(operationId = "getAllQuotations", description = "Get a list of quotations")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get a list of all quotations", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = QuotationDTO.class)))
            }),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    ResponseEntity<?> getAllQuotations();
}
