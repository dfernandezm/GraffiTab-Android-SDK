package com.graffitab.graffitabsdk.common;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.apache.commons.io.IOUtils;

/**
 * Created by davidfernandez on 06/12/2016.
 */

public class MockOkHttpClientInterceptor implements Interceptor {

	// Try to use this: https://github.com/square/retrofit/tree/master/retrofit-mock
	// https://touk.pl/blog/2014/02/26/mock-retrofit-using-dagger-and-mockito/
	private final static String TAG = MockOkHttpClientInterceptor.class.getSimpleName();

	public static Boolean LOGIN_SUCCESS = false;
	public static Boolean LOGIN_FAILED = false;


	@Override
	public Response intercept(Chain chain) throws IOException {
		final HttpUrl uri = chain.request().url();
		System.out.println("Mocked URI invoked is " + uri);

		if (LOGIN_SUCCESS) {
			return mockLoginResponse(chain, 200);
		}

		if (LOGIN_FAILED) {
			return mockFailedLoginResponse(chain, 401);
		}

		return mockLoginResponse(chain, 200);
	}

	private Response mockLoginResponse(Chain chain, Integer statusCode) throws IOException {
		String responseString = readJsonFileToString("/loginResponse.json");
		return mockFailedLoginResponse(chain, statusCode, responseString);
	}

	private Response mockFailedLoginResponse(Chain chain, Integer statusCode) throws IOException {
		String responseString = readJsonFileToString("/loginFailed.json");
		return mockFailedLoginResponse(chain, statusCode, responseString);
	}

	private Response mockFailedLoginResponse(Chain chain, Integer statusCode, String responseString) {
		return new Response.Builder()
				.code(statusCode)
				.message(responseString)
				.request(chain.request())
				.protocol(Protocol.HTTP_1_1)
				.body(ResponseBody
						.create(MediaType.parse("application/json"), responseString.getBytes()))
				.addHeader("Content-type", "application/json").build();
	}

	private String readJsonFileToString(String filePath) throws IOException {
		InputStream in = MockOkHttpClientInterceptor.class.getResourceAsStream(filePath);
		return IOUtils.toString(in);
	}
}
