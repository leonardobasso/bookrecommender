----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

*************************************************************************************************************************

PROGETTO BOOK RECOMMENDER
LABORATORIO INTERDISCIPLINARE B, CORSO DI LAUREA TRIENNALE IN INFORMATICA 
UNIVERSITA' DEGLI STUDI DELL'INSUBRIA - COMO

PROGETTO REALIZZATO DA:
- Matricola 754687, Magni Giulia
	gmagni1@studenti.uninsubria.it
- Matricola 758987, Basso Leonardo
	lbasso@studenti.uninsubria.it
- Matricola 754625, Beretta Lorenzo
	lberetta3@studenti.uninsubria.it

*************************************************************************************************************************

++CONTENUTI DELL'ARCHIVIO++
	--> Eseguibili BookRecommender
	--> Autori.txt
	--> Manuale_Tecnico.pdf
	--> Manuale_Utente.pdf

++REQUISITI: ++
L'applicazione richiede Java JDK 17 e il sistema operativo Windows 10/11 e Linux.

++NOTA: ++
l'applicazione è stata sviluppata e testata su Windows 10/11 e Linux.
I file *.csv sono necessari per il funzionamento dell'applicazione.

++Installazione programma++
	Frontend
Vi si potrà accedere tramite eseguibile all'interno della cartella /bin, per:
 Linux: (usare install.sh)
	.rpm, .deb(non testato)
 Windows: 
	.msi, .exe

Questa parte di Frontend, invece, è utile se si vuole compilare il progetto da sorgente.
Installare le dipendenze del programma:
 Linux:
 Debian/Ubuntu:
sudo apt update
sudo apt install libwebkit2gtk-4.0-dev \
    build-essential \
    curl \
    wget \
    file \
    libssl-dev \
    libgtk-3-dev \
    libayatana-appindicator3-dev \
    librsvg2-dev	

 Fedora
sudo dnf check-update
sudo dnf install webkit2gtk4.0-devel \
    openssl-devel \
    curl \
    wget \
    file \
    libappindicator-gtk3-devel \
    librsvg2-devel
sudo dnf group install "C Development Tools and Libraries"

 Arch
sudo pacman -Syu
sudo pacman -S --needed \
    webkit2gtk \
    base-devel \
    curl \
    wget \
    file \
    openssl \
    appmenu-gtk-module \
    gtk3 \
    libappindicator-gtk3 \
    librsvg \
    libvips	

 OpenSuse
sudo zypper up
sudo zypper in webkit2gtk3-soup2-devel \
    libopenssl-devel \
    curl \
    wget \
    file \
    libappindicator3-1 \
    librsvg-devel
sudo zypper in -t pattern devel_basis

È possibile installare Rust con: 
	curl --proto '=https' --tlsv1.2 https://sh.rustup.rs -sSf | sh

 Windows:
È necessario installare i Build Tools di C++ e le webview2 di rust con:
	winget install --id Rustlang.Rustup
	rustup default stable-msvc
Per far funzionare la prima volta il programma usare:
	cd frontend
	npm install

|| PRIMO AVVIO ||
Importante: 
È necessario avere Node installato sul proprio computer.
Per far partire il programma usare: npx tauri dev
Il programma è in Developer Mode.

-- .. -- .. -- .. -- .. -- .. -- .. -- .. -- .. -- .. -- .. -- .. -- .. -- .. -- .. -- .. -- .. -- .. -- .. 

	Backend & DB
||PRIMO AVVIO||
Importante: 
È necessario avere Docker installato sul proprio computer.
Prima di utilizzare Docker è necessario generare il jar andando nella cartella di backend ed eseguendo: mvn clean package ,oppure si può fare anche graficamente da un IDE.
Su Linux (SystemD) è necessario avviare Docker con: sudo systemctl start docker
Su Windows tutti i comandi citati vanno eseguiti da amministratore.

Per avviare il progetto usare nel terminale:
 Linux:
	sudo docker compose up --build
 Windows (Powershell):
	docker-compose up --build

Per interrompere Docker è necessario scrivere:
 Linux:
	sudo docker compose down
 Windows (Powershell):
	docker-compose down

++Risoluzione errore++
Se vengono apportati cambiamenti al progetto non rilevati da docker, usare:
 Linux:
	sudo docker system prune -a
	sudo docker compose up --build
 Windows (Powershell):
	docker system prune -a
	docker-compose up –build

Se si hanno problemi con le dipendenze di Tauri con errore: 
	%1 non è un’applicazione di Win32 valida. (os error 193)
Per risolvere seguire passo-passo i seguenti passaggi:
1. Verifica toolchain attiva rustup show  ti dirà il target predefinito.
2. Se non è x86_64-pc-windows-msvc, esegui:
	rustup default stable-x86_64-pc-windows-msvc
3. Spostati nella cartella src-tauri:
	cd .\frontend\src-tauri
4. Esegui il clean di Cargo:
	cargo clean
5. Torna alla cartella principale del frontend e rilancia Tauri:
	cd ..\..
	npx tauri dev

Se non cambia nulla provare a spegnere e riaccendere il computer.

