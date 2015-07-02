package FileUtil;
// Created by ZG on 15/7/2.
//

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class FileUtil {
    public static ArrayList<String> readFile(String filePathAndName,
                                             String codeType) throws IOException {
        File f = new File(filePathAndName);
        if (!f.exists()) {
            Log.log("file doesn't exist!");
            throw new IOException("file doesn't exist!");
        }
        InputStreamReader read = null;
        BufferedReader reader = null;
        ArrayList<String> temp = new ArrayList<String>();
        try {
            if (f.isFile() && f.exists()) {
                read = new InputStreamReader(new FileInputStream(f), codeType);
                reader = new BufferedReader(read);
                String line;
                while ((line = reader.readLine()) != null) {
                    temp.add(line);
                }
            }
        } catch (Exception e) {
            Log.log(e.getMessage());
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (read != null) {
                read.close();
            }
        }
        return temp;
    }

    public static File writeFile(String filePathAndName,
                                 ArrayList<String> list, String codeType) throws IOException {
        File f = new File(filePathAndName);
        OutputStreamWriter write = null;
        BufferedWriter writer = null;
        try {
            if (!f.exists()) {
                f.createNewFile();
            }
            write = new OutputStreamWriter(new FileOutputStream(f), codeType);
            writer = new BufferedWriter(write);
            for (String str : list) {
                writer.write(str + "\r\n");
            }
        } catch (Exception e) {
            Log.log("file doesn't exist!");
            Log.log(e.getMessage());
            e.printStackTrace();
            return null;
        } finally {
            if (writer != null) {
                writer.close();
            }
            if (write != null) {
                write.close();
            }
        }
        return f;
    }
}
