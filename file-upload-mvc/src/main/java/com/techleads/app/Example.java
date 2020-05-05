package com.techleads.app;

class Example {
	int x = 10;
	int y = 20;

	void m1() {
		x = 5;
	}
}

class Sample {
	static void m2(Example[] e) {
		e[2].m1();
	}
}

class Test {
	public static void main(String arg[]) {
		Example[] e = { new Example(), new Example(), new Example(), new Example() };
		Sample.m2(e);
		for (int i = 0; i < e.length; i++) {
			System.out.println(e[i].x);
			System.out.println(e[i].y);
		}
	}
}