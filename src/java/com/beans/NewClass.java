/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import java.io.File;
import java.util.UUID;

/**
 *
 * @author chhavikumar.b
 */
public class NewClass {

    public static void main(String[] args) {

        File f = new File("C:\\Users\\Public\\Pictures\\Sample Pictures\\Desert1.jpg");
        f.renameTo(new File("C:\\Users\\Public\\Pictures\\Sample Pictures\\" + UUID.randomUUID() + ".jpg"));
    }
}
