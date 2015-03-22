package suhailmehta.main.downloadinglibrary.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Created by suhailmehta on 18/03/15.
 */
public class ReadThreadTypeAnnotation {

    //@parameter ThreadType.class
    public static String readAnnotationOn(Class clazz)
    {
        String mode = null;
        try {
            Method method = clazz.getMethod("onSuccess", new Class[]{});

            Annotation[] annotations = method.getAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation instanceof ThreadType) {
                    ThreadType fileInfo = (ThreadType) annotation;
                    mode = fileInfo.mode();
                }
            }
        }catch (NoSuchMethodException e){
            e.printStackTrace();
        }
        return mode ;
    }
}
