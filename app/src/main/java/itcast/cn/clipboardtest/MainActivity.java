package itcast.cn.clipboardtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView clipText;
    private Button show_button,start_service,stop_service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        show_button = findViewById(R.id.showClipBoard);
        start_service = findViewById(R.id.start_service);
        stop_service = findViewById(R.id.stop_service);
        start_service.setOnClickListener(this);
        stop_service.setOnClickListener(this);

        clipText = findViewById(R.id.clipText);

        final ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        final ClipData clipData = ClipData.newPlainText(null,clipboardManager.getText());
        clipboardManager.setPrimaryClip(clipData);

        show_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clipText.setText(clipboardManager.getText());
            }
        });


        clipboardManager.addPrimaryClipChangedListener(new ClipboardManager.OnPrimaryClipChangedListener() {
            @Override
            public void onPrimaryClipChanged() {
                Toast.makeText(MainActivity.this, "剪贴板已复制到新内容：", Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.start_service:
                Intent start_service = new Intent(this,clipBoardServixe.class);
                startService(start_service);
                break;
            case R.id.stop_service:
                Intent stop_service = new Intent(this,clipBoardServixe.class);
                stopService(stop_service);
                break;
        }
    }
}
