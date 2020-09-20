-- Table: public.annoaccademico

-- DROP TABLE public.annoaccademico;

CREATE TABLE public.annoaccademico
(
  anno character varying(64) NOT NULL,
  datainizio date NOT NULL,
  datafine date NOT NULL,
  CONSTRAINT annoaccademico_pkey PRIMARY KEY (anno)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.annoaccademico
  OWNER TO postgres;
