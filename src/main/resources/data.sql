/**
 * CREATE Script for init of DB
 */

-- Create 3 OFFLINE drivers

insert into driver (id, date_created, deleted, online_status, password, username) values (1, now(), false, 'OFFLINE',
'driver01pw', 'driver01');

insert into driver (id, date_created, deleted, online_status, password, username) values (2, now(), false, 'OFFLINE',
'driver02pw', 'driver02');

insert into driver (id, date_created, deleted, online_status, password, username) values (3, now(), false, 'OFFLINE',
'driver03pw', 'driver03');


-- Create 3 ONLINE drivers

insert into driver (id, date_created, deleted, online_status, password, username) values (4, now(), false, 'ONLINE',
'driver04pw', 'driver04');

insert into driver (id, date_created, deleted, online_status, password, username) values (5, now(), false, 'ONLINE',
'driver05pw', 'driver05');

insert into driver (id, date_created, deleted, online_status, password, username) values (6, now(), false, 'ONLINE',
'driver06pw', 'driver06');

-- Create 1 OFFLINE driver with coordinate(longitude=9.5&latitude=55.954)

insert into driver (id, coordinate, date_coordinate_updated, date_created, deleted, online_status, password, username)
values
 (7,
 'aced0005737200226f72672e737072696e676672616d65776f726b2e646174612e67656f2e506f696e7431b9e90ef11a4006020002440001784400017978704023000000000000404bfa1cac083127', now(), now(), false, 'OFFLINE',
'driver07pw', 'driver07');

-- Create 1 ONLINE driver with coordinate(longitude=9.5&latitude=55.954)

insert into driver (id, coordinate, date_coordinate_updated, date_created, deleted, online_status, password, username)
values
 (8,
 'aced0005737200226f72672e737072696e676672616d65776f726b2e646174612e67656f2e506f696e7431b9e90ef11a4006020002440001784400017978704023000000000000404bfa1cac083127', now(), now(), false, 'ONLINE',
'driver08pw', 'driver08');

insert into manufacturer(id, name, date_created)
values(1, 'Toyota', now());
insert into manufacturer(id, name, date_created)
values(2, 'Tesla', now());

insert into car(id, license_plate, seat_count,
convertible, engine_type, rating, manufacturer_id,
date_created, deleted)
values (1, 'ABCD-123467890', 4, false, 'GAS',
100, 1, now(), false);
insert into car(id, license_plate, seat_count,
                convertible, engine_type, rating, manufacturer_id,
                date_created, deleted) values (2, 'BBCD-123467890', 2, true, 'GAS',
100, 1, now(), false);

insert into car(id, license_plate, seat_count,
                convertible, engine_type, rating, manufacturer_id,
                date_created, deleted) values (3, 'CBCD-123467890', 2, true, 'ELECTRIC',
                                               100, 1, now(), false);

insert into car(id, license_plate, seat_count,
                convertible, engine_type, rating, manufacturer_id,
                date_created, deleted) values (4, 'DBCD-123467890', 2, true, 'HYBRID',
                                               100, 1, now(), false);

insert into car(id, license_plate, seat_count,
                convertible, engine_type, rating, manufacturer_id,
                date_created, deleted) values (5, 'EBCD-123467890', 2, true, 'DIESEL',
                                               100, 1, now(), false);

insert into car(id, license_plate, seat_count,
                convertible, engine_type, rating, manufacturer_id,
                date_created, deleted) values (6, 'FBCD-123467890', 4, false, 'ELECTRIC',
                                               100, 2, now(), false);

insert into car(id, license_plate, seat_count,
                convertible, engine_type, rating, manufacturer_id,
                date_created, deleted) values (7, 'GBCD-123467890', 4, false, 'ELECTRIC',
                                               100, 2, now(), false);

insert into car(id, license_plate, seat_count,
                convertible, engine_type, rating, manufacturer_id,
                date_created, deleted) values (8, 'HBCD-123467890', 4, false, 'ELECTRIC',
                                               50, 2, now(), false);

insert into user(id, username, password, date_created)
    values(1, 'user01', '$2a$10$sAwPcjVHP1bPj5LOwYBPuO3boP6DBrgnPlH2fmcRbJjL0/h6q.QVa', now());
//password user01pw