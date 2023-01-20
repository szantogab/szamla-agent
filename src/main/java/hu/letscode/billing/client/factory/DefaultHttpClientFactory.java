package hu.letscode.billing.client.factory;


import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;

/**
 * Created by tacsiazuma on 2017.05.14..
 */
public class DefaultHttpClientFactory implements HttpClientFactory {

    /**
     * {@inheritDoc}
     */
    public CloseableHttpClient create() {
        return HttpClients.createDefault();
    }

}
