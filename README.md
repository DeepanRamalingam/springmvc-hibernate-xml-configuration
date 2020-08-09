## Web.xml

	<!DOCTYPE xml>

	<web-app>
		<display-name>Archetype Created Web Application</display-name>
		<servlet>
			<servlet-name>mycontroller</servlet-name>
			<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
			<init-param>
				<param-name>contextConfigLocation</param-name>
				<param-value>/WEB-INF/mycontroller-config.xml</param-value>
			</init-param>
			 <load-on-startup>1</load-on-startup>
		</servlet>

		<servlet-mapping>
			<servlet-name>mycontroller</servlet-name>
			<url-pattern>/</url-pattern>
		</servlet-mapping>

		<context-param>
			<param-name>contextConfigLocation</param-name>
			<param-value>/WEB-INF/hibernate-ctx.xml</param-value>
		</context-param>
		<listener>
	        <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
	    </listener>
	</web-app>  

## Dispatcher-servlet configuration in mycontroller-config.xml  

	<?xml version="1.0" encoding="UTF-8"?>
	<beans xmlns="http://www.springframework.org/schema/beans"
		xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
		xmlns:p="http://www.springframework.org/schema/p"
		xmlns:context="http://www.springframework.org/schema/context"
		xmlns:mvc="http://www.springframework.org/schema/mvc"
		xsi:schemaLocation="
	        http://www.springframework.org/schema/beans
	        http://www.springframework.org/schema/beans/spring-beans.xsd
	        http://www.springframework.org/schema/context
	        http://www.springframework.org/schema/context/spring-context.xsd
	        http://www.springframework.org/schema/mvc
	        http://www.springframework.org/schema/mvc/spring-mvc.xsd">

		<!-- For the Stereotype annotations to work -->
		<context:component-scan
			base-package="com.stackroute" />


		<bean id="viewResolver"
			class="org.springframework.web.servlet.view.InternalResourceViewResolver">
			<property name="prefix" value="/WEB-INF/views/" />
			<property name="suffix" value=".jsp" />
		</bean>
		
		 <mvc:annotation-driven/>
		 
		  <!-- Add support for reading web resources: css, images, js, etc ... -->
	    <mvc:resources location="/resources/" mapping="/resources/**"></mvc:resources>
	</beans> 

## Datasource, Session Factory, HibernateTransactionManager Bean in ApplicationContext(hibernate-ctx.xml)  

	<?xml version="1.0" encoding="UTF-8"?>
	<beans xmlns="http://www.springframework.org/schema/beans"
		xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
		xmlns:tx="http://www.springframework.org/schema/tx"
		xsi:schemaLocation="http://www.springframework.org/schema/beans 
	    http://www.springframework.org/schema/beans/spring-beans.xsd
	    http://www.springframework.org/schema/tx 
	    http://www.springframework.org/schema/tx/spring-tx-2.5.xsd">


		<!-- Database details -->
		<bean id="dataSourceBean"
			class="org.springframework.jdbc.datasource.DriverManagerDataSource">
			<property name="driverClassName"
				value="com.mysql.cj.jdbc.Driver" />
			<property name="url"
				value="jdbc:mysql://localhost:3306/empdb?useSSL=false" />
			<property name="username" value="root" />
			<property name="password" value="Root@123" />
		</bean>

		<!-- Hibernate session factory -->
		<bean id="sfBean"
			class="org.springframework.orm.hibernate5.LocalSessionFactoryBean">
			<property name="dataSource" ref="dataSourceBean" />
			<!--  
			<property name="annotatedClasses">
				<array>
					<value>com.stackroute.model.Employee</value>
				</array>
			</property>
			-->
			<property name="packagesToScan" value="com.stackroute.model" />
			<property name="hibernateProperties">
				<props>
					<prop key="hibernate.dialect">org.hibernate.dialect.MySQL5Dialect</prop>
					<prop key="hibernate.hbm2ddl.auto">validate</prop>
					<prop key="hibernate.show_sql">true</prop>
				</props>
			</property>
		</bean>
				
		<!-- Hibernate Transaction Manager -->

		<bean id="txManager"
			class="org.springframework.orm.hibernate5.HibernateTransactionManager">
			<property name="sessionFactory" ref="sfBean" />
		</bean>

	</beans>


## Dependencies in POM.xml  

	<dependencies>
			<!-- spring dependency -->
			<dependency>
				<groupId>org.springframework</groupId>
				<artifactId>spring-webmvc</artifactId>
				<version>5.0.8.RELEASE</version>
			</dependency>
			<dependency>
				<groupId>javax.servlet</groupId>
				<artifactId>servlet-api</artifactId>
				<version>3.0-alpha-1</version>
			</dependency>
			<dependency>
				<groupId>org.springframework</groupId>
				<artifactId>spring-orm</artifactId>
				<version>5.0.8.RELEASE</version>
			</dependency>
			<dependency>
				<groupId>org.hibernate</groupId>
				<artifactId>hibernate-core</artifactId>
				<version>5.3.6.Final</version>
			</dependency>
			<dependency>
				<groupId>mysql</groupId>
				<artifactId>mysql-connector-java</artifactId>
				<version>8.0.12</version>
			</dependency>
			<dependency>
	            <groupId>javax.xml.bind</groupId>
	            <artifactId>jaxb-api</artifactId>
	            <version>2.3.1</version>
	        </dependency>
		</dependencies>

## Important Points:

1. Enable Transaction Management using @EnableTransactionManagement in DAO class along with @Repository - (else Hibernate Transaction Exception will be thrown)
2. Use @Transactional for individual methods in service class
3. Import @Transactional from "org.springframework.transaction.annotation.Transactional".
4. Must include jaxb-api bind dependency.

