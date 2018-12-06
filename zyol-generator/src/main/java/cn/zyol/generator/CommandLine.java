package cn.zyol.generator;


import cn.zyol.generator.util.ArrayHelper;
import cn.zyol.generator.util.StringHelper;
import cn.zyol.generator.util.SystemHelper;
import freemarker.log.Logger;

import java.io.File;
import java.util.Scanner;

/**
 * 命令行工具类,可以直接运行
 *
 * @author badqiu
 */
public class CommandLine {

    public static void main(String[] args) throws Exception {
        //disable freemarker logging
        Logger.selectLoggerLibrary(Logger.LIBRARY_NONE);
        GeneratorProperties.reload();
        startProcess();
    }

    private static void startProcess() throws Exception {
        Scanner sc = new Scanner(System.in);
        String tempFile = getTemplateRootDir();
        tempFile = new File(tempFile).getAbsolutePath();
        System.out.println("templateRootDir:" + tempFile);
        printUsages();
        while (sc.hasNextLine()) {
            try {
                processLine(sc);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                Thread.sleep(700);
                printUsages();
            }
        }
    }

    private static void processLine(Scanner sc) throws Exception {
        GeneratorFacade facade = new GeneratorFacade();
        String cmd = sc.next();
        if ("gen".equals(cmd)) {
            String[] args = nextArguments(sc);
            if (args.length == 0) {
                return;
            }
            facade.g.setIncludes(getIncludes(args, 1));
            facade.generateByTable(args[0], getTemplateRootDir());
            if (SystemHelper.isWindowsOS) {
                Runtime.getRuntime().exec("cmd.exe /c start " + SystemHelper.getOutRootDir().replace('/', '\\'));
            }
        } else if ("del".equals(cmd)) {
            String[] args = nextArguments(sc);
            if (args.length == 0) {
                return;
            }
            facade.g.setIncludes(getIncludes(args, 1));
            facade.deleteByTable(args[0], getTemplateRootDir());
        } else if ("quit".equals(cmd)) {
            System.exit(0);
        } else {
            System.err.println(" [ERROR] unknow command:" + cmd);
        }
    }

    private static String getIncludes(String[] args, int i) {
        String includes = ArrayHelper.getValue(args, i);
        if (includes == null) {
            return null;
        }
        return includes.indexOf("*") >= 0 || includes.indexOf(",") >= 0 ? includes : includes + "/**";
    }

    private static String getTemplateRootDir() {
        String s = System.getProperty("templateRootDir", "template");
        return CommandLine.class.getResource("/").getPath() + File.separator + s;
    }

    private static void printUsages() {
        System.out.println("Usage:");
        System.out.println("\tgen table_name [include_path]: generate files by table_name");
        System.out.println("\tdel table_name [include_path]: delete files by table_name");
        System.out.println("\tgen * [include_path]: search database all tables and generate files");
        System.out.println("\tdel * [include_path]: search database all tables and delete files");
        System.out.println("\tgen table_name* [include_path]: search database all tables like table_name* and generate files");
        System.out.println("\tquit : quit");
        System.out.println("\t[include_path] subdir of templateRootDir,example: 1. dao  2. dao/**,service/**");
        System.out.print("please input command:");
    }

    private static String[] nextArguments(Scanner sc) {
        return StringHelper.tokenizeToStringArray(sc.nextLine(), " ");
    }
}
