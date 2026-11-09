/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mycompany.sistemkasir;

/**
 *
 * @author Fajri Ramadhan
 */

public class TransaksiPenjualan {
    public static void prosesTransaksi(Pelanggan pelanggan, Produk produk, int jumlah) throws Exception {
        if (jumlah <= 0) {
            throw new Exception("Jumlah barang harus lebih dari 0!");
        }
        if (jumlah > produk.getStok()) {
            throw new Exception("Stok tidak mencukupi! Stok tersedia: " + produk.getStok());
        }

        int total = produk.getHarga() * jumlah;
        produk.setStok(produk.getStok() - jumlah);

        System.out.println("\n==================================");
        System.out.println("        STRUK TRANSAKSI           ");
        System.out.println("==================================");
        System.out.println("ID Pelanggan  : " + pelanggan.getIdPelanggan());
        System.out.println("Nama Pelanggan: " + pelanggan.getNamaPelanggan());
        System.out.println("Item Dibeli   : " + produk.getNama());
        System.out.println("Harga Satuan  : Rp " + produk.getHarga());
        System.out.println("Jumlah Beli   : " + jumlah);
        System.out.println("Total Bayar   : Rp " + total);
        System.out.println("Sisa Stok     : " + produk.getStok());
        System.out.println("Status        : Transaksi Berhasil!");
        System.out.println("==================================");
    }
}
