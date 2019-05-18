package quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import pojo.UpdateTaskVo;
import resource.TaskOperateResource;
import service.TaskMqService;
import util.GsonSingle;
import vo.ResBaseVo;

import javax.annotation.Resource;
import java.util.List;

public class CalcelTaskJob{
    private static final Logger log = LoggerFactory.getLogger(CalcelTaskJob.class);
    @Resource
    private TaskOperateResource taskOperateResource;
    @Autowired
    private TaskMqService taskMqService;

    public void execute(){
        ResBaseVo resBaseVo = taskOperateResource.calcelTaskDDL();
        List<String> exParam = (List<String>) resBaseVo.getExParam();

        log.info("the Task cancel :{}", GsonSingle.getGson().toJson(exParam));

        exParam.forEach((var) -> {
            UpdateTaskVo updateTaskVo = new UpdateTaskVo();
            updateTaskVo.setTaskId(var);
            updateTaskVo.setState((byte) -2);
            taskMqService.updateEsTask(updateTaskVo);
        });
    }
}
