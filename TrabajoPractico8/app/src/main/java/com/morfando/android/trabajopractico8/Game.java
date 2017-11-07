package com.morfando.android.trabajopractico8;

import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.util.Log;
import android.view.MotionEvent;

import org.cocos2d.actions.base.Action;
import org.cocos2d.actions.ease.EaseIn;
import org.cocos2d.actions.interval.IntervalAction;
import org.cocos2d.actions.interval.MoveBy;
import org.cocos2d.actions.interval.MoveTo;
import org.cocos2d.actions.interval.RotateTo;
import org.cocos2d.actions.interval.ScaleBy;
import org.cocos2d.actions.interval.Sequence;
import org.cocos2d.layers.Layer;
import org.cocos2d.nodes.CocosNode;
import org.cocos2d.nodes.Director;
import org.cocos2d.nodes.Label;
import org.cocos2d.nodes.Scene;
import org.cocos2d.nodes.Sprite;
import org.cocos2d.opengl.CCGLSurfaceView;
import org.cocos2d.transitions.FlipXTransition;
import org.cocos2d.types.CCColor3B;
import org.cocos2d.types.CCSize;

import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Matias on 9/28/2017.
 */

public class Game {
    CCGLSurfaceView viewGame;
    CCSize screen;

    Sprite bg, bgPoints, player;
    Label numberPoints, startToPlay;

    int points;

    boolean isPlaying;


    Sprite spikesUp, spikesDown;
    ArrayList<Sprite> spikesLeft, spikesRight;

    EaseIn MoveRight;
    EaseIn MoveLeft;

    EaseIn gravityRight;
    EaseIn gravityLeft;

    Boolean typeLastMovement;


    public Game( CCGLSurfaceView vistadelJuego){
        this.viewGame=vistadelJuego;
    }

    public void onStartGame() {
        Director.sharedDirector().attachInView(viewGame);
        screen=Director.sharedDirector().displaySize();
        Log.d("Tamano pantalla", screen.width + " " + screen.height);
        Director.sharedDirector().runWithScene(MainScene());
        isPlaying = false;
        MoveRight = EaseIn.action(MoveBy.action(0.5f,100,100),15);
        MoveLeft = EaseIn.action(MoveBy.action(0.5f,-100,100),15);

        gravityRight = (EaseIn) gravityPlayer(true);
        gravityLeft = (EaseIn) gravityPlayer(false);
    }

    private Scene MainScene(){
        Scene scene;
        scene=Scene.node();

        Background background;
        background = new Background();

        Points points;
        points= new Points();

        Spikes spikes;
        spikes = new Spikes();

        Player player;
        player= new Player();

        MainMenu menu;
        menu = new MainMenu();


        scene.addChild(background, -10);
        scene.addChild(points,0);
        scene.addChild(spikes,5);
        scene.addChild(player, 10);
        scene.addChild(menu, 15);

        return scene;
    }




     class Background extends Layer{

        public Background(){
            setBackground();
        }

        public void setBackground() {
            bg = Sprite.sprite("bg.png");

            bg.runAction(ScaleBy.action(0.1f,screen.width/bg.getWidth(), screen.height/bg.getHeight()));
            bg.setPosition(screen.width/2, screen.height/2);

            super.addChild(bg,1);
        }
    }

    class Spikes extends Layer{

        public Spikes(){
            setSpikes();
        }

        public void setSpikes() {
            int x = 0;
            do{
                spikesUp = Sprite.sprite("spikes_up.png");
                spikesDown = Sprite.sprite("spikes_down.png");
                spikesUp.setPosition(x, screen.height-(spikesUp.getHeight()/2));
                spikesDown.setPosition(x, (spikesUp.getHeight()/2));
                super.addChild(spikesUp, 101);
                super.addChild(spikesDown,102);
                x += 650;
            }while(screen.getWidth() > x);

            spikesLeft = new ArrayList<Sprite>();
            spikesRight = new ArrayList<Sprite>();
            int y = 110;
            do {
                Sprite spikes = Sprite.sprite("spike.png");
                spikes.setPosition((-spikes.getWidth()), y);
                spikesLeft.add(spikes);

                spikes.setPosition((screen.getWidth() + spikes.getWidth()), y);
                spikesRight.add(spikes);

                y += 10;
            }while ((screen.getHeight()-100) > y);

        }
    }


    class Points extends Layer{

        public Points(){
            setPoints();
        }

        public void setPoints() {
            bgPoints = Sprite.sprite("bgPoints.png");

            bgPoints.setPosition(screen.width/2, screen.height/2);

            numberPoints = Label.label("0","Vedana",60f);
            numberPoints.setColor(new CCColor3B(0,0,0));
            numberPoints.setPosition(screen.getWidth()/2, screen.getHeight()/2);

            super.addChild(bgPoints);
            super.addChild(numberPoints);
        }
    }

    class Player extends Layer{

        public Player(){
            setPlayer();
        }

        public void setPlayer() {
            player = Sprite.sprite("player_01.png");

            player.setPosition(screen.getWidth()/2, screen.getHeight()/2);

            super.addChild(player,2);
        }
    }


    class MainMenu extends Layer{

        public MainMenu(){
            setMainMenu();
        }

        public void setMainMenu() {
            this.setIsTouchEnabled(true);
            startToPlay = Label.label("Touch to start playing", "Vedana", 60f);

            startToPlay.setPosition(screen.width/2, screen.height/2 + 30);

            super.addChild(startToPlay,3);
        }

        @Override
        public boolean ccTouchesBegan(MotionEvent event) {
            Log.d("began", "ccTouchesBegan");
            if(startToPlay.isVisible()){
                Log.d("began", "start playing");
                startPlaying();
            } else {
                Log.d("isPlaying", (isPlaying?"true" : "false"));
                if(isPlaying){
                    Log.d("began", "Move Player");
                    movePlayer(typeLastMovement);
                }

            }
            return true;
        }

    }

    private void movePlayer(Boolean m) {
        Log.d("move player", "entro a movePlayer");

        int finalPosition=0;

        if (m == null || m == true){
            m = true;
            finalPosition = (int) (player.getPositionX()+100-player.getWidth()/2);
        } else {
            finalPosition = (int) (player.getPositionX()-100-player.getWidth()/2);
        }

        IntervalAction secuence;
        if (finalPosition >= (int)(screen.getWidth()-player.getWidth()/2)){
            // Llego a la derecha, entonces se mueve rota y la gravedad es hacia la izquierda
            EaseIn gravity = (EaseIn) gravityPlayer(false);
            secuence = Sequence.actions(MoveRight, RotateTo.action(0,-180), gravityLeft);
            m = false;
            Log.d("move player", "Llego a la derecha, entonces se mueve rota y la gravedad es hacia la izquierda");
        } else if(finalPosition >= (int)(player.getWidth()/2)) {
            // Llego a la izquierda, entonces se mueve rota y la gravedad es hacia la derecha
            EaseIn gravity = (EaseIn) gravityPlayer(true);
            secuence = Sequence.actions(MoveLeft, RotateTo.action(0,-180), gravityRight);
            m = true;
            Log.d("move player", "Llego a la izquierda, entonces se mueve rota y la gravedad es hacia la derecha");
        } else {
            if (m == true){
                //derecha
                secuence = Sequence.actions(MoveRight, RotateTo.action(0,-180), gravityRight);
                Log.d("move player", "se mueve a la derecha");
            }else {
                //izquierda
                secuence = Sequence.actions(MoveLeft, gravityLeft);
                Log.d("move player", "se mueve a la izquierda");
            }
        }
        player.runAction(secuence);
    }

    public Action gravityPlayer(boolean right){
        float x, y;
        if(right){
            x = screen.getWidth() - (player.getWidth()/2) - 20;
        } else {
            x = (player.getWidth()/2) + 20;
        }
        y = (player.getHeight()/2);
        return EaseIn.action(MoveTo.action(2f,x,y),15);
    }

    private void startPlaying() {
        //Iniciar juego
        startToPlay.setVisible(false);
        points = 0;
        numberPoints.setString(points +"");
        isPlaying = true;
        typeLastMovement = null;
        player.runAction(gravityPlayer(true));
    }


    private boolean isColliding(Sprite sprt1, Sprite sprt2){
        int sprt1_left = (int) (sprt1.getPositionX() - sprt1.getWidth()/2);
        int sprt1_right = (int) (sprt1.getPositionX() + sprt1.getWidth()/2);
        int sprt1_bottom =(int) (sprt1.getPositionY() - sprt1.getHeight()/2);
        int sprt1_top = (int) (sprt1.getPositionY() + sprt1.getHeight()/2);
        int sprt2_left = (int) (sprt2.getPositionX() - sprt2.getWidth()/2);
        int sprt2_right = (int) (sprt2.getPositionX() + sprt2.getWidth()/2);
        int sprt2_bottom = (int) (sprt2.getPositionY() - sprt2.getHeight()/2);
        int sprt2_top = (int) (sprt2.getPositionY() + sprt2.getHeight()/2);

        //Borde izq y borde inf de Sprite 1 está dentro de Sprite 2
        if (isBetween(sprt1_left, sprt2_left, sprt2_right) &&
                isBetween(sprt1_bottom, sprt2_bottom, sprt2_top)) {
            return true;
        }
        //Borde izq y borde sup de Sprite 1 está dentro de Sprite 2
        if (isBetween(sprt1_left, sprt2_left, sprt2_right) &&
                isBetween(sprt1_top, sprt2_bottom, sprt2_top)) {
            return true;
        }
        //Borde der y borde sup de Sprite 1 está dentro de Sprite 2
        if (isBetween(sprt1_right, sprt2_left, sprt2_right) &&
                isBetween(sprt1_top, sprt2_bottom, sprt2_top)) {
            return true;
        }
        //Borde der y borde inf de Sprite 1 está dentro de Sprite 2
        if (isBetween(sprt1_right, sprt2_left, sprt2_right) &&
                isBetween(sprt1_bottom, sprt2_bottom, sprt2_top)) {
            return true;
        }
        //Borde izq y borde inf de Sprite 2 está dentro de Sprite 1
        if (isBetween(sprt2_left, sprt1_left, sprt1_right) &&
                isBetween(sprt2_bottom, sprt1_bottom, sprt1_top)) {
            return true;
        }

        //Borde izq y borde sup de Sprite 1 está dentro de Sprite 1
        if (isBetween(sprt2_left, sprt1_left, sprt1_right) &&
                isBetween(sprt2_top, sprt1_bottom, sprt1_top)) {
            return true;
        }
        //Borde der y borde sup de Sprite 2 está dentro de Sprite 1
        if (isBetween(sprt2_right, sprt1_left, sprt1_right) &&
                isBetween(sprt2_top, sprt1_bottom, sprt1_top)) {
            return true;
        }
        //Borde der y borde inf de Sprite 2 está dentro de Sprite 1
        if (isBetween(sprt2_right, sprt1_left, sprt1_right) &&
                isBetween(sprt2_bottom, sprt1_bottom, sprt1_top)) {
            return true;
        }
        return false;
    }

    private boolean isBetween(int number, int smallerNumber, int biggerNumber){
        if (biggerNumber < smallerNumber){
            int aux;
            aux = biggerNumber;
            biggerNumber = smallerNumber;
            smallerNumber = aux;
        }

        if (number >= smallerNumber && number <= biggerNumber)
            return true;
        else
            return false;
    }

    private boolean flip(){
        return false;
    }

}
