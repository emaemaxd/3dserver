
insert into users (id, user_name, email, password) values (-1, 'user', 'email@gmx.com', 'passme');
insert into users (id, user_name, email, password) values (-2, 'hehe_cat2.0', 'ema_il@gmx.com', 'cGFzc21leW95b3lv');
insert into users (id, user_name, email, password) values (-3, 'FAnDave', 'ichliebejuicewrld@gmx.com', 'passme');

insert into theme(id, name, thumbnail_url, container_url, container_mat_url, light_intensity) values (-1, 'Edgy Teen', '~/test.c4d', '~/test.c4d', '~/test.c4d', 0.0 );
insert into theme(id, name, thumbnail_url, container_url, container_mat_url, light_intensity) values (-2, 'Happy Holiday', '~/test.c4d', '~/test.c4d', '~/test.c4d', 0.0 );

insert into exhibition(id, title, user_id, thumbnail_url, description) values (-1, 'Fotos von Künstlern', -1, 'http://3.bp.blogspot.com/-5rLnT_OE6yo/T4078obu5yI/AAAAAAAAAPo/Z-5Xo9lGpnQ/s1600/Marilyn-Monroe-Pop-Art.jpg', 'ich liebe diese künstler so sehr, deren kunst ist erstaundlich');
insert into exhibition(id, title, user_id, thumbnail_url, description) values (-2, 'Fotos von meinem Huhn', -1, 'https://www.huehner-haltung.de/img/rhodelaender-huhn-768x768.jpg', 'Hühner sind die besten Haustiere');
insert into exhibition(id, title, user_id, thumbnail_url, description) values (-3, 'Nudeln mhh', -2, 'src/main/resources/files/file2spaghetti-mhhh.jpg', 'mhhhhhhhhh pasta');
insert into exhibition(id, title, user_id, thumbnail_url, description) values (-4, 'Pingu', -2, 'https://wallsdesk.com/wp-content/uploads/2017/01/Penguin-for-desktop.jpg', 'pengpeng');

insert into exhibit(id, title, scale, alignment) values (-1, 'Picasso Foto', 0, 'c');
insert into exhibit(id, title, scale, alignment, url, exhibition_id) values (-2, 'Geburt.', 0, 'c', 'https://www.agrarheute.com/sites/agrarheute.com/files/styles/ah_bildergalerie_standalone_5x4/public/thumbnails/image/ei-kueken.jpg?itok=IGZdghSz', -2);
insert into exhibit(id, title, scale, alignment, url, exhibition_id) values (-3, 'Freunde.', 0, 'c', 'https://img.fotocommunity.com/hahn-im-korb-bielefelder-kennhuehner-huehnerschar-mit-kraehendem-hahn-19460011-fd7e-4c9f-961e-aeba8b21b00a.jpg?width=1000', -2);
/* insert into exhibit(id, title, url, exhibition_id) values (-4, 'Stand', 'file0BodyPaint_Pinguin.c4d', -4); */

insert into category(id, category_title, color) values (-1, 'Umwelt', '#C1BAFF');
insert into category(id, category_title, color) values (-2, 'Tiere', '#ADD0FF');
insert into category(id, category_title, color) values (-3, 'Essen', '#E2ADFF');

insert into exhibitions_categories(category_id, exhibition_id) values (-1,-2);
insert into exhibitions_categories(category_id, exhibition_id) values (-2,-2);
insert into exhibitions_categories(category_id, exhibition_id) values (-3,-3);

insert into room(id, name, wall_mat_url, floor_mat_url) values (-1, 'Cross', 'src/nopath', 'src/nopath');

/* special test cases

insert into exhibition(id, title, user_id, thumbnail_url) values (1, 'Fotos von Künstlern', -1, 'http://3.bp.blogspot.com/-5rLnT_OE6yo/T4078obu5yI/AAAAAAAAAPo/Z-5Xo9lGpnQ/s1600/Marilyn-Monroe-Pop-Art.jpg');
insert into exhibition(id, title, user_id, thumbnail_url) values (2, 'Fotos von meinem Huhn', -1, 'https://www.huehner-haltung.de/img/rhodelaender-huhn-768x768.jpg');
insert into exhibition(id, title, user_id, thumbnail_url) values (3, 'Sphaghet mhhhhh', -2, 'https://www.marions-kochbuch.de/rezept/0591.jpg');
insert into exhibition(id, title, user_id, thumbnail_url) values (4, 'Rise and Fall', -3, 'http://www.monster-clip.com/images/maps/mw2/gulag/gulag1.jpg');
insert into exhibition(id, title, user_id, thumbnail_url) values (5, 'hoi hoi hoi, toi toi toi', -3, 'https://i.pinimg.com/originals/cf/37/25/cf3725c70291c54d1ada2c6d575ced42.jpg');
insert into exhibition(id, title, user_id, thumbnail_url) values (6, 'JUICEWRLD', -3, 'https://wallpapercave.com/wp/wp6812137.jpg');
*/