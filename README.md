# README - Aplikasi Manajemen Pinjaman

## Deskripsi
Aplikasi manajemen pinjaman ini dirancang untuk mengelola berbagai aspek pinjaman, termasuk pengguna, transaksi, dan data terkait. Aplikasi ini menyediakan antarmuka untuk pengelolaan admin, pelanggan, staf, jenis cicilan, jenis pinjaman, foto profil, dan peran pengguna. Dokumentasi API disediakan menggunakan Swagger UI untuk kemudahan integrasi dan pengujian.

## Entitas

### Admin
Entitas ini mewakili pengguna dengan hak akses admin dalam sistem. Atribut yang terdapat pada entitas Admin adalah:
- `id`: ID unik admin.
- `firstName`: Nama depan admin.
- `lastName`: Nama belakang admin.
- `dateOfBirth`: Tanggal lahir admin.
- `phone`: Nomor telepon admin.
- `status`: Status aktif atau nonaktif admin.
- `user`: Referensi ke informasi pengguna terkait admin.

### AppUser
Entitas ini menyimpan informasi umum tentang pengguna aplikasi. Atribut yang terdapat pada entitas AppUser adalah:
- `id`: ID unik pengguna.
- `username`: Nama pengguna.
- `password`: Kata sandi pengguna.
- `role`: Peran pengguna dalam sistem (misalnya Admin, Customer, Staff).

### Customer
Entitas ini menyimpan informasi tentang pelanggan. Atribut yang terdapat pada entitas Customer adalah:
- `id`: ID unik pelanggan.
- `firstName`: Nama depan pelanggan.
- `lastName`: Nama belakang pelanggan.
- `dateOfBirth`: Tanggal lahir pelanggan.
- `phone`: Nomor telepon pelanggan.
- `status`: Status aktif atau nonaktif pelanggan.
- `user`: Referensi ke informasi pengguna terkait pelanggan.

### InstalmentType
Entitas ini mewakili jenis cicilan yang tersedia. Atribut yang terdapat pada entitas InstalmentType adalah:
- `id`: ID unik jenis cicilan.
- `instalmentType`: Jenis cicilan (misalnya bulanan, tahunan).

### LoanTransaction
Entitas ini menyimpan informasi tentang transaksi pinjaman. Atribut yang terdapat pada entitas LoanTransaction adalah:
- `id`: ID unik transaksi pinjaman.
- `loanType`: Jenis pinjaman yang terlibat.
- `instalmentType`: Jenis cicilan untuk pinjaman tersebut.
- `customer`: Referensi ke pelanggan yang melakukan pinjaman.
- `nominal`: Jumlah pinjaman.
- `approvedAt`: Waktu persetujuan pinjaman.
- `approvedBy`: Admin yang menyetujui pinjaman.
- `approvalStatus`: Status persetujuan pinjaman.
- `loanTransactionDetails`: Daftar rincian transaksi pinjaman.

### LoanTransactionDetail
Entitas ini menyimpan rincian dari transaksi pinjaman. Atribut yang terdapat pada entitas LoanTransactionDetail adalah:
- `id`: ID unik rincian transaksi.
- `transactionDate`: Tanggal transaksi dilakukan.
- `nominal`: Jumlah transaksi.
- `loanTransaction`: Referensi ke transaksi pinjaman terkait.
- `loanStatus`: Status dari pinjaman terkait.

### LoanType
Entitas ini mewakili jenis pinjaman yang tersedia. Atribut yang terdapat pada entitas LoanType adalah:
- `id`: ID unik jenis pinjaman.
- `name`: Nama atau deskripsi dari pinjaman.
- `maxLimit`: Batas maksimum pinjaman yang dapat diberikan.

### Photo
Entitas ini menyimpan foto profil pelanggan. Atribut yang terdapat pada entitas Photo adalah:
- `id`: ID unik foto.
- `name`: Nama file foto.
- `type`: Tipe file foto (misalnya JPEG, PNG).
- `data`: Data foto dalam bentuk byte.
- `customer`: Referensi ke pelanggan yang foto profilnya disimpan.

### Role
Entitas ini mendefinisikan peran dalam sistem. Atribut yang terdapat pada entitas Role adalah:
- `id`: ID unik peran.
- `role`: Nama peran (misalnya Admin, Customer, Staff).

### Staff
Entitas ini menyimpan informasi tentang staf. Atribut yang terdapat pada entitas Staff adalah:
- `id`: ID unik staf.
- `firstName`: Nama depan staf.
- `lastName`: Nama belakang staf.
- `dateOfBirth`: Tanggal lahir staf.
- `phone`: Nomor telepon staf.
- `status`: Status aktif atau nonaktif staf.
- `user`: Referensi ke informasi pengguna terkait staf.

## DTO (Data Transfer Object)

DTO digunakan untuk mentransfer data antara lapisan aplikasi dan API. Ada dua jenis DTO: Request dan Response.

### Request DTO

- **AdminRequest**
    - `firstName`: Nama depan admin.
    - `lastName`: Nama belakang admin.
    - `dateOfBirth`: Tanggal lahir admin.
    - `phone`: Nomor telepon admin.
    - `status`: Status aktif/nonaktif admin.

- **CustomerRequest**
    - `firstName`: Nama depan pelanggan.
    - `lastName`: Nama belakang pelanggan.
    - `dateOfBirth`: Tanggal lahir pelanggan.
    - `phone`: Nomor telepon pelanggan.
    - `status`: Status aktif/nonaktif pelanggan.

- **StaffRequest**
    - `firstName`: Nama depan staf.
    - `lastName`: Nama belakang staf.
    - `dateOfBirth`: Tanggal lahir staf.
    - `phone`: Nomor telepon staf.
    - `status`: Status aktif/nonaktif staf.
    - `userId`: ID pengguna terkait staf.

- **InstalmentTypeRequest**
    - `instalmentType`: Jenis cicilan.

- **AuthRequest**
    - `username`: Nama pengguna.
    - `password`: Kata sandi.

### Response DTO

- **AdminResponse**
    - `id`: ID unik admin.
    - `firstName`: Nama depan admin.
    - `lastName`: Nama belakang admin.
    - `dateOfBirth`: Tanggal lahir admin.
    - `phone`: Nomor telepon admin.
    - `status`: Status aktif/nonaktif admin.
    - `user`: Informasi pengguna terkait admin.

- **CustomerResponse**
    - `id`: ID unik pelanggan.
    - `firstName`: Nama depan pelanggan.
    - `lastName`: Nama belakang pelanggan.
    - `dateOfBirth`: Tanggal lahir pelanggan.
    - `phone`: Nomor telepon pelanggan.
    - `status`: Status aktif/nonaktif pelanggan.
    - `user`: Referensi pengguna.

- **StaffResponse**
    - `id`: ID unik staf.
    - `firstName`: Nama depan staf.
    - `lastName`: Nama belakang staf.
    - `dateOfBirth`: Tanggal lahir staf.
    - `phone`: Nomor telepon staf.
    - `status`: Status aktif/nonaktif staf.
    - `user`: Referensi pengguna.

- **InstalmentTypeResponse**
    - `id`: ID unik jenis cicilan.
    - `instalmentType`: Jenis cicilan.

- **LoanTransactionResponse**
    - `id`: ID unik transaksi.
    - `loanType`: Jenis pinjaman.
    - `instalmentType`: Jenis cicilan.
    - `customer`: Referensi pelanggan.
    - `nominal`: Jumlah pinjaman.
    - `approvedAt`: Waktu persetujuan.
    - `approvedBy`: Admin yang menyetujui.
    - `approvalStatus`: Status persetujuan.
    - `loanTransactionDetails`: Daftar rincian transaksi pinjaman.

- **LoanTransactionDetailResponse**
    - `id`: ID unik rincian transaksi.
    - `transactionDate`: Tanggal transaksi.
    - `nominal`: Jumlah transaksi.
    - `loanTransaction`: Referensi transaksi pinjaman.
    - `loanStatus`: Status pinjaman.

- **PhotoResponse**
    - `id`: ID unik foto.
    - `name`: Nama file foto.
    - `type`: Tipe foto.
    - `data`: Data foto dalam bentuk byte.
    - `customer`: Referensi pelanggan.

- **RoleResponse**
    - `id`: ID unik peran.
    - `role`: Nama peran.

- **AuthResponse**
    - `accessToken`: Token akses JWT.
    - `tokenType`: Jenis token (Bearer).

## Layanan (Service)

Layanan berfungsi untuk mengelola logika bisnis dan interaksi dengan entitas. Berikut adalah layanan utama:

### AdminService
- `addAdmin(AdminRequest request)`: Membuat admin baru berdasarkan permintaan.
- `getAllAdmins()`: Mengambil daftar semua admin yang terdaftar.
- `getAdminByFirstName(String firstName)`: Mengambil admin berdasarkan nama depan.

### CustomerService
- `createCustomer(CustomerRequest request)`: Membuat pelanggan baru berdasarkan permintaan.
- `getAllCustomers()`: Mengambil daftar semua pelanggan.
- `getCustomerById(String id)`: Mengambil data pelanggan berdasarkan ID.
- `updateCustomer(String id, CustomerRequest request)`: Memperbarui data pelanggan.
- `deleteCustomer(String id)`: Menghapus pelanggan berdasarkan ID.

### StaffService
- `createStaff(StaffRequest request)`: Membuat staf baru berdasarkan permintaan.
- `getAllStaffs()`: Mengambil daftar semua staf yang terdaftar.

### InstalmentTypeService
- `createInstalmentType(InstalmentTypeRequest request)`: Membuat jenis cicilan baru.

### LoanTransactionService
- `createLoanTransaction(LoanTransactionRequest request)`: Membuat transaksi pinjaman baru berdasarkan permintaan.
- `getLoanTransactionById(String id)`: Mengambil data transaksi pinjaman berdasarkan ID.
- `getAllLoanTransactions()`: Mengambil daftar semua transaksi pinjaman.

### LoanTransactionDetailService
- `createLoanTransactionDetail(LoanTransactionDetailRequest request)`: Membuat rincian transaksi pinjaman baru.
- `getLoanTransactionDetailById(String id)`: Mengambil rincian transaksi pinjaman berdasarkan ID.
- `getAllLoanTransactionDetails()`: Mengambil daftar semua rincian transaksi pinjaman.

### PhotoService
- `uploadProfile(MultipartFile file, String id)`: Mengunggah foto profil pelanggan.
- `getProfile(String id)`: Mengambil foto profil pelanggan.

### RoleService
- `createRole(RoleRequest request)`: Membuat peran baru berdasarkan permintaan.
- `getRoleById(String id)`: Mengambil data peran berdasarkan ID.
- `getAllRoles()`: Mengambil daftar semua peran.

### AuthService
- `login(AuthRequest request)`: Mengautentikasi pengguna dan memberikan token JWT.
- `getUser(String id)`: Mengambil data pengguna berdasarkan ID.

## Pengontrol (Controller)

Pengontrol bertanggung jawab untuk menangani permintaan HTTP dan memberikan respons. Berikut adalah pengontrol utama:

### AdminController
- `createAdmin(AdminRequest request)`: Membuat admin baru.
- `getAllAdmins()`: Mengambil daftar semua admin.
- `getAdminByFirstName(String firstName)`: Mengambil admin berdasarkan nama depan.

### CustomerController
- `createCustomer(CustomerRequest request)`: Membuat pelanggan baru.
- `getAllCustomers()`: Mengambil daftar semua pelanggan.
- `getCustomerById(String id)`: Mengambil data pelanggan berdasarkan ID.
- `updateCustomer(String id, CustomerRequest request)`: Memperbarui data pelanggan.
- `deleteCustomer(String id)`: Menghapus pelanggan.
- `uploadProfile(MultipartFile file, String id)`: Mengunggah foto profil pelanggan.
- `getProfile(String id)`: Mengambil foto profil pelanggan.

### InstalmentTypeController
- `createInstalmentType(InstalmentTypeRequest request)`: Membuat jenis cicilan baru.

### LoanTransactionController
- `createLoanTransaction(LoanTransactionRequest request)`: Membuat transaksi pinjaman baru.
- `getLoanTransactionById(String id)`: Mengambil transaksi pinjaman berdasarkan ID.
- `getAllLoanTransactions()`: Mengambil daftar semua transaksi pinjaman.

### LoanTransactionDetailController
- `createLoanTransactionDetail(LoanTransactionDetailRequest request)`: Membuat rincian transaksi pinjaman baru.
- `getLoanTransactionDetailById(String id)`: Mengambil rincian transaksi pinjaman berdasarkan ID.
- `getAllLoanTransactionDetails()`: Mengambil daftar semua rincian transaksi pinjaman.

### PhotoController
- `uploadPhotoCustomerById(String id, MultipartFile file)`: Mengunggah foto profil pelanggan.
- `getPhotoCustomerById(String id)`: Mengambil foto profil pelanggan.

### RoleController
- `createRole(RoleRequest request)`: Membuat peran baru.
- `getRoleById(String id)`: Mengambil data peran berdasarkan ID.
- `getAllRoles()`: Mengambil daftar semua peran.

### SignInController
- `login(AuthRequest request)`: Mengautentikasi pengguna dan memberikan token JWT.
- `getUser(String id)`: Mengambil data pengguna berdasarkan ID.

### SignUpController
- `createCustomer(CustomerRequest request)`: Membuat pelanggan baru.
- `createStaff(StaffRequest request)`: Membuat staf baru.

### StaffController
- `getAllStaffs()`: Mengambil daftar semua staf.

## Konfigurasi Swagger UI

Swagger UI digunakan untuk dokumentasi API yang interaktif. Konfigurasi berikut mengaktifkan Swagger UI dan mendefinisikan skema keamanan untuk autentikasi bearer JWT.

### Endpoint Swagger UI
- `/swagger-ui.html`: Antarmuka pengguna Swagger.
- `/docs/openapi`: Dokumentasi API dalam format JSON.

### Konfigurasi
```java
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
@OpenAPIDefinition(
        info = @io.swagger.v3.oas.annotations.info.Info(title = "AbanKu Jago Loan API", version = "1.0.0", description = "AbanKu Jago Loan API Documentation"),
        security = @SecurityRequirement(name = "bearerAuth")
)
@Configuration
public class SwiggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.enigmacamp.loanapp.controller"))
                .paths(PathSelectors.any())
                .build();
    }
}
