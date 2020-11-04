package com.xsb;

import java.util.Scanner;

public class OrderingMar {
	public static void main(String[] args) {
		
		String[][] strs = new String[4][4];
		//0、保存订餐人姓名
		//1、保存所选信息,菜品名及份数
		//2、保存送餐地址
		//3、菜品名称
		
		
		int[][] integers = new int[3][4]; 
		//0、保存送餐时间
		//1、保存订单状态：0表示已预订，1表示已完成
		//2、点赞数
		
		double[][] doubles = new double[2][4];
		//0、保存订单总金额
		//1、保存菜品单价
		
		
		//初始化
		strs[3][0] = "红烧带鱼";
		strs[3][1] = "鱼香肉丝";
		strs[3][2] = "时令鲜蔬";
		doubles[1][0] = 38.0;
		doubles[1][1] = 20.0;
		doubles[1][2] = 10.0;
		strs[0][0] = "王鑫";
		strs[0][1] = "王松";
		strs[1][0] = "红烧带鱼 2份";
		strs[1][1] = "鱼香肉丝 2份";
		integers[0][0] = 12;
		integers[0][1] = 18;
		strs[2][0] = "工投";
		strs[2][1] = "工投";
		integers[1][0] = 1;
		integers[1][1] = 0;
		doubles[0][0] = 76.0;
		doubles[0][1] = 45.0;
		
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n欢迎使用“吃货联盟订餐系统” ");
		while(true) {
			
			//显示主菜单
			System.out.println("*************************");
			System.out.println("1、我要订餐");
			System.out.println("2、查看餐带");
			System.out.println("3、签收订单");
			System.out.println("4、删除订单");
			System.out.println("5、我要点赞");
			System.out.println("6、退出系统");
			System.out.println("*************************");
			
			System.out.println("请输入要使用的功能编号：");
			
			int choose = sc.nextInt();
			
			
			//功能实现
			switch (choose) {
			case 1:
				//我要订餐
				System.out.println("*****我要订餐*****");
				boolean isAdd = true; //记录是否可以订餐
				for (int i = 0; i < strs[0].length; i++) {
					
					if (strs[1][i] == null) { 					
						//找到空位置
						
						System.out.println("请输入订餐人姓名：");
						String name = sc.next();
						//显示可以选择的菜品信息
						System.out.println("序号" + "\t" + "菜名" + "\t" + "单价" + "\t"  + "点赞数");
						for (int j = 0; j < strs[3].length; j++) {
							if (strs[3][j] != null) {
								System.out.println(j + 1 + "\t" + strs[3][j] + "\t" + doubles[1][j] + "元\t" + integers[2][j]);
							}
							
						}
						//用户点菜
						System.out.println("请选择您要点的菜品编号：");
						int chooseDish = sc.nextInt();
						while (chooseDish > 3 || chooseDish < 0) {
							System.out.println("请输入正确编号");
							chooseDish = sc.nextInt();
						}
						System.out.println("请选择您需要的份数：");
						int number = sc.nextInt();
						String dishMeg = strs[3][chooseDish - 1] + " " + number + "份";
						double sumPrice = doubles[1][chooseDish - 1] * number;
						//餐费满50，免送餐费5元
						double deliCharge = (sumPrice >= 50)? 0 : 5;
						
						
						System.out.println("请输入送餐时间(10点到20点之间整点送餐)：");
						int time = sc.nextInt();
						while (time < 10 || time > 20) {
							System.out.println("输入时间有误！请输入10点到20点之间的整数");
							time = sc.nextInt();
						}
						
						
						System.out.println("请输入送餐地址：");
						String address = sc.next();
						
						//添加数据
						strs[0][i] = name;
						strs[1][i] = dishMeg;
						integers[0][i] = time;
						strs[2][i] = address;
						doubles[0][i] = sumPrice + deliCharge;
						
						
						
						System.out.println("订餐成功");
						System.out.println("您订的是：" + dishMeg);
						System.out.println("送餐时间：" + time + "点");
						System.out.println("餐费：" + sumPrice + "元，送餐费" + deliCharge + "元,总计：" + (sumPrice + deliCharge) + "元");

						break;
					}
					if (!isAdd) {
						System.out.println("对不起！您的餐袋已满");
					}
				}
				break;
			case 2:
				//查看餐袋
				System.out.println("*****查看餐袋*****");
				System.out.println("序号\t订餐人\t餐品信息\t\t送餐时间 \t送餐地址 \t\t总金额 \t订单状态");
				for (int i = 0; i < strs[0].length; i++) {
					if (strs[0][i] != null) {
						System.out.println(i + 1 + "\t"+ strs[0][i] + "\t" + strs[1][i] + "\t" + integers[0][i] + "点\t\t" + strs[2][i] + "\t\t\t" + doubles[0][i] + "元\t" + (integers[1][i] == 0 ? "已预订" : "已完成"));
					}								
				}
				break;
				
			case 3:
				//签收订单
				System.out.println("*****签收订单*****");
				boolean isSignFind = false;
				System.out.println("请选择要签收的订单序列号：");
				int signOrderId = sc.nextInt();
				for (int i = 0; i < strs[0].length; i++) {
					//判断订单状态
					if (strs[0][i] != null && integers[1][i] == 0 && signOrderId == i + 1) {
						integers[1][i] = 1;
						System.out.println("订单签收成功！");
						isSignFind = true;
					}else if (strs[0][i] != null && integers[1][i] == 1 && signOrderId == i + 1) {
						System.out.println("您的订单已完成签收，不能再次签收！");
						isSignFind = true;
					}
				}
				//未找到订单序号
				if(!isSignFind) {
					System.out.println("您选择的订单不存在！");
				}
				break;
			case 4:
				//删除订单
				boolean isDelFind = false;
				System.out.println("请输入要删除的订单序号");
				int needDelId = sc.nextInt();
				for (int i = 0; i < doubles.length; i++) {
					//判断是否能删除
					if (strs[i] != null && integers[1][i] == 1 && needDelId == i + 1) {
						isDelFind = true;
						//删除操作，后面的元素前移
						for (int j = needDelId - 1; j < strs[0].length - 1; j++) {
							strs[0][j] = strs[0][j + 1];
							strs[1][j] = strs[1][j + 1];
							integers[0][j] = integers[0][j + 1];
							strs[2][j] = strs[2][j + 1];
							integers[1][j] = integers[1][j + 1];
							doubles[0][j] = doubles[0][j + 1];
							
						}
						strs[0][strs[0].length - 1] = null;
						strs[1][strs[1].length - 1] = null;
						integers[0][integers[0].length - 1] = 0;
						strs[2][strs[2].length - 1] = null;
						integers[1][integers[1].length - 1] = 0;
						doubles[0][doubles[0].length - 1] = 0;
						System.out.println("删除订单成功！");
						break;
						
					}else if (strs[i] != null && integers[1][i] == 0 && needDelId == i + 1) {
						System.out.println("您的订单未签收，不能删除！");
						isDelFind = true;
						break;
					}
				}
				if (!isDelFind) {
					System.out.println("您要删除的订单不存在！");
				}
				break;
			case 5:
				//我要点赞
				System.out.println("序号" + "\t" + "菜名" + "\t" + "单价");
				for (int i = 0; i < strs[3].length; i++) {
					if (strs[3][i] != null) {
						System.out.println(i + 1 + "\t" + strs[3][i] + "\t" + doubles[1][i]);
					}
				}
				System.out.println("请选择您要点赞的菜品序号：");
				integers[2][sc.nextInt() - 1]++;
				System.out.println("点赞成功！");
				break;
			case 6:
				//退出系统
				System.out.println("确认退出系统？1、确认 2、取消");
				if (sc.nextInt() == 1) {
					sc.close();
					return;
				}
				break;
				

			default:
				System.out.println("请输入正确命令！");
				break;
			}
			
			//输入0返回主菜单
			System.out.println("输入0返回：");
			
			if (sc.nextInt() != 0) {
				System.out.println("请输入正确命令！");
				System.out.println("输入0返回：");
			}
			
			
		}
	}


}
