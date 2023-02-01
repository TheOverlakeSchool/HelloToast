package org.overlake.mat803.hellotoast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String COUNT = "count";
    public static final int REQUEST_CODE = 1;
    private int mCount;
    private TextView mCountView;
    private ActivityResultLauncher<Intent> mLauncher;
    private static final String TAG = "Lifecycle State";

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

        mLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == Activity.RESULT_OK) {
                mCount = result.getData().getIntExtra(COUNT,0);
                updateView();
            }
        });
        Log.d(TAG, "onCreate");
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
        Log.d(TAG, "onSaveInstanceState");
    }

    public void send(View view) {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        intent.putExtra(COUNT, mCount);
        mLauncher.launch(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }
}