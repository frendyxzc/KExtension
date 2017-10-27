package vip.frendy.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import vip.frendy.extension.base.BaseActivity;

/**
 * Created by frendy on 2017/10/27.
 */

public class JavaActivity extends BaseActivity {
    private String nullString = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java);

        findViewById(R.id.crash).setOnClickListener(this);
    }

    @Override
    public void onViewClick(View v) {
        if(v.getId() == R.id.crash) {
            nullString.toString();
        }
    }
}
