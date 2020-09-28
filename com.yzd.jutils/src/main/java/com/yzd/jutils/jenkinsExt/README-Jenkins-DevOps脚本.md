## DevOps脚本
- jenkins-aliyun-bj-script-new_2020-09-28-1112
```
set -x
set -e
echo $WORKSPACE
tag=${GIT_COMMIT:0:6}
TMP_NAME=${JOB_NAME%-pre}
NEW_JOB_NAME=${TMP_NAME#*-}
path_package=target/${NEW_JOB_NAME}.jar
name_package=${path_package##*/}
JOB_NAME=www-${JOB_NAME#*-}
IMAGES_NAME="registry.demo.com/www-aliyun/${JOB_NAME}"
TMP_DIR=${WORKSPACE}/tmp-aliyun
echo $name_package
echo $path_package
test -d $TMP_DIR || mkdir $TMP_DIR
cp /home/jenkins/www/aliyun-bj-${JOB_NAME}-Dockerfile ${TMP_DIR}/Dockerfile
cp -r ${WORKSPACE}/${path_package} ${TMP_DIR}/${name_package}

cd ${TMP_DIR}
time docker build --no-cache -t $IMAGES_NAME .

CIR=ali-bj
IMAGE=${JOB_NAME#*-}
time=$(date +%s)

docker login registry.demo.com/xxxxxxxxx
docker tag $IMAGES_NAME $IMAGES_NAME:$tag
docker push $IMAGES_NAME:$tag
curl -XPOST http://sre.demo.com/docker/image -d "circumstance=${CIR}&image=${IMAGE}&tag=${tag}&timestamp=${time}"

PS:百度云》Y-运维管理平台》J-Jenkins-发版-DevOps
```

## jenkins内置变量
- [jenkins中使用变量](https://www.cnblogs.com/wang-mengmeng/p/11332788.html)
- [Jenkins可用环境变量以及使用方法](https://blog.csdn.net/zbj18314469395/article/details/79755985)