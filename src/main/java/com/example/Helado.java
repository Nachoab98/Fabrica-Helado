package com.example;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Helado {
    private int id;
    private int codigo;
    private double kilos;
    private String sabor;
    private LocalDateTime timestampIngreso;
    private LocalDateTime timestampEnvio;
    private LocalDateTime timestampDevolucion;
    private final Map<Integer, Double> baldes; // ID del balde y su peso en kilos

    public Helado() {
        this.baldes = new HashMap<>();
    }

  // Constructor personalizado
  public Helado(int codigo, double kilos) {
    this.codigo = codigo;
    this.kilos = kilos;
    this.sabor = obtenerSabor(codigo);
    this.timestampIngreso = LocalDateTime.now();
    this.baldes = new HashMap<>();
}

// Constructor con sabor
public Helado(int codigo, double kilos, String sabor) {
    this.codigo = codigo;
    this.kilos = kilos;
    this.sabor = sabor;
    this.timestampIngreso = LocalDateTime.now();
    this.baldes = new HashMap<>();
}


    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCodigo() {
        return codigo;
    }

    public double getKilos() {
        return kilos;
    }

    public String getSabor() {
        return sabor;
    }

    public LocalDateTime getTimestampIngreso() {
        return timestampIngreso;
    }
    
    public void setTimestampIngreso(LocalDateTime timestampIngreso) {
        this.timestampIngreso = timestampIngreso;
    }

    public LocalDateTime getTimestampEnvio() {
        return timestampEnvio;
    }

    public void setTimestampEnvio(LocalDateTime timestampEnvio) {
        this.timestampEnvio = timestampEnvio;
    }

    public LocalDateTime getTimestampDevolucion() {
        return timestampDevolucion;
    }

    public void setTimestampDevolucion(LocalDateTime timestampDevolucion) {
        this.timestampDevolucion = timestampDevolucion;
    }

    public String obtenerSabor(int codigoSabor) {
        switch (codigoSabor) {
            case 1:
                return "Crema Vainilla";
            case 2:
                return "Crema Americana";
            case 3:
                return "Crema Rusa";
            case 4: 
                return "Crema Almendras";
            case 5:
                return "Crema Turca";
            case 6:
                return "Marroc";
            case 7:
                return "Frutos del Bosque";
            case 8:
                return "Crema Cielo";
            case 9:
                return "Crema Café";
            case 10: 
                return "Chocolate con Pasas";
            case 11: 
                return "Chocolate";
            case 12:
                return "Chocolate Almendrado";
            case 13:
                return "Mantecol";
            case 14:
                return "Chocolate Blanco";
            case 15:
                return "Chocolate Hoky";
            case 16: 
                return "Chocolate Africano";    
            case 17:
                return "Chocolate Tarento";
            case 18:
                return "Chocolate Bariloche";
            case 19:
                return "Chocolate Nevado";  
            case 20:
                return "Granizado de Chocolate";
            case 21:
                return "Sambayon";
            case 22:
                return "Sambayon con Almendras";
            case 23: 
                return "Sambayon con Cereza";
            case 24:
                return "Amarena";
            case 25:
                return "Coco Split";
            case 26:
                return "Tramontana";  
            case 27:
                return "Manjar Dulce";  
            case 28:
                return "Dulce de Leche con Nuez";
            case 29:
                return "Mousse de Chocolate";
            case 30:
                return "Mousse de Limón";
            case 31: 
                return "Manjar Blanco";     
            case 32:
                return "Dulce de Leche";
            case 33:
                return "Menta Granizada";
            case 34:
                return "Dulce de Leche Granizado";
            case 35:
                return "Kinoto al Whisky";
            case 36:
                return "Coco a la Crema"; 
            case 37:
               return "Cereza a la crema";
            case 38:
                return "Banana a la crema";
            case 39:
                return "Flan";
            case 40: 
                return "Frutilla a la Crema";
            case 41:
                return "Limon";
            case 42:
                return "Banana Split Granizada";
            case 43:
                return "Frutilla a la Crema";
            case 44:
                return "Banana Split";
            case 45:
                return "Melon";
            case 46:
                return "Durazno";
            case 47:
                return "Manzana";
            case 49:
                return "Anana";
            case 50: 
                return "Frutilla";
            case 51:
                return "Bombon Rocher";
            case 52:
                return "Anana a la Crema";         
            case 53:
                return "Mascarpone con Frutos";
            case 54: 
                return "Mascarpone con Maracuya";
            case 55:
                return "Lemon Pie";
            case 56:
                return "Pistacho";
            case 57: 
                return "Tiramisu";
            case 58:
                return "Espumon";
            case 59:
                return "Maracuya a la Crema";
            case 60:
                return "Sambayon a la Reina";
            case 61:
                return "Sambayon Nuez";
            case 62:
                return "Crema Oreo";
            case 63:
                return "Dulce Sorreto";
            case 64:
                return "Chocolate Turco";
            case 65:
                return "Chocolate con Dulce";
            case 66:
                return "Maracuya";
            case 67:
                return "Naranja";
            case 68:
                return "Suflan";
            default:
                throw new IllegalArgumentException("Código de sabor desconocido: " + codigoSabor);
        }
    }
}