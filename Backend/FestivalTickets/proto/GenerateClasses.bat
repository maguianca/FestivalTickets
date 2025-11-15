echo "Generating Java classes"
protoc -I=. --java_out=proto/javaFiles proto/FestivalProtocol.proto

