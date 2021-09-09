# Starlarky Workshop (Fintech Devcon 2021)

# Agenda

### A. Build a Starlarky FaaS Server.
1. [Setup your environment](#setup).

2. Complete the [service code](https://github.com/moehajj/starlarky-workshop-fintech-devcon/blob/main/src/main/java/com/moehajj/spring/boot/grpc/example/StarlarkyService.java) 
   to integrate the Starlarky runtime and build your FaaS Server.

3. [Build](#build--run), [Test](#test), Iterate.

4. (extra) Checkout the [different features](https://github.com/verygoodsecurity/starlarky/tree/master/larky/src/main/resources) 
   already available in Starlarky, and try to build your own use-cases!
   
Solution: [starlarky-grpc-faas-example](https://github.com/moehajj/starlarky-grpc-faas-example)

### B. Implement a Custom Module.
Is there something you can't do with Starlarky? 
Do you want to access your own backend service or 3rd party providers (e.g, KYC provide, PII analytics, etc.), 
without exposing potentially unsafe language features to Starlarky directly? 

You can build a Starlarky module using Java to achieve just that!

1. Customize your own Starlarky [module](https://github.com/moehajj/starlarky-workshop-fintech-devcon/blob/main/src/main/java/com/moehajj/spring/boot/grpc/example/modules/CustomModule.java) 
   using Java.
   
2. Inject your custom module via input bindings.
   
   ```java
   bindings.put(“custom”, new CustomModule());
   ```

3. Invoke your custom module from inside the script.

   ```python
   ...
   custom.method(arg1, arg2, argName=arg3)
   ...
   ```
4. [Build](#build--run), [Test](#test), Iterate.

### C. Contribute to Starlarky!
Starlarky is open-source, 
which means we want the community to freely use, contribute and help build great solutions for everyone.

1. Clone the [Starlarky repository](github.com/verygoodsecurity/starlarky).
   
2. Contribute a [module](https://github.com/verygoodsecurity/starlarky/tree/master/larky/src/main/resources) 
   using Starlarky.

3. (extra) Try using [py2star](https://github.com/mahmoudimus/py2star)
   to port an existing module in python, or to write your module in python and convert it seamless.

4. Contribute a [module](https://github.com/verygoodsecurity/starlarky/tree/master/larky/src/main/java/com/verygood/security/larky/modules) 
   using Java.
   
5. [Build](#build--run) Starlarky, the [Starlarky version](https://github.com/verygoodsecurity/starlarky/blob/master/pom.xml#L9)
   is set to `1.0-SNAPSHOT` by default, you can choose to modify that if you like.
   
   Then update the Starlarky version in the [pom.xml](https://github.com/moehajj/starlarky-workshop-fintech-devcon/blob/main/pom.xml#L14) 
   of your FaaS solution to reflect these changes.
   
6. [Test](#test), Iterate.
 
# Setup
## Tools Required
#### Server
- Maven & Java 11
    -  Install using `sdkman` 
        ```shell
        curl -s "https://get.sdkman.io" | bash
        source ~/.sdkman/bin/sdkman-init.sh
        sdk install maven 3.6.3
        sdk install java 11.0.11.j9-adpt
        ```
    - Set `$JAVA_HOME` in your `~/.bash_profile`
        ```shell
        export JAVA_HOME="$HOME/.sdkman/candidates/java/current"
        ```

- (optional) Install [Docker](https://docs.docker.com/get-docker/) 
  and [docker-compose](https://docs.docker.com/compose/install/)

#### Simple Client
- Install [grpcurl](https://github.com/fullstorydev/grpcurl#installation)
- Install [yq](https://github.com/fullstorydev/grpcurl#installation)

## Configure Maven
Configure maven to retrieve Github artifacts using [this guide](https://github.com/verygoodsecurity/starlarky/blob/master/README.md#developer-setup).

# Build & Run

#### Using Java

You can build and run your application from the command line using maven and java.

Build.
```shell
mvn clean install -DskipTests
```

Run.
```shell
java \
  -Dgrpc.port=6565 \
  -jar ./target/starlarky-workshop-fintech-devncon-1.0.jar
```

#### Using Docker

You can deploy your FaaS using `docker-compose`.

Build.
```
docker-compose run build
```

Deploy.
```
doker-compose up --build -d starlarky-service
```

# Test
You can test your implementation using a `grpcurl` based client.

Try modifying the code and testing new features!
```shell
./simple_client.sh
```
