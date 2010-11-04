/*
 * PhoneGap is available under *either* the terms of the modified BSD license *or* the
 * MIT License (2008). See http://opensource.org/licenses/alphabetical for full text.
 * 
 * Copyright (c) 2005-2010, Nitobi Software Inc.
 * Copyright (c) 2010, IBM Corporation
 */
package com.phonegap.plugins.facebook;

import org.json.JSONArray;
import org.json.JSONException;

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

public class FacebookAuth extends Plugin {
	
	public static final String APP_ID = "175729095772478";
	private Facebook mFb;
    
	/**
	 * Executes the request and returns PluginResult.
	 * 
	 * @param action 		The action to execute.
	 * @param args 			JSONArry of arguments for the plugin.
	 * @param callbackId	The callback id used when calling back into JavaScript.
	 * @return 				A PluginResult object with a status and message.
	 */
	public PluginResult execute(String action, JSONArray args, String callbackId) {
		System.out.println("execute: "+ action);
		
		if (action.equals("authorize")) {
				
			this.authorize();
				
		}
		return new PluginResult(PluginResult.Status.NO_RESULT, "");
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
//				We're forcing dialog auth because DroidGap doesn't like FB dispatching an intent.
//				TODO: Make sso work instead.
		        fba.mFb.authorize((Activity) fba.ctx, new String[] {}, Facebook.FORCE_DIALOG_AUTH, new AuthorizeListener());

			};
		};
		this.ctx.runOnUiThread(runnable);

    }

	class AuthorizeListener implements DialogListener {
	  public void onComplete(Bundle values) {
	   //  Handle a successful login
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
