package Printer;

import java.sql.*;

public class RandomNum {
	private static boolean flag = true;
	private long timeInterval = 10000;
	private int PrinterCount = 0;
	private Connection conn5 = null;
	private Statement stmt;
	private ResultSet rs;
	private String sql;
	private String url = "jdbc:mysql://localhost:3306/javademo?user=root&password=&useUnicode=true&characterEncoding=UTF8";
	private int randomNum;
	private int randomPrinter;

	public static void SetRandomFlag(boolean InputFlag) {
		flag = InputFlag;
	}

	public void GetRandomNum() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn5 = DriverManager.getConnection(url);
			stmt = conn5.createStatement();
			sql = "SELECT * FROM printerdemo";
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				PrinterCount++;
			}
		} catch (SQLException e) {
			System.out.println("MySQL操作错误");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn5.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// 创建runnable对象
		Runnable runnable = new Runnable() {// 匿名类是不能有名称的类，所以没办法引用它们。必须在创建时，作为new语句的一部分来声明它们。
			// 这就要采用另一种形式的new语句，如下所示：
			// new
			// <类或接口> <类的主体>
			// 这种形式的new语句声明一个新的匿名类，它对一个给定的类进行扩展，或者实现一个给定的接口。它还创建那个类的一个新实例，并把它作为语句的结果而返回。要扩展的类和要实现的接口是new语句的操作数，后跟匿名类的主体


			public void run() {
				while (true == flag) {
					randomNum = (int) (Math.random() * 10);
					randomPrinter = (int) (((Math.random() * 10) % PrinterCount) + 1);
					System.out.println("randomNum:" + randomNum + ",randomPrinter:" + randomPrinter);

					try {
						Class.forName("com.mysql.jdbc.Driver");
						conn5 = DriverManager.getConnection(url);
						// 将产生的随机数发送给随机产生的打印机，实际是写入数据库
						sql = "UPDATE printerdemo SET taskinfo = '"
								+ randomNum + "' WHERE printid = '"
								+ randomPrinter + "'";
						stmt = conn5.createStatement();
						if (-1 != stmt.executeUpdate(sql)) {
							System.out.println("produce a random number:" + randomNum);
						}
					} catch (SQLException e) {
						System.out.println("MySQL操作错误");
						e.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						try {
							conn5.close();
						} catch (SQLException e) {
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
}
