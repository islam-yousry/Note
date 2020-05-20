package com.example.note;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class AddEditNoteActivity extends AppCompatActivity {
    public static final String EXTRA_ID =
            "com.example.note.EXTRA_ID";
    public static final String EXTRA_TITLE =
            "com.example.note.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION =
            "com.example.note.EXTRA_DISCRIPTION";
    public static final String EXTRA_PRIORITY =
            "com.example.note.EXTRA_PRIORITY";

    private EditText titleEditText;
    private EditText desriptionEditText;
    private NumberPicker priorityNumberPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        titleEditText = findViewById(R.id.edit_text_title);
        desriptionEditText = findViewById(R.id.edit_text_description);
        priorityNumberPicker = findViewById(R.id.number_picker_priority);

        priorityNumberPicker.setMinValue(1);
        priorityNumberPicker.setMaxValue(10);

        // set the close sign in the menu.
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        // get data of note which will be edited.
        Intent intent = getIntent();
        if(intent.hasExtra(EXTRA_ID)){
            setTitle("Edit Note");
            titleEditText.setText(intent.getStringExtra(EXTRA_TITLE));
            desriptionEditText.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            priorityNumberPicker.setValue(intent.getIntExtra(EXTRA_PRIORITY,1));
        }
        else {
            setTitle("Add Note");
        }


    }

    private void saveNote(){
        String title = titleEditText.getText().toString();
        String description = desriptionEditText.getText().toString();
        int priority = priorityNumberPicker.getValue();

        if(title.trim().isEmpty() || description.trim().isEmpty()) {
            Toast.makeText(this, "Please insert title and description", Toast.LENGTH_SHORT).show();
            return;
        }

        // set the context of the note to return to mainActivity.
        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE,title);
        data.putExtra(EXTRA_DESCRIPTION,description);
        data.putExtra(EXTRA_PRIORITY,priority);

        int id = getIntent().getIntExtra(EXTRA_ID,-1);
        if(id != -1)
            data.putExtra(EXTRA_ID,id);

        setResult(RESULT_OK,data);
        finish();
    }

    // set our menu bar to the activity.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_note_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_note:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
