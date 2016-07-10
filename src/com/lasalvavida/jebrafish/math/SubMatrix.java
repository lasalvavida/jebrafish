package com.lasalvavida.jebrafish.math;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SubMatrix<T extends Number> extends Matrix<T> {
    protected List<Integer> excludeRows;
    protected List<Integer> excludeColumns;
    public SubMatrix(Matrix<T> parent) {
        super(parent.rows, parent.columns, parent.elements);
        excludeRows = new ArrayList<Integer>();
        excludeColumns = new ArrayList<Integer>();
        if (parent instanceof SubMatrix) {
            SubMatrix<T> parentSubMatrix = (SubMatrix<T>)parent;
            excludeRows.addAll(parentSubMatrix.excludeRows);
            excludeColumns.addAll(parentSubMatrix.excludeColumns);
        }
    }

    @Override
    public int getRows() {
        return rows - excludeRows.size();
    }

    @Override
    public int getColumns() {
        return columns - excludeColumns.size();
    }

    public void excludeRow(int row) {
        int rowOffset = getRowOffset(row);
        excludeRows.add(rowOffset, row + rowOffset);
    }

    public void excludeColumn(int column) {
        int columnOffset = getColumnOffset(column);
        excludeColumns.add(columnOffset, column + columnOffset);
    }

    private int getRowOffset(int row) {
        int pos = Collections.binarySearch(excludeRows, row);
        if (pos < 0) {
            pos = -pos-1;
        } else {
            pos++;
        }
        while (pos < excludeRows.size() && excludeRows.get(pos) == row + pos) {
            pos++;
        }
        return pos;
    }

    private int getColumnOffset(int column) {
        int pos = Collections.binarySearch(excludeColumns, column);
        if (pos < 0) {
            pos = -pos-1;
        } else {
            pos++;
        }
        while (pos < excludeColumns.size() && excludeColumns.get(pos) == column + pos) {
            pos++;
        }
        return pos;
    }

    @Override
    public T get(int row, int column) {
        int rowOffset = getRowOffset(row);
        int columnOffset = getColumnOffset(column);
        return super.get(row + rowOffset, column + columnOffset);
    }
}
