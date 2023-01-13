--INSERT INTO role(name) VALUES ("ROLE_ADMIN"),("ROLE_USER"),("ROLE_EDITOR");

INSERT INTO user(email,password ) VALUES ("rai121@gmail.com","121121") , ("a@a.com","azerty" ),("rai@gmail.com","$2a$10$yXq2KL7Zr6fHgOGt7rzbZ.e3Ns1b6b16H9RHbWmsTLezA7cPXt4H6" ),("dddd@ddd.com","1234");


INSERT INTO administrator(id,super_admin ) VALUES (1,1),(3,1);


INSERT INTO module(name,description, referent_id) VALUES ("CDA","Concepteurs développeurs d'application" , 1), ("DWWM","Développeur Web et Web Mobile" , 3);


INSERT INTO user_module( user_id, module_id ) VALUES (1,1),(1,2),(2,2),(4,2),(4,1);


--INSERT INTO user_role( user_id, role_id) VALUES (1,2),(2,2),(3,1),(3,2),(4,1),(4,3);