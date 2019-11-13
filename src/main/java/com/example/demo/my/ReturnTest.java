package com.example.demo.my;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ReturnTest {
	private TestClass testClass = new TestClass();

	public static void say(int a) {
		int b = a * 10;
		System.out.println("hello:" + b);
		// return b;
	}

	public int getIntVal(int in) {
//      if (in < 7) {
//      System.out.println("in < 7, return");
//      throw new UnsupportedOperationException("test");
//  }
		if (in == 5) {
			String msg = null;
			// produce npe
			in += msg.length();
		}

		long startTime = System.currentTimeMillis() + fibonacci(2);
		String strTag = "the return/throw line test tag";
		if (in < 0) {
			return strTag.charAt(0);
		} else if (in == 0) {
			return 1000;
		}
		// > 0
		if (in < 2) {
			double dbVal = 1.1;
			return (int) (dbVal + 100);
		} else if (in == 2) {
			float fVal = 1.2f;
			return (int) (fVal + 200);
		}
		// > 2
		if (in % 2 == 0) {
			Random random = new Random();
			int rdm = random.nextInt(100);
			if (rdm >= 50) {
				throw new IllegalArgumentException("npe test");
			} else if (rdm <= 20) {
				throw new NullPointerException("< 20");
			}
			// end time
			long end = System.currentTimeMillis();
			long cost = startTime - end;
			int ret = testClass.test(in);
			return (int) (rdm * 10 + ret + (cost / 1000));
		} else {
			ParamModel paramModel = new ParamModel();
			paramModel.setIntVal(in);
			paramModel.setDoubleVal(1.0 * in);
			int subVal = getSubIntVal(paramModel);

			if (subVal == 100) {
				throw new IllegalArgumentException("err occ with in:" + subVal);
			}

			throw new IllegalStateException("error occ with in:" + in);
		}
	}

	/**
	 * 不支持递归函数
	 *
	 * @param n
	 * @return
	 */
	public int fibonacci(int n) {
		if (n < 0) {
			return -1;
		}
		if (n == 0) {
			return 0;
		}
		if (n <= 2) {
			return 1;
		}
		return fibonacci(n - 1) + fibonacci(n - 2);
	}

	public int getSubIntVal(ParamModel paramModel) {
		if (paramModel == null) {
			return -1;
		}
		if (paramModel.getIntVal() <= 0) {
			return (int) paramModel.getDoubleVal();
		} else if (paramModel.getIntVal() <= 5) {
			return 100;
		} else if (paramModel.getIntVal() <= 8) {
			return 200;
		} else {
			throw new RuntimeException("ill");
		}
	}

	public static void main(String[] args) {
		new Thread(new Runnable() {
			private Random random = new Random();
			private ReturnTest returnTest = new ReturnTest();

			@Override
			public void run() {
				while (true) {
					try {
						System.err.println(returnTest.getIntVal(random.nextInt(10)));
						TimeUnit.MILLISECONDS.sleep(5);
					} catch (Exception e) {
						// e.printStackTrace();
						// System.out.println("error:" + e.getMessage());
					}
				}
			}
		}).start();
	}
	
	public class TestClass {

		public int test(int in) {

			if (in == 5) {
				return 100;
			}
			String tag = "the in:" + in;
			if (in < 5) {
				in += 2;
			} else {
				in -= 1;
			}

			if (in > 5) {
				throw new IllegalArgumentException("must <= 5");
			}
			if (in <= 3) {
				throw new NullPointerException("must >= 3");
			}

			return in * 100;
		}
	}
}

