--
-- PostgreSQL database dump
--

-- Dumped from database version 9.3.5
-- Dumped by pg_dump version 9.3.5
-- Started on 2015-06-10 14:23:20

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 203 (class 3079 OID 11750)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2151 (class 0 OID 0)
-- Dependencies: 203
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 171 (class 1259 OID 5511820)
-- Name: databasechangelog; Type: TABLE; Schema: public; Owner: wzpn; Tablespace: 
--

CREATE TABLE databasechangelog (
    id character varying(255) NOT NULL,
    author character varying(255) NOT NULL,
    filename character varying(255) NOT NULL,
    dateexecuted timestamp without time zone NOT NULL,
    orderexecuted integer NOT NULL,
    exectype character varying(10) NOT NULL,
    md5sum character varying(35),
    description character varying(255),
    comments character varying(255),
    tag character varying(255),
    liquibase character varying(20)
);


ALTER TABLE public.databasechangelog OWNER TO wzpn;

--
-- TOC entry 170 (class 1259 OID 5511815)
-- Name: databasechangeloglock; Type: TABLE; Schema: public; Owner: wzpn; Tablespace: 
--

CREATE TABLE databasechangeloglock (
    id integer NOT NULL,
    locked boolean NOT NULL,
    lockgranted timestamp without time zone,
    lockedby character varying(255)
);


ALTER TABLE public.databasechangeloglock OWNER TO wzpn;

--
-- TOC entry 184 (class 1259 OID 5511915)
-- Name: druzyna; Type: TABLE; Schema: public; Owner: wzpn; Tablespace: 
--

CREATE TABLE druzyna (
    id bigint NOT NULL,
    nazwa character varying(255),
    usunieta boolean,
    zawieszona boolean,
    prezes character varying(255),
    adres character varying(255),
    telefon character varying(255),
    email character varying(255),
    strona character varying(255),
    trenerzy character varying(255),
    opis character varying(255),
    liga_id bigint
);


ALTER TABLE public.druzyna OWNER TO wzpn;

--
-- TOC entry 183 (class 1259 OID 5511913)
-- Name: druzyna_id_seq; Type: SEQUENCE; Schema: public; Owner: wzpn
--

CREATE SEQUENCE druzyna_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.druzyna_id_seq OWNER TO wzpn;

--
-- TOC entry 2152 (class 0 OID 0)
-- Dependencies: 183
-- Name: druzyna_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: wzpn
--

ALTER SEQUENCE druzyna_id_seq OWNED BY druzyna.id;


--
-- TOC entry 196 (class 1259 OID 5512008)
-- Name: faktura; Type: TABLE; Schema: public; Owner: wzpn; Tablespace: 
--

CREATE TABLE faktura (
    id bigint NOT NULL,
    nazwa character varying(255),
    data_wystawienia timestamp without time zone,
    data_sprzedazy timestamp without time zone,
    miejscowosc character varying(255),
    sprzedawca_id bigint,
    nabywca_id bigint
);


ALTER TABLE public.faktura OWNER TO wzpn;

--
-- TOC entry 195 (class 1259 OID 5512006)
-- Name: faktura_id_seq; Type: SEQUENCE; Schema: public; Owner: wzpn
--

CREATE SEQUENCE faktura_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.faktura_id_seq OWNER TO wzpn;

--
-- TOC entry 2153 (class 0 OID 0)
-- Dependencies: 195
-- Name: faktura_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: wzpn
--

ALTER SEQUENCE faktura_id_seq OWNED BY faktura.id;


--
-- TOC entry 190 (class 1259 OID 5511955)
-- Name: grafik; Type: TABLE; Schema: public; Owner: wzpn; Tablespace: 
--

CREATE TABLE grafik (
    id bigint NOT NULL,
    nazwa character varying(255),
    opis character varying(255),
    rozpoczecie timestamp without time zone,
    pierwszadruzyna_id bigint,
    drugadruzyna_id bigint,
    obiektsportowy_id bigint
);


ALTER TABLE public.grafik OWNER TO wzpn;

--
-- TOC entry 189 (class 1259 OID 5511953)
-- Name: grafik_id_seq; Type: SEQUENCE; Schema: public; Owner: wzpn
--

CREATE SEQUENCE grafik_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.grafik_id_seq OWNER TO wzpn;

--
-- TOC entry 2154 (class 0 OID 0)
-- Dependencies: 189
-- Name: grafik_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: wzpn
--

ALTER SEQUENCE grafik_id_seq OWNED BY grafik.id;


--
-- TOC entry 172 (class 1259 OID 5511826)
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: wzpn
--

CREATE SEQUENCE hibernate_sequence
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.hibernate_sequence OWNER TO wzpn;

--
-- TOC entry 175 (class 1259 OID 5511843)
-- Name: jhi_authority; Type: TABLE; Schema: public; Owner: wzpn; Tablespace: 
--

CREATE TABLE jhi_authority (
    name character varying(50) NOT NULL
);


ALTER TABLE public.jhi_authority OWNER TO wzpn;

--
-- TOC entry 179 (class 1259 OID 5511878)
-- Name: jhi_persistent_audit_event; Type: TABLE; Schema: public; Owner: wzpn; Tablespace: 
--

CREATE TABLE jhi_persistent_audit_event (
    event_id bigint NOT NULL,
    principal character varying(255) NOT NULL,
    event_date timestamp without time zone,
    event_type character varying(255)
);


ALTER TABLE public.jhi_persistent_audit_event OWNER TO wzpn;

--
-- TOC entry 180 (class 1259 OID 5511887)
-- Name: jhi_persistent_audit_event_data; Type: TABLE; Schema: public; Owner: wzpn; Tablespace: 
--

CREATE TABLE jhi_persistent_audit_event_data (
    event_id bigint NOT NULL,
    name character varying(255) NOT NULL,
    value character varying(255)
);


ALTER TABLE public.jhi_persistent_audit_event_data OWNER TO wzpn;

--
-- TOC entry 178 (class 1259 OID 5511876)
-- Name: jhi_persistent_audit_event_event_id_seq; Type: SEQUENCE; Schema: public; Owner: wzpn
--

CREATE SEQUENCE jhi_persistent_audit_event_event_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.jhi_persistent_audit_event_event_id_seq OWNER TO wzpn;

--
-- TOC entry 2155 (class 0 OID 0)
-- Dependencies: 178
-- Name: jhi_persistent_audit_event_event_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: wzpn
--

ALTER SEQUENCE jhi_persistent_audit_event_event_id_seq OWNED BY jhi_persistent_audit_event.event_id;


--
-- TOC entry 177 (class 1259 OID 5511853)
-- Name: jhi_persistent_token; Type: TABLE; Schema: public; Owner: wzpn; Tablespace: 
--

CREATE TABLE jhi_persistent_token (
    series character varying(255) NOT NULL,
    user_id bigint,
    token_value character varying(255) NOT NULL,
    token_date date,
    ip_address character varying(39),
    user_agent character varying(255)
);


ALTER TABLE public.jhi_persistent_token OWNER TO wzpn;

--
-- TOC entry 174 (class 1259 OID 5511830)
-- Name: jhi_user; Type: TABLE; Schema: public; Owner: wzpn; Tablespace: 
--

CREATE TABLE jhi_user (
    id bigint NOT NULL,
    login character varying(50) NOT NULL,
    password character varying(100),
    first_name character varying(50),
    last_name character varying(50),
    email character varying(100),
    activated boolean NOT NULL,
    lang_key character varying(5),
    activation_key character varying(20),
    created_by character varying(50) NOT NULL,
    created_date timestamp without time zone DEFAULT now() NOT NULL,
    last_modified_by character varying(50),
    last_modified_date timestamp without time zone
);


ALTER TABLE public.jhi_user OWNER TO wzpn;

--
-- TOC entry 176 (class 1259 OID 5511848)
-- Name: jhi_user_authority; Type: TABLE; Schema: public; Owner: wzpn; Tablespace: 
--

CREATE TABLE jhi_user_authority (
    user_id bigint NOT NULL,
    authority_name character varying(50) NOT NULL
);


ALTER TABLE public.jhi_user_authority OWNER TO wzpn;

--
-- TOC entry 173 (class 1259 OID 5511828)
-- Name: jhi_user_id_seq; Type: SEQUENCE; Schema: public; Owner: wzpn
--

CREATE SEQUENCE jhi_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.jhi_user_id_seq OWNER TO wzpn;

--
-- TOC entry 2156 (class 0 OID 0)
-- Dependencies: 173
-- Name: jhi_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: wzpn
--

ALTER SEQUENCE jhi_user_id_seq OWNED BY jhi_user.id;


--
-- TOC entry 186 (class 1259 OID 5511931)
-- Name: kara; Type: TABLE; Schema: public; Owner: wzpn; Tablespace: 
--

CREATE TABLE kara (
    id bigint NOT NULL,
    data_otrzymania timestamp without time zone,
    powod character varying(255),
    kwota numeric(10,2),
    druzyna_id bigint
);


ALTER TABLE public.kara OWNER TO wzpn;

--
-- TOC entry 185 (class 1259 OID 5511929)
-- Name: kara_id_seq; Type: SEQUENCE; Schema: public; Owner: wzpn
--

CREATE SEQUENCE kara_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.kara_id_seq OWNER TO wzpn;

--
-- TOC entry 2157 (class 0 OID 0)
-- Dependencies: 185
-- Name: kara_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: wzpn
--

ALTER SEQUENCE kara_id_seq OWNED BY kara.id;


--
-- TOC entry 182 (class 1259 OID 5511904)
-- Name: liga; Type: TABLE; Schema: public; Owner: wzpn; Tablespace: 
--

CREATE TABLE liga (
    id bigint NOT NULL,
    nazwa character varying(255),
    opis character varying(255)
);


ALTER TABLE public.liga OWNER TO wzpn;

--
-- TOC entry 181 (class 1259 OID 5511902)
-- Name: liga_id_seq; Type: SEQUENCE; Schema: public; Owner: wzpn
--

CREATE SEQUENCE liga_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.liga_id_seq OWNER TO wzpn;

--
-- TOC entry 2158 (class 0 OID 0)
-- Dependencies: 181
-- Name: liga_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: wzpn
--

ALTER SEQUENCE liga_id_seq OWNED BY liga.id;


--
-- TOC entry 188 (class 1259 OID 5511944)
-- Name: obiektsportowy; Type: TABLE; Schema: public; Owner: wzpn; Tablespace: 
--

CREATE TABLE obiektsportowy (
    id bigint NOT NULL,
    nazwa character varying(255),
    aktywny boolean,
    miasto character varying(255),
    kod_pocztowy character varying(255),
    ulica character varying(255),
    telefon character varying(255),
    prezes character varying(255),
    strona character varying(255),
    miejsca_siedzace integer,
    miejsca_stojace integer,
    kryty boolean,
    oswietlenie boolean,
    wymiary character varying(255)
);


ALTER TABLE public.obiektsportowy OWNER TO wzpn;

--
-- TOC entry 187 (class 1259 OID 5511942)
-- Name: obiektsportowy_id_seq; Type: SEQUENCE; Schema: public; Owner: wzpn
--

CREATE SEQUENCE obiektsportowy_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.obiektsportowy_id_seq OWNER TO wzpn;

--
-- TOC entry 2159 (class 0 OID 0)
-- Dependencies: 187
-- Name: obiektsportowy_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: wzpn
--

ALTER SEQUENCE obiektsportowy_id_seq OWNED BY obiektsportowy.id;


--
-- TOC entry 202 (class 1259 OID 5512050)
-- Name: poleceniezaplaty; Type: TABLE; Schema: public; Owner: wzpn; Tablespace: 
--

CREATE TABLE poleceniezaplaty (
    id bigint NOT NULL,
    nazwa_odbiorcy character varying(255),
    nazwa_zleceniodawcy character varying(255),
    rachunek character varying(255),
    tytul character varying(255),
    kwota numeric(10,2)
);


ALTER TABLE public.poleceniezaplaty OWNER TO wzpn;

--
-- TOC entry 201 (class 1259 OID 5512048)
-- Name: poleceniezaplaty_id_seq; Type: SEQUENCE; Schema: public; Owner: wzpn
--

CREATE SEQUENCE poleceniezaplaty_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.poleceniezaplaty_id_seq OWNER TO wzpn;

--
-- TOC entry 2160 (class 0 OID 0)
-- Dependencies: 201
-- Name: poleceniezaplaty_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: wzpn
--

ALTER SEQUENCE poleceniezaplaty_id_seq OWNED BY poleceniezaplaty.id;


--
-- TOC entry 198 (class 1259 OID 5512029)
-- Name: pozycjafaktury; Type: TABLE; Schema: public; Owner: wzpn; Tablespace: 
--

CREATE TABLE pozycjafaktury (
    id bigint NOT NULL,
    nazwa character varying(255),
    brutto numeric(10,2),
    ilosc numeric(10,2),
    podatek integer,
    faktura_id bigint
);


ALTER TABLE public.pozycjafaktury OWNER TO wzpn;

--
-- TOC entry 197 (class 1259 OID 5512027)
-- Name: pozycjafaktury_id_seq; Type: SEQUENCE; Schema: public; Owner: wzpn
--

CREATE SEQUENCE pozycjafaktury_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.pozycjafaktury_id_seq OWNER TO wzpn;

--
-- TOC entry 2161 (class 0 OID 0)
-- Dependencies: 197
-- Name: pozycjafaktury_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: wzpn
--

ALTER SEQUENCE pozycjafaktury_id_seq OWNED BY pozycjafaktury.id;


--
-- TOC entry 194 (class 1259 OID 5511997)
-- Name: przedsiebiorca; Type: TABLE; Schema: public; Owner: wzpn; Tablespace: 
--

CREATE TABLE przedsiebiorca (
    id bigint NOT NULL,
    nazwa character varying(255),
    adres character varying(255),
    miejscowosc character varying(255),
    kod_pocztowy character varying(255),
    nip character varying(255),
    email character varying(255),
    telefon character varying(255)
);


ALTER TABLE public.przedsiebiorca OWNER TO wzpn;

--
-- TOC entry 193 (class 1259 OID 5511995)
-- Name: przedsiebiorca_id_seq; Type: SEQUENCE; Schema: public; Owner: wzpn
--

CREATE SEQUENCE przedsiebiorca_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.przedsiebiorca_id_seq OWNER TO wzpn;

--
-- TOC entry 2162 (class 0 OID 0)
-- Dependencies: 193
-- Name: przedsiebiorca_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: wzpn
--

ALTER SEQUENCE przedsiebiorca_id_seq OWNED BY przedsiebiorca.id;


--
-- TOC entry 200 (class 1259 OID 5512042)
-- Name: usluga; Type: TABLE; Schema: public; Owner: wzpn; Tablespace: 
--

CREATE TABLE usluga (
    id bigint NOT NULL,
    nazwa character varying(255),
    cena numeric(10,2),
    podatek integer,
    usunieta boolean
);


ALTER TABLE public.usluga OWNER TO wzpn;

--
-- TOC entry 199 (class 1259 OID 5512040)
-- Name: usluga_id_seq; Type: SEQUENCE; Schema: public; Owner: wzpn
--

CREATE SEQUENCE usluga_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.usluga_id_seq OWNER TO wzpn;

--
-- TOC entry 2163 (class 0 OID 0)
-- Dependencies: 199
-- Name: usluga_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: wzpn
--

ALTER SEQUENCE usluga_id_seq OWNED BY usluga.id;


--
-- TOC entry 192 (class 1259 OID 5511981)
-- Name: wynik; Type: TABLE; Schema: public; Owner: wzpn; Tablespace: 
--

CREATE TABLE wynik (
    id bigint NOT NULL,
    wynik character varying(255),
    bramki_pierwszej character varying(255),
    bramki_drugiej character varying(255),
    zolte_pierwszej character varying(255),
    zolte_drugiej character varying(255),
    czerwone_pierwszej character varying(255),
    czerwone_drugiej character varying(255),
    uwagi character varying(255),
    grafik_id bigint
);


ALTER TABLE public.wynik OWNER TO wzpn;

--
-- TOC entry 191 (class 1259 OID 5511979)
-- Name: wynik_id_seq; Type: SEQUENCE; Schema: public; Owner: wzpn
--

CREATE SEQUENCE wynik_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.wynik_id_seq OWNER TO wzpn;

--
-- TOC entry 2164 (class 0 OID 0)
-- Dependencies: 191
-- Name: wynik_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: wzpn
--

ALTER SEQUENCE wynik_id_seq OWNED BY wynik.id;


--
-- TOC entry 1937 (class 2604 OID 5511918)
-- Name: id; Type: DEFAULT; Schema: public; Owner: wzpn
--

ALTER TABLE ONLY druzyna ALTER COLUMN id SET DEFAULT nextval('druzyna_id_seq'::regclass);


--
-- TOC entry 1943 (class 2604 OID 5512011)
-- Name: id; Type: DEFAULT; Schema: public; Owner: wzpn
--

ALTER TABLE ONLY faktura ALTER COLUMN id SET DEFAULT nextval('faktura_id_seq'::regclass);


--
-- TOC entry 1940 (class 2604 OID 5511958)
-- Name: id; Type: DEFAULT; Schema: public; Owner: wzpn
--

ALTER TABLE ONLY grafik ALTER COLUMN id SET DEFAULT nextval('grafik_id_seq'::regclass);


--
-- TOC entry 1935 (class 2604 OID 5511881)
-- Name: event_id; Type: DEFAULT; Schema: public; Owner: wzpn
--

ALTER TABLE ONLY jhi_persistent_audit_event ALTER COLUMN event_id SET DEFAULT nextval('jhi_persistent_audit_event_event_id_seq'::regclass);


--
-- TOC entry 1933 (class 2604 OID 5511833)
-- Name: id; Type: DEFAULT; Schema: public; Owner: wzpn
--

ALTER TABLE ONLY jhi_user ALTER COLUMN id SET DEFAULT nextval('jhi_user_id_seq'::regclass);


--
-- TOC entry 1938 (class 2604 OID 5511934)
-- Name: id; Type: DEFAULT; Schema: public; Owner: wzpn
--

ALTER TABLE ONLY kara ALTER COLUMN id SET DEFAULT nextval('kara_id_seq'::regclass);


--
-- TOC entry 1936 (class 2604 OID 5511907)
-- Name: id; Type: DEFAULT; Schema: public; Owner: wzpn
--

ALTER TABLE ONLY liga ALTER COLUMN id SET DEFAULT nextval('liga_id_seq'::regclass);


--
-- TOC entry 1939 (class 2604 OID 5511947)
-- Name: id; Type: DEFAULT; Schema: public; Owner: wzpn
--

ALTER TABLE ONLY obiektsportowy ALTER COLUMN id SET DEFAULT nextval('obiektsportowy_id_seq'::regclass);


--
-- TOC entry 1946 (class 2604 OID 5512053)
-- Name: id; Type: DEFAULT; Schema: public; Owner: wzpn
--

ALTER TABLE ONLY poleceniezaplaty ALTER COLUMN id SET DEFAULT nextval('poleceniezaplaty_id_seq'::regclass);


--
-- TOC entry 1944 (class 2604 OID 5512032)
-- Name: id; Type: DEFAULT; Schema: public; Owner: wzpn
--

ALTER TABLE ONLY pozycjafaktury ALTER COLUMN id SET DEFAULT nextval('pozycjafaktury_id_seq'::regclass);


--
-- TOC entry 1942 (class 2604 OID 5512000)
-- Name: id; Type: DEFAULT; Schema: public; Owner: wzpn
--

ALTER TABLE ONLY przedsiebiorca ALTER COLUMN id SET DEFAULT nextval('przedsiebiorca_id_seq'::regclass);


--
-- TOC entry 1945 (class 2604 OID 5512045)
-- Name: id; Type: DEFAULT; Schema: public; Owner: wzpn
--

ALTER TABLE ONLY usluga ALTER COLUMN id SET DEFAULT nextval('usluga_id_seq'::regclass);


--
-- TOC entry 1941 (class 2604 OID 5511984)
-- Name: id; Type: DEFAULT; Schema: public; Owner: wzpn
--

ALTER TABLE ONLY wynik ALTER COLUMN id SET DEFAULT nextval('wynik_id_seq'::regclass);


--
-- TOC entry 2112 (class 0 OID 5511820)
-- Dependencies: 171
-- Data for Name: databasechangelog; Type: TABLE DATA; Schema: public; Owner: wzpn
--

COPY databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase) FROM stdin;
00000000000000	jhipster	classpath:config/liquibase/changelog/00000000000000_initial_schema.xml	2015-05-13 16:20:17.412	1	EXECUTED	7:eda8cd7fd15284e6128be97bd8edea82	createSequence		\N	3.3.2
00000000000001	jhipster	classpath:config/liquibase/changelog/00000000000000_initial_schema.xml	2015-05-13 16:20:18.914	2	EXECUTED	7:9ab74e85122cc7544c0277ee7a7f86dc	createTable, createIndex (x2), createTable (x2), addPrimaryKey, createTable, addForeignKeyConstraint (x3), loadData, dropDefaultValue, loadData (x2), createTable (x2), addPrimaryKey, createIndex (x2), addForeignKeyConstraint		\N	3.3.2
20150505215012	jhipster	classpath:config/liquibase/changelog/20150505215012_added_entity_Liga.xml	2015-05-13 16:20:19.12	3	EXECUTED	7:05f22a8677fdd09979c1b357f51a9950	createTable		\N	3.3.2
20150505215828	jhipster	classpath:config/liquibase/changelog/20150505215828_added_entity_Druzyna.xml	2015-05-13 16:20:19.27	4	EXECUTED	7:ce296331b8288b13740039c2409bd8ff	createTable, addForeignKeyConstraint		\N	3.3.2
20150505220020	jhipster	classpath:config/liquibase/changelog/20150505220020_added_entity_Kara.xml	2015-05-13 16:20:19.374	5	EXECUTED	7:91b93f6661abac49e1fbafa1d2eb49e9	createTable, dropDefaultValue, addForeignKeyConstraint		\N	3.3.2
20150505220328	jhipster	classpath:config/liquibase/changelog/20150505220328_added_entity_ObiektSportowy.xml	2015-05-13 16:20:19.554	6	EXECUTED	7:24da8b4282de6d4bea35da17325dc805	createTable		\N	3.3.2
20150505221048	jhipster	classpath:config/liquibase/changelog/20150505221048_added_entity_Grafik.xml	2015-05-13 16:20:19.752	7	EXECUTED	7:3a374269c9ffc553964540702aef4992	createTable, dropDefaultValue, addForeignKeyConstraint (x3)		\N	3.3.2
20150505221716	jhipster	classpath:config/liquibase/changelog/20150505221716_added_entity_Wynik.xml	2015-05-13 16:20:19.944	8	EXECUTED	7:955914954fb1c3ff07cfd77edd5ebaf4	createTable, addForeignKeyConstraint		\N	3.3.2
20150505230422	jhipster	classpath:config/liquibase/changelog/20150505230422_added_entity_Przedsiebiorca.xml	2015-05-13 16:20:20.147	9	EXECUTED	7:88215f55b92771bddc8b8ba5f22da43d	createTable		\N	3.3.2
20150505230641	jhipster	classpath:config/liquibase/changelog/20150505230641_added_entity_Faktura.xml	2015-05-13 16:20:20.332	10	EXECUTED	7:bef3b38b4a9b9762bd757556993e8962	createTable, dropDefaultValue (x2), addForeignKeyConstraint (x2)		\N	3.3.2
20150505230945	jhipster	classpath:config/liquibase/changelog/20150505230945_added_entity_PozycjaFaktury.xml	2015-05-13 16:20:20.429	11	EXECUTED	7:8b5d125e033aefcc9addc42f0605f3e5	createTable, addForeignKeyConstraint		\N	3.3.2
20150505231555	jhipster	classpath:config/liquibase/changelog/20150505231555_added_entity_Usluga.xml	2015-05-13 16:20:20.562	12	EXECUTED	7:8779d3e78eadc67f7ad634f6ed174da8	createTable		\N	3.3.2
20150505232407	jhipster	classpath:config/liquibase/changelog/20150505232407_added_entity_PolecenieZaplaty.xml	2015-05-13 16:20:20.701	13	EXECUTED	7:1f0de8002ddd33024f1cee936d2e3d4c	createTable		\N	3.3.2
20150511123100	qbisiak	classpath:config/liquibase/changelog/20150511123100_added_target_roles.xml	2015-05-13 16:20:20.813	14	EXECUTED	7:c735d5b52441dc20b4bdfda09e9779cf	addDefaultValue, loadData (x3)		\N	3.3.2
\.


--
-- TOC entry 2111 (class 0 OID 5511815)
-- Dependencies: 170
-- Data for Name: databasechangeloglock; Type: TABLE DATA; Schema: public; Owner: wzpn
--

COPY databasechangeloglock (id, locked, lockgranted, lockedby) FROM stdin;
1	f	\N	\N
\.


--
-- TOC entry 2125 (class 0 OID 5511915)
-- Dependencies: 184
-- Data for Name: druzyna; Type: TABLE DATA; Schema: public; Owner: wzpn
--

COPY druzyna (id, nazwa, usunieta, zawieszona, prezes, adres, telefon, email, strona, trenerzy, opis, liga_id) FROM stdin;
1063	Cisowianka Pabianice	\N	\N	Adam Rogowski	ul. Ulicowa 7, Pabianice	643426787	mail@pabianice.pl	www.cisowianka-klub.pl	Iwo Rostowski, Jerzy Gruszka	\N	1055
1064	GoSport Zduńska Wola	\N	\N	Piotr Marcinkiewicz	ul. Kwiecista, Zduńska Wola	512876876	klub@zdunska.pl	www.klub.zdunska.pl	Jan Kowalski	\N	1055
1066	PP Rakieta	\N	\N	Mikołaj Mrozowski	ul. Jana Pawła II, Piotrków Trybunalski	567444455	pilka@piotrkow.pl	\N	Edward Chłop	\N	1055
1065	Decathlon Sieradz	\N	\N	Mirek Łopata	ul. Solidarności 2, Sieradz	556987434	msksieradz@poczta.pl	www.msk.sieradz.pl	Henryk Popiełuszko	\N	1055
1062	Widzew	\N	\N	Marcin Dusza	ul. Piły 7, Łódź	67656577	sekretariat@widzew.pl	www.widzew.lodz.pl	Zbyszek Rudalski	\N	1055
1061	ŁKS	\N	\N	Jerzy Nowak	ul. Bratysławska 7, Łódź	765576576	prezes@lks.pl	www.lks.pl	Jerzy Nowak, Janusz Bródka	\N	1055
\.


--
-- TOC entry 2165 (class 0 OID 0)
-- Dependencies: 183
-- Name: druzyna_id_seq; Type: SEQUENCE SET; Schema: public; Owner: wzpn
--

SELECT pg_catalog.setval('druzyna_id_seq', 1, false);


--
-- TOC entry 2137 (class 0 OID 5512008)
-- Dependencies: 196
-- Data for Name: faktura; Type: TABLE DATA; Schema: public; Owner: wzpn
--

COPY faktura (id, nazwa, data_wystawienia, data_sprzedazy, miejscowosc, sprzedawca_id, nabywca_id) FROM stdin;
1035	FV/02/2015	2015-06-10 07:25:25	2015-06-10 07:25:25	Łódź	1033	1028
1036	FV/03/2015	2015-06-10 07:26:44.356	2015-06-10 07:26:44.356	Łódź	1033	1030
1037	FV/04/2015	2015-06-10 07:26:59.604	2015-06-10 07:26:59.604	Łódź	1033	1031
1038	FV/05/2015	2015-06-10 07:27:09	2015-06-10 07:27:09	Łódź	1033	1032
1039	FV/06/2015	2015-06-10 07:27:25.844	2015-06-10 07:27:25.844	Łódź	1033	1029
1034	FV/01/2015	2015-06-10 07:23:46	2015-06-10 07:23:46	Łódź	1033	1027
\.


--
-- TOC entry 2166 (class 0 OID 0)
-- Dependencies: 195
-- Name: faktura_id_seq; Type: SEQUENCE SET; Schema: public; Owner: wzpn
--

SELECT pg_catalog.setval('faktura_id_seq', 1, false);


--
-- TOC entry 2131 (class 0 OID 5511955)
-- Dependencies: 190
-- Data for Name: grafik; Type: TABLE DATA; Schema: public; Owner: wzpn
--

COPY grafik (id, nazwa, opis, rozpoczecie, pierwszadruzyna_id, drugadruzyna_id, obiektsportowy_id) FROM stdin;
1084	I runda, I mecz	Cisowianka kontra GoSport	2015-06-01 18:00:00	1063	1064	1072
1085	I runda, II mecz	ŁKS - Widzew	2015-06-02 18:00:00	1062	1061	1071
1086	I runda, III mecz	Decathlon - Rakieta	2015-06-03 18:00:00	1065	1066	1070
\.


--
-- TOC entry 2167 (class 0 OID 0)
-- Dependencies: 189
-- Name: grafik_id_seq; Type: SEQUENCE SET; Schema: public; Owner: wzpn
--

SELECT pg_catalog.setval('grafik_id_seq', 1, false);


--
-- TOC entry 2168 (class 0 OID 0)
-- Dependencies: 172
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: wzpn
--

SELECT pg_catalog.setval('hibernate_sequence', 1100, true);


--
-- TOC entry 2116 (class 0 OID 5511843)
-- Dependencies: 175
-- Data for Name: jhi_authority; Type: TABLE DATA; Schema: public; Owner: wzpn
--

COPY jhi_authority (name) FROM stdin;
ROLE_ADMIN
ROLE_USER
SEDZIA
WYDZIAL_GIER
ADMINISTRACJA
KSIEGOWOSC
SEKRETARIAT
\.


--
-- TOC entry 2120 (class 0 OID 5511878)
-- Dependencies: 179
-- Data for Name: jhi_persistent_audit_event; Type: TABLE DATA; Schema: public; Owner: wzpn
--

COPY jhi_persistent_audit_event (event_id, principal, event_date, event_type) FROM stdin;
1000	ksiegowy	2015-06-10 08:31:06.01	AUTHENTICATION_FAILURE
1001	ksiegowy	2015-06-10 08:31:11.181	AUTHENTICATION_SUCCESS
1002	ksiegowy	2015-06-10 08:31:11.191	AUTHENTICATION_SUCCESS
1003	ksiegowy	2015-06-10 08:31:11.2	AUTHENTICATION_SUCCESS
1004	sekretarz	2015-06-10 08:41:23.375	AUTHENTICATION_SUCCESS
1005	sekretarz	2015-06-10 08:41:23.397	AUTHENTICATION_SUCCESS
1006	sekretarz	2015-06-10 08:41:23.403	AUTHENTICATION_SUCCESS
1007	undefined	2015-06-10 08:43:06.956	AUTHENTICATION_FAILURE
1008	anomyousUser	2015-06-10 08:43:13.729	AUTHENTICATION_FAILURE
1009	sedzia	2015-06-10 09:03:07.094	AUTHENTICATION_SUCCESS
1010	sedzia	2015-06-10 09:03:07.376	AUTHENTICATION_SUCCESS
1011	sedzia	2015-06-10 09:03:08.773	AUTHENTICATION_SUCCESS
1012	sekretarz	2015-06-10 09:03:33.031	AUTHENTICATION_SUCCESS
1013	sekretarz	2015-06-10 09:03:33.059	AUTHENTICATION_SUCCESS
1014	sekretarz	2015-06-10 09:03:33.083	AUTHENTICATION_SUCCESS
1015	gry	2015-06-10 09:03:56.847	AUTHENTICATION_SUCCESS
1016	gry	2015-06-10 09:03:56.874	AUTHENTICATION_SUCCESS
1017	gry	2015-06-10 09:03:56.919	AUTHENTICATION_SUCCESS
1018	sekretarz	2015-06-10 09:06:39.843	AUTHENTICATION_SUCCESS
1019	sekretarz	2015-06-10 09:06:39.851	AUTHENTICATION_SUCCESS
1020	sekretarz	2015-06-10 09:06:39.868	AUTHENTICATION_SUCCESS
1021	sekretarz	2015-06-10 09:09:18.803	AUTHENTICATION_SUCCESS
1022	sekretarz	2015-06-10 09:09:18.911	AUTHENTICATION_SUCCESS
1023	sekretarz	2015-06-10 09:09:18.981	AUTHENTICATION_SUCCESS
1024	ksiegowy	2015-06-10 09:14:19.303	AUTHENTICATION_SUCCESS
1025	ksiegowy	2015-06-10 09:14:19.345	AUTHENTICATION_SUCCESS
1026	ksiegowy	2015-06-10 09:14:19.398	AUTHENTICATION_SUCCESS
1042	sekretarz	2015-06-10 09:29:13.076	AUTHENTICATION_SUCCESS
1043	sekretarz	2015-06-10 09:29:13.119	AUTHENTICATION_SUCCESS
1044	sekretarz	2015-06-10 09:29:13.137	AUTHENTICATION_SUCCESS
1045	sedzia	2015-06-10 09:35:18.305	AUTHENTICATION_SUCCESS
1046	sedzia	2015-06-10 09:35:18.361	AUTHENTICATION_SUCCESS
1047	sedzia	2015-06-10 09:35:18.398	AUTHENTICATION_SUCCESS
1048	gry	2015-06-10 12:11:07.074	AUTHENTICATION_SUCCESS
1049	gry	2015-06-10 12:11:07.434	AUTHENTICATION_SUCCESS
1050	gry	2015-06-10 12:11:07.539	AUTHENTICATION_SUCCESS
1051	gry	2015-06-10 12:16:54.77	AUTHENTICATION_FAILURE
1052	gry	2015-06-10 12:17:01.97	AUTHENTICATION_SUCCESS
1053	gry	2015-06-10 12:17:01.978	AUTHENTICATION_SUCCESS
1054	gry	2015-06-10 12:17:01.994	AUTHENTICATION_SUCCESS
1058	sekretarz	2015-06-10 12:19:03.556	AUTHENTICATION_SUCCESS
1059	sekretarz	2015-06-10 12:19:03.563	AUTHENTICATION_SUCCESS
1060	sekretarz	2015-06-10 12:19:03.577	AUTHENTICATION_SUCCESS
1067	administrator	2015-06-10 12:57:18.096	AUTHENTICATION_SUCCESS
1068	administrator	2015-06-10 12:57:18.103	AUTHENTICATION_SUCCESS
1069	administrator	2015-06-10 12:57:18.118	AUTHENTICATION_SUCCESS
1073	sekretarz	2015-06-10 13:36:55.186	AUTHENTICATION_SUCCESS
1074	sekretarz	2015-06-10 13:36:55.243	AUTHENTICATION_SUCCESS
1075	sekretarz	2015-06-10 13:36:55.26	AUTHENTICATION_SUCCESS
1076	sekretarz	2015-06-10 13:58:20.049	AUTHENTICATION_SUCCESS
1077	gry	2015-06-10 13:58:30.42	AUTHENTICATION_SUCCESS
1078	gry	2015-06-10 13:58:30.427	AUTHENTICATION_SUCCESS
1079	gry	2015-06-10 13:58:30.443	AUTHENTICATION_SUCCESS
1087	sekretarz	2015-06-10 14:07:51.749	AUTHENTICATION_SUCCESS
1088	sekretarz	2015-06-10 14:07:51.757	AUTHENTICATION_SUCCESS
1089	sekretarz	2015-06-10 14:07:51.772	AUTHENTICATION_SUCCESS
1090	ksiegowy	2015-06-10 14:08:23.514	AUTHENTICATION_SUCCESS
1091	ksiegowy	2015-06-10 14:08:23.521	AUTHENTICATION_SUCCESS
1092	ksiegowy	2015-06-10 14:08:23.559	AUTHENTICATION_SUCCESS
1095	sedzia	2015-06-10 14:13:09.312	AUTHENTICATION_SUCCESS
1096	sedzia	2015-06-10 14:13:09.319	AUTHENTICATION_SUCCESS
1097	sedzia	2015-06-10 14:13:09.333	AUTHENTICATION_SUCCESS
1098	sedzia	2015-06-10 14:16:39.739	AUTHENTICATION_SUCCESS
\.


--
-- TOC entry 2121 (class 0 OID 5511887)
-- Dependencies: 180
-- Data for Name: jhi_persistent_audit_event_data; Type: TABLE DATA; Schema: public; Owner: wzpn
--

COPY jhi_persistent_audit_event_data (event_id, name, value) FROM stdin;
1000	type	org.springframework.security.authentication.BadCredentialsException
1000	message	Bad credentials
1001	sessionId	AF2C22CBE7AD01FDDFC61CAB9E292AEE
1001	remoteAddress	0:0:0:0:0:0:0:1
1002	sessionId	AF2C22CBE7AD01FDDFC61CAB9E292AEE
1002	remoteAddress	0:0:0:0:0:0:0:1
1003	sessionId	AF2C22CBE7AD01FDDFC61CAB9E292AEE
1003	remoteAddress	0:0:0:0:0:0:0:1
1004	sessionId	1D86A7C818C967FD173545C3B34AB587
1004	remoteAddress	0:0:0:0:0:0:0:1
1005	sessionId	1D86A7C818C967FD173545C3B34AB587
1005	remoteAddress	0:0:0:0:0:0:0:1
1006	sessionId	1D86A7C818C967FD173545C3B34AB587
1006	remoteAddress	0:0:0:0:0:0:0:1
1007	type	org.springframework.security.authentication.BadCredentialsException
1007	message	Bad credentials
1008	type	org.springframework.security.authentication.BadCredentialsException
1008	message	Bad credentials
1009	sessionId	ADAC4CBA41800B429E61A5A25479890F
1009	remoteAddress	0:0:0:0:0:0:0:1
1010	sessionId	ADAC4CBA41800B429E61A5A25479890F
1010	remoteAddress	0:0:0:0:0:0:0:1
1011	sessionId	ADAC4CBA41800B429E61A5A25479890F
1011	remoteAddress	0:0:0:0:0:0:0:1
1012	sessionId	D8A76AF860D0AE2BA0AFB00769ACBDA6
1012	remoteAddress	0:0:0:0:0:0:0:1
1013	sessionId	D8A76AF860D0AE2BA0AFB00769ACBDA6
1013	remoteAddress	0:0:0:0:0:0:0:1
1014	sessionId	D8A76AF860D0AE2BA0AFB00769ACBDA6
1014	remoteAddress	0:0:0:0:0:0:0:1
1015	sessionId	356124AB5CBCAE7445CDB56EDCE4BF01
1015	remoteAddress	0:0:0:0:0:0:0:1
1016	sessionId	356124AB5CBCAE7445CDB56EDCE4BF01
1016	remoteAddress	0:0:0:0:0:0:0:1
1017	sessionId	356124AB5CBCAE7445CDB56EDCE4BF01
1017	remoteAddress	0:0:0:0:0:0:0:1
1018	sessionId	53DEFB05FA18F2B9D56E25F6E3801980
1018	remoteAddress	0:0:0:0:0:0:0:1
1019	sessionId	53DEFB05FA18F2B9D56E25F6E3801980
1019	remoteAddress	0:0:0:0:0:0:0:1
1020	sessionId	53DEFB05FA18F2B9D56E25F6E3801980
1020	remoteAddress	0:0:0:0:0:0:0:1
1021	sessionId	6F93A41BBDE9521221ADE28DC44568AC
1021	remoteAddress	0:0:0:0:0:0:0:1
1022	sessionId	6F93A41BBDE9521221ADE28DC44568AC
1022	remoteAddress	0:0:0:0:0:0:0:1
1023	sessionId	6F93A41BBDE9521221ADE28DC44568AC
1023	remoteAddress	0:0:0:0:0:0:0:1
1024	sessionId	55D4F9BFC5BE2184DA77C3E67FA97E3D
1024	remoteAddress	0:0:0:0:0:0:0:1
1025	sessionId	55D4F9BFC5BE2184DA77C3E67FA97E3D
1025	remoteAddress	0:0:0:0:0:0:0:1
1026	sessionId	55D4F9BFC5BE2184DA77C3E67FA97E3D
1026	remoteAddress	0:0:0:0:0:0:0:1
1042	sessionId	7CCD11C73EF1592CE29F61D5F04BA092
1042	remoteAddress	0:0:0:0:0:0:0:1
1043	sessionId	7CCD11C73EF1592CE29F61D5F04BA092
1043	remoteAddress	0:0:0:0:0:0:0:1
1044	sessionId	7CCD11C73EF1592CE29F61D5F04BA092
1044	remoteAddress	0:0:0:0:0:0:0:1
1045	sessionId	4747264F6F1FD35E5C955D6AA4513397
1045	remoteAddress	0:0:0:0:0:0:0:1
1046	sessionId	4747264F6F1FD35E5C955D6AA4513397
1046	remoteAddress	0:0:0:0:0:0:0:1
1047	sessionId	4747264F6F1FD35E5C955D6AA4513397
1047	remoteAddress	0:0:0:0:0:0:0:1
1048	sessionId	A7C75BBBDF5D363D30846024AFC052CB
1048	remoteAddress	212.51.220.159
1049	sessionId	A7C75BBBDF5D363D30846024AFC052CB
1049	remoteAddress	212.51.220.159
1050	sessionId	A7C75BBBDF5D363D30846024AFC052CB
1050	remoteAddress	212.51.220.159
1051	type	org.springframework.security.authentication.BadCredentialsException
1051	message	Bad credentials
1052	sessionId	A5FD5A541E0A6F785F2E5264139B63FC
1052	remoteAddress	212.51.220.159
1053	sessionId	A5FD5A541E0A6F785F2E5264139B63FC
1053	remoteAddress	212.51.220.159
1054	sessionId	A5FD5A541E0A6F785F2E5264139B63FC
1054	remoteAddress	212.51.220.159
1058	sessionId	7698AEE2ECBF33B31C91CF11F096FF01
1058	remoteAddress	212.51.220.159
1059	sessionId	7698AEE2ECBF33B31C91CF11F096FF01
1059	remoteAddress	212.51.220.159
1060	sessionId	7698AEE2ECBF33B31C91CF11F096FF01
1060	remoteAddress	212.51.220.159
1067	sessionId	2C87ADFD893207AD7D9F6AC489C531A4
1067	remoteAddress	212.51.220.159
1068	sessionId	2C87ADFD893207AD7D9F6AC489C531A4
1068	remoteAddress	212.51.220.159
1069	sessionId	2C87ADFD893207AD7D9F6AC489C531A4
1069	remoteAddress	212.51.220.159
1073	sessionId	FDA0FC5EC527FE0B8E0E9211C1591999
1073	remoteAddress	0:0:0:0:0:0:0:1
1074	sessionId	FDA0FC5EC527FE0B8E0E9211C1591999
1074	remoteAddress	0:0:0:0:0:0:0:1
1075	sessionId	FDA0FC5EC527FE0B8E0E9211C1591999
1075	remoteAddress	0:0:0:0:0:0:0:1
1076	sessionId	E5D8AE9FB1688DA2B6EB6AAFEB87959D
1076	remoteAddress	0:0:0:0:0:0:0:1
1077	sessionId	1363F82F7E85045FF9E86751559D4BCC
1077	remoteAddress	0:0:0:0:0:0:0:1
1078	sessionId	1363F82F7E85045FF9E86751559D4BCC
1078	remoteAddress	0:0:0:0:0:0:0:1
1079	sessionId	1363F82F7E85045FF9E86751559D4BCC
1079	remoteAddress	0:0:0:0:0:0:0:1
1087	sessionId	8F0D94A11B3D65091567C6A1F4F0EA28
1087	remoteAddress	0:0:0:0:0:0:0:1
1088	sessionId	8F0D94A11B3D65091567C6A1F4F0EA28
1088	remoteAddress	0:0:0:0:0:0:0:1
1089	sessionId	8F0D94A11B3D65091567C6A1F4F0EA28
1089	remoteAddress	0:0:0:0:0:0:0:1
1090	sessionId	0B2E6A902EE69A7C9F2E22A719783C6B
1090	remoteAddress	0:0:0:0:0:0:0:1
1091	sessionId	0B2E6A902EE69A7C9F2E22A719783C6B
1091	remoteAddress	0:0:0:0:0:0:0:1
1092	sessionId	0B2E6A902EE69A7C9F2E22A719783C6B
1092	remoteAddress	0:0:0:0:0:0:0:1
1095	sessionId	E6F842C132B9BA2B712902B71E50F535
1095	remoteAddress	0:0:0:0:0:0:0:1
1096	sessionId	E6F842C132B9BA2B712902B71E50F535
1096	remoteAddress	0:0:0:0:0:0:0:1
1097	sessionId	E6F842C132B9BA2B712902B71E50F535
1097	remoteAddress	0:0:0:0:0:0:0:1
1098	sessionId	249223591AE34D8747ECD2BBFAE5B66E
1098	remoteAddress	0:0:0:0:0:0:0:1
\.


--
-- TOC entry 2169 (class 0 OID 0)
-- Dependencies: 178
-- Name: jhi_persistent_audit_event_event_id_seq; Type: SEQUENCE SET; Schema: public; Owner: wzpn
--

SELECT pg_catalog.setval('jhi_persistent_audit_event_event_id_seq', 1, false);


--
-- TOC entry 2118 (class 0 OID 5511853)
-- Dependencies: 177
-- Data for Name: jhi_persistent_token; Type: TABLE DATA; Schema: public; Owner: wzpn
--

COPY jhi_persistent_token (series, user_id, token_value, token_date, ip_address, user_agent) FROM stdin;
JzucgpJAE1t965mo2v+Jjg==	7	GE3HbrTQHy7T5GQA0yx4qg==	2015-06-10	212.51.220.159	Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10) AppleWebKit/537.16 (KHTML, like Gecko) Version/8.0 Safari/537.16
+e9hcLhPWOVU7IgMfPjVag==	5	D1tKcb3sAGGKi+cxoOSVag==	2015-06-10	0:0:0:0:0:0:0:1	Mozilla/5.0 (Windows NT 6.3; WOW64; rv:38.0) Gecko/20100101 Firefox/38.0
\.


--
-- TOC entry 2115 (class 0 OID 5511830)
-- Dependencies: 174
-- Data for Name: jhi_user; Type: TABLE DATA; Schema: public; Owner: wzpn
--

COPY jhi_user (id, login, password, first_name, last_name, email, activated, lang_key, activation_key, created_by, created_date, last_modified_by, last_modified_date) FROM stdin;
1	system	$2a$10$mE.qmcV0mFU5NcKh73TZx.z4ueI/.bDWbj0T1BYyqP481kGGarKLG	System	System	system@localhost	t	en	\N	system	2015-05-13 16:20:17.556	\N	\N
2	anonymousUser	$2a$10$j8S5d7Sr7.8VTOYNviDPOeWX8KcYILUVJBsYV83Y5NtECayypx9lO	Anonymous	User	anonymous@localhost	t	en	\N	system	2015-05-13 16:20:17.556	\N	\N
3	admin	$2a$10$gSAhZrxMllrbgj/kkK9UceBPpChGWJA7SYIb1Mqo.n5aNLq1/oRrC	Administrator	Administrator	admin@localhost	t	en	\N	system	2015-05-13 16:20:17.556	\N	\N
4	user	$2a$10$VEjxo0jq2YG9Rbk2HmX9S.k1uZBGYUHdUcid3g/vfiEl7lwWgOH/K	User	User	user@localhost	t	en	\N	system	2015-05-13 16:20:17.556	\N	\N
5	sedzia	$2a$10$VEjxo0jq2YG9Rbk2HmX9S.k1uZBGYUHdUcid3g/vfiEl7lwWgOH/K	sedzia	sedzia	Sedzia@localhost	t	pl	\N	system	2015-05-13 16:20:20.713	\N	\N
6	gry	$2a$10$VEjxo0jq2YG9Rbk2HmX9S.k1uZBGYUHdUcid3g/vfiEl7lwWgOH/K	gry	gry	Gry@localhost	t	pl	\N	system	2015-05-13 16:20:20.713	\N	\N
7	administrator	$2a$10$VEjxo0jq2YG9Rbk2HmX9S.k1uZBGYUHdUcid3g/vfiEl7lwWgOH/K	administrator	administrator	Administrator@localhost	t	pl	\N	system	2015-05-13 16:20:20.713	\N	\N
8	ksiegowy	$2a$10$VEjxo0jq2YG9Rbk2HmX9S.k1uZBGYUHdUcid3g/vfiEl7lwWgOH/K	ksiegowy	ksiegowy	Ksiegowy@localhost	t	pl	\N	system	2015-05-13 16:20:20.713	\N	\N
9	sekretarz	$2a$10$VEjxo0jq2YG9Rbk2HmX9S.k1uZBGYUHdUcid3g/vfiEl7lwWgOH/K	sekretarz	sekretarz	Sekretarz@localhost	t	pl	\N	system	2015-05-13 16:20:20.713	\N	\N
\.


--
-- TOC entry 2117 (class 0 OID 5511848)
-- Dependencies: 176
-- Data for Name: jhi_user_authority; Type: TABLE DATA; Schema: public; Owner: wzpn
--

COPY jhi_user_authority (user_id, authority_name) FROM stdin;
1	ROLE_ADMIN
1	ROLE_USER
3	ROLE_ADMIN
3	ROLE_USER
4	ROLE_USER
5	ROLE_USER
6	ROLE_USER
7	ROLE_USER
8	ROLE_USER
9	ROLE_USER
5	SEDZIA
6	WYDZIAL_GIER
7	ADMINISTRACJA
8	KSIEGOWOSC
9	SEKRETARIAT
\.


--
-- TOC entry 2170 (class 0 OID 0)
-- Dependencies: 173
-- Name: jhi_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: wzpn
--

SELECT pg_catalog.setval('jhi_user_id_seq', 1, false);


--
-- TOC entry 2127 (class 0 OID 5511931)
-- Dependencies: 186
-- Data for Name: kara; Type: TABLE DATA; Schema: public; Owner: wzpn
--

COPY kara (id, data_otrzymania, powod, kwota, druzyna_id) FROM stdin;
1100	2015-06-05 20:00:00	Splunięcie na murawę boiska	6650.00	1061
1099	2015-06-07 20:00:00	Rozwiązane obuwie zawodnika	64300.00	1066
\.


--
-- TOC entry 2171 (class 0 OID 0)
-- Dependencies: 185
-- Name: kara_id_seq; Type: SEQUENCE SET; Schema: public; Owner: wzpn
--

SELECT pg_catalog.setval('kara_id_seq', 1, false);


--
-- TOC entry 2123 (class 0 OID 5511904)
-- Dependencies: 182
-- Data for Name: liga; Type: TABLE DATA; Schema: public; Owner: wzpn
--

COPY liga (id, nazwa, opis) FROM stdin;
1055	Ekstraklasa	\N
1056	Pierwsza liga	\N
1057	Druga liga	\N
\.


--
-- TOC entry 2172 (class 0 OID 0)
-- Dependencies: 181
-- Name: liga_id_seq; Type: SEQUENCE SET; Schema: public; Owner: wzpn
--

SELECT pg_catalog.setval('liga_id_seq', 1, false);


--
-- TOC entry 2129 (class 0 OID 5511944)
-- Dependencies: 188
-- Data for Name: obiektsportowy; Type: TABLE DATA; Schema: public; Owner: wzpn
--

COPY obiektsportowy (id, nazwa, aktywny, miasto, kod_pocztowy, ulica, telefon, prezes, strona, miejsca_siedzace, miejsca_stojace, kryty, oswietlenie, wymiary) FROM stdin;
1071	Stadion Widzewa	t	Łódź	92-654	ul. Niklowa 544	55765411	Tomasz Kurpaniuk	www.stadion.widzew.pl	200	100	\N	t	50x100
1070	Stadion ŁKS	t	Łódź	93-342	ul. Bratysławska 7	678764278	Gosia Storczyk	www.stadion.lks.pl	396	150	t	t	50x100
1072	Orlik w Sieradzu	t	Sieradz	96-456	ul. Wiejska 5	511456123	Agata Winiarska	\N	50	250	\N	\N	50x100
\.


--
-- TOC entry 2173 (class 0 OID 0)
-- Dependencies: 187
-- Name: obiektsportowy_id_seq; Type: SEQUENCE SET; Schema: public; Owner: wzpn
--

SELECT pg_catalog.setval('obiektsportowy_id_seq', 1, false);


--
-- TOC entry 2143 (class 0 OID 5512050)
-- Dependencies: 202
-- Data for Name: poleceniezaplaty; Type: TABLE DATA; Schema: public; Owner: wzpn
--

COPY poleceniezaplaty (id, nazwa_odbiorcy, nazwa_zleceniodawcy, rachunek, tytul, kwota) FROM stdin;
1093	Klub ŁKS	Wojewódzki Związek Piłki Nożnej	67 1234 5678 0000 0000 1234 5678	Wynajem stadionu - ŁKS	7300.00
1094	Ministerstwo Sportu i Turysryki RP	Wojewódzki Związek Piłki Nożnej	77 1010 1010 0400 1922 3100 0000	Opłata licencyjna 2015	2500.00
\.


--
-- TOC entry 2174 (class 0 OID 0)
-- Dependencies: 201
-- Name: poleceniezaplaty_id_seq; Type: SEQUENCE SET; Schema: public; Owner: wzpn
--

SELECT pg_catalog.setval('poleceniezaplaty_id_seq', 1, false);


--
-- TOC entry 2139 (class 0 OID 5512029)
-- Dependencies: 198
-- Data for Name: pozycjafaktury; Type: TABLE DATA; Schema: public; Owner: wzpn
--

COPY pozycjafaktury (id, nazwa, brutto, ilosc, podatek, faktura_id) FROM stdin;
1040	Rejestracja drużyny	500.00	1.00	23	1035
1041	Opłata członkowska	250.00	3.00	23	1035
\.


--
-- TOC entry 2175 (class 0 OID 0)
-- Dependencies: 197
-- Name: pozycjafaktury_id_seq; Type: SEQUENCE SET; Schema: public; Owner: wzpn
--

SELECT pg_catalog.setval('pozycjafaktury_id_seq', 1, false);


--
-- TOC entry 2135 (class 0 OID 5511997)
-- Dependencies: 194
-- Data for Name: przedsiebiorca; Type: TABLE DATA; Schema: public; Owner: wzpn
--

COPY przedsiebiorca (id, nazwa, adres, miejscowosc, kod_pocztowy, nip, email, telefon) FROM stdin;
1027	KKS Lech Ponzań	ul. Bułgarska 17	Poznań	60-320	7871938871	karol@lech.poznan.pl	634359262
1028	Legia Warszawa	ul. Łazienkowska 3	Warszawa	00-449	5261724308	jakub@legia.warszawa.pl	624842365
1029	Fly Emirates	ul. Spoczynkowa 14	Wrocław	65-233	4571945641	fly@emirates.com	564375165
1030	Amazon	ul. Mieczysława 689	Gdańsk	45-666	44932443	amazon@poczta.com	546132477
1031	Orange	ul. Bratysławska 41	Warszawa	22-455	76876432	klub@orange.pl	789465132
1032	Harnaś	ul. Browarna 2	Cieszyn	31-776	87412236	pilka@harnas.pl	624654321
1033	Wojewódzki Związek Piłki Nożnej	ul. Wólczańska 215	Łódź	93-519	43534231	poczta@p.lodz.pl	75657212
\.


--
-- TOC entry 2176 (class 0 OID 0)
-- Dependencies: 193
-- Name: przedsiebiorca_id_seq; Type: SEQUENCE SET; Schema: public; Owner: wzpn
--

SELECT pg_catalog.setval('przedsiebiorca_id_seq', 1, false);


--
-- TOC entry 2141 (class 0 OID 5512042)
-- Dependencies: 200
-- Data for Name: usluga; Type: TABLE DATA; Schema: public; Owner: wzpn
--

COPY usluga (id, nazwa, cena, podatek, usunieta) FROM stdin;
1080	Wpisowe	250.00	23	\N
1081	Opłata członkowska (1 rok)	500.00	23	\N
1082	Parking	20.00	23	\N
1083	Sprzęt meczowy	40.00	23	\N
\.


--
-- TOC entry 2177 (class 0 OID 0)
-- Dependencies: 199
-- Name: usluga_id_seq; Type: SEQUENCE SET; Schema: public; Owner: wzpn
--

SELECT pg_catalog.setval('usluga_id_seq', 1, false);


--
-- TOC entry 2133 (class 0 OID 5511981)
-- Dependencies: 192
-- Data for Name: wynik; Type: TABLE DATA; Schema: public; Owner: wzpn
--

COPY wynik (id, wynik, bramki_pierwszej, bramki_drugiej, zolte_pierwszej, zolte_drugiej, czerwone_pierwszej, czerwone_drugiej, uwagi, grafik_id) FROM stdin;
\.


--
-- TOC entry 2178 (class 0 OID 0)
-- Dependencies: 191
-- Name: wynik_id_seq; Type: SEQUENCE SET; Schema: public; Owner: wzpn
--

SELECT pg_catalog.setval('wynik_id_seq', 1, false);


--
-- TOC entry 1968 (class 2606 OID 5511894)
-- Name: jhi_persistent_audit_event_data_pkey; Type: CONSTRAINT; Schema: public; Owner: wzpn; Tablespace: 
--

ALTER TABLE ONLY jhi_persistent_audit_event_data
    ADD CONSTRAINT jhi_persistent_audit_event_data_pkey PRIMARY KEY (event_id, name);


--
-- TOC entry 1960 (class 2606 OID 5511852)
-- Name: jhi_user_authority_pkey; Type: CONSTRAINT; Schema: public; Owner: wzpn; Tablespace: 
--

ALTER TABLE ONLY jhi_user_authority
    ADD CONSTRAINT jhi_user_authority_pkey PRIMARY KEY (user_id, authority_name);


--
-- TOC entry 1952 (class 2606 OID 5511838)
-- Name: jhi_user_email_key; Type: CONSTRAINT; Schema: public; Owner: wzpn; Tablespace: 
--

ALTER TABLE ONLY jhi_user
    ADD CONSTRAINT jhi_user_email_key UNIQUE (email);


--
-- TOC entry 1954 (class 2606 OID 5511840)
-- Name: jhi_user_login_key; Type: CONSTRAINT; Schema: public; Owner: wzpn; Tablespace: 
--

ALTER TABLE ONLY jhi_user
    ADD CONSTRAINT jhi_user_login_key UNIQUE (login);


--
-- TOC entry 1948 (class 2606 OID 5511819)
-- Name: pk_databasechangeloglock; Type: CONSTRAINT; Schema: public; Owner: wzpn; Tablespace: 
--

ALTER TABLE ONLY databasechangeloglock
    ADD CONSTRAINT pk_databasechangeloglock PRIMARY KEY (id);


--
-- TOC entry 1972 (class 2606 OID 5511923)
-- Name: pk_druzyna; Type: CONSTRAINT; Schema: public; Owner: wzpn; Tablespace: 
--

ALTER TABLE ONLY druzyna
    ADD CONSTRAINT pk_druzyna PRIMARY KEY (id);


--
-- TOC entry 1984 (class 2606 OID 5512016)
-- Name: pk_faktura; Type: CONSTRAINT; Schema: public; Owner: wzpn; Tablespace: 
--

ALTER TABLE ONLY faktura
    ADD CONSTRAINT pk_faktura PRIMARY KEY (id);


--
-- TOC entry 1978 (class 2606 OID 5511963)
-- Name: pk_grafik; Type: CONSTRAINT; Schema: public; Owner: wzpn; Tablespace: 
--

ALTER TABLE ONLY grafik
    ADD CONSTRAINT pk_grafik PRIMARY KEY (id);


--
-- TOC entry 1958 (class 2606 OID 5511847)
-- Name: pk_jhi_authority; Type: CONSTRAINT; Schema: public; Owner: wzpn; Tablespace: 
--

ALTER TABLE ONLY jhi_authority
    ADD CONSTRAINT pk_jhi_authority PRIMARY KEY (name);


--
-- TOC entry 1965 (class 2606 OID 5511886)
-- Name: pk_jhi_persistent_audit_event; Type: CONSTRAINT; Schema: public; Owner: wzpn; Tablespace: 
--

ALTER TABLE ONLY jhi_persistent_audit_event
    ADD CONSTRAINT pk_jhi_persistent_audit_event PRIMARY KEY (event_id);


--
-- TOC entry 1962 (class 2606 OID 5511860)
-- Name: pk_jhi_persistent_token; Type: CONSTRAINT; Schema: public; Owner: wzpn; Tablespace: 
--

ALTER TABLE ONLY jhi_persistent_token
    ADD CONSTRAINT pk_jhi_persistent_token PRIMARY KEY (series);


--
-- TOC entry 1956 (class 2606 OID 5511836)
-- Name: pk_jhi_user; Type: CONSTRAINT; Schema: public; Owner: wzpn; Tablespace: 
--

ALTER TABLE ONLY jhi_user
    ADD CONSTRAINT pk_jhi_user PRIMARY KEY (id);


--
-- TOC entry 1974 (class 2606 OID 5511936)
-- Name: pk_kara; Type: CONSTRAINT; Schema: public; Owner: wzpn; Tablespace: 
--

ALTER TABLE ONLY kara
    ADD CONSTRAINT pk_kara PRIMARY KEY (id);


--
-- TOC entry 1970 (class 2606 OID 5511912)
-- Name: pk_liga; Type: CONSTRAINT; Schema: public; Owner: wzpn; Tablespace: 
--

ALTER TABLE ONLY liga
    ADD CONSTRAINT pk_liga PRIMARY KEY (id);


--
-- TOC entry 1976 (class 2606 OID 5511952)
-- Name: pk_obiektsportowy; Type: CONSTRAINT; Schema: public; Owner: wzpn; Tablespace: 
--

ALTER TABLE ONLY obiektsportowy
    ADD CONSTRAINT pk_obiektsportowy PRIMARY KEY (id);


--
-- TOC entry 1990 (class 2606 OID 5512058)
-- Name: pk_poleceniezaplaty; Type: CONSTRAINT; Schema: public; Owner: wzpn; Tablespace: 
--

ALTER TABLE ONLY poleceniezaplaty
    ADD CONSTRAINT pk_poleceniezaplaty PRIMARY KEY (id);


--
-- TOC entry 1986 (class 2606 OID 5512034)
-- Name: pk_pozycjafaktury; Type: CONSTRAINT; Schema: public; Owner: wzpn; Tablespace: 
--

ALTER TABLE ONLY pozycjafaktury
    ADD CONSTRAINT pk_pozycjafaktury PRIMARY KEY (id);


--
-- TOC entry 1982 (class 2606 OID 5512005)
-- Name: pk_przedsiebiorca; Type: CONSTRAINT; Schema: public; Owner: wzpn; Tablespace: 
--

ALTER TABLE ONLY przedsiebiorca
    ADD CONSTRAINT pk_przedsiebiorca PRIMARY KEY (id);


--
-- TOC entry 1988 (class 2606 OID 5512047)
-- Name: pk_usluga; Type: CONSTRAINT; Schema: public; Owner: wzpn; Tablespace: 
--

ALTER TABLE ONLY usluga
    ADD CONSTRAINT pk_usluga PRIMARY KEY (id);


--
-- TOC entry 1980 (class 2606 OID 5511989)
-- Name: pk_wynik; Type: CONSTRAINT; Schema: public; Owner: wzpn; Tablespace: 
--

ALTER TABLE ONLY wynik
    ADD CONSTRAINT pk_wynik PRIMARY KEY (id);


--
-- TOC entry 1963 (class 1259 OID 5511895)
-- Name: idx_persistent_audit_event; Type: INDEX; Schema: public; Owner: wzpn; Tablespace: 
--

CREATE INDEX idx_persistent_audit_event ON jhi_persistent_audit_event USING btree (principal, event_date);


--
-- TOC entry 1966 (class 1259 OID 5511896)
-- Name: idx_persistent_audit_event_data; Type: INDEX; Schema: public; Owner: wzpn; Tablespace: 
--

CREATE INDEX idx_persistent_audit_event_data ON jhi_persistent_audit_event_data USING btree (event_id);


--
-- TOC entry 1949 (class 1259 OID 5511842)
-- Name: idx_user_email; Type: INDEX; Schema: public; Owner: wzpn; Tablespace: 
--

CREATE UNIQUE INDEX idx_user_email ON jhi_user USING btree (email);


--
-- TOC entry 1950 (class 1259 OID 5511841)
-- Name: idx_user_login; Type: INDEX; Schema: public; Owner: wzpn; Tablespace: 
--

CREATE UNIQUE INDEX idx_user_login ON jhi_user USING btree (login);


--
-- TOC entry 1994 (class 2606 OID 5511897)
-- Name: FK_event_persistent_audit_event_data; Type: FK CONSTRAINT; Schema: public; Owner: wzpn
--

ALTER TABLE ONLY jhi_persistent_audit_event_data
    ADD CONSTRAINT "FK_event_persistent_audit_event_data" FOREIGN KEY (event_id) REFERENCES jhi_persistent_audit_event(event_id);


--
-- TOC entry 1991 (class 2606 OID 5511861)
-- Name: fk_authority_name; Type: FK CONSTRAINT; Schema: public; Owner: wzpn
--

ALTER TABLE ONLY jhi_user_authority
    ADD CONSTRAINT fk_authority_name FOREIGN KEY (authority_name) REFERENCES jhi_authority(name);


--
-- TOC entry 1995 (class 2606 OID 5511924)
-- Name: fk_druzyna_liga_id; Type: FK CONSTRAINT; Schema: public; Owner: wzpn
--

ALTER TABLE ONLY druzyna
    ADD CONSTRAINT fk_druzyna_liga_id FOREIGN KEY (liga_id) REFERENCES liga(id);


--
-- TOC entry 2002 (class 2606 OID 5512022)
-- Name: fk_faktura_nabywca_id; Type: FK CONSTRAINT; Schema: public; Owner: wzpn
--

ALTER TABLE ONLY faktura
    ADD CONSTRAINT fk_faktura_nabywca_id FOREIGN KEY (nabywca_id) REFERENCES przedsiebiorca(id);


--
-- TOC entry 2001 (class 2606 OID 5512017)
-- Name: fk_faktura_sprzedawca_id; Type: FK CONSTRAINT; Schema: public; Owner: wzpn
--

ALTER TABLE ONLY faktura
    ADD CONSTRAINT fk_faktura_sprzedawca_id FOREIGN KEY (sprzedawca_id) REFERENCES przedsiebiorca(id);


--
-- TOC entry 1998 (class 2606 OID 5511969)
-- Name: fk_grafik_drugadruzyna_id; Type: FK CONSTRAINT; Schema: public; Owner: wzpn
--

ALTER TABLE ONLY grafik
    ADD CONSTRAINT fk_grafik_drugadruzyna_id FOREIGN KEY (drugadruzyna_id) REFERENCES druzyna(id);


--
-- TOC entry 1999 (class 2606 OID 5511974)
-- Name: fk_grafik_obiektsportowy_id; Type: FK CONSTRAINT; Schema: public; Owner: wzpn
--

ALTER TABLE ONLY grafik
    ADD CONSTRAINT fk_grafik_obiektsportowy_id FOREIGN KEY (obiektsportowy_id) REFERENCES obiektsportowy(id);


--
-- TOC entry 1997 (class 2606 OID 5511964)
-- Name: fk_grafik_pierwszadruzyna_id; Type: FK CONSTRAINT; Schema: public; Owner: wzpn
--

ALTER TABLE ONLY grafik
    ADD CONSTRAINT fk_grafik_pierwszadruzyna_id FOREIGN KEY (pierwszadruzyna_id) REFERENCES druzyna(id);


--
-- TOC entry 1996 (class 2606 OID 5511937)
-- Name: fk_kara_druzyna_id; Type: FK CONSTRAINT; Schema: public; Owner: wzpn
--

ALTER TABLE ONLY kara
    ADD CONSTRAINT fk_kara_druzyna_id FOREIGN KEY (druzyna_id) REFERENCES druzyna(id);


--
-- TOC entry 2003 (class 2606 OID 5512035)
-- Name: fk_pozycjafaktury_faktura_id; Type: FK CONSTRAINT; Schema: public; Owner: wzpn
--

ALTER TABLE ONLY pozycjafaktury
    ADD CONSTRAINT fk_pozycjafaktury_faktura_id FOREIGN KEY (faktura_id) REFERENCES faktura(id);


--
-- TOC entry 1992 (class 2606 OID 5511866)
-- Name: fk_user_id; Type: FK CONSTRAINT; Schema: public; Owner: wzpn
--

ALTER TABLE ONLY jhi_user_authority
    ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES jhi_user(id);


--
-- TOC entry 1993 (class 2606 OID 5511871)
-- Name: fk_user_persistent_token; Type: FK CONSTRAINT; Schema: public; Owner: wzpn
--

ALTER TABLE ONLY jhi_persistent_token
    ADD CONSTRAINT fk_user_persistent_token FOREIGN KEY (user_id) REFERENCES jhi_user(id);


--
-- TOC entry 2000 (class 2606 OID 5511990)
-- Name: fk_wynik_grafik_id; Type: FK CONSTRAINT; Schema: public; Owner: wzpn
--

ALTER TABLE ONLY wynik
    ADD CONSTRAINT fk_wynik_grafik_id FOREIGN KEY (grafik_id) REFERENCES grafik(id);


--
-- TOC entry 2150 (class 0 OID 0)
-- Dependencies: 5
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2015-06-10 14:23:20

--
-- PostgreSQL database dump complete
--

