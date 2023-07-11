# Tài liệu hướng dẫn sử dụng Student Management Web

## *Thông tin sinh viên*
        Họ và tên: Nguyễn Đức Việt
        MSSV: 20120401

## *Giới thiệu*
Student Management Web là một ứng dụng web quản lý thông tin sinh viên và lớp học. Ứng dụng được viết bằng Java Servlet, JSP và JDBC để tương tác với cơ sở dữ liệu.

# Cài đặt và triển khai
Để sử dụng ứng dụng Student Management Web, bạn cần thực hiện các bước sau:
1. Tải xuống và cài đặt Apache Tomcat: https://tomcat.apache.org/
2. Tải xuống và cài đặt Microsoft SQL Server: https://www.microsoft.com/en-us/sql-server
3. Import cơ sở dữ liệu được lưu trữ trong tệp db/database.sql vào Microsoft SQL Server.
4. Cấu hình cơ sở dữ liệu trong tệp src/main/hcmus/viet/studentmgmtweb/helper/DatabaseHelper.java.
5. Chạy lệnh `mvn package` để build ứng dụng.
6. Sao chép tệp target/StudentMgmtWeb.war vào thư mục webapps của Tomcat.
7. Khởi động Tomcat và truy cập vào ứng dụng Student Management Web từ trình duyệt với địa chỉ http://localhost:8080/StudentMgmtWeb.


# Sử dụng ứng dụng
#### Quản lý danh sách sinh viên
+ Để xem danh sách sinh viên, truy cập vào trang http://localhost:8080/StudentMgmtWeb/student.
+ Để thêm sinh viên mới, nhấp vào nút "Thêm sinh viên" và điền thông tin vào biểu mẫu.
+ Để sửa đổi thông tin sinh viên, nhấp vào nút "Chỉnh sửa" ở cột "Thao tác" tương ứng với sinh viên muốn sửa đổi.
+ Để xóa sinh viên, nhấp vào nút "Xóa" ở cột "Thao tác" tương ứng với sinh viên muốn xóa.
+ Để sắp xếp danh sách sinh viên theo tên, nhấp vào tiêu đề cột "Tên".
+ Để sắp xếp danh sách sinh viên theo điểm, nhấp vào tiêu đề cột "Điểm".
+ Để tìm kiếm sinh viên bằng tên, nhập tên sinh viên vào ô tìm kiếm và nhấn nút "Tìm kiếm".


#### Quản lý danh sách lớp học
+ Để xem danh sách lớp học, truy cập vào trang http://localhost:8080/StudentMgmtWeb/course.
+ Để thêm lớp học mới, nhấp vào nút "Thêm lớp học" và điền thông tin vào biểu mẫu.
+ Để sửa đổi thông tin lớp học, nhấp vào nút "Chỉnh sửa" ở cột "Thao tác" tương ứng với lớp học muốn sửa đổi.
+ Để xóa lớp học, nhấp vào nút "Xóa" ở cột "Thao tác" tương ứng với lớp học muốn xóa.
+ Để sắp xếp danh sách lớp học theo tên, nhấp vào tiêu đề cột "Tên".
+ Để tìm kiếm lớp học bằng tên, nhập tên lớp học vào ô tìm kiếm và nhấn nút "Tìm kiếm".
+ Để xem danh sách sinh viên của một lớp học, nhấp vào nút "Xem danh sách sinh viên" ở cột "Thao tác" tương ứng với lớp học muốn xem.
+ Để thêm sinh viên vào lớp học, nhấp vào nút "Chi tiết" ở cột "Thao tác" tương ứng với lớp học muốn thêm sinh viên vào và chọn sinh viên từ danh sách.
+ Để xóa sinh viên khỏi lớp học, ở trang "Chi tiết" lớp học nhấp vào nút "Xóa" tương ứng với sinh viên muốn xóa khỏi lớp học.

#### Xem danh sách lớp học và bảng điểm của sinh viên trong năm

+ Để xem danh sách lớp học và bảng điểm của một sinh viên trong năm, ở trang danh sách sinh viên, chọn "Chi tiết", sau đó chọn năm học.

## Kết luận

Student Management Web là một ứng dụng quản lý sinh viên và lớp học đơn giản nhưng hữu ích. Bằng cách sử dụng Java Servlet, JSP và JDBC, ứng dụng cung cấp một cách tiếp cận dễ dàng cho người dùng để quản lý thông tin sinh viên và lớp học.
