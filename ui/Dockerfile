FROM node:8-alpine

COPY . /home/app/ui

WORKDIR /home/app/ui
ENV NODE_ENV docker
run apk --no-cache add --virtual native-deps \
  g++ gcc libgcc libstdc++ linux-headers make python && \
  npm install --quiet node-gyp -g &&\
  npm install --quiet && \
  apk del native-deps
RUN npm install

CMD ["npm","start"]

EXPOSE 8082

