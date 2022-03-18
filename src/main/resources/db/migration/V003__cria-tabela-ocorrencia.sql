CREATE TABLE ocorrencia (
	id BIGSERIAL NOT NULL CONSTRAINT pk_ocorrencia_id PRIMARY KEY,
	entrega_id BIGINT NOT NULL,
	descricao CHARACTER VARYING NOT NULL,
	data_registro TIMESTAMP WITH TIME ZONE NOT NULL
);

ALTER TABLE ocorrencia ADD CONSTRAINT fk_ocorrencia_entrega FOREIGN KEY (entrega_id) REFERENCES entrega (id);
