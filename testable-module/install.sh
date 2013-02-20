#!/bin/bash

if [ "$JBOSS_HOME" == "" ]; then
	echo "JBOSS_HOME is not set"
	exit 1
fi

mkdir -p $JBOSS_HOME/modules/org/my/m1/main
mkdir -p $JBOSS_HOME/modules/org/my/m2/main

cp module1/module.xml $JBOSS_HOME/modules/org/my/m1/main/module.xml
cp module2/module.xml $JBOSS_HOME/modules/org/my/m2/main/module.xml

cp module1/target/module1-1.0.0-SNAPSHOT.jar $JBOSS_HOME/modules/org/my/m1/main
cp module2/target/module2-1.0.0-SNAPSHOT.jar $JBOSS_HOME/modules/org/my/m2/main
