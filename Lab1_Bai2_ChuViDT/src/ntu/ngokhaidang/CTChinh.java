package ntu.ngokhaidang;

import java.util.Scanner;

public class CTChinh {

	public static void main(String[] args) {
		
		// chuẩn bị cho việc nhập
		Scanner banPhim = new Scanner(System.in);
		
		// nhập chiều dài chiều rộng
		
		System.out.print("Nhập chiều dài: ");
		double dai = banPhim.nextDouble();
		System.out.print("Nhập chiều rộng: ");
		double rong = banPhim.nextDouble();
		
		// tính chu vi và diện tính
		double chuVi = (dai + rong) * 2;
		double dienTich = dai * rong;
		double canhNho = Math.min(dai, rong);
		
		// xuất kết quả
		System.out.println("Chu vi: " + chuVi);
		System.out.println("Diện tích: " + dienTich);
		System.out.println("Cạnh nhỏ: " + canhNho);

		
	}

}
