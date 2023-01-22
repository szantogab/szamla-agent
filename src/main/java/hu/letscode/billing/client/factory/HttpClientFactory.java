package hu.letscode.billing.client.factory;


import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

/**
 * Factory for creating {@link CloseableHttpClient}.
 */
public interface HttpClientFactory {

    /**
     * Creates a new {@link CloseableHttpClient}.
     * @return CloseableHttpClient
     */
    CloseableHttpClient create() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException;

}
