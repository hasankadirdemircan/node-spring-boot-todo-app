FROM node:8-alpine

COPY . /home/app/ui

WORKDIR /home/app/ui
ENV NODE_ENV docker

RUN npm install

CMD ["npm","start"]

EXPOSE 8082

