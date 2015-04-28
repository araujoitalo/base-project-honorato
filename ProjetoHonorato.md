#Como configurar JBOSS 7.1 + Mysql5.

# Introduction #

Passo a passo para configurar Mysql no Jboss7.1.


# Details #

1 - Criar Pasta mysql em **$JBOSS\_HOME/modules/com/**
2 - Criar Pasta main em **$JBOSS\_HOME/modules/com/mysql/main**
3 - Copiar o arquivo _mysql-connector-java-5.1.24-bin.jar_ para a pasta **$JBOSS\_HOME/modules/com/mysql/main**
4 - Criar o arquivo _module.xml_ dentro da pasta **$JBOSS\_HOME/modules/com/mysql/main** com o seguinte conteúdo:

<?xml version="1.0" encoding="UTF-8"?>
> 

&lt;module xmlns="urn:jboss:module:1.0" name="com.mysql"&gt;


> > 

&lt;resources&gt;


> > > 

&lt;resource-root path="mysql-connector-java-5.1.24-bin.jar"/&gt;



> > 

&lt;/resources&gt;


> > 

&lt;dependencies&gt;


> > > 

&lt;module name="javax.api"/&gt;


> > > 

&lt;module name="javax.transaction.api"/&gt;



> > 

&lt;/dependencies&gt;



> 

&lt;/module&gt;



5 - Abra o arquivo:  _$JBOSS\_HOME/standalone/configuration/standalone.xml_
6 - Procure a tag: 

&lt;subsystem xmlns="urn:jboss:domain:datasources:1.0"&gt;

**7 - Dentro da TAG de datasource inclua:**



&lt;datasource jndi-name="java:jboss/datasources/Base-ProjectDS" pool-name="Base-ProjectDS" enabled="true" use-java-context="true"&gt;


> 

&lt;connection-url&gt;

jdbc:mysql://localhost:3306/project-db

&lt;/connection-url&gt;


> > 

&lt;driver&gt;

mysql

&lt;/driver&gt;


> > 

&lt;pool&gt;


> > > 

&lt;min-pool-size&gt;

20

&lt;/min-pool-size&gt;


> > > 

&lt;max-pool-size&gt;

200

&lt;/max-pool-size&gt;



> > 

&lt;/pool&gt;


> > 

&lt;security&gt;


> > > 

&lt;user-name&gt;

root

&lt;/user-name&gt;


> > > 

&lt;password&gt;

root

&lt;/password&gt;



> > 

&lt;/security&gt;




&lt;/datasource&gt;



8 - Dentro da TAG **drivers** inclua:

> 

&lt;driver name="mysql" module="com.mysql"/&gt;

