# adservice

This project is an alternative implementation of the adservice in the [Online Boutique](https://github.com/GoogleCloudPlatform/microservices-demo) microservices demo application.
This implementation uses Quarkus and the GRPC extension.

As in the original implementation, the service listens on port 9555

To package to a java application:

    mvn package

To package to a native application using GraalVM:

    mvn package -Pnative
