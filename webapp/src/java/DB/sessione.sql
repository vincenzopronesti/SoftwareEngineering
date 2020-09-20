-- Table: public.sessione

-- DROP TABLE public.sessione;

CREATE TABLE public.sessione
(
  datainizio date NOT NULL,
  datafine date NOT NULL,
  tipologia character varying(64) NOT NULL,
  anno character varying(32) NOT NULL,
  CONSTRAINT sessione_pkey PRIMARY KEY (datainizio, anno),
  CONSTRAINT sessione_anno_fkey FOREIGN KEY (anno)
      REFERENCES public.annoaccademico (anno) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.sessione
  OWNER TO postgres;
