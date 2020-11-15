package com.nextsap.counter.graphics.frames.result;

import com.nextsap.counter.customer.CustomGame;
import com.nextsap.counter.graphics.FrameManager;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

/**
 * An extended {@link FrameManager} class
 */
public class ResultFrame extends FrameManager {

    /**
     * {@link ResultFrame} Constructor
     *
     * @param customGame is the current game
     */
    public ResultFrame(CustomGame customGame) {
        this.setTitle("Mic!ON - Game Admin - Résultats");
        this.setWidth(460);
        this.setHeight(259);
        this.initialize();

        this.getPanel().setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridheight = 1;
        constraints.gridwidth = 1;


        // Table
        JPanel tablePanel = new JPanel();
        tablePanel.setPreferredSize(new Dimension(450, 230));

        JTable resultTable = new JTable(new TableModel(customGame));
        resultTable.setPreferredSize(new Dimension(450, 230));
        resultTable.setAutoCreateRowSorter(true);
        resultTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        resultTable.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        alignment(resultTable);
        resize(resultTable);
        tablePanel.add(new JScrollPane(resultTable));

        this.getPanel().add(tablePanel);
    }

    /**
     * Align {@link JTable} columns
     *
     * @param jTable is the current {@link JTable}
     */
    public void alignment(JTable jTable) {
        DefaultTableCellRenderer second = new DefaultTableCellRenderer();
        second.setHorizontalAlignment(JLabel.LEFT);
        jTable.getColumnModel().getColumn(0).setCellRenderer(second);
        DefaultTableCellRenderer first = new DefaultTableCellRenderer();
        first.setHorizontalAlignment(JLabel.CENTER);
        jTable.getColumnModel().getColumn(1).setCellRenderer(first);
        jTable.getColumnModel().getColumn(2).setCellRenderer(first);

    }

    /**
     * Resize {@link JTable} columns
     *
     * @param jTable is the current {@link JTable}
     */
    public void resize(JTable jTable) {
        jTable.getColumnModel().getColumn(0).setPreferredWidth(125);
        jTable.getColumnModel().getColumn(1).setPreferredWidth(50);
        jTable.getColumnModel().getColumn(2).setPreferredWidth(50);
        jTable.getTableHeader().setResizingAllowed(false);
        jTable.getTableHeader().setReorderingAllowed(false);
    }

}
