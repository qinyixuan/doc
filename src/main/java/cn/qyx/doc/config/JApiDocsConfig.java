package cn.qyx.doc.config;

import io.github.yedaxia.apidocs.Docs;
import io.github.yedaxia.apidocs.DocsConfig;

/**
 * JApiDocs配置
 *
 * @author qinyixuan
 * @date 2021/10/14
 */
public class JApiDocsConfig {

    public static void main(String[] args) {
        DocsConfig config = new DocsConfig();
        // root project path
        config.setProjectPath("/Users/qinyixuan/IdeaProjects/NewChannel/doc");
        // project name
        config.setProjectName("用户管理");
        // api version
        config.setApiVersion("V1.0");
        // api docs target path
        config.setDocsPath("/Users/qinyixuan/IdeaProjects/NewChannel/doc/src/main/resources/static/japidocs");
        // auto generate
        config.setAutoGenerate(Boolean.TRUE);
        // execute to generate
        Docs.buildHtmlDocs(config);
    }
}
