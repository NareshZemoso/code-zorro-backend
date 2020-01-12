FROM tetafro/golang-gcc:latest as builder
#Keeping bash for development purpose must be removed later
RUN apk add --nocache python3-dev bash
RUN mkdir /app
RUN mkdir /app/src
RUN mkdir /app/src/codes
RUN chmod -R 777 /app/src/codes
ADD ./src /app/src
WORKDIR /app/src
ENV "GOPATH" "/app"
RUN go build -o main .
RUN adduser -D -g '' appuser
USER appuser
EXPOSE 8083
CMD ["/app/src/main"]