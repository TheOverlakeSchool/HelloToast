package org.overlake.mat803.hellotoast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int mCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCount = 0;
    }

    public void showToast(View view) {
        Toast.makeText(this,R.string.toast,Toast.LENGTH_SHORT).show();
    }

    public void increment(View view) {
        mCount++;
        TextView count = findViewById(R.id.show_count);
        count.setText(Integer.toString(mCount));
    }
}