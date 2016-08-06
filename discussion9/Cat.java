/**
 * Created by Administrator on 2016/7/23.
 */
public class Cat extends Animal {
    public Cat(String name, int age) {
        super(name,age);
        this.name = name;
        this.age = age;
        this.noise = "Meow!";
    }
    @Override
    public void greet() {
        System.out.println("Cat " + name + " says: " + makeNoise());
    }
    @Override
    public String makeNoise() {
        if (age < 5) {
            return noise.toUpperCase();
        } else {
            return noise;
        }
    }
}
