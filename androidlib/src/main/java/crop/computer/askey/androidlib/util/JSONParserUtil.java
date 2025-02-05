package crop.computer.askey.androidlib.util;

import android.support.annotation.Keep;

import com.google.gson.Gson;

@Keep
public class JSONParserUtil {
	private static final Gson gson = new Gson();
	
	public final static String toJson(Object o) {
		return gson.toJson(o);
	}
	
	public final static <T> T fromJson(String json, Class<T> classOfT){
		return gson.fromJson(json, classOfT);
	}
}
