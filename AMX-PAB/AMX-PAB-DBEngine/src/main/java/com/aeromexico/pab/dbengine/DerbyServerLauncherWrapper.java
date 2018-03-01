package com.aeromexico.pab.dbengine;

import org.apache.derby.drda.NetworkServerControl;

/**
 * com.tracktopell.openlegacy.fullstackexam.dbengine.DerbyServerLauncherWrapper
 * @author alfredo.estrada
 */
public class DerbyServerLauncherWrapper {
	public static void main(String[] args) {

		//System.setProperty("derby.stream.error.method","com.gonet.papadoc.persistence.test.DerbySlf4jBridge.bridge");
		//System.setProperty("org.jboss.logging.provider", "slf4j");
		System.setProperty("derby.system.home", System.getProperty("user.home")+"/DERBY_DATABASES");

		NetworkServerControl.main(args);
	}
	
}
