package crop.computer.askey.androidlib.http.request;

import java.util.List;

/**
 * Created by weikai on 2019/3/25.
 */

public class RequestConfig {
    private List<HeaderField> rqProperties;
    private List<QueryAttribute> rqParams;
    private String body;

    public RequestConfig(List<HeaderField> rqProperties, List<QueryAttribute> rqParams, String body) {
        super();
        this.rqProperties = rqProperties;
        this.rqParams = rqParams;
        this.body = body;
    }

    public List<HeaderField> getRqProperties() {
        return rqProperties;
    }

    public List<QueryAttribute> getRqParams() {
        return rqParams;
    }

    public String getBody() {
        return body;
    }

}