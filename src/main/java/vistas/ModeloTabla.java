package vistas;

import javax.swing.table.DefaultTableModel;

public class ModeloTabla extends DefaultTableModel {
    

    public ModeloTabla(String[] columnas, int i) {
        super(columnas, i);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }


}
