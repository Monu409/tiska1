package com.app.tiskakarate;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;

import com.sromku.simple.fb.Permission;
import com.sromku.simple.fb.SimpleFacebook;
import com.sromku.simple.fb.SimpleFacebookConfiguration;
import com.sromku.simple.fb.utils.Logger;

import static com.facebook.login.DefaultAudience.FRIENDS;

public class SimpleApplication extends Application {
	private static final String APP_ID = "1447259095571959";
	private static final String APP_NAMESPACE = "tiska_karate";

	@Override
	public void onCreate() {
		super.onCreate();

		// set log to true
		Logger.DEBUG_WITH_STACKTRACE = true;

		// initialize facebook configuration
		Permission[] permissions = new Permission[] {Permission.PUBLIC_PROFILE,Permission.PUBLISH_ACTION};

		SimpleFacebookConfiguration configuration = new SimpleFacebookConfiguration.Builder()
			.setAppId(APP_ID)
			.setNamespace(APP_NAMESPACE)
			.setPermissions(permissions)
			.setDefaultAudience(FRIENDS)
			.setAskForAllPermissionsAtOnce(false)
			.build();
		
		try {
	        PackageInfo info = getPackageManager().getPackageInfo("com.example.tiskakarate", PackageManager.GET_SIGNATURES);
	        for (Signature signature : info.signatures) {
	            MessageDigest md = MessageDigest.getInstance("SHA");
	            md.update(signature.toByteArray());
	            String hashCode  = Base64.encodeToString(md.digest(), Base64.DEFAULT);
	            System.out.println("Print the hashKey for Facebook :"+hashCode);
	            Log.e("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
	        }
	    } catch (NameNotFoundException e) {

	    } catch (NoSuchAlgorithmException e) {

	    }

		SimpleFacebook.setConfiguration(configuration);
	}
}
