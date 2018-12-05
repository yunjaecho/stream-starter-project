# stream-starter-project
scala kafka stream

## kafka sample lib jar(wordcount) execute
### zookeeper start
bin/zookeeper-server-start.sh config/zookeeper.properties

### kafka start
bin/kafka-server-start.sh config/server.properties

### kafka create input topic
bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic streams-plaintext-input

### kafka create output topic
bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic streams-wordcount-output

### kafka topic list
bin/kafka-topics.sh --zookeeper localhost:2181 --list

### start a kafka producer
bin/kafka-console-producer.sh --broker-list 127.0.0.1:9092 --topic streams-plaintext-input

### enter data
kafka stream udemy kafka data proccessing kafka streams course

### verify the data has been written
bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic streams-plaintext-input --from-beginning

### start a consumer on the output topic
bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 \
--topic streams-wordcount-output \
--from-beginning \
--formatter kafka.tools.DefaultMessageFormatter \
--property print.key=true \
--property print.value=true \
--property key.deserializer=org.apache.kafka.common.serialization.StringDeserializer \
--property value.deserializer=org.apache.kafka.common.serialization.StringDeserializer

### wordcount application execute
bin/kafka-run-class.sh org.apache.kafka.streams.examples.wordcount.WordCountDemo





## Custom WordCount Application
### zookeeper start
bin/zookeeper-server-start.sh config/zookeeper.properties

### kafka start
bin/kafka-server-start.sh config/server.properties

### kafka create input topic
bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 2 --topic word-count-input

### kafka create output topic
bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 2 --topic word-count-output

### kafka topic list
bin/kafka-topics.sh --zookeeper localhost:2181 --list

### start a consumer on the output topic
bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 \
--topic word-count-output \
--from-beginning \
--formatter kafka.tools.DefaultMessageFormatter \
--property print.key=true \
--property print.value=true \
--property key.deserializer=org.apache.kafka.common.serialization.StringDeserializer \
--property value.deserializer=org.apache.kafka.common.serialization.LongDeserializer

### launch the streams application (IDE)

### start a kafka producer
bin/kafka-console-producer.sh --broker-list 127.0.0.1:9092 --topic word-count-input

### enter data
kafka stream udemy 
kafka data proccessing 
kafka streams course
