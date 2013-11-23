package jcurl.main.converter;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class CurlObject {

	private String url;
	private Map<String, String> headers;
	private boolean compressed = false;
	private boolean followRedirects = false;
	public boolean isFollowRedirects() {
		return followRedirects;
	}

	public void setFollowRedirects(boolean followRedirects) {
		this.followRedirects = followRedirects;
	}

	private String httpMethod;
	private String data;

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("\n  <CURL REQUEST OBJECT>\n  URL : ").append(url).append("\n");
		stringBuilder.append("  ***--headers--***\n");
		for (String key : headers.keySet()) {
			stringBuilder.append("   ***   ").append(key).append(" : ")
					.append(headers.get(key)).append("\n");
		}
		stringBuilder.append("  ***--end headers--***\n");
		stringBuilder.append("  Compressed : ").append(compressed).append("\n");
		stringBuilder.append("  Method : ").append(httpMethod).append("\n");
		stringBuilder.append("  Data : ").append(data).append("\n");
		stringBuilder.append("  Follow Redirects : ").append(followRedirects).append("\n");

		stringBuilder.append(" </CURL REQUEST OBJECT>\n");
		return stringBuilder.toString();
	}

	public boolean isCompressed() {
		return compressed;
	}

	public void setCompressed(boolean compressed) {
		this.compressed = compressed;
	}

	public CurlObject() {
		headers = new HashMap<String, String>();
	}

	public String getHttpMethod() {
		return httpMethod;
	}

	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Map<String, String> getHeaders() {
		return headers;
	}

	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
