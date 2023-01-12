INSERT INTO role(name) VALUES ("ROLE_ADMIN"),("ROLE_USER"),("ROLE_EDITOR");

INSERT INTO user(email,password , role_id)
VALUES ("a@a.com","azerty" , NULL),("rai@gmail.com","121121" , 2),("rai121@gmail.com","121121",1),("dddd@ddd.com","1234",3);

INSERT INTO module(name,description) VALUES ("CDA","Concepteurs développeurs d'application"), ("DWWM","Développeur Web et Web Mobile");

INSERT INTO user_module( user_id, module_id) VALUES (1,1),(1,2),(2,2),(4,2),(4,1);