package vn.edu.ueh.thanhdnh.firebase_example;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
  public static final String COLLECTION_ARTICLES = "articles";

  FirebaseFirestore db;
  Button btAdd, btShow;
  EditText etTitle, etContent, etImageUrl;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    EdgeToEdge.enable(this);
    setContentView(R.layout.activity_main);
    ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
      Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
      return insets;
    });

    FirebaseApp.initializeApp(this);
    db = FirebaseFirestore.getInstance();
    btAdd = findViewById(R.id.btAdd);
    btShow = findViewById(R.id.btShow);
    etTitle = findViewById(R.id.etTitle);
    etContent = findViewById(R.id.etContent);
    etImageUrl = findViewById(R.id.etImageUrl);
    btAdd.setOnClickListener(this);
    btShow.setOnClickListener(this);
  }

  @Override
  public void onClick(View view) {
    if (view.getId() == R.id.btAdd) {
      addArticle();
    } else if (view.getId() == R.id.btShow) {
      Intent intent = new Intent(getBaseContext(), ShowDataActivity.class);
      startActivity(intent);
    }
  }

  private void addArticle() {
    String title = etTitle.getText().toString().trim();
    String content = etContent.getText().toString().trim();
    String imageUrl = etImageUrl.getText().toString().trim();

    if (title.isEmpty()) {
      etTitle.setError("Nhap tieu de");
      return;
    }

    // Tao document moi de lay id tu Firestore, roi luu id vao chinh field "id"
    DocumentReference ref = db.collection(COLLECTION_ARTICLES).document();
    Article article = new Article(ref.getId(), title, content, imageUrl);

    ref.set(article)
            .addOnSuccessListener(unused -> {
              Toast.makeText(this, "Da them bai viet", Toast.LENGTH_SHORT).show();
              etTitle.setText("");
              etContent.setText("");
              etImageUrl.setText("");
            })
            .addOnFailureListener(e ->
                    Toast.makeText(this, "Loi: " + e.getMessage(), Toast.LENGTH_LONG).show());
  }
}