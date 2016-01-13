package njust.listener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import njust.model.Right;
import njust.service.RightService;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

/**
 * 初始化权限监听器，在spring 初始化完成后立即调用
 * 
 * @author Administrator
 *
 */
@Component
@SuppressWarnings("rawtypes")
public class IniRightListener implements ApplicationListener,
		ServletContextAware {
	private ServletContext sc;
	@Resource
	RightService rightService;

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		if (event instanceof ContextRefreshedEvent) {
			List<Right> rights = rightService.findAllEntities();
			Map<String, Right> map = new HashMap<>();
			for (Right r : rights)
				map.put(r.getRightUrl(), r);
			if (sc != null) {
				sc.setAttribute("all_rights_map", map);
				System.out.println("初始化所有权限到appliction");
			}
		}

	}

	// 注入servlcetContext对象
	@Override
	public void setServletContext(ServletContext servletContext) {
		sc = servletContext;
	}

}
