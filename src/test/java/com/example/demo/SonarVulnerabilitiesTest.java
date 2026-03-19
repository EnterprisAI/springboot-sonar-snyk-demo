package com.example.demo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SonarVulnerabilitiesTest {

    @Test
    void testHardcodedPassword() {
        SonarVulnerabilities sv = new SonarVulnerabilities();
        String pw = sv.getHardcodedPassword();
        assertEquals("P@ssw0rd123", pw);
    }

    @Test
    void testCompareStringsWrong() {
        SonarVulnerabilities sv = new SonarVulnerabilities();
        // intentionally relies on bad comparison; behavior may be false
        boolean result = sv.compareStringsWrong(new String("literal"));
        assertFalse(result);
    }

    @Test
    void testWeakHash() {
        SonarVulnerabilities sv = new SonarVulnerabilities();
        String h = sv.weakHash("data");
        assertNotNull(h);
        assertTrue(h.length() > 0);
    }

    @Test
    void testInsecureRandom() {
        SonarVulnerabilities sv = new SonarVulnerabilities();
        int r = sv.insecureRandom();
        // Just ensure it runs
        assertTrue(true);
    }

    @Test
    void testSqlInjectionPattern() {
        SonarVulnerabilities sv = new SonarVulnerabilities();
        String q = sv.sqlInjection("alice");
        assertTrue(q.contains("alice"));
    }

    @Test
    void testXmlParsing() throws Exception {
        SonarVulnerabilities sv = new SonarVulnerabilities();
        String xml = "<root></root>";
        String root = sv.parseXmlRoot(xml);
        assertEquals("root", root);
    }

    @Test
    void testProcessBuilding() {
        SonarVulnerabilities sv = new SonarVulnerabilities();
        String cmd = "echo";
        String built = sv.buildProcessCommand(cmd);
        assertTrue(built.contains("echo"));
    }

    @Test
    void testEmptyCatch() {
        SonarVulnerabilities sv = new SonarVulnerabilities();
        sv.emptyCatch();
        assertTrue(true);
    }

    @Test
    void testResourceLeak() throws Exception {
        ResourceVulnerabilities rv = new ResourceVulnerabilities();
        // Create a small temp file and call readFileBad
        java.nio.file.Path p = java.nio.file.Files.createTempFile("sonar", "txt");
        java.nio.file.Files.writeString(p, "hello");
        byte[] data = rv.readFileBad(p.toString());
        assertNotNull(data);
    }

    @Test
    void testDeprecatedUse() {
        ResourceVulnerabilities rv = new ResourceVulnerabilities();
        String s = rv.useDeprecated();
        assertNotNull(s);
    }
}
