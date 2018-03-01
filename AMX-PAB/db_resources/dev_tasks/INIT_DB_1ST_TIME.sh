#!/bin/bash
java -Dfile.encoding=UTF-8 -jar SQLCommander.jar -driverClass org.apache.derby.jdbc.ClientDriver -url "jdbc:derby://localhost:1527/AMX_PAB_DB_DEV;create=true" -user AMX_PAB_DEV_USR -password AMX_PAB_DEV_PWD <  ../model/sql/CREATE_SCHEMMA_DEV.DERBY.SQL
java -Dfile.encoding=UTF-8 -jar SQLCommander.jar -driverClass org.apache.derby.jdbc.ClientDriver -url "jdbc:derby://localhost:1527/AMX_PAB_DB_DEV"             -user AMX_PAB_DEV_USR -password AMX_PAB_DEV_PWD <  ../../AMX-PAB-persistence/src/test/resources/sql/FIRST_DATA_SCEN_1.DERBY.SQL
