package com.yunjae.kafka.streams

import java.util.Properties
import java.util.regex.Pattern

import com.lightbend.kafka.scala.streams.{KTableS, StreamsBuilderS}
import org.apache.kafka.common.serialization.{Serdes}
import org.apache.kafka.streams.{KafkaStreams, StreamsConfig}
import com.lightbend.kafka.scala.streams.ImplicitConversions._
import com.lightbend.kafka.scala.streams.DefaultSerdes._

object StreamStarterApp {
  def main(args: Array[String]): Unit = {
    val config = {
      val properties = new Properties()
      properties.put(StreamsConfig.APPLICATION_ID_CONFIG, "wordcount-application")
      properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
      //properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest")
      properties.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass)
      properties.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass)
      properties
    }

    val pattern = Pattern.compile("\\W+", Pattern.UNICODE_CHARACTER_CLASS)

    val builder = new StreamsBuilderS()
    val textLines = builder.stream[String, String]("wordcount-input")

    val wordCounts: KTableS[String, Long] = textLines
      .flatMapValues(textLines => textLines.toLowerCase.split("\\W+"))
      .map { (_, value) => (value, value) }
      //.groupBy((k, v) => v)
      .groupByKey
      .count("Counts")


    wordCounts.toStream.to("wordcount-input")
    val stream = new KafkaStreams(builder.build(), config)
    stream.start()
    println(stream)

    /*Runtime.getRuntime.addShutdownHook(new Thread {
      stream.close()
    })*/
  }
}
