package hu.letscode.billing.client;

import hu.letscode.billing.client.factory.HttpClientFactory;
import hu.letscode.billing.client.factory.HttpPostFactory;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;

import java.io.IOException;
import java.io.InputStream;

/**
 * The client to send requests toward the szamla agent.
 */
public class SzamlaAgentClient {

    public static final String SZAMLA_AGENT_API_URL = "https://www.szamlazz.hu/szamla/?page=main";
    private final HttpClientFactory httpClientFactory;
    private final HttpPostFactory httpPostFactory;
    private final String apiUrl;

    /**
     * Default constructor.
     * @param httpClientFactory the factory to create HTTP clients
     * @param httpPostFactory the factory to create POST requests
     * @param apiUrl the API url.
     */
    public SzamlaAgentClient(HttpClientFactory httpClientFactory, HttpPostFactory httpPostFactory, String apiUrl) {
        this.httpClientFactory = httpClientFactory;
        this.httpPostFactory = httpPostFactory;
        this.apiUrl = apiUrl;
    }

    /**
     * Executes a request toward the API with the given field and content.
     * @param field the field name to be used.
     * @param xmlContent the content of the request body.
     * @return {@link InputStream}
     */
    public String execute(XmlField field, byte[] xmlContent) {
        CloseableHttpClient httpClient = httpClientFactory.create();
        HttpPost httpPost = httpPostFactory.createWithEntity(apiUrl, field.getName(), xmlContent);
        try {
            return httpClient.execute(httpPost, response -> new String(response.getEntity().getContent().readAllBytes()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
