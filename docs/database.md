Basic database exercise    
    
    arjen=# create user yourusername with password 'yourpassword';
    CREATE ROLE
    arjen=# create database fromnothing-test;
    ERROR:  syntax error at or near "-"
    LINE 1: create database fromnothing-test;
                                       ^
    arjen=# create database fromnothing_test;
    CREATE DATABASE
    arjen=# grant all privileges on database fromnothing_test to yourusername;
    GRANT

insert into actor (firstname, lastname, nickname, birthdate, credit)
  values ('Bruce', 'Willis', 'Bruno', '19-03-1955', 'Demi Moore');

insert into movie (year, title, rating)
  values (1998, 'Die Hard', 8.3);
  
insert into moviecredits (actor_id, movie_id, ranking)
  values (
    (select id from actor where firstname='Bruce' and lastname='Willis'),
    (select id from movie where title='Die Hard'),
    1
  );
  
insert into biography (actor_id, text)
 values (
    (select id from actor where firstname='Bruce' and lastname='Willis'),
    'Actor and musician Bruce Willis is well known for his film appearances as wisecracking or hard-edged characters, often in spectacular action films. Collectively, he has appeared in films that have grossed in excess of $2.5 billion USD placing him in the top ten stars in terms of box office receipts. Walter Bruce Willis was born on March 19, 1955 in West Germany to parents living on a United States military base, and raised in Penns Grove, New Jersey. Willis picked up an interest for the dramatic arts in high school, and was allegedly "discovered" whilst working in a café in New York City and then appeared in a couple of off-Broadway productions. While bartending one night, he was seen by a casting director who liked his personality and needed a bartender for a small movie role.'
 );
 
insert into quote (movie_id, text)
 values (
     (select id from movie where title='Die Hard'),
     'Harry Ellis: Hey babe, I negotiate million dollar deals for breakfast. I think I can handle this Eurotrash.'
 );
 
 insert into Trivia (actor_id, text)
 values( 
    (select id from actor where firstname='Bruce' and lastname='Willis'),
 'Stated he will no longer be doing violent action or "save-the-world" movies. [2001]'
 );
 
