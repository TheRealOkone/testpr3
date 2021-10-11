package com.pack;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static org.junit.Assert.assertTrue;


public class MainTest {
    public MainTest() {
    }

    @Test
    public void testLoads() {
    }

    @Test
    public void sevenzTest() {
        SevenZ sevenz = new SevenZ();
        try {
            assertTrue(sevenz.compress("input.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void zipTest(){
        String filename = "input.txt";
        try(ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(".\\output.zip"));
            FileInputStream fis= new FileInputStream(filename);)
        {
            ZipEntry entry1=new ZipEntry("notes.txt");
            zout.putNextEntry(entry1);
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            zout.write(buffer);
            zout.closeEntry();
        }
        catch(Exception ex){

            System.out.println(ex.getMessage());
        }


        assertTrue((new File("output.zip")).exists());
    }

}
