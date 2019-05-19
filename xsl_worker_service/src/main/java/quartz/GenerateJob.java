package quartz;

import org.springframework.beans.factory.annotation.Autowired;
import service.GenerateAccount;

public class GenerateJob {
    @Autowired
    private  GenerateAccount generateAccount;
    public  void execute(){
        System.out.println(555555);
        generateAccount.generateAccount();
    }
}
