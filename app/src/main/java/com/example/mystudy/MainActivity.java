package com.example.mystudy;

        import android.content.Intent;
        import android.os.Environment;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.widget.Button;
        import android.widget.TextView;

        import butterknife.BindView;
        import butterknife.ButterKnife;
        import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.button)
    Button button;
    @BindView(R.id.textView)
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            StringBuffer buffer = new StringBuffer();
            buffer.append(Environment.getExternalStorageDirectory());
            textView.setText(buffer);
        }

    }

    @OnClick(R.id.button)
    void goSecondActivity() {
        Intent intent = new Intent(getApplication(), SecondActivity.class);
        startActivity(intent);
    }
}
