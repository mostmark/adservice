# adservice

This project is an alternative implementation of the adservice in the [Online Boutique](https://github.com/GoogleCloudPlatform/microservices-demo) microservices demo application.
This implementation uses Quarkus and the gRPC extension.

As in the original implementation, the service listens on port 9555

To package to a java application:

```
mvn package

```

The run the application by the following command:

```
java -jar target/adservice-1.0-runner.jar
```

You can then access a simple web interface on http://localhost:8080

To package to a java application and deploy to an openshift cluster:

```
mvn package -Dquarkus.kubernetes.deploy=true

```

To package to a native application using GraalVM:

```
mvn package -Pnative

```

You can the run the application by the following command:

```
./target/adservice-1.0-runner

```

To deploy this application from a prebuilt container image:

```
oc create -f https://raw.githubusercontent.com/mostmark/adservice/refs/heads/main/deployment.yaml

```

