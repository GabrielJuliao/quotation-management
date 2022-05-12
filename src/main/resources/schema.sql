drop table if exists quotation_quotes;
drop table if exists quotation;
CREATE TABLE quotation (
  id binary(16) NOT NULL,
  stock_id varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
);
CREATE TABLE quotation_quotes (
  quotation_id binary(16) NOT NULL,
  quotes varchar(255) DEFAULT NULL,
  quotes_key date NOT NULL,
  PRIMARY KEY (quotation_id, quotes_key),
  CONSTRAINT fk_quotation FOREIGN KEY (quotation_id) REFERENCES quotation (id)
);