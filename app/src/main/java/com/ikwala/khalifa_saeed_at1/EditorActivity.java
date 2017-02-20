package com.ikwala.khalifa_saeed_at1;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditorActivity extends AppCompatActivity
{
    private EditText etNewName;
    private EditText etNewWmail;
    private Button btnUpdate;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
        etNewName = (EditText) findViewById(R.id.etNewName);
        etNewWmail = (EditText)findViewById(R.id.etNewEmail);
        btnUpdate = (Button)findViewById(R.id.btnUpdate);


        Intent i = getIntent();

        final String newName = i.getStringExtra("name").toString();
        final String newEmail = i.getStringExtra("email").toString();


        etNewName.setText(""+newName);
        etNewWmail.setText(""+newEmail);

        btnUpdate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                     if (etNewName.getText().toString().isEmpty())
                 {
                   Snackbar.make(v,"please inseret the new name",Snackbar.LENGTH_LONG).show();
                    return;
                 }
              else if (etNewWmail.getText().toString().isEmpty())
                {
                    Snackbar.make(v,"please inseret the new Email",Snackbar.LENGTH_LONG).show();
                    return;
                }

                Intent data = new Intent(EditorActivity.this,MainActivity.class);
                data.putExtra("name", etNewName.getText().toString());
                data.putExtra("email", etNewWmail.getText().toString());
                setResult(RESULT_OK,data);
                onBackPressed();


            }
        });


    }
}
