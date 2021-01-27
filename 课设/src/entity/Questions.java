package entity;

/**
 * @author 玉竹
 */
public class Questions {
	private String title;
	private String answer;
	private String question;
	private String A;
	private String B;
	private String C;
	private String D;

	/**
	 *
	 * @param title  题目 + 选项
	 * @param answer  答案
	 */
	public Questions(String title, String answer) {
		super();
		this.title = title;
		this.answer = answer;
	}

	/**
	 *
	 * @param question 题目
	 * @param a A选项内容
	 * @param b B选项内容
	 * @param c C选项内容
	 * @param d D选项内容
	 * @param answer 答案
	 */
	public Questions(String question, String a, String b, String c, String d, String answer) {
		super();
		this.answer = answer;
		this.question = question;
		A = a;
		B = b;
		C = c;
		D = d;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getA() {
		return A;
	}

	public void setA(String a) {
		A = a;
	}

	public String getB() {
		return B;
	}

	public void setB(String b) {
		B = b;
	}

	public String getC() {
		return C;
	}

	public void setC(String c) {
		C = c;
	}

	public String getD() {
		return D;
	}

	public void setD(String d) {
		D = d;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
}