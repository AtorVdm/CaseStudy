package net.ponder2.managedobject;

import com.google.template.soy.SoyFileSet;
import com.google.template.soy.data.SoyMapData;
import com.google.template.soy.tofu.SoyTofu;
import net.ponder2.ManagedObject;
import net.ponder2.apt.Ponder2op;

import java.io.File;

/**
 * Created by Ator on 26/08/16.
 */
public class XSSFilter implements ManagedObject {

    @Ponder2op("create")
    public XSSFilter() {
    }

    @Ponder2op("user:filter:")
    public String filterUserText(String userName, String text) {
        SoyFileSet sfs = SoyFileSet.builder().add(new File("xss.soy")).build();
        SoyTofu tofu = sfs.compileToTofu();
        SoyTofu simpleTofu = tofu.forNamespace("xss.simple");
        String xssFiltered = simpleTofu.newRenderer(".filterHTML").setData(new SoyMapData("string", text)).render();
        return userName + ": " + xssFiltered;
    }
}
