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
	//�����ؼ���
	String keyword = "�й�";
	
	Parser parser = new Parser();
	//��������ʼվ��
	String startSite = "";
	//�����������
	SearchResultBean srb = new SearchResultBean();
	//�������ؼ��������б�
	List<SearchResultBean> resultList = new ArrayList<SearchResultBean>();
	//�Ѿ�������վ���б�
	List<String> searchedSite = new ArrayList<String>();
	//������������б�
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
		    url = queue.peek().toString();//�����ж�
		    try {
		    	if (!isSearched(searchedSite, url)) {
		    		//���������Ƿ���������
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
	* ����HTML
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
			parser.setURL(url);// ������aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
			parser.setEncoding("GBK");
			URLConnection uc = parser.getConnection();
			uc.connect(); //������aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
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
	* ����HTML��ǩ
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
	* ����HTML��ǩ���
	* @param node
	* @throws Exception
	*/
    public void parserNode(Node node) throws Exception {
    	if (node instanceof StringNode) {//�ж��Ƿ����ı����
    		StringNode sNode = (StringNode) node;
    		StringFilter sf = new StringFilter(keyword,false);
    		search_key_words = sf.accept(sNode);
    		if (search_key_words) {
    			count++;
    		}
    		// System.out.println("text is :"+sNode.getText().trim());
    	} else if (node instanceof Tag) {//�ж��Ƿ��Ǳ�ǩ����
    		Tag atag = (Tag) node;
    		if (atag instanceof TitleTag) {//�ж��Ƿ��Ǳ�TITLE���
    			srb.setTitle(atag.getText());
    		}
    		if (atag instanceof LinkTag) {//�ж��Ƿ��Ǳ�LINK���
    			LinkTag linkatag = (LinkTag) atag;
    			checkLink(linkatag.getLink(), linkList);
    			// System.out.println("-----------------this is link --------------");
    		}
    		dealTag(atag);
    	} else if (node instanceof RemarkNode) {//�ж��Ƿ���ע��
    		// System.out.println("this is remark");
    	}
    }
    
    /**
     * ��������Ƿ���Ҫ�����ж�
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
	* ���������Ƿ��Ѿ���ɨ��
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
	* ���URL�Ƿ���������
	* @param urlToCheck
	* @return
	*/
	private boolean isRobotAllowed(URL urlToCheck) {
		String host = urlToCheck.getHost().toLowerCase();// ��ȡ����URL������
		// System.out.println("����="+host);
		// ��ȡ����������������URL����
		ArrayList<String> disallowList = disallowListCache.get(host);
		// �����û�л���,���ز����档
		if (disallowList == null) {
			disallowList = new ArrayList<String>();
			try {
				URL robotsFileUrl = new URL("http://" + host + "/robots.txt");
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(robotsFileUrl.openStream()));
				// ��robot�ļ���������������ʵ�·���б�
				String line;
				while ((line = reader.readLine()) != null) {
					if (line.indexOf("Disallow:") == 0) {// �Ƿ����"Disallow:"
						String disallowPath = line.substring("Disallow:"
								.length());// ��ȡ���������·��
						// ����Ƿ���ע�͡�
						int commentIndex = disallowPath.indexOf("#");
						if (commentIndex != -1) {
							disallowPath = disallowPath.substring(0,
									commentIndex);// ȥ��ע��
						}
						disallowPath = disallowPath.trim();
						disallowList.add(disallowPath);
					}
				}
				for (String it : disallowList) {
					System.out.println("Disallow is :" + it);
				}
				// �����������������ʵ�·����
				disallowListCache.put(host, disallowList);
			} catch (Exception e) {
				return true; // webվ���Ŀ¼��û��robots.txt�ļ�,������
			}
		}
		String file = urlToCheck.getFile();
		// System.out.println("�ļ�getFile()="+file);
		for (int i = 0; i < disallowList.size(); i++) {
		    String disallow = disallowList.get(i);
		    if (file.startsWith(disallow)) {
		    	return false;
		    }
		}
		return true;
	}
	
	
	public static void main(String[] args) {
	   Spider ph = new Spider("��ͼ", "http://www.baidu.com/");
	   try {
		    ph.processHtml("http://www.baidu.com/");
		    Thread search = new Thread(ph);
		    search.run();//�����߳�
	   } 
	   catch (Exception ex) {
		   
	   }
	   System.exit(0);
	}
}