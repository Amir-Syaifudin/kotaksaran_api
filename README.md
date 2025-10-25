API Kotak Saran Polstat STIS

Ini adalah web service backend untuk aplikasi Kotak Saran Mahasiswa, yang dibangun sebagai bagian dari Ujian Tengah Semester mata kuliah Pemrograman Platform Khusus (PPK).

Layanan ini menyediakan RESTful API yang aman untuk mengelola alur kerja saran, mulai dari pengajuan oleh mahasiswa hingga tanggapan oleh admin, dengan menggunakan autentikasi berbasis JWT.

Fitur Utama

Autentikasi & Otorisasi: Registrasi dan Login berbasis JWT (Spring Security).

Berbasis Peran (RBAC): Peran ROLE_MAHASISWA (hanya bisa mengirim dan melihat saran sendiri) dan ROLE_ADMIN (bisa melihat semua saran dan memberi tanggapan).

Alur Kerja Saran: Status saran yang jelas (MASUK, DIPROSES, SELESAI).

Akuntabilitas: Sistem mencatat admin mana yang memberi tanggapan.

Anonimitas: Mahasiswa dapat memilih untuk mengirim saran secara anonim.

Kedaluwarsa Akun: Akun mahasiswa otomatis kedaluwarsa setelah 5 tahun dari tanggal pendaftaran.

Dokumentasi API: Live documentation dibuat otomatis dengan Swagger (OpenAPI).

Tumpukan Teknologi (Tech Stack)

Framework: Spring Boot 3.5.7

Bahasa: Java 21

Keamanan: Spring Security 6 (Autentikasi JWT)

Data: Spring Data JPA (Hibernate)

Database: H2 Database (In-Memory)

Dokumentasi: Springdoc OpenAPI (Swagger UI)

Build: Apache Maven

Cara Menjalankan Proyek (Lokal)

Proyek ini menggunakan Maven Wrapper, sehingga Anda tidak perlu menginstal Maven secara manual.

Prasyarat:

Pastikan Anda memiliki JDK (Java Development Kit) 21 atau yang lebih baru terinstal.

Clone Repositori:

git clone [https://github.com/amir-syaifudin/kotaksaran_api.git](https://github.com/amir-syaifudin/kotaksaran_api.git)
cd kotaksaran_api


Build Proyek (Menggunakan Maven Wrapper):

Di Windows:

.\mvnw.cmd clean install


Di macOS/Linux:

./mvnw clean install


Jalankan Aplikasi:

Di Windows:

.\mvnw.cmd spring-boot:run


Di macOS/Linux:

./mvnw spring-boot:run


Aplikasi Siap:
Aplikasi akan berjalan di http://localhost:8083.

Dokumentasi & Database

1. Dokumentasi API (Swagger UI)

Setelah aplikasi berjalan, dokumentasi API interaktif (Swagger UI) dapat diakses di:
http://localhost:8083/swagger-ui/index.html

Di halaman ini, Anda dapat menguji semua endpoint secara langsung.

2. Database (H2 Console)

Database H2 in-memory dapat diakses melalui dashboard web di:
http://localhost:8083/h2-console

Pengaturan Login H2 Console:

JDBC URL: jdbc:h2:mem:testdb

User Name: sa

Password: (biarkan kosong)

Akun Default

Saat aplikasi pertama kali berjalan, sebuah akun admin default akan dibuat oleh DatabaseSeeder:

Username (NIM): admin

Password: admin123

Role: ROLE_ADMIN

Anda dapat menggunakan akun ini untuk login ke /api/auth/login dan mendapatkan token JWT untuk menguji endpoint admin.