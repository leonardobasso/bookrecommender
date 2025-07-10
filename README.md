# Frontend

## Installare le dipendenze del programma

### Linux:

<details>
<summary>Debian/Ubuntu</summary>

```sh
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
```
</details>
<details>
<summary>Arch</summary>

```sh
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
```
</details>
<details>
<summary>Fedora</summary>

```sh
sudo dnf check-update
sudo dnf install webkit2gtk4.0-devel \
    openssl-devel \
    curl \
    wget \
    file \
    libappindicator-gtk3-devel \
    librsvg2-devel
sudo dnf group install "C Development Tools and Libraries"
```
</details>
<details> 
<summary>OpenSuse</summary>

```sh
sudo zypper up
sudo zypper in webkit2gtk3-soup2-devel \
    libopenssl-devel \
    curl \
    wget \
    file \
    libappindicator3-1 \
    librsvg-devel
sudo zypper in -t pattern devel_basis
```
</details>

È possibile installare Rust con

```sh
curl --proto '=https' --tlsv1.2 https://sh.rustup.rs -sSf | sh
```

### Windows:

È necessario installare i [Build Tools di C++](https://visualstudio.microsoft.com/visual-cpp-build-tools/)
e le webview2 di rust con

```sh
winget install --id Rustlang.Rustup
rustup default stable-msvc
```

Per far funzionare la prima volta il programma usare

```sh
cd frontend
npm install

```
### Primo avvio
>[!Important]
> È necessario avere [Node](https://nodejs.org/en) installato sul proprio computer.

Per far partire il programma usare 

```sh
npx tauri dev
```

Il programma è in Developer Mode

# Backend & DB

## Primo avvio
> [!important]
> È necessario avere [Docker](https://docs.docker.com/engine/install/) installato sul proprio computer.

Su Linux (SystemD) è necessario avviare Docker con:
```sh
sudo systemctl start docker
```

Su Windows tutti i comandi citati vanno eseguiti da amministratore

Per avviare il progetto usare nel terminale:

**Linux:**
```sh
sudo docker compose up --build
```
**Windows (Powershell)**
```sh
docker-compose up --build
```

Se vengono apportati cambiamenti al progetto non rilevati da docker, usare:

**Linux:**
```sh
sudo docker system prune -a
sudo docker compose up --build
```
**Windows (Powershell)**
```sh
docker system prune -a
docker-compose up --build
```

Per interrompere Docker è necessario scrivere:

**Linux**
```sh
sudo docker compose down
```
**Windows(Powershell)**
```sh
docker-compose down
```