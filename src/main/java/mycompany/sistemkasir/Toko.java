/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mycompany.sistemkasir;

/**
 *
 * @author Fajri Ramadhan
 */

import java.util.ArrayList;
import java.util.HashMap;

public class Toko {
    private String namaToko;

    private MesinKasir mesinKasirUtama;

    private ArrayList<Produk> daftarProduk;
    private HashMap<String, Pelanggan> dataPelanggan;

    public Toko(String namaToko) {
        this.namaToko = namaToko;
        this.mesinKasirUtama = new MesinKasir("POS-01");
        this.daftarProduk = new ArrayList<>();
        this.dataPelanggan = new HashMap<>();
    }

    public void bukaToko() {
        System.out.println("\n*** Menginisialisasi Sistem Kasir " + namaToko + " ***");
        mesinKasirUtama.nyalakanMesin();
    }

    public void tambahProduk(Produk p) {
        daftarProduk.add(p);
        System.out.println("Sukses! Produk '" + p.getNama() + "' berhasil ditambahkan ke toko.");
    }

    public void lihatSemuaProduk() {
        if (daftarProduk.isEmpty()) {
            System.out.println("Belum ada produk yang tersimpan.");
            return;
        }
        for (int i = 0; i < daftarProduk.size(); i++) {
            System.out.println("\n[ No. " + (i + 1) + " ]");
            daftarProduk.get(i).tampilkanInfo();
        }
    }

    public Produk cariProduk(String namaProduk) throws ProdukTidakDitemukanException {
        for (Produk p : daftarProduk) {
            if (p.getNama().equalsIgnoreCase(namaProduk)) {
                return p;
            }
        }
        throw new ProdukTidakDitemukanException("Gagal: Produk '" + namaProduk + "' tidak terdaftar!");
    }

    public Produk getProdukInt(int index) {
        return daftarProduk.get(index);
    }

    public int getJumlahProduk() {
        return daftarProduk.size();
    }

    public void tambahPelanggan(Pelanggan p) {
        dataPelanggan.put(p.getIdPelanggan(), p);
        System.out.println("Berhasil menambahkan pelanggan baru!");
    }

    public void cariPelanggan(String id) {
        if (dataPelanggan.containsKey(id)) {
            Pelanggan p = dataPelanggan.get(id);
            System.out.println("Ditemukan! ID: " + p.getIdPelanggan() + " | Nama: " + p.getNamaPelanggan());
        } else {
            System.out.println("Pelanggan dengan ID '" + id + "' tidak terdaftar.");
        }
    }

    public void lihatSemuaPelanggan() {
        System.out.println("Total Pelanggan Terdaftar: " + dataPelanggan.size());
        for (Pelanggan p : dataPelanggan.values()) {
            System.out.println("- ID: " + p.getIdPelanggan() + " | Nama: " + p.getNamaPelanggan());
        }
    }

    public Pelanggan getPelanggan(String id) {
        return dataPelanggan.get(id);
    }
}
