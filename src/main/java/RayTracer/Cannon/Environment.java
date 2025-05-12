package RayTracer.Cannon;

import RayTracer.Tuple;

public class Environment {
    Tuple gravity;
    Tuple wind;

    public Environment(Tuple g, Tuple w){
        gravity = g;
        wind = w;
    }
}