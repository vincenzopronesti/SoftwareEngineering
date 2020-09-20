-- Table: public.prenotazionedidattica

-- DROP TABLE public.prenotazionedidattica;

CREATE TABLE public.prenotazionedidattica
(
  idaula integer NOT NULL,
  data date NOT NULL,
  fasciaoraria character varying(64) NOT NULL,
  nomecorso character varying(64),
  tiposeduta character varying(32),
  datainizioappello date,
  CONSTRAINT prenotazionedidattica_pkey PRIMARY KEY (idaula, data, fasciaoraria),
  CONSTRAINT prenotazionedidattica_datainizioappello_fkey FOREIGN KEY (datainizioappello)
      REFERENCES public.appello (datainizio) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT prenotazionedidattica_idaula_fkey FOREIGN KEY (idaula, data, fasciaoraria)
      REFERENCES public.prenotazione (idaula, data, fasciaoraria) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT prenotazione_fasciaoraria_check CHECK (fasciaoraria::text = 'prima'::text OR fasciaoraria::text = 'seconda'::text OR fasciaoraria::text = 'terza'::text OR fasciaoraria::text = 'quarta'::text)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.prenotazionedidattica
  OWNER TO postgres;
