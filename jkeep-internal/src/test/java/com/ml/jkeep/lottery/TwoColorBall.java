package com.ml.jkeep.lottery;

import java.util.Arrays;

/**
 @Date: 2019/11/26-22:42
 @Author Genie
 @Description: 双色球中奖机制
 */
public class TwoColorBall {

	/**
	 * 通过自己购买的双色球和本期双色球计算中奖结果
	 *
	 * @param purchaseNumber - 本期自己购买号码(6红一蓝) -> 如 : 03 07 09 12 14 15 27
	 * @param currentNumber - 本期双色球号码(6红一蓝) -> 如 : 第19136期开奖结果 = 12 14 15 17 29 33 12
	 * 上述内容 : 红色中3个，蓝色未中
	 */
	public static void winningCalculation(String [] purchaseNumber , String []  currentNumber ){
		//蓝球索引
		int blueBallIndex = 6 ;
		//计算红球中的数量
		int redWinCount = 0;
		for (int i = 0 ; i < blueBallIndex ; i ++) {
			String selfBall = purchaseNumber[i];
			for ( int j = 0 ; j < blueBallIndex ; j ++ ) {
				String prizeBall = currentNumber[j];
				if ( selfBall.equals( prizeBall )) {
					redWinCount ++ ;
				}
			}

		}
		//计算蓝球是否中奖
		boolean blueWinStatus = false ;
		if ( purchaseNumber[blueBallIndex].equals( currentNumber[blueBallIndex] ) ){
			blueWinStatus = true ;
		}
		System.out.println("-------------------------------------------");
		System.out.println( String.format("|   ME : %s      |" , Arrays.toString(purchaseNumber) ));
		System.out.println( String.format("|   ** : %s      |" , Arrays.toString(currentNumber) ));
		System.out.println( String.format("|   红色球中奖数 : %s , 蓝色球中奖数 : %s    |" , redWinCount , blueWinStatus ? 1 : 0 ));

		String winMsg = "|   %s                                 |";

		//双色球中奖规则 http://www.500.com/rule/ssq.shtml?0_ala_baidu
		if ( redWinCount == 6 && blueWinStatus ) {
			//选6+1中6+1
			/**
			 * 	当奖池资金低于1亿元时，奖金总额为当期高等奖奖金的70%与奖池中累积的奖金之和，单注奖金按注均分，单注最高限额封顶500万元。
			 *  当奖池资金高于1亿元（含）时，奖金总额包括两部分，一部分为当期高等奖奖金的50%与奖池中累积的奖金之和，单注奖金按注均分，单注最高限额封顶500万元；另一部分为当期高等奖奖金的20%，单注奖金按注均分，单注最高限额封顶500万元。
			 */
			System.out.println(String.format(winMsg,"一等奖"));
		} else if ( redWinCount == 6 ) {
			//选6+1中6+0 (当期高等奖奖金的30%)
			System.out.println(String.format(winMsg,"二等奖"));
		} else if ( redWinCount == 5 && blueWinStatus ) {
			//选6+1中5+1 (单注奖金额固定为3000元)
			System.out.println(String.format(winMsg,"三等奖"));
		} else if ( redWinCount == 5 || ( redWinCount == 4 && blueWinStatus )) {
			//选6+1中5+0或中4+1 (单注奖金额固定为200元)
			System.out.println(String.format(winMsg,"四等奖"));
		} else if ( redWinCount == 4 || ( redWinCount == 3 && blueWinStatus ) ) {
			//选6+1中4+0或中3+1 (单注奖金额固定为10元)
			System.out.println(String.format(winMsg,"五等奖"));
		} else if ( blueWinStatus ) {
			//选6+1中2+1或中1+1或中0+1 (单注奖金额固定为5元)
			System.out.println(String.format(winMsg,"六等奖"));
		} else {
			System.out.println(String.format(winMsg,"没中奖"));
		}
		System.out.println("-------------------------------------------");

	}

	public static void main(String[] args) {
		//自己购买的所有号码
		String [] [] purchaseNumberArray = new String [] [] {
				{"02","04","07","13","17","24","04"},
				{"01","04","12","13","20","22","12"},
				{"02","05","09","13","17","26","09"},
				{"03","07","10","12","16","24","03"},
				{"03","05","10","13","24","32","05"},
				{"05","09","10","11","17","22","11"},
				{"05","09","10","13","22","32","07"},
				{"07","11","17","22","26","28","08"},
				{"11","14","17","22","26","33","03"},
				{"05","09","11","17","22","27","11"},
		};
		//本期号码
		String [] currentNumber = {"12","14","15","17","29","33","12"};
		for (String [] purchaseNumber : purchaseNumberArray){
			TwoColorBall.winningCalculation(purchaseNumber,currentNumber);
		}

	}


}
