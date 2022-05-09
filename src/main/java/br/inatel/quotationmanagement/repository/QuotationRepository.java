package br.inatel.quotationmanagement.repository;

import br.inatel.quotationmanagement.model.Quotation;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface QuotationRepository extends CrudRepository<Quotation, UUID> {
}
