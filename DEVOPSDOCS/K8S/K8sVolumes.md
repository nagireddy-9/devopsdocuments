### KUBERNETES VOLUMES
* Host-based
   * EmptyDir
   * HostPath
* Block Storage
   * Amazon EBS
   * GCE Persistent Disk
   * Azure Disk
   * vSphere Volume
   * ...
* Distributed File System
   * NFS
   * Ceph
   * Gluster
   * Amazon EFS
   * Azure File System
   * ...
* Other
   * Flocker
   * iScsi
   * Git Repo
   * Quobyte
   * ...

#### Host Based   
* emptyDir: [Preview](https://kubernetes.io/docs/concepts/storage/volumes/#emptydir)
  
```
apiVersion: v1
kind: Pod
metadata:
  name: test-pd
spec:
  containers:
  - image: k8s.gcr.io/test-webserver
    name: test-container
    volumeMounts:
    - mountPath: /cache
      name: cache-volume
  volumes:
  - name: cache-volume
    emptyDir: {}
```