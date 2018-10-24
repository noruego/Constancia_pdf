/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1.models;

/**
 *
 * @author adrian
 */
public class Materia {
    private int id ;
    private String  rfc_;
    private String  profesor ;
    private String  cve_materia;
    private String  materia;
    private String  cve_carrera;
    private String  carrera;
    private String  nivel;
    private int     no_alumnos;
    private String  periodo;
    private int     anio;

    public Materia(int id, String rfc_, String profesor, String cve_materia, String materia, String cve_carrera,String carrera, String nivel, int no_alumnos, String periodo, int anio) {
        this.id = id;
        this.rfc_ = rfc_;
        this.profesor = profesor;
        this.cve_materia = cve_materia;
        this.materia = materia;
        this.cve_carrera = cve_carrera;
        this.carrera = carrera;
        this.nivel = nivel;
        this.no_alumnos = no_alumnos;
        this.periodo = periodo;
        this.anio = anio;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the rfc_
     */
    public String getRfc_() {
        return rfc_;
    }

    /**
     * @param rfc_ the rfc_ to set
     */
    public void setRfc_(String rfc_) {
        this.rfc_ = rfc_;
    }

    /**
     * @return the profesor
     */
    public String getProfesor() {
        return profesor;
    }

    /**
     * @param profesor the profesor to set
     */
    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    /**
     * @return the cve_materia
     */
    public String getCve_materia() {
        return cve_materia;
    }

    /**
     * @param cve_materia the cve_materia to set
     */
    public void setCve_materia(String cve_materia) {
        this.cve_materia = cve_materia;
    }

    /**
     * @return the materia
     */
    public String getMateria() {
        return materia;
    }

    /**
     * @param materia the materia to set
     */
    public void setMateria(String materia) {
        this.materia = materia;
    }

    /**
     * @return the cve_carrera
     */
    public String getCve_carrera() {
        return cve_carrera;
    }

    /**
     * @param cve_carrera the cve_carrera to set
     */
    public void setCve_carrera(String cve_carrera) {
        this.cve_carrera = cve_carrera;
    }

    /**
     * @return the nivel
     */
    public String getNivel() {
        return nivel;
    }

    /**
     * @param nivel the nivel to set
     */
    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    /**
     * @return the no_alumnos
     */
    public int getNo_alumnos() {
        return no_alumnos;
    }

    /**
     * @param no_alumnos the no_alumnos to set
     */
    public void setNo_alumnos(int no_alumnos) {
        this.no_alumnos = no_alumnos;
    }

    /**
     * @return the periodo
     */
    public String getPeriodo() {
        return periodo;
    }

    /**
     * @param periodo the periodo to set
     */
    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    /**
     * @return the anio
     */
    public int getAnio() {
        return anio;
    }

    /**
     * @param anio the anio to set
     */
    public void setAnio(int anio) {
        this.anio = anio;
    }
    
}
