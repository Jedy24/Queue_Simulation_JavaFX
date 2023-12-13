# Queue_Simulation Using JavaFX

Project UAS Pemrograman Berbasis Objek Lanjut<br><br>
Program memiliki 4 fungsi utama yaitu menampilkan data master, melakukan input data, melakukan simulasi antrean, dan menampilkan laporan. 

### Skema Database
*customer*
* idCust varchar(10) primary key
* noUrut int(10)
* nama varchar(50)
* umur int(10)

*customerservice*
* idCustService varchar(10) primary key
* nama varchar(50)
* tgllahir DATE
* umur int(10)
* jeniskelamin varchar(10)
* tempattinggal varchar(50)

*layanan*
* nolayanan varchar(10) primary key
* idCustService varchar(10) foreign key customerservice
* tanggal DATE

*detillayanan*
* nolayanan varchar(10) foreign key layanan
* idCust varchar(10) foreign key customer
* desclayanan TEXT
-------------------------------
### Menampilkan Data Master
Pengguna dapat menjalankan program untuk menampilkan data customer dan customer service.
-------------------------------
### Input Data
Pengguna dapat menjalankan program untuk melakukan input data customer dan customer service.
-------------------------------
### Simulasi Antrean
Simulasi antrean menggunakan algoritma queue dengan data diurutkan terlebih dahulu sesuai atribut nomor urut pada customer. <br>
Secara umum data diurutkan ascending menurut nomor urut sehingga customer yang memiliki nomor yang lebih kecil akan dilayani oleh customer service terlebih dahulu.
-------------------------------
### Menampilkan Laporan
Pengguna dapat menjalankan program untuk menampilkan data laporan customer, customer service, dan layanan customer service.

