CREATE TABLE LostPets (
    id bigserial NOT NULL PRIMARY KEY,
    ownerName character(25) NOT NULL ,
    email text not null UNIQUE  ,
    phone int( max 10),
    message char (500) NOT NULL;
    neutered char (3);
    chipped char (3));

INSERT INTO LostPets (ownerName, email, phone, message, neutered, chipped) VALUES (?,?,?,?,?,?);
