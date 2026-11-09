/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mycompany.sistemkasir;

/**
 *
 * @author Fajri Ramadhan
 */

public class MesinKasir {
    private String kodeMesin;

    public MesinKasir(String kodeMesin) {
        this.kodeMesin = kodeMesin;
    }

    public void nyalakanMesin() {
        System.out.println("[KOMPOSISI] Mesin Kasir (" + kodeMesin + ") diinisialisasi & siap digunakan.");
    }

    public String getKodeMesin() {
        return kodeMesin;
    }
}
