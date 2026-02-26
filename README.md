# Two-Phase Commit (2PC)
* Link truy cập vào H2 Database: http://localhost:10002/distributed-transaction/h2-console

# Tổng quan hệ thống
* Hệ thống cho phép nạp tiền vào tài khoản với mỗi lần nạp không quá 1.000.000 đồng. Khi nạp tiền thành công hệ thống sẽ ghi log giao dịch với trạng thái 'SUCCESS' và 'FAILED' khi nạp tiền thất bại.
