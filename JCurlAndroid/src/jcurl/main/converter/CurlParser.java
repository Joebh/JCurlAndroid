package jcurl.main.converter;

import java.util.logging.Logger;

import jcurl.main.converter.syntaxtree.Curl;
import jcurl.main.converter.syntaxtree.Method;
import jcurl.main.converter.syntaxtree.URL;
import jcurl.main.converter.syntaxtree.flags.Compressed;
import jcurl.main.converter.syntaxtree.flags.Data;
import jcurl.main.converter.syntaxtree.flags.H;
import jcurl.main.converter.syntaxtree.flags.L;
import jcurl.main.converter.tokens.CurlToken;
import jcurl.main.converter.tokens.EOFToken;
import jcurl.main.converter.tokens.ErrorToken;
import jcurl.main.converter.tokens.FlagToken;
import jcurl.main.converter.tokens.StringToken;
import jcurl.main.converter.tokens.Token;

public class CurlParser {

	private static Logger log = Logger.getLogger(CurlParser.class.getName());
	private CurlLexer lexer;

	CurlParser(CurlLexer lex) {
		lexer = lex;
	}

	public Curl parse() {
		// make sure first token is CurlToken
		if (lexer.nextToken().getClass() == CurlToken.class) {
			Curl curl = new Curl();

			//create basic method
			curl.setMethod(new Method());
			
			Token token;
			
			do {
				token = lexer.nextToken();
				// if error
				if (token.getClass() == ErrorToken.class) {
					log.severe("Error Parsing Curl String");
				}
				else if (token.getClass() == FlagToken.class) {
					parseFlag((FlagToken) token, curl);
				}
				else if (token.getClass() == StringToken.class) {
					curl.setUrl(new URL(((StringToken) token).getValue()));
				}
			} while (token.getClass() != EOFToken.class);

			return curl;
		}
		return null;
	}

	private void parseFlag(FlagToken flagToken, Curl curl) {
		String type = flagToken.getType();
		if ("-H".equals(type)) {
			Token token = lexer.nextToken();
			if (token.getClass() == ErrorToken.class) {
				log.severe("Error Parsing Flag String");
			}
			if (token.getClass() == StringToken.class) {
				H h = new H(((StringToken) token).getValue());
				log.fine("Adding H Flag " + h);
				curl.addFlag(h);
			}
		}
		if ("--compressed".equals(type)) {
			log.fine("Adding Compressed Flag");
			curl.addFlag(new Compressed());
		}
		
		if( "-L".equals(type)){
			log.fine("Adding Redirect Flag");
			curl.addFlag(new L());
		}		
		
		if("--data".equals(type)){
			curl.setMethod(new Method(Method.POST));
			Token token = lexer.nextToken();
			if (token.getClass() == ErrorToken.class) {
				log.severe("Error Parsing Data String");
			}
			if (token.getClass() == StringToken.class) {
				Data data = new Data(((StringToken) token).getValue());
				log.fine("Adding Data Flag " + data);
				curl.addFlag(data);
			}
		}
	}

}
