#!/bin/sh
#进入文件根目录
#cd "$WORKSPACE"

#启用配置
ActiveProfiles=prod

#基本信息需要配置
#内部端口
#targetPort=8081
#旧镜像版本号
oldVendor=1.0.0
#镜像版本号
vendor=1.0.0
#项目名
#projectName=eureka

#进入target文件夹
cd $WORKSPACE@2/eureka/target

#创建Dockerfile文件
cat << EOF > Dockerfile
FROM kdvolder/jdk8
MAINTAINER eureka1
VOLUME /tmp
LABEL app="eureka1" version="$vendor" by="eureka1"
COPY eureka.jar eureka.jar
EXPOSE 8001
ENTRYPOINT java -Xmx100m -Xms100m -jar -Duser.timezone=GMT+08 eureka.jar --spring.profiles.active=$ActiveProfiles
EOF

#删除镜像下所有容器
docker rm -f $(docker ps -a | grep "eureka1" | awk '{print $1}')

#删除旧镜像
docker rmi -f eureka1:$oldVendor

#创建镜像
docker build -t eureka1:$vendor .

#启动镜像生成容器
docker run --restart=unless-stopped --name eureka1 -d -p 8001:8001 eureka1:$vendor

#创建Dockerfile文件
cat << EOF > Dockerfile
FROM kdvolder/jdk8
MAINTAINER eureka2
VOLUME /tmp
LABEL app="eureka2" version="$vendor" by="eureka2"
COPY eureka.jar eureka.jar
EXPOSE 8002
ENTRYPOINT java -Xmx100m -Xms100m -jar -Duser.timezone=GMT+08 eureka.jar --spring.profiles.active=$ActiveProfiles
EOF

#删除镜像下所有容器
docker rm -f $(docker ps -a | grep "eureka2" | awk '{print $1}')

#删除旧镜像
docker rmi -f eureka2:$oldVendor

#创建镜像
docker build -t eureka2:$vendor .

#启动镜像生成容器
docker run --restart=unless-stopped --name eureka2 -d -p 8002:8002 eureka2:$vendor

#创建Dockerfile文件
cat << EOF > Dockerfile
FROM kdvolder/jdk8
MAINTAINER eureka3
VOLUME /tmp
LABEL app="eureka3" version="$vendor" by="eureka3"
COPY eureka.jar eureka.jar
EXPOSE 8003
ENTRYPOINT java -Xmx100m -Xms100m -jar -Duser.timezone=GMT+08 eureka.jar --spring.profiles.active=$ActiveProfiles
EOF

#删除镜像下所有容器
docker rm -f $(docker ps -a | grep "eureka3" | awk '{print $1}')

#删除旧镜像
docker rmi -f eureka3:$oldVendor

#创建镜像
docker build -t eureka3:$vendor .

#启动镜像生成容器
docker run --restart=unless-stopped --name eureka3 -d -p 8003:8003 eureka3:$vendor