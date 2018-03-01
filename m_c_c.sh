git log -2|awk 'BEGIN{printf("git diff ");}{if(index($0,"commit")){ printf("%s:AMX-PAB/AMX-PAB-persistence/src/test/resources/sql/FIRST_DATA_SCEN_1.DERBY.SQL ",$2);}}END{printf(" > d.txt \n");}'> c_c.sh

git log -2|awk 'BEGIN{printf("git diff ");}{if(index($0,"commit")){ printf("%s:AMX-PAB/AMX-PAB-persistence/src/test/resources/sql/FIRST_DATA_TEST_1.DERBY.SQL ",$2);}}END{printf(" >> d.txt \n");}'> c_c.sh

git log -2|awk 'BEGIN{printf("git diff ");}{if(index($0,"commit")){ printf("%s:AMX-PAB/db_resources/model/sql/CREATE_SCHEMMA_DEV.DERBY.SQL ",$2);}}END{printf(" >> d.txt \n");}'>> c_c.sh

chmod 775 c_c.sh
./c_c.sh
for i in `cat d.txt`; do echo"=====CHANGES DETECTED, NOW RESET DB."; ./call_reset.sh; break; done;
