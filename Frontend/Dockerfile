# Step 1: Use a Node.js image to build the React app
FROM node:18 as build

# Set the working directory inside the container
WORKDIR /app

# Copy package.json and package-lock.json
COPY package.json package-lock.json ./

# Install dependencies
RUN npm install

# Install nodemon globally for live-reloading
RUN npm install -g nodemon

# Copy the rest of the application code
COPY . .

# Step 2: Expose port 3000 for development mode
EXPOSE 3000

# Start the React app with nodemon for auto-reloading
CMD ["nodemon", "npm", "start"]
