CREATE TABLE LostPets (
    id bigserial NOT NULL PRIMARY KEY,
    ownerName character(25) NOT NULL ,
    email character (30) not null UNIQUE  ,
    phone character(10),
    message character (500) NOT NULL,
    neutered character (3),
    chipped character (3)
    );

INSERT INTO LostPets (ownerName, email, phone, message, neutered, chipped) VALUES ('Ion','ion@ion.ion','1234','hello','yes','yes');
