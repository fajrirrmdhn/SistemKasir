/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mycompany.sistemkasir;

/**
 *
 * @author Fajri Ramadhan
 */
public class ProdukMakanan extends Produk { //inheritance
    private String exp;
    public ProdukMakanan(String namaProduk, int hargaProduk, int stok, String exp){
        super(namaProduk, hargaProduk, stok);
        this.exp = exp;   
    }
    
    @Override
    public void tampilkanInfo(){ //method overriding
        System.out.println("Nama Barang: " + getNama());
        System.out.println("Harga Barang: " + getHarga());
        System.out.println("Stok Barang: " + getStok());
        System.out.println("Tanggal Expired: " + this.exp);
    }
    
    
}
