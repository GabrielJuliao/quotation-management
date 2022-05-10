package br.inatel.quotationmanagement.controller;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/stockcache", produces = MediaType.APPLICATION_JSON_VALUE)
public class StockCacheController {
    @DeleteMapping
    @CacheEvict(value = "registered-stocks", allEntries = true)
    public ResponseEntity<?> evictStocks() {
        return ResponseEntity.ok().build();
    }
}
