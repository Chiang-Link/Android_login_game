package com.example.login.uti;

import android.content.Context;
import android.widget.Toast;

public class toastuti {
    private static Toast mToast;
    public static void showmsg(Context context,String msg){
        if (mToast == null){
            mToast = Toast.makeText(context,msg,Toast.LENGTH_SHORT);
        }else{
            mToast.setText(msg);
        }
        mToast.show();
    }
}
