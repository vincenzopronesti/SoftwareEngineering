-- Table: public.prenotazioneconvegni

-- DROP TABLE public.prenotazioneconvegni;

CREATE TABLE public.prenotazioneconvegni
(
  idaula integer NOT NULL,
  data date NOT NULL,
  fasciaoraria character varying(64) NOT NULL,
  titoloevento character varying(64),
  tipoevento character varying(32),
  CONSTRAINT prenotazioneconvegni_pkey PRIMARY KEY (idaula, data, fasciaoraria),
  CONSTRAINT prenotazioneconvegni_idaula_fkey FOREIGN KEY (idaula, data, fasciaoraria)
      REFERENCES public.prenotazione (idaula, data, fasciaoraria) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT prenotazione_fasciaoraria_check CHECK (fasciaoraria::text = 'prima'::text OR fasciaoraria::text = 'seconda'::text OR fasciaoraria::text = 'terza'::text OR fasciaoraria::text = 'quarta'::text)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.prenotazioneconvegni
  OWNER TO postgres;
