package org.artrev.workshop.pbt.examples.board;

import java.util.ArrayList;
import java.util.List;

/*

Board with Tickets
A limited amount of active tickets.



 */
public class Board {
    private List<Ticket> archivedTickets;
    private List<Ticket> activeTickets;

    private Board(final int maximumTickets) {
        archivedTickets = new ArrayList<>();
        activeTickets = new ArrayList<>();
    }


}
