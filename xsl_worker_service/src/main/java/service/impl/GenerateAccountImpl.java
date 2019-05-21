package service.impl;

import com.xsl.user.UserInfoResouce;
import mapper.XslAccountMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.XslAccount;
import resource.TaskInfoResource;
import service.GenerateAccount;
import util.GsonSingle;
import util.UUIDUtil;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Service
public class GenerateAccountImpl implements GenerateAccount {
    private static final Logger log = LoggerFactory.getLogger(GenerateAccountImpl.class);

@Resource
private TaskInfoResource taskInfoResource;
@Resource
private UserInfoResouce userInfoResouce;
@Autowired
private XslAccountMapper xslAccountMapper;
    @Override
    public void generateAccount() {

       try {

           XslAccount xslAccount=new XslAccount();
           XslAccount old=xslAccountMapper.selectLastOne();
           int totalTask=taskInfoResource.totalTask();
           BigDecimal totalMoney=taskInfoResource.totalMoney();
           int totalDoneTask=taskInfoResource.totalDoneTask();
           int totalUser=userInfoResouce.getUserNums();
           int oldtotalTask=0;
           BigDecimal oldtotalMoney =new BigDecimal(0);
           int oldtotalDoneTask=0;
           int oldtotalUser=0;
          if(old!=null){
           oldtotalTask=old.getTotalTask();
           oldtotalMoney=old.getTotalMoney();
           oldtotalDoneTask=old.getTotalDoneTask();
           oldtotalUser=old.getTotalUser();
          }
           xslAccount.setTotalUser(totalUser);
           xslAccount.setNewUser(totalUser-oldtotalUser);

           xslAccount.setTotalTask(totalTask);
           xslAccount.setNewTask(totalTask-oldtotalTask);

           xslAccount.setTotalDoneTask(totalDoneTask);
           xslAccount.setNewDoneTask(totalDoneTask-oldtotalDoneTask);

           xslAccount.setTotalMoney(totalMoney);
           xslAccount.setNewMoney(totalMoney.subtract(oldtotalMoney));

           xslAccount.setAccountId(UUIDUtil.getUUID());
           xslAccountMapper.insert(xslAccount);
           log.info("the Task cancel :{}", GsonSingle.getGson().toJson(xslAccount));


       }catch (Exception e){
           throw new RuntimeException(e);
       }

    }
}
