package yu.mthgh123.booksmall.config;

import yu.mthgh123.booksmall.common.Constants;
import yu.mthgh123.booksmall.interceptor.AdminLoginInterceptor;
import yu.mthgh123.booksmall.interceptor.BooksMallCartNumberInterceptor;
import yu.mthgh123.booksmall.interceptor.BooksMallLoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class BooksMallWebMvcConfigurer implements WebMvcConfigurer {

    @Autowired
    private AdminLoginInterceptor adminLoginInterceptor;
    @Autowired
    private BooksMallLoginInterceptor booksMallLoginInterceptor;
    @Autowired
    private BooksMallCartNumberInterceptor booksMallCartNumberInterceptor;

    public void addInterceptors(InterceptorRegistry registry) {
        // 添加一个拦截器，拦截以/admin为前缀的url路径（后台登陆拦截）
        registry.addInterceptor(adminLoginInterceptor)
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/login")
                .excludePathPatterns("/admin/dist/**")
                .excludePathPatterns("/admin/plugins/**");
        // 购物车中的数量统一处理
        registry.addInterceptor(booksMallCartNumberInterceptor)
                .excludePathPatterns("/admin/**")
                .excludePathPatterns("/register")
                .excludePathPatterns("/login")
                .excludePathPatterns("/logout");
        // 商城页面登陆拦截
        registry.addInterceptor(booksMallLoginInterceptor)
                .excludePathPatterns("/admin/**")
                .excludePathPatterns("/register")
                .excludePathPatterns("/login")
                .excludePathPatterns("/logout")
                .addPathPatterns("/goods/detail/**")
                .addPathPatterns("/shop-cart")
                .addPathPatterns("/shop-cart/**")
                .addPathPatterns("/saveOrder")
                .addPathPatterns("/orders")
                .addPathPatterns("/orders/**")            
                .addPathPatterns("/personal")
                .addPathPatterns("/personal/updateInfo")
                .addPathPatterns("/selectPayType")
                .addPathPatterns("/payPage");
    }

    /*为了提高项目的可移植性，将访问"/upload/**"和"/goods-img/**"路径的均转化为访问：Constants.FILE_UPLOAD_DIC
    你可能会问，为什么当初在往数据库里存入图片的url时，不直接存入图片在本地的路径呢？即Constants.FILE_UPLOAD_DIC。答曰：为了提高可移植性，
    当把存储图片的文件夹设置在不同路径时，那么存入数据库中的路径固定后，当其他人再要使用该项目时，就必须把图片文件夹放在数据库地址映射的
    路径下才行，这样可能造成某些麻烦，所以通过设定一个Constants.FILE_UPLOAD_DIC来实现将图片文件夹放在任意位置的路径均可，只需要设定对应的Constants.FILE_UPLOAD_DIC即可
    */
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**").addResourceLocations("file:" + Constants.FILE_UPLOAD_DIC);
        registry.addResourceHandler("/books-img/**").addResourceLocations("file:" + Constants.FILE_UPLOAD_DIC);
    }
}
