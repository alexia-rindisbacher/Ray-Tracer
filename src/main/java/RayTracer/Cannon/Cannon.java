package RayTracer.Cannon;

import RayTracer.Canvas;
import RayTracer.Color;
import RayTracer.Tuple;

public class Cannon {

    public static void main(String[] args){
        Cannon n = new Cannon();
        Tuple position = Tuple.point(0,1,0);
        Tuple velocity = Tuple.vector(1,1.8,0);
        velocity.normalize();
        velocity.multiply(11.25);

        Projectile p = new Projectile(position, velocity);
        Canvas c = new Canvas(900, 550);
        Tuple gravity = Tuple.vector(0,-0.1,0);
        Tuple wind = Tuple.vector(-0.01,0, 0);
        Environment e = new Environment(gravity, wind);

        int ticks = 0;
        while (p.position.getY() >= 0){
            p = n.tick(e, p);
            int m = (int) p.position.getY();
            if (m <= 0){
                c.writePixel((int) p.position.getX(), 0, new Color(1,0,0));
            }
            else {
                c.writePixel((int) p.position.getX(), (c.getHeight() - (int) p.position.getY()), new Color(1, 0, 0));
            }
            System.out.println("New Position " + p.position);
            ticks++;
        }
        System.out.println("reached ground after " + ticks + " ticks.");
        c.stringToPPM("randomProjectile");

    }



    public Projectile tick(Environment env, Projectile p){
        Tuple position = p.position.add(p.velocity);
        Tuple velocity = p.velocity.add(env.gravity).add(env.wind);
        return new Projectile(position, velocity);
    }
}
