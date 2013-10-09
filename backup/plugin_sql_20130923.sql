--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

--
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.hibernate_sequence OWNER TO postgres;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: item_measure; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE item_measure (
    id integer NOT NULL,
    brand character varying(255),
    effective_price real,
    item_code character varying(255),
    name character varying(255),
    message character varying(255),
    price real,
    sub_category_id integer NOT NULL,
    service_type_id integer NOT NULL
);


ALTER TABLE public.item_measure OWNER TO postgres;

--
-- Name: item_measure_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE item_measure_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.item_measure_id_seq OWNER TO postgres;

--
-- Name: item_measure_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE item_measure_id_seq OWNED BY item_measure.id;


--
-- Name: location; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE location (
    location_id character varying(255) NOT NULL,
    latitude double precision,
    longitude double precision,
    name character varying(255)
);


ALTER TABLE public.location OWNER TO postgres;

--
-- Name: request_processor_dimension_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE request_processor_dimension_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.request_processor_dimension_id_seq OWNER TO postgres;

--
-- Name: request_processor_dimension; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE request_processor_dimension (
    id integer DEFAULT nextval('request_processor_dimension_id_seq'::regclass) NOT NULL,
    no_of_contexts integer,
    no_of_sub_contexts integer,
    processor_class character varying(255),
    request_type character varying(255)
);


ALTER TABLE public.request_processor_dimension OWNER TO postgres;

--
-- Name: service_type; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE service_type (
    service_type_id integer NOT NULL,
    code character varying(255),
    created_ts date,
    service_name character varying(255)
);


ALTER TABLE public.service_type OWNER TO postgres;

--
-- Name: service_type_service_type_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE service_type_service_type_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.service_type_service_type_id_seq OWNER TO postgres;

--
-- Name: service_type_service_type_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE service_type_service_type_id_seq OWNED BY service_type.service_type_id;


--
-- Name: sub_category_dimension; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE sub_category_dimension (
    id integer NOT NULL,
    code character varying(255),
    name character varying(255),
    service_type_id integer NOT NULL
);


ALTER TABLE public.sub_category_dimension OWNER TO postgres;

--
-- Name: sub_category_dimension_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE sub_category_dimension_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.sub_category_dimension_id_seq OWNER TO postgres;

--
-- Name: sub_category_dimension_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE sub_category_dimension_id_seq OWNED BY sub_category_dimension.id;


--
-- Name: user_location_info; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE user_location_info (
    id integer NOT NULL,
    user_id character varying(255),
    visited_ts timestamp without time zone,
    location_id character varying(255)
);


ALTER TABLE public.user_location_info OWNER TO postgres;

--
-- Name: user_request_measure_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE user_request_measure_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_request_measure_id_seq OWNER TO postgres;

--
-- Name: user_request_measure; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE user_request_measure (
    id integer DEFAULT nextval('user_request_measure_id_seq'::regclass) NOT NULL,
    created_ts date,
    modified_ts date,
    primary_context character varying(255),
    is_completed boolean,
    resulted_items character varying(255),
    send_to_user boolean,
    secondary_context character varying(255),
    sub_contexts character varying(255),
    user_id character varying(255),
    sub_cat_id integer NOT NULL,
    request_processor_id integer NOT NULL,
    service_type_id integer NOT NULL
);


ALTER TABLE public.user_request_measure OWNER TO postgres;

--
-- Name: user_service_relation_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE user_service_relation_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_service_relation_seq OWNER TO postgres;

--
-- Name: user_service_relation; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE user_service_relation (
    id integer DEFAULT nextval('user_service_relation_seq'::regclass) NOT NULL,
    service_name character varying(255),
    user_id character varying(255),
    visited_ts timestamp without time zone,
    service_type_id integer
);


ALTER TABLE public.user_service_relation OWNER TO postgres;

--
-- Name: user_settings_measure; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE user_settings_measure (
    id integer NOT NULL,
    modified_ts timestamp without time zone,
    service_type_codes character varying(255),
    user_id character varying(255)
);


ALTER TABLE public.user_settings_measure OWNER TO postgres;

--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY item_measure ALTER COLUMN id SET DEFAULT nextval('item_measure_id_seq'::regclass);


--
-- Name: service_type_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY service_type ALTER COLUMN service_type_id SET DEFAULT nextval('service_type_service_type_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sub_category_dimension ALTER COLUMN id SET DEFAULT nextval('sub_category_dimension_id_seq'::regclass);


--
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('hibernate_sequence', 1, true);


--
-- Data for Name: item_measure; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY item_measure (id, brand, effective_price, item_code, name, message, price, sub_category_id, service_type_id) FROM stdin;
7	best	150	C0001GH	Best Rice	Rice for biryani	210	4	1
8	Jealous	250	L0003AF	Jealous Tees	Tees for girls	300	1	2
2	himgiri	250	C0001GH	Basmati Rice	Rice matlab Basmati Rice	300	4	1
9	Levis	699	L0003AA	Levis Tees	Tees from Levis	800	1	2
\.


--
-- Name: item_measure_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('item_measure_id_seq', 9, true);


--
-- Data for Name: location; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY location (location_id, latitude, longitude, name) FROM stdin;
27b33ce4-80f5-454c-bffe-78a1a73ab5fd	12.916460000000001	77.610009000000005	null
c111cbc9-c1b8-44a1-b7d0-8104340aa214	12.916460000000001	77.610009000000005	null
7cc2b948-646c-44e3-b425-dbfb99dc0794	12.916460000000001	77.610009000000005	null
a4282f55-d613-46f5-bd53-cd02d7f2497e	12.916460000000001	77.610009000000005	null
f3f1357b-5eae-4fc0-982d-382730c9d75a	12.916460000000001	77.610009000000005	null
fd461bf7-5516-4b96-9632-c9f4c55802f6	12.916460000000001	77.610009000000005	null
98d637f3-adc4-4217-968b-4e772d35cbf1	12.916460000000001	77.610009000000005	null
c397a505-09bf-4111-988d-b576a9037897	12.916460000000001	77.610009000000005	null
164a33c4-7638-4c40-8ab8-5c16258026db	12.916460000000001	77.610009000000005	null
\.


--
-- Data for Name: request_processor_dimension; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY request_processor_dimension (id, no_of_contexts, no_of_sub_contexts, processor_class, request_type) FROM stdin;
1	2	0	com.mobile.tool.request.intr.processor.PriceRequestProcessor	PricePro
3	1	0	com.mobile.tool.request.intr.processor.BranRequestProcessor	BrandPro
\.


--
-- Name: request_processor_dimension_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('request_processor_dimension_id_seq', 3, true);


--
-- Data for Name: service_type; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY service_type (service_type_id, code, created_ts, service_name) FROM stdin;
1	G	2013-09-05	grocery
2	A	2013-09-05	apparel
3	O	2013-09-05	other
4	F	2013-09-15	Furniture
\.


--
-- Name: service_type_service_type_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('service_type_service_type_id_seq', 4, true);


--
-- Data for Name: sub_category_dimension; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY sub_category_dimension (id, code, name, service_type_id) FROM stdin;
1	AT	Tees	2
2	AS	Shoes	2
3	AO	Other	2
4	GR	Rice	1
5	GW	Wheat	1
6	GO	Other	1
7	OHD	Home Decore	3
8	OEA	Electrical Appliences	3
9	OO	Other	3
10	FQ	Queen Beds	4
\.


--
-- Name: sub_category_dimension_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('sub_category_dimension_id_seq', 10, true);


--
-- Data for Name: user_location_info; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY user_location_info (id, user_id, visited_ts, location_id) FROM stdin;
0	AND1	2013-09-11 18:32:02.086	c397a505-09bf-4111-988d-b576a9037897
\.


--
-- Data for Name: user_request_measure; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY user_request_measure (id, created_ts, modified_ts, primary_context, is_completed, resulted_items, send_to_user, secondary_context, sub_contexts, user_id, sub_cat_id, request_processor_id, service_type_id) FROM stdin;
1	2013-09-10	2013-09-11	Less Than	t	C0001GH	t	180	\N	AND1	4	1	1
\.


--
-- Name: user_request_measure_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('user_request_measure_id_seq', 1, true);


--
-- Data for Name: user_service_relation; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY user_service_relation (id, service_name, user_id, visited_ts, service_type_id) FROM stdin;
1	Levis Tees	AND1	2013-09-11 17:33:00	2
2	Levis Tees	AND1	2013-09-11 17:33:00	1
\.


--
-- Name: user_service_relation_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('user_service_relation_seq', 2, true);


--
-- Data for Name: user_settings_measure; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY user_settings_measure (id, modified_ts, service_type_codes, user_id) FROM stdin;
11	2013-09-05 18:34:31.944	A	AND2
10	2013-09-05 18:34:15.853	A,G	AND1
\.


--
-- Name: item_measure_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY item_measure
    ADD CONSTRAINT item_measure_pkey PRIMARY KEY (id);


--
-- Name: location_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY location
    ADD CONSTRAINT location_pkey PRIMARY KEY (location_id);


--
-- Name: request_processor_dimension_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY request_processor_dimension
    ADD CONSTRAINT request_processor_dimension_pkey PRIMARY KEY (id);


--
-- Name: service_type_code_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY service_type
    ADD CONSTRAINT service_type_code_key UNIQUE (code);


--
-- Name: service_type_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY service_type
    ADD CONSTRAINT service_type_pkey PRIMARY KEY (service_type_id);


--
-- Name: sub_category_dimension_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY sub_category_dimension
    ADD CONSTRAINT sub_category_dimension_pkey PRIMARY KEY (id);


--
-- Name: user_location_info_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY user_location_info
    ADD CONSTRAINT user_location_info_pkey PRIMARY KEY (id);


--
-- Name: user_request_measure_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY user_request_measure
    ADD CONSTRAINT user_request_measure_pkey PRIMARY KEY (id);


--
-- Name: user_service_relation_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY user_service_relation
    ADD CONSTRAINT user_service_relation_pkey PRIMARY KEY (id);


--
-- Name: user_settings_measure_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY user_settings_measure
    ADD CONSTRAINT user_settings_measure_pkey PRIMARY KEY (id);


--
-- Name: fk7b6fc584deba622d; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY user_location_info
    ADD CONSTRAINT fk7b6fc584deba622d FOREIGN KEY (location_id) REFERENCES location(location_id);


--
-- Name: fkbab5463a1f4cfad2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY user_service_relation
    ADD CONSTRAINT fkbab5463a1f4cfad2 FOREIGN KEY (service_type_id) REFERENCES service_type(service_type_id);


--
-- Name: fkc9c98b3a1f4cfad2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY user_request_measure
    ADD CONSTRAINT fkc9c98b3a1f4cfad2 FOREIGN KEY (service_type_id) REFERENCES service_type(service_type_id);


--
-- Name: fkc9c98b3a245facbe; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY user_request_measure
    ADD CONSTRAINT fkc9c98b3a245facbe FOREIGN KEY (sub_cat_id) REFERENCES sub_category_dimension(id);


--
-- Name: fkc9c98b3ab93e45e5; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY user_request_measure
    ADD CONSTRAINT fkc9c98b3ab93e45e5 FOREIGN KEY (request_processor_id) REFERENCES request_processor_dimension(id);


--
-- Name: fke61804321f4cfad2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY item_measure
    ADD CONSTRAINT fke61804321f4cfad2 FOREIGN KEY (service_type_id) REFERENCES service_type(service_type_id);


--
-- Name: fke6180432aca68438; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY item_measure
    ADD CONSTRAINT fke6180432aca68438 FOREIGN KEY (sub_category_id) REFERENCES sub_category_dimension(id);


--
-- Name: fkead6dfc41f4cfad2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sub_category_dimension
    ADD CONSTRAINT fkead6dfc41f4cfad2 FOREIGN KEY (service_type_id) REFERENCES service_type(service_type_id);


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

