package test;

import mapper.XslAccountMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pojo.XslAccount;
import service.TaskMqService;
import xsl_worker_main.WorkerApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WorkerApplication.class)
public class NewTest {
    @Autowired
    TaskMqService taskMqService;
    @Test
    public void foo(){

    }

}
