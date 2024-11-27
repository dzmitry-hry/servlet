DROP SCHEMA IF EXISTS "weather" CASCADE;

CREATE SCHEMA "weather";

DROP TABLE IF EXISTS "weather".locations CASCADE;

CREATE TABLE "weather".locations
(
    id serial NOT NULL,
    name character varying COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT locations_pkey PRIMARY KEY (id)
);

DROP TABLE IF EXISTS "weather".meteoproviders CASCADE;

CREATE TABLE "weather".meteoproviders
(
    id serial NOT NULL,
    name character varying COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT meteoproviders_pkey PRIMARY KEY (id)
);

DROP TABLE IF EXISTS weather.weathershots CASCADE;
CREATE TABLE weather.weathershots
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

DROP TABLE IF EXISTS weather.meteostations CASCADE;
CREATE TABLE weather.meteostations
(
    id serial NOT NULL,
	name character varying COLLATE pg_catalog."default" NOT NULL, 
    location_id integer NOT NULL,
    CONSTRAINT meteostation_locations_pkey PRIMARY KEY (id)
);


DROP TABLE IF EXISTS weather.meteoprovider_meteostations CASCADE;
CREATE TABLE weather.meteoprovider_meteostations
(
    meteoprovider_id integer NOT NULL,
    meteostation_id integer NOT NULL
);

ALTER TABLE weather.meteoprovider_meteostations DROP CONSTRAINT IF EXISTS FK_METEOPROVIDER_ID;
ALTER TABLE weather.meteoprovider_meteostations DROP CONSTRAINT IF EXISTS FK_METEOSTATION_ID;


ALTER TABLE weather.meteoprovider_meteostations
    ADD CONSTRAINT "FK_METEOPROVIDER_ID" FOREIGN KEY (meteoprovider_id)
    REFERENCES weather.meteoproviders (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE RESTRICT
    NOT VALID;

ALTER TABLE weather.meteoprovider_meteostations
    ADD CONSTRAINT "FK_METEOSTATION_ID" FOREIGN KEY (meteostation_id)
    REFERENCES weather.meteostations (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE RESTRICT
    NOT VALID;






