package com.example.marci.hf1;

/**
 * Created by marci on 19/11/2017.
 */

public class elementoscercanos {
    private String nombre;
    private int idDrawable;
    private String profesion;
    private String ciudad;
    private String distancia;
    private int idUbi;

    public elementoscercanos(String nombre, int idDrawable, String profesion, String ciudad, String distancia, int idUbi) {
        this.nombre = nombre;
        this.idDrawable = idDrawable;
        this.profesion=profesion;
        this.ciudad=ciudad;
        this.distancia=distancia;
        this.idUbi=idUbi;

    }

    public String getNombre() {
        return nombre;
    }

    public int getIdDrawable() {
        return idDrawable;
    }

    public int getId() {
        return nombre.hashCode();
    }


    public int getIdUbi() {
        return  idUbi;
    }

    public String getProfesion(){
        return profesion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getDistancia() {
        return distancia;
    }





    public static elementoscercanos[] ITEMS = {
            new elementoscercanos("Primera Persona", R.drawable.primero, "Estudiante", "Maracaibo", "11km", R.drawable.pin),
            new elementoscercanos("Segunda Persona", R.drawable.segundo,"Profesor", "Maracaibo", "14km", R.drawable.pin),
            new elementoscercanos("Tercera Persona", R.drawable.tercero,"Electricista", "San Francisco", "21km", R.drawable.pin),
            new elementoscercanos("Cuarta Persona", R.drawable.cuarto,"Plomero", "San Francisco", "32km", R.drawable.pin),
            new elementoscercanos("Quinta Persona", R.drawable.quinto,"Refrigeración", "Cabimas", "41km", R.drawable.pin),
            new elementoscercanos("Sexta Persona", R.drawable.sexto,"Mecánico", "Cabimas", "44km", R.drawable.pin),
            new elementoscercanos("Septima Persona", R.drawable.septimo,"Niñera", "Ciudad Ojeda", "50km", R.drawable.pin),
    };

    /**
     * Obtiene item basado en su identificador
     *
     * @param id identificador
     * @return Coche
     */
    public static elementoscercanos getItem(int id) {
        for (elementoscercanos item : ITEMS) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

}
