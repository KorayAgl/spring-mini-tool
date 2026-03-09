package dev.springminitool.generator;

import dev.springminitool.util.FileWriter;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Generates a minimal Spring Boot project scaffold.
 *
 * The generated structure follows standard Maven conventions and includes
 * a working pom.xml, a main application class, a sample controller,
 * application.properties, and a basic README.
 */
public class ProjectScaffoldGenerator {

    private final FileWriter fileWriter;

    public ProjectScaffoldGenerator(FileWriter fileWriter) {
        this.fileWriter = fileWriter;
    }

    public void generate(Path baseDir) throws IOException {
        String groupPath = "com/example/myapp";
        String packageName = "com.example.myapp";

        Path srcMain = baseDir.resolve("src/main/java/" + groupPath);
        Path srcTest = baseDir.resolve("src/test/java/" + groupPath);
        Path resources = baseDir.resolve("src/main/resources");

        fileWriter.createDirectory(srcMain.resolve("controller"));
        fileWriter.createDirectory(srcMain.resolve("service"));
        fileWriter.createDirectory(srcMain.resolve("model"));
        fileWriter.createDirectory(srcTest);
        fileWriter.createDirectory(resources);

        fileWriter.writeFile(baseDir.resolve("pom.xml"), buildPomXml());
        fileWriter.writeFile(baseDir.resolve(".gitignore"), buildGitignore());
        fileWriter.writeFile(baseDir.resolve("README.md"), buildReadme());

        fileWriter.writeFile(
            srcMain.resolve("Application.java"),
            buildApplicationClass(packageName)
        );
        fileWriter.writeFile(
            srcMain.resolve("controller/HelloController.java"),
            buildHelloController(packageName)
        );
        fileWriter.writeFile(
            resources.resolve("application.properties"),
            buildApplicationProperties()
        );
        fileWriter.writeFile(
            srcTest.resolve("ApplicationTest.java"),
            buildApplicationTest(packageName)
        );
    }

    private String buildPomXml() {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
            + "<project xmlns=\"http://maven.apache.org/POM/4.0.0\"\n"
            + "         xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n"
            + "         xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0\n"
            + "             http://maven.apache.org/xsd/maven-4.0.0.xsd\">\n\n"
            + "    <modelVersion>4.0.0</modelVersion>\n\n"
            + "    <parent>\n"
            + "        <groupId>org.springframework.boot</groupId>\n"
            + "        <artifactId>spring-boot-starter-parent</artifactId>\n"
            + "        <version>3.2.3</version>\n"
            + "    </parent>\n\n"
            + "    <groupId>com.example</groupId>\n"
            + "    <artifactId>myapp</artifactId>\n"
            + "    <version>0.0.1-SNAPSHOT</version>\n"
            + "    <name>myapp</name>\n\n"
            + "    <properties>\n"
            + "        <java.version>17</java.version>\n"
            + "    </properties>\n\n"
            + "    <dependencies>\n"
            + "        <dependency>\n"
            + "            <groupId>org.springframework.boot</groupId>\n"
            + "            <artifactId>spring-boot-starter-web</artifactId>\n"
            + "        </dependency>\n"
            + "        <dependency>\n"
            + "            <groupId>org.springframework.boot</groupId>\n"
            + "            <artifactId>spring-boot-starter-test</artifactId>\n"
            + "            <scope>test</scope>\n"
            + "        </dependency>\n"
            + "    </dependencies>\n\n"
            + "    <build>\n"
            + "        <plugins>\n"
            + "            <plugin>\n"
            + "                <groupId>org.springframework.boot</groupId>\n"
            + "                <artifactId>spring-boot-maven-plugin</artifactId>\n"
            + "            </plugin>\n"
            + "        </plugins>\n"
            + "    </build>\n\n"
            + "</project>\n";
    }

    private String buildGitignore() {
        return "target/\n"
            + ".idea/\n"
            + "*.iml\n"
            + ".vscode/\n"
            + ".DS_Store\n"
            + "*.log\n";
    }

    private String buildReadme() {
        return "# myapp\n\n"
            + "A Spring Boot application generated with "
            + "[spring-mini-tool](https://github.com/your-username/spring-mini-tool).\n\n"
            + "## Getting started\n\n"
            + "```bash\n"
            + "mvn spring-boot:run\n"
            + "```\n\n"
            + "The application starts on port 8080 by default.\n\n"
            + "## Endpoints\n\n"
            + "| Method | Path   | Description        |\n"
            + "|--------|--------|--------------------|\n"
            + "| GET    | /hello | Returns a greeting |\n";
    }

    private String buildApplicationClass(String packageName) {
        return "package " + packageName + ";\n\n"
            + "import org.springframework.boot.SpringApplication;\n"
            + "import org.springframework.boot.autoconfigure.SpringBootApplication;\n\n"
            + "@SpringBootApplication\n"
            + "public class Application {\n\n"
            + "    public static void main(String[] args) {\n"
            + "        SpringApplication.run(Application.class, args);\n"
            + "    }\n"
            + "}\n";
    }

    private String buildHelloController(String packageName) {
        return "package " + packageName + ".controller;\n\n"
            + "import org.springframework.web.bind.annotation.GetMapping;\n"
            + "import org.springframework.web.bind.annotation.RestController;\n\n"
            + "@RestController\n"
            + "public class HelloController {\n\n"
            + "    @GetMapping(\"/hello\")\n"
            + "    public String hello() {\n"
            + "        return \"Hello from Spring Boot!\";\n"
            + "    }\n"
            + "}\n";
    }

    private String buildApplicationProperties() {
        return "spring.application.name=myapp\n"
            + "server.port=8080\n";
    }

    private String buildApplicationTest(String packageName) {
        return "package " + packageName + ";\n\n"
            + "import org.junit.jupiter.api.Test;\n"
            + "import org.springframework.boot.test.context.SpringBootTest;\n\n"
            + "@SpringBootTest\n"
            + "class ApplicationTest {\n\n"
            + "    @Test\n"
            + "    void contextLoads() {\n"
            + "    }\n"
            + "}\n";
    }
}
