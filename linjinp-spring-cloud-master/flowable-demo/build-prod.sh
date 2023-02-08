#!/bin/sh
#进入文件根目录
#cd "$WORKSPACE"

#启用配置
ActiveProfiles=prod

#基本信息需要配置
#内部端口
targetPort=8087
#旧镜像版本号
oldVendor=1.0.0
#镜像版本号
vendor=1.0.0
#项目名
projectName=flowable-demo

#进入target文件夹
cd $WORKSPACE@2/$projectName/target

#创建Dockerfile文件
cat << EOF > Dockerfile
FROM docker.io/ascdc/jdk8
MAINTAINER $projectName
VOLUME /tmp
LABEL app="$projectName" version="$vendor" by="$projectName"
COPY $projectName.jar $projectName.jar
EXPOSE $targetPort
CMD  java -Xmx100m -Xms100m -jar -Duser.timezone=GMT+08 $projectName.jar --spring.profiles.active=$ActiveProfiles
EOF

#删除旧镜像
docker rmi -f $projectName:$oldVendor

#创建镜像
docker build -t $projectName:$vendor .

#启动镜像生成容器
docker run --restart=unless-stopped --name $projectName -d -p $targetPort:$targetPort $projectName:$vendor


