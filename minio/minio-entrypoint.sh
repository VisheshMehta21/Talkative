#!/bin/sh

# Run initialization script
if [ -f /etc/minio/init.sh ]; then
    echo "Running init script..."
    /etc/minio/init.sh
else
    echo "No init script found, skipping initialization..."
fi

# Start MinIO server
echo "Starting MinIO server..."
exec minio server /data --console-address ":9001"
