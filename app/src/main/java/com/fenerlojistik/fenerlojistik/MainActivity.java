package com.fenerlojistik.fenerlojistik;

import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.productNoEditText)
    EditText productNoEditText;

    @BindView(R.id.searchButton)
    ImageView searchButton;


    @OnClick(R.id.searchButton)
    public void openResult() {
        String productNo = productNoEditText.getText().toString().trim();
        Intent intent = new Intent(MainActivity.this, ResultActivity.class);
        intent.putExtra(ResultActivity.RESULT_ACTIVITY_TAG, productNo);
        startActivity(intent);
    }

    @OnClick(R.id.addressTextView)
    public void openAdress() {
        Uri uri = Uri.parse("geo:0,0?q=" + "41.0053434" + "," + "28.8873730" + "(Fener Lojistik");
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(uri);
        startActivity(intent);
    }

    @OnClick(R.id.emailTextView)
    public void openEmail() {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"fenerlojistikltd@gmail.com"});
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    @OnClick(R.id.websiteTextView)
    public void openWebsite() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://www.fenerlojistik.com"));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    @OnClick(R.id.phoneTextView)
    public void openPhone() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + "+905354181551"));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Product product = new Product("Saed","khaled","00000","at istanbul");
        Product product1 = new Product("khaled","saed","11111","at hatay");
        Product product2 = new Product("omar","khaled","22222","at syria");
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("products").child(product.getCode()).setValue(product);
        databaseReference.child("products").child(product1.getCode()).setValue(product1);
        databaseReference.child("products").child(product2.getCode()).setValue(product2);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                searchButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                        String productNo = productNoEditText.getText().toString().trim();
                        Product currentProduct = dataSnapshot.child("products").child(productNo).getValue(Product.class);
                        if (currentProduct != null) {
                            Parcelable wrapped = Parcels.wrap(currentProduct);
                            intent.putExtra(ResultActivity.RESULT_ACTIVITY_TAG, wrapped);
                            startActivity(intent);
                        } else {
                            Toast.makeText(MainActivity.this,"no product with the code: " + productNo,Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("MainActivity", "loadPost:onCancelled", databaseError.toException());

            }
        });
    }
}
