package crop.computer.askey.androidlib.http.remoteservice;

import java.util.List;
import java.util.Map;

import crop.computer.askey.androidlib.http.request.HeaderField;
import crop.computer.askey.androidlib.http.request.PathParam;
import crop.computer.askey.androidlib.http.request.QueryAttribute;
import crop.computer.askey.androidlib.http.request.RequestCallback;
import crop.computer.askey.androidlib.http.response.Response;

public abstract class MockService implements RemoteService {

    public abstract Map<String, String> getDummyDataByApiKey();

    private ServiceDataReceiver dataListener;

    public void registerDataReceiver(ServiceDataReceiver dataListener) {
        this.dataListener = dataListener;
    }

    public void unregisterDataReceiver() {
        this.dataListener = null;
    }

    public void invoke(final String key) {
        invoke(key, null, null, null);
    }

    protected void invoke(final String key, List<HeaderField> rqProperties, List<QueryAttribute> rqParams,
                          final String requestBody) {
        invoke(key, rqProperties, rqParams, requestBody, this);
    }

    @Override
    public void invoke(final String key, List<HeaderField> rqProperties, List<QueryAttribute> rqParams,
                       final String requestBody, RequestCallback callback) {
        invoke(key, rqProperties, rqParams, null, requestBody, callback);
    }

    public void invoke(final String key, List<HeaderField> rqProperties, List<QueryAttribute> rqParams,
                       List<PathParam> rqPathParams, final String requestBody, RequestCallback callback) {
        Map<String, String> dummyDataMap = getDummyDataByApiKey();
        if (dummyDataMap != null && dummyDataMap.get(key) != null) {
            String dummyData = dummyDataMap.get(key);
            callback.onSuccess(key, null, dummyData);
        } else {
            callback.onFail(key, null, 0,
                    "[MOCK] the response of this API is undefined");
        }

    }

    @Override
    public void onSuccess(String key, Response response, String content) {
        if(dataListener != null) {
            dataListener.onSuccess(key, content);
        }
    }

    @Override
    public void onFail(String key, Response response, int errorType, String errorMessage) {
        if(dataListener != null) {
            dataListener.onFail(key, errorType, errorMessage);
        }
    }

    @Override
    public void finish() {
        if (dataListener != null) {
            dataListener = null;
        }
    }
}
