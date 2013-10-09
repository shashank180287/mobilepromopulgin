d:
cd "Program Files\apache-activemq-5.6.0\bin\"
start activemq
cd ../..
cd mongodb-win32-i386-2.2.0\mongodb-win32-i386-2.2.0\bin
start mongod.exe
start psql -U postgres promotion_plugin
sleep 5
start mongo.exe
cd ../../..
cd "Apache Software Foundation\apache-tomcat-7.0.30-secondary\bin"
debug.bat
