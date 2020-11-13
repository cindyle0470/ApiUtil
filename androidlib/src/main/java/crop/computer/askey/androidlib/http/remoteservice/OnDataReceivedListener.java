package crop.computer.askey.androidlib.http.remoteservice;

/**
 * Created by weikai on 2019/3/25.
 */

public interface OnDataReceivedListener {
    public void onSuccess(String key, String content);
    public void onFail(String key, int errorType, String errorMessage);
}
