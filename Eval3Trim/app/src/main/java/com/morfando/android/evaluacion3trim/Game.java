package com.morfando.android.evaluacion3trim;

import android.drm.DrmStore;
import android.util.Log;
import android.view.MotionEvent;

import org.cocos2d.actions.interval.IntervalAction;
import org.cocos2d.actions.interval.MoveBy;
import org.cocos2d.actions.interval.MoveTo;
import org.cocos2d.actions.interval.RotateBy;
import org.cocos2d.actions.interval.ScaleBy;
import org.cocos2d.actions.interval.ScaleTo;
import org.cocos2d.actions.interval.Sequence;
import org.cocos2d.layers.Layer;
import org.cocos2d.nodes.Director;
import org.cocos2d.nodes.Scene;
import org.cocos2d.nodes.Sprite;
import org.cocos2d.opengl.CCGLSurfaceView;
import org.cocos2d.types.CCSize;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Matias on 11/21/2017.
 */

class Game {
    CCGLSurfaceView myContext;
    CCSize screen;
    ArrayList<Sprite> sprites;

    int selected, rotation;

    public Game(CCGLSurfaceView context)
    {
        myContext = context;
    }

    public void startgame()
    {
        Director.sharedDirector().attachInView(myContext);
        screen = Director.sharedDirector().displaySize();
        Director.sharedDirector().runWithScene(gameScene());
    }

    private Scene gameScene(){
        Scene scene = Scene.node();

        layer l = new layer();
        scene.addChild(l);

        return  scene;
    }

    class layer extends Layer {
        public layer() {
            setSprites();
            this.setIsTouchEnabled(true);
        }

        private void setSprites(){
            sprites = new ArrayList<Sprite>();
            rotation = 0;

            for (int i = 0; i < 2; i++){
                sprites.add(Sprite.sprite(i +".png"));
                super.addChild(sprites.get(i));
            }

            float screenCenter = screen.getWidth()/2;
            double ySpriteInferior = screen.getHeight()*0.25;
            double ySpriteSuperior = screen.getHeight()*0.75;

            Log.d("Primer Sprite", "x= " + screenCenter + " & y=" + ySpriteInferior);
            sprites.get(0).setPosition(screenCenter,(float) ySpriteInferior);
            Log.d("Segundo Sprite", "x= " + screenCenter + " & y=" + ySpriteSuperior);
            sprites.get(1).setPosition(screenCenter,(float) ySpriteSuperior);

        }

        @Override
        public boolean ccTouchesBegan(MotionEvent event) {
            MoveTo m;
            Random random = new Random();
            selected = random.nextInt(2);

            if (selected == 0){
                Log.d("Sprite que se mueve", "Primer Spite");
                m = MoveTo.action(3f, screen.getWidth() - sprites.get(selected).getWidth()/2, screen.getHeight()-sprites.get(selected).getHeight()/2);

            } else {
                Log.d("Sprite que se mueve", "Segundo Spite");
                m = MoveTo.action(3f, screen.getWidth() - sprites.get(selected).getWidth()/2, screen.getHeight()-sprites.get(selected).getHeight()/2);
            }

            sprites.get(selected).runAction(m);

            return true;
        }

        @Override
        public boolean ccTouchesMoved(MotionEvent event) {
            RotateBy r = RotateBy.action(1.5f, 15f);

            if(rotation < 360){
                rotation += 15;
                Log.d("rotation", "is " + rotation);
                if(selected != 1){
                    sprites.get(1).runAction(r);
                } else {
                    sprites.get(0).runAction(r);
                }
            }
            return true;
        }
        @Override
        public boolean ccTouchesEnded(MotionEvent event) {
            IntervalAction secuence;
            ScaleBy s1 = ScaleBy.action(1f,1.5f);
            ScaleTo s2 = ScaleTo.action(1f,1f);
            secuence = Sequence.actions(s1,s2,s1,s2,s1,s2);
            sprites.get(0).runAction(secuence);
            sprites.get(1).runAction(secuence);


            return true;
        }

    }

}
