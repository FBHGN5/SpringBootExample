package org.test.util;

import java.util.Scanner;

public class SalaryTest {
    public void test(int a) {
        double money = a; //税前工资

        double society = getOther(money); //应扣社保

        double tax = getTax(money - society);//应扣税款
        double realMoney = money - society - tax; //实际到手工资

        System.out.println(
                String.format("税前：%.2f, 社保:%.2f, 扣税:%.2f,　到手:%.2f",
                        money, society, tax, realMoney));
        //税前：30000.00, 社保:6660.00, 扣税:3955.00,　到手:19385.00
    }

    /**
     * 计算应扣除的社保缴费金额，没算上限，如 society = society < 23118? society : 23118;
     *
     * @param society 　社保基数
     * @param society  　公积金基数
     * @return
     */
    public double getOther(double society) {
        //保险类型       个人比例       公司比例
        //养老保险金：      8%            19%
        //医疗保险金：      2%            10%
        //失业保险金：    0.2%            0.8%
        //基本住房公积金：  12%            12%
        //工伤保险金：       0            0.4%
        //生育保险金：       0            0.8%
        return society * 0.08 + society * 0.02 + society * 0.002 + society * 0.12;
    }

    /**
     * 计算应扣税额
     \*
     个人所得税起征点为3500元。
     应缴个人所得税的计算公式为=（月应税收入-3500）*税率-速算扣除数
     级数   含税级距   税率   速算扣除数
     1  不超过1,500元的 3%      0
     2 超过1,500元至4,500元的部分 10% 105
     3 超过4,500元至9,000元的部分20% 555
     4 超过9,000元至35,000元的部分 25% 1005
     5 超过35,000元至55,000元的部分 30% 2,755
     6 超过55,000元至80,000元的部分 35% 5,505
     7 超过80,000元的部分 45% 13,505
     *\
     ---------------------
     作者：FreeToFlyForMonkey
     来源：CSDN
     原文：https://blog.csdn.net/qwe13600as/article/details/77148884
     版权声明：本文为博主原创文章，转载请附上博文链接！
     */
    public double getTax(double total) {
        double base = 5000;
        double money = total - base;
        if (money <= 0) {
            return 0;
        }

        if (money < 1500) {
            return money * 0.03;
        }

        if (money < 4500) {
            return money * 0.1 - 105;
        }

        if (money < 9000) {
            return money * 0.2 - 555;
        }

        if (money < 35000) {
            return money * 0.25 - 1005;
        }

        if (money < 55000) {
            return money * 0.3 - 2755;
        }

        if (money < 80000) {
            return money * 0.35 - 5505;
        }

        return money * 0.45 - 13505;
    }

    public static void main(String[] args) {
        System.out.println("请输入工资");
        Scanner sc=new Scanner(System.in);
        int a=sc.nextInt();
        SalaryTest salaryTest = new SalaryTest();
        salaryTest.test(a);
    }
}
