package cn.willian.coding.base;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-10-16 22:10:41
 */
public class ClassSeqDemo {

    public static void main(String[] args) {
        Child child = new Child();
        System.out.println(Child.a);
    }

    static class Parent {

        static {
            System.out.println("1、父类静态代码块被执行");
        }

        {
            System.out.println("3、父类同步块被执行");
        }

        public Parent() {
            System.out.println("4、父类构造方法被执行");
        }
    }


    static class Child extends Parent {
        public static int a = 330;

        static {
            System.out.println("a=="+a);
            System.out.println("2、子类静态代码块被执行");
        }

        {
            System.out.println("5、子类同步块被执行");
        }

        public Child() {
            System.out.println("6、子类构造方法被执行");
        }
    }
}


