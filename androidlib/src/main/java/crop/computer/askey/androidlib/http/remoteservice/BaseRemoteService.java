package crop.computer.askey.androidlib.http.remoteservice;

import android.util.Log;

import java.util.List;
import java.util.Locale;

import crop.computer.askey.androidlib.http.request.HeaderField;
import crop.computer.askey.androidlib.http.request.HttpRequest;
import crop.computer.askey.androidlib.http.request.HttpsRequest;
import crop.computer.askey.androidlib.http.request.QueryAttribute;
import crop.computer.askey.androidlib.http.request.Request;
import crop.computer.askey.androidlib.http.request.RequestCallback;
import crop.computer.askey.androidlib.http.request.RequestManager;
import crop.computer.askey.androidlib.http.response.Response;
import crop.computer.askey.androidlib.http.url.URLConfigManager;
import crop.computer.askey.androidlib.http.url.URLInfo;


public abstract class BaseRemoteService
        implements RemoteService {

    private boolean isApiDebugModeEnabled;

    private RequestManager mRequestManager;

    private URLConfigManager mURLConfigManager;

    private ServiceDataReceiver dataListener;

    public boolean isApiDebugModeEnabled() {
        return isApiDebugModeEnabled;
    }

    public void enableApiDebugMode(boolean enabled) {
        isApiDebugModeEnabled = enabled;
    }

    public void registerDataReceiver(ServiceDataReceiver dataListener) {
        Log.i("LOG_TAG_API_DEBUG", "registerDataReceiver()");
        this.dataListener = dataListener;
    }

    public void unregisterDataReceiver() {
        Log.i("LOG_TAG_API_DEBUG", "unregisterDataReceiver()");
        this.dataListener = null;
    }

    protected void invoke(final String key, List<HeaderField> rqProperties, List<QueryAttribute> rqParams,
                          final String requestBody) {
        invoke(key, rqProperties, rqParams, requestBody, this);
    }

    @Override
    public void invoke(final String key, List<HeaderField> rqProperties, List<QueryAttribute> rqParams,
                       final String requestBody, RequestCallback callback) {

        final URLInfo urlInfo = getURLConfigManager().findURL(key);
        if (urlInfo == null) {
            callback.onFail(key, null, 5, "Cannot find the URLInfo for key: " + key);
            return;
        }

        String url = generateDefaultURLString(urlInfo);

        if (interceptURLString(url) != null) {
            url = interceptURLString(url);
        }

        if (isApiDebugModeEnabled) {
            Log.d("LOG_TAG_API_DEBUG", String.format(Locale.US, "%s: %s %s", key, urlInfo.getMethod(), url));
        }

        Request request;

        if (url.startsWith("https")) {
            request = new HttpsRequest(url);
        } else {
            request = new HttpRequest(url);
        }

        request.setKey(key);
        request.setMethod(urlInfo.getMethod());
        request.setRqProperties(rqProperties);
        request.setRqParams(rqParams);
        request.setBody(requestBody);
        request.setCallback(callback);

        getRequestManager().start();
        getRequestManager().execute(request);
    }

    public void cancelRequest() {
        if (getRequestManager() != null) {
            getRequestManager().cancelRequests();
        }
    }

    private String generateDefaultURLString(URLInfo urlInfo) {
        StringBuilder builder = new StringBuilder();
        if (urlInfo == null) {
            System.out.println("urlInfo == null");
            throw new RuntimeException("cannot find the url info");
        }
        builder.append(urlInfo.getScheme());
        builder.append(urlInfo.getHost());
        String port =urlInfo.getPort();
        if(port != null) {
            builder.append(":").append(port);
        }
        builder.append(urlInfo.getPath());
        return builder.toString();
    }

    protected abstract RequestManager injectRequestManager();

    protected abstract URLConfigManager injectURLConfigManager();

    protected abstract String interceptURLString(String url);


    @Override
    public void onSuccess(String key, Response response, String content) {
        Log.d("LOG_TAG_API_DEBUG", String.format(Locale.US, "%s: content: %s", key, content));
        if (dataListener != null) {
            Log.d("LOG_TAG_API_DEBUG", String.format(Locale.US, "onSuccess(%s, %s)", key, content));
            dataListener.onSuccess(key, content);
        }
    }

    @Override
    public void onFail(String key, Response response, int errorType, String errorMessage) {
        Log.d("LOG_TAG_API_DEBUG", String.format(Locale.US, "%s: errorType: %d, errorMessage: %s", key, errorType, errorMessage));
        if (dataListener != null) {
            dataListener.onFail(key, errorType, errorMessage);
        }
    }

    public RequestManager getRequestManager() {
        if (mRequestManager == null) {
            mRequestManager = injectRequestManager();
        }
        return mRequestManager;
    }

    public URLConfigManager getURLConfigManager() {
        if (mURLConfigManager == null) {
            mURLConfigManager = injectURLConfigManager();
        }
        return mURLConfigManager;
    }

    @Override
    public void finish() {
        if (mRequestManager != null) {
            mRequestManager.finish();
            mRequestManager = null;
        }
        if (mURLConfigManager != null) {
            mURLConfigManager = null;
        }
        if (dataListener != null) {
            dataListener = null;
        }
    }
}
