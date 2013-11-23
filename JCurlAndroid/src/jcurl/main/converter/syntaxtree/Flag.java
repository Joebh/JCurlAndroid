package jcurl.main.converter.syntaxtree;

import jcurl.main.converter.visitors.Visitor;

public abstract class Flag implements Acceptor {

	protected Flag() {

	}

	public abstract void accept(Visitor visitor);

	public abstract String toString();

}
