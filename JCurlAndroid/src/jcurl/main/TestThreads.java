package jcurl.main;

import jcurl.main.JCurlSession.KeyValuePair;

public class TestThreads {

	public static void main(String[] args) {
		JCurlSession session = JCurl.createSession();

		MyThread threadA = new MyThread(session, 0);
		MyThread threadB = new MyThread(session, 1);

		threadA.start();
		threadB.start();

		try {
			threadA.join();
			threadB.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

class MyThread extends Thread {

	private final JCurlSession session;
	private int i;

	private String[] urls = {
			"http://hc.apache.org/httpcomponents-client-4.3.x/tutorial/html/advanced.html",
			"http://hc.apache.org/httpcomponents-client-4.3.x/tutorial/html/statemgmt.html" };

	MyThread(JCurlSession session, int i) {
		this.session = session;
		this.i = i;
	}

	@Override
	public void run() {
		try {
			System.out.println(session.callCurl("curl '${url}' --compressed",
					new KeyValuePair("url", urls[i])));
		} catch (ScrapeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}