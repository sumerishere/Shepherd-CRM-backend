# Use the official MySQL image as the base image
FROM mysql:8.0

# Set environment variables for MySQL root user and database
ENV MYSQL_ROOT_PASSWORD=rootpassword
ENV MYSQL_DATABASE=templategen
ENV MYSQL_USER=myuser
ENV MYSQL_PASSWORD=mypassword

# Copy the database dump into the container
COPY backup.sql /docker-entrypoint-initdb.d/

# The database will be initialized with the dump upon container startup
