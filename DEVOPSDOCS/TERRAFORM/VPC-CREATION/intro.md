### TERRAFORM INTRO
* terraform will take input as a directory.
* we can create the different files in a same directory i.e define like      resource.tf, vars.tf, provider.tf, terraform.tfvars (or)                    terraform.tfvars.json (or) <anyname.auto.tfvars>.
 ```
 resource.tf
 vars.tf
 provider.tf
 terraform.tfvars (or) anyname.auto.tfvars (or) terraform.tfvars.json (or) anyname.auto.tfvars.json
 ```   
* Pass the variables from the command line using "-var" or "-var-file" or bydefault terraform will load variable from terraform.tfvars automatically but naming convention should be same as "terraform.tfvars"
 ```
 terraform validate .
 terraform plan .
 terraform apply -var-file="filename.tfvars" (directory path)
 ```
#### how to pass variable [Preview](https://www.terraform.io/docs/configuration/variables.html)

## CONNECTION AND PROVISIONERS

```
   connection = {
        user = "${var.os_user}"
        private_key = "${file("${var.key_path}")}"
    }
    
    provisioner "remote-exec"  {
        inline = [ 
            "sudo yum update",
            "sudo yum install git -y",
            "sudo yum install tree -y"

        ]
    }

```
#### main.tf
```
provider "aws" {
    access_key = "${var.aws_access_key}"
    secret_key = "hY99tu8426oydyk5vcrUtINUVcexxJM0InDkR7bc"
    region = "us-west-2"
  
}
resource "aws_vpc" "main" {
    cidr_block = "${var.vpc_cidr_block}"
    tags = {
        name = "mainvpc"
    }
  
}
resource "aws_subnet" "mainsubnet" {
    vpc_id = "${aws_vpc.main.id}"
    availability_zone = "us-west-2a"
    cidr_block = "${var.subnet_cidr_block}"
    tags = {
        name = "subnet1"
    }
}
resource "aws_security_group" "mysgp" {
        vpc_id = "${aws_vpc.main.id}"
        name = "${var.security_group_name}"
        ingress = {
            from_port = "0"
            to_port = "6000"
            protocol = "tcp"
            cidr_blocks = ["0.0.0.0/0"]
        }
        egress = {
            from_port = "0"
            to_port = "6000"
            protocol = "tcp"
            cidr_blocks = ["0.0.0.0/0"]
        }
        
  
}
resource "aws_internet_gateway" "igw" {
    vpc_id = "${aws_vpc.main.id}"
  
}

resource "aws_route_table" "rtb" {
    vpc_id = "${aws_vpc.main.id}"
    
    

  
}

resource "aws_route" "myroute" {
    route_table_id = "${aws_route_table.rtb.id}"
    destination_cidr_block = "0.0.0.0/0"
    gateway_id = "${aws_internet_gateway.igw.id}"

  
}
resource "aws_route_table_association" "arta" {
    subnet_id = "${aws_subnet.mainsubnet.id}"
    route_table_id = "${aws_route_table.rtb.id}"

  
}
resource "aws_instance" "web" {
    ami = "${var.ami_id}"
    availability_zone = "${var.availability_zone}"
    instance_type = "${var.instance_type}"
    key_name = "${var.key_name}"
    subnet_id = "${aws_subnet.mainsubnet.id}"
    associate_public_ip_address = true
    security_groups = ["${aws_security_group.mysgp.id}"]
    
    
    connection = {
        user = "${var.os_user}"
        private_key = "${file("${var.key_path}")}"
    }
    
    provisioner "remote-exec"  {
        inline = [ 
            "sudo yum update",
            "sudo yum install git -y",
            "sudo yum install tree -y"

        ]
    }

  
}

```
#### vars.tf
```
variable "aws_access_key" {
    default = ""
}
variable "aws_secret_key" {
    default = ""
  
}

variable "vpc_cidr_block" {
    default = "192.168.0.0/16"
  
}
variable "subnet_cidr_block" {
    default = "192.168.0.0/24"
  
}
variable "security_group_name" {
    default = "allow_all"
  
}
variable "ami_id" {
    default = "ami-04b762b4289fba92b"
  
}
variable "availability_zone" {
    default = "us-west-2a"
  
}
variable "instance_type" {
    default = "t2.micro"
  
}
variable "key_name" {
    default = "K8S"
  
}
variable "os_user" {
    default = "ec2-user"
  
}
variable "key_path" {
    default = "./K8S.pem"
  
}

```
#### terraform.tfvars
```
aws_access_key = "AKIA2DIMYJHGGBFWWENH"
aws_secret_key = "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"
```