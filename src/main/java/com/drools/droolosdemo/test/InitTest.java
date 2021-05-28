package com.drools.droolosdemo.test;

import com.drools.droolosdemo.entity.User;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.utils.KieHelper;

/**
 * @author hwb
 * @version 1.0
 * @date 2021/5/28 0028 8:41
 */
public class InitTest {

    /**
     * 动态规则
     */
    public static void main(String[] args) {
        String myRule = "import com.drools.droolosdemo.entity.User\n" +
                "\n" +
                "dialect  \"mvel\"\n" +
                "\n" +
                "rule \"age\"\n" +
                "    when\n" +
                "        $use : User(age<16 || age>50)\n" +
                "    then\n" +
                "        System.out.println(\"--------------\");\n" +
                "        System.out.println(\"这个人的年龄不符合要求！（基于动态加载）\");\n" +
                "        System.out.println(\"--------------\");\n" +
                "end";

        KieHelper helper = new KieHelper();
        helper.addContent(myRule, ResourceType.DRL);
        KieSession ksession = helper.build().newKieSession();

        User user = new User();
        user.setAge(51);
        user.setName("Test");

        ksession.insert(user);
        ksession.fireAllRules();
        ksession.dispose();
    }

}
