package com.example.calculoconta;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView edtConsumoT;
    private TextView edtCouvert;
    private TextView edtDividir;
    private Button btnCalcular;
    private TextView txtResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtConsumoT = findViewById(R.id.edtConsumoT);
        edtCouvert = findViewById(R.id.edtCouvet);  // Corrigido nome para 'edtCouvert'
        edtDividir = findViewById(R.id.edtDividir);
        btnCalcular = findViewById(R.id.btnCalcular);
        txtResultado = findViewById(R.id.txtResultado);

        EdgeToEdge.enable(this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void Calcular(View v) {
        try {
            // Verifica se os campos estão preenchidos antes de converter
            if (edtConsumoT.getText().toString().isEmpty() || edtCouvert.getText().toString().isEmpty() || edtDividir.getText().toString().isEmpty()) {
                txtResultado.setText("Por favor, preencha todos os campos.");
                return;
            }

            double consumo = Double.parseDouble(edtConsumoT.getText().toString());
            double taxa = Double.parseDouble(edtCouvert.getText().toString());
            double div = Double.parseDouble(edtDividir.getText().toString());

            double total = taxa + consumo;

            if (div != 0) {
                double divisao = total / div;
                txtResultado.setText(String.format("Taxa de serviço: %.2f\nConta Total: %.2f\nValor por pessoa: %.2f", taxa, total, divisao));
            } else {
                txtResultado.setText(String.format("Taxa de serviço: %.2f\nConta Total: %.2f\nDivisão inválida.", taxa, total));
            }
        } catch (NumberFormatException e) {
            txtResultado.setText("Por favor, insira valores numéricos válidos.");
        }
    }
}
