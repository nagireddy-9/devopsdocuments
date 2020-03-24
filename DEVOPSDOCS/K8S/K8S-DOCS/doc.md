## K8S DOCES
* K8S mainly Master and Nodes(older name of nodes is miniones)
* ### master componetes
  * Apiserver()
  * Kube-Controller Manager
  * Scheduler
  * etcd
  * Kube-Cloud-Controller
* ### Node components
  * Kubelet(this is k8s agent)
  * Container Enigine
  * Kube Proxy

* PODE LIFE CYCLE:
   * Pending --> Running --> Sucesses/Failure
   * Pode will have a ipadderess not container boz Pode will intract with kernel.
   * Pode consist of docker/rocket containers.
   * One pode will have more then one container but this is not decent approche.
* NOTE:: Kubectl will do convert into object spec and object status.


## Manifest file
   
   * SPEC:: specification is tell to Apiserver what has to be done.

## TAINT NODE:
* means k8s master working as a node called taint node generelly it will not open in prodcation.

9849522725
