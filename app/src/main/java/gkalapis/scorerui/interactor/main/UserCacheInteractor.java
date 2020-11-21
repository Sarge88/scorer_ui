package gkalapis.scorerui.interactor.main;

import android.content.Context;
import android.content.SharedPreferences;

public class UserCacheInteractor {

    private static String nameID = "UserName_ID";

    public void setUser(Context context, String name){
        SharedPreferences sharedPref = context.getSharedPreferences("user_db", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(nameID, name);
        editor.apply();
    }

    public String getUser(Context context){
        SharedPreferences sharedPref = context.getSharedPreferences("user_db", Context.MODE_PRIVATE);
        return sharedPref.getString(nameID, null);
    }


}
