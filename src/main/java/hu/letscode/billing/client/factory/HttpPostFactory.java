package hu.letscode.billing.client.factory;

import hu.letscode.billing.client.ByteContentBody;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
import org.apache.hc.core5.http.HttpEntity;

/**
 * Class for creating {@link HttpPost} with multipart entites in it.
 */
public class HttpPostFactory {

    /**
     * Creates a {@link HttpPost}.
     * @param url the URL to point the request
     * @param fieldName the field to put the file in
     * @param multipartContent the content of the file
     * @return HttpPost filled with the multipart entity
     */
    public HttpPost createWithEntity(String url, String fieldName, byte[] multipartContent) {
        HttpPost httpPost = new HttpPost(url);
        ByteContentBody body = new ByteContentBody(fieldName, multipartContent);
        HttpEntity requestEntity = MultipartEntityBuilder.create().addPart(fieldName, body).build();
        httpPost.setEntity(requestEntity);
        return httpPost;
    }

}
