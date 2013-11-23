package jcurl.main.converter.syntaxtree;

import jcurl.main.converter.visitors.Visitor;

public class URL implements Acceptor {

	private String url;

	public URL(String url) {
		super();
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public void accept(Visitor visitor) {
		// TODO Auto-generated method stub
		visitor.accept(this);
	}

	@Override
	public String toString() {
		return "URL [url=" + url + "]";
	}

}
