package com.connectfour;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class ColorPicker extends Actor{
    private int sizex;
    private int sizey;
    private Color color;
    private String colorString;
    private Pixmap pixmap;
    private TextureRegionDrawable drawable;
    private int selectedsize;
    private int paddingsize;
    private int colorarea;
    public ColorPicker(int x, int y, Color color, String colorString) {
        this.sizex = x;
        this.sizey = y;
        this.color = color;
        this.colorString = colorString;
        this.pixmap = new Pixmap(this.sizex,this.sizey, Pixmap.Format.RGBA8888);
        init();
        addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                int pixval = pixmap.getPixel((int) x,(int) y);
                for (int i = 0; i < selectedsize; i++) {
                    pixmap.setColor(pixval);
                    color.set(pixval);
                    pixmap.fillRectangle(i, 0,1,sizey);
                }
                drawable = new TextureRegionDrawable(new Texture(pixmap));
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
        this.drawable.draw(batch, getX(),getY(),this.sizex,this.sizey);
    }
    private void init(){
        setBounds(0,0,this.sizex,this.sizey);
        this.selectedsize = this.sizey;
        this.paddingsize = this.sizey;
        this.colorarea =this.sizex- this.selectedsize-this.paddingsize;
        for (int i = 0; i < selectedsize; i++) {
            pixmap.setColor(this.color);
            pixmap.fillRectangle(i, 0,1,sizey);
        }

        for (int i = 0; i < colorarea; i++) {
            this.pixmap.setColor(hslToRgb((float) i/colorarea,1, 0.5f));
            this.pixmap.fillRectangle(i+selectedsize+paddingsize, 0,1,this.sizey);
        }
        this.drawable = new TextureRegionDrawable(new Texture(this.pixmap));

    }
    public static Color hslToRgb(float h, float s, float l){
        float r, g, b;

        if (s == 0f) {
            r = g = b = l; // achromatic
        } else {
            float q = l < 0.5f ? l * (1 + s) : l + s - l * s;
            float p = 2 * l - q;
            r = hueToRgb(p, q, h + 1f/3f);
            g = hueToRgb(p, q, h);
            b = hueToRgb(p, q, h - 1f/3f);
        }
        return new Color(r,g,b,1);
    }
    public static float hueToRgb(float p, float q, float t) {
        if (t < 0f)
            t += 1f;
        if (t > 1f)
            t -= 1f;
        if (t < 1f/6f)
            return p + (q - p) * 6f * t;
        if (t < 1f/2f)
            return q;
        if (t < 2f/3f)
            return p + (q - p) * (2f/3f - t) * 6f;
        return p;
    }

    public Color getCurrentColor() {
        return color;
    }

    public String getStringColorHex() {
        return color.toString();
    }
}
