package demo.song.com.myshiepei;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void but(View v){
        Intent addShortCut = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
        addShortCut.putExtra(Intent.EXTRA_SHORTCUT_NAME, "aaaas");
        // 不允许重复创建
        addShortCut.putExtra("duplicate", false);
        addShortCut.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE,R.mipmap.ic_launcher);
        addShortCut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, addShortCut);
        sendBroadcast(addShortCut);
    }
}
