package com.example;
import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;

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
        stockManager.agregarBalde(1, 10.5); 
        stockManager.agregarBalde(2, 5.0);  
        stockManager.agregarBalde(3, 7.2);  

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
    }
}