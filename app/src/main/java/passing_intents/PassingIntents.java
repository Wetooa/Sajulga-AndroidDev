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

            intent.putExtra("Name", editFirstname.getText() + " " + editLastname.getText());

            RadioButton rbGender = findViewById(radioGender.getCheckedRadioButtonId());
            intent.putExtra("Gender", rbGender.getText());

            intent.putExtra("Number", editNumber.getText());
            intent.putExtra("Birthdate", editBirthdate.getText());
            intent.putExtra("Email", editEmail.getText());
            intent.putExtra("Address", editAddress.getText());
            intent.putExtra("Hobbies", editHobbies.getText());
            intent.putExtra("Program", editProgram.getText());
            intent.putExtra("YearLevel", editYearLevel.getText());
            intent.putExtra("Crush", editCrush.getText());
            intent.putExtra("Height", editHeight.getText());

            startActivity(intent);
        });
    }
}