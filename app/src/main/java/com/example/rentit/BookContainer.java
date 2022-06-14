package com.example.rentit;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BookContainer extends AppCompatActivity {

    RecyclerView recyclerView;
    BookRvAdapter bookRvAdapter;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    String uid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_container);

        Toast.makeText(this, "Please wait...", Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "data is Loading...", Toast.LENGTH_SHORT).show();

        recyclerView = (RecyclerView) findViewById(R.id.Rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("RentIt").child("RentBy");
        FirebaseUser user = firebaseAuth.getCurrentUser();
        assert user != null;
        uid = user.getUid();

        FirebaseRecyclerOptions<BookRvModel> options =
                new FirebaseRecyclerOptions.Builder<BookRvModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("RentIt").child("BookList"), BookRvModel.class)
                        .build();

        bookRvAdapter = new BookRvAdapter(options);
        recyclerView.setAdapter(bookRvAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        txtSearch(uid);
        bookRvAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        bookRvAdapter.stopListening();

    }

    public void txtSearch(String str) {
        FirebaseRecyclerOptions<BookRvModel> options =
                new FirebaseRecyclerOptions.Builder<BookRvModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("RentIt").child("BookList").orderByChild("UserId").startAt(str).endAt(str + "~"), BookRvModel.class)
                        .build();

        bookRvAdapter = new BookRvAdapter(options);
        bookRvAdapter.startListening();

        recyclerView.setAdapter(bookRvAdapter);

    }
}