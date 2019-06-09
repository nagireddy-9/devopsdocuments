# DOCKER NETWORKS

```
docker networks ls
```
 ==> it will shows the default networks and drivers names.
* we should create two containers with default networks as shown bellow.
```
docker container run -d -it --name container1 alpine:latest
docker container run -d -it --name container2 alpine:latest
``` 
* login into the any one of the container and ping another container from there with ip of container  and container name, here with ip it works means both containers can able to pingable each other rather than container names. Because both container haveing same default networks.(some service not enable)
* this is with name
```
docker container exec container1 ping container2
    ping: bad address 'n2'
```
* this is with ipaddress of container
```
 docker container exec container1 ping 172.17.0.3
    PING 172.17.0.3 (172.17.0.3): 56 data bytes
    64 bytes from 172.17.0.3: seq=0 ttl=255 time=0.081 ms
```
## CREATE A CUSTOM NETWORKS 
* create a custome network with default driver i.e bridge driver.
```
docker network --help
docker network create --help
docker network create --subnet 192.168.0.0/16 --driver bridge --gateway 192.168.0.1 customnetwork
```
![preview](/DEVOPSDOCS/DOCKER/images/customnet.PNG)
* create two more containers with our customenetwork as showing below

```
docker container run --help
docker container run -d -it --name dev2 --network customnetwork tomcat:latest
docker container run -d -it --name dev1 --network customnetwork tomcat:latest
```
* check whether two containers pingable each other with containers names and ipaddress.
```
docker container exec dev1 ping dev2
```
![preview](/DEVOPSDOCS/DOCKER/images/pingwithname.PNG)
```
docker container exec dev1 ping 192.168.0.3
```
![preview](/DEVOPSDOCS/DOCKER/images/pingwithip.PNG)
