package com.yangfan.mvp;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.support.multidex.MultiDex;

import com.yangfan.mvp.enums.AppEnvEnum;
import com.yangfan.mvp.enums.AppEnvHelper;
import com.yangfan.mvp.helper.ContextHelper;
import com.yangfan.mvp.network.subscriber.SafeOnlyNextSubscriber;
import com.yangfan.mvp.mvp.base.BaseActivity;
import com.yangfan.mvp.utils.FrescoUtils;
import com.yangfan.mvp.utils.LogUtils;
import com.yangfan.mvp.utils.SPUtil;

import rx.Subscription;
import rx.observers.SafeSubscriber;
import rx.plugins.RxJavaObservableExecutionHook;
import rx.plugins.RxJavaPlugins;

/**
 * Created by js on 2016/11/29.
 */
public class MyApplication extends Application {

	private Handler mHandler = new Handler();

	@Override
	public void onCreate() {
		super.onCreate();
		MultiDex.install(this);
		ContextHelper.initWithApplication(this);
		SPUtil.initWithApplication(this);
//		Fresco.initialize(this);
		FrescoUtils.initialize(this);
		LogUtils.setAppEnvEnum(AppEnvHelper.currentEnv());
		RxJavaPlugins.getInstance().registerObservableExecutionHook(new RxJavaObservableExecutionHook() {//hook rxjava observer
			@Override
			public Subscription onSubscribeReturn(Subscription subscription) {
				if (subscription instanceof SafeSubscriber) {
					SafeSubscriber safeSubscriber = (SafeSubscriber) subscription;
					Subscription sub = safeSubscriber.getActual();
					BaseActivity act = ContextHelper.getLastActivity();
					if (act != null && (sub instanceof SafeOnlyNextSubscriber)) {//如果是耗时请求的话
						act.addSubscription(sub);
					}
				}
				return super.onSubscribeReturn(subscription);
			}
		});
	}

	@Override
	protected void attachBaseContext(Context base) {
		super.attachBaseContext(base);
		MultiDex.install(this);
	}

	/**
	 * 在主线程执行方法
	 **/
	public void runOnUiThread(Runnable runnable) {
		if (runnable != null) mHandler.post(runnable);
	}

	/**
	 * 延时执行
	 **/
	public void runDelay(Runnable runnable, long delay) {
		if (runnable != null) mHandler.postDelayed(runnable, delay);
	}

//	{
//		PlatformConfig.setWeixin("wxe429888b5b3f1797", "a848751ded09fbc94924cec7b38e454a");
//		PlatformConfig.setQQZone("101378577", "6dcf18afe6acec35382d7f0e9a621ac2");
//	}

	private boolean isDebug(){
		if (AppEnvHelper.currentEnv() == AppEnvEnum.DEBUG) {
			return true;
		}
		return false;
	}
}
