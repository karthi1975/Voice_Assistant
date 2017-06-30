package ai.api.sample;

/***********************************************************************************************************************
 *
 * API.AI Android SDK -  API.AI libraries usage example
 * =================================================
 *
 * Copyright (C) 2015 by Speaktoit, Inc. (https://www.speaktoit.com)
 * https://www.api.ai
 *
 ***********************************************************************************************************************
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 ***********************************************************************************************************************/

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import ai.api.model.AIError;

public class MainActivity extends BaseActivity {

    private EditText idEditText;
    private EditText passwordEditText;
    SharedData sessiondata;

    public static final String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sessiondata = new SharedData(getApplicationContext());
        idEditText = (EditText) findViewById(R.id.inputAccount);
        passwordEditText = (EditText) findViewById(R.id.inputPassword);
        TTS.init(getApplicationContext());
    }

    @Override
    protected void onStart() {
        super.onStart();

        checkAudioRecordPermission();
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        final int id = item.getItemId();
        if (id == R.id.action_settings) {
            startActivity(AISettingsActivity.class);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void serviceSampleClick(final View view) {
        startActivity(AIServiceSampleActivity.class);
    }

    public void buttonSampleClick(final View view) {
        final String idString = String.valueOf(idEditText.getText());
        final String pwString = String.valueOf(passwordEditText.getText());

        AccountCheck acnt= new AccountCheck();
        if (TextUtils.isEmpty(idString) || TextUtils.isEmpty(pwString)) {
            LoginAlertDialog alertd= new LoginAlertDialog();
            alertd.showAlertDialog(MainActivity.this,"Login fail","Please enter your username and password.",null);
        }

        if(acnt.isAccountCorrect(idString,pwString)==true){
            sessiondata.createLoginSession(idString,acnt.getAccessLevel());
            startActivity(AIButtonSampleActivity.class);
        }
        else{
            LoginAlertDialog alertd= new LoginAlertDialog();
            alertd.showAlertDialog(MainActivity.this,"Login fail","Account does not exist or password is incorrect.",null);
        }
    }

    public void dialogSampleClick(final View view) {
        startActivity(AIDialogSampleActivity.class);
    }

    public void textSampleClick(final View view) {
        startActivity(AITextSampleActivity.class);
    }

    private void startActivity(Class<?> cls) {
        final Intent intent = new Intent(this, cls);
        startActivity(intent);
    }

    private void clearEditText() {
        idEditText.setText("");
        passwordEditText.setText("");
    }
}
