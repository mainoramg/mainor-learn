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

