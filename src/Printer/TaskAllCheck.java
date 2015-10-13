package Printer;

import java.sql.*;

public class TaskAllCheck {
	private final long timeInterval = 30 * 1000;
	private String sql = "SELECT * FROM printerdemo";
	private String url = "jdbc:mysql://localhost:3306/javademo?user=root&password=&useUnicode=true&characterEncoding=UTF8";
	private Connection connCheck = null;
	private boolean flag = true;

	public void Check() {
		// 创建runnable对象
		Runnable runnable = new Runnable() {
			public void run() {
				while (true == flag) {
					try {
						Class.forName("com.mysql.jdbc.Driver");
						connCheck = DriverManager.getConnection(url);
						Statement stmt = connCheck.createStatement();
						ResultSet rs = stmt.executeQuery(sql);
						System.out.println("---System task to scan all printers---");
						System.out.println("PrinterID\tPrintName\tPrintType\tUserInfo\tTaskInfo\tSpeed");
						while (rs.next()) {
							System.out.println(rs.getInt(1) + "       \t"
									+ rs.getString(2) + "        \t"
									+ rs.getString(3) + "        \t"
									+ rs.getString(4) + "        \t"
									+ rs.getString(5) + "        \t"
									+ rs.getString(6));
						}
						System.out.println("------END------");
					} catch (SQLException e) {
						System.out.println("MySQL操作错误");
						e.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						try {
							connCheck.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					try {
						Thread.sleep(timeInterval);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};

		// 启动定时任务
		Thread thread = new Thread(runnable);
		thread.start();
	}

	public void SetFlag(boolean InputFlag) {
		flag = InputFlag;
	}
}
