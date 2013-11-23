package jcurl.main.converter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jcurl.main.converter.syntaxtree.Curl;
import jcurl.main.converter.tokens.EOFToken;
import jcurl.main.converter.tokens.Token;
import jcurl.main.converter.visitors.CurlObjectBuilderVisitor;

/**
 * 
 * @author joebh
 * 
 */
public class CurlConverter {

	private static final Logger log = Logger.getLogger(CurlConverter.class
			.getName());

	public static CurlObject convertCurl(String curlString) {
		CurlLexer lex = new CurlLexer(curlString);
		CurlParser parser = new CurlParser(lex);

		// generate root of syntax tree
		Curl curl = parser.parse();

		log.info(curl.toString());

		CurlObjectBuilderVisitor visitor = new CurlObjectBuilderVisitor();
		curl.accept(visitor);
		CurlObject curlObject = visitor.getCurlObject();

		log.info(curlObject.toString());
		
		return curlObject;
	}

}
