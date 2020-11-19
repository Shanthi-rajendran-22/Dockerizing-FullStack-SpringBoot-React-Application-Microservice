## Stage 1 - Lets build .gzip
FROM node:13.12.0-alpine as frontend-build
WORKDIR /hello-world/frontend

# Step 1 - Download dependency
COPY package*.json ./
RUN npm install

# Step 2 - Copy all source and run build
COPY . ./
RUN npm run build

## Stage 2 - Let's build a minimal image with .gzip
FROM nginx:1.12-alpine
COPY --from=frontend-build /hello-world/frontend/build /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]