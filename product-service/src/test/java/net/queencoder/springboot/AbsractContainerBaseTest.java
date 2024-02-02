package net.queencoder.springboot;

import org.testcontainers.containers.MySQLContainer;

public abstract class AbsractContainerBaseTest {
	static final MySQLContainer MY_SQL_CONTAINER;
	
	static {
		MY_SQL_CONTAINER = new MySQLContainer("mysql:latest");
		
		MY_SQL_CONTAINER.start();
		
		
	}
}
