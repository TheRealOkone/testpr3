package com.pack;

import org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry;
import org.apache.commons.compress.archivers.sevenz.SevenZFile;
import org.apache.commons.compress.archivers.sevenz.SevenZOutputFile;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;

import java.io.*;
import java.util.zip.*;

public class Main {
    public static void main(String[] args) {
        if(args[0] == "--zip"){
            if(args[1] == "-c"){
                String filename = args[2];
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
            }
            if(args[1] == "-d"){
                String filename = args[2];
                try(ZipInputStream zin = new ZipInputStream(new FileInputStream(filename)))
                {
                    ZipEntry entry;
                    String name;
                    long size;
                    while((entry=zin.getNextEntry())!=null){

                        name = entry.getName();
                        size=entry.getSize();
                        System.out.printf("File name: %s \t File size: %d \n", name, size);

                        // распаковка
                        FileOutputStream fout = new FileOutputStream("C:\\somedir\\new" + name);
                        for (int c = zin.read(); c != -1; c = zin.read()) {
                            fout.write(c);
                        }
                        fout.flush();
                        zin.closeEntry();
                        fout.close();
                    }
                }
                catch(Exception ex){

                    System.out.println(ex.getMessage());
                }
            }
        }
        if(args[0] == "--7z"){
            SevenZ sevenz = new SevenZ();
            if(args[1] == "-c"){
                try {
                    sevenz.compress(args[2]);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(args[1] == "-d"){
                try {
                    File file = new File(".");
                    sevenz.decompress(args[2],file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }







    }
}
