package com.example.fatih.pixelium;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import java.util.UUID;
import android.provider.MediaStore;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class DrawingScreen extends AppCompatActivity implements OnClickListener{

    private DrawingView drawView;
    private Button drawBtn, eraseBtn, saveBtn, newBtn;
    private ImageButton currPaint;
    private float smallBrush, mediumBrush, largeBrush;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawing_screen);
        drawView = (DrawingView)findViewById(R.id.drawing);
        LinearLayout paintLayout = (LinearLayout)findViewById(R.id.paint_layout);
        currPaint = (ImageButton)paintLayout.getChildAt(0);

        smallBrush = getResources().getInteger(R.integer.small_size);
        mediumBrush = getResources().getInteger(R.integer.medium_size);
        largeBrush = getResources().getInteger(R.integer.large_size);
        drawView.setBrushSize(mediumBrush);

        drawBtn = (Button)findViewById(R.id.pencil_btn);
        drawBtn.setOnClickListener(this);

        eraseBtn = (Button)findViewById(R.id.eraser_btn);
        eraseBtn.setOnClickListener(this);

        saveBtn = (Button)findViewById(R.id.save_btn);
        saveBtn.setOnClickListener(this);

        newBtn = (Button)findViewById(R.id.newfile_btn);
        newBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        //respond to clicks
        if(view.getId()==R.id.pencil_btn){
            widthDialog();
            drawView.pencil();
        }

        else if(view.getId()==R.id.eraser_btn){
            //switch to erase - choose size
            widthDialog();
            drawView.eraser();
        }

        /*else if(view.getId()==R.id.airBrush_btn){
            widthDialog();
            ToolActions.airBrush(drawView);
        }*/

        /*else if(view.getId()==R.id.fill_btn){
            ToolActions.fill(drawView);
        }*/

        /*else if(view.getId()==R.id.colorPicker_btn){
            ToolActions.colorPicker(drawView);
        }*/

        /*else if(view.getId()==R.id.shape_btn){
            shapeDialog();
            ToolActions.shape(drawView);
        }*/

        /*else if(view.getId()==R.id.preferences_btn){
            Intent intent = new Intent(getApplicationContext(),Preferences.class);
            startActivity(intent);
        }*/

        else if(view.getId()==R.id.background_btn){
            //backgroundDialog();
            drawView.setSquareGround();
        }

        else if(view.getId()==R.id.save_btn){
            saveDialog();
        }

        else if(view.getId()==R.id.newfile_btn){
            newFileDialog();
        }

    }

    /*public void backgroundDialog(){
        final Dialog backgroundDialog = new Dialog(this);
        backgroundDialog.setTitle("Background:");
        ...
        */

    public void widthDialog(){
        final Dialog brushDialog = new Dialog(this);
        brushDialog.setTitle("Brush size:");
        brushDialog.setContentView(R.layout.brush_chooser);

        ImageButton smallBtn = (ImageButton)brushDialog.findViewById(R.id.small_brush);
        smallBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                drawView.setBrushSize(smallBrush);
                drawView.setLastBrushSize(smallBrush);
                brushDialog.dismiss();
            }
        });

        ImageButton mediumBtn = (ImageButton)brushDialog.findViewById(R.id.medium_brush);
        mediumBtn.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v) {
                drawView.setBrushSize(mediumBrush);
                drawView.setLastBrushSize(mediumBrush);
                brushDialog.dismiss();
            }
        });

        ImageButton largeBtn = (ImageButton)brushDialog.findViewById(R.id.large_brush);
        largeBtn.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v) {
                drawView.setBrushSize(largeBrush);
                drawView.setLastBrushSize(largeBrush);
                brushDialog.dismiss();
            }
        });
        brushDialog.show();
    }

    public void saveDialog(){
        AlertDialog.Builder saveDialog = new AlertDialog.Builder(this);
        saveDialog.setTitle("Save drawing");
        saveDialog.setMessage("Save drawing to device Gallery?");
        saveDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                drawView.setDrawingCacheEnabled(true);
                String imgSaved = MediaStore.Images.Media.insertImage(
                        getContentResolver(), drawView.getDrawingCache(),
                        UUID.randomUUID().toString() + ".png", "drawing");
                if (imgSaved != null) {
                    Toast savedToast = Toast.makeText(getApplicationContext(),
                            "Drawing saved to Gallery!", Toast.LENGTH_SHORT);
                    savedToast.show();
                } else {
                    Toast unsavedToast = Toast.makeText(getApplicationContext(),
                            "Oops! Image could not be saved.", Toast.LENGTH_SHORT);
                    unsavedToast.show();
                }

                drawView.destroyDrawingCache();
            }
        });
        saveDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        saveDialog.show();
    }

    public void newFileDialog() {
        AlertDialog.Builder newDialog = new AlertDialog.Builder(this);
        newDialog.setTitle("New drawing");
        newDialog.setMessage("Start new drawing (you will lose the current drawing)?");
        newDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which){
                drawView.newFile();
                dialog.dismiss();
            }
        });
        newDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which){
                dialog.cancel();
            }
        });
        newDialog.show();
    }

    public void paintClicked(View view){
        drawView.setErase(false);
        drawView.setBrushSize(drawView.getLastBrushSize());
        //use chosen color
        if(view!=currPaint){
            //update color
            ImageButton imgView = (ImageButton)view;
            String color = view.getTag().toString();
            drawView.setColor(color);
        }

    }
}
