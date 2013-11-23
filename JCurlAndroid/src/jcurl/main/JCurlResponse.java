package jcurl.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;

import org.apache.http.Header;
import org.apache.http.HttpResponse;

import jcurl.main.converter.CurlObject;

public class JCurlResponse {

	private String content;
	private Header[] headers;
	private String responseMessage;
	private int responseCode;

	public JCurlResponse(HttpResponse response, CurlObject curlObject)
			throws IOException {
		headers = response.getAllHeaders();
		responseMessage = response.getStatusLine().getReasonPhrase();
		responseCode = response.getStatusLine().getStatusCode();

		// convert input stream to string
		BufferedReader rd = null;
		Header contentEncoding = response.getFirstHeader("Content-Encoding");
		if (curlObject.isCompressed() && contentEncoding != null && contentEncoding.getValue().contains("gzip")) {
			rd = new BufferedReader(new InputStreamReader(new GZIPInputStream(
					response.getEntity().getContent())));
		} else {
			rd = new BufferedReader(new InputStreamReader(response.getEntity()
					.getContent()));
		}

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		content = result.toString();

	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("<CURL RESPONSE>\n");
		stringBuilder.append("content : ").append(content).append("\n");
		stringBuilder.append("responseCode : ").append(responseCode)
				.append("\n");
		stringBuilder.append("responseMessage : ").append(responseMessage)
				.append("\n");

		stringBuilder.append(" ---headers---\n");
		for (Header header : headers) {
			stringBuilder.append("   ** ").append(header).append("\n");
		}
		stringBuilder.append(" ---end headers---\n");

		stringBuilder.append("</CURL RESPONSE>\n");
		return stringBuilder.toString();
	}

	public String getContent() {
		return content;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public String getResponseMessage() {
		return responseMessage;
	}
}
