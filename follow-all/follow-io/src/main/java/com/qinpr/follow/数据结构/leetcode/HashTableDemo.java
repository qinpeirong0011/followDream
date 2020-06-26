package com.qinpr.follow.数据结构.leetcode;

import java.util.Scanner;

/**
 * 题目:有一个公司,当有新的员工来入职的时候,要求将该员工的信息加入(id,性别,年龄,名字,住址...).
 *     当输入员工id时,要求查找到该员工所有信息
 * 要求:不要使用数据库,速度越快越好
 *
 * Created by qinpr on 2020/5/6.
 */
public class HashTableDemo {
    public static void main(String[] args) {
        //创建hash表
        HashTable hashTable = new HashTable(7);

        //创建一个简单菜单
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add:  添加雇员");
            System.out.println("list: 显示雇员");
            System.out.println("find: 查找雇员");
            System.out.println("exit: 退出系统");

            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();

                    Emp emp = new Emp(id, name);
                    hashTable.add(emp);
                    break;
                case "list":
                    hashTable.list();
                    break;
                case "find":
                    System.out.println("请输入要查找的id");
                    id = scanner.nextInt();
                    hashTable.findEmpById(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }
    }
}

//创建hashTable,管理多条链表
class HashTable {
    private int size; //表示有多少条链表
    private EmpLinkedList[] empLinkedListArray;

    public HashTable(int size) {
        this.size = size;
        this.empLinkedListArray = new EmpLinkedList[size];
        for (int i=0 ; i<size; i++) {
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }

    public void add(Emp emp) {
        //根据雇员id,得到该员工应该加入到那条链表
        int empLinkedListNo = hashFun(emp.id);
        empLinkedListArray[empLinkedListNo].add(emp);
    }

    //遍历所有的链表,遍历hash表(数组+链表共同构成哈希表)
    public void list() {
        for (int i=0 ; i<size; i++) {
            empLinkedListArray[i].list(i);
        }
    }

    public void findEmpById(int id) {
        //使用散列函数确定链表
        int empLinkedListNo = hashFun(id);
        Emp emp = empLinkedListArray[empLinkedListNo].findEmpById(id);
        if (emp != null) {
            System.out.printf("在第%d链表中找到 雇员id = %d\n", (empLinkedListNo+1), id);
        } else {
            System.out.println("在哈希表中,没有找到该雇员");
        }
    }

    //编写一个散列函数,方法用很多,使用一个简单的取模法
    private int hashFun(int id) {
        return id % size;
    }
}

class Emp {
    public int id;
    public String name;
    public Emp next; //指针

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

class EmpLinkedList {
    //头指针,执向第一个emp,默认是null
    private Emp head;

    //添加雇员
    //假设添加雇员时id是自增长的,即id的分配是从小到大的
    //因此将该雇员直接加入到本链表最后一个即可
    public void add(Emp emp) {
        //如果是添加第一个雇员
        if (head == null) {
            head = emp;
            return;
        }

        Emp currentEmp = head;
        while (true) {
            if (currentEmp.next == null) {
                break;
            }
            currentEmp = currentEmp.next;
        }
        currentEmp.next = emp;
    }

    //遍历链表的雇员信息
    public void list(int no) {
        if (head == null) {
            System.out.println("当前第" + (no+1) + "链表为空");
            return;
        }

        System.out.printf("当前第" + (no+1) + "链表信息为");
        Emp curEmp = head;
        while (true) {
            System.out.printf(" => id=%d name=%s\t", curEmp.id, curEmp.name);
            if (curEmp.next == null) {
                break;
            }
            curEmp = curEmp.next;
        }
        System.out.println("");

    }

    //如果查抄到返回emp,否则返回null
    public Emp findEmpById(int id) {
        if (head == null) {
            System.out.println("当前链表为空");
            return null;
        }

        Emp curEmp = head;
        while (true) {
            if (curEmp.id == id) {//找到
                break;
            }
            //退出
            if (curEmp.next == null) { //遍历链表没有找到id
                curEmp = null;
                break;
            }
            curEmp = curEmp.next;
        }

        return curEmp;
    }
}
