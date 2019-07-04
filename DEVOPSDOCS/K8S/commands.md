# K8S COMMANDS
```
kubectl init 
```
* it should run only on k8s master only
```
kubectl get nodes
kubectl get pods
kubectl get pods -o wide
kubectl get pods --all-namespaces -o wide
  * it shows master related podes it will make master does work and node pods which are joined with master
 kubectl describe <replicationcontroller/first-sample-rc>  
``` 

[Preview](https://www.assistanz.com/steps-to-create-a-replication-controller-using-the-kubectl-command/)