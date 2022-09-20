#!/bin/bash
#1. 判断参数是否为空，为空，则向每个节点发送jps命令
if (($#==0));then
 for host in hadoop102 hadoop103 hadoop104
   do
        echo =============== $host ===============
        ssh $host jps
   done
 eval "exit"
else
#2. 非空时，则将参数发送到每个节点执行
for host in hadoop102 hadoop103 hadoop104
   do
        echo =============== $host ===============
        ssh $host $*
   done
eval "exit"
fi
