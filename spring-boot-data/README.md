# eclipse
@prerequise => JAVA 8 JDK need to be installed and referenced in the global variable JAVA_HOME
1. Install Maven 3.3 on your computer if not already done and of course Eclipse
2. Run: mvn dependencies:list, then mvn eclipse:eclipse
3. Open Eclipse with a new workspace or an existing one
4. File -> Import -> Existing Project in workspace and choose the base directory of Spring-boot-data project
5. Select and import all projects

#install Oracle JDBC driver
1. Download (from Oracle website, create account if needed) and copy the Oracle JDBC driver 11.1.0.7.0 into your MAVEN local repository (com/Oracle/ojdbc6/11.1.0.7.0) => minimum version supported
2. rerun mvn dependency:list

#setup local DB
@prerequise => Oracle XE + Oracle SQL Developer
1. Open SQL Developer and login in the SYSTEM database
2. Run the next SQL script:

DROP USER neonell CASCADE;
CREATE TABLESPACE BACKEND_TBS DATAFILE 'backend_tbs.dbf' SIZE 100M;
CREATE USER neonell IDENTIFIED by "neonell" DEFAULT TABLESPACE BACKEND_TBS;
ALTER USER neonell DEFAULT ROLE ALL; 
GRANT CREATE SESSION to neonell;
GRANT CREATE SEQUENCE TO neonell;
GRANT CREATE TABLE TO neonell;
GRANT CREATE VIEW TO neonell;
GRANT CREATE MATERIALIZED VIEW TO neonell;
GRANT ALTER SESSION TO neonell;
GRANT CREATE PROCEDURE TO neonell;
GRANT EXECUTE ON ctx_ddl TO neonell;
GRANT CREATE JOB TO neonell;
ALTER USER neonell QUOTA UNLIMITED ON BACKEND_TBS;
commit;


#start the backend locally
1. mvn spring-boot:run

#start the test:
1. mvn test

Notice: the tests run for now in the same database as the devlopment one

