# API Kotak Saran Polstat STIS

Ini adalah **web service backend** untuk aplikasi **Kotak Saran Mahasiswa**, yang dikembangkan sebagai bagian dari **Ujian Tengah Semester** mata kuliah *Pemrograman Platform Khusus (PPK)* di **Politeknik Statistika STIS**.

Layanan ini menyediakan **RESTful API** yang aman dan terstruktur untuk mengelola alur kerja saran  mulai dari pengajuan oleh mahasiswa hingga tanggapan oleh admin  dengan **autentikasi berbasis JWT**.

---

##  Fitur Utama

- **Autentikasi & Otorisasi**
  - Registrasi dan Login berbasis JWT (Spring Security).
  - Refresh token otomatis untuk sesi pengguna.

- **Berbasis Peran (RBAC)**
  - `ROLE_MAHASISWA`: hanya dapat mengirim dan melihat saran sendiri.
  - `ROLE_ADMIN`: dapat melihat semua saran dan memberikan tanggapan.

- **Alur Kerja Saran**
  - Status saran jelas dan transparan: `MASUK`, `DIPROSES`, `SELESAI`.

- **Akuntabilitas**
  - Setiap tanggapan dicatat oleh sistem, termasuk identitas admin pemberi tanggapan.

- **Anonimitas**
  - Mahasiswa dapat memilih untuk mengirim saran secara anonim.

- **Kedaluwarsa Akun**
  - Akun mahasiswa otomatis kedaluwarsa setelah 5 tahun sejak pendaftaran.

- **Dokumentasi API Otomatis**
  - Dihasilkan secara langsung menggunakan **Swagger (OpenAPI)**.

---

##  Tumpukan Teknologi (Tech Stack)

| Komponen | Teknologi |
|-----------|------------|
| **Framework** | Spring Boot 3.5.7 |
| **Bahasa** | Java 21 |
| **Keamanan** | Spring Security 6 (JWT) |
| **Database** | H2 Database (In-Memory) |
| **ORM** | Spring Data JPA (Hibernate) |
| **Dokumentasi** | Springdoc OpenAPI (Swagger UI) |
| **Build Tool** | Apache Maven |

---

##  Cara Menjalankan Proyek (Lokal)

Proyek ini menggunakan **Maven Wrapper**, sehingga Anda **tidak perlu menginstal Maven secara manual**.

### 1. Prasyarat

Pastikan Anda telah menginstal:
- **JDK 21** atau versi lebih baru
- **Git** (opsional, untuk cloning repo)

---

### 2. Clone Repositori

```bash
git clone https://github.com/amir-syaifudin/kotaksaran_api.git
cd kotaksaran_api
```

---

### 3. Build Proyek

**Windows**
```bash
.\mvnw.cmd clean install
```

**macOS/Linux**
```bash
./mvnw clean install
```

---

### 4. Jalankan Aplikasi

**Windows**
```bash
.\mvnw.cmd spring-boot:run
```

**macOS/Linux**
```bash
./mvnw spring-boot:run
```

---

### 5. Aplikasi Siap Digunakan

Aplikasi akan berjalan di:  
 [http://localhost:8083](http://localhost:8083)

---

## 📖 Dokumentasi & Database

### 1. Dokumentasi API (Swagger UI)

Akses dokumentasi interaktif di:  
 [http://localhost:8083/swagger-ui/index.html](http://localhost:8083/swagger-ui/index.html)

Anda dapat mencoba seluruh endpoint langsung dari antarmuka Swagger.

---

### 2. Database (H2 Console)

Akses database H2 di:  
 [http://localhost:8083/h2-console](http://localhost:8083/h2-console)

**Konfigurasi Login:**
```
JDBC URL : jdbc:h2:mem:testdb
User Name: sa
Password : (biarkan kosong)
```

---

##  Akun Default

Saat aplikasi pertama kali dijalankan, sistem otomatis membuat akun admin melalui `DatabaseSeeder`.

| Field | Nilai |
|--------|--------|
| **Username (NIM)** | admin |
| **Password** | admin123 |
| **Role** | ROLE_ADMIN |

Gunakan akun ini untuk login di endpoint `/api/auth/login` dan mendapatkan **token JWT** untuk mengakses endpoint admin.

---

##  Contoh Penggunaan API

Berikut beberapa contoh permintaan (request) dan tanggapan (response) JSON utama.

---

### 1. Registrasi Mahasiswa

**Endpoint:**  
`POST /api/auth/register`

**Body:**
```json
{
  "nim": "222111111",
  "nama": "Andi Pratama",
  "password": "password123",
  "angkatan": 2022
}
```

**Response:**
```json
{
  "message": "Registrasi berhasil, silakan login untuk mendapatkan token."
}
```

---

### 2. Login

**Endpoint:**  
`POST /api/auth/login`

**Body:**
```json
{
  "nim": "222111111",
  "password": "password123"
}
```

**Response:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "role": "ROLE_MAHASISWA",
  "expiresIn": 3600000
}
```

---

### 3. Mengirim Saran (Mahasiswa)

**Endpoint:**  
`POST /api/saran`

**Header:**  
`Authorization: Bearer <JWT_TOKEN>`

**Body:**
```json
{
  "judul": "Kantin Perlu Diperluas",
  "isi": "Jumlah meja sangat terbatas pada jam makan siang.",
  "anonim": true
}
```

**Response:**
```json
{
  "id": 3,
  "status": "MASUK",
  "createdAt": "2025-10-25T10:15:30",
  "anonim": true
}
```

---

### 4. Melihat Saran Sendiri (Mahasiswa)

**Endpoint:**  
`GET /api/saran/me`

**Header:**  
`Authorization: Bearer <JWT_TOKEN>`

**Response:**
```json
[
  {
    "id": 3,
    "judul": "Kantin Perlu Diperluas",
    "status": "MASUK",
    "anonim": true
  }
]
```

---

### 5. Melihat Semua Saran (Admin)

**Endpoint:**  
`GET /api/saran/all`

**Header:**  
`Authorization: Bearer <JWT_TOKEN_ADMIN>`

**Response:**
```json
[
  {
    "id": 3,
    "judul": "Kantin Perlu Diperluas",
    "status": "MASUK",
    "mahasiswa": "Anonim",
    "tanggal": "2025-10-25T10:15:30"
  }
]
```

---

### 6. Memberi Tanggapan (Admin)

**Endpoint:**  
`POST /api/saran/{id}/tanggapan`

**Header:**  
`Authorization: Bearer <JWT_TOKEN_ADMIN>`

**Body:**
```json
{
  "isiTanggapan": "Terima kasih atas sarannya, akan kami evaluasi bersama pihak kampus."
}
```

**Response:**
```json
{
  "message": "Tanggapan berhasil disimpan.",
  "status": "DIPROSES"
}
```

---

##  Pengujian JWT dengan Postman

1. Jalankan aplikasi di `http://localhost:8083`.
2. Lakukan **login** dan salin token JWT dari respons.
3. Tambahkan header berikut di setiap request:
   ```
   Authorization: Bearer <JWT_TOKEN>
   ```
4. Uji endpoint `/api/saran` untuk peran mahasiswa dan `/api/saran/all` untuk admin.

---

##  Struktur Proyek (Ringkas)

```
kotaksaran_api/
 ┣ src/
 ┃ ┣ main/
 ┃ ┃ ┣ java/id/ac/stis/kotaksaran/kotaksaran_api/
 ┃ ┃ ┃ ┣ controller/
 ┃ ┃ ┃ ┣ model/
 ┃ ┃ ┃ ┣ repository/
 ┃ ┃ ┃ ┣ security/
 ┃ ┃ ┃ ┗ service/
 ┃ ┃ ┗ resources/
 ┃ ┃    ┣ application.properties
 ┃ ┃    ┗ data.sql (opsional)
 ┣ pom.xml
 ┗ README.md
```

---

##  Lisensi

Proyek ini dikembangkan untuk **tujuan akademik** sebagai bagian dari Ujian Tengah Semester mata kuliah **Pemrograman Platform Khusus (PPK)** di **Politeknik Statistika STIS**.  
Segala bentuk distribusi ulang untuk kepentingan komersial **tidak diperkenankan**.

---

 **Dikembangkan oleh:**  
**Amir Syaifudin**  
Politeknik Statistika STIS  
Mata Kuliah: Pemrograman Platform Khusus (PPK)  
Tahun: 2025
