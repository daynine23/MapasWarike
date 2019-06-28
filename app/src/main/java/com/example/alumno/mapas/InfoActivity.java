package com.example.alumno.mapas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {

    public TextView nombre, descripcion, horario, referencia, comentario, telefono;
    public RatingBar barra;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        barra = findViewById(R.id.mRatingBar);
        nombre = findViewById(R.id.txtRestauranteNombre);
        descripcion = findViewById(R.id.txtDescripcion);
        horario = findViewById(R.id.txtHorario);
        referencia = findViewById(R.id.txtReferencia);
        comentario = findViewById(R.id.txtRateComment);
        telefono = findViewById(R.id.txtTelefono);

        barra.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                comentario.setText(String.valueOf(v));
                switch ((int) ratingBar.getRating()){
                    case 1:
                        comentario.setText("Muy malo");
                        break;
                    case 2:
                        comentario.setText("Malo");
                        break;
                    case 3:
                        comentario.setText("Puede mejorar");
                        break;
                    case 4:
                        comentario.setText("Bueno, lo recomiendo");
                        break;
                    case 5:
                        comentario.setText("¡Exelente!");
                        break;
                    default:
                        comentario.setText("");
                }
            }
        });
    }
}
