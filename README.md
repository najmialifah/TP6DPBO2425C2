# 🐤 Flappy Bird Java Edition

Saya **Najmi Alifah Hilmiya** dengan **NIM 2410393** mengerjakan **Tugas Praktikum 5** dalam mata kuliah *Desain Pemrograman Berorientasi Objek* untuk keberkahan-Nya, maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan.  
**Aamiin.**

Proyek ini merupakan implementasi sederhana dari game **Flappy Bird** menggunakan **Java Swing**.  
Game dikembangkan untuk tugas praktikum dengan penambahan beberapa fitur wajib, bonus, dan kreativitas tambahan.

---

##  Fitur Utama

###  Spesifikasi Wajib
1. **Game Over**  
   Permainan otomatis berhenti saat burung menabrak pipa atau jatuh ke bawah layar (`JFrame`).
2. **Restart Game (Tombol R)**  
   Setelah *Game Over*, tekan **R** pada keyboard untuk memulai ulang permainan.
3. **Skor (JLabel)**  
   Terdapat label skor di pojok kiri atas yang bertambah **+1** setiap kali burung berhasil melewati sepasang pipa.
4. **Kontrol Game**
   - Tekan **Spasi (SPACE)** untuk membuat burung melompat.  
   - Burung baru akan jatuh setelah **lompatan pertama** (ada waktu siap di awal).

---

###  Spesifikasi Bonus (+20)
Terdapat **menu awal (main menu GUI)** sebelum game dimulai.  
Menu terdiri dari dua tombol:
-  **Play** → Memulai permainan Flappy Bird  
-  **Exit** → Menutup program  

Background menu menggunakan gambar `assets/background.png`.

---

### Spesifikasi Kreatif
- Game memiliki **delay awal**: sebelum tombol SPACE ditekan, burung diam di tengah dan pipa sudah berjalan di background.  
- Teks **"GET READY!"** muncul sebelum game dimulai.  
- Teks **"GAME OVER"** dan **"Press R to Restart"** muncul setelah kalah.  
- Semua gambar dan animasi digambar menggunakan `Graphics` dari Java Swing.

---

## Cara Menjalankan Program
Gunakan kontrol berikut:

SPACE → Melompat

R → Restart setelah Game Over

Exit → Menutup game dari menu awal

## Mekanisme Game
Burung memiliki gravitasi konstan (gravity = 1).
Setiap kali tombol SPACE ditekan, kecepatan vertikal (velocityY) berubah ke arah atas.
Pipa bergerak ke kiri dengan kecepatan tetap (pipeVelocityX = -2).
Skor bertambah setiap kali burung melewati satu set pipa tanpa menabrak.
Jika burung menyentuh pipa atau jatuh ke bawah, permainan berhenti dan menampilkan pesan Game Over.

## Dokumentasi
Menu Utama

<img width="431" height="790" alt="image" src="https://github.com/user-attachments/assets/c48bc025-4d99-443f-ba80-b3d5b233e4ff" />

GamePlay

<img width="432" height="791" alt="image" src="https://github.com/user-attachments/assets/48226acd-3c85-4fbd-b7e9-00971b21683f" />

GameOver

<img width="431" height="788" alt="image" src="https://github.com/user-attachments/assets/90e2f47f-4d7f-4a1a-951a-2c86a4006ddd" />

