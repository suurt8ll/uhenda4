package com.connectfour;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.ArrayList;
import java.util.ListIterator;

public class SimpleMenuScreen implements Screen {

    private final Games game;
    private float menuWidth;
    private float menuHeight;

    private final ArrayList<ArrayList<Float>> layout;
    private final ArrayList<ArrayList<Actor>> actorsMatrix;

    private Stage stage;

    public SimpleMenuScreen(Games game) {
        this.game = game;
        layout = new ArrayList<>();
        actorsMatrix = new ArrayList<>();
    }

    public void newColumn(int rowNumber, float widthPercentage, Actor actor, Alignment verticalAlignment) {
        layout.get(rowNumber - 1).add(widthPercentage);
        actorsMatrix.get(rowNumber - 1).add(actor);
    }

    public void newRow(float heightPercentage) {
        layout.add(new ArrayList<>());
        actorsMatrix.add(new ArrayList<>());
    }

    public void finish() {
        //Kontrollib, kas kõigi ridade veergude summa on 100%.
        //sum < 100% --> lisa lõpu täiteks uus tühi veerg
        //sum > 100% --> kärbi veerge
        int i = 0;
        for (ArrayList<Float> row : layout) {
            float sum = 0;
            for (Float percentage : row) {
                sum += percentage;
            }
            if (sum < 1) {
                row.add(1 - sum);
                actorsMatrix.get(i).add(null);
            } else if (sum > 1) {
                ListIterator<Float> floatIterator = row.listIterator(row.size());
                int j = row.size() - 1;
                while (floatIterator.hasPrevious()) {
                    Float veerg = floatIterator.previous();
                    if (sum - veerg > 1) {
                        row.remove(j);
                        actorsMatrix.get(i).remove(j);
                        sum -= veerg;
                    } else if (sum - veerg < 1) {
                        row.set(j, 1 - (sum - veerg));
                        break;
                    }
                    j-=1;
                }
            }
            i += 1;
        }

    }

    public void visualise(int komakohad) {
        for (int i = 0; i < komakohad; i++) {
            System.out.print("#");
        }
        System.out.println();
        for (ArrayList<Float> row: layout) {
            for (Float veerg : row) {
                for (int i = 0; i < veerg * komakohad - 1; i++) {
                    System.out.print("0");
                }
                System.out.print("#");
            }
            System.out.println();
            for (int i = 0; i < komakohad; i++) {
                System.out.print("#");
            }
            System.out.println();
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    public enum Alignment {
        RIGHT,
        LEFT,
        CENTERED,
    }

}
