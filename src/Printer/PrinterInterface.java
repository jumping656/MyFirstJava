package Printer;

import java.sql.*;
import java.util.Scanner;

public class PrinterInterface {

	public static void main(String[] args) throws Exception {
		// ÿ30s������д�ӡ��״̬
		TaskAllCheck taskall = new TaskAllCheck();
		taskall.Check();
		int choice;
		boolean WhileFlag = false;//�˳�whileѭ�����
		String sql;
		String url = "jdbc:mysql://localhost:3306/javademo?user=root&password=&useUnicode=true&characterEncoding=UTF8";

		while (true) {
			System.out.println("������ѡ�1.�鿴��ӡ����Ϣ��2.��Ӵ�ӡ����3.���´�ӡ����Ϣ��4.�˳�;5.��ʱ������������;6.ɾ����ӡ��");
			Scanner sc = new Scanner(System.in);
			choice = sc.nextInt();
			switch (choice) {
				case 1:
					Connection conn = null;

					try {
						Class.forName("com.mysql.jdbc.Driver");
						conn = DriverManager.getConnection(url);
						Statement stmt = conn.createStatement();
						sql = "select * from printerdemo";
						ResultSet rs = stmt.executeQuery(sql);
						System.out
								.println("PrinterID\tPrintName\tPrintType\tUserInfo\tTaskInfo\tSpeed");
						while (rs.next()) {
							System.out.println(rs.getInt(1) + "       \t"
									+ rs.getString(2) + "        \t"
									+ rs.getString(3) + "        \t"
									+ rs.getString(4) + "        \t"
									+ rs.getString(5) + "        \t"
									+ rs.getString(6));
						}
					} catch (SQLException e) {
						System.out.println("MySQL��������");
						e.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						conn.close();
					}

					break;
				case 2:
					Printer pt = new Printer();
					Scanner ScName = new Scanner(System.in);
					System.out.println("Input printer name:");
					pt.SetName(ScName.nextLine());
					Scanner ScType = new Scanner(System.in);
					System.out.println("Input printer type:");
					pt.SetType(ScType.next());
					Scanner ScUser = new Scanner(System.in);
					System.out.println("Input User Info:");
					pt.SetUser(ScUser.next());
					Scanner ScTask = new Scanner(System.in);
					System.out.println("Input Task Info:");
					pt.SetTask(ScTask.next());
					Scanner ScSpeed = new Scanner(System.in);
					System.out.println("Input Speed:");
					pt.SetSpeed(ScSpeed.next());

					Connection conn1 = null;
					// sql������溬�б���,step1:������д����ʽд��SQL��䣻step2:���������ɱ������������˼���+��step3:��+���˼���˫����
					sql = "INSERT INTO printerdemo(printname,printtype,userinfo,taskinfo,speed) values('"
							+ pt.GetName()
							+ "','"
							+ pt.GetType()
							+ "','"
							+ pt.GetUser()
							+ "','"
							+ pt.GetTask()
							+ "','"
							+ pt.GetSpeed() + "')";

					try {
						Class.forName("com.mysql.jdbc.Driver");
						conn1 = DriverManager.getConnection(url);
						Statement stmt = conn1.createStatement();
						if (-1 != stmt.executeUpdate(sql)) {
							System.out.println("Update Successed!");
						}
					} catch (SQLException e) {
						System.out.println("MySQL��������");
						e.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						conn1.close();
					}

					break;
				case 3:
					Printer Updatept = new Printer();
					System.out.println("Input the printer name to update:");
					Scanner ScUpdateName = new Scanner(System.in);
					String Printer = ScUpdateName.next();
					Scanner ScUpdateType = new Scanner(System.in);
					System.out.println("Input printer type:");
					Updatept.SetType(ScUpdateType.next());
					Scanner ScUpdateUser = new Scanner(System.in);
					System.out.println("Input User Info:");
					Updatept.SetUser(ScUpdateUser.next());
					Scanner ScUpdateTask = new Scanner(System.in);
					System.out.println("Input Task Info:");
					Updatept.SetTask(ScUpdateTask.next());
					Scanner ScUpdateSpeed = new Scanner(System.in);
					System.out.println("Input Speed:");
					Updatept.SetSpeed(ScUpdateSpeed.next());

					Connection conn2 = null;
					// sql������溬�б���,step1:������д����ʽд��SQL��䣻step2:���������ɱ������������˼���+��step3:��+���˼���˫����
					sql = "SELECT * FROM printerdemo WHERE printname = '" + Printer + "'";

					try {
						Class.forName("com.mysql.jdbc.Driver");
						conn2 = DriverManager.getConnection(url);
						Statement stmt = conn2.createStatement();
						ResultSet rs = stmt.executeQuery(sql);

						if (!rs.next()) {
							System.out.println("Printer name not found!");
							break;
						}
						sql = "UPDATE printerdemo SET printtype = '"
								+ Updatept.GetType() + "',userinfo = '"
								+ Updatept.GetUser() + "'," + "taskinfo = '"
								+ Updatept.GetTask() + "',speed = '"
								+ Updatept.GetSpeed() + "' WHERE printname = '"
								+ Printer + "'";
						if (-1 != stmt.executeUpdate(sql)) {
							System.out.println("Update Successed!");
						}
					} catch (SQLException e) {
						System.out.println("MySQL��������");
						e.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						conn2.close();
					}

					break;
				case 4:
					System.out.println("Thank you��");
					WhileFlag = true;

					break;
				case 5:
					RandomNum random = new RandomNum();
					random.GetRandomNum();

					break;
				case 6:
					Connection conn6 = null;
					System.out.println("Input the printer name to delete:");
					Scanner ScDeleteName = new Scanner(System.in);
					String name = ScDeleteName.next();

					try {
						Class.forName("com.mysql.jdbc.Driver");
						conn6 = DriverManager.getConnection(url);
						Statement stmt = conn6.createStatement();
						sql = "DELETE FROM printerdemo WHERE printname = '" + name + "'";
						if (-1 != stmt.executeUpdate(sql)) {
							System.out.println("Delete Successed!");
						}
					} catch (SQLException e) {
						System.out.println("MySQL��������");
						e.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						conn6.close();
					}

					break;
				default:
					System.out.println("invalid input/n");
					break;
			}
			//�ж��˳�����Ƿ�Ϊtrue
			if (true == WhileFlag) {
				taskall.SetFlag(false);
				RandomNum.SetRandomFlag(false);
				break;
			}
		}
	}
}
