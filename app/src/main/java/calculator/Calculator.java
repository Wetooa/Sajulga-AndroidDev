package calculator;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sajulga_androiddev.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;



public class Calculator extends AppCompatActivity {
    ArrayList<String> history = new ArrayList<>();
    StringBuilder full = new StringBuilder();
    StringBuilder seq = new StringBuilder();
    Eval eval = new Eval();
    Boolean display = true;

    TextView currentNumber;
    TextView fullEquation;

    public void updateText() {
        StringBuilder text = new StringBuilder();
        history.forEach(s -> text.append(s).append("\n\n"));
        text.append(full.toString());

        fullEquation.setText(text);
        currentNumber.setText(seq.toString());
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calculator);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.paneMain), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        currentNumber = findViewById(R.id.txtCurrentNumber);
        fullEquation = findViewById(R.id.txtFullEquation);

        Button[] btnNumbers = new Button[10];
        for (int i = 0; i < 10; ++i) {
            btnNumbers[i] = findViewById(
                    this.getResources().getIdentifier("btn" + String.valueOf(i), "id", this.getPackageName()));
        }
        for (Button btn : btnNumbers) {
            btn.setOnClickListener((view) -> {
                if (full.length() > 0 && full.charAt(full.length() - 1) == '%')
                    return;

                String number = btn.getText().toString();
                if (display) {
                    full.append(number);
                    seq.append(number);
                } else {
                    full = new StringBuilder(number);
                    seq = new StringBuilder(number);
                }
                display = true;
                updateText();
            });
        }

        Button btnAdd = findViewById(R.id.btnAdd);
        Button btnSub = findViewById(R.id.btnSub);
        Button btnMultiply = findViewById(R.id.btnMultiply);
        Button btnDivide = findViewById(R.id.btnDivide);

        Button[] operations = {btnAdd, btnSub, btnMultiply, btnDivide};

        Button btnPercent = findViewById(R.id.btnPercent);
        Button btnPeriod = findViewById(R.id.btnPeriod);
        Button btnErase = findViewById(R.id.btnErase);
        Button btnClear = findViewById(R.id.btnClear);
        Button btnEquals = findViewById(R.id.btnEquals);

        btnEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double res = eval.eval(full.toString());
                history.add(full.toString());

                String newNumber = res == res.intValue() ? Integer.toString(res.intValue()) : res.toString();
                full = new StringBuilder(newNumber);
                seq = new StringBuilder(newNumber);

                display = false;
                updateText();
            }
        });

        Arrays.stream(operations).forEach((op) -> {
            op.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String ch = op.getText().toString();

                    if (seq.length() > 0 && Eval.operationsList.contains(seq.toString().charAt(seq.length() - 1))) {
                        seq.setCharAt(seq.length() - 1, ch.charAt(0));
                        full.setCharAt(full.length() - 1, ch.charAt(0));
                    } else {
                        if (Pattern.compile(Eval.operationsRegex).matcher(seq.toString()).find()) {
                            Double res = eval.eval(seq.toString());
                            String newNumber = res == res.intValue() ? Integer.toString(res.intValue()) : res.toString();
                            seq = new StringBuilder(newNumber);
                        }
                        seq.append(ch);
                        full.append(ch);
                    }
                    display = true;
                    updateText();
                }
            });
        });

        Button[] btnSpecialOperations = {btnPercent, btnPeriod};

        Arrays.stream(btnSpecialOperations).forEach(op -> {
            op.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String n = "";
                    String ch = op.getText().toString();
                    String tmp = seq.toString();
                    if (tmp.length() > 0 && tmp.charAt(0) == '-') tmp = seq.substring(1);

                    String[] numbers = tmp.split(Eval.operationsRegex);

                    boolean withOperations = Pattern.compile(Eval.operationsRegex).matcher(tmp).find();
                    if (numbers.length == 2) n = numbers[1];
                    else if (!withOperations) n = numbers[0];

                    if (!n.contains(ch)) {
                        full.append(ch);
                        seq.append(ch);
                    } else if (full.length() > 0 && full.charAt(full.length() - 1) == ch.charAt(0)) {
                        full.deleteCharAt(full.length() - 1);
                        seq.deleteCharAt(seq.length() - 1);
                    }

                    display = true;
                    updateText();
                }
            });
        });


        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                history.clear();
                full = new StringBuilder();
                seq = new StringBuilder();
                updateText();
            }
        });

        btnErase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                full.deleteCharAt(full.length() - 1);
                seq.deleteCharAt(seq.length() - 1);
                updateText();
            }
        });
    }
}