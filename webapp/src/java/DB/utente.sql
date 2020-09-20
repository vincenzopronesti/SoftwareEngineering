-- Table: public.utente

-- DROP TABLE public.utente;

CREATE TABLE public.utente
(
  name character varying(64),
  username character varying(64) NOT NULL,
  password character varying(64),
  type character varying(64),
  CONSTRAINT utente_pkey PRIMARY KEY (username),
  CONSTRAINT utente_type_check CHECK (type::text = 'Professore'::text OR type::text = 'Segretario'::text)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.utente
  OWNER TO postgres;
