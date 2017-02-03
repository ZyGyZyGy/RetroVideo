-- SET SQL_SAFE_UPDATES = 0;
select *
from films;


select *
from films inner join genres
on films.genreid = genres.id
where naam = ?;


select films.id as filmid, genreid, titel, voorraad, gereserveerd, prijs
from films inner join genres
on films.genreid = genres.id
where genreid = 1
order by titel;


select titel
from films
where id = 1;


select films.id as filmid, genreid, titel, voorraad, gereserveerd, prijs
from films inner join genres
on films.genreid = genres.id
order by titel;


select id, familienaam, voornaam, straatNummer, postcode, gemeente
from klanten
where familienaam like '%?%';


insert into reservaties(klantid, filmid, reservatieDatum)
values(?, ?, ?);

insert into reservaties(klantid, filmid, reservatieDatum)
values(1, 2, curdate());


update films
set gereserveerd = gereserveerd + 1
where id = ?;

select klantid, filmid, reservatieDatum
from reservaties
where klantid = 1 and filmid = 7;



-- RESERVATIES
select films.titel, concat(voornaam, " ", familienaam) as naam
from reservaties inner join klanten
on reservaties.klantid = klanten.id
inner join films
on reservaties.filmid = films.id;

select *
from reservaties;

delete from reservaties
where klantid > 0;



-- FILMS
select id, titel, voorraad, gereserveerd
from films;

select *
from films;

update films
set gereserveerd = 0
where gereserveerd > 0;

















