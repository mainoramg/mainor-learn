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