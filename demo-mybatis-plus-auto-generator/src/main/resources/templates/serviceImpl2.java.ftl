package ${package.ServiceImpl};

import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import com.alicms.ex.common.core.mybatis.BaseMapper;
import ${superServiceImplClassPackage};
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * <p>
 * ${table.comment!} 服务实现类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Service
<#if kotlin>
open class ${table.serviceImplName} : ${superServiceImplClass}(), ${table.serviceName} {

}
<#else>
public class ${table.serviceImplName} extends ${superServiceImplClass} implements ${table.serviceName} {
    @Resource
    private ${table.mapperName} ${table.mapperName ? uncap_first};

    @Override
    public BaseMapper getMapper() {
        return ${table.mapperName ? uncap_first};
    }
}
</#if>
