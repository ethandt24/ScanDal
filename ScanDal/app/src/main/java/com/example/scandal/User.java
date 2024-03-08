package com.example.scandal;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.provider.Settings;
import android.widget.Toast;
import androidx.annotation.Nullable;

/**
 *  Represents a single instance of a User. Handles the use being an Admin, Organizer and Attendee.
 */
public class User extends Service {
    /**
     * Stores a unique id pulled from the user's device
     */
    private String id; // Stores Emulator id

    /**
     * Gets the User id
     * @return The id of the User's device
     */
    // Getters and Setters
    public String getId() {
        return id;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Remove Toast when Implementation Complete
        Toast toast = Toast.makeText(this, "Starting Service", Toast.LENGTH_SHORT);
        toast.show();

        // Drhuvil Please store this on firebase
        id = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        stopSelf();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Remove Toast when Implementation Complete
        Toast toast = Toast.makeText(this, "Ending Service", Toast.LENGTH_SHORT);
        toast.show();
    }
}
