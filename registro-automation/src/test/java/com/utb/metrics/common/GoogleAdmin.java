package com.utb.metrics.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GoogleAdmin {

    public static void initChrome() throws IOException {
        String os = System.getProperty("os.name").toLowerCase();
        String chromePath = getChromePath(os);

        ProcessBuilder builder = new ProcessBuilder(
                chromePath,
                "--remote-debugging-port=9222",
                "--user-data-dir=" + (os.contains("win") ? "C:\\chrome-dev-profile" : "/tmp/chrome-dev-profile"),
                "--start-maximized"
        );

        try {
            builder.start();
            Thread.sleep(3000);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static String getChromePath(String os) throws IOException {
        if (os.contains("win")) {
            ProcessBuilder pb = new ProcessBuilder(
                    "powershell",
                    "-command",
                    "(Get-ItemProperty 'HKLM:\\Software\\Microsoft\\Windows\\CurrentVersion\\App Paths\\chrome.exe').'(default)'"
            );
            Process process = pb.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            return reader.readLine();
        } else if (os.contains("mac")) {
            return "/Applications/Google Chrome.app/Contents/MacOS/Google Chrome";
        } else {
            throw new RuntimeException("Unsupported OS: " + os);
        }
    }

    public static void closeRemoteDebuggingSession() {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c",
                    "netstat -ano | findstr :9222");
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            String pid = null;

            while ((line = reader.readLine()) != null) {
                if (line.contains("9222")) {
                    pid = line.split(" ")[line.split(" ").length - 1];
                    break;
                }
            }

            if (pid != null) {
                Process killProcess = new ProcessBuilder("taskkill", "/F", "/PID", pid).start();
                killProcess.waitFor();
                System.out.println("Proceso con remote-debugging-port cerrado.");
            } else {
                System.out.println("No se encontró el proceso con puerto 9222.");
            }
        } catch (Exception e) {
            System.err.println("Error al cerrar la sesión de remote debugging: " + e.getMessage());
        }
    }
}
