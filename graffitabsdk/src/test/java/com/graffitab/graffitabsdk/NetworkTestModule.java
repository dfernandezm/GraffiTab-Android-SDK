package com.graffitab.graffitabsdk;

import android.app.Application;
import android.support.annotation.Nullable;
import com.graffitab.graffitabsdk.common.MockOkHttpClientInterceptor;
import com.graffitabsdk.config.GTConfig;
import com.graffitabsdk.config.dagger.modules.NetworkModule;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by davidfernandez on 06/12/2016.
 */
public class NetworkTestModule extends NetworkModule {

	public NetworkTestModule(GTConfig config) {
		super(config);
	}

	@Override
	public CookieJar provideCookieJar(@Nullable Application application) {
		// init cookie manager to in-memory one
		return new CookieJar() {

			private List<Cookie> cookies;

			@Override
			public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
				this.cookies =  cookies;
			}

			@Override
			public List<Cookie> loadForRequest(HttpUrl url) {
				if (cookies != null)
					return cookies;
				return new ArrayList<>();

			}
		};
	}

	@Override
	public OkHttpClient provideOkHttpClient(CookieJar cookieJar) {
		HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
		httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
		return new OkHttpClient.Builder()
				.cookieJar(cookieJar).addInterceptor(httpLoggingInterceptor)
				.addInterceptor(new MockOkHttpClientInterceptor())
				.build();
	}

}