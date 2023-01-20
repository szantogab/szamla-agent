package hu.letscode.billing.domain.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import hu.letscode.billing.domain.TaxCode;

import java.io.IOException;

public class TaxCodeSerializer extends JsonSerializer<TaxCode> {

    @Override
    public void serialize(TaxCode value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {
        jsonGenerator.writeString(value.getValue());
    }

}
