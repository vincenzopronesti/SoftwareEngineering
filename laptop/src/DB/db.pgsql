--
-- PostgreSQL database dump
--

-- Dumped from database version 10.1
-- Dumped by pg_dump version 10.1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: University; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON DATABASE "University" IS 'ISPW PRJ';


--
-- Name: asdffdsa; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA asdffdsa;


ALTER SCHEMA asdffdsa OWNER TO postgres;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: annoaccademico; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE annoaccademico (
    anno character varying(64) NOT NULL,
    datainizio date NOT NULL,
    datafine date NOT NULL
);


ALTER TABLE annoaccademico OWNER TO postgres;

--
-- Name: appello; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE appello (
    datainizio date NOT NULL,
    datafine date NOT NULL,
    numero integer NOT NULL,
    anno character varying(34) NOT NULL,
    datainiziosessione date
);


ALTER TABLE appello OWNER TO postgres;

--
-- Name: aula; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE aula (
    id integer NOT NULL,
    lavagna boolean,
    microfono boolean,
    numero_posti integer,
    proiettore boolean,
    ethernet boolean,
    lavagnainterattiva boolean,
    presecorrente boolean,
    tipo character varying
);


ALTER TABLE aula OWNER TO postgres;

--
-- Name: COLUMN aula.tipo; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN aula.tipo IS 'didattica convegno laboratorio
';


--
-- Name: prenotazione; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE prenotazione (
    idaula integer NOT NULL,
    data date NOT NULL,
    fasciaoraria character varying(64) NOT NULL,
    username character varying(64),
    CONSTRAINT prenotazione_fasciaoraria_check CHECK ((((fasciaoraria)::text = 'prima'::text) OR ((fasciaoraria)::text = 'seconda'::text) OR ((fasciaoraria)::text = 'terza'::text) OR ((fasciaoraria)::text = 'quarta'::text)))
);


ALTER TABLE prenotazione OWNER TO postgres;

--
-- Name: prenotazioneconferenza; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE prenotazioneconferenza (
    idaula integer NOT NULL,
    data date NOT NULL,
    fasciaoraria character varying(64) NOT NULL,
    titoloevento character varying(64),
    annoaccademico character varying(32),
    CONSTRAINT prenotazione_fasciaoraria_check CHECK ((((fasciaoraria)::text = 'prima'::text) OR ((fasciaoraria)::text = 'seconda'::text) OR ((fasciaoraria)::text = 'terza'::text) OR ((fasciaoraria)::text = 'quarta'::text)))
);


ALTER TABLE prenotazioneconferenza OWNER TO postgres;

--
-- Name: prenotazionedidattica; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE prenotazionedidattica (
    idaula integer NOT NULL,
    data date NOT NULL,
    fasciaoraria character varying(64) NOT NULL,
    nomecorso character varying(64),
    tiposeduta character varying(32),
    datainizioappello date,
    annoaccademico character varying(32),
    CONSTRAINT prenotazione_fasciaoraria_check CHECK ((((fasciaoraria)::text = 'prima'::text) OR ((fasciaoraria)::text = 'seconda'::text) OR ((fasciaoraria)::text = 'terza'::text) OR ((fasciaoraria)::text = 'quarta'::text)))
);


ALTER TABLE prenotazionedidattica OWNER TO postgres;

--
-- Name: prenotazionisedutedilaurea; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE prenotazionisedutedilaurea (
    idaula integer NOT NULL,
    data date NOT NULL,
    fasciaoraria character varying(64) NOT NULL,
    numeroseduta integer,
    annoaccademico character varying(64)
);


ALTER TABLE prenotazionisedutedilaurea OWNER TO postgres;

--
-- Name: prenotazionitestdingresso; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE prenotazionitestdingresso (
    idaula integer NOT NULL,
    data date NOT NULL,
    fasciaoraria character varying(64) NOT NULL
);


ALTER TABLE prenotazionitestdingresso OWNER TO postgres;

--
-- Name: sessione; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE sessione (
    datainizio date NOT NULL,
    datafine date NOT NULL,
    tipologia character varying(64) NOT NULL,
    anno character varying(32) NOT NULL
);


ALTER TABLE sessione OWNER TO postgres;

--
-- Name: utente; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE utente (
    name character varying(64),
    username character varying(64) NOT NULL,
    password character varying(64),
    type character varying(64),
    CONSTRAINT utente_type_check CHECK ((((type)::text = 'Professore'::text) OR ((type)::text = 'Segretario'::text)))
);


ALTER TABLE utente OWNER TO postgres;

--
-- Data for Name: annoaccademico; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY annoaccademico (anno, datainizio, datafine) FROM stdin;
\.


--
-- Data for Name: appello; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY appello (datainizio, datafine, numero, anno, datainiziosessione) FROM stdin;
\.


--
-- Data for Name: aula; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY aula (id, lavagna, microfono, numero_posti, proiettore, ethernet, lavagnainterattiva, presecorrente, tipo) FROM stdin;
2	\N	\N	111	\N	\N	\N	\N	\N
1	\N	\N	22	\N	\N	\N	\N	\N
\.


--
-- Data for Name: prenotazione; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY prenotazione (idaula, data, fasciaoraria, username) FROM stdin;
2	2018-02-09	prima	 
2	2018-02-10	prima	 
2	2018-02-11	prima	 
2	2018-02-12	prima	 
2	2018-02-13	prima	 
2	2018-02-14	prima	 
2	2018-02-15	prima	 
2	2018-02-16	prima	 
2	2018-02-17	prima	 
2	2018-02-18	prima	 
2	2018-02-19	prima	 
2	2018-02-20	prima	 
2	2018-02-21	prima	 
2	2018-02-22	prima	 
2	2018-02-23	prima	 
2	2018-02-24	prima	 
2	2018-02-25	prima	 
2	2018-02-26	prima	 
2	2018-02-27	prima	 
2	2018-02-28	prima	 
\.


--
-- Data for Name: prenotazioneconferenza; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY prenotazioneconferenza (idaula, data, fasciaoraria, titoloevento, annoaccademico) FROM stdin;
2	2018-02-09	prima	 	\N
2	2018-02-10	prima	 	\N
2	2018-02-11	prima	 	\N
2	2018-02-12	prima	 	\N
2	2018-02-13	prima	 	\N
2	2018-02-14	prima	 	\N
2	2018-02-15	prima	 	\N
2	2018-02-16	prima	 	\N
2	2018-02-17	prima	 	\N
2	2018-02-18	prima	 	\N
2	2018-02-19	prima	 	\N
2	2018-02-20	prima	 	\N
2	2018-02-21	prima	 	\N
2	2018-02-22	prima	 	\N
2	2018-02-23	prima	 	\N
2	2018-02-24	prima	 	\N
2	2018-02-25	prima	 	\N
2	2018-02-26	prima	 	\N
2	2018-02-27	prima	 	\N
2	2018-02-28	prima	 	\N
\.


--
-- Data for Name: prenotazionedidattica; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY prenotazionedidattica (idaula, data, fasciaoraria, nomecorso, tiposeduta, datainizioappello, annoaccademico) FROM stdin;
\.


--
-- Data for Name: prenotazionisedutedilaurea; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY prenotazionisedutedilaurea (idaula, data, fasciaoraria, numeroseduta, annoaccademico) FROM stdin;
\.


--
-- Data for Name: prenotazionitestdingresso; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY prenotazionitestdingresso (idaula, data, fasciaoraria) FROM stdin;
\.


--
-- Data for Name: sessione; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY sessione (datainizio, datafine, tipologia, anno) FROM stdin;
\.


--
-- Data for Name: utente; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY utente (name, username, password, type) FROM stdin;
a	a	a	Professore
deAngelisMerdaSeccaAmbulante	 	 	Professore
\.


--
-- Name: aula Aula_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY aula
    ADD CONSTRAINT "Aula_pkey" PRIMARY KEY (id);


--
-- Name: annoaccademico annoaccademico_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY annoaccademico
    ADD CONSTRAINT annoaccademico_pkey PRIMARY KEY (anno);


--
-- Name: appello appello_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY appello
    ADD CONSTRAINT appello_pkey PRIMARY KEY (datainizio);


--
-- Name: prenotazione prenotazione_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY prenotazione
    ADD CONSTRAINT prenotazione_pkey PRIMARY KEY (idaula, data, fasciaoraria);


--
-- Name: prenotazioneconferenza prenotazioneconvegni_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY prenotazioneconferenza
    ADD CONSTRAINT prenotazioneconvegni_pkey PRIMARY KEY (idaula, data, fasciaoraria);


--
-- Name: prenotazionedidattica prenotazionedidattica_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY prenotazionedidattica
    ADD CONSTRAINT prenotazionedidattica_pkey PRIMARY KEY (idaula, data, fasciaoraria);


--
-- Name: prenotazionisedutedilaurea prenotazionisedutedilaurea_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY prenotazionisedutedilaurea
    ADD CONSTRAINT prenotazionisedutedilaurea_pkey PRIMARY KEY (idaula, data, fasciaoraria);


--
-- Name: prenotazionitestdingresso prenotazionitestdingresso_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY prenotazionitestdingresso
    ADD CONSTRAINT prenotazionitestdingresso_pkey PRIMARY KEY (idaula, data, fasciaoraria);


--
-- Name: sessione sessione_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sessione
    ADD CONSTRAINT sessione_pkey PRIMARY KEY (datainizio, anno);


--
-- Name: utente utente_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY utente
    ADD CONSTRAINT utente_pkey PRIMARY KEY (username);


--
-- Name: appello appello_datainiziosessione_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY appello
    ADD CONSTRAINT appello_datainiziosessione_fkey FOREIGN KEY (datainiziosessione, anno) REFERENCES sessione(datainizio, anno);


--
-- Name: prenotazioneconferenza prenotazioneconvegni_idaula_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY prenotazioneconferenza
    ADD CONSTRAINT prenotazioneconvegni_idaula_fkey FOREIGN KEY (idaula, data, fasciaoraria) REFERENCES prenotazione(idaula, data, fasciaoraria) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: prenotazionedidattica prenotazionedidattica_datainizioappello_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY prenotazionedidattica
    ADD CONSTRAINT prenotazionedidattica_datainizioappello_fkey FOREIGN KEY (datainizioappello) REFERENCES appello(datainizio);


--
-- Name: prenotazionedidattica prenotazionedidattica_idaula_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY prenotazionedidattica
    ADD CONSTRAINT prenotazionedidattica_idaula_fkey FOREIGN KEY (idaula, data, fasciaoraria) REFERENCES prenotazione(idaula, data, fasciaoraria) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: prenotazionisedutedilaurea prenotazionisedutedilaurea_idaula_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY prenotazionisedutedilaurea
    ADD CONSTRAINT prenotazionisedutedilaurea_idaula_fkey FOREIGN KEY (idaula, data, fasciaoraria) REFERENCES prenotazione(idaula, data, fasciaoraria) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: prenotazionitestdingresso prenotazionitestdingresso_idaula_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY prenotazionitestdingresso
    ADD CONSTRAINT prenotazionitestdingresso_idaula_fkey FOREIGN KEY (idaula, data, fasciaoraria) REFERENCES prenotazione(idaula, data, fasciaoraria) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: sessione sessione_anno_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sessione
    ADD CONSTRAINT sessione_anno_fkey FOREIGN KEY (anno) REFERENCES annoaccademico(anno);


--
-- PostgreSQL database dump complete
--

