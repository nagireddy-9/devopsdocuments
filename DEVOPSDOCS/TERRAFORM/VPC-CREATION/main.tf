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





