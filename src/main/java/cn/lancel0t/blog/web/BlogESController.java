package cn.lancel0t.blog.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/*
 * 博客 Elastic Search Controller
 */
@RestController
@RequestMapping("/blogs")
public class BlogESController {

	@Autowired
	private RestTemplate restTemplate;

	/**
	 * 自定义搜索
	 * 
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String search(@RequestBody String query) {
		final String url = "http://127.0.0.1:9200/blog/blog/_search";
		JSONObject jsonObject = JSON.parseObject(query);
		JSONObject json1 = restTemplate.postForEntity(url, jsonObject, JSONObject.class).getBody();
		return json1.toJSONString();
	}
}