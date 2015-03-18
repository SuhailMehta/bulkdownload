package suhail.downloader.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Created by suhailmehta on 18/03/15.
 */
public class ReadThreadTypeAnnotation {

     //@parameter ThreadType.class
    public static String readAnnotationOn(Class clazz) throws NoSuchMethodException
    {
        Method method = clazz.getMethod("getString", new Class[]{}); //TODO : change method name at later stages
        String mode = null ;
        Annotation[] annotations = method.getAnnotations();
        for (Annotation annotation : annotations)
        {
            if (annotation instanceof ThreadType)
            {
                ThreadType fileInfo = (ThreadType) annotation;
                mode = fileInfo.mode();
            }
        }
        return mode ;
    }
}
