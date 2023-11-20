# Use node version 18
FROM node:18

# Set the working directory in the container
WORKDIR /app/

# Copy all folders
COPY diceui/public/ /app/public
COPY diceui/src/ /app/src
COPY diceui/package.json /app/

# Install dependecies
RUN npm install

# Expose the port your app will run on
EXPOSE 3000

# Define the command to run your React app
CMD ["npm", "start"]