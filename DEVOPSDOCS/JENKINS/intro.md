#INSTALL JENKINS
* create a os user for jenkins here i am creating a user called as jenkins
```
sudo -i
useradd jenkins
```
* make this user as a admin previlize and make him password less authentication for this do bellow steps
```
visudo
vi /etc/ssh/sshd_config
passwordauthentication yes
```
## JAVA IS REQUIRED Java 1.8(JAVA INSTALLTION)
* Amazon: by default it has java 7 
    * ```
      sudo yum install java-1.8.0 -y
      sudo yum remove java-1.7.0-openjdk -y
      ```
    * We need to add the Jenkins repository so that yum knows where to       install Jenkins from.  
      ```
      sudo wget -O /etc/yum.repos.d/jenkins.repo http://pkg.jenkins-ci.org/redhat/jenkins.repo
      ```
    * Next, we’re adding the Jenkins GPG key to our trusted keys so that we’re able to install Jenkins, verifying that the files are being sourced from a trusted location.
    ```
    sudo rpm --import http://pkg.jenkins-ci.org/redhat/jenkins-ci.org.key
    ```  
    * We’ve prepared our environment with the required dependancies so we can now install Jenkins.
    ```
    sudo yum install jenkins -y
    sudo service jenkins start
    ```
    * If you want Jenkins to automatically start when your instance is started, we can use chkconfig to add Jenkins to our startup services
    ```
    sudo chkconfig --add jenkins
    ``` 
## Jenkins-Slave(Node) COnfiguration
* Java need to be install on slave nodes.
* create a user in salve node(what user was created in master should be      the same )
* make that user sudo privilize and make him passwordless authentication
 ## Configure with master
   * go to Manage Jenkins--> Manage Node
     * give a agent name and make him parement agent
     * give the creadentiol of slavenode user.
   