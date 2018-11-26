package ca.mydemo.tcoffice;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

public class ServletInitializer //implements WebApplicationInitializer
        extends SpringBootServletInitializer
        //extends AbstractAnnotationConfigDispatcherServletInitializer
{

//	@Override
//	protected Class<?>[] getRootConfigClasses() {
//		return new Class[] { ServletInitializer.class, DataSourceConfig.class, TCOfficeWebConfig.class };
//	}
//
//	@Override
//	protected Class<?>[] getServletConfigClasses() {
//		return null;
//	}
//
//	@Override
//	protected String[] getServletMappings() {
//		return new String[] { "/" };
//	}


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(TcOffice2Application.class, DataSourceConfig.class, TCOfficeWebConfig.class);
    }


//			public void onStartup(ServletContext container) throws ServletException {
//
//				AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
//				ctx.register(TCOfficeWebConfig.class);
//				ctx.setServletContext(container);
//
//				ServletRegistration.Dynamic servlet = container.addServlet("dispatcher", new DispatcherServlet(ctx));
//
//				servlet.setLoadOnStartup(1);
//				servlet.addMapping("/");
//			}
}
