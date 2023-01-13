INSERT INTO role(name) VALUES ("ROLE_ADMIN"),("ROLE_USER"),("ROLE_EDITOR");

INSERT INTO user(email,password )
VALUES ("a@a.com","azerty" ),("rai@gmail.com","121121" ),("rai121@gmail.com","121121"),("dddd@ddd.com","1234");

INSERT INTO module(name,description) VALUES ("CDA","Concepteurs développeurs d'application"), ("DWWM","Développeur Web et Web Mobile");

INSERT INTO user_module( user_id, module_id) VALUES (1,1),(1,2),(2,2),(4,2),(4,1);
INSERT INTO user_role( user_id, role_id) VALUES (1,2),(2,2),(3,1),(3,2),(4,1),(4,3);