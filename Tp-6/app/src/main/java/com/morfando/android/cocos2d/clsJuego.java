package com.morfando.android.cocos2d;

import android.media.tv.TvContract;
import android.util.Log;

import org.cocos2d.layers.Layer;
import org.cocos2d.nodes.Director;
import org.cocos2d.nodes.Scene;
import org.cocos2d.nodes.Sprite;
import org.cocos2d.opengl.CCGLSurfaceView;
import org.cocos2d.types.CCSize;

/**
 * Created by Matias on 8/15/2017.
 */

public class clsJuego {
    CCGLSurfaceView viewGame;
    CCSize screenSize;
    Sprite naveJugador;

    public clsJuego( CCGLSurfaceView vistadelJuego){
        this.viewGame=vistadelJuego;
    }


    public void comenzarJuego() {
        Director.sharedDirector().attachInView(viewGame);
        screenSize=Director.sharedDirector().displaySize();
        Director.sharedDirector().runWithScene(EscenaDelJuego());
    }
    private  Scene EscenaDelJuego(){
        Log.d("Escenadeljugo", "declaro e instancio la escena");
        Scene escenaAdevolver;
        escenaAdevolver=Scene.node();


        Log.d("Escena del juego","declaro e instancio al capa que va a conete");
        CapaDeFondo miCapaDeFondo;
        miCapaDeFondo = new CapaDeFondo();

        Log.d("Escena de juego","declaro e instancio la capa que va a contener ");
        CapaDeFrente MicapaFrente;
        MicapaFrente= new CapaDeFrente();


        Log.d("Escena del juego", "Agrego la escena de la capa del fondo amas atras");
        escenaAdevolver.addChild(miCapaDeFondo, -10);
        escenaAdevolver.addChild(MicapaFrente,-10);

        Log.d("Escena del juego", "Devuelvo la esenca ya armada");
        return escenaAdevolver;
    }

    class CapaDeFrente extends Layer {

        public CapaDeFrente() {
            ponerNaveJugadorInicial();
        }

        private void ponerNaveJugadorInicial() {
            Log.d("PonernaveJugador", "comenzo a poner la nav del jugador");

            naveJugador = Sprite.sprite("logo.png");

            naveJugador.setPosition(100, 300);

            super.addChild(naveJugador);
        }
    }

    class CapaDeFondo extends Layer {
    }


}
