package com.ichings.generator.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件读写
 *
 * @author 宋欢
 */
public final class FileUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileUtils.class);

    /**
     * 路径分隔符
     */
    public static final String SEPARATOR = File.separator;

    /**
     * 扩展名分隔符
     */
    public static final String EXTENSION_SEPARATOR = ".";

    /**
     * 默认的缓冲大小
     */
    private static final int BUFFER_SIZE = 1024;

    /**
     * 读回调
     */
    public interface ReadCallback<T> {
        /**
         * Use Buffered Reader
         *
         * @param reader BufferedReader
         * @return 文件内容
         * @throws IOException 读失败
         */
        T doInBuffer(BufferedReader reader) throws IOException;
    }

    private FileUtils() {
    }

    /**
     * 读文本，按行读
     */
    public static List<String> getLines(File f) throws IOException {
        return FileUtils.read(f, reader -> {
            List<String> result = new ArrayList<>();

            String line;
            while ((line = reader.readLine()) != null) {
                result.add(line);
            }

            return result;
        });
    }

    /**
     * 读文本
     */
    public static String getChars(File f) throws IOException {
        return read(f, reader -> {
            StringBuilder result = new StringBuilder();

            int num;
            char[] buf = new char[BUFFER_SIZE];
            while ((num = reader.read(buf)) != -1) {
                result.append(buf, 0, num);
            }

            return result.toString();
        });
    }

    /**
     * 读文件
     */
    public static <T> T read(File f, ReadCallback<T> action) throws IOException {
        AssertUtils.nonNull(f, "f");
        AssertUtils.nonNull(action, "action");
        Assert.checkArgument(f.exists(), String.format("target not exists, path: %s", f.getAbsolutePath()));
        Assert.checkArgument(f.isFile(), String.format("target must be a file, path: %s", f.getAbsolutePath()));

        try (FileInputStream fileStream = new FileInputStream(f);
             InputStreamReader streamReader = new InputStreamReader(fileStream);
             BufferedReader bufferReader = new BufferedReader(streamReader)) {
            return action.doInBuffer(bufferReader);
        }
    }

    /**
     * 写文本
     *
     * @param append 追加写？
     */
    public static void write(File f, String s, final boolean append) throws IOException {
        AssertUtils.nonNull(f, "f");
        AssertUtils.nonNull(s, "s");
        if (f.exists()) {
            Assert.checkArgument(f.isFile(), String.format("exists's target must be a file, path: %s", f.getAbsolutePath()));
        }

        try (FileWriter writer = new FileWriter(f, append)) {
            writer.write(s);
            writer.flush();
        }
    }

    /**
     * 写文件
     *
     * @param append 追加写？
     */
    public static void write(File f, byte[] data, final boolean append) throws IOException {
        AssertUtils.nonNull(f, "f");
        AssertUtils.nonNull(data, "data");
        if (f.exists()) {
            Assert.checkArgument(f.isFile(), String.format("exists's target must be a file, path: %s", f.getAbsolutePath()));
        }

        try (FileOutputStream outStream = new FileOutputStream(f, append)) {
            outStream.write(data);
            outStream.flush();
        }
    }

    /**
     * 创建目录
     */
    public static boolean mkdirs(String directory) {
        AssertUtils.nonEmpty(directory, "directory");

        return mkdirs(new File(directory));
    }

    /**
     * 创建目录
     */
    public static boolean mkdirs(File directory) {
        AssertUtils.nonNull(directory, "directory");

        if (directory.exists()) {
            if (directory.isDirectory()) {
                return true;
            }

            throw new IllegalArgumentException(String.format("exists's target must be a directory, path: %s", directory.getAbsolutePath()));
        }

        return directory.mkdirs();
    }

    /**
     * 递归删除目录
     * if directory not exists return true
     */
    public static boolean rmdir(String directory) {
        AssertUtils.nonEmpty(directory, "directory");

        try {
            return rmdir(new File(directory));
        } catch (IOException e) {
            LOGGER.error("rmdir failed, directory: {}, throwable: ", directory, e);
            return false;
        }
    }

    /**
     * 递归删除目录
     * if directory not exists return true
     */
    public static boolean rmdir(File directory) throws IOException {
        AssertUtils.nonNull(directory, "directory");

        if (directory.exists()) {
            if (directory.isDirectory()) {
                return deleteRecursively(directory.toPath());
            }

            throw new IllegalArgumentException(String.format("exists's target must be a directory, path: %s", directory.getAbsolutePath()));
        } else {
            return true;
        }
    }

    /**
     * 删除文件和目录
     * if root not exists return false
     */
    public static boolean deleteRecursively(Path root) throws IOException {
        AssertUtils.nonNull(root, "root");

        if (!Files.exists(root)) {
            return false;
        }

        Files.walkFileTree(root, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Files.delete(file);
                return super.visitFile(file, attrs);
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                Files.delete(dir);
                return FileVisitResult.CONTINUE;
            }
        });

        return true;
    }

    /**
     * 取扩展名
     */
    public static String getExtension(String fileName) {
        if (fileName == null || "".equals(fileName)) {
            return "";
        }

        int num = fileName.lastIndexOf(EXTENSION_SEPARATOR);
        if (num < 0) {
            return "";
        }

        return fileName.substring(num + 1);
    }

}
