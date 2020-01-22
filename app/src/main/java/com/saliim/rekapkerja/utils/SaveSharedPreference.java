package com.saliim.rekapkerja.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import static com.saliim.rekapkerja.utils.PreferencesUtility.ID_PREF;
import static com.saliim.rekapkerja.utils.PreferencesUtility.KELAS_PREF;
import static com.saliim.rekapkerja.utils.PreferencesUtility.LEVEL_PREF;
import static com.saliim.rekapkerja.utils.PreferencesUtility.LOGGED_IN_PREF;
import static com.saliim.rekapkerja.utils.PreferencesUtility.NAMA_PREF;
import static com.saliim.rekapkerja.utils.PreferencesUtility.PASSWORD_PREF;
import static com.saliim.rekapkerja.utils.PreferencesUtility.USERNAME_PREF;

public class SaveSharedPreference {

    static SharedPreferences getPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    /**
     * Set the Login Status
     * @param context
     * @param loggedIn
     */
    public static void setLoggedIn(Context context, boolean loggedIn, String kelas, String level, String password, String username, String nama, String id) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putBoolean(LOGGED_IN_PREF, loggedIn);
        editor.putString(KELAS_PREF, kelas);
        editor.putString(LEVEL_PREF, level);
        editor.putString(PASSWORD_PREF, password);
        editor.putString(USERNAME_PREF, username);
        editor.putString(NAMA_PREF, nama);
        editor.putString(ID_PREF, id);
        editor.apply();
    }

    public static void setLoggedOut(Context context) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.remove(LOGGED_IN_PREF);
        editor.remove(KELAS_PREF);
        editor.remove(LEVEL_PREF);
        editor.remove(PASSWORD_PREF);
        editor.remove(USERNAME_PREF);
        editor.remove(NAMA_PREF);
        editor.remove(ID_PREF);
        editor.apply();
    }

    /**
     * Get the Login Status
     * @param context
     * @return boolean: login status
     */
    public static boolean getLoggedStatus(Context context, String key) {
        return getPreferences(context).getBoolean(LOGGED_IN_PREF, false);
    }

    public static String getLoggedStatusString(Context context, String key) {
        switch (key) {
            case KELAS_PREF:
                return getPreferences(context).getString(KELAS_PREF, "");
            case LEVEL_PREF:
                return getPreferences(context).getString(LEVEL_PREF, "");
            case PASSWORD_PREF:
                return getPreferences(context).getString(PASSWORD_PREF, "");
            case USERNAME_PREF:
                return getPreferences(context).getString(USERNAME_PREF, "");
            case NAMA_PREF:
                return getPreferences(context).getString(NAMA_PREF, "");
            case ID_PREF:
                return getPreferences(context).getString(ID_PREF, "");
            default:
                return getPreferences(context).getString(ID_PREF, "");
        }
    }
}
