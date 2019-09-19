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








