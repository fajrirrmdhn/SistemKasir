/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mycompany.sistemkasir;

/**
 *
 * @author Fajri Ramadhan
 */
public abstract class Produk {
    //encapsulation
    private String namaProduk;
    private int hargaProduk;
    private int stok;
    
    public Produk(String namaProduk, int hargaProduk, int stok){
        this.namaProduk = namaProduk;
        this.hargaProduk = hargaProduk;
        this.stok = stok;
    }
    
    public String getNama(){
        return this.namaProduk;
    }
    
    public int getHarga(){
        return this.hargaProduk;
    }
    
    public int getStok(){
        return this.stok;
    }
    
    public void setNama(String namaProduk){
        this.namaProduk = namaProduk;
    }
    
    public void setHarga(int hargaProduk){
        this.hargaProduk = hargaProduk;
    }
    
    public void setStok(int stok){
        this.stok =  stok;
    }
    
    public void tampilkanInfo(){
        System.out.println("Nama Barang: " + this.namaProduk);
        System.out.println("Harga Barang: " + this.hargaProduk);
        System.out.println("Stok Barang: " + this.stok);
    }
    
    public abstract void prosesRetur();
}
