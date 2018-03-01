#!/bin/bash
java -Dfile.encoding=UTF-8 -jar SQLCommander.jar -driverClass org.apache.derby.jdbc.ClientDriver -url "jdbc:derby://localhost:1527/AMX_PAB_DB_DEV;create=true" -user AMX_PAB_DEV_USR -password AMX_PAB_DEV_PWD
