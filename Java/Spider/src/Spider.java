import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.htmlparser.RemarkNode;
import org.htmlparser.StringNode;
import org.htmlparser.tags.Tag;
import org.htmlparser.Node;
import org.htmlparser.tags.*;
import org.htmlparser.Parser;
import org.htmlparser.filters.StringFilter;
import org.htmlparser.util.NodeIterator;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import java.util.Queue;
import java.util.LinkedList;

/**
 * 
 * @author Xuan Li
 *
 */
public class Spider implements Runnable {
	
	/**************************Variables***********************************/
	
	boolean search_key_words = false;
	int count = 0;
	int limitsite = 10;
	int countsite = 1;
	//搜索关键字
	String keyword = "中国";
	
	Parser parser = new Parser();
	//搜索的起始站点
	String startSite = "";
	//保存搜索结果
	SearchResultBean srb = new SearchResultBean();
	//搜索到关键字链接列表
	List<SearchResultBean> resultList = new ArrayList<SearchResultBean>();
	//已经被搜索站点列表
	List<String> searchedSite = new ArrayList<String>();
	//需解析的链接列表
	Queue<String> linkList = new LinkedList<String>();
	
	HashMap<String, ArrayList<String>> disallowListCache = 
			new HashMap<String, ArrayList<String>>();
	/**************************Variables***********************************/
	
	
	
	/************************** Methods ***********************************/
	/**
	 * Constructor of Spider
	 * @param keyword
	 * @param startSite
	 */
	public Spider(String keyword, String startSite) {
	   this.keyword = keyword;
	   this.startSite = startSite;
	   linkList.add(startSite);
	   srb = new SearchResultBean();
	}
	
	/**
	 * start the search url function
	 */
	public void run() {
	   // TODO Auto-generated method stub
		search(linkList);
	}
	
	/**
	 * Search all the urls saved in the queue and remove them from the queue
	 * @param queue
	 */
	public void search(Queue<String> queue) {
		String url = "";
	    while(!queue.isEmpty()) {
		    url = queue.peek().toString();//查找列队
		    try {
		    	if (!isSearched(searchedSite, url)) {
		    		//检查该链接是否被允许搜索
		    		if (isRobotAllowed(new URL(url)))
		    			processHtml(url);
		    		else
		    			System.out.println("this page is disallowed to search");
		    	}
		    } 
		    catch (Exception ex) {
		    	
		    }
		    queue.remove();
	    }
	}

	/**
	* 解析HTML
	* @param url 
	* @throws ParserException
	* @throws Exception
	*/
	public void processHtml(String url) throws ParserException, Exception {
		searchedSite.add(url);
		count = 0;
		System.out.println("searching ... :" + url);
		System.out.println(url);
		try {
			parser.setURL("http://www.baidu.com");
			parser.setURL(url);// 有问题aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
			parser.setEncoding("GBK");
			URLConnection uc = parser.getConnection();
			uc.connect(); //有问题aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
			//uc.getLastModified();
		}
		catch (Exception ex) {
			System.out.println(ex);
		}
		NodeIterator nit = parser.elements();
		while (nit.hasMoreNodes()) {
				Node node = nit.nextNode();
			    parserNode(node);
		}
		srb.setKeywords(keyword);
		srb.setUrl(url);
		srb.setCount_key_words(count);
		resultList.add(srb);
		System.out.println("count keywords is :" + count);
		System.out.println("----------------------------------------------");
	}

	/**
	* 处理HTML标签
	* @param tag
	* @throws Exception
	*/
	public void dealTag(Tag tag) throws Exception {
		NodeList list = tag.getChildren();
		if (list != null) {
			NodeIterator it = list.elements();
			while (it.hasMoreNodes()) {
				Node node = it.nextNode();
				parserNode(node);
			}
		}
	}
	
	/**
	* 处理HTML标签结点
	* @param node
	* @throws Exception
	*/
    public void parserNode(Node node) throws Exception {
    	if (node instanceof StringNode) {//判断是否是文本结点
    		StringNode sNode = (StringNode) node;
    		StringFilter sf = new StringFilter(keyword,false);
    		search_key_words = sf.accept(sNode);
    		if (search_key_words) {
    			count++;
    		}
    		// System.out.println("text is :"+sNode.getText().trim());
    	} else if (node instanceof Tag) {//判断是否是标签库结点
    		Tag atag = (Tag) node;
    		if (atag instanceof TitleTag) {//判断是否是标TITLE结点
    			srb.setTitle(atag.getText());
    		}
    		if (atag instanceof LinkTag) {//判断是否是标LINK结点
    			LinkTag linkatag = (LinkTag) atag;
    			checkLink(linkatag.getLink(), linkList);
    			// System.out.println("-----------------this is link --------------");
    		}
    		dealTag(atag);
    	} else if (node instanceof RemarkNode) {//判断是否是注释
    		// System.out.println("this is remark");
    	}
    }
    
    /**
     * 检查链接是否需要加入列队
     */
    public void checkLink(String link, Queue<String> queue) {
    	if (link != null && !link.equals("") && link.indexOf("#") == -1) {
    		if (!link.startsWith("http://") && !link.startsWith("ftp://")
    				&& !link.startsWith("www.")) {
    			link = "file:///" + link;
    		} else if (link.startsWith("www.")) {
    			link = "http://" + link;
    		}
    		if (queue.isEmpty()) {
    			queue.add(link);
    		} else {
    			String link_end_=link.endsWith("/")?link.substring(0,
    					link.lastIndexOf("/")):(link+"/");
    			if (!queue.contains(link)&&!queue .contains(link_end_)) {
    				queue.add(link);
    			}
    		}
	   }
    }

    /**
	* 检查该链接是否已经被扫描
	* @param list
	* @param url
	* @return
	*/
	public boolean isSearched(List<String> list, String url) {
		String url_end_ = "";
		if (url.endsWith("/")) {
			url_end_ = url.substring(0, url.lastIndexOf("/"));
		} else {
			url_end_ = url + "/";
		}
		if (list.size() > 0) {
			if (list.indexOf(url) != -1 || list.indexOf(url_end_) != -1) {
				return true;
			}
		}
	   return false;
	}
	
	/**
	* 检查URL是否被允许搜索
	* @param urlToCheck
	* @return
	*/
	private boolean isRobotAllowed(URL urlToCheck) {
		String host = urlToCheck.getHost().toLowerCase();// 获取给出URL的主机
		// System.out.println("主机="+host);
		// 获取主机不允许搜索的URL缓存
		ArrayList<String> disallowList = disallowListCache.get(host);
		// 如果还没有缓存,下载并缓存。
		if (disallowList == null) {
			disallowList = new ArrayList<String>();
			try {
				URL robotsFileUrl = new URL("http://" + host + "/robots.txt");
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(robotsFileUrl.openStream()));
				// 读robot文件，创建不允许访问的路径列表。
				String line;
				while ((line = reader.readLine()) != null) {
					if (line.indexOf("Disallow:") == 0) {// 是否包含"Disallow:"
						String disallowPath = line.substring("Disallow:"
								.length());// 获取不允许访问路径
						// 检查是否有注释。
						int commentIndex = disallowPath.indexOf("#");
						if (commentIndex != -1) {
							disallowPath = disallowPath.substring(0,
									commentIndex);// 去掉注释
						}
						disallowPath = disallowPath.trim();
						disallowList.add(disallowPath);
					}
				}
				for (String it : disallowList) {
					System.out.println("Disallow is :" + it);
				}
				// 缓存此主机不允许访问的路径。
				disallowListCache.put(host, disallowList);
			} catch (Exception e) {
				return true; // web站点根目录下没有robots.txt文件,返回真
			}
		}
		String file = urlToCheck.getFile();
		// System.out.println("文件getFile()="+file);
		for (int i = 0; i < disallowList.size(); i++) {
		    String disallow = disallowList.get(i);
		    if (file.startsWith(disallow)) {
		    	return false;
		    }
		}
		return true;
	}
	
	
	public static void main(String[] args) {
	   Spider ph = new Spider("地图", "http://www.baidu.com/");
	   try {
		    ph.processHtml("http://www.baidu.com/");
		    Thread search = new Thread(ph);
		    search.run();//启动线程
	   } 
	   catch (Exception ex) {
		   
	   }
	   System.exit(0);
	}
}