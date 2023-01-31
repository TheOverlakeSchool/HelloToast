package org.overlake.mat803.hellotoast;

import androidx.activity.result.ActivityResult;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String COUNT = "count";
    public static final int REQUEST_CODE = 1;
    private int mCount;
    private TextView mCountView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCount = savedInstanceState != null ? savedInstanceState.getInt(COUNT) : 0;
//        if (savedInstanceState != null) {
//            mCount = savedInstanceState.getInt(COUNT);
//        } else {
//            mCount = 0;
//        }
        setContentView(R.layout.activity_main);
        mCountView = findViewById(R.id.show_count);
        updateView();

        findViewById(R.id.send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra(COUNT, mCount);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE) {
            mCount = data.getIntExtra(COUNT, 0);
            updateView();
        }
    }

    public void showToast(View view) {
        Toast.makeText(this,R.string.toast,Toast.LENGTH_SHORT).show();
    }

    public void increment(View view) {
        mCount++;
        updateView();
    }

    private void updateView() {
        mCountView.setText(Integer.toString(mCount));
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(COUNT, mCount);
    }
}