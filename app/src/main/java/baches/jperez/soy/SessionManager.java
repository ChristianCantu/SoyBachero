package baches.jperez.soy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.widget.Toast;

import java.util.HashMap;

public class SessionManager {
	// Shared Preferences
	SharedPreferences pref;
	
	// Editor for Shared preferences
	Editor editor;
	
	// Context
	Context _context;
	


	



	
	/**
	 * Clear session details
	 * */
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
		_context.startActivity(i );
	}
	

}
