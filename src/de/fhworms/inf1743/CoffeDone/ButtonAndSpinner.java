package de.fhworms.inf1743.CoffeDone;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * @author Frank Denzer
 */
public class ButtonAndSpinner extends Activity {

    private MediaPlayer mp;
    private ArrayAdapter adapter;

    // debugging
    private static final String TAG = ButtonAndSpinner.class.getName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Spinner sp = (Spinner) findViewById(R.id.voiceSelector);
        adapter = ArrayAdapter.createFromResource(
                this, R.array.voiceType, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);

        sp.setOnItemSelectedListener(new OnVoiceTypeSelected());
    }

    @Override
    public void onStart() {
        // media source: These sound files are music and sound effects that are
        // used in the annual presentation of The Wonders of Physics. You may
        // download them for your personal use. You may distribute them for non-
        // commercial purposes, provided their source is acknowledged. Permission
        // is required if you wish to use them for commercial purposes.
        // @see: http://sprott.physics.wisc.edu/wop/sounds/
        mp = MediaPlayer.create(this, R.raw.gong);
    }

    @Override
    public void onStop() {
        mp.release();
    }

    public void startMediaPlayback(View v)
        mp.start();
    }

    private class OnVoiceTypeSelected implements OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parentView, View selectedItemView,
                                   int position, long id) {
            Toast.makeText(parentView.getContext(),
                    parentView.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
            if (position == 0) {
                // source: own voice
                mp = MediaPlayer.create(parentView.getContext(), R.raw.male_coffee_done);
            } else {
                mp = MediaPlayer.create(parentView.getContext(), R.raw.gong);
            }

        }

        public void onNothingSelected(AdapterView<?> parentView) {
            //todo: stub
        }
    }
}
