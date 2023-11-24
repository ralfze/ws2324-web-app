# Use node version 18
FROM node:18 AS build

# Set the working directory in the container
WORKDIR /app/

# Copy all folders
COPY frontend/public/ /app/public
COPY frontend/src/ /app/src
COPY frontend/package.json /app/

# Install dependencies
RUN npm install

# Create react package in the builder
RUN npm run build

# Create smaller image to host the react server
FROM node:18-slim

# Copy build
COPY --from=build /app/ /app/

# Expose the port your app will run on
EXPOSE 3000

# Define the command to run your React app
CMD ["npm", "start"]