CREATE TABLE LostPets (
    id bigserial NOT NULL PRIMARY KEY,
    ownerName character(25) NOT NULL ,
    email text not null UNIQUE  ,
    phone int (min 10, max 10) NOT NULL,
    message char (500) NOT NULL;
    addedAt Date not null default CURRENT_DATE
);

INSERT INTO LostPets (ownerName, email, phonem, message) VALUES ('LostPet','text 1','text2','text 3','text 4','text 5');
