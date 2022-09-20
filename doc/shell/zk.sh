#!/bin/bash

if [ $# -lt 1 ]
then
  echo "请输入参数：start|stop|status" && exit
fi

case $1 in
"start"){
 for i in hadoop102 hadoop103 hadoop104
 do
        echo =================== zookeeper $i 启动 ===================
  ssh $i "/opt/module/zookeeper/bin/zkServer.sh start"
 done
};;
"stop"){
 for i in hadoop102 hadoop103 hadoop104
 do
        echo ---------- zookeeper $i 停止 ------------
  ssh $i "/opt/module/zookeeper/bin/zkServer.sh stop"
 done
};;
"status"){
 for i in hadoop102 hadoop103 hadoop104
 do
        echo ---------- zookeeper $i 状态 ------------
  ssh $i "/opt/module/zookeeper/bin/zkServer.sh status"
 done
};;
*){
  echo "请输入参数：start|stop|status" && exit
};;
esac
