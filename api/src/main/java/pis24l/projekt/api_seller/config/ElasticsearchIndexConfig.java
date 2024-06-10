package pis24l.projekt.api_seller.config;

import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.PutMappingRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Configuration
public class ElasticsearchIndexConfig {

    private final RestHighLevelClient client;

    @Autowired
    public ElasticsearchIndexConfig(RestHighLevelClient client) {
        this.client = client;
    }

    @PostConstruct
    public void createIndexIfNotExists() throws IOException {
        GetIndexRequest getIndexRequest = new GetIndexRequest("products");
        boolean exists = client.indices().exists(getIndexRequest, RequestOptions.DEFAULT);
        if (!exists) {
            CreateIndexRequest createIndexRequest = new CreateIndexRequest("products");
            createIndexRequest.settings("""
                    {
                      "number_of_shards": 1,
                      "number_of_replicas": 1
                    }""", XContentType.JSON);

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
                        "location": {"type": "text"},
                        "date": {"type": "date", "format": "uuuu-MM-dd"},
                        "category": {"type": "keyword"},
                        "subcategory": {"type": "keyword"},
                        "description": {"type": "text"},
                        "imageUrls": {"type": "keyword"}
                      }
                    }""", XContentType.JSON);

            client.indices().putMapping(putMappingRequest, RequestOptions.DEFAULT);
        }
    }
}
