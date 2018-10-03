/**
 * CREATE Script for init of DB
 */
SET FOREIGN_KEY_CHECKS = 0;
-- Create 3 OFFLINE drivers

insert into sat.driver (id, date_created, deleted, online_status, password, username)
values (1, now(), false, 'OFFLINE', 'driver01pw', 'driver01');

insert into sat.driver (id, date_created, deleted, online_status, password, username)
values (2, now(), false, 'OFFLINE', 'driver02pw', 'driver02');

insert into sat.driver (id, date_created, deleted, online_status, password, username)
values (3, now(), false, 'OFFLINE', 'driver03pw', 'driver03');

-- Create 3 ONLINE drivers

insert into sat.driver (id, date_created, deleted, online_status, password, username)
values (4, now(), false, 'ONLINE', 'driver04pw', 'driver04');

insert into sat.driver (id, date_created, deleted, online_status, password, username)
values (5, now(), false, 'ONLINE', 'driver05pw', 'driver05');

insert into sat.driver (id, date_created, deleted, online_status, password, username)
values (6, now(), false, 'ONLINE', 'driver06pw', 'driver06');

-- Create 1 OFFLINE driver with coordinate(longitude=9.5&latitude=55.954)

insert into sat.driver (id,
                        coordinate,
                        date_coordinate_updated,
                        date_created,
                        deleted,
                        online_status,
                        password,
                        username)
values (7,
        'aced0005737200226f72672e737072696e676672616d65776f726b2e646174612e67656f2e506f696e7431b9e90ef11a4006020002440001784400017978704023000000000000404bfa1cac083127',
        now(),
        now(),
        false,
        'OFFLINE',
        'driver07pw',
        'driver07');

-- Create 1 ONLINE driver with coordinate(longitude=9.5&latitude=55.954)

insert into sat.driver (id,
                        coordinate,
                        date_coordinate_updated,
                        date_created,
                        deleted,
                        online_status,
                        password,
                        username)
values (8,
        'aced0005737200226f72672e737072696e676672616d65776f726b2e646174612e67656f2e506f696e7431b9e90ef11a4006020002440001784400017978704023000000000000404bfa1cac083127',
        now(),
        now(),
        false,
        'ONLINE',
        'driver08pw',
        'driver08');

-- todo: Serialize data
insert into sat.manufacturer (id, name, origin, service_toll_free, website, factory)
values (1, 'BWM', 'Germany', '080-060-0555', 'https://www.bmw.com/en/index.html', 'Munich');


insert into sat.manufacturer (id, name, origin, service_toll_free, website, factory)
values (2, 'AUDI', 'Germany', '0860-434-838', 'https://www.audi.com/en.html', 'Munich');

insert into sat.car_type (id, manufacturer_id, make, model, year, body_style, engine_type, seat_count, quantity)
values (1, 1, 'BMW', 'X6', DATEADD('YEAR', -1, CURRENT_DATE), 'SUV', 'HYBRID_ELECTRIC_SUPER_UNLEADED_PETROL', 6, 5);

insert into sat.car_type (id, manufacturer_id, make, model, year, body_style, engine_type, seat_count, quantity)
values (2, 2, 'AUDI', 'A4', DATEADD('YEAR', -1, CURRENT_DATE), 'SEDAN', 'PREMIUM_UNLEADED_PETROL', 5, 3);

insert into sat.car (id, car_type_id, car_state_id, average_rating, licence_no, mileage, next_service)
values (1001, 1, 1, 0.00, '303-707', 100.0013, now());

insert into sat.car (id, car_type_id, car_state_id, average_rating, licence_no, mileage, next_service)
values (2001, 2, 2, 0.00, '303-606', 50.02, now());

insert into sat.car_state (id, car_status, car_id, created, updated)
values (1, 'READY', 1001, now(), now());

insert into sat.car_state (id, car_status, car_id, created, updated)
values (2, 'READY', 2001, now(), now());

SET FOREIGN_KEY_CHECKS = 1;