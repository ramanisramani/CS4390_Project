all:
	javac src/server/*.java src/client/*.java src/protocol/*.java

run-server:
	java -cp src server.MathServer

run-client:
	java -cp src client.MathClient

clean:
	rm -f src/server/*.class src/client/*.class src/protocol/*.class server_log.txt
