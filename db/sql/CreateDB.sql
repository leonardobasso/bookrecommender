CREATE TABLE Libro
(
    id          INT PRIMARY KEY,
    Nome        VARCHAR(255)   NOT NULL,
    Autore      VARCHAR(255)   NOT NULL,
    Descrizione VARCHAR(255),
    Categoria   VARCHAR(255),
    Publisher   VARCHAR(255)   NOT NULL,
    Prezzo      DECIMAL(10, 4) NOT NULL,
    MesePub     VARCHAR(20)    NOT NULL,
    AnnoPub     INT            NOT NULL
);

CREATE TABLE Utente
(
    UserId   VARCHAR(255) PRIMARY KEY,
    Password VARCHAR(255) NOT NULL,
    Nome     VARCHAR(255) NOT NULL,
    Cognome  VARCHAR(255) NOT NULL,
    Taxcode  VARCHAR(255) UNIQUE CHECK (Utente.Taxcode ~ '^[A-Z]{6}[0-9]{2}[A-Z][0-9]{2}[A-Z][0-9]{3}[A-Z]$'),
    Email    VARCHAR(255) UNIQUE CHECK ( Utente.Email ~ '^[A-Za-z0-9._+%-]+@[A-Za-z0-9.-]+[.][A-Za-z]+$')
);

CREATE TABLE Libreria
(
    LibreriaId   SERIAL PRIMARY KEY,
    NomeLibreria VARCHAR(255) NOT NULL,
    UserId       VARCHAR(255) REFERENCES utente (UserId)
);

CREATE TABLE LibroInLibreria
(
    LibroId    INT REFERENCES Libro (id),
    LibreriaId INT REFERENCES Libreria (LibreriaId),
    PRIMARY KEY (LibroId, LibreriaId)
);

CREATE TABLE Libriconsigliati
(
    UserId             VARCHAR(255) REFERENCES utente (UserId),
    LibreriaId         INT REFERENCES libreria (LibreriaId),
    LibroConsigliatoId INT REFERENCES libro (id),
    LibroDeiConsigliId INT REFERENCES libro (id),
    PRIMARY KEY (UserId, LibroConsigliatoId, LibroDeiConsigliId)
);

CREATE TABLE Recensione
(
    UserId               VARCHAR(255) REFERENCES utente (UserId),
    LibroId              INT REFERENCES libro (id),
    StileVoto            SMALLINT CHECK (StileVoto BETWEEN 1 AND 5),
    ContenutoVoto        SMALLINT CHECK (ContenutoVoto BETWEEN 1 AND 5),
    GradevolezzaVoto     SMALLINT CHECK (GradevolezzaVoto BETWEEN 1 AND 5),
    OriginalitaVoto      SMALLINT CHECK (OriginalitaVoto BETWEEN 1 AND 5),
    EdizioneVoto         SMALLINT CHECK (EdizioneVoto BETWEEN 1 AND 5),
    VotoFinale           FLOAT CHECK (VotoFinale BETWEEN 1 AND 5),
    StileCommento        VARCHAR(255),
    ContenutoCommento    VARCHAR(255),
    GradevolezzaCommento VARCHAR(255),
    OriginalitaCommento  VARCHAR(255),
    EdizioneCommento     VARCHAR(255),
    PRIMARY KEY (UserId, LibroId)
);

