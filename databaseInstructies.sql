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


select *
from reservaties;


insert into reservaties(klantid, filmid, reservatieDatum)
values(?, ?, ?);

insert into reservaties(klantid, filmid, reservatieDatum)
values(1, 2, curdate());


update films
set gereserveerd = gereserveerd + 1
where id = ?








