CREATE DATABASE "Emotional_Songs" IF NOT EXISTS;

CREATE TABLE "Utenti_Registrati" (
    id SERIAL PRIMARY KEY,
    cod_fiscale CHAR(16) NOT NULL,
    nome VARCHAR(50) NOT NULL,
    cognome VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    psw VARCHAR(255) NOT NULL,
    indirizzo VARCHAR(100) NOT NULL,
    cap CHAR(5) NOT NULL,
    comune VARCHAR(50) NOT NULL,
    provincia VARCHAR(50) NOT NULL,
    UNIQUE(email, cod_fiscale)
);

CREATE TABLE "Stati_Emozionali" (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(50) UNIQUE,
    descrizione VARCHAR(150)
);

CREATE TABLE "Playlists" (
    id SERIAL PRIMARY KEY,
    id_utente INT REFERENCES "Utenti_Registrati" ON UPDATE CASCADE,
    nome VARCHAR(50),
    UNIQUE(id_utente, nome)
);

CREATE TABLE "Canzoni" (
    id SERIAL PRIMARY KEY,
    autore VARCHAR(300),
    titolo VARCHAR(300),
    anno INT NOT NULL,
    id_genere INT NOT NULL REFERENCES "Stati_Emozionali" ON UPDATE CASCADE,
    durata_ms BIGINT NOT NULL,
    UNIQUE(autore, titolo)
);

CREATE TABLE "Assegnazioni_Canzoni" (
    id SERIAL PRIMARY KEY,
    id_playlist INT NOT NULL REFERENCES "Playlists" ON UPDATE CASCADE,
    id_canzone INT NOT NULL REFERENCES "Canzoni" ON UPDATE CASCADE,
    UNIQUE(id_playlist, id_canzone)
);

CREATE TABLE "Emozioni" (
    id SERIAL PRIMARY KEY,
    id_assegnazione INT NOT NULL REFERENCES "Assegnazioni_Canzoni" ON UPDATE CASCADE,
    id_stato_emozionale INT NOT NULL REFERENCES "Stati_Emozionali" ON UPDATE CASCADE,
    intensita INT NOT NULL,
    nota VARCHAR(255) DEFAULT NULL,
    CHECK(intensita BETWEEN 1 AND 5),
    UNIQUE(id_assegnazione, id_emozione)
);
