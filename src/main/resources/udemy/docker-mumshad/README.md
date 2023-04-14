# Docker for the Absolute Beginner - Mumshad Mannambeth

## Install Docker

On Linux: https://docs.docker.com/install/linux/docker-ce/ubuntu/

See the section: `Install using the convenience script`

## Docker Commands

### docker version

Check the Docker Server Engine version:

```shell
docker version
```

### docker run

To start a container:

```shell
docker run nginx
```

If you need to access the inside of the container: add ` -it` to the `docker run` command and then run the `bash` command:

```shell
docker run -it centos bash
```

To run the container in detach mode, add ` -d`:

```shell
docker run -d kodekloud/simple-webapp
```

To change the name of the container when running it: add ` --name the-name-you-want` in the run command:

```shell
docker run -d --name webapp nginx:1.14-alpine
```

To run a specific tag (version), you add a colon `:` after the name and then the tag. If you do not provide any tag, Docker by default uses the `latest` tag:

```shell
docker run redis:4.0
```

#### STDIN

By default, the docker container does not listen to standard input, even if you attach to the console.

The container does not have a terminal from where read input. It runs on a non-interactive mode.

If you want to map the STDIN from your host to the container, you have to run it on interactive mode by adding the ` -i` parameter:

```shell
docker run -i kodekloud/simple-prompt-docker
```

An add the parameter ` -t` for sudo terminal, so you can see STDOUT from container:

```shell
docker run -it kodekloud/simple-prompt-docker
```

#### Port Mapping

Use the parameter ` -p` to map a port: specify the docker host port you want to map, then a colon `:`, and finally the docker container port:

```shell
docker run -p 80:5000 kodekloud/webapp
```

#### Volume 

Use the parameter ` -v` to map a volume (folder): specify the docker host path you want to map, then a colon `:`, and finally the docker container path:

```shell
docker run -v /opt/datadir:/var/lib/mysql mysql
```

#### Restart Policy

Use the parameter ` --restart` to setup the restart policy, a value could be `always`:

```shell
docker run -d --restart always registry:2
```

#### Links (Deprecated)

IMPORTANT NOTE: this is deprecated.

In order to link one container to others, which is basically add a entry to the `hosts` file, you use the parameter ` --link`: specify the container name, then a colon `:`, and finally the alias:

```shell
docker run -d --name=redis redis

docker run -d --name=db postgres:9.4

docker run -d --name=vote -p 5000:80 --link redis:redis voting-app

docker run -d --name=result -p 5001:80 --link db:db result-app

docker run -d --name=worker --link db:db --link redis:redis worker
```

### docker attach

To attach back to a running container, you have to provide the `CONTAINER ID` or the `CONTAINER NAME`:

```shell
docker attach a043d
```

### docker ps

List running containers:

```shell
docker ps
```

List all containers, running and not running:

```shell
docker ps -a
```

### docker stop

To stop a container, you have to provide the `CONTAINER ID` or the `CONTAINER NAME`:

```shell
docker stop silly_sammet
```

### docker rm

To remove a container, you have to provide the `CONTAINER ID` or the `CONTAINER NAME`:

```shell
docker rm silly_sammet
```

You can provide multiple containers and remove them with a single command:

```shell
docker rm 34a e0a 4d5
```

### docker images

List images:

```shell
docker images
```

To remove all the dangling (without at least one container associated to them) images we have locally:

```shell
docker image prune -a
```

### docker rmi

To remove a docker image, you have to provide the `REPOSITORY` or the `IMAGE ID`:

```shell
docker rmi nginx
```

You need to delete first all the dependent containers in order to be able to delete it.

### docker pull

Pull an image:

```shell
docker pull nginx
```

### Append a command

To run a command immediately after running a container:

```shell
docker run ubuntu sleep 5
```

### exec - execute a command

To execute a command on an already running container. 

You have to provide the `CONTAINER ID` or the `CONTAINER NAME` and then the command you want to execute:

```shell
docker exec mycontainer cat /etc/hosts
```

### docker inspect

To see all details about a running container, you have to provide the `CONTAINER ID` or the `CONTAINER NAME`:

```shell
docker inspect blissful_hopper
```

### docker logs

To see the logs of an already running container in detach mode, you have to provide the `CONTAINER ID` or the `CONTAINER NAME`:

```shell
docker logs blissful_hopper
```

### Important things to know

The container only lives as long as the process inside it is alive. For example if the web service inside the container stops or crashes then the container exits.

When providing a `CONTAINER ID` to any of the commands, you can provide only the first characters that are different from the other containers on the host machine. For example: `docker attach a043d`

## Docker Images

### Dockerfile

To create your own image locally in your system, create a `Dockerfile` with the following instructions:

`FROM`: base image you want to use, usually an OS image. Every docker image must be based on another docker image. All Dockerfile must start with `FROM` instruction.

`RUN`: to run a specific command to set up the image.

`COPY`: to copy files from local system into the image.

`ENTRYPOINT`: allow us to specify a command that will be run when the image is run as a container.

This is an example of a Dockerfile:

```dockerfile
FROM ubuntu

RUN apt-get update && apt-get -y install python

RUN pip install flask flask-mysql

COPY . /opt/source-code

ENTRYPOINT  FLASK_APP=/opt/source-code/app.py flask run
```

To build the image, run the `build` command and use ` -f` to specify the `Dockerfile` and ` -t` to give it a name and tag:

```shell
docker build . -f Dockerfile -t mmumshad/my-custom-app
```

### docker push

Before pushing your image, you must tag it (use ` -t`) when building it:

```shell
docker build . -t mmumshad/my-simple-webapp
```

If you want to make the image available for everyone, push it to the Docker Hub using the `push` command:

```shell
docker push mmumshad/my-custom-app
```

You have to log in first before pushing an image:

```shell
docker login
```

### docker history

To see the history of an image, run the `history` command:

```shell
docker history mmumshad/simple-webapp
```

### Environment Variables

To add an environment variable when running a container, use the ` -e`:

```shell
docker run -e APP_COLOR=blue simple-webapp-color
```

To find out what are the environment variables of an already running docker container, use the `inspect` command, you have to provide the `CONTAINER ID` or the `CONTAINER NAME`:

```shell
docker inspect blissful_hopper
```

You will see the environment variables under the `Config` section, and then the `Env` section.

### Command vs Entrypoint

To override the default `CMD` command specified within the image, append a command to the `docker run` command:

```shell
docker run ubuntu [COMMAND]
```

For example:

```shell
docker run ubuntu sleep 5
```

If you want to make that change permanent, specify a new command, you can create your own image and add the `CMD` argument with the command you want to run:

To specify a command, use the `CMD` instruction:

```dockerfile
FROM ubuntu

CMD sleep 5
```

There are two ways of specify the `CMD` instruction:

```dockerfile
CMD command param1
```

```dockerfile
CMD ["command","param1"]
```

For example:

```dockerfile
CMD sleep 5
```

```dockerfile
CMD ["sleep","5"]
```

The `ENTRYPOINT` instruction is like the `CMD` instruction as you can specify the command that will run when the container starts.

If you append a command when running the container, it will get **appended** to the `ENTRYPOINT` command.

But if you only have a `CMD` specified in the image, when you append a command when running the container, the `CMD` instruction in the image will get **replaced** with the one specified in the `docker run` command.

To configure a default value to get appended to the `ENTRYPOINT` instruction, if there is no value specified when running the container, you use the `CMD` instruction:

```dockerfile
# this is the Dockerfile for ubuntu-sleeper
FROM ubuntu

ENTRYPOINT ["sleep"]

CMD ["5"]
```

IMPORTANT NOTE: both `ENTRYPOINT` and `CMD` must be specified in JSON format (array format) to make the append/replace work.

This command: `docker run ubuntu-sleeper` will append the default value specified in the `CMD` instruction in the image to the `ENTRYPOINT` command, so it will run: `sleep 5`

This command: `docker run ubuntu-sleeper 10` will append the `10` to the `ENTRYPOINT`, so it will run: `sleep 10`.

Finally, in case you want to override the `ENTRYPOINT` command, you can use the ` --entrypoint` and then the command: `docker run --entrypoint sleep2 ubuntu-sleeper 10`, so it will run: `sleep2 10`.

## Docker Compose

### docker-compose.yml

This is a file when you specify multiple containers, so you can run one or more containers:

Old way using only `docker run` command:

```shell
docker run -d --name=redis redis

docker run -d --name=db postgres:9.4

docker run -d --name=vote -p 5000:80 --link redis:redis voting-app

docker run -d --name=result -p 5001:80 --link db:db result-app

docker run -d --name=worker --link db:db --link redis:redis worker
```

New way using `docker-compose` command:

This is the `docker-compose.yml`:

```
redis:
  image: redis
db:
  image: postgres:9.4
vote:
  image: voting-app
  ports:
    - 5000:80
  links:
    - redis
result:
  image: result-app
  ports:
    - 5001:80
  links:
    - db
worker:
  image: worker
  links:
    - redis
    - db
```

Note: when specifying the `links` these both ways are the same:

```
worker:
  image: worker
  links:
    - redis
    - db
```

```
worker:
  image: worker
  links:
    - redis: redis
    - db: db
```

### docker-compose up

Once `docker-compose.yml` file is ready, you run the `docker-compose up` command, it will read the `docker-compose.yml` and start all the containers:

```shell
docker-compose up
```

### docker-compose - build

If you have your own container when runs your app, and it has a `Dockerfile`, you can use the `build` key in the `docker-compose.yml` file to build the image, for the value you add the path to the `Dockerfile`:

```
redis:
  image: redis
db:
  image: postgres:9.4
vote:
  build: ./vote
  ports:
    - 5000:80
  links:
    - redis
result:
  build: ./result
  ports:
    - 5001:80
  links:
    - db
worker:
  build: ./worker
  links:
    - redis
    - db
```

### docker-compose - versions

#### version: 1

Networking: attaches all the containers it runs to the default `bridge` network, and the uses `links` to enable communication between containers.

```
redis:
  image: redis
db:
  image: postgres:9.4
vote:
  image: voting-app
  ports:
    - 5000:80
  links:
    - redis
```

#### version: 2

From version 2 and on, you must specify the version at the top of the `docker-compose.yml`.

Also, you have the new section `services` when you specify all the containers.

Networking: automatically creates a dedicated `bridge` network for this application and then attaches all containers to that new network, so all containers are able to communicate using their service name. You don't need yo use `links` anymore.

Introduces a `depends_on` feature: to specify the start-up order of the containers, in case you need to start a container before another.

```
version: 2
services:
  redis:
    image: redis
  db:
    image: postgres:9.4
  vote:
    image: voting-app
    ports:
      - 5000:80
    depends_on:
      - redis
```

Another new section is `networks` when you can specify the networks, and then you can specify to each container to which network it will attach. This is an **incomplete** `docker-compose.yml` file, only to specify the networks section:

```
version: 2
services:
  redis:
    image: redis
    networks:
      - back-end
  db:
    image: postgres:9.4
    networks:
      - back-end
  vote:
    image: voting-app
    networks:
      - front-end
      - back-end
  result:
    image: result
    networks:
      - front-end
      - back-end

networks:
  - front-end
  - back-end
```

#### version: 3

From version 2 and on, you must specify the version at the top of the `docker-compose.yml`.

It comes with support for Docker Swarm.

```
version: 3
services:
  redis:
    image: redis
  db:
    image: postgres:9.4
  vote:
    image: voting-app
    ports:
      - 5000:80
```

## Docker Registry

When specifying the image name, the correct form is `user-or-account/image-or-repository`, but when the `user` or `account` is the same as the `image` or `repository`, you can only specify the `image`. Both are the same:

```
image: nginx
```

```
image: nginx/nginx
```

If you do not specify the docker registry, it is assumed that images should be pull from Docker Hub (`docker.io`):

```
image: docker.io/nginx/nginx
#      |_______| |___| |___|
#      registry  user  image
```

### Private Registry

If you want to use a private docker registry, you have to login to the private repository:

```shell
docker login my-private-docker-registry.com
```

Then to use an image from the private registry, you have to specify the registry:

```shell
docker run my-private-docker-registry.com/apps/internal-app
```

### Deploy Private Registry

Docker provides an app (image) to run a local registry:

```shell
docker run -d -p 5000:5000 --name registry registry:2
```

Tag an image to the local registry running at `localhost:5000`:

```shell
docker image tag my-image localhost:5000/my-image
```

To push the image to the local registry at `localhost:5000`:

```shell
docker push localhost:5000/my-image
```

To pull the image from the local registry at `localhost:5000` you can use it as it is or use the IP/domain-name of the docker host:

```shell
docker pull localhost:5000/my-image

docker pull 192.168.56.100:5000/my-image
```

To check the list of images pushed into the local registry:

```shell
curl -X GET localhost:5000/v2/_catalog
```

To remove images from our local registry:

## Docker Engine, Storage and Networking

### Docker Engine

You can run the Docker CLI from a different computer than the one running the Docker Engine. You have to use ` -H` option:

```shell
docker -H=remote-docker-engine:2375

# an example:
docker -H=10.123.2.1:2375 run nginx
```

#### cgroups

Docker uses `cgroups` to restrict the resources used in the system docker engine is running. You can limit the CPU and memory of a container by:

```shell
# limit CPU use to 50%:
docker run --cpus=.5 ubuntu

# limit memory:
docker run --memory=100m ubuntu
```

#### Namespaces - PID

The processes in the container run with a different PID than the host, but you can see the all processes running in the host, even the ones running in the container, but with a different PID.
To see all the processes running in a container:

```shell
docker exec container-id ps -eaf
```

### Docker Storage

Important terms:

1. Layered Architecture:
   1. Image Layers: read only
   2. Container Layer: read write
2. COPY-ON-WRITE

#### volumes

Default path in host system where docker volumes are storage: `/var/lib/docker/volumes`

To crate a volume:

```shell
docker volume create data_volume
```

This will create the folder: `/var/lib/docker/volumes/data_volume`

You can use this volume by passing the ` -v` option to the run command:

```shell
docker run -v data_volume:/var/lib/mysql mysql
```

Important note: if you do not create the volume, docker will automatically create the volume in the path: `/var/lib/docker/volumes/`. For example:

```shell
docker run -v data_volume2:/var/lib/mysql mysql
```

It creates automatically the folder: `/var/lib/docker/volumes/data_volume2`

There are two types of mounts:

1. Volume mounting: when tou create a volume a use it in the run command: `docker run -v data_volume:/var/lib/mysql mysql`
2. Bind mounting: when you have the data in a different folder than `/var/lib/docker/volumes/`: `docker run -v /data/mysql:/var/lib/mysql mysql`

The ` -v` option is deprecated, you should use ` --mount`:

```shell
# using -v:
docker run -v /data/mysql:/var/lib/mysql mysql

# using --mount:
docker run --mount type=bind,source=/data/mysql,target=/var/lib/mysql mysql
```

#### Storage drivers

Docker storage drivers are the responsible for all the layered architecture operations, moving files across layers, etc.

Some common storage drivers are:

1. AUFS
2. ZFS
3. BTRFS
4. Device Mapper
5. Overlay
6. Overlay2

If you want to check which drivers docker is using:

```shell
docker info | more
```

To check the size of files in unix:

```shell
du -sh *
```

To see the actual disk space consumption by docker in the host system:

```shell
docker system df

# to see a breakdown (more details) use -v:
docker system df -v
```

### Docker Networking

Docker has 3 defaults networks: `bridge`, `none`, and `host`

To check more details about a network:

```shell
# instead of bridge you can input any network name
docker network inspect bridge
```

#### bridge

It is the network a container is attached by default. 

You don't need to specify it when running a container: `docker run ubuntu`

Containers get an IP usually in the range: `172.17.X.X`. You can use this IP to access other containers.

To access containers attached to this network from the outside world, map the ports of this container to ports in the host system.

#### none

You have to specify it when running a container: `docker run --network=none ubuntu`

The containers are NOT attach to any network. Containers do not have any external access. Containers run in an isolated network.

#### host

You have to specify it when running a container: `docker run --network=host ubuntu`

If you attach a container to this network, it takes out any network isolation between the docker host and the container.

For example, if you run a webserver that listens the port 5000, you can access it from the docker host without any port mapping required.

If several containers are attached to this network, be careful to use different port on each service on each container. Multiple containers cannot use the same ports.

#### User-defined networks

To create a network:

```shell
docker network create \
 --driver brigde \
 --subnet 182.18.0.0/16 \
 the-name-of-my-isolated-network
```

To list all networks:

```shell
docker network ls
```

To find out what networks the container is attached, use the `inspect` command, you have to provide the `CONTAINER ID` or the `CONTAINER NAME`. Check under the `"Networks"` section:

```shell
docker inspect blissful_hopper
```

#### Embedded DNS

Containers can reach each other using their names if they are attached to the same network.

You can use the IP to access each other, but IP could change if you restart the containers. Better to use the name of the containers.

The DNS server runs at `127.0.0.11`