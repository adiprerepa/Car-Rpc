import com.prerepa.esp8266_client.MessageFactory;
import com.prerepa.generated.Request;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MessageFactoryTest {

    @Test
    public void buildRequestTest() {
        int mockData = 92876;
        Request mockRequest = Request.newBuilder()
                .setRequestData(mockData)
                .build();
        Request generatedRequest = MessageFactory.buildRequest(mockData);
        assertEquals(mockRequest, generatedRequest);
    }
}
