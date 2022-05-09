package br.inatel.quotationmanagement.swagger;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;

public interface Test {
    @ApiOperation(value = "Return a sample Get test")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return the get test"),
            @ApiResponse(code = 403, message = "You do not have permission to access this resource"),
            @ApiResponse(code = 500, message = "Internal server error"),
    })
    public ResponseEntity<?> getTest();
}
