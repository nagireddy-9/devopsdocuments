## jenkins-docker-k8s-CI/CD
* TASK::
   * create a docker image and push it into dockerhub.
   * deploy that image from dockerhub into k8s cluster.
   * we should everithing from jenkins. 
     * For this i have configured bellow architecture
```
* one jenkins server
  * two slave nodes
    * one is docker node
    * another one is k8s master
      * again k8s master have two nodes.
```     