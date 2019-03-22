#    Licensed to the Apache Software Foundation (ASF) under one or more
#    contributor license agreements.  See the NOTICE file distributed with
#    this work for additional information regarding copyright ownership.
#    The ASF licenses this file to You under the Apache License, Version 2.0
#    (the "License"); you may not use this file except in compliance with
#    the License.  You may obtain a copy of the License at
#
#       http://www.apache.org/licenses/LICENSE-2.0
#
#    Unless required by applicable law or agreed to in writing, software
#    distributed under the License is distributed on an "AS IS" BASIS,
#    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
#    See the License for the specific language governing permissions and
#    limitations under the License.

ANNONGEN            = "annogen:annogen:jar:0.1.0"
ANT                 = "ant:ant:jar:1.6.5"
AXIOM               = [ group("axiom-api", "axiom-impl", "axiom-dom",
                        :under=>"org.apache.ws.commons.axiom", :version=>"1.2.20") ]
AXIS2_ALL           = [ group("axis2-adb", "axis2-codegen", "axis2-kernel",
                        "axis2-java2wsdl", "axis2-jibx", "axis2-saaj", "axis2-xmlbeans",
                        :under=>"org.apache.axis2", :version=>"1.7.6"),
                        group("woden-core",
                              :under=>"org.apache.woden",
                              :version=>"1.0M10"),
                      "org.apache.axis2:axis2-transport-base:jar:1.7.6",
                      "org.apache.axis2:axis2-transport-http:jar:1.7.6",
                      "org.apache.axis2:axis2-transport-local:jar:1.7.6",
                      "org.apache.axis2:axis2-transport-tcp:jar:1.7.6",
                      "org.apache.axis2:axis2-transport-jms:jar:1.7.6",
                      "org.apache.httpcomponents:httpcore:jar:4.4.4"]
AXIS2_TEST          = group("httpcore", "httpcore-nio", 
                           :under=>"org.apache.httpcomponents", :version=>"4.4.4")
AXIS2_MODULES        = struct(
 :mods              => ["org.apache.rampart:rampart:mar:1.7.1",
                         "org.apache.rampart:rahas:mar:1.7.1",
                         "org.apache.axis2:addressing:mar:1.7.6",
                         "org.apache.axis2:mex:mar:1.7.6"],
 :libs              => [group("rampart-core", "rampart-policy", "rampart-trust",
                              :under=>"org.apache.rampart",
                              :version=>"1.7.1"),
                        "org.apache.ws.security:wss4j:jar:1.6.16",
                        "org.apache.santuario:xmlsec:jar:1.5.7",
                        "org.apache.axis2:mex:jar:impl:1.7.6",
                        "org.opensaml:opensaml1:jar:1.1",
                        "org.opensaml:opensaml:jar:2.5.1-1",
                        "org.opensaml:openws:jar:1.4.2-1",
                        "org.opensaml:xmltooling:jar:1.3.2-1",
                        "org.bouncycastle:bcprov-jdk15on:jar:1.49",
                        "velocity:velocity:jar:1.5",
                        "joda-time:joda-time:jar:2.5",
                        "org.owasp.esapi:esapi:jar:2.0GA",
                        "org.apache.james:apache-mime4j-core:jar:0.7.2"]
)
AXIS2_WAR           = "org.apache.axis2:axis2-webapp:war:1.7.6"
BACKPORT            = "backport-util-concurrent:backport-util-concurrent:jar:3.1"
COMMONS             = struct(
  :codec            =>"commons-codec:commons-codec:jar:1.3",
  :collections      =>"commons-collections:commons-collections:jar:3.2.2",
  :dbcp             =>"commons-dbcp:commons-dbcp:jar:1.4",
  :fileupload       =>"commons-fileupload:commons-fileupload:jar:1.3.3",
  :httpclient       =>"commons-httpclient:commons-httpclient:jar:3.1",
  :lang             =>"commons-lang:commons-lang:jar:2.6",
  :logging          =>"commons-logging:commons-logging:jar:1.1.1",
  :io               =>"commons-io:commons-io:jar:2.1",
  :pool             =>"commons-pool:commons-pool:jar:1.6",
  :primitives       =>"commons-primitives:commons-primitives:jar:1.0",
  :beanutils        =>"commons-beanutils:commons-beanutils:jar:1.8.2"
)
DERBY               = "org.apache.derby:derby:jar:10.5.3.0_1"
DERBY_TOOLS         = "org.apache.derby:derbytools:jar:10.5.3.0_1"
DOM4J               = "dom4j:dom4j:jar:1.6.1"
GERONIMO            = struct(
  :kernel           =>"org.apache.geronimo.modules:geronimo-kernel:jar:2.0.1",
  :transaction      =>"org.apache.geronimo.components:geronimo-transaction:jar:3.1.4",
  :connector        =>"org.apache.geronimo.components:geronimo-connector:jar:3.1.4"
)
HIBERNATE           = ["org.hibernate:hibernate-core:jar:4.3.11.Final", "org.javassist:javassist:jar:3.18.1-GA", "antlr:antlr:jar:2.7.7",
                        "dom4j:dom4j:jar:1.6.1", "org.hibernate.common:hibernate-commons-annotations:jar:4.0.5.Final", 
                        "org.hibernate.javax.persistence:hibernate-jpa-2.1-api:jar:1.0.0.Final"  , "org.jboss:jandex:jar:1.1.0.Final", 
                        "org.jboss.logging:jboss-logging:jar:3.1.3.GA" , "org.jboss.logging:jboss-logging-annotations:jar:1.2.0.Beta1"]
HK2                 = [
                        "org.glassfish.hk2:hk2-api:jar:2.2.0",
                        "org.glassfish.hk2:hk2-utils:jar:2.2.0",
                        "org.glassfish.hk2:hk2-locator:jar:2.2.0",
                        "org.glassfish.hk2.external:aopalliance-repackaged:jar:2.2.0",
                        "org.glassfish.hk2.external:javax.inject:jar:2.2.0",
                        "org.glassfish.hk2:osgi-resource-locator:jar:1.0.1"
                      ]
HSQLDB              = "org.hsqldb:hsqldb:jar:2.3.3"
JAVAX               = struct(
  :annotation       =>"javax.annotation:javax.annotation-api:jar:1.2",
  :activation       =>"javax.activation:activation:jar:1.1",
  #:activation       =>"geronimo-spec:geronimo-spec-activation:jar:1.0.2-rc4",
  :connector        =>"org.apache.geronimo.specs:geronimo-j2ee-connector_1.6_spec:jar:1.0",
  :ejb              =>"org.apache.geronimo.specs:geronimo-ejb_2.1_spec:jar:1.1",
  :javamail         =>"org.apache.geronimo.specs:geronimo-javamail_1.4_spec:jar:1.7.1",
  :jms              =>"org.apache.geronimo.specs:geronimo-jms_1.1_spec:jar:1.1.1",
  :persistence      =>"org.apache.geronimo.specs:geronimo-jpa_2.0_spec:jar:1.1",
  :servlet          =>"org.apache.geronimo.specs:geronimo-servlet_2.4_spec:jar:1.0",
  :stream           =>"org.codehaus.woodstox:stax2-api:jar:4.0.0",
  :transaction      =>"org.apache.geronimo.specs:geronimo-jta_1.1_spec:jar:1.1.1",
  :resource         =>"org.apache.geronimo.specs:geronimo-j2ee-connector_1.6_spec:jar:1.0",
  :validation       =>"javax.validation:validation-api:jar:1.1.0.Final",
  :wsrs             =>"javax.ws.rs:javax.ws.rs-api:jar:2.0"
)
JACKSON             = [
                        "com.fasterxml.jackson.core:jackson-core:jar:2.3.2",
                        "com.fasterxml.jackson.core:jackson-annotations:jar:2.3.2",
                        "com.fasterxml.jackson.core:jackson-databind:jar:2.3.2",
                        "com.fasterxml.jackson.jaxrs:jackson-jaxrs-json-provider:jar:2.3.2",
                        "com.google.guava:guava:jar:20.0"
                      ]
JAXEN               = "jaxen:jaxen:jar:1.1.6"
JBI                 = group("org.apache.servicemix.specs.jbi-api-1.0", :under=>"org.apache.servicemix.specs", :version=>"1.1.0")
JENCKS              = "org.jencks:jencks:jar:all:2.2"
JERSEY              = [
                        "org.glassfish.jersey.core:jersey-client:jar:2.6",
                        "org.glassfish.jersey.media:jersey-media-multipart:jar:2.6",
                        "org.glassfish.jersey.media:jersey-media-json-jackson:jar:2.6"
                      ]
JIBX                = "org.jibx:jibx-run:jar:1.2.1"
KARAF               = [
                        "org.apache.felix:org.osgi.core:jar:1.4.0",
                        "org.apache.felix:org.osgi.compendium:jar:1.4.0",
                        "org.apache.karaf.shell:org.apache.karaf.shell.console:jar:2.2.0",
                        group("org.apache.felix.gogo.command","org.apache.felix.gogo.runtime",
                          :under=>"org.apache.felix", :version=>"0.6.1")
                      ]
LOG4J               = "log4j:log4j:jar:1.2.17"
LOG4J2               = group("log4j-api", "log4j-core", "log4j-slf4j-impl", "log4j-web", :under=>"org.apache.logging.log4j", :version=>"2.3")
MIGBASE64           = "com.brsanthu:migbase64:jar:2.2"
OPENJPA             = ["org.apache.openjpa:openjpa:jar:2.4.2",
                       "net.sourceforge.serp:serp:jar:1.15.1",
                       "org.apache.xbean:xbean-asm5-shaded:jar:4.5"]

SAXON               = ["net.sourceforge.saxon:saxon:jar:9.1.0.8", "net.sourceforge.saxon:saxon:jar:xpath:9.1.0.8", "net.sourceforge.saxon:saxon:jar:dom:9.1.0.8", "net.sourceforge.saxon:saxon:jar:xqj:9.1.0.8"]
SERVICEMIX          = [
                        group("servicemix-core", 
                            :under=>"org.apache.servicemix", :version=>"3.3"), 
                        group("servicemix-soap", "servicemix-common", "servicemix-shared", "servicemix-http", "servicemix-eip",
                            :under=>"org.apache.servicemix", :version=>"2008.01"), 
                        group("servicemix-utils", 
                            :under=>"org.apache.servicemix", :version=>"1.0.0"),
                        "commons-httpclient:commons-httpclient:jar:3.0", 
                        "commons-codec:commons-codec:jar:1.2",
                        "org.mortbay.jetty:jetty:jar:6.1.12rc1",
                        "org.mortbay.jetty:jetty-client:jar:6.1.12rc1",
                        "org.mortbay.jetty:jetty-sslengine:jar:6.1.12rc1",
                        "org.mortbay.jetty:servlet-api-2.5:jar:6.1.12rc1",
                        "org.mortbay.jetty:jetty-util:jar:6.1.12rc1",
                        "org.codehaus.woodstox:wstx-asl:jar:3.2.2",
                        "org.apache.geronimo.specs:geronimo-activation_1.1_spec:jar:1.0.1",
                        "org.apache.geronimo.specs:geronimo-annotation_1.0_spec:jar:1.1",
                        "org.apache.geronimo.specs:geronimo-javamail_1.4_spec:jar:1.2",
                        "org.apache.geronimo.specs:geronimo-stax-api_1.0_spec:jar:1.0.1",
                        "org.apache.geronimo.specs:geronimo-jms_1.1_spec:jar:1.1",
                        "org.jencks:jencks:jar:2.1",
                        "org.objectweb.howl:howl:jar:1.0.1-1",
                        "org.apache.activemq:activemq-core:jar:4.1.1",
                        "org.apache.activemq:activemq-ra:jar:4.1.1",
                        "commons-beanutils:commons-beanutils:jar:1.7.0",
                        "tranql:tranql-connector-derby-common:jar:1.1"
                        ]
SLF4J = group(%w{ slf4j-api jcl-over-slf4j}, :under=>"org.slf4j", :version=>"1.7.12")
SPRING              = ["org.springframework:spring:jar:2.5.6"]
SPRING_OSGI         = ["org.springframework.osgi:spring-osgi-core:jar:1.2.0"]
SPRING_TEST         = ["org.springframework:spring-test:jar:2.5.6"]
TRANQL              = [ "org.tranql:tranql-connector:jar:1.8", COMMONS.primitives ]
WOODSTOX            = "org.codehaus.woodstox:woodstox-core-asl:jar:4.2.0"
WSDL4J              = "wsdl4j:wsdl4j:jar:1.6.3"
XALAN               = ["xalan:xalan:jar:2.7.1", "xalan:serializer:jar:2.7.1"]
XERCES              = ["xerces:xercesImpl:jar:2.11.0", "xml-apis:xml-apis:jar:1.4.01"]
WS_COMMONS          = struct(
  :axiom            =>AXIOM,
  :neethi           =>"org.apache.neethi:neethi:jar:3.0.3",
  :xml_schema       =>"org.apache.ws.xmlschema:xmlschema-core:jar:2.2.1"
)
XBEAN               = [
  "org.apache.xbean:xbean-kernel:jar:3.3",
  "org.apache.xbean:xbean-server:jar:3.3",
  "org.apache.xbean:xbean-spring:jar:3.4.3",
  "org.apache.xbean:xbean-classloader:jar:3.4.3"
]
XMLBEANS            = "org.apache.xmlbeans:xmlbeans:jar:2.6.0"

TOMEE = struct(
    :tomee =>  [
                  group(
                    "tomee-embedded",
                    "tomee-catalina",
                    "tomee-common",
                    "tomee-loader",
                    "tomee-jdbc",
                    "tomee-juli",
                    "tomee-util",
                    :under=>"org.apache.tomee", :version=>"7.0.4"
                    )
                 ],
    :openejb => [
                    group(
                      "openejb-server",
                      "openejb-ejbd",
                      "openejb-http",
                      "openejb-core",
                      "openejb-api",
                      "openejb-javaagent",
                      "openejb-jee",
                      "openejb-jee-accessors",
                      "openejb-loader",
                      "openejb-jpa-integration",
                      "openejb-client",
                      "mbean-annotation-api",
                      :under=>"org.apache.tomee", :version=>"7.0.4"
                    ),
                      "org.apache.openejb.shade:quartz-openejb-shade:jar:2.2.1"
                  ],
    :tomcat => [
                  group(
                      "tomcat-websocket-api",
                      "tomcat-jdbc",
                      "tomcat-dbcp",
                      "tomcat-catalina",
                      "tomcat-servlet-api",
                      "tomcat-jsp-api",
                      "tomcat-api",
                      "tomcat-jni",
                      "tomcat-util",
                      "tomcat-util-scan",
                      "tomcat-catalina-ha",
                      "tomcat-tribes",
                      "tomcat-coyote",
                      "tomcat-jasper",
                      "tomcat-el-api",
                      "tomcat-jasper-el",
                      "tomcat-websocket",
                      "tomcat-juli",
                      :under=>"org.apache.tomcat", :version=>"8.5.23"
                    ),
                "org.apache.tomcat.extras:tomcat-extras-juli:jar:8.5.2"
               ],
    :javaee_api => "org.apache.tomee:javaee-api:jar:7.0-1",
    :commons => ["org.apache.commons:commons-lang3:jar:3.5",
                  "org.apache.commons:commons-dbcp2:jar:2.1",
                  "org.apache.commons:commons-pool2:jar:2.3"
                ],
    :commons_cli => "commons-cli:commons-cli:jar:1.2",
    :commons_collections => "commons-collections:commons-collections:jar:3.2.2",
    :commons_beanutils => "commons-beanutils:commons-beanutils:jar:1.9.3",
    :commons_lang => "commons-lang:commons-lang:jar:2.6",
    :sxc => [
              group(
                "sxc-jaxb-core",
                "sxc-runtime",
                :under=>"org.metatype.sxc", :version=>"0.8"
              )
            ],
     :activemq => [group(
                    "activemq-ra",
                    "activemq-kahadb-store",
                    "activemq-broker",
                    "activemq-client",
                    "activemq-openwire-legacy",
                    "activemq-jdbc-store",
                    :under=>"org.apache.activemq", :version=>"5.14.5"
                  ),
                    "org.apache.activemq.protobuf:activemq-protobuf:jar:1.1"
                  ],
      :geronimo => ["org.apache.geronimo.components:geronimo-transaction:jar:3.1.4",
                    "org.apache.geronimo.javamail:geronimo-javamail_1.4_mail:jar:1.9.0-alpha-2",
                    "org.apache.geronimo.components:geronimo-connector:jar:3.1.4",
                   ],
      :howl => "org.objectweb.howl:howl:jar:1.0.1-1",
      :xbean => group(
                  "xbean-asm5-shaded",
                  "xbean-finder-shaded",
                  "xbean-naming",
                  "xbean-reflect",
                  "xbean-bundleutils",
                  :under=>"org.apache.xbean", :version=>"4.5"
                ),
       :hsqldb => "org.hsqldb:hsqldb:jar:2.3.2",
       :swizzle => "org.codehaus.swizzle:swizzle-stream:jar:1.6.2",
       :openwebbeans => group(
                        "openwebbeans-impl",
                        "openwebbeans-spi",
                        "openwebbeans-ejb",
                        "openwebbeans-ee",
                        "openwebbeans-ee-common",
                        "openwebbeans-web",
                        "openwebbeans-el22",
                        :under=>"org.apache.openwebbeans", :version=>"1.7.4"
                        ),
        :bval => group(
                    "bval-core",
                    "bval-jsr",
                    :under=>"org.apache.bval", :version=>"1.1.2"
                 ),
        :velocity => "org.apache.velocity:velocity:jar:1.6.4",
        :oro => "oro:oro:jar:2.0.8",
        :openjpa => ["org.apache.openjpa:openjpa:jar:2.4.2","net.sourceforge.serp:serp:jar:1.15.1"]
)

TRADE_API           = [ JERSEY, JACKSON, MIGBASE64, JAVAX.wsrs, JAVAX.annotation, JAVAX.validation, HK2,
                        "org.javassist:javassist:jar:3.18.1-GA",
                        "org.jvnet.mimepull:mimepull:jar:1.9.3",
                        "org.glassfish.jersey.core:jersey-common:jar:2.6",
                        "org.glassfish.jersey.core:jersey-server:jar:2.6",
                        "org.glassfish.jersey.bundles.repackaged:jersey-guava:jar:2.6",
                        "com.fasterxml.jackson.jaxrs:jackson-jaxrs-base:jar:2.3.2",
                        "com.fasterxml.jackson.module:jackson-module-jaxb-annotations:jar:2.3.2"
                      ]

ODE_WEB_CONSOLE     = "org.apache.ode:ode-console:jar:0.1.0"
TUCKEY_URLREWRITE   = "org.tuckey:urlrewritefilter:jar:4.0.4"
