package jcurl.main.converter.syntaxtree;

import java.util.ArrayList;
import java.util.List;

import jcurl.main.converter.syntaxtree.flags.Compressed;
import jcurl.main.converter.syntaxtree.flags.H;
import jcurl.main.converter.visitors.CurlObjectBuilderVisitor;
import jcurl.main.converter.visitors.Visitor;

public class Curl implements Acceptor {

	private List<Flag> flags;
	private URL url;
	private Method method;

	public Curl() {
		super();
		flags = new ArrayList<Flag>();
	}

	@Override
	public String toString() {
		return "Curl [flags=" + flags + ", url=" + url + ", method=" + method
				+ "]";
	}

	public void addFlag(Flag value) {
		// TODO Auto-generated method stub
		flags.add(value);
	}

	public void accept(Visitor visitor) {
		visitor.accept(this);
	}

	public List<Flag> getFlags() {
		return flags;
	}

	public void setFlags(List<Flag> flags) {
		this.flags = flags;
	}

	public URL getUrl() {
		return url;
	}

	public void setUrl(URL url) {
		this.url = url;
	}

	public Method getMethod() {
		return method;
	}

	public void setMethod(Method method) {
		this.method = method;
	}

}
