insert into users (id, user_name, email, password) values (-1, 'user', 'email@gmx.com', 'cGFzc21leW95b3lv');
insert into users (id, user_name, email, password) values (-2, 'lorenz', 'ema_il@gmx.com', 'cGFzc21leW95b3lv');
insert into users (id, user_name, email, password) values (-3, 'FAnDave', 'ichliebejuicewrld@gmx.com', 'cGFzc21leW95b3lv');

insert into theme(id, name, thumbnail_url, container_url, container_mat_url, light_intensity)
    values (1, 'Edgy Teen', 'thumbnails/themeEdgy.jpg', '~/test.c4d', '~/test.c4d', 0.0 );
insert into theme(id, name, thumbnail_url, container_url, container_mat_url, light_intensity)
    values (2, 'Dreamy', 'thumbnails/themeDreamy.jpg', '~/test.c4d', '~/test.c4d', 0.0 );

insert into room(id, name, thumbnail_url, room_floor_url, room_wall_url, wall_mat_url, floor_mat_url, floorRepeatTexture)
    values (1, 'Cross', 'thumbnails/crossRoomThumbnail.png', 'rooms/floor2.gltf', 'rooms/wall2.gltf', 'src/nopath', 'src/nopath', 1);  /* src/main/resources/files/rooms/ */
insert into room(id, name, thumbnail_url, room_floor_url, room_wall_url, wall_mat_url, floor_mat_url, floorRepeatTexture)
    values (2, 'Cube', 'thumbnails/crossRoomThumbnail.png', 'rooms/floor1.gltf', 'rooms/wall1.gltf', 'src/nopath', 'src/nopath', 1);  /* src/main/resources/files/rooms/ */

insert into position(id, rotation, x, y, is_wall, room_id) values (1, null, 500, 0, false, 1);
insert into position(id, rotation, x, y, is_wall, room_id) values (2, null, -100, 0, false, 1);

/*
insert into position(id, rotation, x, y, is_wall, room_id) values (3, 40, 200, 350, false, 1);
insert into position(id, rotation, x, y, is_wall, room_id) values (4, 40, 200, 500, false, 1);
*/
insert into position(id, rotation, x, y, is_wall, room_id) values (3, 0, 200, -590, true, 1);
insert into position(id, rotation, x, y, is_wall, room_id) values (4, 90, -790, 0, true, 1);
insert into position(id, rotation, x, y, is_wall, room_id) values (5, 0, -500, 190, true, 1);
insert into position(id, rotation, x, y, is_wall, room_id) values (6, 0, -500, -190, true, 1);
insert into position(id, rotation, x, y, is_wall, room_id) values (7, 0, 200, 575, true, 1);


insert into exhibition(id, title, user_id, thumbnail_url, description, room_id)
    values (-1, 'Fotos von Künstlern', -1, 'thumbnails/banksyThumbnail.jpg', 'ich liebe diese künstler so sehr, deren kunst ist erstaundlich', 1);
insert into exhibition(id, title, user_id, thumbnail_url, description, room_id)
    values (-2, 'Fotos von meinem Haustier', -1, 'thumbnails/chickenThumbnail.jpg', 'Hühner sind die besten Haustiere', 1);

insert into exhibition(id, title, user_id, thumbnail_url, description, room_id)
    values (-3, 'Newer Abstract', -2, 'example-exhibits/abstract1.jpg', 'Just some of my latest pieces. I tried combining lines with bright colors to achieve abstract pieces.', 1);
insert into exhibition(id, title, user_id, thumbnail_url, description, room_id)
    values (-4, 'Favourite Shots in CP2077', -2, 'thumbnails/ExampleThumbnailCyberpunk.jpg', 'Just some pictures i took while exploring Night City!', 1);

insert into exhibit(id, title, scale, alignment) values (-1, 'Picasso Foto', 0, 'c');
insert into exhibit(id, title, scale, alignment, url, exhibition_id)
    values (-2, 'Geburt.', 0, 'c', 'https://www.agrarheute.com/sites/agrarheute.com/files/styles/ah_bildergalerie_standalone_5x4/public/thumbnails/image/ei-kueken.jpg?itok=IGZdghSz', -2);
insert into exhibit(id, title, scale, alignment, url, exhibition_id)
    values (-3, 'Freunde.', 0, 'c', 'https://img.fotocommunity.com/hahn-im-korb-bielefelder-kennhuehner-huehnerschar-mit-kraehendem-hahn-19460011-fd7e-4c9f-961e-aeba8b21b00a.jpg?width=1000', -2);
 insert into exhibit(id, title, scale, alignment, url, exhibition_id)
    values (-4, 'Stand', 0, 'c', 'example-exhibits/exampleExhibitOrbital.jpg', -4);

/*
 TODO
 */
insert into exhibit(id, title, scale, alignment, url, exhibition_id, position_id, data_type)
values (-5, 'Abstract 1', 400, 'c', 'example-exhibits%2Fabstract1.jpg', -3, 4, 'jpg');
insert into exhibit(id, title, scale, alignment, url, exhibition_id, position_id, data_type)
values (-6, 'Abstract 2', 0, 'c', 'example-exhibits%2Fabstract2.jpg', -3, 3, 'jpg');
insert into exhibit(id, title, scale, alignment, url, exhibition_id, position_id, data_type)
values (-7, 'Abstract 3', 0, 'c', 'example-exhibits%2Fabstract3.jpg', -3, 5, 'jpg');
insert into exhibit(id, title, scale, alignment, url, exhibition_id, position_id, data_type)
values (-8, 'Abstract 3D', 0, 'c', 'example-exhibits%2Fabstract5.gltf', -3, 1, 'gltf');

insert into category(id, category_title, color) values (1, 'Umwelt', '#C1BAFF');
insert into category(id, category_title, color) values (2, 'Tiere', '#ADD0FF');
insert into category(id, category_title, color) values (3, 'Essen', '#E2ADFF');
insert into category(id, category_title, color) values (4, 'Geschichte', '#FA9EDC');
insert into category(id, category_title, color) values (5, 'Länder', '#D9A7F5');
insert into category(id, category_title, color) values (6, 'Kunst', '#E4D7F6');
/*
insert into exhibitions_categories(category_id, exhibition_id) values (1,-2);
insert into exhibitions_categories(category_id, exhibition_id) values (2,-2);

 */
insert into exhibitions_categories(category_id, exhibition_id) values (3,-3);


/* special test cases

insert into exhibition(id, title, user_id, thumbnail_url) values (1, 'Fotos von Künstlern', -1, 'http://3.bp.blogspot.com/-5rLnT_OE6yo/T4078obu5yI/AAAAAAAAAPo/Z-5Xo9lGpnQ/s1600/Marilyn-Monroe-Pop-Art.jpg');
insert into exhibition(id, title, user_id, thumbnail_url) values (2, 'Fotos von meinem Huhn', -1, 'https://www.huehner-haltung.de/img/rhodelaender-huhn-768x768.jpg');
insert into exhibition(id, title, user_id, thumbnail_url) values (3, 'Sphaghet mhhhhh', -2, 'https://www.marions-kochbuch.de/rezept/0591.jpg');
insert into exhibition(id, title, user_id, thumbnail_url) values (4, 'Rise and Fall', -3, 'http://www.monster-clip.com/images/maps/mw2/gulag/gulag1.jpg');
insert into exhibition(id, title, user_id, thumbnail_url) values (5, 'hoi hoi hoi, toi toi toi', -3, 'https://i.pinimg.com/originals/cf/37/25/cf3725c70291c54d1ada2c6d575ced42.jpg');
insert into exhibition(id, title, user_id, thumbnail_url) values (6, 'JUICEWRLD', -3, 'https://wallpapercave.com/wp/wp6812137.jpg');
*/