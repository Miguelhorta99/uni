/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.t2;

import java.util.List;
import javax.persistence.*;

/**
 *
 * @author miguel
 */
@Entity
@Table(name = "anuncios_table")
public class Anuncio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer anuncioID;

    @Column
    private String anunciante, zona, estado, detalhesAnuncio, tipo, genero, dataAnunc;

    @Column
    private Integer contacto, id, preco;

    public Anuncio() {
    }

    public Anuncio(String anunciante1, String zona1, String estado1, String detalhes, String tipo1, String genero1, String date1, Integer contacto1, Integer id1, Integer preco1) {
        this.anunciante = anunciante1;
        this.zona = zona1;
        this.estado = estado1;
        this.detalhesAnuncio = detalhes;
        this.tipo = tipo1;
        this.genero = genero1;
        this.dataAnunc = date1;
        this.contacto = contacto1;
        this.id = id1;
        this.preco = preco1;

    }

    public void setAnunciante(String anun) {
        this.anunciante = anun;
    }

    public void setZona(String zone) {
        this.zona = zone;
    }

    public void setEstado(String state) {
        this.estado = state;
    }

    public void setDetalhes(String det) {
        this.detalhesAnuncio = det;
    }

    public void setTipo(String type) {
        this.tipo = type;
    }

    public void setGenero(String genre) {
        this.genero = genre;
    }

    public void setData(String data) {
        this.dataAnunc = data;
    }

    public void setContacto(Integer contact) {
        this.contacto = contact;
    }

    public void setId(Integer ids) {
        this.id = ids;
    }

    public void setPreco(Integer price) {
        this.preco = price;
    }

    public Integer getAnuncioID() {
        return anuncioID;
    }

    public String getAnunciante() {
        return anunciante;
    }

    public String getZona() {
        return zona;
    }

    public String getEstado() {
        return estado;
    }
    
    public String getDetalhes() {
       return detalhesAnuncio;
    }

    public String getTipo() {
        return tipo;
    }

    public String getGenero() {
        return genero;
    }

    public String getData() {
        return dataAnunc;
    }

    public Integer getContacto() {
        return contacto;
    }

    public Integer getId() {
        return id;
    }

    public Integer getPreco() {
        return preco;
    }

}
