package RayTracer.Cannon;

import RayTracer.Tuple;

public class Cannon {

    public static void main(String[] args){
        Cannon n = new Cannon();
        Tuple position = Tuple.point(0,1,0);
        Tuple velocity = Tuple.vector(1,1,0);
        velocity.normalize();

        Projectile p = new Projectile(position, velocity);

        Tuple gravity = Tuple.vector(0,-0.1,0);
        Tuple wind = Tuple.vector(-0.01,0, 0);
        Environment e = new Environment(gravity, wind);

        int ticks = 0;
        while (p.position.getY() >= 0){
            p = n.tick(e, p);
            System.out.println("New Position " + p.position);
            ticks++;
        }
        System.out.println("reached ground after " + ticks + " ticks.");
    }



    public Projectile tick(Environment env, Projectile p){
        Tuple position = p.position.add(p.velocity);
        Tuple velocity = p.velocity.add(env.gravity).add(env.wind);
        return new Projectile(position, velocity);
    }
}
