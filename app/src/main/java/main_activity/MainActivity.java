package main_activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.sajulga_androiddev.R;

import button_exercise.ButtonExercise;
import calculator.Calculator;
import layout_exercise.LayoutExercise;
import match3.Match3;
import menu.Menu;
import passing_intents.PassingIntents;

public class MainActivity extends AppCompatActivity {


    protected void createLink(Button btn, Class<?> cls) {
        btn.setOnClickListener(v ->
        {
            Intent intent = new Intent(this, cls);
            startActivity(intent);
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.paneMain), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        Button btnLayoutExercise = findViewById(R.id.btnLayoutExercise);
        Button btnButtonExercise = findViewById(R.id.btnButtonExercise);
        Button btnCalculator = findViewById(R.id.btnCalculator);
        Button btnMidterm = findViewById(R.id.btnMidterm);
        Button btnMatch3 = findViewById(R.id.btnMatch3);
        Button btnPassingIntents = findViewById(R.id.btnPassingIntents);
        Button btnFragments = findViewById(R.id.btnFragments);
        Button btnMenus = findViewById(R.id.btnMenus);

        createLink(btnLayoutExercise, LayoutExercise.class);
        createLink(btnButtonExercise, ButtonExercise.class);
        createLink(btnCalculator, Calculator.class);
//        createLink(btnMidterm, .class);
        createLink(btnMatch3, Match3.class);
        createLink(btnPassingIntents, PassingIntents.class);
//        createLink(btnFragments, LayoutExercise.class);
        createLink(btnMenus, Menu.class);

    }

}