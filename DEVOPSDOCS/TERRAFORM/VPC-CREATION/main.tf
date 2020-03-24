
provider "aws" {

access_key= "${var.aws_access_key}"
secret_key ="${var.aws_seceret_key}"
region=""  
}

resource "aws_security_group" "TF-MYSGP" {
    name = "seurity-group"
    ingress {
        from_port = "0"
        to_port = "6843"
        protocol = "tcp"
        cidr_blocks = "0.0.0.0/0"
    }
    egress {
        from_port = "0"
        to_port = "6000"
        protocol = "tcp"
        cidr_blocks = "0.0.0.0/0"
    }
  
}
resource "aws_route_table" "TF-ROUTE" {
    vpc_id = "${aws_vpc.TF-VPC.id}"
    cidr_block = "0.0.0.0/0"

  
}
resource "aws_internet_gatway" "TF-IGW" {
    vpc_id = "${aws_vpc.TF-VPC.id}"
    
  
}




resource "aws_vpc" "TF-VPC" {
    cidr_block = "192.168.0.0/16"
    tags {
        name = "TF_VPC"
    }
  
}
resource "aws_subnet" "TF-SUB1" {
  vpc_id = "${aws_vpc.TF-VPC.id}"
  cidr_block = "192.168.0.0/24"
  tags {
      name = "subnet1"
  }
}
resource "aws_subnet" "TF-SUB2" {
    vpc_id = "${aws_vpc.TF-VPC.id}"
    cidr_block = "192.168.1.0/24"
    tags {
        name = "subnet2"
    }
  
}


