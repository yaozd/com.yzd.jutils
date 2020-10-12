package com.yzd.jutils.velocityExt;

import com.yzd.jutils.fileExt.ResourceUtils;
import com.yzd.jutils.toolHeaven.util.io.FileUtil;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * 模板工具
 * </pre>
 *
 * @author <a href="https://github.com/rememberber">RememBerBer</a>
 * @since 2019/1/5.
 */
public class TemplateUtil {

    private static VelocityEngine velocityEngine;

    static {
        velocityEngine = new VelocityEngine();
        velocityEngine.init();
    }

    public static String evaluate(String content, VelocityContext velocityContext) {
        StringWriter writer = new StringWriter();
        velocityEngine.evaluate(velocityContext, writer, "", content);
        return writer.toString();
    }

    public static void main(String[] args) {
        VelocityContext ctx = new VelocityContext();
        ctx.put("name", "Velocity");
        List list = new ArrayList();
        list.add("1");
        list.add("2");
        //
        ctx.put("list", list);
        //
        String content=  FileUtil.getFileContent(ResourceUtils.toPath("hellovelocity.vm"));
        for (int i = 0; i < 1_000_000; i++) {
            //效率高，速度快
            "tt".replace("t","1");
            //效率不高，速度很慢
            //String evaluate = TemplateUtil.evaluate(content, ctx);
            //System.out.println(evaluate);
        }

    }
}