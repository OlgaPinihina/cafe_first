insert into cafe (id, name) values ('fa59ad02-bfe3-4567-a6cf-390ba176f6ab', 'BoB_cafe');
insert into cafe (id, name) values ('d40fdec5-6fdb-4915-91cb-e91c7464abc5', 'Patric_cafe');
insert into cafe (id, name) values ('cfb5b181-cbf4-4f59-aa84-1cb9e945a95c', 'Lulu_cafe');

insert into coffee (id, name, price, is_new, is_hot, description, picture, cafe_id) values ('bd516c3e-1a2f-479a-8673-64003bba73c0', 'Black', 1.2, false, false, 'ice or hot', 'foto2.jpg', 'fa59ad02-bfe3-4567-a6cf-390ba176f6ab');
insert into coffee (id, name, price, is_new, is_hot, description, picture, cafe_id) values ('b4957f07-46ba-444f-9ec3-7ccaa9811653', 'Black', 1.3, false, true, 'hot', 'foto6.jpg', 'd40fdec5-6fdb-4915-91cb-e91c7464abc5');
insert into coffee (id, name, price, is_new, is_hot, description, picture, cafe_id) values ('850399d5-4449-42ea-ae0f-e56f7a3f4ad9', 'Black', 1.4, true, false, 'ice', 'foto4.jpg', 'cfb5b181-cbf4-4f59-aa84-1cb9e945a95c');

insert into coffee (id, name, price, is_new, is_hot, description, picture, cafe_id) values ('b6f09c5c-2a4a-4f78-ba79-980c00632752', 'Cappuccino', 1.5, true, true, 'lactic-free!', 'foto1.jpg', 'fa59ad02-bfe3-4567-a6cf-390ba176f6ab');
insert into coffee (id, name, price, is_new, is_hot, description, picture, cafe_id) values ('5f5dcfa0-6cc6-419b-be8d-a5702906761c', 'Cappuccino', 1.6, true, false, 'coconut milk', 'foto5.jpg', 'd40fdec5-6fdb-4915-91cb-e91c7464abc5');
insert into coffee (id, name, price, is_new, is_hot, description, picture, cafe_id) values ('75e344de-27d9-4c38-97f8-87093e935762', 'Cappuccino', 1.7, false, true, 'soy milk', 'foto3.jpg', 'cfb5b181-cbf4-4f59-aa84-1cb9e945a95c');

insert into coffee (id, name, price, is_new, is_hot, description, picture, cafe_id) values ('0b0eac25-001c-4c3f-8a2c-ae92d8d140aa', 'Irish coffee', 1.8, true, true, 'ice or hot', 'foto7.jpg', 'd40fdec5-6fdb-4915-91cb-e91c7464abc5');

insert into orders (id, order_date) values ('13dac2b8-6880-4c6e-8bea-1b1c10968ef6', '2023-02-20 19:32:30');
insert into orders (id, order_date) values ('13dac2b8-6880-4c6e-8bea-1b1c10968ef7', '2023-02-20 19:35:30');

-- orders_coffees_list  @ManyToMany
insert into orders_coffees_list (orders_list_id, coffees_list_id) values ('13dac2b8-6880-4c6e-8bea-1b1c10968ef6', '0b0eac25-001c-4c3f-8a2c-ae92d8d140aa');
insert into orders_coffees_list (orders_list_id, coffees_list_id) values ('13dac2b8-6880-4c6e-8bea-1b1c10968ef6', '5f5dcfa0-6cc6-419b-be8d-a5702906761c');
insert into orders_coffees_list (orders_list_id, coffees_list_id) values ('13dac2b8-6880-4c6e-8bea-1b1c10968ef6', '75e344de-27d9-4c38-97f8-87093e935762');
insert into orders_coffees_list (orders_list_id, coffees_list_id) values ('13dac2b8-6880-4c6e-8bea-1b1c10968ef6', '75e344de-27d9-4c38-97f8-87093e935762');

insert into orders_coffees_list (orders_list_id, coffees_list_id) values ('13dac2b8-6880-4c6e-8bea-1b1c10968ef7', '75e344de-27d9-4c38-97f8-87093e935762');

insert into roles (id, name) values ('eb8f4b2c-df05-4f05-857d-46f4ff75eb62','ROLE_ADMIN');
insert into roles (id, name) values ('ff87dbfd-9f0e-48f6-9001-9e5091dc9a9b','ROLE_USER');

-- insert into users (id, login, name, password) values ('fab092ee-9b1a-4913-9de8-d7beb9fb864b', 'admin', 'John', 'admin');
-- insert into users (id, login, name, password) values ('c15edd0e-0eb7-4982-ad4b-f05ea0ef98cd', 'user', 'Ann', 'user');
insert into users (id, login, name, password) values ('fab092ee-9b1a-4913-9de8-d7beb9fb864b', 'admin', 'John', '$2a$10$9zELWTjgWlR4mvCL0M10.eVSXulcwtQ3kfQ/7Rrfh7KnRAyYx/iES');
insert into users (id, login, name, password) values ('c15edd0e-0eb7-4982-ad4b-f05ea0ef98cd', 'user', 'Ann', '$2a$10$X/p2DcfMjwiXE/Ae64cuheVyrZUJg3Yekvb5kBH1PQxYlkSvvRMH.');


insert into users_roles (users_id, roles_id) values ('fab092ee-9b1a-4913-9de8-d7beb9fb864b', 'eb8f4b2c-df05-4f05-857d-46f4ff75eb62');
insert into users_roles (users_id, roles_id) values ('c15edd0e-0eb7-4982-ad4b-f05ea0ef98cd', 'ff87dbfd-9f0e-48f6-9001-9e5091dc9a9b');
