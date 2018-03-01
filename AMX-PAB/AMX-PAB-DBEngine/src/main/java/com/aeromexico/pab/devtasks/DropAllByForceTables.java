package com.aeromexico.pab.devtasks;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * com.aeromexico.pab.devtasks.DropAllByForceTables
 * @author alfredo.estrada
 */
public class DropAllByForceTables {
	
	public static void main(String[] args) {
		String jdbcDriver;
		String url;
		String user;
		String password;
		Connection conn=null;
		
		jdbcDriver  = args[0];
		url			= args[1];
		user		= args[2];
		password	= args[3];
		
		

        try {
            Class.forName(jdbcDriver).newInstance();
        } catch (Exception ex) {
            System.err.println("Driver Exception:"+ex);
			System.exit(1);
        }
		
		try{
			conn = DriverManager.getConnection(url, user,  password);
		}catch(SQLException se){
			System.err.println("Connection Exception:"+se);
			System.exit(2);
		}
		try{
			//printDBInfo(conn,user);			
			deleteAllTables(conn, user);
			
		}catch(SQLException se){
			System.err.println("SQL Exception:"+se);
			System.exit(3);
		}finally{
			try{
				conn.close();
			}catch(SQLException se){
			}
		}
	}
	
	private static void deleteAllTables(Connection conn,String user) throws SQLException {
        DatabaseMetaData metaData = conn.getMetaData();

		ResultSet tablesRS = metaData.getTables(null, user, "%", null);
		System.out.println("Tables BEFORE:");
		List<String> tableNames=new ArrayList<>();
        HashMap<String,List<String>> importedKeysFKs=new HashMap<String,List<String>>();
		String tableName = null;
		while (tablesRS.next()) {			
			tableName = tablesRS.getString(3);			
			System.out.println("TABLE:"+tableName);
			tableNames.add(tableName);
            final ArrayList<String> importedKFKsList = new ArrayList<>();
            
            final ResultSet importedKeysRS = metaData.getImportedKeys(null, user, tableName);
            
            while(importedKeysRS.next()){
                String fkC=importedKeysRS.getString("FK_NAME");
                importedKFKsList.add(fkC);
                System.out.println("\tFK_NAME:"+fkC);
            }
            importedKeysRS.close();
            importedKeysFKs.put(tableName, importedKFKsList);
		}
		tablesRS.close();
		
		int numTables=tableNames.size();
		
		int numDroppedTables=0;
		Statement statement = conn.createStatement();		
		int numIntent = 0;
		do{
			ResultSet tablesRS_C = metaData.getTables(null, user, "%", null);
			System.out.println("---------------- DROP CONSTRINTS --------");
			while (tablesRS_C.next()) {

                tableName = tablesRS_C.getString(3);

                final List<String> importedFKList = importedKeysFKs.get(tableName);

                for(String fkc: importedFKList){
                    System.out.println("["+numIntent+"] DROP FK_CONSTRAINT :"+tableName+"."+fkc+" : SQL ->"+"ALTER TABLE "+tableName+" DROP CONSTRAINT "+fkc);
                    try {
                        statement.executeUpdate("ALTER TABLE "+tableName+" DROP CONSTRAINT "+fkc);                        
                    }catch(SQLException seKKs){
                        System.out.println("["+numIntent+"] DROPING FK_COSNTRINT : "+seKKs.getMessage());
                    }
                }
            }
            tablesRS_C.close();
            
            tablesRS = metaData.getTables(null, user, "%", null);
            
			System.out.println("---------------- DROP TABLES --------");
			while (tablesRS.next()) {
				try{
					tableName = tablesRS.getString(3);                    
					statement.executeUpdate("DROP TABLE "+tableName);
					numDroppedTables++;
					System.out.println("["+numIntent+"] TABLE DROPPED:"+tableName);
				}catch(SQLException se){
                    try{
                        //System.out.println("["+numIntent+"] CAN'T DROP TABLE :"+tableName+": "+se.getMessage());
                        tableName = tablesRS.getString(3);
                        statement.executeUpdate("DELETE FROM "+tableName);                        
                        //System.out.println("["+numIntent+"] DELETE ALL RECORDS FROM TABLE:"+tableName);
                    }catch(SQLException seD){
                        System.out.println("["+numIntent+"] CAN'T DELETE ALL RECORDS FROM TABLE :"+tableName+": "+seD.getMessage());
                    }
				}
			}
			tablesRS.close();
			numIntent++;
			
		}while(numDroppedTables < numTables && numIntent<numTables);
        tablesRS.close();
		ResultSet tablesRS_Afer = metaData.getTables(null, user, "%", null);
		System.out.println("Tables AFTER ( after "+numIntent+" attempts ):");
        int numTAfter = 0;
		while (tablesRS_Afer.next()) {			
			tableName = tablesRS_Afer.getString(3);			
			System.out.println("\t-> TABLE:"+tableName);
            numTAfter++;
		}
		tablesRS_Afer.close();
        System.out.println("--------------");
        System.out.println("Num TABLES AFTER:"+numTAfter);
		if(numTAfter == 0){
            System.out.println(" :D !! ");
        }else{
            System.out.println(" :( ");
            System.exit(1);
        }
	}
	
	
	private static void printDBInfo(Connection conn,String user) throws SQLException {
        DatabaseMetaData metaData = conn.getMetaData();

        System.out.println("\t=>>SchemaTerm:" + metaData.getSchemaTerm());

        System.out.println("Schemas:");

        ResultSet schemas = metaData.getSchemas();
		String schemaTableIter=null;
        while (schemas.next()) {
			if(schemas.getString("TABLE_SCHEM").equalsIgnoreCase(user)){
				System.out.println("\t=>>" + schemas.getString("TABLE_SCHEM") + ", " + schemas.getString("TABLE_CATALOG"));
				schemaTableIter = schemas.getString("TABLE_SCHEM");
			}
        }
        schemas.close();
        ResultSet tablesRS = metaData.getTables(null, user, "%", null);
        System.out.println("Tables:");
		List<String> tableNames=new ArrayList<>();
		while (tablesRS.next()) {			
			tableNames.add(tablesRS.getString(3));
			//System.out.println("\tTABLE ["+tablesRS.getString(2)+"."+tablesRS.getString(3)+"]");
		}
		
        Statement statement = conn.createStatement();
		
        for(String tableNameIter: tableNames){
			
            //System.out.print("\t" + schemaTableIter + "." + tableNameIter + "(");
			System.out.print("\t" + tableNameIter + "(");
			
			ResultSet rsFKs = metaData.getImportedKeys(null, schemaTableIter, tableNameIter);
			HashMap<String,String> tableFKs= new HashMap<>();
			while(rsFKs.next()){
				tableFKs.put(rsFKs.getString("FKCOLUMN_NAME"), rsFKs.getString("PKTABLE_NAME")+"."+rsFKs.getString("PKCOLUMN_NAME"));
			}
			rsFKs.close();
			
			ResultSet rsPKs = metaData.getPrimaryKeys(null, schemaTableIter, tableNameIter);
			HashSet<String> tablePKs= new HashSet<>();
			while(rsPKs.next()){
				tablePKs.add(rsPKs.getString("COLUMN_NAME"));
			}
			rsPKs.close();
			
        	ResultSet resColumnsTable = metaData.getColumns(null, schemaTableIter, tableNameIter, null);
			
			for(int columnCounter = 0;resColumnsTable.next();columnCounter++) {
				if(columnCounter>0){
					System.out.println(",");
				}else{
					System.out.println("");
				}
												
				int columnSize = resColumnsTable.getInt("COLUMN_SIZE");
				int columnDD   = resColumnsTable.getInt("DECIMAL_DIGITS");
				int nullableFlag = resColumnsTable.getInt("NULLABLE");
				boolean autoIncrementFlag = resColumnsTable.getString("IS_AUTOINCREMENT").equalsIgnoreCase("yes");
				boolean isPK              = tablePKs.contains(resColumnsTable.getString("COLUMN_NAME"));
				
				if(isPK) {
					System.out.print("\t\t->");
				} else{
					System.out.print("\t\t  ");
				}
				
				System.out.print(resColumnsTable.getString("COLUMN_NAME")+ "  " + resColumnsTable.getString("TYPE_NAME"));
				
				if(columnSize>0) {
					System.out.print(" ( " + columnSize);
					if(columnDD>0){
					
					}
					System.out.print(" )");
				}
				if(nullableFlag ==1) {
					System.out.print(" NULL");
				}
				if(isPK) {
					System.out.print(" PRIMARY KEY");
				}
				if(autoIncrementFlag) {
					System.out.print(" AUTOINCREMENT");
				}
				
				
				String stringFKs = tableFKs.get(resColumnsTable.getString("COLUMN_NAME"));
				if(stringFKs != null){
					System.out.print(" => "+stringFKs);
				}
			}
			System.out.println();
			System.out.println("\t);");
			resColumnsTable.close();
            
        }
		
        tablesRS.close();
		System.out.println("\tTABLES COUNT:"+tableNames.size());
        System.out.println("\t=======================================");
    }	
}
