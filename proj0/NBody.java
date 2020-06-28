public class NBody {

	public static double readRadius(String fileName) {
		In in = new In(fileName);

		int N = in.readInt();
		double radius = in.readDouble();
		return radius;

	}

	public static Body[] readBodies(String fileName) {
		In in = new In(fileName);
		int N = in.readInt();
		double radius = in.readDouble();
		Body[] bodies = new Body[N];

		for(int i=0; i<N;i++) {

			double xP = in.readDouble();
			double yP = in.readDouble();
			double xV = in.readDouble();
			double yV = in.readDouble();
			double mass = in.readDouble();
			String img = in.readString();
			bodies[i] = new Body(xP, yP, xV, yV,mass, img);
		}
		return bodies;
	}

	public static void main(String[] args) {
		 
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		Body[] bodies = readBodies(filename);
		double radius = readRadius(filename);

		StdDraw.setScale(-radius, radius);
		StdDraw.clear();
		StdDraw.picture(0,0,"images/starfield.jpg");

		int N = bodies.length;

		for(int i=0;i<N;i++) {
			bodies[i].draw();
		}

		StdDraw.enableDoubleBuffering();
		double time =0;
		while(time <T) {
			double[] xForces = new double[N];
			double[] yForces = new double[N];

			for(int i =0;i<N;i++) {
				xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
				yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
			}

			for(int i=0;i<N;i++) {
				bodies[i].update(dt,xForces[i],yForces[i]);
			}
			StdDraw.picture(0,0,"images/starfield.jpg");
			for(int i=0;i<N;i++) {
				bodies[i].draw();
			}
			StdDraw.show();
			StdDraw.pause(10);

			time = time+dt;

		}

		StdOut.printf("%d\n", bodies.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < bodies.length; i++) {
			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
                  bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);
    		
		}


	}
}