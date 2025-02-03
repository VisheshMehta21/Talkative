#!/bin/bash

# Configure MinIO Client (mc)
mc alias set myminio http://minio:9000 $MINIO_ROOT_USER $MINIO_ROOT_PASSWORD

# Create a bucket 
mc mb myminio/talkative

# Set the access policy of the bucket to private
mc policy set private myminio/talkative

# Sleep to keep the script running.
sleep infinity