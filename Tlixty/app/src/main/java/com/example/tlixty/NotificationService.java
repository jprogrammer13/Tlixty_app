package com.example.tlixty;

import android.app.Notification;
import android.content.Context ;
import android.content.Intent;
import android.service.notification.NotificationListenerService ;
import android.service.notification.StatusBarNotification ;
import android.util.Log ;

import org.json.JSONException;
import org.json.JSONObject;

import emoji4j.Emoji;
import emoji4j.EmojiUtils;

import static android.net.Uri.encode;

public class NotificationService extends NotificationListenerService {
    private String TAG = this .getClass().getSimpleName() ;
    Context context ;
    static MyListener myListener ;
    RequestTlixty requestTlixty = new RequestTlixty("http://192.168.178.132:1518");

    @Override
    public void onCreate () {
        super .onCreate() ;
        context = getApplicationContext() ;
    }


    @Override
    public void onNotificationPosted (StatusBarNotification sbn) {
        //Log. i ( TAG , "********** onNotificationPosted" ) ;
        //Log. i ( TAG , "ID :" + sbn.getId() + " \t " + sbn.getNotification().extras.getCharSequence(Notification.EXTRA_TITLE) + "\t " + sbn.getNotification().extras.getCharSequence(Notification.EXTRA_TEXT) + "\t " + sbn.getPackageName()) ;
        //requestTlixty.requestData("?title="+sbn.getNotification().tickerText);
        String title = sbn.getNotification().extras.getCharSequence(Notification.EXTRA_TITLE).toString();
        title = EmojiUtils.shortCodify(title);
        String text = sbn.getNotification().extras.getCharSequence(Notification.EXTRA_TEXT).toString();
        text = EmojiUtils.shortCodify(text);


        if(!sbn.getPackageName().toUpperCase().contains((sbn.getNotification().extras.getCharSequence(Notification.EXTRA_TITLE)).toString().toUpperCase())) {
            try {
                JSONObject notification = new JSONObject();
                notification.put("id", sbn.getPackageName()); // id for package name
                notification.put("title",title) ;
                notification.put("text", text);
                Log.d("New Notification", notification.toString());
                requestTlixty.requestData("notification?notification=" + encode(notification.toString()));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void onNotificationRemoved (StatusBarNotification sbn) {
        //Log. i ( TAG , "********** onNotificationRemoved" ) ;
        //Log. i ( TAG , "ID :" + sbn.getId() + " \t " + sbn.getNotification(). tickerText + " \t " + sbn.getPackageName()) ;
        //myListener .setValue( "Remove: " + sbn.getPackageName()) ;
    }
    public void setListener (MyListener myListener) {
        NotificationService. myListener = myListener ;
    }


}