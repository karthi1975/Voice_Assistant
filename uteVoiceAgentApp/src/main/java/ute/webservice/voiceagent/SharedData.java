package ute.webservice.voiceagent;

/**
 * Session data used in package ute.webservice.voiceagent.
 * Created by u1076070 on 6/29/2017.
 */
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SharedData {

    SharedPreferences sPref;
    Editor editor;
    Context _context;

    int PRIVATE_MODE=0;

    private static final String FILE_NAME="AILOGINFILE";
    private static final String KEY_ACCOUNT="Account";
    private static final String KEY_ACCESS_LEVEL="Administration";

    public SharedData(Context context){
        this._context = context;
        sPref = _context.getSharedPreferences(FILE_NAME,PRIVATE_MODE);
        editor = sPref.edit();
    }

    /**
     * Save Account Info for all activity
     * @param name login account name
     * @param access administration of this account
     */
    public void createLoginSession(String name, int access){

        // Storing name in pref
        editor.putString(KEY_ACCOUNT, name);

        // Storing email in pref
        editor.putInt(KEY_ACCESS_LEVEL, access);

        // commit changes
        editor.commit();
    }

    /**
     * Get stored user account
     * @return user account
     */
    public String getKeyAccount(){

        // return user
        return sPref.getString(KEY_ACCOUNT, null);
    }

    public int getKeyAccess(){
        return sPref.getInt(KEY_ACCESS_LEVEL, -1);
    }

    /**
     * Clear User info
     */
    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Loing Activity
        Intent i = new Intent(_context, MainActivity.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        _context.startActivity(i);
    }
}
