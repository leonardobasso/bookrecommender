#!/bin/bash
set -e

echo "Downloading public key from Codeberg..."
curl -s -o key.pub https://codeberg.org/leonardobasso/pubkeys/raw/branch/main/rpm/key.pub

echo "Importing public key..."
sudo rpm --import key.pub

echo "Installing the rpm..."
sudo rpm -i ./book-recommender-0.1.0-1.x86_64.rpm
echo "Installed"
