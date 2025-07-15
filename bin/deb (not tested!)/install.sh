#!/bin/bash
set -e

KEY_URL="https://codeberg.org/leonardobasso/pubkeys/raw/branch/main/rpm/key.pub"
KEY_FILE="key.pub"
DEB_FILE="./book-recommender_0.1.0_amd64.deb"

echo "Downloading public key from Codeberg..."
curl -s -o key.pub https://codeberg.org/leonardobasso/pubkeys/raw/branch/main/rpm/key.pub

echo "Importing public key..."
sudo apt-key add "key.pub"

echo "📦 install..."
sudo dpkg -i "key.pub" || true
sudo apt-get install -f -y

echo "Installed"

