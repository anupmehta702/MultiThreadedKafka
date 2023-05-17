## installation of Kafka and zookeeper
Referred link -
How to install zookeeper on windows
https://medium.com/@shaaslam/installing-apache-zookeeper-on-windows-45eda303e835

How to install and start kafka on windows 
https://medium.com/@shaaslam/installing-apache-kafka-on-windows-495f6f2fd3c8


## Steps to start Kafka
1. Start zookeeper using below command -
zkserver ( on conemu editor)
2. Start kafka server using below command - 
.\bin\windows\kafka-server-start.bat .\config\server.properties


## Create Kafka topic 
Create Kafka topic - "my_big_topic" using below command -
.\bin\windows\kafka-topics.bat --create --topic my_big_topic --zookeeper localhost:2181 --replication-factor 3 --partitions 3