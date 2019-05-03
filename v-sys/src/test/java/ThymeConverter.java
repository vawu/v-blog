import cn.hutool.core.io.IoUtil;
import org.junit.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.util.List;

public class ThymeConverter {
    @Test
    public void convert() throws FileNotFoundException {
//        项目路径
        String path = "E:\\workspace\\HBuilderProjects\\abc";
        File file = new File(path);
        try {
//            如果是目录的话
            if (file.isDirectory()) {
//                获取文件集合
                File[] files = file.listFiles();
//                遍历文件
                for (File layer : files != null ? files : new File[0]) {
                    if (layer.getName().split("\\.").length > 1) {
                        if ("html".equals(layer.getName().split("\\.")[1])) {
                            BufferedReader bufferedInputStream = new BufferedReader(new FileReader(layer));
                            String line;
                            while ((line = bufferedInputStream.readLine()) != null) {
                                if (line.contains("<link rel=")) {
                                    System.out.println(line.lastIndexOf("<link rel="));
                                } else if (line.contains("<script type=")) {

                                }
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}