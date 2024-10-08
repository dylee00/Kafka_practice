package com.kafka_pipeline_test.kafka_pipeline_test;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.IndexRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class TestConsumer {

    private final ElasticsearchClient elasticsearchClient;

    @Autowired
    public TestConsumer(ElasticsearchClient elasticsearchClient) {
        this.elasticsearchClient = elasticsearchClient;
    }

    @KafkaListener(topics = "topic", groupId = "group_1")
    public void listen(String message) {
        System.out.println("Received Message in group group_1: " + message);
        // 메시지를 Elasticsearch에 저장하는 로직 추가
        try {
            saveToElasticsearch(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveToElasticsearch(String message) throws IOException {
        // 데이터를 인덱스에 저장하기 위한 Map 생성
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("message", message);
        jsonMap.put("timestamp", System.currentTimeMillis());

        // Elasticsearch 인덱스 요청 생성
        IndexRequest<Map<String, Object>> indexRequest = IndexRequest.of(i -> i
                .index("kafka-messages-v5") // 인덱스 이름
                .document(jsonMap)  // 저장할 문서
        );
        System.out.println("Saving document to Elasticsearch: " + jsonMap);

        // Elasticsearch에 데이터 인덱싱
        elasticsearchClient.index(indexRequest);

        // 로그 추가 - 인덱싱 후 로그 출력
        System.out.println("Document successfully saved to Elasticsearch");

    }
}
