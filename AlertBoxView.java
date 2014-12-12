

import java.util.ArrayList;


import controllers.ContactsController;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class AlertBoxView extends Activity {
	static final int EXIT_DELAY= 3500;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    requestWindowFeature(Window.FEATURE_NO_TITLE);
	    setContentView(R.layout.alert_box);
	    TextView details = (TextView) findViewById(R.id.tvDetailsText);
	    this.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
	    String strNotificationToShow = "Oops.. Something went wrong.";
	    int myParentWidth = getWindow().getAttributes().width;
	    try {
        	Bundle extras = getIntent().getExtras();
    		if (extras != null) {	
    			strNotificationToShow = extras.get(Common.Consts.ALERT_BOX_TEXT).toString();
    			myParentWidth = extras.getInt(Common.Consts.PARENT_WITH);
    		}
    		else
    		{
    			strNotificationToShow = "Oops.. Something went wrong.";
    			myParentWidth = getWindow().getAttributes().width;
    		}
        } catch (Exception e) {
            e.printStackTrace();
            strNotificationToShow = "Oops.. Something went wrong.";
            myParentWidth = getWindow().getAttributes().width;
        }
        finally
        {
        	details.setText(strNotificationToShow);
        }
	    
	    WindowManager.LayoutParams params = getWindow().getAttributes();  
	    params.width = myParentWidth;
	    params.gravity = Gravity.BOTTOM;

	    this.getWindow().setAttributes(params);

	    Window window = this.getWindow();
	    window.setAttributes((android.view.WindowManager.LayoutParams) params);
	    window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
	    window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL, WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);

	    new Handler().postDelayed(Exit(), EXIT_DELAY);
	}

	private Runnable Exit(){
	    return new  Runnable(){     

	        @Override
	        public void run() {
	            finish();
	        }
	    };

	}
}
