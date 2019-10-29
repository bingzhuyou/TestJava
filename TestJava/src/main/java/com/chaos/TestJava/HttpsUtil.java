package com.chaos.TestJava;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class used to add the server's certificate to the KeyStore with your trusted
 * certificates.
 */
public class HttpsUtil {

	private static final Logger logger = LoggerFactory.getLogger(HttpsUtil.class);

	public static final String MY_KEY_STORE = "jssecacerts";
	public static final String MY_PASS_PHRASE = "changeit";

	public static void installCertification(String httpsUrl) throws Exception {

		String host = null;
		Integer port = null;

		char[] passphrase = null;
		String url = parseUrl2IpPortPassphrase(httpsUrl);
		if (url != null) {
			System.out.println(url);
			String[] args = url.split(" ");
			if ((args.length == 1) || (args.length == 2)) {
				String[] c = args[0].split(":");
				host = c[0];
				port = (c.length == 1) ? 443 : Integer.parseInt(c[1]);
				String password = (args.length == 1) ? MY_PASS_PHRASE : args[1];
				passphrase = password.toCharArray();
			}
		} else {
			System.out.println("URL null or format error. valid url pattern: [https://]<host>[:port][/path]");
			return;
		}

		String projectBasePath = HttpsUtil.class.getResource("/").getPath();
		String filePath = projectBasePath + MY_KEY_STORE;
		File file = new File(filePath);
		if (file.isFile() == false) {
			char SEP = File.separatorChar;
			File dir = new File(System.getProperty("java.home") + SEP + "lib" + SEP + "security");
			file = new File(dir, MY_KEY_STORE);
			if (file.isFile() == false) {
				file = new File(dir, "cacerts");
				logger.info("Try Default KeyStore File: " + file + "...");
			}
		}
		logger.info("Loading KeyStore " + file + "...");
		InputStream in = new FileInputStream(file);
		KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
		ks.load(in, passphrase);
		in.close();

		SSLContext context = SSLContext.getInstance("TLS");
		TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
		tmf.init(ks);
		X509TrustManager defaultTrustManager = (X509TrustManager) tmf.getTrustManagers()[0];
		SavingTrustManager tm = new SavingTrustManager(defaultTrustManager);
		context.init(null, new TrustManager[] { tm }, null);
		SSLSocketFactory factory = context.getSocketFactory();

		logger.info("Opening connection to " + host + ":" + port + "...");
		SSLSocket socket = (SSLSocket) factory.createSocket(host, port);
		socket.setSoTimeout(10000);
		try {
			logger.info("Starting SSL handshake...");
			socket.startHandshake();
			socket.close();
			logger.info("No errors, certificate is already trusted.");
		} catch (SSLException e) {
			logger.error("Could not obtain certificate from --> " + file);
			logger.error(e.getMessage(), e);
		}

		X509Certificate[] chain = tm.chain;
		if (chain == null) {
			logger.error("Could not obtain server certificate chain");
			return;
		}

		logger.info("Server sent " + chain.length + " certificate(s):");
		MessageDigest sha1 = MessageDigest.getInstance("SHA1");
		MessageDigest md5 = MessageDigest.getInstance("MD5");

		// print certificate content.
		for (int i = 0; i < chain.length; i++) {
			X509Certificate cert = chain[i];
			System.out.println(" " + (i + 1) + " Subject " + cert.getSubjectDN());
			System.out.println("   Issuer  " + cert.getIssuerDN());
			sha1.update(cert.getEncoded());
			System.out.println("   sha1    " + toHexString(sha1.digest()));
			md5.update(cert.getEncoded());
			System.out.println("   md5     " + toHexString(md5.digest()));

		}

		// BufferedReader reader =
		// new BufferedReader(new InputStreamReader(System.in));

		// System.out.println("Enter certificate to add to trusted keystore or
		// 'q' to quit: [1]");
		// String line = reader.readLine().trim();
		// int k;
		// try {
		// k = (line.length() == 0) ? 0 : Integer.parseInt(line) - 1;
		// } catch (NumberFormatException e) {
		// logger.info("KeyStore not changed. " + e.getMessage(),e );
		// return;
		// }

		// store certificate
		for (int k = 0; k < chain.length; k++) {
			X509Certificate cert = chain[k];
			String alias = host + "-" + (k + 1);
			ks.setCertificateEntry(alias, cert);

			OutputStream out = new FileOutputStream(filePath);
			ks.store(out, passphrase);
			out.close();

			System.out.println();
			System.out.println(cert);
			System.out.println();
			System.out.println("Added certificate to keystore 'jssecacerts' using alias '" + alias + "'");

		}

	}

	private static final char[] HEXDIGITS = "0123456789abcdef".toCharArray();

	private static String toHexString(byte[] bytes) {
		StringBuilder sb = new StringBuilder(bytes.length * 3);
		for (int b : bytes) {
			b &= 0xff;
			sb.append(HEXDIGITS[b >> 4]);
			sb.append(HEXDIGITS[b & 15]);
			sb.append(' ');
		}
		return sb.toString();
	}

	private static class SavingTrustManager implements X509TrustManager {

		private final X509TrustManager tm;
		private X509Certificate[] chain;

		SavingTrustManager(X509TrustManager tm) {
			this.tm = tm;
		}

		public X509Certificate[] getAcceptedIssuers() {

			/**
			 * This change has been done due to the following resolution advised
			 * for Java 1.7+
			 * http://infposs.blogspot.kr/2013/06/installcert-and-java-7.html
			 **/

			return new X509Certificate[0];
			// throw new UnsupportedOperationException();
		}

		public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			throw new UnsupportedOperationException();
		}

		public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			this.chain = chain;
			tm.checkServerTrusted(chain, authType);
		}
	}

	private static String parseUrl2IpPortPassphrase(String url) {
		String host = "";
		String port = "";
		String passphraseStr = "";
		if (url != null && !"".equals(url)) {
			String[] arr = url.trim().split(" ");
			if (arr != null) {
				passphraseStr = (arr.length == 1 ? "" : arr[1]);
			}

			if (url.trim().startsWith("https://")) {
				url = url.substring("https://".length(), url.length());
			} else if (url.trim().startsWith("http://")) {
				logger.error("HTTPS URL format ERROR:" + url);
				return null;
			}

			if (url.indexOf(":") != -1) {
				String regex = "//(.*?):(.*\\d)";
				Pattern p = Pattern.compile(regex);
				Matcher m = p.matcher(url);
				while (m.find()) {
					host = m.group(1);
					port = m.group(2);
				}
				if (!"".equals(host) && !"".equals(port)) {
					url = host + ":" + port;
				} else {
					logger.error("URL format ERROR:" + url);
				}
			} else if (url.indexOf("/") != -1) {
				int endIndex = url.indexOf("/");
				url = url.substring(0, endIndex);
			}
			return url + " " + passphraseStr;
		} else {
			return null;
		}
	}

	public static CloseableHttpClient createHttpsClient(String url) throws Exception {

		// 调用安装证书方法
		installCertification(url);

		KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
		File file = new File(HttpsUtil.class.getResource("/").getPath() + MY_KEY_STORE);
		InputStream instream = new FileInputStream(file);
		try {
			trustStore.load(instream, MY_PASS_PHRASE.toCharArray());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (instream != null) {
					instream.close();
				}
			} catch (Exception ignore) {
				logger.error(ignore.getMessage(), ignore);
			}
		}
		// 相信自己的CA和所有自签名的证书
		SSLContext sslcontext = SSLContexts.custom().loadTrustMaterial(trustStore, new TrustSelfSignedStrategy()).build();
		// 只允许使用TLSv1协议
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" }, null, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
		CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();

		return httpclient;
	}

	public static void main(String[] args) {
		try {
			HttpsUtil.installCertification("https://182.18.57.7:6443");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}