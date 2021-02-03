package com.connectfour;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.utils.Disposable;

public class ColorPicker extends Actor{
    private int sizex;
    private int sizey;
    private Color color;
    private String colorString;
    private Pixmap pixmap;
    public ColorPicker(int x, int y, Color color, String colorString) {
        this.sizex = x;
        this.sizey = y;
        this.color = color;
        this.colorString = colorString;
        this.pixmap = new Pixmap(100,100, Pixmap.Format.RGBA8888);
        init();
        addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("-----");
                System.out.println(x);
                System.out.println(y);
                return true;
            }
        });
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        this.pixmap.setColor(Color.CYAN);
        this.pixmap.fillRectangle(10,0,100,100);
    }
    private void init(){
        setBounds(0,0,100,100);
    }
}
