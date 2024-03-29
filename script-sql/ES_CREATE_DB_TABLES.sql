DROP DATABASE IF EXISTS Emotional_Songs;
CREATE DATABASE Emotional_Songs;

CREATE TABLE Utenti_Registrati (
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

CREATE TABLE Stati_Emozionali (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(50) UNIQUE,
    descrizione VARCHAR(150)
);

CREATE TABLE Playlists (
    id SERIAL PRIMARY KEY,
    id_utente INT 
        REFERENCES Utenti_Registrati(id) 
            ON UPDATE CASCADE 
            ON DELETE CASCADE,
    nome VARCHAR(50),
    UNIQUE(id_utente, nome)
);

CREATE TABLE Generi_Musicali (
    id INT PRIMARY KEY,
    nome VARCHAR(20) UNIQUE
);

CREATE TABLE Canzoni (
    id SERIAL PRIMARY KEY,
    autore VARCHAR(150),
    titolo VARCHAR(150),
    anno INT NOT NULL,
    id_genere INT NOT NULL 
        REFERENCES Generi_Musicali(id) 
            ON UPDATE CASCADE,
    durata_ms BIGINT NOT NULL,
    UNIQUE(autore, titolo)
);

CREATE TABLE Assegnazioni_Canzoni (
    id SERIAL PRIMARY KEY,
    id_playlist INT NOT NULL 
        REFERENCES Playlists(id) 
            ON UPDATE CASCADE 
            ON DELETE CASCADE,
    id_canzone INT NOT NULL 
        REFERENCES Canzoni(id) 
            ON UPDATE CASCADE 
            ON DELETE CASCADE,
    UNIQUE(id_playlist, id_canzone)
);

CREATE TABLE Emozioni (
    id SERIAL PRIMARY KEY,
    id_assegnazione INT NOT NULL 
        REFERENCES Assegnazioni_Canzoni(id) 
            ON UPDATE CASCADE,
    id_stato_emozionale INT NOT NULL
        REFERENCES Stati_Emozionali(id) 
            ON UPDATE CASCADE,
    intensita INT NOT NULL,
    nota VARCHAR(255) DEFAULT NULL,
    CHECK(intensita BETWEEN 1 AND 5),
    UNIQUE(id_assegnazione, id_stato_emozionale)
);

INSERT INTO Generi_Musicali VALUES
(1,'Blues'), (2,'Black Metal'), (3,'Country'), (4,'Dark'),
(5,'Death Metal'), (6,'Folk'), (7,'Funky'), (8,'Gospel'),
(9,'Hard Rock'), (10,'Hip-Hop'), (11,'Industrial'), (12,'Jazz'),
(13,'Liscio'), (14,'Metal'), (15,'Musica classica'), (16,'Musica dance'),
(17,'New-Wave'), (18,'Pop'), (19,'Punk Rock'), (20,'Rock & roll'),
(21,'Latina'), (22,'Underground');

INSERT INTO Stati_Emozionali VALUES
(1,'Amazement','Feeling of wonder or happiness'),
(2,'Solemnity','Feeling of transcendence, inspiration. Thrills.'),
(3,'Tenderness','Sensuality, affect, feeling of love'),
(4,'Nostalgia','Dreamy, melancholic, sentimental feelings'),
(5,'Calmness','Relaxation, serenity, meditativeness'),
(6,'Power','Feeling of strong, heroic, triumphant, energetic'),
(7,'Joy','Feels like dancing, bouncy feeling, animated, amused'),
(8,'Tension','Feeling nervous, impatient, irritated'),
(9,'Sadness','Feeling depressed, sorrowful');
