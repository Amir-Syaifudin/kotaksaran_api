package id.ac.stis.kotaksaran; // <-- Ini adalah root package Anda (SUDAH BENAR)

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * PENTING: File ini HARUS berada di root package 'id.ac.stis.kotaksaran'
 * agar @SpringBootApplication bisa memindai (scan) semua sub-package
 * (seperti repository, service, security, dll).
 *
 * PENDEKATAN BARU:
 * Kita MENGHAPUS anotasi @EnableJpaRepositories dan @EntityScan.
 * Dengan struktur folder yang sudah 100% benar (terbukti dari image_587097.png),
 * anotasi @SpringBootApplication SAJA SUDAH CUKUP untuk menemukan semuanya
 * secara otomatis. Anotasi eksplisit mungkin malah berkonflik.
 */
@SpringBootApplication
// HAPUS ANOTASI: @EnableJpaRepositories(basePackages = "id.ac.stis.kotaksaran.repository")
// HAPUS ANOTASI: @EntityScan(basePackages = "id.ac.stis.kotaksaran.entity")
public class KotaksaranApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(KotaksaranApiApplication.class, args);
    }
}

