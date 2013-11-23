package jcurl.main;

public class JCurl {

	/**
	 * Create a new JCurlSession for managing cookies of subsequent curl calls
	 * 
	 * @return JCurlSession
	 */
	public static JCurlSession createSession() {
		return new JCurlSession();
	}

	/**
	 * Create a new session with a different starting timeout
	 * 
	 * @param timeout
	 * @return
	 */
	public static JCurlSession createSession(int timeout) {
		JCurlSession session = new JCurlSession();
		session.setTimeout(timeout);
		return session;
	}

	/**
	 * Create a new session with a timeout and a front regex
	 * 
	 * @param timeout
	 *            0 for negative, in milliseconds
	 * @param frontRegex
	 *            the front of templating regex, encapsulates variable name
	 * @param backRegex
	 *            the back of templating regex, encapsulates variable name
	 * @return
	 */
	public static JCurlSession createSession(int timeout, String frontRegex,
			String backRegex) {
		JCurlSession session = new JCurlSession();
		session.setTimeout(timeout);

		session.setFrontParamDetect(frontRegex);
		session.setBackParamDetect(backRegex);
		return session;
	}

}
