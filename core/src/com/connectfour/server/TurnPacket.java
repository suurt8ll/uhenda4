package com.connectfour.server;

import java.io.Serializable;

/**Infopakike mängiate vahel suhtlemiseks.*/
public class TurnPacket implements Serializable {

    public int mitmesTurn;
    public boolean yourTurn;
    /**Veeru number kuhu litter pandi. Muud arvutused tehakse serveris.*/
    public int turnX;

    public TurnPacket(int mitmesTurn, boolean yourTurn, int turnX) {
        this.mitmesTurn = mitmesTurn;
        this.yourTurn = yourTurn;
        this.turnX = turnX;
    }
}
