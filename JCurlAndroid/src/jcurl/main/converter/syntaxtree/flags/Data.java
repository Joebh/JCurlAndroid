package jcurl.main.converter.syntaxtree.flags;

import jcurl.main.converter.syntaxtree.Flag;
import jcurl.main.converter.visitors.Visitor;

public class Data extends Flag {

	private String data;

	@Override
	public void accept(Visitor visitor) {
		// TODO Auto-generated method stub
		visitor.accept(this);
	}
	
	public Data(String data) {
		super();
		this.data = data;
	}

	@Override
	public String toString() {
		return "Data [data=" + data + "]";
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
