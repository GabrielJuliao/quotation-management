package br.inatel.quotationmanagement.model;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Table(name = "quotation")
public class Quotation {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    /*
      @Column(columnDefinition = "BINARY(16)")
      Is necessary to query id's while using UUID due to the database adding zeros to the rest of the id.
    */
    @Column(name = "id", columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "stockId")
    private String stockId;

    @ElementCollection
    @CollectionTable(name = "quotation_quotes")
    @MapKeyJoinColumn(name = "quotes_key")
    @Column(name = "quotes")
    private Map<LocalDate, String> quotes = new HashMap<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Quotation quotation = (Quotation) o;
        return id != null && Objects.equals(id, quotation.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
