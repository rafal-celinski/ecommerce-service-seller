package pis24l.projekt.api_seller.configs;

import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.PutMappingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class ElasticsearchIndexConfig {

    private final RestHighLevelClient client;

    @Autowired
    public ElasticsearchIndexConfig(RestHighLevelClient client) {
        this.client = client;
    }

    @PostConstruct
    public void createIndexIfNotExists() throws Exception {
        GetIndexRequest getIndexRequest = new GetIndexRequest("products");
        boolean exists = client.indices().exists(getIndexRequest, RequestOptions.DEFAULT);
        if (!exists) {
            CreateIndexRequest createIndexRequest = new CreateIndexRequest("products");
            createIndexRequest.settings("""
                    {
                      "number_of_shards": 1,
                      "number_of_replicas": 1
                    }""", org.elasticsearch.common.xcontent.XContentType.JSON);

            CreateIndexResponse createIndexResponse = client.indices().create(createIndexRequest, RequestOptions.DEFAULT);
            if (!createIndexResponse.isAcknowledged()) {
                throw new RuntimeException("Failed to create index 'products'");
            }

            PutMappingRequest putMappingRequest = new PutMappingRequest("products");
            putMappingRequest.source("""
                    {
                      "properties": {
                        "id": {"type": "keyword"},
                        "title": {"type": "text"},
                        "price": {"type": "float"},
                        "location": {
                          "type": "text",
                          "fields": {
                            "keyword": {
                              "type": "keyword",
                              "ignore_above": 256
                            }
                          }
                        },
                        "date": {
                          "type": "date",
                          "format": "uuuu-MM-dd HH:mm:ss||uuuu-MM-dd||epoch_millis||strict_date_optional_time||strict_date"
                        },
                        "category": {
                          "type": "text",
                          "fields": {
                            "keyword": {
                              "type": "keyword",
                              "ignore_above": 256
                            }
                          }
                        },
                        "subcategory": {
                          "type": "text",
                          "fields": {
                            "keyword": {
                              "type": "keyword",
                              "ignore_above": 256
                            }
                          }
                        },
                        "description": {"type": "text"},
                        "imageUrls": {"type": "keyword"}
                      }
                    }""", org.elasticsearch.common.xcontent.XContentType.JSON);

            client.indices().putMapping(putMappingRequest, RequestOptions.DEFAULT);
        }
    }
}
