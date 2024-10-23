package com.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // Verificar la conexión a la base de datos
        try (Connection conn = DatabaseConnection.getConnection()) {
            System.out.println("Conexión a la base de datos exitosa.");
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
            return; // Salir si no se puede conectar a la base de datos
        }

        StockManager stockManager = new StockManager();

        // Agregar baldes al stock
        stockManager.agregarBalde(11, 10.5); 
        stockManager.agregarBalde(25, 5.0);  
        stockManager.agregarBalde(38, 7.2);  

        // Enviar baldes a la sucursal
        stockManager.enviarASucursal(1, "Ramos");
        stockManager.enviarASucursal(2, "Palermo");

        // Generar devolución
        stockManager.generarDevolucion(2);

        // Obtener stock disponible
        List<Helado> stockDisponible = stockManager.getStock();
        for (Helado helado : stockDisponible) {
            System.out.println("ID: " + helado.getId() + ", Sabor: " + helado.getSabor() + ", Peso: " + helado.getKilos());
        }

        // Obtener peso total y cantidad por sabor
        Map<String, StockManager.SaborInfo> pesoYCantidadPorSabor = stockManager.getPesoYCantidadPorSabor();
        for (Map.Entry<String, StockManager.SaborInfo> entry : pesoYCantidadPorSabor.entrySet()) {
            System.out.println("Sabor: " + entry.getKey() + ", Kilos: " + entry.getValue().getTotalPeso() + ", Cantidad de Baldes: " + entry.getValue().getCantidad());
        }
    }
}