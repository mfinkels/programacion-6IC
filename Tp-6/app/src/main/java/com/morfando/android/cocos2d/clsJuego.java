package com.morfando.android.cocos2d;

import android.media.tv.TvContract;
import android.util.Log;

import org.cocos2d.actions.interval.MoveTo;
import org.cocos2d.actions.interval.ScaleBy;
import org.cocos2d.layers.Layer;
import org.cocos2d.nodes.Director;
import org.cocos2d.nodes.Scene;
import org.cocos2d.nodes.Sprite;
import org.cocos2d.opengl.CCGLSurfaceView;
import org.cocos2d.types.CCSize;

import java.util.Random;

/**
 * Created by Matias on 8/15/2017.
 */

public class clsJuego {
    CCGLSurfaceView viewGame;
    CCSize screenSize;
    Sprite imagenVertice, fondo;

    public clsJuego( CCGLSurfaceView vistadelJuego){
        this.viewGame=vistadelJuego;
    }


    public void comenzarJuego() {
        Director.sharedDirector().attachInView(viewGame);
        screenSize=Director.sharedDirector().displaySize();
        Log.d("Tamano pantalla", screenSize.width + " " + screenSize.height);
        Director.sharedDirector().runWithScene(EscenaDelJuego());

    }
    private  Scene EscenaDelJuego(){
        Log.d("Escenadeljugo", "declaro e instancio la escena");
        Scene scene;
        scene=Scene.node();


        Log.d("Escena del juego","declaro e instancio al capa que va a conete");
        CapaDeFondo miCapaDeFondo;
        miCapaDeFondo = new CapaDeFondo();

        Log.d("Escena de juego","declaro e instancio la capa que va a contener ");
        CapaDeFrente miCapaFrente;
        miCapaFrente= new CapaDeFrente();


        Log.d("Escena del juego", "Agrego la escena de la capa del fondo amas atras");
        scene.addChild(miCapaDeFondo, -10);
        scene.addChild(miCapaFrente,10);

        Log.d("Escena del juego", "Devuelvo la esenca ya armada");
        return scene;
    }

    class CapaDeFrente extends Layer {
        int x, y;

        public CapaDeFrente() {
            ponerImagen();
            super.schedule("ponerImagen", 3.0f);
        }

        private void moverAlVertice() {
            int xF, yF;
            if (x < screenSize.width/2){
               //izquierda
                xF = (int)screenSize.width + ((int)imagenVertice.getWidth()/2);
            } else {
              //derecha
                xF = - ((int)imagenVertice.getWidth()/2);
            }
            if (y < screenSize.height/2){
                yF =  (int) screenSize.getHeight() + ((int)imagenVertice.getHeight()/2);
            }else {
                yF =  - ((int)imagenVertice.getHeight()/2);
            }
            imagenVertice.runAction(MoveTo.action(3,xF,yF));
        }

        private void ponerImagen() {

            imagenVertice = Sprite.sprite("logo.png");

            Random getNumber;
            getNumber = new Random();
            x = getNumber.nextInt((int) screenSize.width-160) + 80;
            y = getNumber.nextInt((int) screenSize.height-176) + 88;

            imagenVertice.setPosition(x, y);

            super.addChild(imagenVertice);
            moverAlVertice();
        }

    }

    class CapaDeFondo extends Layer {

        public CapaDeFondo(){
            ponerFondo();
        }

        private void ponerFondo() {
            fondo = Sprite.sprite("fondo.jpg");

            fondo.runAction(ScaleBy.action(0.1f,screenSize.width/fondo.getWidth(), screenSize.height/fondo.getHeight()));
            fondo.setPosition(screenSize.width/2, screenSize.height/2);

            super.addChild(fondo);
        }
    }



}
