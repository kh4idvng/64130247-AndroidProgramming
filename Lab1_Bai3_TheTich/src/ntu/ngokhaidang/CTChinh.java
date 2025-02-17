package ntu.ngokhaidang;

import java.util.Scanner;

public class CTChinh {

	public static void main(String[] args) {
		
		// chuẩn bị cho việc nhập
		Scanner banPhim = new Scanner(System.in);
		
		// nhập cạnh
		System.out.print("Nhập cạnh của khối lập phương: ");
		double canh = banPhim.nextDouble();
		
		// tính thể tích
		double theTich = Math.pow(canh, 3);
		
		// in kết quả
		System.out.println("Thể tích của khối lập phương là: " + theTich);
		

	}

}
