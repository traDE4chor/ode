#
# Licensed to the Apache Software Foundation (ASF) under one
# or more contributor license agreements.  See the NOTICE file
# distributed with this work for additional information
# regarding copyright ownership.  The ASF licenses this file
# to you under the Apache License, Version 2.0 (the
# "License"); you may not use this file except in compliance
# with the License.  You may obtain a copy of the License at
#
#    http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing,
# software distributed under the License is distributed on an
# "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
# KIND, either express or implied.  See the License for the
# specific language governing permissions and limitations
# under the License.
#

## ODE-AXIS2 Configuraiton Properties

## @hahnml: Property values can also be assigned through environment values using the following notation: "key=${ENV-VAR-NAME}"

## Database Mode ("INTERNAL", "EXTERNAL", "EMBEDDED")
## What kind of database should ODE use?
##   * "EMBEDDED" - ODE will create its own embbeded database (Derby)
##	           and connection pool (Minerva).
##   * "EXTERNAL" - ODE will use an app-server provided database and pool.
##                  The "ode-axis2.db.ext.dataSource" property will need to
##                  be set.
##   * "INTERNAL" - ODE will create its own connection pool for a user-
##                  specified JDBC URL and driver.
#ode-axis2.db.mode=EMBEDDED

## EmbeddedDatabase Type ("DERBY", "H2", "HSQL")
## What type of embedded database should ODE use?
## Note: If an embedded name is not specified an in memory database will
## will be used.
##
##   * "DERBY" - ODE will manage an embedded HSQL database instance
##   * "H2" - ODE will manage an embedded HSQL database instance
##   * "HSQL" - ODE will manage an embedded HSQL database instance
#ode-axis2.db.emb.type=H2
ode-axis2.db.emb.type=derby

## External Database [JNDI Name]
## JNDI Name of the DataSource for the ODE database. This is only
## used if the "ode-axis2.db.mode" property is set to "EXTERNAL"
#ode-axis2.db.ext.dataSource=java:comp/env/jdbc/ode

## Embedded Database Name [String]
## Name of the embedded Derby database. This is only used if the
## "ode-axis2.db.mode" property is set to "EMBEDDED".
#ode-axis2.db.emb.name=h2-hibdb
#ode-axis2.db.emb.name=h2-jpadb
#ode-axis2.db.emb.name=derby-hibdb
ode-axis2.db.emb.name=derby-jpadb

## Internal Database Configuration
#ode-axis2.db.int.jdbcurl=jdbc:mysql://localhost/ode?user=sa
#ode-axis2.db.int.driver=com.mysql.jdbc.Driver
#ode-axis2.db.int.password = 
#ode-axis2.db.int.username = 

## DB Pool Configuration for internal DB
#ode-axis2.db.pool.max = 10
#ode-axis2.db.pool.min = 1

## DAO Connection Factory class.
## uncomment the following for hibernate.
#ode-axis2.dao.factory=org.apache.ode.daohib.bpel.BpelDAOConnectionFactoryImpl

## Transaction Factory
## default is org.apache.ode.il.EmbeddedGeronimoFactory
## available alternatives are:
##   org.apache.ode.axis2.util.GeronimoFactory
##   org.apache.ode.axis2.util.JBossFactory
##   org.apache.ode.axis2.util.TomcatFactory
##   org.apache.ode.axis2.util.WLSFactory
#ode-axis2.tx.factory.class = 

## JCA connector port (default 2099), set to 0 to disable JCA connector
#ode-axis2.jca.port = 

## JCA conncetor name (default 'ode')
#ode-axis2.jca.name = 

## Working dir
#ode-axis2.working.dir = 

## MEX Interceptors
#ode-axis2.mex.interceptors = 

## Process dehydration
#ode-axis2.process.dehydration = 

## Event listeners
#ode-axis2.event.listeners=
#ode-axis2.event.listeners=org.apache.ode.bpel.common.evt.DebugBpelEventListener

## Extension Bundles
## FQCNs, comma separated.
ode-axis2.extension.bundles=org.apache.ode.bpel.extension.bpel4restlight.Bpel4RestLightExtensionBundle

# Change the URL of the associated TraDE Middleware node
ode-axis2.traDE.url={{ .Env.TRADE_URL }}

# Specify the host through which ODE is exposed in a containerized setup
ode-axis2.host.ip={{ .Env.HOST_IP }}

# Specify the dynamic port through which ODE is exposed in a containerized setup
ode-axis2.container.port={{ .Env.DYNAMIC_ODE_PORT }}
