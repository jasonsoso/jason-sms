package com.jason.sms.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 *
 */
public class MiscUtils {

	private MiscUtils(){
	}
	
    /**
     * 四舍五入,一位小数
     * BigDecimal 类型
     * @param src
     * @return
     */
    public static BigDecimal round(BigDecimal src) {
    	return src.setScale(1, BigDecimal.ROUND_HALF_UP);
    }
    

    /**
     * 四舍五入,两位小数
     * double 类型
     * @param src
     * @return
     */
    public static double round(double src) {
        return new BigDecimal(String.valueOf(src)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    
    /**
     * 格式化 货币 : #,#00.##
     * @param s
     * @return
     */
    public static String getCurrency(double s){  
        NumberFormat formatter = new DecimalFormat("#,#00.##");  
        return formatter.format(s);  
    }  
    
    public static void main(String[] args) {
    	
    	BigDecimal b7 = new BigDecimal(18.1);
    	System.out.println("四舍五入,保留一位小数"+round(b7));
		BigDecimal b8 = new BigDecimal(18.2);
		System.out.println("四舍五入,保留一位小数"+round(b8));
		
    	double src = round(155445.555d);
    	System.out.println("四舍五入"+src);
    	String num = getCurrency(15888855445.25d);
    	System.out.println("格式化货币"+num);
	}
}
