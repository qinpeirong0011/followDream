package com.qinpr.follow.数据结构.leetcode;

import com.qinpr.follow.数据结构.Stack;

/**
 * Created by qinpr on 2020/5/6.
 */
public class _20_有效括号 {

    /**
     * 1: 遇见左字符,将左字入栈
     * 2: 遇见右字符, 如果栈是空的,说明括号无效;如果栈不为空,将栈顶元素出栈,与右字符匹配
     *    --如果左右字符匹配不成功,说明括号无效
     *    --如果左右字符匹配成功,继续扫描下一个字符
     * 3: 所有字符扫描完毕后
     *    栈为空,说明括号有效
     *    栈不空,说明括号无效
     *
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack();

        int len = s.length();
        for (int i=0 ; i<len; i++) {
            char c = s.charAt(i);

            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else { //右括号
                if (stack.isEmpty()) {
                    return false;
                }

                Character left = stack.pop();
                if (left == '(' && c != ')') return false;
                if (left == '{' && c != '}') return false;
                if (left == '[' && c != ']') return false;
            }
        }
        return stack.isEmpty();
    }

    /**
     * 效率很低: contains方法和replace效率低下
     * @param s
     * @return
     */
    public boolean isValid2(String s) {
        while (s.contains("()")
                || s.contains("{}")
                || s.contains("[]")) {
            s = s.replace("()","");
            s = s.replace("{}","");
            s = s.replace("[]","");
        }

        return s.isEmpty();
    }
}
