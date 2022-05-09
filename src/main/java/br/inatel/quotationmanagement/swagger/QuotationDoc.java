package br.inatel.quotationmanagement.swagger;

import br.inatel.quotationmanagement.dto.QuotationDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface QuotationDoc {
    @ApiOperation(value = "Creates a quotation then returns")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return the quotation"),
            @ApiResponse(code = 403, message = "You do not have permission to access this resource"),
            @ApiResponse(code = 500, message = "Internal server error"),
    })
    ResponseEntity<?> createQuotation(QuotationDTO quotationDTO);

    @ApiOperation(value = "Get a specific quotation by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Get the quotation"),
            @ApiResponse(code = 403, message = "You do not have permission to access this resource"),
            @ApiResponse(code = 500, message = "Internal server error"),
    })
    ResponseEntity<?> getQuotationById(UUID id);

    @ApiOperation(value = "Get a list of all quotations")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Get a list of quotations"),
            @ApiResponse(code = 403, message = "You do not have permission to access this resource"),
            @ApiResponse(code = 500, message = "Internal server error"),
    })
    ResponseEntity<?> getAllQuotations();
}
