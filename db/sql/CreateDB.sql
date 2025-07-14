CREATE TABLE Libro
(
    id          INT PRIMARY KEY,
    nome        VARCHAR(255)   NOT NULL,
    autore      VARCHAR(255)   NOT NULL,
    descrizione VARCHAR(255),
    categoria   VARCHAR(255),
    publisher   VARCHAR(255)   NOT NULL,
    prezzo      DECIMAL(10, 4) NOT NULL,
    mesepub     VARCHAR(20)    NOT NULL,
    annopub     INT            NOT NULL
);

CREATE TABLE Utente
(
    userid   VARCHAR(255) PRIMARY KEY,
    password VARCHAR(255) NOT NULL,
    nome     VARCHAR(255) NOT NULL,
    cognome  VARCHAR(255) NOT NULL,
    taxcode  VARCHAR(255) UNIQUE CHECK (Utente.taxcode ~ '^[A-Z]{6}[0-9]{2}[A-Z][0-9]{2}[A-Z][0-9]{3}[A-Z]$'),
    email    VARCHAR(255) UNIQUE CHECK ( Utente.email ~ '^[A-Za-z0-9._+%-]+@[A-Za-z0-9.-]+[.][A-Za-z]+$')
);

CREATE TABLE Libreria
(
    libreriaid   SERIAL PRIMARY KEY,
    nomelibreria VARCHAR(255) UNIQUE NOT NULL,
    userid       VARCHAR(255) REFERENCES utente (userid)
);

CREATE TABLE LibroInLibreria
(
    libroid    INT REFERENCES Libro (id),
    libreriaid INT REFERENCES Libreria (libreriaid),
    PRIMARY KEY (libroid, libreriaid)
);

CREATE TABLE ConsigliLibri
(
    userid             VARCHAR(255) REFERENCES utente (userid),
    libroconsigliatoid INT REFERENCES libro (id),
    librodeiconsigliid INT REFERENCES libro (id),
    PRIMARY KEY (userid, libroconsigliatoid, librodeiconsigliid)
);

CREATE TABLE Recensione
(
    userid               VARCHAR(255) REFERENCES utente (userid),
    libroid              INT REFERENCES libro (id),
    stilevoto            SMALLINT CHECK (stilevoto BETWEEN 1 AND 5),
    contenutovoto        SMALLINT CHECK (contenutovoto BETWEEN 1 AND 5),
    gradevolezzavoto     SMALLINT CHECK (gradevolezzavoto BETWEEN 1 AND 5),
    originalitavoto      SMALLINT CHECK (originalitavoto BETWEEN 1 AND 5),
    edizionevoto         SMALLINT CHECK (edizionevoto BETWEEN 1 AND 5),
    votofinale           FLOAT CHECK (votofinale BETWEEN 1 AND 5),
    stilecommento        VARCHAR(255),
    contenutocommento    VARCHAR(255),
    gradevolezzacommento VARCHAR(255),
    originalitacommento  VARCHAR(255),
    edizionecommento     VARCHAR(255),
    commentofinale       VARCHAR(255),
    PRIMARY KEY (userid, libroid)
);

