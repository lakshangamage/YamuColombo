package com.phrixus.yamucolombo.yamucolomboversion10.Controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by Lakshan on 2016-05-03.
 */
public class SessionManager {
    // LogCat tag
    private static String CLASS = SessionManager.class.getSimpleName();

    // Shared Preferences
    SharedPreferences pref;

    SharedPreferences.Editor editor;
    Context _context;

    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences("YamuColomboLogin", 0);
        editor = pref.edit();
    }

    public void setLogin(boolean isLoggedIn) {

        editor.putBoolean("isLoggedIn", isLoggedIn);

        // commit changes
        editor.commit();

        Log.d(CLASS, "User login session modified!");
    }

    public boolean isLoggedIn(){
        return pref.getBoolean("isLoggedIn", false);
    }
}
