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
			System.out.println("MySQL��������");
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

		// ����runnable����
		Runnable runnable = new Runnable() {// �������ǲ��������Ƶ��࣬����û�취�������ǡ������ڴ���ʱ����Ϊnew����һ�������������ǡ�
			// ���Ҫ������һ����ʽ��new��䣬������ʾ��
			// new
			// <���ӿ�> <�������>
			// ������ʽ��new�������һ���µ������࣬����һ���������������չ������ʵ��һ�������Ľӿڡ����������Ǹ����һ����ʵ������������Ϊ���Ľ�������ء�Ҫ��չ�����Ҫʵ�ֵĽӿ���new���Ĳ���������������������


			public void run() {
				while (true == flag) {
					randomNum = (int) (Math.random() * 10);
					randomPrinter = (int) (((Math.random() * 10) % PrinterCount) + 1);
					System.out.println("randomNum:" + randomNum + ",randomPrinter:" + randomPrinter);

					try {
						Class.forName("com.mysql.jdbc.Driver");
						conn5 = DriverManager.getConnection(url);
						// ����������������͸���������Ĵ�ӡ����ʵ����д�����ݿ�
						sql = "UPDATE printerdemo SET taskinfo = '"
								+ randomNum + "' WHERE printid = '"
								+ randomPrinter + "'";
						stmt = conn5.createStatement();
						if (-1 != stmt.executeUpdate(sql)) {
							System.out.println("produce a random number:" + randomNum);
						}
					} catch (SQLException e) {
						System.out.println("MySQL��������");
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

		// ������ʱ����
		Thread thread = new Thread(runnable);
		thread.start();
	}
}
