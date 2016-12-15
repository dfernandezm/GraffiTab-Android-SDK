package com.graffitab.graffitabsdk;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;

import com.graffitab.graffitabsdk.common.MockOkHttpClientInterceptor;
import com.graffitabsdk.api.GTUserManager;
import com.graffitabsdk.config.GTConfig;
import com.graffitabsdk.config.GTSDK;
import com.graffitabsdk.config.dagger.modules.AppModule;
import com.graffitabsdk.model.GTUser;
import com.graffitabsdk.network.common.GTResponse;
import com.graffitabsdk.network.common.GTResponseHandler;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.fail;

/**
 * Created by davidfernandez on 06/12/2016.
 */

public class UserServiceTest {

	private static final Long TERMINATION_TIMEOUT_MS = 5000L;

	@Inject
	GTUserManager userManager;

	/** Countdown latch */
	private CountDownLatch lock = new CountDownLatch(1);

	@Before
	public void setUp() {
		//TODO: Use GTSDK, otherwise it won't work

		TestComponent testComponent = DaggerTestComponent.builder()
				.appModule(new AppModule(null))
				.networkModule(new NetworkTestModule(GTConfig.defaultConfig()))
				.build();

		GTSDK.initUserComponent(testComponent, GTConfig.defaultConfig());

		testComponent.inject(this);
	}

	@Test
	public void loginAndGetMe() throws InterruptedException {
		MockOkHttpClientInterceptor.LOGIN_FAILED = false;
		MockOkHttpClientInterceptor.LOGIN_SUCCESS = true;
		GTResponseHandler<GTUser> responseHandler = new GTResponseHandler<GTUser>() {
			@Override
			public void onSuccess(GTResponse<GTUser> responseObject) {
				GTUser user = responseObject.getObject();
				Assert.assertNotNull(user);
				System.out.println(responseObject);
				System.out.println(user.toString());
				userManager.getMe(new GTResponseHandler<GTUser>() {
					@Override
					public void onSuccess(GTResponse<GTUser> responseObject) {
						GTUser user = responseObject.getObject();
						System.out.println(user.toString());
						System.out.println(responseObject);
						lock.countDown();
					}

					@Override
					public void onFailure(GTResponse<GTUser> responseObject) {
						System.out.println("Failure detected");

						fail();
						lock.countDown();
					}
				});
			}

			@Override
			public void onFailure(GTResponse<GTUser> responseObject) {
				System.out.println("Failure detected");
				System.out.println(responseObject);
				fail();
				lock.countDown();
			}
		};

		userManager.login("david", "password1", responseHandler);
		awaitForTermination();
	}

	@Test
	public void cannotUseApiUnauthenticated() {
		MockOkHttpClientInterceptor.LOGIN_FAILED = true;
		MockOkHttpClientInterceptor.LOGIN_SUCCESS = false;
		userManager.getMe(new GTResponseHandler<GTUser>() {
			@Override
			public void onSuccess(GTResponse<GTUser> responseObject) {
				System.out.println(responseObject);
				fail();
				lock.countDown();
			}

			@Override
			public void onFailure(GTResponse<GTUser> responseObject) {
				Assert.assertEquals(responseObject.getResultCode().getStatusCode().intValue(), 401);
				lock.countDown();
			}
		});
		awaitForTermination();
	}

	private void awaitForTermination() {
		try {
			lock.await(TERMINATION_TIMEOUT_MS, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			// Nothing to do
		}
	}
}
