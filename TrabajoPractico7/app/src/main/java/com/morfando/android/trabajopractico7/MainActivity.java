package com.morfando.android.trabajopractico7;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import org.cocos2d.opengl.CCGLSurfaceView;

public class MainActivity extends Activity {

    CCGLSurfaceView vistaPrincipal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        CCGLSurfaceView vistaPrincipal = new CCGLSurfaceView(this);
        setContentView(vistaPrincipal);
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        Game game;
        game=new Game (vistaPrincipal);
        game.onStartGame();
    }
}
