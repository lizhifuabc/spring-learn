import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * @description ${tableInfo.tableComment}
 * @author ${paramInfo.authorName}
 * @date ${.now?string('yyyy-MM-dd')}
 */
public interface ${tableInfo.className}Mapper {

    /**
    * 新增
    * @author ${paramInfo.authorName}
    * @date ${.now?string('yyyy/MM/dd')}
    **/
    int insert(${tableInfo.className} ${tableInfo.className?uncap_first});

    /**
    * 刪除
    * @author ${paramInfo.authorName}
    * @date ${.now?string('yyyy/MM/dd')}
    **/
    int deleteByPrimaryKey(Long ${tableInfo.keyProperty});

    /**
    * 更新
    * @author ${paramInfo.authorName}
    * @date ${.now?string('yyyy/MM/dd')}
    **/
    int updateByPrimaryKey(${tableInfo.className} ${tableInfo.className?uncap_first});

    /**
    * 查询 根据主键查询
    * @author ${paramInfo.authorName}
    * @date ${.now?string('yyyy/MM/dd')}
    **/
    ${tableInfo.className} selectByPrimaryKey(Long ${tableInfo.keyProperty});

    /**
    * 查询 分页查询
    * @author ${paramInfo.authorName}
    * @date ${.now?string('yyyy/MM/dd')}
    **/
    List<${tableInfo.className}> selectPageList(int offset,int pagesize);

    /**
    * 查询 count
    * @author ${paramInfo.authorName}
    * @date ${.now?string('yyyy/MM/dd')}
    **/
    int count();

}
