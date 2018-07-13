package cn.lancel0t.blog.vo;

public class Result<T> {
	private int code;// 返回码
	private String msg;// 返回信息
	private T data; // 返回数据

	// 成功时候的调用
	public static <T> Result<T> success(T data) {
		return new Result<T>(data);
	}

	// 失败时候的调用
	public static <T> Result<T> error(CodeMsg cm) {
		return new Result<T>(cm);
	}

	private Result(T data) {
		this.code = 0;
		this.msg = "success";
		this.data = data;
	}

	private Result(CodeMsg cm) {
		if (cm == null) {
			return;
		}
		this.code = cm.getCode();
		this.msg = cm.getMsg();
	}

	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	public T getData() {
		return data;
	}
}

class CodeMsg {
	private int code;
	private String msg;

	// 通用异常
	public static CodeMsg SUCCESS = new CodeMsg(0, "success");
	public static CodeMsg SERVER_ERROR = new CodeMsg(500100, "服务端异常");
	// 登录模块 5002XX

	// 商品模块 5003XX

	// 订单模块 5004XX

	// 秒杀模块 5005XX

	private CodeMsg(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
}
