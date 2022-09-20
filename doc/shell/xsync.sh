#!/bin/bash
# 1. 判断参数个数
if [ $# == 0 ]
then
  echo " 请输入要分发的文件/目录"&&exit
fi
# 2. 遍历节点
for host in hadoop102 hadoop103 hadoop104
  do
    echo ========== $host ==========
    # 3. 遍历每个目录/文件
    for file in $@
      do
        # 4. 判断是否是文件/目录
        if [ -e $file ]
          then
          # 5. 获取父目录
          pdir=$(cd -P $(dirname $file); pwd)
          # 6. 获取文件名称
          fname=$(basename $file)
          # 7. 创建父目录
          ssh $host "mkdir -p ${pdir}"
          # 8. 分发文件
          rsync -av ${pdir}/${fname} ${host}:${pdir}
        else
          echo "$file 文件不存在！"
        fi
      done
  done
