package com.example.fatih.pixelium;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.view.View;

public class ToolActions{

    public static void pencil(DrawingView view){
        view.setErase(false);
    }

    public static void eraser(DrawingView view){
        view.setErase(true);
    }

    public static void newFile(DrawingView view){
        view.getCanvas().drawColor(0, PorterDuff.Mode.CLEAR);
    }

    /*public static void airBrush(DrawingView view){
        Shader shader = new RadialGradient(view.getLastBrushSize()/2, view.getLastBrushSize()/2, view.getLastBrushSize()/2,
                Color.argb(255, 0, 0, 0), Color.argb(0, 0, 0, 0), Shader.TileMode.CLAMP);
        view.getPaint().setShader(shader);
        view.getPaint().setAlpha(0x10);
    }*/

    /*public static void fill(DrawingView view){
        ...
    }*/

    /*public static void colorPicker(DrawingView view){

    }*/

    /*public static void shape(DrawingView view){
        ...
    }*/
}
