CREATE DATABASE promotion_plugin;

\c promotion_plugin;

CREATE TABLE location(
   	location_id VARCHAR(64) NOT NULL PRIMARY KEY,
   	name VARCHAR(256),
   	latitude double precision,
   	longitude double precision
);

CREATE INDEX location_idx ON location(latitude,longitude);

CREATE TABLE service_type(
   	service_type_id serial NOT NULL PRIMARY KEY,
   	service_name VARCHAR(36),
   	code VARCHAR(5)
);

CREATE INDEX service_type_idx ON service_type(code);


CREATE TABLE user_location_info(
   	id serial NOT NULL PRIMARY KEY,
   	user_id VARCHAR(256),
   	location_id VARCHAR(64) REFERENCES location(location_id),
   	visited_ts timestamp
);

CREATE INDEX user_location_info_idx ON user_location_info(user_id,location_id);


CREATE TABLE user_service_relation(
   	id serial NOT NULL PRIMARY KEY,
   	service_type_id integer REFERENCES service_type(service_type_id),
   	user_id VARCHAR(256),
   	service_name VARCHAR(256),
   	visited_ts timestamp
);

CREATE INDEX user_service_relation_idx ON user_service_relation(user_id, service_type_id);

