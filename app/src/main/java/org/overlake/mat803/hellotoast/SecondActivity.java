package org.overlake.mat803.hellotoast;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private int mCount;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCount = getIntent().getIntExtra(MainActivity.COUNT, 0);
        setContentView(R.layout.activity_second);
        mTextView = findViewById(R.id.count_phrase);
        findViewById(R.id.increment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCount++;
                updateView();
            }
        });

        updateView();


    }

    private void updateView() {
        String msg = getResources().getString(R.string.count_string, mCount);
        mTextView.setText(msg);
    }

    public void finish(View view) {
        Intent intent = new Intent();
        intent.putExtra(MainActivity.COUNT, mCount);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}