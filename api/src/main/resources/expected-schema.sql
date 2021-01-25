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
    has_fuel_95 float DEFAULT NULL,
    has_fuel_98 float DEFAULT NULL,
    has_fuel_diesel float DEFAULT NULL,
    has_fuel_lpg float DEFAULT NULL,
    brand int DEFAULT NULL,
    opening_hours varchar(255) DEFAULT NULL
) DEFAULT CHARSET=utf8;
