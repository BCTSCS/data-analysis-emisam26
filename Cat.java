public class Cat{
    private String name;
    private int age;
    public Cat (String n, int a){
        name = n;
        age = a;
    }
    public String getName(){
        return name;
    }
    public Integer getAge(){
        return age;
    }
    public String toString(){
        return name + "-" + age;
    }
    public static void main(String[] args){
        Cat c1 = new Cat ("Whiskers", 3);
        System.out.println(c1.getAge());
    }
}