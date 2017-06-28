package cloud.test;

public class TryTry {

	public static void main(String[] args) {
		//int intrandomos = 1;
		// String str = intrandomos+2 + ""; //整数转换成字符串
		// System.out.println("str="+str);
		//String pricevalue = "0.02元/小时";
		//pricevalue = pricevalue.substring(0, pricevalue.length() - 4);
		
		boolean valueOk = false;
		String pricevalue = "0.0200";
		System.out.println("pricevalue=" + pricevalue);
		double sum = 0.0200;
		
		String sumTo = String.format("%.4f", sum);
		//String sumTo = String.valueOf(sum);
		System.out.println("sumTo=" + sumTo);
	    
		valueOk = sumTo.equals(pricevalue);

		System.out.println("valueOk=" + valueOk);

	}

}
