package com.prerepa.rpc_algorithm;

import java.util.*;

class Decrypt {
    public static void main(String[] args) {
        String rev = "jU5t_a_sna_3lpm17ga45_u_4_mbrf4c";
        System.out.println(reverse(rev));
    }

    static String reverse(String reverse) {
        char[] buffer = new char[32];
        int i;

        for (i = 0; i < 8; i++) {
            buffer[i] = reverse.charAt(i);
        }
        for (; i<16; i++) {
            buffer[23-i] = reverse.charAt(i);
        }
        for (; i<32; i+=2) {
            buffer[46-i] = reverse.charAt(i);
        }
        for (i = 31; i >= 17; i-=2) {
            buffer[i] = reverse.charAt(i);
        }
        return new String(buffer);
    }
}
