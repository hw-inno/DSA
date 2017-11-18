class h implements e, f, g {
	public void foo() {
		System.out.println("IS: ");
		System.out.println(this instanceof a);
		System.out.println(this instanceof b);
		System.out.println(this instanceof c);
		System.out.println(this instanceof d);
		System.out.println(this instanceof e);
		System.out.println(this instanceof f);
		System.out.println(this instanceof g);
	}

	public static void main(String[] args) {
		new h().foo();	
	}
}