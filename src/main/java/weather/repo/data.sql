INSERT INTO weather.locations(id, name)
	VALUES (1, 'Oslo');
INSERT INTO weather.locations(id, name)
	VALUES (2, 'Sydney');
INSERT INTO weather.locations(id, name)
	VALUES (3, 'Moscow');
INSERT INTO weather.locations(id, name)
	VALUES (4, 'London');

INSERT INTO weather.meteostations(id,  name, location_id)
	VALUES (1,'meteostation_1', 1);
INSERT INTO weather.meteostations(id, name, location_id)
	VALUES (2,'meteostation_2',2);
INSERT INTO weather.meteostations(id, name, location_id)
	VALUES (3,'meteostation_3',3);
INSERT INTO weather.meteostations(id, name, location_id)
	VALUES (4,'meteostation_4',4);



INSERT INTO weather.meteoprovider_meteostations(meteoprovider_id,  meteostation_id)
	VALUES (1,1);
INSERT INTO weather.meteoprovider_meteostations(meteoprovider_id,  meteostation_id)
	VALUES (1,2);
INSERT INTO weather.meteoprovider_meteostations(meteoprovider_id,  meteostation_id)
	VALUES (2,1);
