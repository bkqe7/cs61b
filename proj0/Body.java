public class Body {
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	static final double G = 6.67e-11;

	public Body(double xP, double yP, double xV, double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m; 
		imgFileName = img;
		
	}

	public Body(Body b) {
		xxPos = b.xxPos;
		yyPos = b.yyPos;
		xxVel = b.xxVel;
		yyVel = b.yyVel;
		mass = b.mass;
		imgFileName = b.imgFileName;
	}

	public double calcDistance(Body m) {
		return Math.sqrt((m.xxPos - this.xxPos)*(m.xxPos-this.xxPos)+(m.yyPos - this.yyPos)*(m.yyPos - this.yyPos));
	}

	public double calcForceExertedBy(Body m) {
		return (G*m.mass*this.mass)/(this.calcDistance(m)*this.calcDistance(m));

	}

	public double calcForceExertedByX(Body m) {
		double F = this.calcForceExertedBy(m);
		double dx = m.xxPos - this.xxPos;
		double r = this.calcDistance(m);
		return F*dx/r;
	}

	public double calcForceExertedByY(Body m) {
		double F = this.calcForceExertedBy(m);
		double dy = m.yyPos - this.yyPos;
		double r = this.calcDistance(m);
		return F*dy/r;
	}

	public double calcNetForceExertedByX(Body[] planets) {
		double netForce = 0;
		int i =0;
		while (i<planets.length) {
			if (!this.equals(planets[i])) {
				netForce = netForce+ this.calcForceExertedByX(planets[i]);
			}
			i = i+1;

		}
		return netForce;

	}

	public double calcNetForceExertedByY(Body[] planets) {
		double netForce = 0;
		int i =0;
		while (i<planets.length) {
			if (!this.equals(planets[i])) {
				netForce = netForce+ this.calcForceExertedByY(planets[i]);
			}
			i = i+1;

		}
		return netForce;

	}

	public void update(double t, double Fx, double Fy) {
		double ax = Fx/this.mass;
		double ay = Fy/this.mass;
		this.xxVel = this.xxVel+ax*t;
		this.yyVel = this.yyVel+ay*t;
		this.xxPos = this.xxPos+this.xxVel*t;
		this.yyPos = this.yyPos + this.yyVel*t;			
		
	}



}