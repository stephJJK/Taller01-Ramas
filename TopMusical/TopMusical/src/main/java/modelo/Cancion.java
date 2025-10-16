/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.*;

/**
 *
 * @author Gladys
 */
public class Cancion implements Comparable<Cancion> {

    private String titulo;
    private String cantante;

    private String imagen;
    private int posActual; //la posicion actual en el ranking
    private int posPrevia;

    private int semanas;
    private List<Integer> historialPos; //lista con todas las posiciones que ha tenido en el ranking

    public Cancion(String titulo, String cantante, String imagen, int posActual, int posPrevia, int semanas, List<Integer> historialPos) {
        this.titulo = titulo;
        this.cantante = cantante;
        this.imagen = imagen;
        this.posActual = posActual;
        this.posPrevia = posPrevia;

        this.semanas = semanas;
        this.historialPos = historialPos;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getCantante() {
        return cantante;
    }

    public String getImagen() {
        return imagen;
    }

    public int getPosActual() {
        return posActual;
    }

    public int getPosPrevia() {
        return posPrevia;
    }

 
    public int getSemanas() {
        return semanas;
    }

    public List<Integer> getHistorialPos() {
        return historialPos;
    }

    public static ArrayList<Cancion> leerCanciones() {
        ArrayList<Cancion> lista = new ArrayList<>();

        try ( BufferedReader br = new BufferedReader(new FileReader("top10.csv"))) {
            String linea = "";
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                String[] historial = datos[6].split("-");
                ArrayList<Integer> listaHistorial = new ArrayList<>();
                for (String h:historial){
                    listaHistorial.add(Integer.valueOf(h));
                }
                Cancion c = new Cancion(datos[0], datos[1], datos[2], Integer.valueOf(datos[3]), Integer.valueOf(datos[4]), Integer.valueOf(datos[5]),  listaHistorial);
                lista.add(c);
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        /*  String s = "20,4,3,2,1,1,1,1,1,1,1,1";
        Cancion c = new Cancion("Anti-Hero","TayLor Swift","antihero.jpg",1,1,1,12,new ArrayList<>(Arrays.asList(s.split(","))));
        lista.add(c);
        s = "15,10,7,3,2,1,2,3";
        Cancion c2 = new Cancion("Unholy","Sam Smith & Kim Petras","unholy.jpg",3,2,1,8,new ArrayList<>(Arrays.asList(s.split(","))));
        lista.add(c2);*/
        return lista;
    }

    @Override
    public int compareTo(Cancion o) {
        return posActual - o.posActual;
    }

}
