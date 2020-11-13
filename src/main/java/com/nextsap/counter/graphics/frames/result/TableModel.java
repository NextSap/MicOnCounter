package com.nextsap.counter.graphics.frames.result;

import com.nextsap.counter.customer.CustomParty;

import javax.swing.table.AbstractTableModel;

public class TableModel extends AbstractTableModel {

    private final CustomParty customParty;
    private final String[] columnName = {"Pseudo", "Kills", "Place"};

    public TableModel(CustomParty customParty) {
        this.customParty = customParty;
    }

    @Override
    public int getRowCount() {
        return customParty.getPodium().size();
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
        switch (columnIndex) {
            case 0:
                return customParty.getPodium().get(rowIndex);
            case 1:
                String name = customParty.getPodium().get(rowIndex);
                return customParty.getKills().get(name);
            case 2:
                return rowIndex + 1;
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
