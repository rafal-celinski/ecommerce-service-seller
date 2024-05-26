FROM node:14
WORKDIR /app
COPY . .
# RUN echo 'REACT_APP_API_URL="http://api:8080"' > .env
RUN npm install
RUN npm run build
RUN npm install -g serve
EXPOSE 5000
ENTRYPOINT ["serve", "-s", "build", "-l", "5000"]