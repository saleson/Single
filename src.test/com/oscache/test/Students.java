package com.oscache.test;

	import java.io.Serializable;

	/**
	 * 这个类是一个简单的学生类，用于测试OSCache缓存对象用
	 * @author tincotian
	 *
	 */
	public class Students implements Serializable{
		/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
		//学生ID
		public String ID;
		//学生姓名
		public String name;
	    //学生成绩
		public String score;
		
		public TestT testT;
		
		public Students(){
			
		}
		
		public Students(String studentsID) {
			this.ID=studentsID;
		}
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getScore() {
			return score;
		}
		public void setScore(String score) {
			this.score = score;
		}
		public String getID() {
			return ID;
		}
		public void setID(String id) {
			ID = id;
		}

		public TestT getTestT() {
			return testT;
		}

		public void setTestT(TestT testT) {
			this.testT = testT;
		}

}
