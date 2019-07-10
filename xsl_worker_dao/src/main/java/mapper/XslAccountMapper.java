package mapper;

import org.apache.ibatis.annotations.Mapper;
import pojo.XslAccount;
@Mapper
public interface XslAccountMapper {
    int insert(XslAccount xslAccount);
    XslAccount selectLastOne();
}
