#!/bin/bash
DERBY_PORT=$1
java -Dfile.encoding=UTF-8 -cp  AMX-PAB-DBEngine.jar com.aeromexico.pab.devtasks.DropAllByForceTables org.apache.derby.jdbc.ClientDriver "jdbc:derby://localhost:$DERBY_PORT/AMX_PAB_DB_DEV" AMX_PAB_DEV_USR AMX_PAB_DEV_PWD
java -Dfile.encoding=UTF-8 -jar SQLCommander.jar -driverClass org.apache.derby.jdbc.ClientDriver -url "jdbc:derby://localhost:$DERBY_PORT/AMX_PAB_DB_DEV;create=true" -user AMX_PAB_DEV_USR -password AMX_PAB_DEV_PWD < ../model/sql/CREATE_SCHEMMA_DEV.DERBY.SQL
java -Dfile.encoding=UTF-8 -jar SQLCommander.jar -driverClass org.apache.derby.jdbc.ClientDriver -url "jdbc:derby://localhost:$DERBY_PORT/AMX_PAB_DB_DEV"             -user AMX_PAB_DEV_USR -password AMX_PAB_DEV_PWD < ../../AMX-PAB-persistence/src/test/resources/sql/FIRST_DATA_SCEN_1.DERBY.SQL
