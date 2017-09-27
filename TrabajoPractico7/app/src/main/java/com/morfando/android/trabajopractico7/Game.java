package com.morfando.android.trabajopractico7;

import android.util.Log;
import android.view.MotionEvent;

import org.cocos2d.actions.interval.IntervalAction;
import org.cocos2d.actions.interval.MoveBy;
import org.cocos2d.actions.interval.MoveTo;
import org.cocos2d.actions.interval.ScaleBy;
import org.cocos2d.actions.interval.Sequence;
import org.cocos2d.layers.Layer;
import org.cocos2d.nodes.Director;
import org.cocos2d.nodes.Scene;
import org.cocos2d.nodes.Sprite;
import org.cocos2d.opengl.CCGLSurfaceView;
import org.cocos2d.types.CCSize;

import java.util.Random;

/**
 * Created by Matias on 9/25/2017.
 */

public class Game {
    CCGLSurfaceView viewGame;
    CCSize screenSize;
    Sprite obj1, obj2, fondo;
    boolean isObj1Touched, isObj2Touched;

    public Game( CCGLSurfaceView vistadelJuego){
        this.viewGame=vistadelJuego;
    }

    public void onStartGame() {
        Director.sharedDirector().attachInView(viewGame);
        screenSize=Director.sharedDirector().displaySize();
        Log.d("Tamano pantalla", screenSize.width + " " + screenSize.height);
        isObj1Touched = false;
        isObj1Touched = false;
        Director.sharedDirector().runWithScene(MainScene());
    }

    private Scene MainScene(){
        Scene scene;
        scene=Scene.node();

        CapaDeFondo miCapaDeFondo;
        miCapaDeFondo = new CapaDeFondo();

        CapaDeFrente miCapaFrente;
        miCapaFrente= new CapaDeFrente();

        scene.addChild(miCapaDeFondo, -10);
        scene.addChild(miCapaFrente,10);

        return scene;
    }

    class CapaDeFrente extends Layer {

        IntervalAction intervalAction = Sequence.actions(MoveBy.action(3f, 50f, 0f),
                MoveBy.action(3f, 0f, -50f), MoveBy.action(3f, -50f, 0f), MoveBy.action(3f, 0f, 50f));

        public CapaDeFrente() {
            addObj();
            this.setIsTouchEnabled(true);
        }

        @Override
        public boolean ccTouchesBegan(MotionEvent event) {
            if (isBetween((int)event.getX(), (int)(obj1.getPositionX()-obj1.getWidth()/2), (int)(obj1.getPositionX()+obj1.getWidth()/2))) {
                if (isBetween((int)(screenSize.getHeight()-event.getY()), (int)(obj1.getPositionY()-obj1.getHeight()/2), (int)(obj1.getPositionY()+obj1.getHeight()/2))){
                    isObj1Touched = true;
                }
            } else if (isBetween((int)event.getX(), (int)(obj2.getPositionX()-obj2.getWidth()/2), (int)(obj2.getPositionX()+obj2.getWidth()/2))){
                if(isBetween((int)(screenSize.getHeight()-event.getY()), (int)(obj2.getPositionY()-obj2.getHeight()/2), (int)(obj2.getPositionY()+obj2.getHeight()/2))){
                    isObj2Touched = true;
                }
            }

            return true;
        }

        @Override
        public boolean ccTouchesMoved(MotionEvent event) {
            if (isObj1Touched){
                moveSprite(obj1,event.getX(),screenSize.getHeight() - event.getY() - obj1.getHeight());
            } else if (isObj2Touched) {
                moveSprite(obj2,event.getX(),screenSize.getHeight() - event.getY() - obj2.getHeight());
            }

            if (isColliding(obj1,obj2)){
                if (isObj1Touched) {
                    obj1.runAction(intervalAction);
                } else {
                    obj2.runAction(intervalAction);
                }
            }

            return true;
        }

        @Override
        public boolean ccTouchesEnded(MotionEvent event) {
            isObj1Touched = false;
            isObj2Touched = false;
            return true;
        }



        public void addObj() {

            obj1 = Sprite.sprite("apple.jpg");
            obj2 = Sprite.sprite("android.jpg");
            do {
                setRandomPosition(obj1);
                setRandomPosition(obj2);
            }while (isColliding(obj1,obj2));


            super.addChild(obj1);
            super.addChild(obj2);
        }

    }

    private void setRandomPosition(Sprite sprite){
        Random getNumber;
        getNumber = new Random();
        int x = getNumber.nextInt((int) screenSize.width-((int) sprite.getWidth()*2)) + (int)sprite.getWidth();
        int y = getNumber.nextInt((int) getNumber.nextInt((int) screenSize.width-((int) sprite.getHeight()*2)) + (int)sprite.getHeight());

        sprite.setPosition(x,y);
    }

    private void moveSprite(Sprite sprite, float x, float y){
        sprite.setPosition(x,y);
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
