# Simple project on Spring with Netty

## Features:
- Welcome html
- Posts rest service (one by id, all)
- Echo service - return all request variables
- Server events example (https://developer.mozilla.org/en-US/docs/Web/API/Server-sent_events)

## Build and run
```
mvn spring-boot:run
```

### Open
http://localhost:8090/

Should see this:
```
Hello from Netty!
Mapped paths:

- /
- echo service
- all posts
- post 1
- post 2
- post 3
- post 4
- server events
```

### Running on GraalVM

https://medium.com/graalvm/instant-netty-startup-using-graalvm-native-image-generation-ed6f14ff7692

### Windows GraalVM

http://download.oracle.com/otn/utilities_drivers/oracle-labs/graalvm-ee-1.0.0-rc12-snapshot-windows-amd64.zip
