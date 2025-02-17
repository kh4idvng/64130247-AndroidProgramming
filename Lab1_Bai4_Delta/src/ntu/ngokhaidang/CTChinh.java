package ntu.ngokhaidang;
import java.util.Scanner;

public class CTChinh {

	public static void main(String[] args) {
		
		// chuẩn bị cho việc nhập
		Scanner banPhim = new Scanner(System.in);
		
		// Nhập a, b, c
		System.out.print("Nhập hệ số a: ");
		double a = banPhim.nextDouble();
		System.out.print("Nhập hệ số b: ");
		double b = banPhim.nextDouble();
		System.out.print("Nhập hệ số c: ");
		double c = banPhim.nextDouble();
		
		// tính delta
		double delta = Math.pow(b, 2) - 4*a*c;
		// căn delta
		double canDelta = Math.sqrt(delta);
		
		// in kết quả
		System.out.println("Delta = " + delta);
		System.out.println("Căn delta = " + canDelta);
		
	}

}
