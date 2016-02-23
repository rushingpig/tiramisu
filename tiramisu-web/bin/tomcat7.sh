#!/bin/bash
# created by : Pigo.can
# created time : 2015/10/31
# xfshop项目通过tomcat7启动发布


mvn=`which mvn`

#	脚本绝对路径
script_path=$0
#	脚本父文件夹路径
script_parent_path=`dirname $0`
#	xfshop_wx路径
xfshop_wx_dir_path=${script_parent_path}/..
#	父工程路径
xfshop_parent_dir_path=${script_parent_path}/../..


#	父工程所有子模块重新打包，避免新增或修改工程不生效
function doPack(){

	echo "###########     begin to clean and do pack project    #############"

	echo ""

	${mvn} clean package -Dmaven.test.skip=true 
	if [ $? -ne 0 ]; then
		echo "###############      there is something wrong with packing     #############"
		exit 1
	fi
	echo ""
	echo "##########      the project has done pack finished    #############"
	echo ""
}

#	直接将工程部署并运行在tomcat容器
function runTomcat(){
	echo "↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓          run tomcat7 plugin of maven          ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓"
	echo ""

	cd ${xfshop_wx_dir_path}
	${mvn} tomcat7:run
}


cd ${xfshop_parent_dir_path}

doPack

sleep 2

runTomcat
