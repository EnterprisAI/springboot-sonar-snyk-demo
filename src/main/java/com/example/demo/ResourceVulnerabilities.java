package com.example.demo;

import java.io.FileInputStream;
import java.io.InputStream;

public class ResourceVulnerabilities {

    // Method that intentionally does not close InputStream properly
    public byte[] readFileBad(String path) throws Exception {
        InputStream is = new FileInputStream(path); // Resource leak if exception occurs
        byte[] buf = new byte[is.available()];
        is.read(buf);
        // forgot to close stream
        return buf;
    }

    // Uses deprecated or insecure APIs
    @SuppressWarnings("deprecation")
    public String useDeprecated() {
        java.util.Date d = new java.util.Date(2020, 1, 1); // Deprecated constructor
        return d.toString();
    }
}
