# adservice

This project is an alternative implementation of the adservice in the [Online Boutique](https://github.com/GoogleCloudPlatform/microservices-demo) microservices demo application.
This implementation uses Quarkus and the gRPC extension.

As in the original implementation, the service listens on port 9555

To package to a java application:

    mvn package

To package to a java application and deploy to an openshift cluster:

    mvn package -Dquarkus.kubernetes.deploy=true

To package to a native application using GraalVM:

    mvn package -Pnative
