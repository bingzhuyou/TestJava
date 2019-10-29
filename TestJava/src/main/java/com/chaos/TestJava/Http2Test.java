package com.chaos.TestJava;

import java.util.Arrays;

import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;

public class Http2Test {
	public static void main(String[] args) throws Exception {
		testH2C();
	}

	public static void testH2C() throws Exception {
		// OkHttpClient okHttpClient = new
		// OkHttpClient.Builder().protocols(Arrays.asList(
		// Protocol.HTTP_1_1, Protocol.HTTP_2)).build();
		OkHttpClient okHttpClient = new OkHttpClient.Builder().protocols(Arrays.asList(
				Protocol.H2_PRIOR_KNOWLEDGE)).build();
		// H2_PRIOR_KNOWLEDGE

		// Request request = new
		// Request.Builder().url("http://10.91.0.114:10080/ne").build();
		Request request = new Request.Builder().url("http://127.0.0.1:10080/index").build();
		// Request request = new
		// Request.Builder().url("http://10.31.2.6:8089/ne").build();
		Response response = okHttpClient.newCall(request).execute();
		System.out.println(response.protocol());
		System.out.println(response.body().string());
	}
}
