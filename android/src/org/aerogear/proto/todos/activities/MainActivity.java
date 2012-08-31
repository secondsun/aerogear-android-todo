package org.aerogear.proto.todos.activities;

import android.os.Bundle;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import org.aerogear.android.AeroGear;
import org.aerogear.proto.todos.Constants;
import org.aerogear.proto.todos.R;

/**
 * @author <a href="mailto:marko.strukelj@gmail.com">Marko Strukelj</a>
 */
public class MainActivity extends SherlockFragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        AeroGear.initialize(Constants.API_KEY, Constants.ROOT_URL);
    }
}
