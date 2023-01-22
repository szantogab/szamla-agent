package hu.letscode.billing.client.factory;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.client5.http.io.HttpClientConnectionManager;
import org.apache.hc.client5.http.ssl.NoopHostnameVerifier;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactoryBuilder;
import org.apache.hc.client5.http.ssl.TrustAllStrategy;
import org.apache.hc.core5.ssl.SSLContextBuilder;
import org.apache.hc.core5.ssl.SSLContexts;

import javax.net.ssl.SSLContext;

public class TrustAllHttpClientFactory implements HttpClientFactory {

    @Override
    public CloseableHttpClient create() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        final SSLContext sslcontext = SSLContexts.custom()
                .loadTrustMaterial(null, new TrustAllStrategy())
                .build();
        final SSLConnectionSocketFactory sslSocketFactory = SSLConnectionSocketFactoryBuilder.create()
                .setSslContext(sslcontext)
                .build();
        final HttpClientConnectionManager cm = PoolingHttpClientConnectionManagerBuilder.create()
                .setSSLSocketFactory(sslSocketFactory)
                .build();
        return HttpClients.custom()
                .setConnectionManager(cm)
                .evictExpiredConnections()
                .build();
    }
}
