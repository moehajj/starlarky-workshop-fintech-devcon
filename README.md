# starlarky-workshop-fintech-devcon

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

- (optional) [Docker](https://docs.docker.com/get-docker/)

#### Simple Client
- [grpcurl](https://github.com/fullstorydev/grpcurl#installation)
- [yq](https://github.com/fullstorydev/grpcurl#installation)

## Configure Maven
Configure maven to retrieve Github artifacts using [this guide](https://github.com/verygoodsecurity/starlarky/blob/master/README.md#developer-setup).

# Build & Run

#### Using Java

```shell
mvn clean install -DskipTests
java \
  -Dgrpc.port=6565 \
  -jar ./target/starlarky-workshop-fintech-devncon-1.0.jar
```

#### Using Docker

```
docker-compose run build
doker-compose up --build -d starlarky-service
```


# Test
```shell
./simple_client.sh
```
