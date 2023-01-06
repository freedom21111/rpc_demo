import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Text {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        A a = new A();
        Method sum = a.getClass().getMethod("sum", int.class, int.class);
       Object o= sum.invoke(a,1,2);
        System.out.println(o);
    }
}
class A{
    public int sum(int a1,int a2){
        return a1+a2;
    }
}