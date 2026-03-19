package com.example.demo;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class SonarVulnerabilities {

    // Hardcoded credential (should be detected by Sonar)
    public String getHardcodedPassword() {
        String password = "P@ssw0rd123"; // S107: hardcoded credentials
        return password;
    }

    // Incorrect string comparison using '==' instead of equals
    public boolean compareStringsWrong(String a) {
        if (a == "literal") { // S1871-like bug: reference equality
            return true;
        }
        return false;
    }

    // Weak hashing algorithm (MD5) usage
    public String weakHash(String input) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5"); // S523: weak crypto
            byte[] digest = md.digest(input.getBytes(java.nio.charset.StandardCharsets.UTF_8));
            return java.util.HexFormat.of().formatHex(digest);
        } catch (Exception e) {
            return null;
        }
    }

    // Predictable random number generator usage
    public int insecureRandom() {
        java.util.Random r = new java.util.Random(); // S2293: predictable RNG
        return r.nextInt();
    }

    // String concatenation building SQL (SQL injection pattern)
    public String sqlInjection(String userInput) {
        String query = "SELECT * FROM users WHERE name = '" + userInput + "'"; // S2076-like
        return query;
    }

    // XML parsing without disabling external entities (potential XXE)
    public String parseXmlRoot(String xml) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        // Intentionally NOT disabling external entities to trigger analysis warnings
        DocumentBuilder builder = factory.newDocumentBuilder();
        org.w3c.dom.Document doc = builder.parse(new org.xml.sax.InputSource(new java.io.StringReader(xml)));
        return doc.getDocumentElement().getNodeName();
    }

    // Example of Runtime.exec-like construction (dangerous pattern if used with user input)
    public String buildProcessCommand(String cmd) {
        // Do not execute; demonstrate construction pattern. Sonar flags use of Runtime/ProcessBuilder with untrusted data.
        ProcessBuilder pb = new ProcessBuilder(cmd);
        return String.join(" ", pb.command());
    }

    // Empty catch block (bad practice, often flagged)
    public void emptyCatch() {
        try {
            int x = 1 / 0;
        } catch (Exception e) {
            // intentionally empty to surface analyzer warning
        }
    }
}
