package com.drools.droolosdemo.test;

import com.drools.droolosdemo.DroolosDemoApplication;
import com.drools.droolosdemo.entity.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author hwb
 * @version 1.0
 * @date 2021/5/27 0027 15:34
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DroolosDemoApplication.class})
public class TestDrools {


    /**
     * 其实用springboot都没啥用，直接用psvm都能跑，不用psvm用junit也能跑，剩下就是套进去就完事了
     */
    @Test
    public void test() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer container = kieServices.getKieClasspathContainer();
        // 这个参数的value  必须与kmodule.xml文件内的ksession标签的name相等，不然会报错
        KieSession statefulKieSession = container.newKieSession("test");

        Person person = new Person();
        person.setAge(12);
        person.setName("Test");

        statefulKieSession.insert(person);
        statefulKieSession.fireAllRules();
        statefulKieSession.dispose();

    }
}
