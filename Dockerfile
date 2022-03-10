FROM node:16-alpine

WORKDIR /frontend
COPY client .
RUN npm ci

EXPOSE 3000
ENTRYPOINT npm run start
