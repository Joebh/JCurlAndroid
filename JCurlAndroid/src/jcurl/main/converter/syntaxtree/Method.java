package jcurl.main.converter.syntaxtree;

import jcurl.main.converter.visitors.Visitor;

public class Method implements Acceptor {

	private String type;
	
	public static final String GET = "GET";
	public static final String POST = "POST";
	public static final String PUT = "PUT";

	public Method(){
		super();
		type = GET;
	}

	public Method(String type) {
		super();
		this.type = type;
	}

	public String getType() {
		return type;
	}

	@Override
	public String toString() {
		return "Method [type=" + type + "]";
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public void accept(Visitor visitor) {
		// TODO Auto-generated method stub
		visitor.accept(this);
	}

}
