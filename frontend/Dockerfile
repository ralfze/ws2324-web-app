# Use node version 18 for the build stage
FROM node:18 AS build

# Set the working directory in the container
WORKDIR /app/

# Copy only the necessary files for dependency installation
COPY frontend/package.json /app/
COPY frontend/package-lock.json /app/
COPY frontend/.env.development.local /app/

# Install dependencies
RUN npm install

# Copy the rest of the application code
COPY frontend/public/ /app/public
COPY frontend/src/ /app/src

# Create react package in the builder
RUN npm run build

# Create smaller image to host the react server
FROM node:18-slim

# Set the working directory in the container
WORKDIR /app

# Copy only the necessary files from the build stage
COPY --from=build /app/build /app/build
COPY --from=build /app/package.json /app/
COPY --from=build /app/package-lock.json /app/
COPY --from=build /app/.env.development.local /app/

# Install production dependencies only
RUN npm install --only=production
RUN npm install -g serve

# Expose the port your app will run on
EXPOSE 3000

# Define the command to run your React app with serve
CMD ["serve", "-s", "build", "-l", "3000"]
