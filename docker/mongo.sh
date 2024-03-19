docker run --name mongo-quarkus -d -p 27017:27017 \
    -e MONGO_INITDB_ROOT_USERNAME=root \
    -e MONGO_INITDB_ROOT_PASSWORD=root \
    -e MONGO_INITDB_DATABASE=quarkus \
    mongo:7-nanoserver