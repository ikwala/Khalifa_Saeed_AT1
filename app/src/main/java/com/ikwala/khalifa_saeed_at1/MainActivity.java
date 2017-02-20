package com.ikwala.khalifa_saeed_at1;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    private Button btnAdd;
    private ListView lvNames;
    private ListView lvEmails;
    private EditText etName;
    private EditText etEmail;
    private String name = "";
    private String email = "";
    private List<String> names = new ArrayList<>();
    private List<String> emails = new ArrayList<>();


    public static int pos = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        lvNames = (ListView) findViewById(R.id.lvNames);
        lvEmails = (ListView) findViewById(R.id.lvEmails);
        etEmail = (EditText)findViewById(R.id.etEmail);
        etName = (EditText)findViewById(R.id.etName);

        btnAdd.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (etName.getText().toString().isEmpty())
                {
                    Snackbar.make(v,"Please fill the names box",Snackbar.LENGTH_LONG).show();
                    return;
                }
                else if (etEmail.getText().toString().isEmpty())
                {
                    Snackbar.make(v,"Please fill the Email box",Snackbar.LENGTH_LONG).show();
                    return;
                }
                name = etName.getText().toString();
                email = etEmail.getText().toString();
                names.add(name);
                emails.add(email);
                updateList();
                etName.setText("");
                etEmail.setText("");
                etName.requestFocus();
            }
        });
        lvNames.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
        {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id)
            {

                names.remove(position);
                emails.remove(position);
                removeFromList();
                Snackbar.make(parent,"Removed",Snackbar.LENGTH_SHORT).show();
                return false;
            }
        });
        lvEmails.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
        {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id)
            {

                names.remove(position);
                emails.remove(position);
                removeFromList();
                Snackbar.make(parent,"Removed",Snackbar.LENGTH_SHORT).show();
                return false;
            }
        });

        lvNames.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                pos = position;
                Intent i = new Intent(MainActivity.this, EditorActivity.class);
                i.putExtra("name",names.get(position).toString());
                i.putExtra("email",emails.get(position).toString());
                startActivityForResult(i, 0);
            }
        });


    }

    private void removeFromList()
    {
        ArrayAdapter<String> namesAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,names);
        ArrayAdapter<String> emailsAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,emails);

        lvNames.setAdapter(namesAdapter);
        lvEmails.setAdapter(emailsAdapter);
    }

    private void updateList()
    {






        ArrayAdapter<String> namesAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,names);
        ArrayAdapter<String> emailsAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,emails);

        lvNames.setAdapter(namesAdapter);
        lvEmails.setAdapter(emailsAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (resultCode != RESULT_OK)
        {
            return;
        }
        if (requestCode == 0)
        {
            if (data == null)
            {
                return;
            }
            name = data.getStringExtra("name").toString();
            email = data.getStringExtra("email").toString();
           for (int i = 0; i < names.size(); i++)
           {
               names.set(pos,name);
               emails.set(pos,email);
               updateList();
           }


        }
    }
}
