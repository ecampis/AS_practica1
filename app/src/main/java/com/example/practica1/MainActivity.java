package com.example.practica1;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText edt_num1, edt_num2;
    Spinner spinnerOperacion;
    TextView txt_resultado;
    Button btn_sumar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Vincula las vistas
        edt_num1 = findViewById(R.id.edt_num1);
        edt_num2 = findViewById(R.id.edt_num2);
        spinnerOperacion = findViewById(R.id.spinner_operacion);
        txt_resultado = findViewById(R.id.txt_resultado);
        btn_sumar = findViewById(R.id.btn_sumar);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.operaciones, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOperacion.setAdapter(adapter);


        btn_sumar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strNum1 = edt_num1.getText().toString();
                String strNum2 = edt_num2.getText().toString();

                if (!strNum1.isEmpty() && !strNum2.isEmpty()) {
                    double num1 = Double.parseDouble(strNum1);
                    double num2 = Double.parseDouble(strNum2);
                    String operacion = spinnerOperacion.getSelectedItem().toString();
                    double resultado = 0;

                    switch (operacion) {
                        case "+":
                            resultado = num1 + num2;
                            break;
                        case "-":
                            resultado = num1 - num2;
                            break;
                        case "*":
                            resultado = num1 * num2;
                            break;
                        case "/":
                            if (num2 != 0) {
                                resultado = num1 / num2;
                            } else {
                                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                                txt_resultado.setText("No se puede dividir por 0");
                                return;
                            }
                            break;
                        default:
                            txt_resultado.setText("Operación no válida");
                            return;
                    }

                    txt_resultado.setText(String.valueOf(resultado));
                } else {
                    txt_resultado.setText("Error");
                }
            }
        });
    }
}
