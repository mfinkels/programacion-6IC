package com.morfando.android.evaluacion3trim;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import org.cocos2d.opengl.CCGLSurfaceView;

public class MainActivity extends AppCompatActivity {

    CCGLSurfaceView vistaprincipal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //setContentView(R.layout.activity_main);
        vistaprincipal = new CCGLSurfaceView(this);
        setContentView(vistaprincipal);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Game g = new Game(vistaprincipal);
        g.startgame();

    }

}
