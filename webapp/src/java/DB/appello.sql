-- Table: public.appello

-- DROP TABLE public.appello;

CREATE TABLE public.appello
(
  datainizio date NOT NULL,
  datafine date NOT NULL,
  numero integer NOT NULL,
  anno character varying(34) NOT NULL,
  datainiziosessione date,
  CONSTRAINT appello_pkey PRIMARY KEY (datainizio),
  CONSTRAINT appello_datainiziosessione_fkey FOREIGN KEY (datainiziosessione, anno)
      REFERENCES public.sessione (datainizio, anno) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.appello
  OWNER TO postgres;
