package mapper;

import pojo.XslAccount;

public interface XslAccountMapper {
    int insert(XslAccount xslAccount);
    XslAccount selectLastOne();
}
