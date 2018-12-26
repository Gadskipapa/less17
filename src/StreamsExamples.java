import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StreamsExamples {

    public static void justRead() {
        FileInputStream fis = null;
        try {
            //fis = new FileInputStream("test/test.txt");
            fis = new FileInputStream("C:\\Users\\Java Core Student 1\\IdeaProjects\\less17\\test\\test.txt");
            System.out.println("file size in bytes: " + fis.available());
            int content;
            while ((content = fis.read()) != -1) {
                System.out.println((char) content);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public static void readWithResources() {
        try (FileInputStream fis = new FileInputStream("test9/test.txt")) {
            System.out.println("file size " + fis.available());
            int content;
            while ((content = fis.read()) != -1) {
                System.out.println((char) content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readAndWrite() {
        try (FileInputStream fis = new FileInputStream("test/test.txt");
             FileOutputStream fos = new FileOutputStream("test/result.txt")) {
            int content;
            while ((content = fis.read()) != -1) {
                System.out.println((char) content);
                fos.write(content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readAndWriteWithoutClosing() {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream("test/test.txt");
            fos = new FileOutputStream("test/result.txt");
            int content;
            while ((content = fis.read()) != -1) {
                System.out.println((char) content);
                fos.write(content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void bufferedInputStream() {
        InputStream inStream = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bus = null;
        try {
            inStream = new FileInputStream("test/test.txt");
            bis = new BufferedInputStream(inStream);
            bus = new BufferedOutputStream(new FileOutputStream("test/buff_res.txt"));
            while (bis.available() > 0) {
                char c = (char) bis.read();
                System.out.println("char: " + c);
                bus.write(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inStream != null && bis != null && bus != null) {
                try {
                    inStream.close();
                    bis.close();
                    bus.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

/*    public static void bufferedInputStreamWithResources() {
        try (FileInputStream fis = new FileInputStream("test/test.txt");
             FileOutputStream bus = new FileOutputStream("test/buf_res/txt");
             BufferedOutputStream fos = new BufferedOutputStream((fos);) {
                 while (bus.available() > 0) {
                     char c = (char) bus.read();
                     System.out.println("char: " + c);
                     bus.write(c);
                 }
        } ....
    }*/

    public static List<String> getLinesFromFile() {
        File file = new File("test/test.txt");
        List<String> result = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                result.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void writeByLines () {
        List<String> strings = new ArrayList<>();
        strings.add("one");
        strings.add("two");
        strings.add("three");
        Buffered writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("test/buf_string_res.txt"));
            for (String s: strings) {
                writer.write(s);
            }
            catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (writer != null) {
                        writer.close();
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    public static void main(String[] args) {
        // justRead();
        // readWithResources();
        // readAndWrite();
        // readAndWriteWithoutClosing();
        // bufferedInputStream();
        List<String> file = getLinesFromFile();
        for (String s : file) {
            System.out.println(s);
        }
    }
}