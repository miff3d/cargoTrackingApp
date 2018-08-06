package com.fenerlojistik.fenerlojistik;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResultActivity extends AppCompatActivity {
    public static final String RESULT_ACTIVITY_TAG = "ResultActivityTag";
    public static final String TAG = ResultActivity.class.getSimpleName();

    @BindView(R.id.senderTextView)
    TextView senderTextView;
    @BindView(R.id.sentToTextView)
    TextView sentToTextView;
    @BindView(R.id.statueTextView)
    TextView statueTextView;
    @BindView(R.id.codeTextView)
    TextView codeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resault);
        ButterKnife.bind(this);
        Product currentProduct = Parcels.unwrap(getIntent().getParcelableExtra(RESULT_ACTIVITY_TAG));
        if (currentProduct != null) {
            senderTextView.setText(currentProduct.getSender());
            sentToTextView.setText(currentProduct.getSentTo());
            statueTextView.setText(currentProduct.getStatue());
            codeTextView.setText(currentProduct.getCode());
        } else {
            Log.e(TAG,"unable to show result! because of a null product. ");
        }
    }
}
