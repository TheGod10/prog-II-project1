package ch.fhnw.project.project;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.io.BufferedWriter;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * Created by TheGod on 27.04.16.
 */
public class inputData {
    public static void main(String[] args) {

        File file = new File("/Users/TheGod/Programming/Java/Programieren2/prog-II-project/src/main/java/ch/fhnw/project/project/helvetia.txt");
        if (file.exists()) {
            TextFileReader textFileReader = new TextFileReader(file);
            Map<String, ColumnBean> map = textFileReader.getColumnBeansMap();
            ColumnBean bean = map.get("X");
            ColumnBean bean1 = map.get("Y");
            bean.getValues();
            bean1.getValues();


        }



    }



























}


























































































