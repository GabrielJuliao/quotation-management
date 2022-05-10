package br.inatel.quotationmanagement.controller;

import br.inatel.quotationmanagement.dto.QuotationDTO;
import br.inatel.quotationmanagement.service.QuotationService;
import br.inatel.quotationmanagement.swagger.QuotationDoc;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(value = "/quotation", produces = MediaType.APPLICATION_JSON_VALUE)
public class QuotationController implements QuotationDoc {

    private final QuotationService quotationService;

    public QuotationController(QuotationService quotationService) {
        this.quotationService = quotationService;
    }

    @Override
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createQuotation(@RequestBody @Valid QuotationDTO quotationDTO) {
        return new ResponseEntity<>(quotationService.createOne(quotationDTO), HttpStatus.OK);
    }

    @Override
    @GetMapping("{uuid}")
    public ResponseEntity<?> getQuotationById(@PathVariable UUID uuid) {
        return new ResponseEntity<>(quotationService.readOneById(uuid), HttpStatus.OK);
    }

    @Override
    @GetMapping
    public ResponseEntity<?> getAllQuotations() {
        return new ResponseEntity<>(quotationService.readAll(), HttpStatus.OK);
    }
}
