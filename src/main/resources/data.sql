insert into writer (name,bio) values
( 'John Doe','Famous writer of fantasy tales'),('Jane Smith','Renowned journalist and editor'),('Emily Brontë','Author of Wuthering Heights'),
('Ernest Hemingway','Nobel Prize-winning author known for works like The Old Man and the Sea');


insert into magazine(title,publicationDate)values
('Fantasy Tales','2023-10-05'),('Journalist Weekly','2023-09-15'),('Classic Literature Monthly','2023-10-15'),('Modern Writers Digest','2023-09-20');

insert into writer_magazine(writerId,magazineId)values
(1,1),(1,2),(2,2),(3,3),(4,3),(4,4);

  