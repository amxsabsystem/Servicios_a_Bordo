#!/bin/bash
nohup java -Dfile.encoding=UTF-8 -Dderby.system.home=$HOME${nix.file.separator}DERBY_DATABASES  -cp dependency-jars${nix.file.separator}derby.jar${nix.path.separator}dependency-jars${nix.file.separator}derbyLocale_es.jar${nix.path.separator}dependency-jars${nix.file.separator}derbyclient.jar${nix.path.separator}dependency-jars${nix.file.separator}derbynet.jar${nix.path.separator}${project.artifactId}.jar ${project.mainclass} start -h localhost -p  ${derby.server.port} &
for i in `ps -fea|awk '{if(index($0,"derby")&&!index($0,"grep")){printf("%d",$2);}}'`; do
  echo "#!/bin/bash"  >  ./stopCurrentDerby.sh;
  echo "kill -15 $i"  >> ./stopCurrentDerby.sh;
done;
