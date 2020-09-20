-- Table: public.aulaconferenza

-- DROP TABLE public.aulaconferenza;

CREATE TABLE public.aulaconferenza
(
  id integer NOT NULL,
  lavagna boolean,
  microfono boolean,
  numero_posti integer,
  proiettore boolean,
  ethernet boolean,
  lavagnainterattiva boolean,
  presecorrente boolean,
  CONSTRAINT "Aula_pkey" PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.aulaconferenza
  OWNER TO postgres;
