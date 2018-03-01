package com.aeromexico.pab.web.dto;

import com.aeromexico.pab.entity.Matriz;
import com.aeromexico.pab.entity.MatrizDetalle;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alfredo Estrada
 */
public class MatrizSearchResult {
    List<Matriz> matrizUniqueList;
    List<MatrizDetalle> matrizDetalleList;

    public MatrizSearchResult(Matriz matriz, List<MatrizDetalle> matrizDetalleList) {
        this.matrizUniqueList = new ArrayList<Matriz>();
        this.matrizUniqueList.add(matriz);
        this.matrizDetalleList = matrizDetalleList;
    }

    public List<Matriz> getMatrizUniqueList() {
        return matrizUniqueList;
    }

    public List<MatrizDetalle> getMatrizDetalleList() {
        return matrizDetalleList;
    }    
}
