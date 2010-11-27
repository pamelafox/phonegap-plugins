/*
 * Copyright (c) 2010, HipSnip Ltd
 */
package com.hipsnip.plugins.facebook;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.content.Context;
import android.app.Activity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.phonegap.api.Plugin;
import com.phonegap.api.PluginResult;

import com.facebook.android.Facebook;
import com.facebook.android.AsyncFacebookRunner;
import com.facebook.android.Facebook.DialogListener;
import com.facebook.android.DialogError;
import com.facebook.android.FacebookError;
import com.facebook.android.Util;

public class FacebookAuth extends Plugin {
	
	public static final String APP_ID = "175729095772478";
	private Facebook mFb;
    public String callback;
	/**
	 * Executes the request and returns PluginResult.
	 * 
	 * @param action 		The action to execute.
	 * @param args 			JSONArry of arguments for the plugin.
	 * @param callbackId	The callback id used when calling back into JavaScript.
	 * @return 				A PluginResult object with a status and message.
	 */
	public PluginResult execute(String action, JSONArray args, String callbackId) {
		callback = callbackId;
		System.out.println("execute: "+ action);
		
		if (action.equals("authorize")) {
				
			this.authorize();
				
		} else if (action.equals("request")){
			
		}
		
		PluginResult r = new PluginResult(PluginResult.Status.NO_RESULT);
		r.setKeepCallback(true);
		return r;
	}
	
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		this.mFb.authorizeCallback(requestCode, resultCode, intent);
        this.onResponse();
	}
	
	public void onResponse(){
		Log.d("DroidGap", "onActivityResult FacebookAuth");
		try {
			String response = this.mFb.request("me");
			JSONObject json = Util.parseJson(response);
            
			this.success(new PluginResult(PluginResult.Status.OK, json), this.callback);
			
		} catch (java.net.MalformedURLException e) {
			this.error(new PluginResult(PluginResult.Status.ERROR), this.callback);
		} catch (java.io.IOException e) {
			this.error(new PluginResult(PluginResult.Status.ERROR), this.callback);
        } catch (JSONException e) {
            Log.w("Facebook-Example", "JSON Error in response");
        } catch (FacebookError e) {
            Log.w("Facebook-Example", "Facebook Error: " + e.getMessage());
        }
	}
	/**
	 * Identifies if action to be executed returns a value and should be run synchronously.
	 * 
	 * @param action	The action to execute
	 * @return			T=returns value
	 */
	public boolean isSynch(String action) {
		return false;
	}
    
    /**
     * Called by AccelBroker when listener is to be shut down.
     * Stop listener.
     */
    public void onDestroy() { 	
    }

    //--------------------------------------------------------------------------
    // LOCAL METHODS
    //--------------------------------------------------------------------------
    
    /**
     * Display a new browser with the specified URL.
     * 
     * @return				"" if ok, or error message.
     */
    public void authorize() {
    	Log.d("PhoneGapLog", "authorize");
		final FacebookAuth fba = this;
		Runnable runnable = new Runnable() {
			public void run() {
				fba.mFb = new Facebook(APP_ID);
				fba.mFb.setPlugin(fba);
		        fba.mFb.authorize((Activity) fba.ctx, new String[] {}, new AuthorizeListener(fba));

			};
		};
		this.ctx.runOnUiThread(runnable);

    }

	class AuthorizeListener implements DialogListener {
		final FacebookAuth fba;
		
		public AuthorizeListener(FacebookAuth fba){
			super();
			this.fba = fba;
		}
		
		public void onComplete(Bundle values) {
			//  Handle a successful login
	   
			Log.d("PhoneGapLog",values.toString());
			this.fba.onResponse();
		}
		
		public void onFacebookError(FacebookError e) {
			e.printStackTrace();
		}

		public void onError(DialogError e) {
			e.printStackTrace();        
		}

		public void onCancel() {        
		}
	}

    
}
