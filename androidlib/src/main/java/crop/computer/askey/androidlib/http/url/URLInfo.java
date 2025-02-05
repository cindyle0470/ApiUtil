package crop.computer.askey.androidlib.http.url;

public class URLInfo {
	
	private String key;
    private long expires; // cache使用
    private String method;
    private String scheme;
    private String host;
    private String port;
    private String path;

    
    public URLInfo(String key, long expires, String method, String scheme, String host, String port, String path) {
		this.key = key;
		this.expires = expires;
		this.method = method;
		this.scheme = scheme;
		this.host = host;
		this.port = port;
		this.path = path;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public long getExpires() {
		return expires;
	}

	public void setExpires(long expires) {
		this.expires = expires;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	public String getScheme() {
		return scheme;
	}

	public void setScheme(String scheme) {
		this.scheme = scheme;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("key: ").append(key).append("\n");
		builder.append("method: ").append(method).append("\n");
		builder.append("expires: ").append(expires).append("\n");
		builder.append("scheme: ").append(scheme).append("\n");
		builder.append("host: ").append(host).append("\n");
		builder.append("port: ").append(port).append("\n");
		builder.append("path: ").append(path).append("\n");
		return builder.toString();
	}
}
