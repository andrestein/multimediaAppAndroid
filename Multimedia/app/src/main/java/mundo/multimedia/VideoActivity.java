package mundo.multimedia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.VideoView;

public class VideoActivity extends AppCompatActivity {

    private Button btnBuscar;
    private ImageButton imageBtnPlay;
    private VideoView reproductor;
    private String filePath="";
    private final int PICKER = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        initComponents();
        cargarArchivo();
        reproducir();
    }

    private void initComponents(){
        btnBuscar =(Button) findViewById(R.id.btnBuscarVideo);
        imageBtnPlay=(ImageButton) findViewById(R.id.imageBtnPlay);
        reproductor = (VideoView) findViewById(R.id.videoView);

    }

    public void reproducir(){
        imageBtnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(filePath != "") {
                    reproductor.setVideoPath(filePath);
                    reproductor.start();
                    reproductor.requestFocus();
                }else{
                    Toast.makeText(getApplicationContext(),"Primero busque el video",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void cargarArchivo(){
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("file/*");
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                try{
                    startActivityForResult(Intent.createChooser(intent,"Seleccione un archivo para subir"),PICKER );
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"Por favor, instale un administrador de archivos",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        switch (requestCode){
            case PICKER:
                if(resultCode == RESULT_OK){
                    filePath = data.getData().getPath();
                    break;
                }
        }
    }


}
