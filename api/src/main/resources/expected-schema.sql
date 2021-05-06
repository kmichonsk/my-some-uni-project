CREATE TABLE IF NOT EXISTS brands (
    id long NOT NULL,
    brand_name varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS stations (
    id long NOT NULL,
    name varchar(255) DEFAULT NULL,
    address varchar(39) DEFAULT NULL,
    city varchar(27) DEFAULT NULL,
    postal_code varchar(6) DEFAULT NULL,
    price_fuel95 float DEFAULT NULL,
    price_fuel98 float DEFAULT NULL,
    price_fuel_diesel float DEFAULT NULL,
    price_fuel_lpg float DEFAULT NULL,
    brand_id int DEFAULT NULL,
    opening_hours varchar(255) DEFAULT NULL,
	latitude float DEFAULT NULL,
	longitude float DEFAULT NULL,
	sum_of_rating int(11) DEFAULT NULL,
	rating_count int(11) DEFAULT NULL
) DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS comments (
  id int(11) NOT NULL,
  comment varchar(255) NOT NULL,
  station_id int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
