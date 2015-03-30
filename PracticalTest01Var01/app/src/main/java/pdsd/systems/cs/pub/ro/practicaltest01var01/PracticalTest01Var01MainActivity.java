package pdsd.systems.cs.pub.ro.practicaltest01var01;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class PracticalTest01Var01MainActivity extends Activity {

    private final static int SECONDARY_ACTIVITY_REQUEST_CODE = 1;

    private CheckboxListener checkboxListener = new CheckboxListener();

    private class CheckboxListener implements CompoundButton.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            EditText text = (EditText) PracticalTest01Var01MainActivity.this.findViewById(R.id.editText);
            if (buttonView.isChecked()) {
                text.setText(String.valueOf(Integer.parseInt(text.getText().toString()) + 1));
            } else {
                text.setText(String.valueOf(Integer.parseInt(text.getText().toString()) - 1));
            }
        }
    }

    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements Button.OnClickListener {
        @Override
        public void onClick(View view) {
            CheckBox phone = (CheckBox) findViewById(R.id.phone);
            CheckBox email = (CheckBox) findViewById(R.id.email);
            CheckBox im = (CheckBox) findViewById(R.id.im);
            switch (view.getId()) {
                case R.id.button:
                    Intent intent = new Intent("ro.pub.cs.systems.pdsd.intent.action.PracticalTest01Var01SecondaryActivity");
                    StringBuilder stringBuilder = new StringBuilder();
                    if (phone.isChecked()) stringBuilder.append("Phone  ");
                    if (email.isChecked()) stringBuilder.append("Email  ");
                    if (im.isChecked()) stringBuilder.append("Instant messaging ");

                    intent.putExtra("textint", stringBuilder.toString());
                    startActivityForResult(intent, SECONDARY_ACTIVITY_REQUEST_CODE);
                    break;
                // ...
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var01_main);

        EditText text = (EditText) findViewById(R.id.editText);
        text.setText(String.valueOf(0));

        CheckBox phone = (CheckBox) findViewById(R.id.phone);
        phone.setOnCheckedChangeListener(checkboxListener);

        CheckBox email = (CheckBox) findViewById(R.id.email);
        email.setOnCheckedChangeListener(checkboxListener);

        CheckBox im = (CheckBox) findViewById(R.id.im);
        im.setOnCheckedChangeListener(checkboxListener);

        Button but = (Button) findViewById(R.id.button);
        but.setOnClickListener(buttonClickListener);

        if (savedInstanceState != null) {
            boolean phoneb = savedInstanceState.getBoolean("phone");
            phone.setChecked(phoneb);
            boolean emailb = savedInstanceState.getBoolean("email");
            email.setChecked(emailb);
            boolean imb = savedInstanceState.getBoolean("im");
            im.setChecked(imb);

            String texts = savedInstanceState.getString("text");
            if ( texts != null){
                text.setText(texts);
            }
            else
            {
                text.setText(String.valueOf(0));
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_practical_test01_var01_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        EditText text = (EditText) findViewById(R.id.editText);
        CheckBox phone = (CheckBox) findViewById(R.id.phone);
        CheckBox email = (CheckBox) findViewById(R.id.email);
        CheckBox im = (CheckBox) findViewById(R.id.im);
        savedInstanceState.putString("text", text.getText().toString());
        savedInstanceState.putBoolean("phone",phone.isChecked());
        savedInstanceState.putBoolean("email",email.isChecked());
        savedInstanceState.putBoolean("im",im.isChecked());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        Toast.makeText(this, "The activity returned with result " + resultCode, Toast.LENGTH_LONG).show();
    }
}

