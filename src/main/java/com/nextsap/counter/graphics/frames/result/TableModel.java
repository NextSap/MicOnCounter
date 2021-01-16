package com.nextsap.counter.graphics.frames.result;

import com.nextsap.counter.customer.CustomGame;
import com.nextsap.counter.customer.PodiumEntry;

import javax.swing.table.AbstractTableModel;
import java.util.Map;

/**
 * An extended {@link AbstractTableModel} class
 */
public class TableModel extends AbstractTableModel {

    // Define attributes
    private final CustomGame customGame;
    private final String[] columnName = {"Pseudo", "Kills", "Place"};

    /**
     * {@link TableModel} Constructor
     *
     * @param customGame is the current game
     */
    public TableModel(CustomGame customGame) {
        this.customGame = customGame;
    }

    @Override
    public int getRowCount() {
        return customGame.getPodium().size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public String getColumnName(int column) {
        return columnName[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Map<String, PodiumEntry> podium = customGame.getPodium();
        switch (columnIndex) {
            case 0:
                return podium.keySet().toArray()[rowIndex];
            case 1:
                return customGame.getPodium().get(podium.keySet().toArray()[rowIndex]).getKills();
            case 2:
                return customGame.getPodium().get(podium.keySet().toArray()[rowIndex]).getPodium();
            default:
                throw new IllegalArgumentException();
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return String.class;
            case 1:
            case 2:
                return Integer.class;
            default:
                return Object.class;
        }
    }
}
