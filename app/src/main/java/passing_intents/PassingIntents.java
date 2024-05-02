package passing_intents;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.sajulga_androiddev.R;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class PassingIntents extends AppCompatActivity {


    public static Map<String, Button> items = new HashMap<>();
    public static final String[] keys = {"Name", "Gender", "Birthdate", "Number", "Email", "Address", "Hobbies", "Program", "YearLevel", "Crush", "Height"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_passing_intents);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.paneMain), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnSubmit = findViewById(R.id.btnSubmit);
        Button btnClear = findViewById(R.id.btnClear);

        EditText editFirstname = findViewById(R.id.editFirstname);
        EditText editLastname = findViewById(R.id.editLastname);

        RadioGroup radioGender = findViewById(R.id.radioGender);

        EditText editNumber = findViewById(R.id.editNumber);
        EditText editBirthdate = findViewById(R.id.editBirthdate);
        EditText editEmail = findViewById(R.id.editEmail);
        EditText editAddress = findViewById(R.id.editAddress);
        EditText editHobbies = findViewById(R.id.editHobbies);
        EditText editProgram = findViewById(R.id.editProgram);
        EditText editYearLevel = findViewById(R.id.editYearLevel);
        EditText editCrush = findViewById(R.id.editCrush);
        EditText editHeight = findViewById(R.id.editHeight);


        btnSubmit.setOnClickListener(v -> {
            Intent intent = new Intent(this, PassingIntentsAnother.class);



            RadioButton rbGender = findViewById(radioGender.getCheckedRadioButtonId());

            intent.putExtra("Name", editFirstname.getText() + " " + editLastname.getText());
            intent.putExtra("Gender", rbGender.getText());
            intent.putExtra("Number", editNumber.getText().toString());
            intent.putExtra("Birthdate", editBirthdate.getText().toString());
            intent.putExtra("Email", editEmail.getText().toString());
            intent.putExtra("Address", editAddress.getText().toString());
            intent.putExtra("Hobbies", editHobbies.getText().toString());
            intent.putExtra("Program", editProgram.getText().toString());
            intent.putExtra("YearLevel", editYearLevel.getText().toString());
            intent.putExtra("Crush", editCrush.getText().toString());
            intent.putExtra("Height", editHeight.getText().toString());

            startActivity(intent);
        });


        btnClear.setOnClickListener(v -> {
            editFirstname.setText("");
            editLastname.setText("");
            editNumber.setText("");
            editBirthdate.setText("");
            editEmail.setText("");
            editAddress.setText("");
            editHobbies.setText("");
            editProgram.setText("");
            editYearLevel.setText("");
            editCrush.setText("");
            editHeight.setText("");
        });
    }
}