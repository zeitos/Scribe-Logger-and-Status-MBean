manually include libfb303-0.7.0.jar from your thrift distribution folder
to start scribe go to resources and run
scribed -c local_server.conf
scribed -c central_server.conf


the main classes are: 
TestLogger.jar that will log 2 messages to the local server

ScribeStatusMBeanRunner that will start the Status MBean

from developersnightmare.blogspot.com
