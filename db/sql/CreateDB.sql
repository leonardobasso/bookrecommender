CREATE TABLE Utente
(
    UserId  VARCHAR(255) PRIMARY KEY,
    Nome    VARCHAR(255) NOT NULL,
    Cognome VARCHAR(255) NOT NULL,
    Taxcode VARCHAR(255) UNIQUE,
    Email   VARCHAR(255) UNIQUE
);

CREATE TABLE Libro
(
    id        VARCHAR(255) PRIMARY KEY,
    Nome      VARCHAR(255)   NOT NULL,
    Autore    VARCHAR(255)   NOT NULL,
    Categoria VARCHAR(255),
    Publisher VARCHAR(255)   NOT NULL,
    Prezzo    DECIMAL(10, 4) NOT NULL,
    MesePub   VARCHAR(20)    NOT NULL,
    AnnoPub   INT            NOT NULL
);

CREATE TABLE Libreria
(
    LibreriaId VARCHAR(255) PRIMARY KEY,
    UserId     VARCHAR(255) REFERENCES utente (UserId)
);

CREATE TABLE Libriconsigliati
(
    UserId     VARCHAR(255) REFERENCES utente (UserId),
    LibreriaId VARCHAR(255) REFERENCES libreria (LibreriaId),
    LibroConsigliatiId    VARCHAR(255) REFERENCES libro (id),
    LibroDeiConsigliId    VARCHAR(255) REFERENCES libro (id),
    PRIMARY KEY (UserId, LibroId)
);

CREATE TABLE Recensione
(
    UserId               VARCHAR(255) REFERENCES utente (UserId),
    LibroId              VARCHAR(255) REFERENCES libro (id),
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

