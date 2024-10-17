package com.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class StockManager {
    private List<Helado> stock = new ArrayList<>();
    private List<Helado> enviadoASucursal = new ArrayList<>();

    public void agregarBalde(int codigoSabor, double peso) {
        try {
            Helado helado = new Helado(codigoSabor, peso);
            stock.add(helado);
            int id = registrarEnStock(codigoSabor, peso, helado.getSabor(), true);
            helado.setId(id);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

    public void enviarASucursal(int id, String sucursal) {
        Helado helado = buscarHeladoEnStock(id);
        if (helado != null) {
            stock.remove(helado);
            enviadoASucursal.add(helado);
            actualizarEnStock(id, false);
            registrarEnvio(id, helado.getCodigo(), helado.getSabor(), sucursal, false, null);
        }
    }

    public void generarDevolucion(int id) {
        Helado helado = buscarHeladoEnSucursal(id);
        if (helado != null) {
            enviadoASucursal.remove(helado);
            stock.add(helado);
            actualizarEnStock(id, true);
            actualizarEnvio(id, true, new Timestamp(System.currentTimeMillis()));
        }
    }

    private Helado buscarHeladoEnStock(int id) {
        for (Helado helado : stock) {
            if (helado.getId() == id) {
                return helado;
            }
        }
        return null;
    }

    private Helado buscarHeladoEnSucursal(int id) {
        for (Helado helado : enviadoASucursal) {
            if (helado.getId() == id) {
                return helado;
            }
        }
        return null;
    }

    public List<Helado> getStock() {
        List<Helado> stockList = new ArrayList<>();
        String sql = "SELECT id, codigo_sabor, peso, sabor, fecha_ingreso FROM stock WHERE en_stock = true";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                int codigoSabor = rs.getInt("codigo_sabor");
                double peso = rs.getDouble("peso");
                String sabor = rs.getString("sabor");
                Timestamp timestamp = rs.getTimestamp("fecha_ingreso");
                Helado helado = new Helado(codigoSabor, peso, sabor);
                helado.setId(id);
                helado.setTimestampIngreso(timestamp.toLocalDateTime());
                stockList.add(helado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stockList;
    }

    public List<Helado> getEnviadoASucursal() {
        return enviadoASucursal;
    }

    private int registrarEnStock(int codigoSabor, double peso, String sabor, boolean enStock) {
        String sql = "INSERT INTO stock (codigo_sabor, peso, sabor, en_stock, fecha_ingreso) VALUES (?, ?, ?, ?, ?) RETURNING id";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, codigoSabor);
            pstmt.setDouble(2, peso);
            pstmt.setString(3, sabor);
            pstmt.setBoolean(4, enStock);
            pstmt.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private void actualizarEnStock(int id, boolean enStock) {
        String sql = "UPDATE stock SET en_stock = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setBoolean(1, enStock);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void registrarEnvio(int id, int codigoSabor, String sabor, String sucursal, boolean devuelto, Timestamp fechaDevolucion) {
        String sql = "INSERT INTO envios (id, codigo_sabor, sabor, sucursal, devuelto, fecha_devolucion, fecha_ingreso) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setInt(2, codigoSabor);
            pstmt.setString(3, sabor);
            pstmt.setString(4, sucursal);
            pstmt.setBoolean(5, devuelto);
            pstmt.setTimestamp(6, fechaDevolucion);
            pstmt.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void actualizarEnvio(int id, boolean devuelto, Timestamp fechaDevolucion) {
        String sql = "UPDATE envios SET devuelto = ?, fecha_devolucion = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setBoolean(1, devuelto);
            pstmt.setTimestamp(2, fechaDevolucion);
            pstmt.setInt(3, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}