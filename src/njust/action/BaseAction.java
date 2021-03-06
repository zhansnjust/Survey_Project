package njust.action;

import java.lang.reflect.ParameterizedType;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * 
 */
public abstract class BaseAction<T> extends ActionSupport implements
		ModelDriven<T>, Preparable{

	private static final long serialVersionUID = 9180917383072055589L;
	public T model;
	protected String result;

	@SuppressWarnings("unchecked")
	public BaseAction() {
		try {
			ParameterizedType pt = (ParameterizedType) this.getClass()
					.getGenericSuperclass();
			Class<?> clazz = (Class<?>) pt.getActualTypeArguments()[0];
			model = (T) clazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void prepare() throws Exception {
		System.out.println("prepare is excute");
	}
	@Override
	public T getModel() {
		return model;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
}
