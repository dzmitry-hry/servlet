
DROP TABLE IF EXISTS locations CASCADE;

CREATE TABLE locations
(
    id serial NOT NULL,
    name character varying COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT locations_pkey PRIMARY KEY (id)
);

DROP TABLE IF EXISTS meteoproviders CASCADE;

CREATE TABLE meteoproviders
(
    id serial NOT NULL,
    name character varying COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT meteoproviders_pkey PRIMARY KEY (id)
);

DROP TABLE IF EXISTS weathershots CASCADE;
CREATE TABLE weathershots
(
    id serial NOT NULL,
	date timestamp NOT NULL,
    humidity integer NOT NULL,
    temperature integer NOT NULL,
    windSpeed double precision NOT NULL,
    clouds integer NOT NULL,
	visibility integer NOT NULL,
	meteoprovider_id integer NOT NULL,
	location_id integer NOT NULL,

	CONSTRAINT weathershots_pkey PRIMARY KEY (id)
);

DROP TABLE IF EXISTS meteostations CASCADE;
CREATE TABLE meteostations
(
    id serial NOT NULL,
	name character varying COLLATE pg_catalog."default" NOT NULL,
    location_id integer NOT NULL,
    CONSTRAINT meteostation_locations_pkey PRIMARY KEY (id)
);


DROP TABLE IF EXISTS meteoprovider_meteostations CASCADE;
CREATE TABLE meteoprovider_meteostations
(
    meteoprovider_id integer NOT NULL,
    meteostation_id integer NOT NULL
);

ALTER TABLE meteoprovider_meteostations DROP CONSTRAINT IF EXISTS FK_METEOPROVIDER_ID;
ALTER TABLE meteoprovider_meteostations DROP CONSTRAINT IF EXISTS FK_METEOSTATION_ID;


ALTER TABLE meteoprovider_meteostations
    ADD CONSTRAINT "FK_METEOPROVIDER_ID" FOREIGN KEY (meteoprovider_id)
    REFERENCES meteoproviders (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE RESTRICT
    NOT VALID;

ALTER TABLE meteoprovider_meteostations
    ADD CONSTRAINT "FK_METEOSTATION_ID" FOREIGN KEY (meteostation_id)
    REFERENCES meteostations (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE RESTRICT
    NOT VALID;






