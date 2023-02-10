# Docker for the Absolute Beginner - Mumshad Mannambeth

## Install Docker

On Linux: https://docs.docker.com/install/linux/docker-ce/ubuntu/

See the section: `Install using the convenience script`

## Docker Commands

### docker run

To start a container:

```shell
docker run nginx
```

To run the container in detach mode, add ` -d`:

```shell
docker run -d kodekloud/simple-webapp
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

### docker images

List images:

```shell
docker images
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


### Important things to know

The container only lives as long as the process inside it is alive. For example if the web service inside the container stops or crashes then the container exits.

When providing a `CONTAINER ID` to any of the commands, you can provide only the first characters that are different from the other containers on the host machine. For example: `docker attach a043d`