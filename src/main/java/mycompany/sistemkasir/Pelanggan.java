/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mycompany.sistemkasir;

/**
 *
 * @author Fajri Ramadhan
 */

public class Pelanggan {
    private String idPelanggan;
    private String namaPelanggan;

    public Pelanggan(String idPelanggan, String namaPelanggan) {
        this.idPelanggan = idPelanggan;
        this.namaPelanggan = namaPelanggan;
    }

    public String getIdPelanggan() {
        return idPelanggan;
    }

    public String getNamaPelanggan() {
        return namaPelanggan;
    }
}