package com.example.demo.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class FirebaseInitializerJava  {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    @PostConstruct
    private  void iniFirestore() throws IOException {
        InputStream serviceAccount = getClass().getClassLoader().getResourceAsStream("dsmoviles_crendenciales.json");
        assert serviceAccount != null;
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://dsm-bd.firebaseio.com/")
                .build();
        if(FirebaseApp.getApps().isEmpty()){
            FirebaseApp.initializeApp(options);
            firebaseDatabase=FirebaseDatabase.getInstance();
            databaseReference=firebaseDatabase.getReference();
        }
    }

    public Firestore getFirestore(){
        return FirestoreClient.getFirestore();
    }

}
