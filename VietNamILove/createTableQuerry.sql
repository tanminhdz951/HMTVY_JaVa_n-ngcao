create database QuanLyKhoHang
go
use QuanLyKhoHang
go
-- tblSanPham
create table tblSanPham
(
	maSP char(7),
	tenSP nvarchar(50) not null,
	donGia money
		check (donGia >= 0),
	soLuongCon int
		check (soLuongCon >= 0),
	constraint PK_SanPham_maSP primary key (maSP)
)
go

create function fnAuto_maSP ()
returns varchar(7)
begin
	declare @id varchar(7)
	if(select COUNT(maSP) from tblSanPham) = 0
		set @id = '0'
	else
		select @id = MAX(RIGHT(maSP, 5)) from tblSanPham
	set @id = 'SP' + format(CONVERT(int, @id) + 1, 'D5')
	return @id
end
go

alter table tblSanPham
	add constraint DF_SanPham_maSP default dbo.fnAuto_maSP() for maSP with values
go

alter table tblSanPham
	add constraint DF_SanPham_donGia default 0 for donGia with values
go

alter table tblSanPham
	add constraint DF_SanPham_soLuongCon default 0 for soLuongCon with values
go

-- tblNhanVien
create table tblNhanVien
(
	maNV char(7),
	tenNV nvarchar(50), 
	soDienThoai char(10) not null
		check (soDienThoai like '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]'),
	diaChi nvarchar(100) not null,
	matKhau char(6) not null,
	constraint PK_NhanVien_maNV primary key (maNV)
)
go

create function fnAuto_maNV ()
returns varchar(7)
begin
	declare @id varchar(7)
	if(select COUNT(maNV) from tblNhanVien) = 0
		set @id = '0'
	else
		select @id = MAX(RIGHT(maNV, 5)) from tblNhanVien
	set @id = 'NV' + format(CONVERT(int, @id) + 1, 'D5')
	return @id
end
go

alter table tblNhanVien
	add constraint DF_NhanVien_maNV default dbo.fnAuto_maNV() for maNV with values
go

-- tblKhachHang
create table tblKhachHang
(
	maKH char(7),
	tenKH nvarchar(50),
	soDienThoai char(10) not null
		check (soDienThoai like '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]'),
	diaChi nvarchar(100) not null,
	constraint PK_KhachHang_maKH primary key (maKH)
)
go

create function fnAuto_maKH ()
returns varchar(7)
begin
	declare @id varchar(7)
	if(select COUNT(maKH) from tblKhachHang) = 0
		set @id = '0'
	else
		select @id = MAX(RIGHT(maKH, 5)) from tblKhachHang
	set @id = 'KH' + format(CONVERT(int, @id) + 1, 'D5')
	return @id
end
go

alter table tblKhachHang
	add constraint DF_KhachHang_maKH default dbo.fnAuto_maKH() for maKH with values
go

-- tblPhieuNhap
create table tblPhieuNhap
(
	maPN char(7),
	ngayNhap date default getDate(),
	maNV1 char(7),
	maNV2 char(7),
	constraint PK_PhieuNhap_maPN primary key (maPN),
	constraint FK_PhieuNhap_maNV1 
		foreign key (maNV1) references tblNhanVien (maNV)
		on delete cascade
		on update cascade,
	constraint FK_PhieuNhap_maNV2
		foreign key (maNV2) references tblNhanVien (maNV),
		--on delete cascade
		--on update cascade,
	constraint CK_PhieuNhap_maNV
		check (maNV1 <> maNV2)
)
go

create function fnAuto_maPN ()
returns varchar(7)
begin
	declare @id varchar(7)
	if(select COUNT(maPN) from tblPhieuNhap) = 0
		set @id = '0'
	else
		select @id = MAX(RIGHT(maPN, 5)) from tblPhieuNhap
	set @id = 'PN' + format(CONVERT(int, @id) + 1, 'D5')
	return @id
end
go

alter table tblPhieuNhap
	add constraint DF_PhieuNhap_maPN default dbo.fnAuto_maPN() for maPN with values
go

-- tblChiTietPhieuNhap
create table tblChiTietPhieuNhap
(
	maPN char(7),
	maSP char(7),
	soLuong int
		check (soLuong > 0),
	giaTien money
		check (giaTien > 0),
	constraint PK_ChiTietPhieuNhap primary key (maPN, maSP),
	constraint FK_ChiTietPhieuNhap_maPN
		foreign key (maPN) references tblPhieuNhap (maPN)
		on delete cascade
		on update cascade,
	constraint FK_ChiTietPhieuNhap_maSP
		foreign key (maSP) references tblSanPham (maSP)
		on delete cascade
		on update cascade,
)
go

-- tblPhieuXuat
create table tblPhieuXuat
(
	maPX char(7),
	ngayXuat date default getDate(),
	lyDo nvarchar(100) not null,
	maNV char(7) not null,
	maKH char(7) not null,
	diaChi nvarchar(100),
	constraint PK_PhieuXuat_maPX primary key (maPX),
	constraint FK_PhieuXuat_maNV
		foreign key (maNV) references tblNhanVien (maNV)
		on delete cascade
		on update cascade,
	constraint FK_PhieuXuat_maKH
		foreign key (maKH) references tblKhachHang (maKh)
		on delete cascade
		on update cascade,
)
go

create function fnAuto_maPX ()
returns varchar(7)
begin
	declare @id varchar(7)
	if(select COUNT(maPX) from tblPhieuXuat) = 0
		set @id = '0'
	else
		select @id = MAX(RIGHT(maPX, 5)) from tblPhieuXuat
	set @id = 'PX' + format(CONVERT(int, @id) + 1, 'D5')
	return @id
end
go

alter table tblPhieuXuat
	add constraint DF_PhieuXuat_maPX default dbo.fnAuto_maPX() for maPX with values
go

-- tblChiTietPhieuXuat
create table tblChiTietPhieuXuat
(
	maPX char(7),
	maSP char(7),
	soLuong int
		check (soLuong > 0),
	giaTien money
		check (giaTien > 0),
	constraint PK_ChiTietPhieuXuat primary key (maPX, maSP),
	constraint FK_ChiTietPhieuXuat_maPX 
		foreign key (maPX) references tblPhieuXuat (maPX)
		on delete cascade
		on update cascade,
	constraint FK_ChiTietPhieuXuat_maSP
		foreign key (maSP) references tblSanPham (maSP)
		on delete cascade
		on update cascade,
)
go