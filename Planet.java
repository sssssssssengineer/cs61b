import java.lang.reflect.Array;

/**
 * Created by Administrator on 2016/7/20.
 */
public class Planet {
    // Its current x position
    public double xxPos;

    //Its current y position
    public double yyPos;

    //: Its current velocity in the x direction
    public double xxVel;

    //: Its current velocity in the y direction
    public double yyVel;

    //: Its mass
    public double mass;

    //: The name of an image in the images directory that depicts the planet
    public String imgFileName;

    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img){
    xxPos=xP;
    yyPos=yP;
    xxVel=xV;
    yyVel=yV;
    mass=m;
    imgFileName=img;
    }
    public Planet (Planet p){
        xxPos=p.xxPos;
        yyPos=p.yyPos;
        xxVel=p.xxVel;
        yyVel=p.yyVel;
        mass=p.mass;
        imgFileName=p.imgFileName;

    }

    public double calcForceExertedByX(Planet p){
        double dx=p.xxPos-this.xxPos;
        double Fx=this.calcForceExertedBy(p)*dx/this.calcDistance(p);
        return Fx;
    }
    public double calcForceExertedByY(Planet p){
        double dy=p.yyPos-this.yyPos;
        double Fy=this.calcForceExertedBy(p)*dy/this.calcDistance(p);
        return Fy;
    }

    public double calcForceExertedBy(Planet p){
        double r=this.calcDistance(p);
        double G=java.lang.Math.pow(10,-11)*6.67;
        double F=G*p.mass*this.mass/r/r;
        return F;
    }
    public double calcDistance(Planet B){
        double dx=B.xxPos-this.xxPos;
        double dy=B.yyPos-this.yyPos;
        double r=Math.sqrt(dx*dx+dy*dy);
        return r;
    }
    public double calcNetForceExertedByX(Planet ps[]){
        double Fx=0.0;
        int number=0;
        while (number!=ps.length){
            if (this.equals(ps[number])){
                number +=1;
                continue;
            }
            else {
                Fx += this.calcForceExertedByX(ps[number]);
                number +=1;
            }
        }
        return Fx;
    }
    public double calcNetForceExertedByY(Planet ps[]){
        double Fy=0.0;
        int number=0;
        while (number!=ps.length) {
            if (this.equals(ps[number])) {
                number +=1;
                continue;
            }
            else {
                Fy += this.calcForceExertedByY(ps[number]);
                number += 1;
            }
        }
        return Fy;
    }
    public Planet update(double dt,double fX,double fY){
    double ax=fX/this.mass;
    double ay=fY/this.mass;
     this.xxVel=this.xxVel+ax*dt;
     this.yyVel=this.yyVel+ay*dt;
     this.xxPos=this.xxPos+this.xxVel*dt;
    this.yyPos=this.yyPos+this.yyVel*dt;
    return this;}

    public void draw() {
        StdDraw.picture(this.xxPos,this.yyPos,this.imgFileName);
        StdDraw.show(1);

    }
}

