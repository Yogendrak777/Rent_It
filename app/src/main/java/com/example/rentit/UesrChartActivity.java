package com.example.rentit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UesrChartActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    UserAdapter userAdapter;
    List<personInfoRvModel> personInfoRvModelList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uesr_chart);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        personInfoRvModelList = new ArrayList<>();
        readUsers();
    }

    private void readUsers() {
        final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("RentIt").child("RentBy");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                personInfoRvModelList.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    personInfoRvModel personInfoRvModel1 = snapshot1.getValue(personInfoRvModel.class);
                    assert personInfoRvModel1 != null;
                    assert firebaseUser != null;
                   // personInfoRvModelList.add(personInfoRvModel1);
                    if (!personInfoRvModel1.getUserEmailDb().equals(firebaseUser.getEmail())) {
                        personInfoRvModelList.add(personInfoRvModel1);
                    }

                }
                userAdapter = new UserAdapter(UesrChartActivity.this,personInfoRvModelList);
                recyclerView.setAdapter(userAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}


