package com.graffitabsdk.config.okhttp;

import com.graffitabsdk.config.GTSDK;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by davidfernandez on 08/12/2016.
 */
public class LanguageHeaderInterceptor implements Interceptor {

	@Override
	public Response intercept(Interceptor.Chain chain) throws IOException {
		Request request = chain.request()
								.newBuilder()
								.addHeader("Accept-Language", GTSDK.getConfig().getLanguage())
								.build();
		return chain.proceed(request);
	}
}