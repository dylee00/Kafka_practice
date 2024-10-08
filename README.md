## 1008
### 공부한 것
- kafka producer에서 key-value형태로 message를 날리면 consumer에 어떻게 작성되는지 직접 해봄
- docker로 zookeeper, kafka cluser 사용해 봄
- docker로 elastic search, Grafana 사용해 봄
- 이때 Grafana와 elastic search매핑 과정에서 timestamp의 type이 Long이라 mapping이 안되는 문제가 발생했지만, timestamp에 format을 넣어서 문제 해결
- ```
  {
  "kafka-messages-v5" : {
    "mappings" : {
      "properties" : {
        "message" : {
          "type" : "text",
          "fields" : {
            "keyword" : {
              "type" : "keyword",
              "ignore_above" : 256
            }
          }
        },
        "timestamp" : {
          "type" : "date",
          "format" : "epoch_millis"
        }
      }
    }
  }
}
```
### 보충해야 할 점
- kafka 아키텍처에 대한 공부 필요 (producer -> kafka cluster -> consumer)
- elastic search의 로그 수집 및 Grafana로 시각화 되는 과정에 대한 이해 필요
