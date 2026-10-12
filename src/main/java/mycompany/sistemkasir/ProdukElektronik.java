/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mycompany.sistemkasir;

/**
 *
 * @author Fajri Ramadhan
 */
public class ProdukElektronik extends Produk implements Garansi { //inheritance
    private String garansi;
    public ProdukElektronik(String namaProduk, int hargaProduk, int stok, String garansi){
        super(namaProduk, hargaProduk, stok);
        this.garansi = garansi;   
    }
    
    @Override //method overriding
    public void tampilkanInfo(){
        System.out.println("Nama Barang: " + getNama());
        System.out.println("Harga Barang: " + getHarga());
        System.out.println("Stok Barang: " + getStok());
        System.out.println("Garansi: " + this.garansi);
    }
    
    @Override //abstract method
    public void prosesRetur(){
        System.out.println("Barang Diretur Karena Klaim Garansi");
    }
    
    @Override //interface
    public void klaimGaransi(){
        System.out.println("Klaim Garansi sedang Diproses");
    }
}
