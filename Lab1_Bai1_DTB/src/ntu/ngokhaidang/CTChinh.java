package ntu.ngokhaidang;

import java.util.Scanner;

public class CTChinh {

	public static void main(String[] args) {
		
		// chuẩn bị cho việc nhập
		Scanner banPhim = new Scanner(System.in);
		
		// nhập họ và tên sinh viên
		System.out.print("Nhập họ và tên sinh viên: " );
		String hoTen = banPhim.nextLine();
		
		// nhập điểm trung bình
		System.out.print("Nhập điểm trung bình: ");
		double diemTB = banPhim.nextDouble();
		
		// in thông tin
		System.out.println("Họ và tên: " + hoTen);
		System.out.println("Điểm trung bình: " + diemTB);
		


	}

}
