package edu.fsu.cs.mobile.example.contextmenu;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class ContextMenuExample extends ListActivity {
    
	String[] entries = new String[]{"Martin","Anderson","Junior","George","Dan"};
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        this.setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, entries));
        
        registerForContextMenu(getListView());
    }
    
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
    	super.onCreateContextMenu(menu, v, menuInfo);
    	
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.context_menu, menu);
    }
    
    @Override
    public boolean onContextItemSelected(MenuItem item) {
    	AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
    	
    	TextView tv = (TextView) getListView().getAdapter().getView(info.position, null, null);
    	
    	String message = "You chose to [ACTION] " + tv.getText() + " (position " + info.position + ")";
    	
    	switch(item.getItemId()) {
    	
    	case R.id.edit_option:
    		message = message.replace("[ACTION]", "EDIT");
    		break;
    		
    	case R.id.share_option:
    		message = message.replace("[ACTION]", "SHARE");
    		break;
    		
    	case R.id.delete_option:
    		message = message.replace("[ACTION]", "DELETE");
    		break;
    	
    	}
    	
    	Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    	
    	return true;
    }
}