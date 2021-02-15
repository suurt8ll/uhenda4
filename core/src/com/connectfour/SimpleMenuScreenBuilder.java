package com.connectfour;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;

public class SimpleMenuScreenBuilder {

    private final Games game;
    public float menuWidth;
    public float menuHeight;
    private int rows = 0;

    private final ArrayList<Float> rowLayout;
    //TODO Parem alternatiiv HashMapidele oleks rohkem memory efficient.
    private final HashMap<Integer, ArrayList<Float>> columnLayout;
    private final HashMap<Integer, ArrayList<Actor>> actorsMatrix;

    private Stage stage;

    public SimpleMenuScreenBuilder(Games game) {
        this.game = game;
        rowLayout = new ArrayList<>();
        columnLayout = new HashMap<>();
        actorsMatrix = new HashMap<>();
    }

    public void newEmptyColumn(int rowNumber, float widthPercentage) {
        newColumn(rowNumber, widthPercentage, null, null, false);
    }

    /**Kogu ekraani kohandab ennast selle veeru järgi.*/
    public void newAncorColumn(int rowNumber, float widthPercentage, Actor actor) {
        newColumn(rowNumber, widthPercentage, actor, null, true);
    }

    public void newColumn(int rowNumber, float widthPercentage, Actor actor, Alignment verticalAligment) {
        newColumn(rowNumber, widthPercentage, actor, verticalAligment, false);
    }

    private void newColumn(int rowNumber, float widthPercentage, Actor actor, Alignment verticalAlignment, boolean ancor) {
        if (ancor) {
            menuWidth = actor.getWidth() / widthPercentage;
            menuHeight = actor.getHeight() / rowLayout.get(rowNumber);
        }
        columnLayout.get(rowNumber - 1).add(widthPercentage);
        actorsMatrix.get(rowNumber - 1).add(actor);
    }

    public void newRow(float heightPercentage) {
        rowLayout.add(heightPercentage);
        columnLayout.put(rows + 1, new ArrayList<>());
        actorsMatrix.put(rows + 1, new ArrayList<>());
        rows += 1;
    }

    public void finish() {
        //Kontrollib, kas kõigi ridade ja veergude summa on 100%.
        float sumY = 0;
        for (int reaNumber = 0; reaNumber < rowLayout.size(); reaNumber++) {
            sumY += rowLayout.get(reaNumber);
            float sumX = 0;
            ArrayList<Float> row = columnLayout.get(reaNumber);
            for (Float percentage : row) {
                sumX += percentage;
            }
            //sumX < 100% --> lisa lõpu täiteks uus tühi veerg
            //sumX > 100% --> kärbi veerge
            if (sumX < 1) {
                row.add(1 - sumX);
                actorsMatrix.get(reaNumber).add(null);
            } else if (sumX > 1) {
                ListIterator<Float> floatIterator = row.listIterator(row.size());
                int j = row.size() - 1;
                while (floatIterator.hasPrevious()) {
                    Float veerg = floatIterator.previous();
                    if (sumX - veerg > 1) {
                        row.remove(j);
                        actorsMatrix.get(reaNumber).remove(j);
                        sumX -= veerg;
                    } else if (sumX - veerg < 1) {
                        row.set(j, 1 - (sumX - veerg));
                        break;
                    }
                    j-=1;
                }
            }
            //TODO Ridade automaatne kärpimine.
            reaNumber += 1;
        }
        //Tühja Stage loomine.
        Camera cam = new OrthographicCamera();
        cam.position.set(menuWidth / 2, menuHeight / 2, 0);
        Viewport viewport = new ExtendViewport(menuWidth, menuHeight, cam);
        game.batch.setProjectionMatrix(stage.getViewport().getCamera().combined);
        stage = new Stage(viewport, game.batch);

        //TODO Actorite lisamine ja scalemine Stagesse.

    }

    /**Algeline framework, suudab ainult keskele joonestatud {@link Actor} ridasid luua, reas saab vaid üks {@link Actor} olla.*/
    public void initStage(Actor[] actorArr, float rowSpacing) {

        if (rowSpacing > 1f / actorArr.length) System.err.println("HOIATUS! Ridade vahe ei saa olla nii suur, sest matemaatika ei luba!");

        int biggestX = 0;
        int heightSum = 0;

        for (Actor actor : actorArr) {
            int x = (int) actor.getWidth();
            int y = (int) actor.getHeight();
            if (x > biggestX) biggestX = x;
            heightSum += y;
        }

        float aspectRatio = Games.MONITORWIDTH / (float) Games.MONITORHEIGHT;
        float idealHeight = biggestX / aspectRatio;
        float realHeight = heightSum / (1 - rowSpacing * actorArr.length + rowSpacing);
        float realWidth = biggestX;
        boolean edgePadding = false;

        //Tõene juhul kui objektid ei mahuks antud laiuse juures vertikaalselt ekraanile.
        if (realHeight > idealHeight) {
            realWidth = aspectRatio * realHeight;
            edgePadding = true;
        }

        menuWidth = realWidth;
        if (edgePadding) {
            menuHeight = realHeight;
        } else {
            menuHeight = idealHeight;
        }

        Camera cam = new OrthographicCamera();
        cam.position.set(menuWidth / 2, menuHeight / 2, 0);
        Viewport viewport = new ExtendViewport(menuWidth, menuHeight, cam);

        Stage stage = new Stage(viewport, game.batch);
        stage.getBatch().setProjectionMatrix(stage.getViewport().getCamera().combined);

        int i = 1;
        for (Actor actor : actorArr) {
            float x = (realWidth - actor.getWidth()) / 2;
            float y;
            if (edgePadding) {
                y = realHeight - i * actor.getHeight() - (i - 1) * realHeight * rowSpacing;
            } else {
                y = idealHeight - ((idealHeight - realHeight) / 2) - i * actor.getHeight() - (i - 1) * idealHeight * rowSpacing;
            }
            actor.setPosition(x, y);
            stage.addActor(actor);
            i += 1;
        }

        this.stage = stage;
    }

    public Stage getStage() {
        return stage;
    }

    public enum Alignment {
        RIGHT,
        LEFT,
        CENTERED,
    }

}
